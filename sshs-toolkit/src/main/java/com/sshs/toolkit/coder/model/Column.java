package com.sshs.toolkit.coder.model;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 列对象
 * 
 * @author Suny
 *
 */
@Alias("Column")
public class Column implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 列号
	 */
	private String columnId;
	/**
	 * 表名
	 */
	private String tableName;

	public String getPropFuncName() {
		return propFuncName;
	}

	public void setPropFuncName(String propFuncName) {
		this.propFuncName = propFuncName;
	}

	/**
	 * 表描述
	 */
	private String tableComment;
	/**
	 * 属性名称
	 */
	private String propertyName;
	/**
	 * 属性set get 方法名称
	 */
	private String propFuncName;
	/**
	 * pojo的数据类型
	 */
	private String propertyType;
	/**
	 * 字段名称；
	 */
	private String columnName;
	/**
	 * 字段描述；
	 */
	private String columnComment;
	/**
	 * 数据库字段类型
	 */
	private String columnType;
	/**
	 * 数据库字段長度
	 */
	private Integer columnSize;
	/**
	 * 数据库字段精度
	 */
	private Integer columnScale;

	/**
	 * 默认值
	 */
	private String defaultValue;

	/**
	 * 是否可为空
	 */
	private String requiredFlag;

	/**
	 * 是否主键
	 */
	private String primaryKeyFlag;

	/**
	 * 是否查询
	 */
	private String searchFlag;

	/**
	 * 查询类型
	 */
	private String searchType;

	/**
	 * 列表是否显示
	 */
	private String listFlag;

	/**
	 * 修改是否显示
	 */
	private String modifyFlag;

	/**
	 * 修改是否只读
	 */
	private String modifyFlag1;

	/**
	 * 编辑组件类型
	 */
	private String componentType1;

	/**
	 * 新增是否显示
	 */
	private String addFlag;

	/**
	 * 新增是否之读
	 */
	private String addDefault;

	/**
	 * 验证
	 */
	private String validators;

	/**
	 * 
	 */
	private String dataRange;

	/**
	 * 查询下拉框字典类型dictCode
	 */
	private String dictCode;

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public void setModifyFlag1(String modifyFlag1) {
		this.modifyFlag1 = modifyFlag1;
	}

	public String getComponentType1() {
		return componentType1;
	}

	public void setComponentType1(String componentType1) {
		this.componentType1 = componentType1;
	}

	public String getValidators() {
		return validators;
	}

	public void setValidators(String validators) {
		this.validators = validators;
	}

	public String getDataRange() {
		return dataRange;
	}

	public void setDataRange(String dataRange) {
		this.dataRange = dataRange;
	}

	private String remarks;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public Integer getColumnScale() {
		return columnScale;
	}

	public void setColumnScale(Integer columnScale) {
		this.columnScale = columnScale;
	}

	public void setColumnSize(Integer columnSize) {
		this.columnSize = columnSize;
	}

	public Integer getColumnSize() {
		return columnSize;
	}

	public String getRequiredFlag() {
		return requiredFlag;
	}

	public void setRequiredFlag(String requiredFlag) {
		this.requiredFlag = requiredFlag;
	}

	public String getPrimaryKeyFlag() {
		return primaryKeyFlag;
	}

	public void setPrimaryKeyFlag(String primaryKeyFlag) {
		this.primaryKeyFlag = primaryKeyFlag;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getListFlag() {
		return listFlag;
	}

	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	public String getAddDefault() {
		return addDefault;
	}

	public void setAddDefault(String addDefault) {
		this.addDefault = addDefault;
	}

	public String getModifyFlag1() {
		return modifyFlag1;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

}
