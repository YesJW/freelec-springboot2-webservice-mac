package com.jojodu.book.springboot;

import com.jojodu.book.springboot.config.auth.SecurityConfig;
import com.jojodu.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //SpringExtention이라는 스프링 실행자 사용, 스프링부트 테스트와 junit사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
}) //웹에 집중할 수 있는 어노테이션, @Controller를 사용가능
public class HelloControllerTest {
    @Autowired //스프링이 관리하는 Bean을 주입받음
    private MockMvc mvc; //웹 API를 테스트할 때 사용

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }
    @WithMockUser(roles="USER")
    @Test
    public void hello_dto가_리턴된다() throws Exception{
        String name = "spring";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(e->{
                    jsonPath("$.name",is(name));})
                .andExpect(e->{jsonPath("$.amount",is(amount));});

    }
}
