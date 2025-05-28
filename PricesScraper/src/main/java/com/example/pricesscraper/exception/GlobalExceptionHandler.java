package com.example.pricesscraper.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import com.example.pricesscraper.common.BaseResponse;
import com.example.pricesscraper.common.ErrorCode;
import com.example.pricesscraper.utils.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> BusinessExcptionHandler(BusinessException e){
        return ResultUtils.error(e.getCode(),e.getMessage());

    }
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> RuntimeExceptionHandler(RuntimeException e){
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,"系统错误！");
    }

    @ExceptionHandler(NotLoginException.class)
    public BaseResponse<?> notLoginExceptionHandler(RuntimeException e) {
        return ResultUtils.error(ErrorCode.NOT_LOGIN_ERROR, "未登录");
    }


}
