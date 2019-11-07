package com.sshs.toolkit.coder.service;


import com.sshs.core.base.service.IBaseService;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.toolkit.coder.model.Coder;
import com.sshs.toolkit.coder.model.Column;

import java.util.List;
import java.util.Map;

/**
 * 类名称：代码生成器接口类
 *
 * @author Suny
 * @date 2017年10月24日
 */
public interface ICoderService extends IBaseService<Coder> {
    /**
     * 列表(主表)
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    List<Column> findColumnForList(String tableName);

    /**
     * 查询表信息
     *
     * @param params
     * @return
     */
    Message<Page<Coder>> findDbTableForPageList(int limit, int offset, Map<String, Object> params);

    /**
     * 生成代码
     * @param tableName
     * @return
     */
    Message<Coder> run(String tableName);

}
