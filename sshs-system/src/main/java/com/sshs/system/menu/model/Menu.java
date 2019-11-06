package com.sshs.system.menu.model;

import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统管理->系统管理-菜单表bean Menu类
 *
 * @author Suny
 * @date 2018/03/13
 */
@Alias("Menu")
@Table(name = "SYS_MENU")
public class Menu implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 菜单编码
     */
    @Id
    @Column(name = "MENU_CODE")
    private String id;

    /**
     * 菜单名称
     */
    @Column(name = "MENU_NAME")
    private String menuName;

    /**
     * 菜单类型:0-节点，1-功能
     */
    @Column(name = "MENU_TYPE")
    private String menuType;

    /**
     * 菜单路径URL
     */
    @Column(name = "MENU_URL")
    private String menuUrl;

    /**
     * 所属父菜单
     */
    @Column(name = "PARENT_CODE")
    private String parentCode;

    /**
     * 是否相对路径
     */
    @Column(name = "IS_RELATIVE_PATH")
    private String isRelativePath;

    /**
     * 菜单层次
     */
    @Column(name = "MENU_LEVEL")
    private BigDecimal menuLevel;

    /**
     * 同级菜单顺序号
     */
    @Column(name = "MENU_SEQ")
    private BigDecimal menuSeq;

    /**
     * 菜单图标路径/或样式
     */
    @Column(name = "MENU_ICON")
    private String menuIcon;

    /**
     * 菜单激活图标路径/或样式
     */
    @Column(name = "MENU_ICONA")
    private String menuIcona;

    /**
     * 是否可视:1-是,0-否
     */
    @Column(name = "IS_VISIBLE")
    private String isVisible;

    /**
     * 主窗口打开、弹出窗口打开
     */
    @Column(name = "OPEN_MODE")
    private String openMode;

    /**
     * 提示信息
     */
    @Column(name = "MENU_TIP")
    private String menuTip;

    /**
     * 是否启用:1-是,0-否
     */
    @Column(name = "IS_START")
    private String isStart;

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
     * 所属模块
     */
    @Column(name = "MENU_MODULE")
    private String menuModule;


    @Transient
    private String dataAuthType;

    @Transient
    private List<Menu> children;

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public void addChild(Menu child) {
        if (this.children == null) {
            this.children = new ArrayList<Menu>();
        }
        this.children.add(child);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return this.menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuUrl() {
        return this.menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getParentCode() {
        return this.parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getIsRelativePath() {
        return this.isRelativePath;
    }

    public void setIsRelativePath(String isRelativePath) {
        this.isRelativePath = isRelativePath;
    }

    public BigDecimal getMenuLevel() {
        return this.menuLevel;
    }

    public void setMenuLevel(BigDecimal menuLevel) {
        this.menuLevel = menuLevel;
    }

    public BigDecimal getMenuSeq() {
        return this.menuSeq;
    }

    public void setMenuSeq(BigDecimal menuSeq) {
        this.menuSeq = menuSeq;
    }

    public String getMenuIcon() {
        return this.menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuIcona() {
        return this.menuIcona;
    }

    public void setMenuIcona(String menuIcona) {
        this.menuIcona = menuIcona;
    }

    public String getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(String isVisible) {
        this.isVisible = isVisible;
    }

    public String getOpenMode() {
        return this.openMode;
    }

    public void setOpenMode(String openMode) {
        this.openMode = openMode;
    }

    public String getMenuTip() {
        return this.menuTip;
    }

    public void setMenuTip(String menuTip) {
        this.menuTip = menuTip;
    }

    public String getIsStart() {
        return this.isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
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

    public String getMenuModule() {
        return this.menuModule;
    }

    public void setMenuModule(String menuModule) {
        this.menuModule = menuModule;
    }

    public String getDataAuthType() {
        return dataAuthType;
    }

    public void setDataAuthType(String dataAuthType) {
        this.dataAuthType = dataAuthType;
    }
}