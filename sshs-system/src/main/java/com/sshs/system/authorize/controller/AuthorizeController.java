package com.sshs.system.authorize.controller;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.message.Message;
import com.sshs.core.version.Version;
import com.sshs.system.authorize.model.Authorize;
import com.sshs.system.authorize.service.IAuthorizeService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 系统管理->系统管理-角色权限表controller类
 *
 * @author 61910
 * @date 2018/11/16
 */
@Api(tags = "系统管理-权限管理")
@RestController
@Version(1)
@RequestMapping("/{version:v\\d+}/system/authorizes")
public class AuthorizeController extends BaseController {
    @Resource
    private IAuthorizeService authorizeService;

    private static final Logger logger = LoggerFactory.getLogger(AuthorizeController.class);

    /**
     * 保存系统管理->系统管理-角色权限表数据
     */
    @PostMapping
    public Message<Authorize> save(@RequestBody Authorize authorize) {
        logger.debug("开始保存系统管理->系统管理-角色权限表信息……");
        return authorizeService.save(authorize);
    }

    /**
     * 修改系统管理->系统管理-角色权限表数据
     */
    @PutMapping
    public Message<Authorize> update(@RequestBody Authorize authorize) {
        logger.debug("开始更新系统管理->系统管理-角色权限表信息……");
        return authorizeService.update(authorize);
    }

    /**
     * 根据主键删除系统管理->系统管理-角色权限表数据
     */
    @DeleteMapping("/{authorizeId}")
    public Message<Integer> delete(@PathVariable("authorizeId") String authorizeId) {
        logger.debug("开始删除系统管理->系统管理-角色权限表信息……");
        return authorizeService.deleteById(authorizeId);
    }

    /**
     * 批量删除系统管理->系统管理-角色权限表数据
     */
    @DeleteMapping
    public Message<Integer> delete(@RequestBody List<String> ids) {
        logger.debug("开始批量删除系统管理->系统管理-角色权限表信息……");
        return authorizeService.deleteByIds(ids);
    }

    /**
     * 根据主键查找系统管理->系统管理-角色权限表信息
     */
    @GetMapping("/{authorizeId}")
    public Message<Authorize> getById(@PathVariable("authorizeId") String authorizeId) {
        logger.debug("开始查询系统管理->系统管理-角色权限表信息……");
        return authorizeService.getById(authorizeId);
    }

    /**
     * 角色分配菜单查询功能
     * 6191
     *
     * @param params
     * @return Message
     */
    @GetMapping
    public Message<List<Authorize>> queryList(Authorize params) {
        logger.debug("开始查询系统管理->系统管理-角色权限表列表信息……");
        return authorizeService.findForList(params);
    }
}
