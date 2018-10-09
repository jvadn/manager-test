package com.ch.manager.entity;

import java.util.ArrayList;
import java.util.List;

public class UserContext {

    private static MtUser chenhao = null;
    private static MtUser zhangna = null;
    private static MtUser dengqing = null;

    public static boolean checkOpenId(String openId) {
        List<String> list = new ArrayList<>();
        if (chenhao != null) {
            list.add(chenhao.getOpenId());
        }
        if (zhangna != null) {
            list.add(zhangna.getOpenId());
        }
        if (dengqing != null) {
            list.add(dengqing.getOpenId());
        }
        for (String li : list) {
            if (li.equals(openId)) {
                return true;
            }
        }
        return false;
    }

    public static MtUser getChenhao() {
        return chenhao;
    }

    public static void setChenhao(MtUser chenhao) {
        UserContext.chenhao = chenhao;
    }

    public static MtUser getZhangna() {
        return zhangna;
    }

    public static void setZhangna(MtUser zhangna) {
        UserContext.zhangna = zhangna;
    }

    public static MtUser getDengqing() {
        return dengqing;
    }

    public static void setDengqing(MtUser dengqing) {
        UserContext.dengqing = dengqing;
    }
}
