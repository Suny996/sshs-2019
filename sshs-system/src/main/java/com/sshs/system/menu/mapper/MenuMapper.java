package com.sshs.system.menu.mapper;

import com.sshs.core.page.Page;
import com.sshs.system.menu.model.Menu;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * 系统管理->系统管理-菜单表dao接口类
 *
 * @author Suny
 * @date 2018/03/13
 */
public interface MenuMapper extends Mapper<Menu> {
    /**
     *根据menuName查询menu
     */
    public Menu getMenuByMenuName(String menuName);

    /**
     * 根据menuCode查询菜单记录，menuCode为null时返回跟节点（parentMenuCode为空的记录）
     *
     * @param menuCode
     * @return
     */
    public Menu findMenuById(String menuCode);

    /**
     * 根据menuCode查询最后一个子菜单
     *
     * @param menuCode
     * @return
     */
    public String findLastChildCodeById(String menuCode);

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<Menu> findMenuAll();

    public Page<Menu> findForPageList();

    /*
     * 功能描述:通过父级菜单Code查询子级最大序列
     * @param:
     * @return:
     * @auther: huangnan
     * @date: 2018/11/13 17:26
     */
    public BigDecimal getMaxMenuSeq(String parentCode);

    /*
     * 功能描述:通过菜单名称查询菜单信息
     * @param:
     * @return:
     * @auther: huangnan
     * @date: 2018/11/13 17:25
     */
    public List<Menu> getMenuByName(String menuName);
}