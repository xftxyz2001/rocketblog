package com.xftxyz.rocketblog.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        // 返回此可抛出对象的详细信息消息字符串。
        String message = e.getMessage();
        // 提供对 printStackTrace（） 打印的堆栈跟踪信息的编程访问。
        StackTraceElement[] stackTrace = e.getStackTrace();
        String stackTraceString = "";
        for (StackTraceElement stackTraceElement : stackTrace) {
            stackTraceString += stackTraceElement.toString() + "\n";
        }
        // 返回此可抛件的简短说明。
        String toString = e.toString();
        model.addAttribute("message", message);
        model.addAttribute("stackTrace", stackTraceString);
        model.addAttribute("toString", toString);
        return "error";
    }

}
