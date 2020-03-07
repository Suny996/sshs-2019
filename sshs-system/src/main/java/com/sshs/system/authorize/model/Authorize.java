package com.sshs.system.authorize.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sshs.core.model.BaseEntity;
import com.sshs.system.menu.model.Menu;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * 系统管理->系统管理-角色权限表bean Authorize类
 *
 * @author 61910
 * @date 2018/11/16
 */
@Alias("Authorize")
@TableName("SYS_AUTHORIZE")
public class Authorize extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @Id
    @TableId(type = IdType.ID_WORKER_STR)
    private String authorizeId;

    /**
     * 创建日期
     */
    @Column(name = "CRT_DATE")
    private Date crtDate;

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
     * 资源ID(包括菜单ID和按钮ID)
     */
    @Column(name = "RESOURCE_ID")
    private String resourceId;

    /**
     * 资源名称
     */
    @Column(name = "RESOURCE_NAME")
    private String resourceName;

    /**
     * 角色编号
     */
    @Column(name = "ROLE_CODE")
    private String roleCode;

    /**
     * 资源类型:01-菜单,02-按钮
     */
    @Column(name = "RESOURCE_TYPE")
    private String resourceType;

    /**
     * 授权类型:01-操作,02-授权,03-全部
     */
    @Column(name = "AUTHORIZE_TYPE")
    private String authorizeType;

    /**
     * 权限级别，对应机构级别（RESOURCE_TYPE为01且AUTHORIZE_TYPE为01或03时该字段可用）
     */
    @Column(name = "DATA_AUTH_TYPE")
    private String dataAuthType;

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

    @TableField(exist = false)
    List<Menu> menus;


    public Date getCrtDate() {
        return this.crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
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

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getRoleCode() {
        return this.roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getAuthorizeType() {
        return this.authorizeType;
    }

    public void setAuthorizeType(String authorizeType) {
        this.authorizeType = authorizeType;
    }

    public String getDataAuthType() {
        return this.dataAuthType;
    }

    public void setDataAuthType(String dataAuthType) {
        this.dataAuthType = dataAuthType;
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

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public String getAuthorizeId() {
        return authorizeId;
    }

    public void setAuthorizeId(String authorizeId) {
        this.authorizeId = authorizeId;
    }

    @Override
    public String toString() {
        return "Authorize{" +
                "id='" + authorizeId + '\'' +
                ", crtDate=" + crtDate +
                ", updUserCode='" + updUserCode + '\'' +
                ", updOrgCode='" + updOrgCode + '\'' +
                ", updDate=" + updDate +
                ", resourceId='" + resourceId + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", authorizeType='" + authorizeType + '\'' +
                ", dataAuthType='" + dataAuthType + '\'' +
                ", crtUserCode='" + crtUserCode + '\'' +
                ", crtOrgCode='" + crtOrgCode + '\'' +
                ", menus=" + menus +
                '}';
    }
}