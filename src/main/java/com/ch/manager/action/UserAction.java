package com.ch.manager.action;

import com.ch.manager.api.UserApi;
import com.ch.manager.entity.MtUser;
import com.ch.manager.exception.ExceptionEnum;
import com.ch.manager.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserAction {

    @Autowired
    UserApi api;

    @RequestMapping("/update")
    @ResponseBody
    public Message update(@RequestBody MtUser user){
        try {
            api.update(user);
            api.updateContext();
        }catch (Exception e){
            e.printStackTrace();
            return Message.warn(ExceptionEnum.SYSTEM_ERROR);
        }
        return Message.SUCCESS;
    }

}
