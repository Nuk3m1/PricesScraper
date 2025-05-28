package com.example.pricesscraper.utils;

import com.example.pricesscraper.common.BaseResponse;
import com.example.pricesscraper.common.ErrorCode;
import org.jsoup.Connection;

public class ResultUtils {

    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0,data,"success!");
    }
    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }
    public static BaseResponse error(int code , String message){
        return new BaseResponse<>(code,null,message);
    }
    public static BaseResponse error(ErrorCode errorCode , String message){
        return new BaseResponse<>(errorCode.getCode(),null,message);
    }
}
