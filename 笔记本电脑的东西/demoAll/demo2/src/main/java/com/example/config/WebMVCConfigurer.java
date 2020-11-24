package com.example.config;

import com.example.component.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hhr
 * @date 2020-06-23 11:45
 * @description:
 */
//@Configuration
public class WebMVCConfigurer implements WebMvcConfigurer {

    @Autowired
    UserInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login",
                        "/**/*.html",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.woff",
                        "/**/*.ttf"
                );
    }
}
