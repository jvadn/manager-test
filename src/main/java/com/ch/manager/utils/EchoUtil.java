package com.ch.manager.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhao
 * @date ${date}
 * 反射工具类
 */
public class EchoUtil {

    public final static Map<String, Class> PACKING = new HashMap<>();

    static {
        PACKING.put("Integer", int.class);
        PACKING.put("Long", long.class);
        PACKING.put("Double", double.class);
        PACKING.put("Float", float.class);
        PACKING.put("Byte", byte.class);
        PACKING.put("Boolean", boolean.class);
    }

    /**
     * 测评过反射用枚举方法
     *
     * @param _enum
     * @param methodName
     * @return
     */
    public static Object invoke(Enum _enum, String methodName) {
        Class<? extends Enum> clazz = _enum.getClass();
        try {
            Method method = getMethod(clazz, methodName);
            return method.invoke(_enum);
        } catch (Exception e) {
            throw new RuntimeException(String.format("反射调用枚举[%s]方法失败！", methodName));
        }
    }

    /**
     * 通过反射调用有参方法
     *
     * @param clazz      类
     * @param methodName 方法名
     * @param args       参数
     * @return
     */
    public static Object invoke(Class clazz, String methodName, Object... args) {
        try {
            Method method = getMethod(clazz, methodName, args);
            return method.invoke(clazz.newInstance(), args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("反射调用[%s]方法失败！", methodName));
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("反射调用[%s]方法失败！", methodName));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("反射调用[%s]方法失败！", methodName));
        }
    }

    /**
     * 通过反射调用无参方法
     *
     * @param clazz      类
     * @param methodName 方法名
     * @return
     */
    public static Object invoke(Class clazz, String methodName) {
        try {
            Method method = getMethod(clazz, methodName);
            Object obj = clazz.newInstance();
            return method.invoke(clazz.newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("反射调用[%s]方法失败！", methodName));
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("反射调用[%s]方法失败！", methodName));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("反射调用[%s]方法失败！", methodName));
        }
    }

    /**
     * 通过反射获取无参方法
     *
     * @param clazz      类
     * @param methodName 方法名
     * @return
     */
    public static Method getMethod(Class clazz, String methodName) {
        if (methodName == null || "".equals(methodName)) {
            throw new RuntimeException("方法名为NULL！");
        }
        try {
            return clazz.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("反射获取方法失败！无此方法[%s]", methodName));
        }
    }

    /**
     * 通过反射获取有参方法
     *
     * @param clazz      类
     * @param methodName 方法名
     * @param args       参数
     * @return
     */
    public static Method getMethod(Class clazz, String methodName, Object... args) {
        if (args == null) {
            throw new RuntimeException("反射调用方法参数为NULL!");
        }
        if (methodName == null || "".equals(methodName)) {
            throw new RuntimeException("方法名为NULL！");
        }
        Class[] classs = new Class[args.length];
        for (int i = 0; i <= args.length; i++) {
            for (int j = 0; j < args.length; j++) {
                if (i > j && PACKING.get(args[j].getClass().getSimpleName()) != null) {
                    classs[j] = PACKING.get(args[j].getClass().getSimpleName());
                } else {
                    classs[j] = args[j].getClass();
                }
            }
            try {
                return getMethod(clazz, methodName, classs);
            } catch (NoSuchMethodException e) {
            }
        }
        throw new RuntimeException(String.format("反射获取方法失败！无此方法[%s]", methodName));
    }

    private static Method getMethod(Class clazz, String methodName, Class... classs) throws NoSuchMethodException {
        return clazz.getMethod(methodName, classs);
    }

    /**
     * 通过反射获取枚举CODE
     *
     * @param _enum
     * @return
     */
    public static String getEnumCode(Enum _enum) {
        Object getCode = invoke(_enum, "getCode");
        return getCode == null ? "" : getCode.toString();
    }

    /**
     * 通过反射获取枚举VALUE
     *
     * @param _enum
     * @return
     */
    public static String getEnumValue(Enum _enum) {
        Object getValue = invoke(_enum, "getValue");
        return getValue == null ? "" : getValue.toString();
    }

    /**
     * 通过反射获取枚举VALUE, 并替换字符串
     *
     * @param _enum
     * @param args
     * @return
     */
    public static String getEnumValue(Enum _enum, Object... args) {
        Object getValue = invoke(_enum, "getValue");
        return getValue == null ? "" : String.format(getValue.toString(), args);
    }

}
