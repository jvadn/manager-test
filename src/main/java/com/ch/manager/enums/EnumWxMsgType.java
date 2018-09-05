package com.ch.manager.enums;

public enum EnumWxMsgType {
    TEXT("00", "text"),
    IMAGE("10", "image"),
    VOICE("20", "voice"),//语音
    VIDEO("30", "video"),
    MUSIC("40", "music"),
    NEWS("50", "news"),//图文
    EVENT("60","event")
    ;

    private String code;
    private String value;

    public static String getType(String value){
        for(EnumWxMsgType e :EnumWxMsgType.values()){
            if(e.getValue().equals(value)){
                return e.getCode();
            }
        }
        return value;
    }

    EnumWxMsgType(String code, String value) {
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
