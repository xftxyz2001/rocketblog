package com.xftxyz.rocketblog.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.xftxyz.rocketblog.interceptor.AdminInterceptor;
import com.xftxyz.rocketblog.interceptor.LoggingInterceptor;
import com.xftxyz.rocketblog.interceptor.LoginInterceptor;

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
        // 日志拦截器
        registry.addInterceptor(new LoggingInterceptor());

        // 登录拦截器
        List<String> loginExcludePathPatterns = List.of(
                // 用户相关
                "/user/register", // 注册
                "/user/login", // 登录
                "/user/code", // 获取验证码
                "/user/forget", // 忘记密码
                "/user/info", // 获取用户信息（用户名、头像、关注数、粉丝数、文章数）
                // 博客相关
                "/blog/info", // 获取博客信息
                "/blog/detail", // 获取博客详情
                "/blog/search", // 搜索博客
                "/blog/new", // 获取最新博客
                "/blog/hot/*", // 获取热门博客
                "/blog/comment", // 获取评论
                // 静态资源
                "/static/**");
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(loginExcludePathPatterns);

        // 管理员拦截器 拦截路径 /admin
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/**");

    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(simpleFactory());
        return restTemplate;
    }
    @Bean
    public ClientHttpRequestFactory simpleFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 响应超时时间20s
        factory.setReadTimeout(20 * 1000);
        // 连接超时10s
        factory.setConnectTimeout(10 * 1000);
        return factory;
    }

}
