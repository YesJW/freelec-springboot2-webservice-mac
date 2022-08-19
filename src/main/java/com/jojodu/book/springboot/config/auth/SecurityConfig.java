package com.jojodu.book.springboot.config.auth;

import com.jojodu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 disable함
                .and()
                .authorizeRequests()//URL별 권한 관리를 설정하는 옵션의 시작, antMatcher 옵션을 사용하기 위함.
                //angMachers -> 권한 관리 대상을 지정
                .antMatchers("/","/css/**","/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated().and().logout().logoutSuccessUrl("/")// 설정값 이외의 RUL
                //logout.logoutSuccessUrl -> 로그아웃 성공시 / 주소 이동
                .and().oauth2Login().userInfoEndpoint().userService(customOauth2UserService);
        //Oauth2 로그인 기능 설정의 진입점, EndPoint()-> 로그인 성공 이후 사용자 정보를 가져옴
        //userServie-> 소셜 로그인 성공 시 후속 조치를 진행, 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시

    }
}