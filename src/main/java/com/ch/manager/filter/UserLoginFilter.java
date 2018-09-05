package com.ch.manager.filter;

import com.alibaba.fastjson.JSONObject;
import com.ch.manager.entity.UserContext;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UserLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        setAttrbite(req);
        String openId = getOpenIdByCookie(req);
        if(1 == 1 || openId != null && UserContext.checkOpenId(openId)){
            filterChain.doFilter(req, servletResponse);
        }else{
            RequestDispatcher dispatch = servletRequest.getRequestDispatcher("error");
            dispatch.forward(req, servletResponse);
        }
    }

    public static String getOpenIdByCookie(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                System.out.println("name=" + cookies[i].getName() + ", value=" + cookies[i].getValue());
                if ("openId".equals(cookies[i].getName())) {
                    return cookies[i].getValue();
                }
            }
        }
        return null;
    }

    public static void setAttrbite(HttpServletRequest req){
        req.setAttribute("chenhao",UserContext.getChenhao());
        req.setAttribute("zhangna",UserContext.getZhangna());
    }

    @Override
    public void destroy() {

    }
}
