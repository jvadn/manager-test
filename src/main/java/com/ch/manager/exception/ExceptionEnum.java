package com.ch.manager.exception;

/**
 * @author chenhao
 * @date ${date}
 */
public enum ExceptionEnum {

    SYSTEM_ERROR(1000, "系统内部错误！"), SYSTEM_POWER_ERROR(1001, "无访问权限,请检查系统标识！"),

    PARAM_EMPTY(2001, "参数[%s]为空！"), CLASS_ERROR(2002, "参数[%s]类型错误！"),
    PARAM_VALUE_ERROR(2003, "参数[%s]值[%s]错误！"), PARAM_CHECK(2004, "参数校验不通过！"),
    PARAM_EX_DATE(2005, "起始时间[%s]不能大于结束时间[%s]!"), LENGTH_ERROR(2006, "[%s]超长[%s]"),
    PARAM_REPEAT(2007, "参数[%s]重复！"), PARAM_ERROR(2008, "参数错误！%s"), SERVICE_ERROR(2009, "电文号[%s]不存在！"),

    QUERY_EMPTY(3001, "%s查询为空！"), QUERY_EXIST(3002, "%s具有唯一性, %s不允许重复！"),

    WEB_SERVICE_INIT_ERROR(10000, "WebService 初始化失败！");

    private final String fix = "SRM";
    private final int code;
    private final String value;

    ExceptionEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return fix + "_SYSTEM_" + code;
    }

    public String getValue() {
        return value;
    }

}
