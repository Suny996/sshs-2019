package com.sshs.core.base.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.page.Page;

import java.util.List;

public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {
    /**
     * 默认分页查询
     *
     * @param page
     * @param parameter
     * @return
     */
    IPage<T> findForList(Page<T> page, Object parameter);

    /**
     * 默认不分页查询
     *
     * @param parameter
     * @return
     */
    List<T> findForList(Object parameter);
}
