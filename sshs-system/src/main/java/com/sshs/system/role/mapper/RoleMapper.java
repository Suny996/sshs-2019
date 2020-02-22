package com.sshs.system.role.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.system.role.model.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统管理->系统管理-角色表类
 *
 * @author 61910
 * @date 2018/11/07
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    IPage<Role> findForPageList();

    /**
    /* * 功能描述:
     *
     * @param:
     * @return:
     * @auther: huangnan
     * @date: 2018/11/13 18:17
     *//*
    List<Role> getRoleInfo(@Param("roleCode") String roleCode, @Param("roleName") String roleName, @Param("roleId") String roleId);

    Role getRoleByRoleCode(String roleCode);*/
}