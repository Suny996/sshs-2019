package com.sshs.system.menu.controller;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
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
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 系统管理->系统管理-菜单表controller类
 *
 * @author 61910
 * @date 2018/11/12
 */
@Api(tags = "系统管理-菜单管理接口")
@RestController
@RequestMapping("/api/v1/system/menus")
public class MenuController extends BaseController {
    @Resource
    private IMenuService menuService;

    Logger logger = LoggerFactory.getLogger(MenuController.class);

    /**
     * 保存系统管理->系统管理-菜单表数据
     */
    @PostMapping
    public Mono<Message> save(@RequestBody Menu menu) {
        try {
//            if(null!=menuService.getMenuByMenuName(menu.getMenuName())){
//               throw new BusinessException("U1002");
//            }
            logger.debug("开始保存系统管理->系统管理-菜单表信息……");
            //校验菜单信息
            menuService.verifyMenuForm(menu);
            return Mono.justOrEmpty(menuService.save(menu));
        } catch (BusinessException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            logger.error("保存系统管理->系统管理-菜单表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 修改系统管理->系统管理-菜单表数据
     */
    @PutMapping
    public Mono<Message> update(@RequestBody Menu menu) {
        try {
            logger.debug("开始更新系统管理->系统管理-菜单表信息……");
            //校验菜单信息
            menuService.verifyMenuForm(menu);
            return Mono.justOrEmpty(menuService.update(menu));
        } catch (Exception e) {
            logger.error("更新系统管理->系统管理-菜单表信息异常！", e);
            throw new BusinessException("SY0002");
        }
    }

    /**
     * 根据主键删除系统管理->系统管理-菜单表数据
     */
    @DeleteMapping("/{menuCode}")
    public Mono<Message> delete(@PathVariable("menuCode") String menuCode) {
        try {
            logger.debug("开始删除系统管理->系统管理-菜单表信息……");
            return Mono.justOrEmpty(menuService.deleteById(menuCode));
        } catch (Exception e) {
            logger.error("删除系统管理->系统管理-菜单表信息异常！", e);
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 批量删除系统管理->系统管理-菜单表数据
     */
    @DeleteMapping
    public Mono<Message> delete(@RequestBody List<String> ids) {
        try {
            logger.debug("开始批量删除系统管理->系统管理-菜单表信息……");
            return Mono.justOrEmpty(menuService.deleteByIds(ids));
        } catch (Exception e) {
            logger.error("批量删除系统管理->系统管理-菜单表信息异常！", e);
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 根据主键查找系统管理->系统管理-菜单表信息
     */
    @GetMapping("/{menuId}/{type}")
    public Mono<Message> getById(@PathVariable("menuId") String menuId, @PathVariable("type") String type) {
        try {
            logger.debug("开始查询系统管理->系统管理-菜单表信息……");
            Message menu = null;
            if (StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("tree")) {
                menu = menuService.getMenuTree(menuId);
            } else {
                menu = Message.success(menuService.getById(menuId));
            }
            return Mono.justOrEmpty(menu);
        } catch (Exception e) {
            logger.error("查询系统管理->系统管理-菜单表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 查询系统管理->系统管理-菜单表信息列表
     */
    @ApiOperation(value = "菜单查询（可分页）")
    @ApiImplicitParams
            ({@ApiImplicitParam(name = "limit", value = "分页大小", dataType = "String", paramType = "path"),
                    @ApiImplicitParam(name = "offset", value = "页码", dataType = "String", paramType = "path"),
                    @ApiImplicitParam(name = "param", value = "条件", dataType = "Map", paramType = "path")})
    @GetMapping
    public Mono<Message> queryList(@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset, @RequestParam(required = false) Map<String, Object> params) {
        try {
            logger.debug("开始查询系统管理->系统管理-菜单表列表信息……");
            Message message = menuService.queryPageList(limit, offset, params);
            return Mono.justOrEmpty(message);
        } catch (Exception e) {
            logger.error("查询系统管理->系统管理-菜单表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }
}
