package com.sshs.core.exception;

import com.sshs.core.message.Message;

/**
 * 异常类
 *
 * @author Suny
 * @date 2017-10-22
 */
public class BusinessException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -7638041501183925225L;

    private int code;
    private String msg;

    /**
     * @param errorCode
     */
    public BusinessException(BaseErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    @Deprecated
    public BusinessException(int code) {
        super(Message.getMessage(code));
        this.code = code;
    }

    @Deprecated
    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(int code, Throwable cause) {
        super(cause);
        this.code = code;
        this.msg = cause.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}