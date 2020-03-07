package com.sshs.system.userrole.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.message.Message;
import com.sshs.core.util.UuidUtil;
import com.sshs.core.wrapper.QueryWrapper;
import com.sshs.system.role.model.Role;
import com.sshs.system.role.service.IRoleService;
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
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        List<Role> roles = roleService.findList(new Role());
        queryWrapper.setEntity(params);
        List<UserRole> userRoles = mapper.selectList(queryWrapper);
        data.put("roles", roles);
        data.put("userRoles", userRoles);
        return Message.success(data);
    }

    @Override
    public void deleteByUserCode(String userCode) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userCode",userCode);
        mapper.delete(queryWrapper);
    }
}
