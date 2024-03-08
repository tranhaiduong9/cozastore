package com.member.cozastore.payload.request;

import lombok.Getter;
import lombok.Setter;

public class VerifyTokenRequest {
    @Getter
    @Setter
    private int idUser;

    @Getter
    @Setter
    private String token;
}
