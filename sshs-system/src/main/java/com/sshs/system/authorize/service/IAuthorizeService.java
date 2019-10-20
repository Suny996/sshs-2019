package com.sshs.system.authorize.service;

import com.sshs.core.base.service.IBaseService;
import com.sshs.core.message.Message;
import com.sshs.system.authorize.model.Authorize;
import com.sshs.system.menu.model.Menu;
import com.sshs.system.role.model.Role;

import java.util.List;
import java.util.Map;

/**
 * 系统管理->系统管理-角色权限表service类
 *
 * @author 61910
 * @date 2018/11/16
 */
public interface IAuthorizeService extends IBaseService<Authorize> {
    /**
     * 6191
     */
    public Message save(Authorize authorize);
    public Message queryForList(Map<String , Object> params);
}