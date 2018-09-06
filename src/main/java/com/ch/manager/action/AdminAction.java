package com.ch.manager.action;

import com.ch.manager.api.UserInfoApi;
import com.ch.manager.entity.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminAction {

    @Autowired
    UserInfoApi userInfoApi;

    @RequestMapping("/info")
    public String info(HttpServletRequest req){
        req.setAttribute("chenhao",userInfoApi.queryByUserId(UserContext.getChenhao().getId()));
        return "/admin/info";
    }

}
