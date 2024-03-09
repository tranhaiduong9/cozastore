package com.member.cozastore.payload;

import org.springframework.stereotype.Component;

@Component
public class Response {
    public BaseResponse baseResponse(int statusCode, String message, Object data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(statusCode);
        baseResponse.setMessage(message);
        baseResponse.setData(data);
        return baseResponse;
    }
}
