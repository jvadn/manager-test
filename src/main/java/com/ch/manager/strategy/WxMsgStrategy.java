package com.ch.manager.strategy;

public interface WxMsgStrategy {

    public String getCode();

    public String run(String msg);

}
