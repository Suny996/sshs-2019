package com.sshs.toolkit.coder.mapper;

import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.core.page.Page;
import com.sshs.toolkit.coder.model.DbTable;

/**
 * 
 * @author Suny
 *
 */
public interface DbTableMapper extends BaseMapper<DbTable> {
	/**
	 * 根据ID查询对象
	 * @param tableName
	 * @return
	 */
	DbTable findById(String tableName);

	/**
	 * 查询table表列表
	 * @param page
	 */
	void findForPageList(Page<DbTable> page);
}
