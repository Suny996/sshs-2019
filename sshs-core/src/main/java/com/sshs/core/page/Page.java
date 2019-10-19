package com.sshs.core.page;

import com.alibaba.fastjson.JSONObject;
import com.sshs.core.util.ReflectHelper;
import com.sshs.core.constant.Global;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.Alias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页类
 *
 * @author Suny
 * @date 2017年9月28日
 */
@Alias("Page")
public class Page<T> {
    private static Logger logger = LoggerFactory.getLogger(Page.class);
    /**
     * 每页显示记录数
     */
    @Value("${page.pageSize}")
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 当前记录起始索引
     */
    private int currentRow;
    /**
     * bootstrap-table 当前记录起始索引
     */
    private int offset;
    /**
     * 每页显示记录数
     */
    private int limit;
    /**
     * 排序
     */
    private String sort, order = "";
    private String orderBy = "";
    List<T> rows;
    Map<String, Object> variables = new HashMap<String, Object>(20);
    Map<String, Object> userdata = new HashMap<String, Object>(10);
    Map<String, Object> operators = new HashMap<String, Object>(20);

    public Page() {
        /*try {
            if (this.pageSize == 0) {
                this.pageSize = 10;
            }
            this.limit = this.pageSize;
            if (this.currentPage > 1) {
                this.offset = (this.currentPage - 1) * this.pageSize;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.pageSize = 10;
            this.limit = this.pageSize;
        }
*/
    }

    public Page(int limit, int offset, Map<String, Object> variables) {
        this.setLimit(limit);
        this.setOffset(offset);
        if (this.operators == null || this.operators.isEmpty()) {
            Map<String, Object> operators = null;
            Object operator = variables.get("operators");
            if (operator instanceof String) {
                operators = (Map<String, Object>) JSONObject.parse((String) operator);
            }
            if (operators != null && !operators.isEmpty()) {
                this.setOperators(operators);
            }
        }
        this.setVariables(variables);
    }
/*
	public void getPage(HttpServletRequest request) {
		this.limit = 10;
		Map<String, String[]> properties = request.getParameterMap();
		Iterator<Map.Entry<String, String[]>> entries = properties.entrySet().iterator();
		Map.Entry<String, String[]> entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			this.variables.put(name, value);
		}
	}*/

    public int getTotalPage() {
        if (limit == 0) {
            this.limit = 10;
        }
        if (totalCount % limit == 0) {
            totalPage = totalCount / limit;
        } else {
            totalPage = totalCount / limit + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (currentPage > getTotalPage()) {
            currentPage = getTotalPage();
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentRow() {
        currentRow = this.offset;
        if (currentRow < 0) {
            currentRow = 0;
        }
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getOffset() {
        if (this.currentPage >= 1) {
            //this.offset = (this.currentPage - 1) * this.pageSize;
            this.offset = this.currentPage;
        }
        return offset;
    }

    public int getLimit() {
        if (this.pageSize >= 1) {
            this.limit = this.pageSize;
        }
        return limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public Map<String, Object> getUserdata() {
        return userdata;
    }

    public void setUserdata(Map<String, Object> userdata) {
        this.userdata = userdata;
    }

    public Map<String, Object> getOperators() {
        return operators;
    }

    public void setOperators(Map<String, Object> operators) {
        this.operators = operators;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        if (StringUtils.isNotEmpty(sort)) {
            this.sort = ReflectHelper.getColumnName(sort);
            this.orderBy = this.sort + " " + this.orderBy;
        }
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        if (StringUtils.isNotEmpty(order)) {
            this.order = order;
            if (StringUtils.isNotEmpty(this.orderBy)) {
                this.orderBy = this.orderBy + " " + order;
            }
        }
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        String orderName = "";
        String order = "";
        if (StringUtils.isNotEmpty(orderBy)) {
            if (orderBy.contains(Global.CHARACTER_BLANK)) {
                String[] orders = orderBy.split(Global.CHARACTER_BLANK);
                orderName = orders[0];
                order = orders[1];
                this.orderBy = ReflectHelper.getColumnName(orderName) + Global.CHARACTER_BLANK + order;
            } else {
                this.orderBy = orderBy;
            }
        } else {
            if (StringUtils.isNotEmpty(this.sort)) {
                this.orderBy = this.sort + Global.CHARACTER_BLANK + this.order;
            } else {
                this.orderBy = orderBy;
            }
        }
    }
}
