package com.ch.manager.utils;

import com.ch.manager.exception.ExceptionEnum;

import java.util.Map;

/**
 * @author chenhao
 * @date ${date}
 */
public class ParamUtil {

    /**
     * 校验参数是否为空
     *
     * @param map
     * @return
     */
    public static Message check(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        try {
            for (String key : map.keySet()) {
                if (map.get(key) == null) {
                    sb.append(key + ",");
                } else {
                    Object obj = map.get(key);
                    try {
                        if ("".equals(obj.toString().trim())) {
                            sb.append(key + ",");
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        } catch (Exception e) {
            Message.warn(ExceptionEnum.PARAM_CHECK);
        }
        if (sb.toString().length() > 0) {
            return Message.warn(ExceptionEnum.PARAM_EMPTY.getValue(), StringUtil.subLast(sb));
        }
        return Message.SUCCESS;
    }

    /**
     * 校验参数是否为空, 并且校验类型
     *
     * @param map
     * @param clazzValue
     * @return
     */
    public static Message check(Map<String, Object> map, Map<String, Object> clazzValue) {
        Message check = check(map);
        if (!check.getSuccess()) {
            return check;
        }
        Message checkClazz = checkClass(map, clazzValue);
        if (!checkClazz.getSuccess()) {
            return checkClazz;
        }
        return Message.SUCCESS;
    }

    /**
     * 校验参数是否为空, 并且校验类型, 长度
     *
     * @param map
     * @param clazzValue
     * @return
     */
    public static Message check(Map<String, Object> map, Map<String, Object> clazzValue, Map<String, Object> cLength) {
        Message checkClazz = clazzValue == null ? check(map) : check(map, clazzValue);
        if (!checkClazz.getSuccess()) {
            return checkClazz;
        }
        Message checkLength = checkLength(map, cLength);
        if (!checkLength.getSuccess()) {
            return checkLength;
        }
        return Message.SUCCESS;
    }

    /**
     * 校验参数长度
     *
     * @param map
     * @param clazzValue
     * @return
     */
    public static Message checkLength(Map<String, Object> map, Map<String, Object> cLength) {
        StringBuffer sb = new StringBuffer();
        StringBuffer sbLength = new StringBuffer();
        try {
            for (String key : cLength.keySet()) {
                if (map.get(key) != null && cLength.get(key) != null) {
                    if (map.get(key).toString().length() > Integer.valueOf(cLength.get(key).toString())) {
                        sb.append(key + ",");
                        sbLength.append(cLength.get(key) + ",");
                    }
                }
            }
        } catch (Exception e) {
            Message.warn(ExceptionEnum.PARAM_CHECK);
        }
        if (sb.toString().length() > 0) {
            return Message.warn(String.format(ExceptionEnum.LENGTH_ERROR.getValue(), StringUtil.subLast(sb), StringUtil.subLast(sbLength)));
        }
        return Message.SUCCESS;
    }

    /**
     * 校验参数和类型是否匹配类型
     *
     * @param map
     * @param clazzValue
     * @return
     */
    public static Message checkClass(Map<String, Object> map, Map<String, Object> clazzValue) {
        StringBuffer sb = new StringBuffer();
        try {
            for (String key : clazzValue.keySet()) {
                if (map.get(key) != null && clazzValue.get(key) != null) {
                    Class clazz = (Class) clazzValue.get(key);
                    if (!checkClass(map.get(key).toString(), clazz)) {
                        sb.append(String.format("%s{%s},", key, map.get(key)));
                    }
                }
            }
        } catch (Exception e) {
            Message.warn(ExceptionEnum.PARAM_CHECK);
        }
        if (sb.toString().length() > 0) {
            return Message.warn(String.format(ExceptionEnum.CLASS_ERROR.getValue(), StringUtil.subLast(sb)));
        }
        return Message.SUCCESS;
    }

    /**
     * 校验参数是否为空,并且是否为时间格式字符串
     *
     * @param map
     * @return
     */
    public static Message checkDate(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        StringBuffer keySb = new StringBuffer();
        StringBuffer valueSb = new StringBuffer();
        try {
            for (String key : map.keySet()) {
                if (map.get(key) == null) {
                    sb.append(key);
                } else {
                    Object obj = map.get(key);
                    try {
                        if ("".equals(obj.toString().trim())) {
                            sb.append(key + ",");
                        } else if (!DateUtil.checkFormat(obj.toString())) {
                            keySb.append(key + ",");
                            valueSb.append(obj + ",");
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        } catch (Exception e) {
            Message.warn(ExceptionEnum.PARAM_CHECK);
        }
        if (sb.length() > 0) {
            return Message.warn(String.format(ExceptionEnum.PARAM_EMPTY.getValue(), StringUtil.subLast(sb)));
        }
        if (keySb.length() > 0) {
            return Message.warn(String.format(ExceptionEnum.PARAM_VALUE_ERROR.getValue(), StringUtil.subLast(keySb), StringUtil.subLast(valueSb)));
        }
        return Message.SUCCESS;
    }

    /**
     * 校验参数是否为空,并且是否为时间格式字符串,并且startKey是否小于结果时间
     *
     * @param map
     * @param startKey
     * @param endKey
     * @return
     */
    public static Message checkStartEnd(Map<String, Object> map, String startKey, String endKey) {
        Message check = checkDate(map);
        if (!check.getSuccess()) {
            return check;
        }
        boolean dateExce = DateUtil.getDateExce(map.get(startKey).toString(), map.get(endKey).toString());
        if (dateExce) {
            return Message.warn(ExceptionEnum.PARAM_EX_DATE, map.get(startKey).toString(), map.get(endKey).toString());
        }
        return Message.SUCCESS;
    }

    public static boolean checkClass(String str, Class clazz) {
        try {
            if (ClassUtil.equals(clazz, String.class)) {
                return true;
            }
            if (ClassUtil.equals(clazz, Integer.class)) {
                Integer i = Integer.valueOf(str);
            } else if (ClassUtil.equals(clazz, Short.class)) {
                Short s = Short.valueOf(str);
            } else if (ClassUtil.equals(clazz, Byte.class)) {
                Byte b = Byte.valueOf(str);
            } else if (ClassUtil.equals(clazz, Double.class)) {
                Double d = Double.valueOf(str);
            } else if (ClassUtil.equals(clazz, Float.class)) {
                Float f = Float.valueOf(str);
            } else if (ClassUtil.equals(clazz, Boolean.class)) {
                Boolean b = Boolean.valueOf(str);
            } else if (ClassUtil.equals(clazz, Long.class)) {
                Long b = Long.valueOf(str);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
