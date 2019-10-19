package com.sshs.core.plugin.dialect;

/**
 * 数据库方言抽象类
 *
 * @author Suny
 * @date 2017-11-12
 */
public abstract class AbstractDialect implements IDialect {

    /**
     * Defines a default batch size constant
     */
    public static final String DEFAULT_BATCH_SIZE = "15";

    /**
     * Defines a "no batching" batch size constant
     */
    public static final String NO_BATCH = "0";

    /**
     * Characters used as opening for quoting SQL identifiers
     */
    public static final String QUOTE = "`\"[";

    /**
     * Char quoting SQL identifiers
     */
    public static final char CHAR_QUOTE = '`';

    /**
     * Characters used as closing for quoting SQL identifiers
     */
    public static final String CLOSED_QUOTE = "`\"]";

    /**
     * Does this dialect support sequences?
     *
     * @return True if sequences supported; false otherwise.
     */
    public boolean supportsSequences() {
        return false;
    }

    /**
     * Does this dialect support "pooled" sequences. Not aware of a better name for
     * this. Essentially can we specify the initial and increment values?
     *
     * @return True if such "pooled" sequences are supported; false otherwise.
     * @see #getCreateSequenceStrings(String, int, int)
     * @see #getCreateSequenceString(String, int, int)
     */
    public boolean supportsPooledSequences() {
        return false;
    }

    public boolean supportsLimit() {
        return false;
    }

    public boolean supportsLimitOffset() {
        return supportsLimit();
    }

    public boolean supportsVariableLimit() {
        return supportsLimit();
    }

    /**
     * ANSI SQL defines the LIMIT clause to be in the form LIMIT offset, limit. Does
     * this dialect require us to bind the parameters in reverse order?
     *
     * @return true if the correct order is limit, offset
     * @deprecated {@link #getLimitHandler()} should be overridden instead.
     */
    @Deprecated
    public boolean bindLimitParametersInReverseOrder() {
        return false;
    }

    /**
     * Does the <tt>LIMIT</tt> clause come at the start of the <tt>SELECT</tt>
     * statement, rather than at the end?
     *
     * @return true if limit parameters should come before other parameters
     * @deprecated {@link #getLimitHandler()} should be overridden instead.
     */
    public boolean bindLimitParametersFirst() {
        return false;
    }

    public boolean useMaxForLimit() {
        return false;
    }

    public boolean forceLimitUsage() {
        return false;
    }

    /**
     * Render the <tt>rownumber() over ( .... ) as rownumber_,</tt> bit, that goes
     * in the select list
     */
    public int getOrderByIdex(String sql) {
        int orderByIndex = sql.toLowerCase().lastIndexOf("order by");
        int fromByIndex = sql.toLowerCase().lastIndexOf("from ");
        if (orderByIndex > 0 && orderByIndex > fromByIndex && !hasDistinct(sql)) {
            return orderByIndex;
        }
        return sql.length();
    }

    /**
     * Render the <tt>rownumber() over ( .... ) as rownumber_,</tt> bit, that goes
     * in the select list
     */
    public String getRowNumber(String sql) {
        StringBuffer rownumber = new StringBuffer(50).append("rownumber() over(");

        int orderByIndex = sql.toLowerCase().lastIndexOf("order by");
        int fromByIndex = sql.toLowerCase().lastIndexOf("from ");

        if (orderByIndex > 0 && orderByIndex > fromByIndex && !hasDistinct(sql)) {
            rownumber.append(sql.substring(orderByIndex));
        }

        rownumber.append(") as rownumber_,");

        return rownumber.toString();
    }

    public static boolean hasDistinct(String sql) {
        return sql.toLowerCase().indexOf("select distinct") >= 0;
    }

    /**
     * @param query
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public String getLimitString(String query, int offset, int limit) {
        return getLimitString(query, (offset > 0 || forceLimitUsage()));
    }

    /**
     * @param query
     * @param hasOffset
     * @return
     */
    protected String getLimitString(String query, boolean hasOffset) {
        throw new UnsupportedOperationException("Paged queries not supported by " + getClass().getName());
    }

    /**
     * Command used to create a table.
     *
     * @return The command used to create a table.
     */
    @Override
    public String getCreateTableString() {
        return "create table";
    }

    /**
     * Command used to alter a table.
     *
     * @param tableName The name of the table to alter
     * @return The command used to alter a table.
     * @since 5.2.11
     */
    @Override
    public String getAlterTableString(String tableName) {
        final StringBuilder sb = new StringBuilder("alter table ");

        sb.append(tableName);
        return sb.toString();
    }

    /**
     * Does this dialect support a way to retrieve the database's current timestamp
     * value?
     *
     * @return True if the current timestamp can be retrieved; false otherwise.
     */
    public boolean supportsCurrentTimestampSelection() {
        return false;
    }

    /**
     * The name of the database-specific SQL function for retrieving the current
     * timestamp.
     *
     * @return The function name.
     */
    public String getCurrentTimestampSQLFunctionName() {
        // the standard SQL function name is current_timestamp...
        return "current_timestamp";
    }

    /**
     * Does this dialect support UNION ALL, which is generally a faster variant of
     * UNION?
     *
     * @return True if UNION ALL is supported; false otherwise.
     */
    public boolean supportsUnionAll() {
        return false;
    }

    /**
     * The fragment used to insert a row without specifying any column values. This
     * is not possible on some databases.
     *
     * @return The appropriate empty values clause.
     */
    public String getNoColumnsInsertString() {
        return "values ( )";
    }

    /**
     * The name of the SQL function that transforms a string to lowercase
     *
     * @return The dialect-specific lowercase function.
     */
    public String getLowercaseFunction() {
        return "lower";
    }

    /**
     * The name of the SQL function that can do case insensitive <b>like</b>
     * comparison.
     *
     * @return The dialect-specific "case insensitive" like function.
     */
    public String getCaseInsensitiveLike() {
        return "like";
    }

    // identifier quoting support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * The character specific to this dialect used to begin a quoted identifier.
     *
     * @return The dialect's specific open quote character.
     */
    public char openQuote() {
        return '"';
    }

    /**
     * The character specific to this dialect used to close a quoted identifier.
     *
     * @return The dialect's specific close quote character.
     */
    public char closeQuote() {
        return '"';
    }

    /**
     * Apply dialect-specific quoting.
     * <p/>
     * By default, the incoming value is checked to see if its first character is
     * the back-tick (`). If so, the dialect specific quoting is applied.
     *
     * @param name The value to be quoted.
     * @return The quoted (or unmodified, if not starting with back-tick) value.
     * @see #openQuote()
     * @see #closeQuote()
     */
    public final String quote(String name) {
        if (name == null) {
            return null;
        }

        if (name.charAt(0) == CHAR_QUOTE) {
            return openQuote() + name.substring(1, name.length() - 1) + closeQuote();
        } else {
            return name;
        }
    }

    /**
     * 获取模糊匹配变量（like 加%）
     *
     * @param variable
     * @return
     */
    public String getLikedVariable(String variable) {
        return "%" + variable + "%";
    }
}
