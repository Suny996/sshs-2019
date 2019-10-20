package com.sshs.system.userRole.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.core.util.UuidUtil;
import com.sshs.system.role.model.Role;
import com.sshs.system.role.service.IRoleService;
import com.sshs.system.user.model.User;
import com.sshs.system.userRole.mapper.UserRoleMapper;
import com.sshs.system.userRole.model.UserRole;
import com.sshs.system.userRole.service.IUserRoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hsqldb.lib.StringUtil;
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
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements IUserRoleService {
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
        try {
            this.sqlSessionTemplate.delete("com.sshs.system.userRole.mapper.UserRoleMapper.deleteByUserCode", user.getUserCode());
            List<String> roleCodes = user.getRoleCodes();
            for (String roleCode : roleCodes) {
                UserRole userRole = new UserRole();
                userRole.setRoleCode(roleCode);
                userRole.setUserRoleId(UuidUtil.get32UUID());
                userRole.setUserCode(user.getUserCode());
                super.save(userRole);
            }
            return Message.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存系统管理->系统管理-用户角色对照表信息异常！");
            throw new BusinessException("SY0001");
        }
    }


    /**
     * 批量保存系统管理->系统管理-用户角色对照表数据方法
     *
     * @param userRoles
     * @return Message
     */
    @Override
    public Message save(List<UserRole> userRoles) {
        try {
            for (UserRole userRole : userRoles) {
                userRole.setUserRoleId(UuidUtil.get32UUID());
            }
            return super.save(userRoles);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("批量保存系统管理->系统管理-用户角色对照表信息异常！");
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 查询系统管理->系统管理-用户角色对照表列表信息
     *
     * @param limit
     */
    @Override
    public Message queryPageList(String limit, String offset, Map<String, Object> parameter) {
        if (StringUtil.isEmpty(limit)) {
            return Message.success(queryList(parameter));
        } else {
            Page<UserRole> page = new Page<UserRole>(Integer.valueOf(limit, 10), Integer.valueOf(offset, 10), parameter);
            return queryPageList(page);
        }
    }

    /**
     * 查询系统管理->系统管理-用户角色对照表列表信息
     *
     * @param parameter
     */
    @Override
    public List<UserRole> queryList(Map<String, Object> parameter) {
        return super.findForList("com.sshs.system.userRole.mapper.UserRoleMapper.findForList", parameter);
    }

    /**
     * 分页查询系统管理->系统管理-用户角色对照表信息
     */
    @Override
    public Message queryPageList(Page<UserRole> page) {
        return findForPageList("com.sshs.system.userRole.mapper.UserRoleMapper.findForPageList", page);
    }

    /**
     * 用户分配角色查询功能
     *
     * @param params
     * @return
     */
    @Override
    public Message queryForList(Map<String, Object> params) {
        Map<String, Object> data = new HashMap<String, Object>();
        List<Role> roles = roleService.queryList(data);
        List<UserRole> userRoles = queryList(params);
        data.put("roles", roles);
        data.put("userRoles", userRoles);
        return Message.success(data);
    }
}
