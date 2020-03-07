package com.sshs.system.userrole.service;

import com.sshs.core.base.service.IBaseService;
import com.sshs.core.message.Message;
import com.sshs.system.userrole.model.UserRole;

/**
 * 系统管理->系统管理-用户角色对照表service类
 *
 * @author 61910
 * @date 2018/11/16
 */
public interface IUserRoleService extends IBaseService<UserRole> {

    Message queryForList(UserRole params);

    void deleteByUserCode(String userCode);
}