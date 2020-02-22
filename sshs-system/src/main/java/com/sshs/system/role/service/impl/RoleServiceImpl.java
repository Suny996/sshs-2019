package com.sshs.system.role.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.message.Message;
import com.sshs.core.util.BusiUtil;
import com.sshs.core.wrapper.QueryWrapper;
import com.sshs.system.error.SystemErrorCode;
import com.sshs.system.role.mapper.RoleMapper;
import com.sshs.system.role.model.Role;
import com.sshs.system.role.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 系统管理->系统管理-角色表Service实现类
 *
 * @author 61910
 * @date 2018/11/07
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {
    Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Resource
    private RoleMapper mapper;

    /**
     * 保存系统管理->系统管理-角色表方法
     *
     * @param role
     * @return Message
     */
    @Override
    public Message save(Role role) {
        logger.debug("角色保存开始...");
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        //role.setRoleId(UuidUtil.get32UUID());
        wrapper.eq("roleCode", role.getRoleCode());
        if (null != mapper.selectList(wrapper)) {
            return Message.failure(SystemErrorCode.ROLE_CODE_EXISTS);
        }
        wrapper = new QueryWrapper<>();
        wrapper.eq("roleName", role.getRoleName());
        List<Role> roleOldN = mapper.selectList(wrapper);
        if (BusiUtil.isNotEmpty(roleOldN)) {
            return Message.failure(SystemErrorCode.ROLE_CODE_EXISTS);
        }
        return super.save(role);
    }
}
