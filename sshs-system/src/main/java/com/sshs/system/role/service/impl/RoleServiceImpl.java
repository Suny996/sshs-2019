package com.sshs.system.role.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.core.util.BusiUtil;
import com.sshs.core.util.UuidUtil;
import com.sshs.system.role.mapper.RoleMapper;
import com.sshs.system.role.model.Role;
import com.sshs.system.role.service.IRoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hsqldb.lib.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


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
    public Message save(Role role) {
        role.setRoleId(UuidUtil.get32UUID());
        if(null!=mapper.getRoleByRoleCode(role.getRoleCode())){
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
            return super.save(role);
        }
        catch (BusinessException e) {
            e.printStackTrace();
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("保存系统管理->系统管理-角色表信息异常！");
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 查询系统管理->系统管理-角色表列表信息
     *
     * @param limit
     */
    @Override
    public Message queryPageList(String limit, String offset, Map<String, Object> parameter) {
        if (StringUtil.isEmpty(limit)) {
            return Message.success(findForList("com.sshs.system.role.mapper.RoleMapper.findForList", parameter));
        } else {
            Page<Role> page = new Page<Role>(Integer.valueOf(limit, 10), Integer.valueOf(offset, 10), parameter);
            return queryPageList(page);
        }
    }

    /**
     * 分页查询系统管理->系统管理-角色表信息
     */
    @Override
    public Message queryPageList(Page<Role> page) {
        return findForPageList("com.sshs.system.role.mapper.RoleMapper.findForPageList", page);
    }

    /**
     * 分页查询系统管理->系统管理-角色表信息
     */
    @Override
    public List<Role> queryList(Map<String, Object> parameter) {
        return findForList("com.sshs.system.role.mapper.RoleMapper.findForList", parameter);
    }
}
