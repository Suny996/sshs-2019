package com.sshs.system.menu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.base.controller.BaseController;
import com.sshs.core.message.Message;
import com.sshs.system.menu.model.Menu;
import com.sshs.system.menu.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 系统管理->系统管理-菜单表controller类
 *
 * @author 61910
 * @date 2018/11/12
 */
@Api(tags = "系统管理-菜单管理接口")
@RestController
@RequestMapping("/v1/system/menus")
public class MenuController extends BaseController {
    @Resource
    private IMenuService menuService;

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    /**
     * 保存系统管理->系统管理-菜单表数据
     */
    @PostMapping
    public Message<Menu> save(@RequestBody Menu menu) {
//            if(null!=menuService.getMenuByMenuName(menu.getMenuName())){
//               throw new BusinessException("U1002");
//            }
        logger.debug("开始保存系统管理->系统管理-菜单表信息……");
        //校验菜单信息
        menuService.verifyMenuForm(menu);
        return menuService.save(menu);
    }

    /**
     * 修改系统管理->系统管理-菜单表数据
     */
    @PutMapping
    public Message<Menu> update(@RequestBody Menu menu) {
        logger.debug("开始更新系统管理->系统管理-菜单表信息……");
        //校验菜单信息
        menuService.verifyMenuForm(menu);
        return menuService.update(menu);
    }

    /**
     * 根据主键删除系统管理->系统管理-菜单表数据
     */
    @DeleteMapping("/{menuCode}")
    public Message delete(@PathVariable("menuCode") String menuCode) {
        logger.debug("开始删除系统管理->系统管理-菜单表信息……");
        return menuService.deleteById(menuCode);
    }

    /**
     * 批量删除系统管理->系统管理-菜单表数据
     */
    @DeleteMapping
    public Message delete(@RequestBody List<String> ids) {
        logger.debug("开始批量删除系统管理->系统管理-菜单表信息……");
        return menuService.deleteByIds(ids);
    }

    /**
     * 根据主键查找系统管理->系统管理-菜单表信息
     */
    @GetMapping("/{menuId}/{type}")
    public Message<Menu> getById(@PathVariable("menuId") String menuId, @PathVariable("type") String type) {
        logger.debug("开始查询系统管理->系统管理-菜单表信息……");
        Message menu;
        if (StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("tree")) {
            menu = menuService.getMenuTree(menuId);
        } else {
            menu = menuService.getById(menuId);
        }
        return menu;
    }

    /**
     * 查询系统管理->系统管理-菜单表信息列表
     */
    @ApiOperation(value = "菜单查询（可分页）")
    @ApiImplicitParams
            ({@ApiImplicitParam(name = "limit", value = "分页大小", dataType = "String", paramType = "path"),
                    @ApiImplicitParam(name = "offset", value = "页码", dataType = "String", paramType = "path"),
                    @ApiImplicitParam(name = "param", value = "条件", dataType = "Map", paramType = "query")})
    @GetMapping
    public Message<IPage<Menu>> queryPageList(@RequestParam(value = "limit", required = false) int limit, @RequestParam(value = "offset", required = false) int offset, @ModelAttribute Menu params) {
        logger.debug("开始查询系统管理->系统管理-菜单表列表信息……");
        return menuService.findForPageList(limit, offset, params);
    }
}
