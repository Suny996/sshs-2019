package com.sshs.system.authorize.service;

import com.sshs.core.base.service.IBaseService;
import com.sshs.core.message.Message;
import com.sshs.system.authorize.model.Authorize;

import java.util.Map;

/**
 * 系统管理->系统管理-角色权限表service类
 *
 * @author 61910
 * @date 2018/11/16
 */
public interface IAuthorizeService extends IBaseService<Authorize> {
    /**
     * 角色分配菜单查询功能
     * 6191
     *
     * @param params
     * @return Message
     */
    Message<Map> queryAuthorizeList(Map<String, Object> params);
}