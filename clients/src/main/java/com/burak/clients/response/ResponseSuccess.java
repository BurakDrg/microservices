package com.burak.clients.response;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.http.HttpStatus;

public class ResponseSuccess extends Response{

    private Integer code;
    private String message;

    public ResponseSuccess(HttpStatus status){
        super(status);
        this.code = status.value();
        this.message = "Successful";
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
        return "ResponseSuccess{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
