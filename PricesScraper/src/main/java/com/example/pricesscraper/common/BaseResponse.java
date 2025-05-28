package com.example.pricesscraper.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {

    private int code;
    private T data;
    private String message;

    public BaseResponse(int code , T data , String message){
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public BaseResponse(int code , T data){
        this.code = code;
        this.data = data;
        this.message = "";
    }
    public BaseResponse(ErrorCode errorcode){
        this.code = errorcode.getCode();
        this.data = null;
        this.message = errorcode.getMessage();

    }



}
