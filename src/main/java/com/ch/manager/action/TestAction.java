package com.ch.manager.action;

import com.alibaba.fastjson.JSONObject;
import com.ch.manager.action.Base.BaseAction;
import com.ch.manager.api.TestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestAction extends BaseAction {

    @Autowired
    TestApi api;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        logger.info("測試[{}]","test");
        return JSONObject.toJSONString(api.getNameAndAge("0"));
    }

}
