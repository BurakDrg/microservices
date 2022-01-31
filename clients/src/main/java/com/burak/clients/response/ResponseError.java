package com.burak.clients.response;

import org.springframework.http.HttpStatus;

public class ResponseError extends  Response{

    private Integer code;
    private String message;

    public ResponseError(HttpStatus status, String message){
        super(status);
        this.code = status.value();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ResponseError{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
