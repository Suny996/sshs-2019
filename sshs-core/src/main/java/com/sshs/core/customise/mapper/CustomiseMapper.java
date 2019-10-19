package com.sshs.core.customise.mapper;


import com.sshs.core.customise.model.Customise;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 代码生成mapper接口
 *
 * @author Suny
 * @date 2017-10-15
 */
public interface CustomiseMapper extends Mapper<Customise> {
    /**
     * 新增
     *
     * @param customise
     * @return
     */
    int save(Customise customise);


    /**
     * 根据主键删除
     *
     * @param customise
     * @return
     */
    @Override
    int delete(Customise customise);

    /**
     * 根据名称删除
     *
     * @param customise
     * @return
     */
    int deleteByCustomiseName(Customise customise);

    /**
     * 根据主键查询
     *
     * @param customise
     * @return
     */
    List<Customise> getCustomises(Customise customise);

}
