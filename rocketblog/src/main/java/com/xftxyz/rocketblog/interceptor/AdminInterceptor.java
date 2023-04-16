package com.xftxyz.rocketblog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.xftxyz.rocketblog.pojo.User;
import com.xftxyz.rocketblog.util.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 管理员拦截器
 */
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 获取session中的user判断是否登录
        Object obj = Utils.currentUser(request.getSession());
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
