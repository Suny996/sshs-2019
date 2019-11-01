package com.sshs.system.menu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.constant.MenuType;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.util.BusiUtil;
import com.sshs.system.menu.mapper.MenuMapper;
import com.sshs.system.menu.model.Menu;
import com.sshs.system.menu.service.IMenuService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 系统管理->系统管理-菜单表service实现类
 *
 * @author Suny
 * @date 2018/03/13
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Resource
    private MenuMapper mapper;

    /**
     * 根据menuName查询menu
     */
    @Override
    public Menu getMenuByMenuName(String menuName) {
        return mapper.getMenuByMenuName(menuName);
    }

    /**
     * 保存菜单功能自动生成menuCode
     *
     * @param menu
     * @return
     * @throws Exception
     */
    @Override
    public Message<Menu> save1(Menu menu) {
        String ccode = mapper.findLastChildCodeById(menu.getParentCode());
        String parentMenuCode = menu.getParentCode();
        if (parentMenuCode == null || parentMenuCode.equals("0") || parentMenuCode.equals("00")) {
            parentMenuCode = "";
        }
        if (StringUtils.isNotEmpty(ccode) && ccode.length() >= 2) {
            menu.setMenuCode(parentMenuCode + StringUtils.leftPad((Integer.valueOf(ccode.substring(ccode.length() - 2)) + 1) + "", 2, "0"));
        } else {
            menu.setMenuCode(parentMenuCode + StringUtils.leftPad((Integer.valueOf(ccode == null ? "0" : ccode) + 1) + "", 2, "0"));
        }
        return saveMenu(menu);
    }

    /**
     * 保存系统管理->系统管理-菜单表数据方法
     *
     * @param menu
     * @return Message
     */
    public Message saveMenu(Menu menu) {
        //List<Menu> menuOld = mapper.getMenuByName(menu.getMenuName());
//        if (BusiUtil.isNotEmpty(menuOld)){
//            throw new BusinessException("M2000","该菜单名称已定义，请重新定义!");
//        }
//        menu.setMenuCode(UuidUtil.get32UUID());
        //设置同级菜单序列--查询父级菜单下最大序号然后自增1
        BigDecimal menuSeq = getMaxMenuSeq(menu.getParentCode());
        menu.setMenuSeq(BusiUtil.nvl(menuSeq, BigDecimal.ZERO).add(new BigDecimal(1)));
        try {
            return super.save1(menu);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存系统管理->系统管理-菜单表信息异常！");
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 查询指定menuCode及向下菜单树
     *
     * @param rootId
     * @return
     */
    @Override
    public Message<Menu> getMenuTree(String rootId) {
        Menu menu = mapper.findMenuById(rootId);
        if (menu != null) {
            QueryWrapper<Menu> wrapper = new QueryWrapper<>();
            wrapper.orderByAsc("menuCode");
            List<Menu> menus = super.list(wrapper);
            menu = initChildren(menu, menus);
        }
        return Message.success(menu);
    }

    /**
     * 初始化子节点
     *
     * @param menu
     * @param menus
     * @return
     */
    private Menu initChildren(Menu menu, List<Menu> menus) {
        for (Menu m : menus) {
            if (menu.getMenuCode().equals(m.getParentCode())) {
                m = initChildren(m, menus);
                menu.addChild(m);
            }
        }
        return menu;
    }

    /*
     * 功能描述:验证菜单新增是否正确
     * @param: [menu]
     * @return: void
     * @auther: huangnan
     * @date: 2018/11/13 11:58
     */
    @Override
    public void verifyMenuForm(Menu menu) {
        if (StringUtils.isBlank(menu.getMenuName())) {
            throw new BusinessException("M1000", "菜单名称不能为空!");
        }
        if (StringUtils.isBlank(menu.getParentCode())) {
            throw new BusinessException("M1001", "上级菜单不能为空!");
        }
        if (StringUtils.isBlank(menu.getMenuType())) {
            throw new BusinessException("M1004", "菜单类型不能为空!");
        }

        // 菜单
        if (menu.getMenuType() == MenuType.FEATURES) {
            if (StringUtils.isBlank(menu.getMenuUrl())) {
                throw new BusinessException("M1002", "菜单URL不能为空!");
            }
        }
        // 上级菜单类型
        String parentType = MenuType.NODE;
     /*   if (BusiUtil.notEquals(menu.getParentCode(),"0")) {
            Menu parentMenu = findMenuById(menu.getParentCode());
            parentType = parentMenu.getMenuType();
        }*/

        // 目录、菜单
        if (menu.getMenuType() == MenuType.FEATURES) {
            if (parentType != MenuType.NODE) {
                throw new BusinessException("M1003", "上级菜单只能为节点类型!");
            }
            return;
        }
    }

    /**
     * 功能描述:根据菜单code查询菜单信息
     *
     * @param:
     * @return:
     * @auther: huangnan
     * @date: 2018/11/13 15:24
     */
    @Override
    public Menu findMenuById(String menuCode) {
        return super.getEntityById(menuCode);
    }

    /**
     * 功能描述:获取同级目录下菜单最大序列
     *
     * @param parentCode
     * @return:
     * @auther: huangnan
     * @date: 2018/11/13 15:48
     */
    @Override
    public BigDecimal getMaxMenuSeq(String parentCode) {
        return mapper.getMaxMenuSeq(parentCode);
    }

}

