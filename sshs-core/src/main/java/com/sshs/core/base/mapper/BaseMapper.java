package com.sshs.core.base.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {
    /**
     * 列表查询不可分页
     *
     * @param parameter
     * @return
     */
    List<T> findForList(@Param("variable") Object parameter);

    /**
     * 列表查询可分页
     *
     * @param parameter
     * @return
     */
    IPage<T> findForList( @Param("page") IPage<T> page, @Param("variable") Object parameter);
}
