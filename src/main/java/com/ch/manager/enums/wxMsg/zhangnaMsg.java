package com.ch.manager.enums.wxMsg;

public enum zhangnaMsg {

    EVENT("event","策略Code");

    private String fromMsg;
    private String toMsg;

    public static String getMsg(String fromMsg){
        for(zhangnaMsg e : zhangnaMsg.values()){
            if(e.getFromMsg().equals(fromMsg)){
                return e.getToMsg();
            }
        }
        return "";
    }

    zhangnaMsg(String formMsg,String toMsg){
        this.fromMsg = formMsg;
        this.toMsg = toMsg;
    }

    public static String getKey(){
        return "zhangna";
    }

    public String getFromMsg() {
        return fromMsg;
    }

    public String getToMsg() {
        return toMsg;
    }
}
