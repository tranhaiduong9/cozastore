package com.member.cozastore.service.imp;

import com.member.cozastore.payload.request.SignUpRequest;
import com.member.cozastore.payload.response.VerifyEmailResponse;

public interface LoginServiceImp {

    boolean insertUser(SignUpRequest signUpRequest);

    boolean insertVerifyToken(String verifyToken, String email);

    VerifyEmailResponse verifyToken(String verifyToken);

    boolean isEmailExist (String email);
}
