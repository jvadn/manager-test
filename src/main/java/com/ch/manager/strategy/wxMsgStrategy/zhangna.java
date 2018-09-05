package com.ch.manager.strategy.wxMsgStrategy;

import com.ch.manager.enums.wxMsg.zhangnaMsg;
import com.ch.manager.strategy.WxMsgStrategy;
import org.springframework.stereotype.Component;

@Component
public class zhangna implements WxMsgStrategy {

    @Override
    public String getCode() {
        return zhangnaMsg.getKey();
    }

    @Override
    public String run(String msg) {
        return zhangnaMsg.getMsg(msg);
    }
}
