package com.ch.manager.entity;

import java.util.ArrayList;
import java.util.List;

public class UserContext {

    private static MtUser chenhao = null;
    private static MtUser zhangna = null;

    public static boolean checkOpenId(String openId){
        List<String> list = new ArrayList<>();
        list.add(chenhao.getOpenId());
        list.add(zhangna.getOpenId());
        for(String li : list){
            if(li.equals(openId)){
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
}
