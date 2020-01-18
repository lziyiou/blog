package com.ziyiou.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ziyiou
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // session中没有user，则未登录
        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/admin/login");
            return false;
        }

        return true;
    }
}
