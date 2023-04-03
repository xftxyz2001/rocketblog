package com.xftxyz.rocketblog.interceptor;

import java.util.Map;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuffer parms = new StringBuffer();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            for (String value : entry.getValue()) {
                parms.append(entry.getKey()).append("=").append(value).append("&");
            }
        }

        log.info("{}:{} <- {}", request.getMethod(), request.getRequestURL(), parms);
        return true;
    }
}
