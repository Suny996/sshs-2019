package com.sshs.core.plugin.dialect;

/**
 * 翻页查询oracle
 * 
 * @author Suny
 * @date 2017/11/01
 */
public class Oracle10gDialect extends AbstractDialect {
	/**
	 * Constructs a Oracle10gDialect
	 */
	public Oracle10gDialect() {
		super();
	}

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.trim();

		final StringBuilder pagingSelect = new StringBuilder(sql.length() + 100);
		if (offset > 0) {
			pagingSelect.append("SELECT * FROM ( SELECT ROW_.*, ROWNUM ROWNUM_ FROM ( ");
		} else {
			pagingSelect.append("SELECT * FROM ( ");
		}
		pagingSelect.append(sql);
		if (offset > 0) {
			pagingSelect.append(" ) ROW_ WHERE ROWNUM <= " + offset + limit + ") where rownum_ >" + offset);
		} else {
			pagingSelect.append(" ) WHERE ROWNUM <= " + limit);
		}

		return pagingSelect.toString();
	}

}
