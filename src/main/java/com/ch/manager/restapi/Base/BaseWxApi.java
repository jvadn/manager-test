package com.ch.manager.restapi.Base;

import com.alibaba.fastjson.JSONObject;
import com.ch.manager.api.UserApi;
import com.ch.manager.api.WxMsgLogApi;
import com.ch.manager.constants.Constants;
import com.ch.manager.entity.MtUser;
import com.ch.manager.entity.MtWxMsgLog;
import com.ch.manager.strategy.WxMsgStrategy;
import com.ch.manager.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class BaseWxApi extends BaseRestApi {

    private static Map<String, MtUser> map = new HashMap<>();
    private static String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    protected static String wxMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    private static Long adExpires = -200L;
    private static String accessToken = null;
    private static Long endAccessToken = null;

    @Autowired
    protected Environment env;

    @Autowired
    UserApi userApi;

    @Autowired
    WxMsgLogApi wxMsgLogApi;

    @Autowired
    List<WxMsgStrategy> wxMsgStrategys;

    /**
     * 根据不同用户回复消息
     *
     * @param user
     * @param msg
     * @return
     */
    public String run(MtUser user, String msg) {
        if (CollectionUtil.isEmpty(wxMsgStrategys)) {
            return null;
        }
        for (WxMsgStrategy wxMsgStrategy : wxMsgStrategys) {
            if (wxMsgStrategy.equals(user.getRemark())) {
                return wxMsgStrategy.run(msg);
            }
        }
        return null;
    }

    /**
     * 通过Openid获取微信用户
     *
     * @param req
     * @return
     */
    protected MtUser getUser(HttpServletRequest req) {
        String openId = req.getParameter("openid");
        if (StringUtil.isNotBlank(openId)) {
            if (map.get(openId) == null) {
                MtUser mtUser = userApi.queryByOpenId(openId);
                if (mtUser == null) {
                    WXUserInfo userInfo = getUserInfo(openId);
                    MtUser user = userApi.queryByWxName(userInfo.getNickname());
                    if (user == null) {
                        return userApi.insert(new MtUser(userInfo.getNickname(), openId));
                    }
                    user.setOpenId(openId);
                    return userApi.update(user);
                }
                return mtUser;
            }
            return map.get(openId);
        }
        return null;
    }

    /**
     * 校验请求来源是否微信
     *
     * @param req
     * @return
     */
    protected boolean isWx(HttpServletRequest req) {
        String nonce = req.getParameter(Constants.NONCE);
        String timestamp = req.getParameter(Constants.TIMESTAMP);
        String signature = req.getParameter(Constants.SIGNATURE);
        if (StringUtil.isBlank(signature, nonce, signature)) {
            return false;
        }
        String[] str = {env.getProperty("app.token"), timestamp, nonce};
        Arrays.sort(str); // 字典序排序
        String digest = SHA1Util.encode(str[0] + str[1] + str[2]);// SHA1加密
        return digest.equals(signature);// 确认请求来至微信
    }

    protected String getAccessToken() {
        if (accessToken != null && endAccessToken > new Date().getTime()) {
            return accessToken;
        }
        String appId = env.getProperty("app.id");
        String appSecret = env.getProperty("app.secret");
        String url = getAccessTokenUrl.replaceAll("APPID", appId).replaceAll("APPSECRET", appSecret);
        String result = HttpUtils.URLGet(url);
        JSONObject json = JSONObject.parseObject(result);
        if (json != null && StringUtil.isNotBlank(json.getString("access_token"))) {
            accessToken = json.getString("access_token");
            Long expiresIn = json.getLong("expires_in") * 1000 + adExpires;
            endAccessToken = new Date().getTime() + expiresIn;
        }
        return accessToken;
    }

    protected WXUserInfo getUserInfo(String openId) {
        String url = getUserInfoUrl.replaceAll("ACCESS_TOKEN", getAccessToken()).replaceAll("OPENID", openId);
        WXUserInfo wxUserInfo = null;
        try {
            String result = HttpUtils.URLGet(url);
            System.out.println(result);
            wxUserInfo = JSONObject.parseObject(result, WXUserInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wxUserInfo;
    }

    protected String getWxMenuUrl() {
        return wxMenuUrl.replaceAll("ACCESS_TOKEN", getAccessToken());
    }

    protected void addWxMsgLog(String createCode, String msgType, String createType, String context) {
        wxMsgLogApi.addLog(new MtWxMsgLog(createCode, msgType, createType, context));
    }

}
