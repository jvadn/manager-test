package com.ch.manager.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

/**
 * @author chenhao
 * @date ${date}
 */
@Table(name = "mt_gandong")
public class MtGandong extends BaseEntity {

    private String userId;
    private String type;
    private String desc;

    public MtGandong() {
    }

    public MtGandong(String userId, String type) {
        this.setUserId(userId);
        this.setType(type);
        this.setIsDelete("0");
    }

    public MtGandong(String userId, String type, String desc) {
        this.setUserId(userId);
        this.setType(type);
        this.setDesc(desc);
        this.setCreateDate(new Date());
        this.setIsDelete("0");
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
