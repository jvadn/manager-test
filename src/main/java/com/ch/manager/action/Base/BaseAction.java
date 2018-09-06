package com.ch.manager.action.Base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class BaseAction{

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected String getCookie(HttpServletRequest req, String cookie){
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookie.equals(cookies[i].getName())) {
                    return cookies[i].getValue();
                }
            }
        }
        return null;
    }

}
