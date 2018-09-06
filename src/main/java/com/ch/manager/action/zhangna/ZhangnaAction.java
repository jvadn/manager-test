package com.ch.manager.action.zhangna;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("zhangna")
public class ZhangnaAction {

    @RequestMapping("/nanting")
    public String nanTing() {
        return "/zhangna/nanting";
    }

    @RequestMapping("/dasi")
    public String dasi() {
        return "/zhangna/dasi";
    }

}
