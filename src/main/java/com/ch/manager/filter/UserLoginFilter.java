package com.ch.manager.filter;

import com.alibaba.fastjson.JSONObject;
import com.ch.manager.entity.UserContext;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String servlettPath = req.getServletPath();
        if (servlettPath.indexOf(".css") != -1 || servlettPath.indexOf(".js") != -1) {
            filterChain.doFilter(req, servletResponse);
        } else {
            setAttrbite(req);
            String openId = getOpenIdByCookie(req);
            if (1 == 1 || openId != null && UserContext.checkOpenId(openId)) {
                filterChain.doFilter(req, servletResponse);
            } else {
                RequestDispatcher dispatch = servletRequest.getRequestDispatcher("error");
                dispatch.forward(req, servletResponse);
            }
        }
    }

    public static String getOpenIdByCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                if ("openId".equals(cookies[i].getName())) {
                    System.out.println("openId=" + cookies[i].getValue());
                    return cookies[i].getValue();
                }
            }
        }
        return null;
    }

    public static void setAttrbite(HttpServletRequest req) {
        req.setAttribute("chenhao", UserContext.getChenhao());
        req.setAttribute("chenhaoJson", JSONObject.toJSONString(UserContext.getChenhao()));
        req.setAttribute("zhangna", UserContext.getZhangna());
        req.setAttribute("zhangnaJson", JSONObject.toJSONString(UserContext.getZhangna()));
    }

    @Override
    public void destroy() {

    }
}
