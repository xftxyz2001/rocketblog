package com.xftxyz.rocketblog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.exception.user.NotLoginException;
import com.xftxyz.rocketblog.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 获取session中的user判断是否登录
        Object sUser = request.getSession().getAttribute(EnvironmentVariables.SESSION_USER);
        if (sUser != null) {
            return true;
        }
        // 未在session中找到登录信息，尝试从cookie中获取
        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(EnvironmentVariables.COOKIE_TOKEN)) {
                token = cookie.getValue();
                break;
            }
        }
        if (token == null || userService.fromToken(token) == null) {
            throw new NotLoginException();
        }
        return true;

    }
}
