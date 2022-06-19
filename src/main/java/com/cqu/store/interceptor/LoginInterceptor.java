package com.cqu.store.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//定义一个拦截器
public class LoginInterceptor implements HandlerInterceptor {
    //调用所有处理请求的方法之前被自动调用执行的方法
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
         //HttpServletRequest request获取session对象
        Object obj= request.getSession().getAttribute("uid");

        if(obj==null)
        {
            //如果没有session对象就拦截，重定向到login.html登录页面
            response.sendRedirect("/web/login.html");
            //结束后续的调用
            return false;
        }

        //请求放行
        return true;
    }
}
