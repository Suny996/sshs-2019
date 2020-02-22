package com.sshs.security.error;

import com.sshs.core.exception.BaseErrorCode;

public enum SecurityErrorCode implements BaseErrorCode<SecurityErrorCode> {
    USERNAME_CAN_NOT_NULL(10003, "用户名称不能为空"),
    USER_NOT_EXISTS(10003, "用户不存在"),
    NO_PRIVILEGE_FOUND(10004, "未找到任何权限"),
    AUTHORISE_EXCEPTION(10005, "身份验证发生异常"),
    AUTHORISE_FAILURE(10006,"身份验证未通过"),
    ACCOUNT_IS_LOCKED(10007,"账户已锁定"),
    PASSWORD_IS_WRONG(10008,"密码错误"),
    PASSWORD_IS_EXPIRED(10008,"密码已过期"),
    ACCOUNT_IS_EXPIRED(10008,"账户已过期"),
    ACCOUNT_IS_DISABLED(10008,"账户已禁用"),
    NO_AUTHORISED(100601,"您尚未登录"),
    AUTHORISE_INVALID(100602,"登录无效"),
    AUTHORISE_TIMEOUT(10603,"登录超时"),
    OLD_PASSWORD_IS_WRONG(10009,"密码错误");

    int code;
    String msg;

    SecurityErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMsg(String... parameter) {
       /* String desc = helper.getMessage(this.name());
        if (desc == null || desc.equals(this.name())) {
            return this.desc;
        }*/
        return msg;
    }

    public void setDesc(String msg) {
        this.msg = msg;
    }
}
