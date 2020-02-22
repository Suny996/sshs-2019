package com.sshs.system.error;

import com.sshs.core.exception.BaseErrorCode;

public enum SystemErrorCode implements BaseErrorCode<SystemErrorCode> {
    ROLE_CODE_EXISTS(20001, "角色编号已经存在"),
    ROLE_NAME_EXISTS(20002, "角色名称已经存在"),
    NO_INPUT_PARAMETER(101, "输入参数不能为空"), MAX_INPUT_PARAMETER(102, "输入参数超出允许范围"),
    SAVE_FAILURE(10001, "保存失败"), NO_UPDATE_ENTITY(10002, "未更新到任何记录");

    int code;
    String msg;

    SystemErrorCode(int code, String msg) {
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
