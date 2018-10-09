package com.ch.manager.action.dengqing;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dengqing")
public class DengqingAction {

    @Autowired
    GandongApi gandongApi;

    @Autowired
    UserInfoApi userInfoApi;

    @RequestMapping("/nanting")
    public String nanTing() {
        return "dengqing/nanting";
    }

    @RequestMapping("/dasi")
    public String dasi() {
        return "dengqing/dasi";
    }

    @RequestMapping("/gandong")
    public String ganDong() {
        return "dengqing/gandong";
    }

    @RequestMapping("/gandong-li/{type}")
    public String ganDong(@PathVariable("type") String type, HttpServletRequest req) {
        req.setAttribute("type", type);
        req.setAttribute("gandong", gandongApi.queryByUserIdType(UserContext.getDengqing().getId(), type));
        return "dengqing/gandong-li";
    }

    @RequestMapping("/gandong-add")
    @ResponseBody
    public Message gandongAdd(@RequestParam("type") String type, @RequestParam("value") String value) {
        try {
            gandongApi.removeByUserIdType(UserContext.getDengqing().getId(), type);
            gandongApi.add(new MtGandong(UserContext.getDengqing().getId(), type, value));
        } catch (Exception e) {
            e.printStackTrace();
            return Message.warn(ExceptionEnum.SYSTEM_ERROR);
        }
        return Message.SUCCESS;
    }

    @RequestMapping("/gaobai")
    public String gaobai() {
        return "dengqing/gaobai";
    }

    @RequestMapping("/nvwang")
    public String info(HttpServletRequest req) {
        MtUserInfo userInfo = userInfoApi.queryByUserId(UserContext.getDengqing().getId());
        req.setAttribute("user", userInfo);
        return "dengqing/nvwang";
    }

    @RequestMapping("/add-user-info")
    @ResponseBody
    public Message addUserInfo(@RequestBody MtUserInfo userInfo) {
        try {
            userInfoApi.removeByUserId(userInfo.getUserId());
            userInfo.init();
            userInfoApi.addUserInfo(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Message.warn(ExceptionEnum.SYSTEM_ERROR);
        }
        return Message.SUCCESS;
    }

}
