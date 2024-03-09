package com.member.cozastore.service.Imp;

import com.member.cozastore.entity.UserEntity;
import com.member.cozastore.payload.request.SignUpRequest;
import com.member.cozastore.payload.response.VerifyEmailResponse;
import org.apache.catalina.User;

public interface LoginServiceImp {

    boolean insertUser(SignUpRequest signUpRequest);

    boolean insertVerifyToken(String verifyToken, String email);

    VerifyEmailResponse verifyToken(String verifyToken);

    boolean isEmailExist (String email);

    boolean isUserVerify (String email);

    boolean resetPassword (String email, String password);
}
