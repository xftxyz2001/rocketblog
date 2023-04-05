package com.xftxyz.rocketblog.exception.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.xftxyz.rocketblog.exception.user.UserNotExistException;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultMessageEnum;

@RestControllerAdvice
public class CustomerExceptionHandler {
    // 用户不存在异常
    @ExceptionHandler(UserNotExistException.class)
    public Result<Object> handleUserNotExistException(UserNotExistException e) {
        return Result.error(ResultMessageEnum.USER_NOT_EXIST.getCode(),
                e.getMessage() + ResultMessageEnum.USER_NOT_EXIST.getMessage());
    }

    // 未知异常
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        return Result.error(ResultMessageEnum.UNKNOWN_ERROR.getCode(), e.getMessage());
    }
}
