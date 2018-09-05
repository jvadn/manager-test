package com.ch.manager.restapi;


import com.alibaba.fastjson.JSONObject;
import com.ch.manager.api.MenuApi;
import com.ch.manager.constants.ConstantWXMsg;
import com.ch.manager.constants.Constants;
import com.ch.manager.entity.MtMenu;
import com.ch.manager.entity.MtUser;
import com.ch.manager.entity.UserContext;
import com.ch.manager.entity.WXMessage;
import com.ch.manager.enums.EnumWxMsgType;
import com.ch.manager.restapi.Base.AbstractFileAction;
import com.ch.manager.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class WXApi extends AbstractFileAction {

    private static String checkFilePath = null;

    @Autowired
    MenuApi menuApi;

    @RequestMapping(value = "/wx", method = RequestMethod.GET)
    public void wxToken(HttpServletRequest req, HttpServletResponse rep) throws IOException {
        logger.info("校验微信token");
        boolean wx = isWx(req);
        logger.info("校验结果：{}", wx);
        if (wx) {
            rep.getWriter().print(req.getParameter(Constants.ECHOSTR));
        }
    }

    @RequestMapping(value = "/wx", method = RequestMethod.POST)
    @ResponseBody
    public String wxMsg(HttpServletRequest req, HttpServletResponse rep) throws IOException {
        Map<String, String> map = XMLUtil.toMap(req);
        WXMessage wxMessage = JSONObject.parseObject(JSONObject.toJSONString(map), WXMessage.class);
        logger.info("接受消息：{}", JSONObject.toJSONString(wxMessage));
        WXMessage wx = null;
        MtUser user = null;
        try {
            user = getUser(req);
            if (wxMessage != null && wxMessage.getMsgType().equals(EnumWxMsgType.EVENT.getValue())) {
                logger.info("关注公众号，openId:[{}]", wxMessage.getFromUserName());
                logger.info("获取用户信息：[{}]", JSONObject.toJSONString(user));
                if (user != null) {
                    wx = new WXMessage(wxMessage, run(user, EnumWxMsgType.EVENT.getValue()));
                }
            } else if (wxMessage.getMsgType().equals("text")) {
                wx = new WXMessage(wxMessage);
            } else {
                wx = new WXMessage(wxMessage, ConstantWXMsg.ONLY_TEXT);
            }
        } catch (Exception e) {
            logger.error("被动消息失败！");
            e.printStackTrace();
        }
        //暂时只开放给张娜
        if (user == null || (!UserContext.getZhangna().getRemark().equals(user.getRemark()) && !UserContext.getChenhao().getRemark().equals(user.getRemark()))) {
            wx = new WXMessage(wxMessage, String.format(ConstantWXMsg.ONLY_LOVE, UserContext.getChenhao().getNickName(), UserContext.getZhangna().getNickName()));
        }
        logger.info("返回消息：{}", JSONObject.toJSONString(wx));
        return wx == null || StringUtil.isBlank(wx.getContent()) ? "" : XMLUtil.toXML(wx);
    }

    @RequestMapping("/wx-menu")
    public String wxMenu(HttpServletRequest req) {
        List<MtMenu> menus = menuApi.queryByUserId(req.getParameter("id"));
        String jsonString = "{\"button\":[{\"name\":\"小主\",\"type\":\"view\",\"url\":\"http://www.baidu.com\"},{\"name\":\"告白情书\",\"sub_button\":[{\"name\":\"想要的感动\",\"type\":\"view\",\"url\":\"http://www.360.cn\"},{\"name\":\"告白情书\",\"type\":\"view\",\"url\":\"http://www.360.cn\"}]},{\"name\":\"小娜\",\"sub_button\":[{\"name\":\"小娜的信息\",\"type\":\"view\",\"url\":\"http://www.baidu.com\"},{\"name\":\"小娜的男票\",\"type\":\"view\",\"url\":\"http://www.baidu.com\"}]}]}";
        if (CollectionUtil.isNotEmpty(menus)) {
            JSONObject json = JSONObject.parseObject(jsonString);
            for (MtMenu menu : menus) {
            }
        }
        String s = HttpUtils.postJSON(getWxMenuUrl(), jsonString);
        logger.info(s);
        return "zhangna/menu";
    }

    @RequestMapping("/check-js-file/{fileName}")
    public void checkJsFile(@PathVariable("fileName") String fileName, HttpServletRequest req, HttpServletResponse rep) {
        checkFilePath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        downloadFile(fileName, rep);
    }

    @Override
    public String getFilePath() {
        logger.info("文件路径：{}",checkFilePath);
        return checkFilePath;
    }
}
