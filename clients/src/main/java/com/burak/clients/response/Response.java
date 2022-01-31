package com.burak.clients.response;


import net.minidev.json.annotate.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public abstract class Response {

    private Timestamp timestamp;
    private Integer code;
    private HttpStatus status;
    private String message;

    Response(HttpStatus status){
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Integer getCode() {
        return code;
    }

    @JsonIgnore
    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                '}';
    }
}
