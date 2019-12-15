package com.sshs.system.post.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/** 
 * 系统管理->系统管理-岗位表bean Post类
 * @author 61910
 * @date 2018/11/08
 */
@Alias("Post")
@Table(name="SYS_POST")
public class Post implements Serializable {

private static final long serialVersionUID = 1L;

    /**
    * 岗位主键
    */
	@Id
	@TableId(type= IdType.ID_WORKER_STR)
	@Column(name="POST_ID",length = 32)
	private String id;

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

    /**
    * 岗位编号
    */
	@Column(name="POST_NO")
	private String postNo;

    /**
    * 岗位名称
    */
	@Column(name="POST_NAME")
	private String postName;

    /**
    * 岗位序列:管理序列、专业序列、营销序列、技能序列
    */
	@Column(name="POST_SERIAL")
	private String postSerial;

    /**
    * 岗位子序列
    */
	@Column(name="POST_SERIAL_CHILD")
	private String postSerialChild;

    /**
    * 岗位描述
    */
	@Column(name="POST_DESC")
	private String postDesc;

    /**
    * 所属标准部门
    */
	@Column(name="STAND_ORG_CODE")
	private String standOrgCode;

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


	public String getId(){
		return this.id;
	}
	
	public void setId(String postId){
		this.id = id;
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

	public String getPostNo(){
		return this.postNo;
	}
	
	public void setPostNo(String postNo){
		this.postNo = postNo;
	}

	public String getPostName(){
		return this.postName;
	}
	
	public void setPostName(String postName){
		this.postName = postName;
	}

	public String getPostSerial(){
		return this.postSerial;
	}
	
	public void setPostSerial(String postSerial){
		this.postSerial = postSerial;
	}

	public String getPostSerialChild(){
		return this.postSerialChild;
	}
	
	public void setPostSerialChild(String postSerialChild){
		this.postSerialChild = postSerialChild;
	}

	public String getPostDesc(){
		return this.postDesc;
	}
	
	public void setPostDesc(String postDesc){
		this.postDesc = postDesc;
	}

	public String getStandOrgCode(){
		return this.standOrgCode;
	}
	
	public void setStandOrgCode(String standOrgCode){
		this.standOrgCode = standOrgCode;
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
}