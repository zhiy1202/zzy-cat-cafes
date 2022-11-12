package com.hlw.handler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zzy
 * @desc
 */
public class MyPathInterceptors implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null){
            response.sendRedirect("/");
            return false;
        }
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null){
            response.sendRedirect("/");
            return false;
        }else {
            return true;
        }
    }
}
