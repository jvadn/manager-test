package com.ch.manager.utils;

/**
 * @author chenhao
 * @date ${date}
 */
public class StringUtil {

    /**
     * 格式化SQL字符串, 如 4\'\null\ 转换为\'4\'
     *
     * @param str
     * @return
     */
    public static String regSql(String str) {
        return str == null ? "NULL" : "\'" + str.replaceAll("\\\\", "").replaceAll("\'", "").replaceAll("null", "") + "\'";
    }

    /**
     * 格式化字符串, 如 4\'\null\ 转换为4
     *
     * @param str
     * @return
     */
    public static String reg(String str) {
        return str.replaceAll("\\\\", "").replaceAll("\'", "").replaceAll("null", "");
    }

    /**
     * 半角转全角
     *
     * @param input
     * @return
     */
    public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);

            }
        }
        System.out.println(new String(c));
        return new String(c);
    }

    /**
     * 全角转半角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);

            }
        }
        return new String(c);
    }

    public static Integer toIntger(String str) {
        if (isBlank(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }

    public static Integer toIntger(Object str) {
        if (str == null) {
            return 0;
        }
        return toIntger(str.toString());
    }

    public static boolean andEquals(String str, String... eq) {
        if (isBlank(str)) {
            return false;
        }
        for (String e : eq) {
            if (!str.equals(e)) {
                return false;
            }
        }
        return true;
    }

    public static boolean orEquals(String str, String... eq) {
        if (isBlank(str)) {
            return false;
        }
        for (String e : eq) {
            if (str.equals(e)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBlank(Object str) {
        if (str == null || str.toString().trim().length() < 1) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(Object str) {
        return !isBlank(str);
    }

    public static boolean isNotBlankAll(Object... str) {
        for (Object obj : str) {
            if (isBlank(obj)) {
                return false;
            }
        }
        return true;
    }

    public static String subLast(String str) {
        return str.substring(0, str.length() - 1);
    }

    public static String subLast(StringBuffer sb) {
        return subLast(sb.toString());
    }

}
