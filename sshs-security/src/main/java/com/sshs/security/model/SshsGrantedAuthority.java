package com.sshs.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Suny
 * @date 2019-02-25
 */
public class SshsGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private String userCode;
    private String userName;
    private String orgCode;

    private String orgName;

    private final List<Privilege> roles;

    public List<Map<String, Object>> menus;
    public Map<String, Object> buttons;

    public SshsGrantedAuthority(List<Privilege> role) {
        this.roles = role;
    }

    @Override
    public String getAuthority() {
        return this.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof SshsGrantedAuthority) {
            return roles.equals(((SshsGrantedAuthority) obj).roles);
        }

        return false;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<Privilege> getRoles() {
        return roles;
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

    @Override
    public int hashCode() {
        return this.roles.hashCode();
    }

    @Override
    public String toString() {
        List<String> roles = new ArrayList<String>();
        for (Privilege i : this.roles) {
            roles.add(i.getId());
        }
        return roles.toString();
    }
}
