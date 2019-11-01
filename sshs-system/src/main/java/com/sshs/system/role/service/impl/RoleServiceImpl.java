package com.sshs.system.role.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.util.BusiUtil;
import com.sshs.core.util.UuidUtil;
import com.sshs.system.role.mapper.RoleMapper;
import com.sshs.system.role.model.Role;
import com.sshs.system.role.service.IRoleService;
import org.apache.commons.lang.StringUtils;
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
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {
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
    public Message save1(Role role) {
        role.setRoleId(UuidUtil.get32UUID());
        if (null != mapper.getRoleByRoleCode(role.getRoleCode())) {
            throw new BusinessException("U1001");
        }
        if (StringUtils.isBlank(role.getRoleName())) {
            throw new BusinessException("R1000", "角色名称不能为空!");
        }
        List<Role> roleOldI = mapper.getRoleInfo(role.getRoleId(), null, null);
        if (BusiUtil.isNotEmpty(roleOldI)) {
            throw new BusinessException("R1001", "该角色ID已存在，请重新输入！");
        }
        List<Role> roleOldN = mapper.getRoleInfo(null, role.getRoleName(), null);
        if (BusiUtil.isNotEmpty(roleOldN)) {
            throw new BusinessException("R1002", "该角色名称已存在，请重新输入！");
        }
        try {
            return super.save1(role);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error("保存系统管理->系统管理-角色表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }
}
