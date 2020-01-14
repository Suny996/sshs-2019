package com.sshs.toolkit.coder.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.toolkit.coder.model.Coder;
import com.sshs.toolkit.coder.model.Column;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 代码生成dao接口
 *
 * @author Suny
 * @date 2017-10-15
 */
public interface CoderMapper extends BaseMapper<Coder> {
    /**
     * 查询所有字段
     *
     * @param tableName
     * @return
     */
    List<Column> findColumnAll(@Param("dbUser") String dbuser, @Param("tableName") String tableName);

    /**
     * 查询表列表
     *
     * @param params
     * @return
     */
    IPage<Coder> findDbTableForPageList(IPage<Coder> page,@Param("dbUser") String dbuser, @Param("variables") Map params);
}
