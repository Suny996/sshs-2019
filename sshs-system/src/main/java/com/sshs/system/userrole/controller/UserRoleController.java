package com.sshs.system.userrole.controller;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.message.Message;
import com.sshs.system.userrole.model.UserRole;
import com.sshs.system.userrole.service.IUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 系统管理->系统管理-用户角色对照表controller类
 *
 * @author 61910
 * @date 2018/11/16
 */
@RestController
@RequestMapping("/system/userRoles")
public class UserRoleController extends BaseController {
    @Resource
    private IUserRoleService userRoleService;

    private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    /**
     * 保存系统管理->系统管理-用户角色对照表数据
     */
    @PostMapping
    public Message<UserRole> save(@RequestBody UserRole user) {
        logger.debug("开始保存系统管理->系统管理-用户角色对照表信息……");
        return userRoleService.save(user);
    }

    /**
     * 修改系统管理->系统管理-用户角色对照表数据
     */
    @PutMapping
    public Message<UserRole> update(@RequestBody UserRole userRole) {
        logger.debug("开始更新系统管理->系统管理-用户角色对照表信息……");
        return userRoleService.update(userRole);
    }

    /**
     * 根据主键删除系统管理->系统管理-用户角色对照表数据
     */
    @DeleteMapping("/{userRoleId}")
    public Message<Integer> delete(@PathVariable("userRoleId") String userRoleId) {
        logger.debug("开始删除系统管理->系统管理-用户角色对照表信息……");
        return userRoleService.deleteById(userRoleId);
    }

    /**
     * 批量删除系统管理->系统管理-用户角色对照表数据
     */
    @DeleteMapping
    public Message<Integer> delete(@RequestBody List<String> ids) {
        logger.debug("开始批量删除系统管理->系统管理-用户角色对照表信息……");
        return userRoleService.deleteByIds(ids);
    }

    /**
     * 根据主键查找系统管理->系统管理-用户角色对照表信息
     */
    @GetMapping("/{userRoleId}")
    public Message<UserRole> getById(@PathVariable("userRoleId") String userRoleId) {
        logger.debug("开始查询系统管理->系统管理-用户角色对照表信息……");
        return userRoleService.getById(userRoleId);
    }

    /**
     * 查询系统管理->系统管理-用户角色对照表信息列表
     */
    @GetMapping
    public Message<Map> queryList(@RequestParam UserRole params) {
        logger.debug("开始查询系统管理->系统管理-用户角色对照表列表信息……");
        return userRoleService.queryForList(params);
    }
}
