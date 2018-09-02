package com.ch.manager.utils;

import java.io.Serializable;

/**
 * @author chenhao
 * @date ${date}
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    public final static Message SUCCESS = new Message(true, "1");
    public final static Message WARN = new Message(true, "-1");


    private boolean success;
    private String code;
    private String msg;
    private Object result;

    public Message() {
    }

    public Message(boolean success, String code) {
        this.success = success;
        this.code = code;
    }

    public Message(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public Message(boolean success, String code, String msg, Object result) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static Message success(Object result) {
        return new Message(true, "1", "", result);
    }

    public static Message warn(String msg) {
        return new Message(false, "-1", msg, "");
    }

    public static Message warn(String code, String msg) {
        return new Message(false, code, msg, "");
    }

    public static Message warn(Enum ex) {
        return new Message(false, EchoUtil.getEnumCode(ex), EchoUtil.getEnumValue(ex), "");
    }

    public static Message warn(Enum ex, Object... args) {
        return new Message(false, EchoUtil.getEnumCode(ex), EchoUtil.getEnumValue(ex, args), "");
    }

}
