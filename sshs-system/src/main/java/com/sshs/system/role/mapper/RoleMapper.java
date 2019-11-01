package com.sshs.system.role.mapper;

import com.sshs.core.page.Page;
import com.sshs.system.role.model.Role;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 系统管理->系统管理-角色表类
 *
 * @author 61910
 * @date 2018/11/07
 */
public interface RoleMapper extends Mapper<Role> {
    public Page<Role> findForPageList();

    /*
     * 功能描述:
     * @param:
     * @return:
     * @auther: huangnan
     * @date: 2018/11/13 18:17
     */
    public List<Role> getRoleInfo(@Param("roleCode")String roleCode, @Param("roleName")String roleName, @Param("roleId")String roleId);
    public Role getRoleByRoleCode(String roleCode);
}