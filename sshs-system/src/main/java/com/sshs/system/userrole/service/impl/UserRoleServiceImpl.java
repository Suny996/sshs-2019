package com.sshs.system.userrole.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.message.Message;
import com.sshs.core.util.UuidUtil;
import com.sshs.system.role.model.Role;
import com.sshs.system.role.service.IRoleService;
import com.sshs.system.user.model.User;
import com.sshs.system.userrole.mapper.UserRoleMapper;
import com.sshs.system.userrole.model.UserRole;
import com.sshs.system.userrole.service.IUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统管理->系统管理-用户角色对照表service实现类
 *
 * @author 61910
 * @date 2018/11/16
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);
    @Resource
    private UserRoleMapper mapper;

    @Resource
    private IRoleService roleService;

    /**
     * 保存系统管理->系统管理-用户角色对照表数据方法
     *
     * @param user
     * @return Message
     */
    @Override
    public Message save(User user) {
        mapper.deleteByUserCode(user.getUserCode());
        List<String> roleCodes = user.getRoleCodes();
        for (String roleCode : roleCodes) {
            UserRole userRole = new UserRole();
            userRole.setRoleCode(roleCode);
            userRole.setUserRoleId(UuidUtil.get32UUID());
            userRole.setUserCode(user.getUserCode());
            super.save(userRole);
        }
        return Message.success();
    }


    /**
     * 批量保存系统管理->系统管理-用户角色对照表数据方法
     *
     * @param userRoles
     * @return Message
     */
    @Override
    public Message save(List<UserRole> userRoles) {
        for (UserRole userRole : userRoles) {
            userRole.setUserRoleId(UuidUtil.get32UUID());
        }
        return super.save(userRoles);
    }


    /**
     * 用户分配角色查询功能
     *
     * @param params
     * @return
     */
    @Override
    public Message queryForList(UserRole params) {
        Map<String, Object> data = new HashMap<String, Object>();
        List<Role> roles = (List<Role>) roleService.findForList(new Role()).getData();
        List<UserRole> userRoles = (List<UserRole>) queryForList(params).getData();
        data.put("roles", roles);
        data.put("userRoles", userRoles);
        return Message.success(data);
    }
}
