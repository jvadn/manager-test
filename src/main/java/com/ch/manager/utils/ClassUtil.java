package com.ch.manager.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhao
 * @date ${date}
 */
public class ClassUtil {

    public final static Map<String, String> PACKING = new HashMap<>();

    static {
        PACKING.put("Integer", "int");
        PACKING.put("Long", "long");
        PACKING.put("Double", "double");
        PACKING.put("Float", "float");
        PACKING.put("Byte", "byte");
        PACKING.put("Short", "short");
        PACKING.put("Boolean", "boolean");
    }

    public static boolean equals(Class clazz, Class cla) {
        return getSimName(clazz).equals(getSimName(cla));
    }

    public static String getSimName(Class clazz) {
        String name = clazz.getSimpleName();
        return PACKING.get(name) == null ? name : PACKING.get(name);
    }

}
