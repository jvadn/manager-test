package com.ch.manager.enums;

public enum EnumWxUserType {

    SYSTEM("00", "系統"),
    LOVE_USER("10", "特别关心"),
    USER("20", "用戶"),
    TOURIST("30", "游客")
    ;

    private String code;
    private String value;

    EnumWxUserType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
