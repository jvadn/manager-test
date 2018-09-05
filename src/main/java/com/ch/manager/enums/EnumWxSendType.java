package com.ch.manager.enums;

public enum EnumWxSendType {

    SYSTEM("00", "系統"),
    LOVE_USER("10", "特别关心"),
    SYSTEM_LOVE("20","系统特别关心"),
    USER("30", "用戶"),
    TOURIST("40", "游客")
    ;

    private String code;
    private String value;

    EnumWxSendType(String code, String value) {
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
