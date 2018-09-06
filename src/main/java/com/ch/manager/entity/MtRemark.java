package com.ch.manager.entity;

import org.beetl.sql.core.annotatoin.Table;

@Table(name = "mt_remark")
public class MtRemark extends BaseEntity{

    private String userId;
    private String type;
    private String simple;
    private String desc;

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

    public String getSimple() {
        return simple;
    }

    public void setSimple(String simple) {
        this.simple = simple;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
