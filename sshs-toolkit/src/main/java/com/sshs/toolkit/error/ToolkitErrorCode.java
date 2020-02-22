package com.sshs.toolkit.error;

import com.sshs.core.exception.BaseErrorCode;

public enum ToolkitErrorCode implements BaseErrorCode<ToolkitErrorCode> {
    GENERATE_EXCEPTION(20001, "代码生成异常"),
    COLUMN_NOT_FOUND(20002, "数据表不存在");

    int code;
    String msg;

    ToolkitErrorCode(int code, String msg) {
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
