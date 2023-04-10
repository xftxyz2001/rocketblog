package com.xftxyz.rocketblog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultMessageEnum;
import com.xftxyz.rocketblog.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 获取session中的user判断是否登录
        if (request.getSession().getAttribute(EnvironmentVariables.COOKIE_TOKEN) != null) {
            return true;
        }
        // 未在session中找到登录信息，尝试从cookie中获取
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(EnvironmentVariables.COOKIE_TOKEN)) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        User user = userService.fromToken(token);
        if (user == null) {
            // 未登录
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(Result
                    .error(ResultMessageEnum.USER_NOT_LOGIN.getCode(), ResultMessageEnum.USER_NOT_LOGIN.getMessage())));

            return false;
        }
        request.getSession().setAttribute(EnvironmentVariables.SESSION_USER, user);
        return true;

    }
}
