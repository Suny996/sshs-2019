package com.sshs.core.constant;

/**
 * 全局常量定义
 *
 * @author Suny
 * @date 2017-12-07
 */
public class Global {
    /**
     * 机构级别、角色级别等最高级，控制权限时判断是否为最高权限，不进行数据过滤
     */
    public static final String AUTH_LEVEL_TOP = "00";

    /**
     * session存储用户标志
     */
    public static final String USER = "user";

    /**
     * 数据字典类型为字典值时的dictType
     */
    public static final String DICTIONARY_DICTTYPE_KEYVALUE = "3";

    /**
     * 字符-空格符
     */
    public static final String CHARACTER_BLANK = " ";

    /**
     * 字符-下划线
     */
    public static final String CHARACTER_UNDERLINE = "_";

    /**
     * 字符-杠
     */
    public static final String CHARACTER_STICK = "-";

    /**
     * 字符-点
     */
    public static final String CHARACTER_DOT = ".";

    /**
     * 字符-冒号
     */
    public static final String CHARACTER_COLON = ":";

    /**
     * 字符-斜杠
     */
    public static final String CHARACTER_SPRIT = "/";

    /**
     * 字符-逗号
     */
    public static final String CHARACTER_COMMA = ",";

    /**
     * false
     */
    public static final String CHARACTER_FALSE = "false";

    /**
     * token
     */
    public static final String TOKEN_HEADER = "Authorization";
}
