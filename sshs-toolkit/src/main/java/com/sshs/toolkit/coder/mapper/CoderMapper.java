package com.sshs.toolkit.coder.mapper;

import com.sshs.toolkit.coder.model.Coder;
import com.sshs.toolkit.coder.model.Column;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 代码生成dao接口
 *
 * @author Suny
 * @date 2017-10-15
 */
public interface CoderMapper extends Mapper<Coder> {


    /**
     * 删除
     *
     * @param tableName
     * @return
     */
    int deleteByTableName(String tableName);


    /**
     * 查询所有字段
     *
     * @param tableName
     * @return
     */
    List<Column> findColumnAll(String tableName);

    /**
     * 查询表列表
     *
     * @param params
     * @return
     */
    List<Coder> findDbTableForPageList(Map params);
}
