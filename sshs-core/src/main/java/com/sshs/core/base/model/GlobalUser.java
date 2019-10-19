package com.sshs.core.base.model;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 系统全局用户
 *
 * @author Suny
 * @date 2018/01/09
 */
public class GlobalUser implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 机构编号:到具体的部门、支行和二级中心
     */
    private String orgCode;

    /**
     * 岗位
     */
    private String postNo;

    /**
     * 性别:1－男性，2－女性，9－未说明性别
     */
    private String userSex;


    /**
     * 用户状态:0-无效，1-有效
     */
    private String status;


    /**
     * 在线状态:在线、离线、锁定
     */
    private String onlineStatus;

    /**
     * IP地址
     */
    private String ipAddr;

    private List<Map<String, Object>> menus;
    private Map<String, Object> buttons;

    /**
     * role角色
     */
    @Transient
    private List<String> roleCodes;


    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getPostNo() {
        return this.postNo;
    }

    public void setPostNo(String postNo) {
        this.postNo = postNo;
    }

    public String getUserSex() {
        return this.userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOnlineStatus() {
        return this.onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getIpAddr() {
        return this.ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public List<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(List<String> roleCodes) {
        this.roleCodes = roleCodes;
    }

    public List<Map<String, Object>> getMenus() {
        return menus;
    }

    public void setMenus(List<Map<String, Object>> menus) {
        this.menus = menus;
    }

    public Map<String, Object> getButtons() {
        return buttons;
    }

    public void setButtons(Map<String, Object> buttons) {
        this.buttons = buttons;
    }
}