package com.xftxyz.rocketblog.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        // String
        // getLocalizedMessage()
        // 创建此可抛件的本地化说明。
        String localizedMessage = e.getLocalizedMessage();
        // String
        // getMessage()
        // 返回此可抛出对象的详细信息消息字符串。
        String message = e.getMessage();
        // StackTraceElement[]
        // getStackTrace()
        // 提供对 printStackTrace（） 打印的堆栈跟踪信息的编程访问。
        StackTraceElement[] stackTrace = e.getStackTrace();
        String stackTraceString = "";
        for (StackTraceElement stackTraceElement : stackTrace) {
            stackTraceString += stackTraceElement.toString();
        }
        // String
        // toString()
        // 返回此可抛件的简短说明。
        String toString = e.toString();
        model.addAttribute("localizedMessage", localizedMessage);
        model.addAttribute("message", message);
        model.addAttribute("stackTrace", stackTraceString);
        model.addAttribute("toString", toString);
        return "error";
    }

}
