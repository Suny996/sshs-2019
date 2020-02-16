package com.sshs.system.authorize.controller;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.system.authorize.model.Authorize;
import com.sshs.system.authorize.service.IAuthorizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 系统管理->系统管理-角色权限表controller类
 *
 * @author 61910
 * @date 2018/11/16
 */
@RestController
@RequestMapping("/system/authorizes")
public class AuthorizeController extends BaseController {
    @Resource
    private IAuthorizeService authorizeService;

    private static final Logger logger = LoggerFactory.getLogger(AuthorizeController.class);

    /**
     * 保存系统管理->系统管理-角色权限表数据
     */
    @PostMapping
    public Message save(@RequestBody Authorize authorize) {
        try {
            logger.debug("开始保存系统管理->系统管理-角色权限表信息……");
            return authorizeService.save(authorize);
        } catch (Exception e) {
            logger.error("保存系统管理->系统管理-角色权限表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 修改系统管理->系统管理-角色权限表数据
     */
    @PutMapping
    public Message update(@RequestBody Authorize authorize) {
        try {
            logger.debug("开始更新系统管理->系统管理-角色权限表信息……");
            return authorizeService.update(authorize);
        } catch (Exception e) {
            logger.error("更新系统管理->系统管理-角色权限表信息异常！", e);
            throw new BusinessException("SY0002");
        }
    }

    /**
     * 根据主键删除系统管理->系统管理-角色权限表数据
     */
    @DeleteMapping("/{authorizeId}")
    public Message delete(@PathVariable("authorizeId") String authorizeId) {
        try {
            logger.debug("开始删除系统管理->系统管理-角色权限表信息……");
            return authorizeService.deleteById(authorizeId);
        } catch (Exception e) {
            logger.error("删除系统管理->系统管理-角色权限表信息异常！", e);
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 批量删除系统管理->系统管理-角色权限表数据
     */
    @DeleteMapping
    public Message delete(@RequestBody List<String> ids) {
        try {
            logger.debug("开始批量删除系统管理->系统管理-角色权限表信息……");
            return authorizeService.deleteByIds(ids);
        } catch (Exception e) {
            logger.error("批量删除系统管理->系统管理-角色权限表信息异常！", e);
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 根据主键查找系统管理->系统管理-角色权限表信息
     */
    @GetMapping("/{authorizeId}")
    public Message getById(@PathVariable("authorizeId") String authorizeId) {
        try {
            logger.debug("开始查询系统管理->系统管理-角色权限表信息……");
            Message message = Message.success(authorizeService.getById(authorizeId));
            return message;
        } catch (Exception e) {
            logger.error("查询系统管理->系统管理-角色权限表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 角色分配菜单查询功能
     * 6191
     *
     * @param params
     * @return Message
     */
    @GetMapping
    public Message queryList(@RequestParam Map<String, Object> params) {
        try {
            logger.debug("开始查询系统管理->系统管理-角色权限表列表信息……");
            Message message = authorizeService.queryAuthorizeList(params);
            return message;
        } catch (Exception e) {
            logger.error("查询系统管理->系统管理-角色权限表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }
}
