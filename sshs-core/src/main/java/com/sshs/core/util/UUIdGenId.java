package com.sshs.core.util;

import tk.mybatis.mapper.genid.GenId;

/**
 * 通用UUID主键生成器
 *
 * @author Suny
 * @date 2019-11-01
 */
public class UUIdGenId implements GenId<String> {
    @Override
    public String genId(String table, String column) {
        return UuidUtil.get32UUID();
    }
}