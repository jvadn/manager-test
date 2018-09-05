package com.ch.manager.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminAction {

    @RequestMapping("/info")
    public String info(){
        return "/admin/info";
    }

}
