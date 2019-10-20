package com.sshs.system.userRole.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import org.apache.ibatis.type.Alias;

/** 
 * 系统管理->系统管理-用户角色对照表bean UserRole类
 * @author 61910
 * @date 2018/11/16
 */
@Alias("UserRole")
@Table(name="SYS_USER_ROLE")
public class UserRole implements Serializable {

private static final long serialVersionUID = 1L;


    /**
    * ID
    */
	@Id
	//@GeneratedValue(generator = "UUID")
	@Column(name="USER_ROLE_ID")
	private String userRoleId;

    /**
    * 用户编号
    */
	@Column(name="USER_CODE")
	private String userCode;

    /**
    * 角色编号
    */
	@Column(name="ROLE_CODE")
	private String roleCode;

    /**
    * 创建人
    */
	@Column(name="CRT_USER_CODE")
	private String crtUserCode;

    /**
    * 创建机构
    */
	@Column(name="CRT_ORG_CODE")
	private String crtOrgCode;

    /**
    * 创建日期
    */
	@Column(name="CRT_DATE")
	private Date crtDate;

    /**
    * 修改人
    */
	@Column(name="UPD_USER_CODE")
	private String updUserCode;

    /**
    * 修改机构
    */
	@Column(name="UPD_ORG_CODE")
	private String updOrgCode;

    /**
    * 修改日期
    */
	@Column(name="UPD_DATE")
	private Date updDate;


	public String getUserRoleId(){
		return this.userRoleId;
	}
	
	public void setUserRoleId(String userRoleId){
		this.userRoleId = userRoleId;
	}

	public String getUserCode(){
		return this.userCode;
	}
	
	public void setUserCode(String userCode){
		this.userCode = userCode;
	}

	public String getRoleCode(){
		return this.roleCode;
	}
	
	public void setRoleCode(String roleCode){
		this.roleCode = roleCode;
	}

	public String getCrtUserCode(){
		return this.crtUserCode;
	}
	
	public void setCrtUserCode(String crtUserCode){
		this.crtUserCode = crtUserCode;
	}

	public String getCrtOrgCode(){
		return this.crtOrgCode;
	}
	
	public void setCrtOrgCode(String crtOrgCode){
		this.crtOrgCode = crtOrgCode;
	}

	public Date getCrtDate(){
		return this.crtDate;
	}
	
	public void setCrtDate(Date crtDate){
		this.crtDate = crtDate;
	}

	public String getUpdUserCode(){
		return this.updUserCode;
	}
	
	public void setUpdUserCode(String updUserCode){
		this.updUserCode = updUserCode;
	}

	public String getUpdOrgCode(){
		return this.updOrgCode;
	}
	
	public void setUpdOrgCode(String updOrgCode){
		this.updOrgCode = updOrgCode;
	}

	public Date getUpdDate(){
		return this.updDate;
	}
	
	public void setUpdDate(Date updDate){
		this.updDate = updDate;
	}
}