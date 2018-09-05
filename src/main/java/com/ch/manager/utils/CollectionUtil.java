package com.ch.manager.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhao
 * @date ${date}
 */
public class CollectionUtil {

    public static Map<String, Object> newHashMap(Object... objs) {
        Map<String, Object> map = new HashMap<>();
        if (objs == null || objs.length == 0) {
            throw new RuntimeException("创建MAP的值为空");
        }
        if (objs.length % 2 != 0) {
            throw new RuntimeException("创建MAP的值数量错误");
        }
        for (int i = 0; i < objs.length; i += 2) {
            if (objs[i] == null || objs[i].toString().trim().equals("")) {
                throw new RuntimeException("创建MAP的key值错误");
            }
//            if (!clazz.isAssignableFrom(objs[i + 1].getClass())) {
//                throw new RuntimeException("创建MAP的值类型错误");
//            }
            map.put(objs[i].toString(), objs[i + 1]);
        }
        return map;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection coll) {
        return !isEmpty(coll);
    }

    public static boolean isEmpty(Object[] objs) {
        return objs == null || objs.length < 1;
    }


}
