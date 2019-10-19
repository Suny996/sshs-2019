package com.sshs.core.exception;

import com.sshs.core.message.Message;

/**
 * 异常类
 * 
 * @author Suny
 * @date 2017-10-22
 *
 */
public class BusinessException extends RuntimeException {

	/**
	 */
	private static final long serialVersionUID = -7638041501183925225L;

	private String code;

	public BusinessException(String code) {
		super(Message.getMessage(code));
		this.code = code;
	}

	public BusinessException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public BusinessException(String code, String msg, Throwable cause) {
		super(msg, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}