package com.sshs.system.authorize.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.message.Message;
import com.sshs.core.wrapper.QueryWrapper;
import com.sshs.system.authorize.mapper.AuthorizeMapper;
import com.sshs.system.authorize.model.Authorize;
import com.sshs.system.authorize.service.IAuthorizeService;
import com.sshs.system.menu.model.Menu;
import com.sshs.system.menu.service.IMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统管理->系统管理-角色权限表service实现类
 *
 * @author 61910
 * @date 2018/11/16
 */
@Service("authorizeService")
public class AuthorizeServiceImpl extends BaseServiceImpl<AuthorizeMapper, Authorize> implements IAuthorizeService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizeServiceImpl.class);
    @Resource
    private AuthorizeMapper mapper;

    @Resource
    IMenuService menuService;

    /**
     * 保存系统管理->系统管理-角色权限表数据方法
     *
     * @param authorize
     * @return Message
     */
    @Override
    public Message<Authorize> save(Authorize authorize) {
        QueryWrapper<Authorize> example = new QueryWrapper<>();
        example.eq("roleCode", authorize.getRoleCode());
        mapper.delete(example);
        for (Menu menu : authorize.getMenus()) {
            Authorize auth = new Authorize();
            authorize.setRoleCode(authorize.getRoleCode());
            authorize.setResourceId(menu.getMenuCode());
            authorize.setResourceName(menu.getMenuName());
            //资源类型：01-菜单
            authorize.setResourceType("01");
            //授权类型：03-全部（操作和授权）
            authorize.setAuthorizeType("03");
            authorize.setDataAuthType(menu.getDataAuthType());
            super.save(authorize);
        }
        return Message.success();
    }

    /**
     * 角色分配菜单查询功能
     * 6191
     *
     * @param roleCode
     * @return Message
     */
    @Override
    public Message<Map> queryAuthorizeList(String roleCode) {
        Map<String, Object> data = new HashMap<String, Object>();
        Message menus = menuService.getMenuTree("0");
        Menu menu = (Menu) menus.getData();
        QueryWrapper<Authorize> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roleCode", roleCode);
        List<Authorize> authorizes = mapper.selectList(queryWrapper);
        initAuthData(menu, authorizes);
        data.put("menus", menu);
        data.put("authorizes", authorizes);
        return Message.success(data);
    }


    /**
     * 设置菜单数据权限
     *
     * @param menu
     * @param auths
     * @return
     */
    private Menu initAuthData(Menu menu, List<Authorize> auths) {
        for (Authorize auth : auths) {
            if (menu.getMenuCode().equals(auth.getResourceId())) {
                menu.setDataAuthType(auth.getDataAuthType());
                continue;
            }
        }
        if (menu.getChildren() != null && !menu.getChildren().isEmpty()) {
            for (Menu sub : menu.getChildren()) {
                initAuthData(sub, auths);
            }
        }
        return menu;
    }

}
