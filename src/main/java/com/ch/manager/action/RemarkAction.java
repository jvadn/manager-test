package com.ch.manager.action;

import com.ch.manager.action.Base.BaseAction;
import com.ch.manager.api.RemarkApi;
import com.ch.manager.api.UserApi;
import com.ch.manager.entity.MtRemark;
import com.ch.manager.entity.MtUser;
import com.ch.manager.exception.ExceptionEnum;
import com.ch.manager.utils.Message;
import com.ch.manager.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/remark")
public class RemarkAction extends BaseAction {

    @Autowired
    RemarkApi api;

    @Autowired
    UserApi userApi;

    @RequestMapping("/add")
    @ResponseBody
    public Message add(@RequestBody MtRemark remark, HttpServletRequest req){
        try {
            String openId = getCookie(req, "openId");
            if(StringUtil.isNotBlank(openId)){
                MtUser user = userApi.queryByOpenId(openId);
                if(user != null){
                    remark.setUserId(user.getId());
                }
            }
            api.add(remark);
        }catch (Exception e){
            e.printStackTrace();
            return Message.warn(ExceptionEnum.SYSTEM_ERROR);
        }
        return Message.SUCCESS;
    }

}
