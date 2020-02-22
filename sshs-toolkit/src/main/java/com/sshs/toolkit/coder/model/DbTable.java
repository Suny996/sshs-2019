package com.sshs.toolkit.coder.model;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 
 * @author Suny
 *
 */
@ApiModel("代码生成表实体类")
@Alias("DbTable")
public class DbTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "表名", dataType = "String")
	@TableId
	private String tableName;
	@ApiModelProperty(value = "表描述", dataType = "String")
	private String tableComment;
	@ApiModelProperty(value = "状态", dataType = "String")
	private String status;
	@ApiModelProperty(value = "生成标志", dataType = "String")
	private String coderFlag;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCoderFlag() {
		return coderFlag;
	}

	public void setCoderFlag(String coderFlag) {
		this.coderFlag = coderFlag;
	}
}
