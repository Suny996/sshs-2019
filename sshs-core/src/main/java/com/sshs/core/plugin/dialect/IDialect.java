package com.sshs.core.plugin.dialect;

/**
 * 数据库方言接口
 * 
 * @author Suny
 * @date 2017-12-01
 */
public interface IDialect {
	/**
	 * 获取分页后sql
	 * 
	 * @param query
	 * @param offset
	 * @param limit
	 * @return
	 */
	String getLimitString(String query, int offset, int limit);

	/**
	 * 生成建表语句关键字
	 * 
	 * @return
	 */
	String getCreateTableString();

	/**
	 * 生成建表语句结束关键字
	 * 
	 * @param tableName
	 * @return
	 */
	String getAlterTableString(String tableName);

	/**
	 * 获取模糊匹配变量（like 加%）
	 * @param variable
	 * @return
	 */
	String getLikedVariable(String variable);
}
