package com.sshs.system.role.controller;

import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.system.role.model.Role;
import com.sshs.system.role.service.IRoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;
import com.sshs.core.base.controller.BaseController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 系统管理->系统管理-角色表controller类
 *
 * @author 61910
 * @date 2018/11/07
 */
@RestController
@RequestMapping("/system/roles")
public class RoleController extends BaseController {
    @Resource
    private IRoleService roleService;

    Logger logger = LoggerFactory.getLogger(RoleController.class);

    /**
     * 保存系统管理->系统管理-角色表
     */
    @PostMapping
    public Mono<Message> save(@RequestBody Role role) {
        try {
            logger.debug("开始保存系统管理->系统管理-角色表信息……");
            return Mono.justOrEmpty(roleService.save(role));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存系统管理->系统管理-角色表信息异常！");
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 修改系统管理->系统管理-角色表
     */
    @PutMapping
    public Mono<Message> update(@RequestBody Role role) {
        try {
            logger.debug("开始更新系统管理->系统管理-角色表信息……");
            return Mono.justOrEmpty(roleService.update(role));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新系统管理->系统管理-角色表信息异常！");
            throw new BusinessException("SY0002");
        }
    }

    /**
     * 删除系统管理->系统管理-角色表
     */
    @DeleteMapping("/{roleId}")
    public Mono<Message> delete(@PathVariable("roleId") String roleId) {
        try {
            logger.debug("开始删除系统管理->系统管理-角色表信息……");
            return Mono.justOrEmpty(roleService.deleteById(roleId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除系统管理->系统管理-角色表信息异常！");
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 批量删除系统管理->系统管理-角色表
     */
    @DeleteMapping
    public Mono<Message> delete(@RequestBody List<String> ids) {
        try {
            logger.debug("开始批量删除系统管理->系统管理-角色表信息……");
            return Mono.justOrEmpty(roleService.deleteByIds(ids));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("批量删除系统管理->系统管理-角色表信息异常！");
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 根据主键查找系统管理->系统管理-角色表信息
     */
    @GetMapping("/{roleId}")
    public Mono<Message> getById(@PathVariable("roleId") String roleId) {
        try {
            logger.debug("开始查询系统管理->系统管理-角色表信息……");
            Message message = Message.success(roleService.getById(roleId));
            return Mono.justOrEmpty(message);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询系统管理->系统管理-角色表信息异常！");
            throw new BusinessException("SY0001");
        }
    }


    /**
     * 查询系统管理->系统管理-角色表信息列表
     */
    @GetMapping
    public Mono<Message> queryList(@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset, @RequestParam Map
            <String, Object> params) {
        try {
            logger.debug("开始查询系统管理->系统管理-角色表列表信息……");
            Message message = roleService.queryPageList(limit, offset, params);
            return Mono.justOrEmpty(message);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询系统管理->系统管理-角色表信息异常！");
            throw new BusinessException("SY0001");
        }
    }


    /**
     * 分页查询系统管理->系统管理-角色表信息
     */
    @PostMapping("/pageList")
    public Mono<Message> queryPageList(@RequestBody Page<Role> page) {
        try {
            logger.debug("开始分页查询系统管理->系统管理-角色表信息……");
            Message message = roleService.queryPageList(page);
            return Mono.justOrEmpty(message);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("分页查询系统管理->系统管理-角色表信息异常！");
            throw new BusinessException("SY0005");
        }
    }
}
