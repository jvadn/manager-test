package com.ch.manager.action.zhangna;

import com.ch.manager.api.GandongApi;
import com.ch.manager.api.UserInfoApi;
import com.ch.manager.entity.MtGandong;
import com.ch.manager.entity.MtUserInfo;
import com.ch.manager.entity.UserContext;
import com.ch.manager.exception.ExceptionEnum;
import com.ch.manager.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/zhangna")
public class ZhangnaAction {

    @Autowired
    GandongApi gandongApi;

    @Autowired
    UserInfoApi userInfoApi;

    @RequestMapping("/nanting")
    public String nanTing() {
        return "zhangna/nanting";
    }

    @RequestMapping("/dasi")
    public String dasi() {
        return "zhangna/dasi";
    }

    @RequestMapping("/gandong")
    public String ganDong() {
        return "zhangna/gandong";
    }

    @RequestMapping("/gandong-li/{type}")
    public String ganDong(@PathVariable("type") String type, HttpServletRequest req) {
        req.setAttribute("type", type);
        req.setAttribute("gandong", gandongApi.queryByUserIdType(UserContext.getZhangna().getId(), type));
        return "zhangna/gandong-li";
    }

    @RequestMapping("/gandong-add")
    @ResponseBody
    public Message gandongAdd(@RequestParam("type") String type, @RequestParam("value") String value) {
        try {
            gandongApi.removeByUserIdType(UserContext.getZhangna().getId(), type);
            gandongApi.add(new MtGandong(UserContext.getZhangna().getId(), type, value));
        } catch (Exception e) {
            e.printStackTrace();
            return Message.warn(ExceptionEnum.SYSTEM_ERROR);
        }
        return Message.SUCCESS;
    }

    @RequestMapping("/gaobai")
    public String gaobai() {
        return "zhangna/gaobai";
    }

    @RequestMapping("/nvwang")
    public String info(HttpServletRequest req) {
        MtUserInfo userInfo = new MtUserInfo();
        userInfo.setName("45678");
        userInfo.setBirth(new Date());
//        req.setAttribute("user", userInfoApi.queryByUserId(UserContext.getZhangna().getId()));
        req.setAttribute("user", userInfo);
        return "zhangna/nvwang";
    }

}
