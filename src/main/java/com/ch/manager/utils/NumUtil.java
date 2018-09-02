package com.ch.manager.utils;

/**
 * @author chenhao
 * @date ${date}
 */
public class NumUtil {

    public static boolean andEquals(Integer num, Integer... eq) {
        if (num == null || eq == null) {
            return false;
        }
        for (Integer e : eq) {
            if (e == null || e != num) {
                return false;
            }
        }
        return true;
    }

    public static boolean orEquals(Integer num, Integer... eq) {
        if (num == null || eq == null) {
            return false;
        }
        for (Integer e : eq) {
            if (e != null && e == num) {
                return true;
            }
        }
        return false;
    }

}
