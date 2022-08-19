package com.jojodu.book.springboot.config.auth;

import com.jojodu.book.springboot.config.auth.dto.SessionUser;
import com.jojodu.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    //조건에 맞으면 HandlerMethodArgumentResolver의 구현체가 지정한 값으로 해당 메소드의 파라미터 넘김.

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) { //컨트롤러 메서드의 특정 파라미터를 지원하는지 판단.
        //파라미터에 @LoginUser 어노테이션이 붙어있는지
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        //파라미터 타입이 SessionUser 클래스인지
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    @Override //파라미터에 전달할 객체 생성.
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user"); //세션에서 객체를 가저옴.
    }
}
