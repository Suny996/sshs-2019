package com.sshs.core.plugin.dialect;

/**
 * An SQL dialect for DB2.
 * 
 * @author Suny
 */
public class Db2Dialect extends AbstractDialect {

	@Override
	public String getLowercaseFunction() {
		return "lcase";
	}

	@Override
	public boolean supportsSequences() {
		return true;
	}

	@Override
	public boolean supportsPooledSequences() {
		return true;
	}

	@Override
	public boolean supportsLimit() {
		return true;
	}

	@Override
	public boolean supportsVariableLimit() {
		return false;
	}

	/**
	 * @param sql
	 * @param offset
	 * @param limit
	 * @return
	 */
	@Override
	public String getLimitString(String sql, int offset, int limit) {
		int startOfSelect = sql.toLowerCase().indexOf("select");

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100).append(sql, 0, startOfSelect)
				.append("select * from ( select ").append(getRowNumber(sql));
		if (hasDistinct(sql)) {
			pagingSelect.append(" row_.* from ( ").append(sql, startOfSelect, getOrderByIdex(sql))
					.append(" ) as row_");
		} else {
			pagingSelect.append(sql, startOfSelect + 6, getOrderByIdex(sql));
		}
		pagingSelect.append(" ) as temp_ where rownumber_ ");

		pagingSelect.append(" >= " + (offset + 1) + "  fetch first " + (limit - offset) + " rows only");

		return pagingSelect.toString();
	}

	@Override
	public boolean useMaxForLimit() {
		return true;
	}

	@Override
	public boolean supportsUnionAll() {
		return true;
	}

	@Override
	public boolean supportsCurrentTimestampSelection() {
		return true;
	}
}
