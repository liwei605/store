package com.cqu.store.config;


import com.cqu.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


@Configuration  //加载当前的拦截器并进行注册
//处理器拦截器的注册
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    //这个方法是用来配置拦截器的
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //创建一个拦截器的对象，刻意进行重定向
        HandlerInterceptor interceptor = new LoginInterceptor();
        //配置白名单：存放在一个List集合中
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/js/**");
        patterns.add("/images/**");
        patterns.add("/index.html");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");patterns.add("products/**");
        //完成拦截器的注册
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);//要拦截的URL是什么
    }
}
