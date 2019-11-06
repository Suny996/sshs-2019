package com.sshs.core.base.mapper;


import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

@RegisterMapper
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T> {
    /**
     * 列表查询可分页查询
     *
     * @param parameter
     * @return
     */
    List<T> findForList(Object parameter);
}
