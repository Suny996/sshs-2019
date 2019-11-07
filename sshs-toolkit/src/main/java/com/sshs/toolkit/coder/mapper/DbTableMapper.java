package com.sshs.toolkit.coder.mapper;

import com.sshs.core.page.Page;
import com.sshs.toolkit.coder.model.DbTable;

import tk.mybatis.mapper.common.Mapper;

/**
 * 
 * @author Suny
 *
 */
public interface DbTableMapper extends Mapper<DbTable> {
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
