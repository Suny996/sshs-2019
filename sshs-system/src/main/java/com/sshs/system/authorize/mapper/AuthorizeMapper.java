package com.sshs.system.authorize.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sshs.core.page.Page;
import com.sshs.system.authorize.model.Authorize;


/**
 * 系统管理->系统管理-角色权限表类
 *
 * @author 61910
 * @date 2018/11/16
 */
public interface AuthorizeMapper extends BaseMapper<Authorize> {
    Page<Authorize> findForPageList();

    void deleteByRoleCode(String roleCode);
}