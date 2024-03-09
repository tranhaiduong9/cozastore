package com.member.cozastore.payload.response;

import lombok.Getter;
import lombok.Setter;

public class VerifyEmailResponse {
    @Getter
    @Setter
    private boolean isSuccess;

    @Getter
    @Setter
    private String status;

}
