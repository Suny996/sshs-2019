package com.sshs.core.exception;

public interface BaseErrorCode<T extends Enum<T>> {

    int getCode();

    String getMsg(String... parameter);

}