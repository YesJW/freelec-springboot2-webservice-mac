package com.jojodu.book.springboot.config;

import com.jojodu.book.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//스프링에 인식될 수 있도록(LoginUserArgumentResolver) WebConfig 생성
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //argumentResolvers()를 통해 추가해야함(HandlerMethodArgumentResolver)
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
