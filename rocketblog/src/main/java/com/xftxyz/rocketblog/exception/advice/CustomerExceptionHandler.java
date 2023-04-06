package com.xftxyz.rocketblog.exception.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.xftxyz.rocketblog.exception.blog.BlogNotExistException;
import com.xftxyz.rocketblog.exception.image.ImageException;
import com.xftxyz.rocketblog.exception.user.AlreadyDoneException;
import com.xftxyz.rocketblog.exception.user.CaptchaErrorException;
import com.xftxyz.rocketblog.exception.user.EmailExistException;
import com.xftxyz.rocketblog.exception.user.EmailOrPasswordErrorException;
import com.xftxyz.rocketblog.exception.user.IllegalOperationException;
import com.xftxyz.rocketblog.exception.user.NotLoginException;
import com.xftxyz.rocketblog.exception.user.PasswordErrorException;
import com.xftxyz.rocketblog.exception.user.SelfOperationException;
import com.xftxyz.rocketblog.exception.user.UserNotExistException;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultMessageEnum;

@RestControllerAdvice
public class CustomerExceptionHandler {
    // 用户未登录异常
    @ExceptionHandler(NotLoginException.class)
    public Result<Object> handleNotLoginException(NotLoginException e) {
        return Result.error(ResultMessageEnum.USER_NOT_LOGIN.getCode(), ResultMessageEnum.USER_NOT_LOGIN.getMessage());
    }

    // 用户不存在异常
    @ExceptionHandler(UserNotExistException.class)
    public Result<Object> handleUserNotExistException(UserNotExistException e) {
        return Result.error(ResultMessageEnum.USER_NOT_EXIST.getCode(),
                e.getMessage() + ResultMessageEnum.USER_NOT_EXIST.getMessage());
    }

    // 已经执行过该操作
    @ExceptionHandler(AlreadyDoneException.class)
    public Result<Object> handleAlreadyDoneException(AlreadyDoneException e) {
        return Result.error(ResultMessageEnum.ALREADY_DONE.getCode(), e.getMessage());
    }

    // 验证码错误
    @ExceptionHandler(CaptchaErrorException.class)
    public Result<Object> handleCaptchaErrorException(CaptchaErrorException e) {
        return Result.error(ResultMessageEnum.CAPTCHA_ERROR.getCode(), ResultMessageEnum.CAPTCHA_ERROR.getMessage());
    }

    // 邮箱已存在
    @ExceptionHandler(EmailExistException.class)
    public Result<Object> handleEmailExistException(EmailExistException e) {
        return Result.error(ResultMessageEnum.EMAIL_EXIST.getCode(), ResultMessageEnum.EMAIL_EXIST.getMessage());
    }

    // 邮箱或密码错误
    @ExceptionHandler(EmailOrPasswordErrorException.class)
    public Result<Object> handleEmailOrPasswordErrorException(EmailOrPasswordErrorException e) {
        return Result.error(ResultMessageEnum.EMAIL_OR_PASSWORD_ERROR.getCode(),
                ResultMessageEnum.EMAIL_OR_PASSWORD_ERROR.getMessage());
    }

    // 密码错误
    @ExceptionHandler(PasswordErrorException.class)
    public Result<Object> handlePasswordErrorException(PasswordErrorException e) {
        return Result.error(ResultMessageEnum.PASSWORD_ERROR.getCode(), ResultMessageEnum.PASSWORD_ERROR.getMessage());
    }

    // 自己操作自己
    @ExceptionHandler(SelfOperationException.class)
    public Result<Object> handleSelfOperationException(SelfOperationException e) {
        return Result.error(ResultMessageEnum.SELF_OPERATION.getCode(), ResultMessageEnum.SELF_OPERATION.getMessage());
    }

    // 图片异常
    @ExceptionHandler(ImageException.class)
    public Result<Object> handleIOException(ImageException e) {
        return Result.error(ResultMessageEnum.IMAGE_ERROR.getCode(), e.getMessage());
    }

    // 博客不存在
    @ExceptionHandler(BlogNotExistException.class)
    public Result<Object> handleBlogNotExistException(BlogNotExistException e) {
        return Result.error(ResultMessageEnum.BLOG_NOT_EXIST.getCode(), e.getMessage());
    }

    // 非法操作
    @ExceptionHandler(IllegalOperationException.class)
    public Result<Object> handleIllegalOperationException(IllegalOperationException e) {
        return Result.error(ResultMessageEnum.ILLEGAL_OPERATION.getCode(), e.getMessage());
    }

    // 未知异常
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        return Result.error(ResultMessageEnum.UNKNOWN_ERROR.getCode(), e.getMessage());
    }
}
