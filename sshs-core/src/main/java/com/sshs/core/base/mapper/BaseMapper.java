package com.sshs.core.base.mapper;


import com.sshs.core.page.Page;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;


public interface BaseMapper<T> extends tk.mybatis.mapper.common.Mapper<T>, MySqlMapper<T>, IdsMapper<T> {
    /**
     * 默认分页查询
     *
     * @param page
     * @param parameter
     * @return
     */
    Page<T> findForList(Page<T> page, Object parameter);

    /**
     * 默认不分页查询
     *
     * @param parameter
     * @return
     */
    List<T> findForList(Object parameter);
}
