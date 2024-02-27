package com.member.cozastore.service.imp;

import com.member.cozastore.payload.request.SignUpRequest;

public interface LoginServiceImp {

    boolean insertUser(SignUpRequest signUpRequest);
}
