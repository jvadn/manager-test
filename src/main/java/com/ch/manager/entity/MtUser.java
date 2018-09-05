package com.ch.manager.entity;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.util.Date;

@Table(name = "mt_user")
public class MtUser implements Serializable {

    private String id;
    private String remark;
    private String type;
    private String name;
    private String wxName;
    private String nickName;
    private String guanxi;
    private Date date;
    private String openId;
    private String headImage;

    public MtUser(String wxName, String openId){
        this.setWxName(wxName);
        this.setOpenId(openId);
    }

    public MtUser(){

    }

    public MtUser(String openId){
        this.setOpenId(openId);
    }

    @AssignID("uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGuanxi() {
        return guanxi;
    }

    public void setGuanxi(String guanxi) {
        this.guanxi = guanxi;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
}
