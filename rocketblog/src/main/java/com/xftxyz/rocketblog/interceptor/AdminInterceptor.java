package com.xftxyz.rocketblog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.xftxyz.rocketblog.pojo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 获取session中的user判断是否登录
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return false;
        }
        if (obj instanceof User u) {
            if (u.getIsSuperuser() != 1) {
                return false;
            }
            return true;
        }
        return false;
    }
}