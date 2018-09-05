package com.ch.manager.enums;

public enum EnumContent {
    TEST("测试","测试")
    ;

    private String fromMsg;
    private String toMsg;

    public static String getMsg(String fromMsg){
        for(EnumContent e : EnumContent.values()){
            if(e.getFromMsg().equals(fromMsg)){
                return e.getToMsg();
            }
        }
        return "";
    }

    EnumContent(String formMsg,String toMsg){
        this.fromMsg = formMsg;
        this.toMsg = toMsg;
    }

    public String getFromMsg() {
        return fromMsg;
    }

    public String getToMsg() {
        return toMsg;
    }

}
