package com.ch.manager.exception;

/**
 * @author chenhao
 * @date ${date}
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    private String code;
    private String msg;

    public ServiceException() {
        super();
    }

    public ServiceException(ExceptionEnum ex) {
        super(ex.getValue());
        this.setCode(ex.getCode());
        this.setMsg(ex.getValue());
    }

    public ServiceException(ExceptionEnum ex, String... reg) {
        super(String.format(ex.getValue(), reg));
        this.setCode(ex.getCode());
        this.setMsg(String.format(ex.getValue(), reg));
    }


    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String code, String msg) {
        super(msg);
        this.setCode(code);
        this.setMsg(msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
