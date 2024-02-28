package com.member.cozastore.payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class BaseResponse {
    private int statusCode;
    private String message;
    private Object data;
}
