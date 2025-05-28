package com.example.pricesscraper.utils;

import com.example.pricesscraper.common.ErrorCode;
import com.example.pricesscraper.exception.BusinessException;

public class ThrowUtils {
    public static void ThrowIf(boolean condition , RuntimeException runtimeException){
        if(condition){
            throw runtimeException;
        }
    }
    public static void ThrowIf(boolean condition , ErrorCode errorCode){
        ThrowIf(condition , new BusinessException(errorCode));
    }
    public static void ThrowIf(boolean condition , ErrorCode errorCode , String message){
        ThrowIf(condition , new BusinessException(errorCode,message));
    }
}
