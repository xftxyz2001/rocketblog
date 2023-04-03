package com.xftxyz.rocketblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.xftxyz.rocketblog.interceptor.AdminInterceptor;
import com.xftxyz.rocketblog.interceptor.LoggingInterceptor;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 管理员拦截器
        InterceptorRegistration adminInterceptor = registry.addInterceptor(new AdminInterceptor());
        // 拦截路径 /admin
        adminInterceptor.addPathPatterns("/admin/**");

        // 日志拦截器
        registry.addInterceptor(new LoggingInterceptor());

    }

}
