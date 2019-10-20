package com.sshs.system.userRole.mapper;

import com.sshs.core.page.Page;
import com.sshs.system.userRole.model.UserRole;
import tk.mybatis.mapper.common.Mapper;


/**
* 系统管理->系统管理-用户角色对照表类
* @author 61910
* @date 2018/11/16
*/
public interface UserRoleMapper extends Mapper<UserRole> {
    public Page<UserRole> findForPageList();
}