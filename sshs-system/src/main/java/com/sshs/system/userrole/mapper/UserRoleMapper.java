package com.sshs.system.userrole.mapper;

import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.core.page.Page;
import com.sshs.system.userrole.model.UserRole;
import org.apache.ibatis.annotations.Mapper;


/**
 * 系统管理->系统管理-用户角色对照表类
 *
 * @author 61910
 * @date 2018/11/16
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    Page<UserRole> findForPageList();

    int deleteByUserCode(String userCode);
}