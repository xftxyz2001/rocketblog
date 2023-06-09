package com.xftxyz.rocketblog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultCode;
import com.xftxyz.rocketblog.service.UserService;
import com.xftxyz.rocketblog.util.Utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 获取session中的user判断是否登录
        if (request.getSession().getAttribute(EnvironmentVariables.SESSION_USER) != null) {
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
        // 未登录
        if (user == null) {
            // 清除cookie
            Utils.clearLoginCookie(response);
            // 返回未登录信息
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(Result
                    .error(ResultCode.USER_NOT_LOGIN.getCode(), ResultCode.USER_NOT_LOGIN.getMessage())));

            return false;
        }

        request.getSession().setAttribute(EnvironmentVariables.SESSION_USER, user);
        return true;

    }
}
