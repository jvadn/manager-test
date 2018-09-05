package com.ch.manager.entity;

import com.ch.manager.enums.EnumContent;
import com.ch.manager.enums.EnumWxMsgType;

import java.util.Date;

public class WXMessage {

    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private String Content;

    public WXMessage() {
    }

    public WXMessage(WXMessage wx, String content) {
        this.setFromUserName(wx.getToUserName());
        this.setToUserName(wx.getFromUserName());
        this.setMsgType(EnumWxMsgType.TEXT.getValue());
        this.setCreateTime(new Date().getTime());
        this.setContent(content);
    }

    public WXMessage(WXMessage wx) {
        this.setFromUserName(wx.getToUserName());
        this.setToUserName(wx.getFromUserName());
        this.setMsgType(wx.getMsgType());
        this.setCreateTime(new Date().getTime());
        this.setContent(EnumContent.getMsg(wx.getContent()));
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
