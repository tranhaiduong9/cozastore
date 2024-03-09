package com.member.cozastore.controller;

import com.google.gson.Gson;
import com.member.cozastore.email.EmailService;
import com.member.cozastore.payload.BaseResponse;
import com.member.cozastore.payload.Response;
import com.member.cozastore.payload.request.SignUpRequest;
import com.member.cozastore.payload.response.VerifyEmailResponse;
import com.member.cozastore.service.Imp.LoginServiceImp;
import com.member.cozastore.util.JwtHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private Response response;
    @Autowired
    private EmailService emailService;
    @Autowired
    private LoginServiceImp loginServiceImp;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private Gson gson = new Gson();
    @Value("${testing.email}")
    private String testingEmail;

    @GetMapping()
    public ResponseEntity<?> login() {
        return new ResponseEntity<>("vào trang login", HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String email, @RequestParam String password){
        //Create secret key
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        String secretKey = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println("kiemtra: " + secretKey);
        UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(email, password);
        BaseResponse baseResponse = new BaseResponse();

        try {
            if(loginServiceImp.isUserVerify(email)){
                authenticationManager.authenticate(authen);

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
                String jsonRole = gson.toJson(roles);
                String token = jwtHelper.generateToken(jsonRole);

                baseResponse = response.baseResponse(200,"", token);

                return new ResponseEntity<>(baseResponse, HttpStatus.OK);
            } else {
                baseResponse = response.baseResponse(200,"Unverified email", "");
                return new ResponseEntity<>(baseResponse, HttpStatus.FORBIDDEN);
            }

        }catch (Exception e) {
            baseResponse = response.baseResponse(200, "Signin Error: " + e.getLocalizedMessage(), "");
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup (@Valid @RequestBody SignUpRequest signUpRequest) {
        boolean isSuccess = false;
        boolean isEmailExist = loginServiceImp.isEmailExist(signUpRequest.getEmail());
        BaseResponse baseResponse = new BaseResponse();
        if (!isEmailExist) {
            isSuccess = loginServiceImp.insertUser(signUpRequest);
            String signupToken = jwtHelper.generateSignupToken("");
            loginServiceImp.insertVerifyToken(signupToken, signUpRequest.getEmail());

            String body = "Click here to verify: http://127.0.0.1:5500/fe_cozastore/verify-announ.html?token="+signupToken;
            emailService.sendEmail(signUpRequest.getEmail(), "Sign up success", body);

            baseResponse = response.baseResponse(200,"",isSuccess);
        } else {
            baseResponse = response.baseResponse(200,"Email Exists", isSuccess);
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/signup/verify")
    public ResponseEntity<?> signupVeryfy (@RequestParam String token) {
        VerifyEmailResponse verifyEmailResponse = loginServiceImp.verifyToken(token);

        BaseResponse baseResponse = response.baseResponse(200,"",verifyEmailResponse);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/forgot")
    public ResponseEntity<?> sendForgotPasswordEmail (String email) {
        boolean isSuccess = false;
        BaseResponse baseResponse = new BaseResponse();
        if (loginServiceImp.isEmailExist(email)){
            String body = "Click here to reset your password: http://127.0.0.1:5500/fe_cozastore/reset-password.html?email="+email;
            emailService.sendEmail(email, "Reset password", body );
            isSuccess = true;
            baseResponse = response.baseResponse(200,"",isSuccess);
        } else {
            baseResponse = response.baseResponse(200,"No user found",isSuccess);
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("/reset")
    public ResponseEntity<?> resetPassword (String email, String password) {
        boolean isSuccess = loginServiceImp.resetPassword(email, password);
        BaseResponse baseResponse = response.baseResponse(200,"reset password successfully",isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}