package com.sshs.core.wrapper;

import com.google.common.base.CaseFormat;

/**
 * 重写Wrapper,实现 属性与字段的映射关系
 *
 * @author Suny
 * @date 2019-12-13
 */
public class QueryWrapper<T> extends com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<T> {
    public QueryWrapper() {
    }

    /*public QueryWrapper(T entity) {
        super(entity);
    }

    public QueryWrapper(T entity, String... columns) {
        super(entity, columns);
    }*/

    @Override
    protected String columnToString(String column) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, column);
    }
}
