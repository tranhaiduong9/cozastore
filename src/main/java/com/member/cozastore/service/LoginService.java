package com.member.cozastore.service;

import com.member.cozastore.entity.UserEntity;
import com.member.cozastore.payload.BaseResponse;
import com.member.cozastore.payload.request.SignUpRequest;
import com.member.cozastore.repository.UserRepository;
import com.member.cozastore.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

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

}
