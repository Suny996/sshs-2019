package com.sshs.system.menu.service;

import com.sshs.core.message.Message;
import com.sshs.system.menu.model.Menu;
import com.sshs.core.base.service.IBaseService;

import java.math.BigDecimal;

/**
 * 系统管理->系统管理-菜单表service接口
 *
 * @author Suny
 * @date 2018/03/13
 */
public interface IMenuService extends IBaseService<Menu> {
    /**
     * 查询树形结构菜单对象
     * @param rootId
     * @return
     */
    Message getMenuTree(String rootId);

    void verifyMenuForm(Menu menu);

    Menu findMenuById(String menuCode);

    BigDecimal getMaxMenuSeq(String parentCode);

    public Menu getMenuByMenuName(String menuName);
}

