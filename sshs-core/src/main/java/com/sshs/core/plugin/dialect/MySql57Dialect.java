package com.sshs.core.plugin.dialect;

/**
 * 翻页查询mysql
 *
 * @author Suny
 * @date 2018/5/11
 */
public class MySql57Dialect extends AbstractDialect {
    /**
     * Constructs a MySql57Dialect
     */
    public MySql57Dialect() {
        super();
    }

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        sql = sql.trim();

        final StringBuilder pagingSelect = new StringBuilder(sql.length() + 100);
        pagingSelect.append(sql);
        if (offset > 0) {
            pagingSelect.append(" limit " + offset + ", " + limit);
        } else {
            pagingSelect.append(" limit   " + limit);
        }
        return pagingSelect.toString();
    }
}
