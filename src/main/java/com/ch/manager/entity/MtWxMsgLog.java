package com.ch.manager.entity;

import com.ch.manager.enums.EnumWxMsgType;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

@Table(name = "mt_wx_msg_log")
public class MtWxMsgLog {

    private String id;
    private String createCode;
    private Date createDate;
    private String msgType;
    private String createType;
    private String content;

    public MtWxMsgLog() {
    }

    public MtWxMsgLog(String createCode, String msgType, String createType, String context) {
        this.setCreateCode(createCode);
        this.setCreateDate(new Date());
        this.setMsgType(EnumWxMsgType.getType(msgType));
        this.setCreateType(createType);
        this.setContent(context);
    }

    @AssignID("uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateCode() {
        return createCode;
    }

    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
