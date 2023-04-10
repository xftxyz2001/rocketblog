package com.xftxyz.rocketblog.exception.advice;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.xftxyz.rocketblog.exception.blog.BlogNotExistException;
import com.xftxyz.rocketblog.exception.chat.NoChatException;
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

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomerExceptionHandler {

    // 没有与该用户的聊天
    @ExceptionHandler(NoChatException.class)
    public Result<Object> handleNotChatException(NoChatException e) {
        return Result.error(ResultMessageEnum.NO_CHAT.getCode(), ResultMessageEnum.NO_CHAT.getMessage());
    }

    // 用户未登录异常
    @ExceptionHandler(NotLoginException.class)
    public Result<Object> handleNotLoginException(NotLoginException e) {
        return Result.error(ResultMessageEnum.USER_NOT_LOGIN.getCode(), e.getMessage());
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

    // 请求参数校验失败异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = result.getFieldErrors().stream()
                .map(error -> String.format("%s : %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return Result.error(ResultMessageEnum.PARAM_VALID_ERROR.getCode(), "请求参数校验失败" + errorMessages);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Object> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> errorMessages = violations.stream()
                .map(violation -> String.format("%s : %s", violation.getPropertyPath(), violation.getMessage()))
                .collect(Collectors.toList());
        return Result.error(ResultMessageEnum.PARAM_VALID_ERROR.getCode(), "请求参数校验失败" + errorMessages);
    }

    // 请求参数缺失
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return Result.error(ResultMessageEnum.PARAM_ERROR.getCode(),
                ResultMessageEnum.PARAM_ERROR.getMessage() + ": " + e.getParameterName() + "不能为空");
    }

    // HTTP消息不可读异常
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return Result.error(ResultMessageEnum.PARAM_ERROR.getCode(), ResultMessageEnum.PARAM_ERROR.getMessage());
    }

    // 请求参数异常
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return Result.error(ResultMessageEnum.PARAM_ERROR.getCode(),
                ResultMessageEnum.PARAM_ERROR.getMessage() + ": " + e.getName() + "应为" + e.getRequiredType());
    }

    // 未知异常
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        log.error("出现未知异常：", e);
        return Result.error(ResultMessageEnum.UNKNOWN_ERROR.getCode(), e.getMessage());
    }
}
