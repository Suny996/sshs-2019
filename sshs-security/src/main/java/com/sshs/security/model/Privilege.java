package com.sshs.security.model;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 系统管理->系统管理-权限表bean Privilege类
 * 
 * @author Suny
 * @date 2018/01/09
 */
@Alias("Privilege")
public class Privilege implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
