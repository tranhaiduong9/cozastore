package com.member.cozastore.service;

import com.member.cozastore.entity.RoleEntity;
import com.member.cozastore.entity.UserEntity;
import com.member.cozastore.entity.VerificationTokenEntity;
import com.member.cozastore.payload.BaseResponse;
import com.member.cozastore.payload.request.SignUpRequest;
import com.member.cozastore.payload.request.VerifyTokenRequest;
import com.member.cozastore.payload.response.VerifyEmailResponse;
import com.member.cozastore.repository.UserRepository;
import com.member.cozastore.repository.VerificationTokenRepository;
import com.member.cozastore.service.Imp.LoginServiceImp;
import com.member.cozastore.util.JwtHelper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public boolean insertUser(SignUpRequest signUpRequest) {
        boolean isSuccess = false;
        UserEntity user = new UserEntity();
        user.setUsername(signUpRequest.getUserName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        try {
            userRepository.save(user);
            isSuccess = true;
        } catch (Exception e) {
            System.out.println("Thêm user thất bại" + e.getLocalizedMessage());
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public boolean insertVerifyToken(String verifyToken, String email) {
        boolean isSuccess = false;

        VerificationTokenEntity verificationToken = new VerificationTokenEntity();
        verificationToken.setToken(verifyToken);

        UserEntity user = userRepository.findByEmail(email);
        verificationToken.setUser(user);
        verificationToken.setExpiredDate(jwtHelper.getExprirationToken(verifyToken));
        try {
            verificationTokenRepository.save(verificationToken);
            isSuccess = true;
        } catch (Exception e) {
            System.out.println("Lỗi thêm verify token: " + e.getLocalizedMessage());
            isSuccess = false;
        }
        return isSuccess;
    }

    @Override
    public VerifyEmailResponse verifyToken(String token) {
        VerifyEmailResponse verifyEmailResponse = new VerifyEmailResponse();
        VerificationTokenEntity verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken != null && verificationToken.getToken().equals(token)) {
            Date date = new Date();
            long now = date.getTime();
            if (verificationToken.getExpiredDate().getTime() >= now) {
                verificationTokenRepository.deleteById(verificationToken.getId());
                userRepository.updateVerifyById(verificationToken.getUser().getId());
                verifyEmailResponse.setSuccess(true);
                verifyEmailResponse.setStatus("Verified successfully");
            } else {
                userRepository.deleteById(verificationToken.getUser().getId());
                verifyEmailResponse.setSuccess(false);
                verifyEmailResponse.setStatus("Verification has expired");
            }
        } else if (verificationToken != null){
            verifyEmailResponse.setSuccess(false);
            verifyEmailResponse.setStatus("Invalid verification");
        } else {
            verifyEmailResponse.setSuccess(false);
            verifyEmailResponse.setStatus("Can not find email or email verified");
        }
        return verifyEmailResponse;
    }

    @Override
    public boolean isEmailExist(String email) {
        boolean isExist = false;
        UserEntity user = userRepository.findByEmail(email);
        if(user == null) {
            isExist = false;
        } else {
            isExist = true;
        }
        return isExist;
    }

    @Override
    public boolean isUserVerify(String email) {
        boolean isVerify = false;
        UserEntity userEntity = userRepository.findByEmail(email);
        isVerify = userEntity.getIsVerify() == 0 ? false : true;
        return isVerify;
    }

    @Override
    public boolean resetPassword(String email, String password) {
        boolean isSuccess = false;
        String newPass = passwordEncoder.encode(password);
        isSuccess = userRepository.updatePasswordByEmail(email, newPass) == 0 ? false : true;
        return isSuccess;
    }
}
