package com.sshs.toolkit.coder.model;

import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 
 * @author Suny
 *
 */
@Alias("DbTable")
public class DbTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@TableId
	private String tableName;
	private String tableComment;
	private String status;
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
