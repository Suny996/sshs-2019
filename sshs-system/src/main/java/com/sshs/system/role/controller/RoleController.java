package com.sshs.system.role.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.base.controller.BaseController;
import com.sshs.core.message.Message;
import com.sshs.core.version.Version;
import com.sshs.system.role.model.Role;
import com.sshs.system.role.service.IRoleService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 系统管理->系统管理-角色表controller类
 *
 * @author 61910
 * @date 2018/11/07
 */
@Api(tags = "系统管理-角色管理")
@RestController
@Version(1)
@RequestMapping("/{version:v\\d+}/system/roles")
public class RoleController extends BaseController {
    @Resource
    private IRoleService roleService;

    Logger logger = LoggerFactory.getLogger(RoleController.class);

    /**
     * 保存系统管理->系统管理-角色表
     */
    @PostMapping
    public Message save(@RequestBody Role role) {
        logger.debug("开始保存系统管理->系统管理-角色表信息……");
        return roleService.save(role);
    }

    /**
     * 修改系统管理->系统管理-角色表
     */
    @PutMapping
    public Message update(@RequestBody Role role) {
        logger.debug("开始更新系统管理->系统管理-角色表信息……");
        return roleService.update(role);
    }

    /**
     * 删除系统管理->系统管理-角色表
     */
    @DeleteMapping("/{roleId}")
    public Message delete(@PathVariable("roleId") String roleId) {
        logger.debug("开始删除系统管理->系统管理-角色表信息……");
        return roleService.deleteById(roleId);
    }

    /**
     * 批量删除系统管理->系统管理-角色表
     */
    @DeleteMapping
    public Message delete(@RequestBody List<String> ids) {
        logger.debug("开始批量删除系统管理->系统管理-角色表信息……");
        return roleService.deleteByIds(ids);
    }

    /**
     * 根据主键查找系统管理->系统管理-角色表信息
     */
    @GetMapping("/{roleId}")
    public Message getById(@PathVariable("roleId") String roleId) {
        logger.debug("开始查询系统管理->系统管理-角色表信息……");
        Message message = Message.success(roleService.getById(roleId));
        return message;
    }


    /**
     * 查询系统管理->系统管理-角色表信息列表
     */
    @GetMapping
    public Message<IPage<Role>> queryPageList(@RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "offset", required = false) Integer offset, @ModelAttribute Role
            params) {
        logger.debug("开始查询系统管理->系统管理-角色表列表信息……");
        Message message = roleService.findForPageList(limit, offset, params);
        return message;
    }

}
