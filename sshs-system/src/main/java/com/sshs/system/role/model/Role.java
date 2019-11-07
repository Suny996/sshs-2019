package com.sshs.system.role.model;

import com.sshs.core.util.UUIdGenId;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.ibatis.type.Alias;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统管理->系统管理-角色表bean Role类
 *
 * @author 61910
 * @date 2018/11/07
 */
@Alias("Role")
@Table(name = "SYS_ROLE")
public class Role implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     * 角色ID
     */
    @Id
    @KeySql(genId = UUIdGenId.class)
    @Column(name = "ROLE_ID", length = 32)
    private String roleId;

    /**
     * 修改人
     */
    @Column(name = "UPD_USER_CODE")
    private String updUserCode;

    /**
     * 修改机构
     */
    @Column(name = "UPD_ORG_CODE")
    private String updOrgCode;

    /**
     * 修改日期
     */
    @Column(name = "UPD_DATE")
    private Date updDate;

    /**
     * 角色编码
     */
    @Column(name = "ROLE_CODE")
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 角色级别:对应机构级别
     */
    @Column(name = "ROLE_LEVEL")
    private String roleLevel;

    /**
     * 状态:01--启用，02--停用
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 创建人
     */
    @Column(name = "CRT_USER_CODE")
    private String crtUserCode;

    /**
     * 创建机构
     */
    @Column(name = "CRT_ORG_CODE")
    private String crtOrgCode;

    /**
     * 创建日期
     */
    @Column(name = "CRT_DATE")
    private Date crtDate;


    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUpdUserCode() {
        return this.updUserCode;
    }

    public void setUpdUserCode(String updUserCode) {
        this.updUserCode = updUserCode;
    }

    public String getUpdOrgCode() {
        return this.updOrgCode;
    }

    public void setUpdOrgCode(String updOrgCode) {
        this.updOrgCode = updOrgCode;
    }

    public Date getUpdDate() {
        return this.updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public String getRoleCode() {
        return this.roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoleLevel() {
        return this.roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCrtUserCode() {
        return this.crtUserCode;
    }

    public void setCrtUserCode(String crtUserCode) {
        this.crtUserCode = crtUserCode;
    }

    public String getCrtOrgCode() {
        return this.crtOrgCode;
    }

    public void setCrtOrgCode(String crtOrgCode) {
        this.crtOrgCode = crtOrgCode;
    }

    public Date getCrtDate() {
        return this.crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("roleId", roleId)
                .append("updUserCode", updUserCode)
                .append("updOrgCode", updOrgCode)
                .append("updDate", updDate)
                .append("roleCode", roleCode)
                .append("roleName", roleName)
                .append("remark", remark)
                .append("roleLevel", roleLevel)
                .append("status", status)
                .append("crtUserCode", crtUserCode)
                .append("crtOrgCode", crtOrgCode)
                .append("crtDate", crtDate)
                .toString();
    }
}