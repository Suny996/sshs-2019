package com.sshs.core.customise.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.customise.model.Customise;
import com.sshs.core.page.Page;

import java.util.List;

/**
 * 代码生成mapper接口
 *
 * @author Suny
 * @date 2017-10-15
 */
public interface CustomiseMapper extends BaseMapper<Customise> {
    /**
     * 新增
     *
     * @param customise
     * @return
     */
    //int save(Customise customise);


    /**
     * 根据主键删除
     *
     * @param customise
     * @return
     */
    //int delete(Wrapper<Customise> customise);

    /**
     * 根据名称删除
     *
     * @param customise
     * @return
     */
    //int deleteByCustomiseName(Customise customise);

    /**
     * 根据主键查询
     *
     * @param customise
     * @return
     */
    //List<Customise> getCustomises(Customise customise);

    /**
     * 分页查询示例，该功能不需要
     *
     * @param page
     * @param customise
     * @return
     */
    //IPage<Customise> getCustomises(Page<Customise> page, Wrapper<Customise> customise);

}
