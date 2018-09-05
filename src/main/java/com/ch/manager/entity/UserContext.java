package com.ch.manager.entity;

public class UserContext {

    private static MtUser chenhao = null;
    private static MtUser zhangna = null;

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
}
