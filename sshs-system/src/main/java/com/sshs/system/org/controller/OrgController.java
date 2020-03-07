package com.sshs.system.org.controller;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.message.Message;
import com.sshs.core.version.Version;
import com.sshs.system.org.model.Org;
import com.sshs.system.org.service.IOrgService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 系统管理->系统管理-机构表controller类
 *
 * @author 61910
 * @date 2018/11/07
 */
@Api(tags = "系统管理-机构管理")
@RestController
@Version(1)
@RequestMapping("/{version:v\\d+}/system/orgs")
public class OrgController extends BaseController {
    @Resource
    private IOrgService orgService;

    private static final Logger logger = LoggerFactory.getLogger(OrgController.class);

    /**
     * 保存系统管理->系统管理-机构表
     */
    @PostMapping
    public Message save(@RequestBody Org org) {
        logger.debug("开始保存系统管理->系统管理-机构表信息……");
        return orgService.save(org);
    }

    /**
     * 修改系统管理->系统管理-机构表
     */
    @PutMapping
    public Message update(@RequestBody Org org) {
        logger.debug("开始更新系统管理->系统管理-机构表信息……");
        return orgService.update(org);
    }

    /**
     * 删除系统管理->系统管理-机构表
     */
    @DeleteMapping("/{orgId}")
    public Message delete(@PathVariable("orgId") String orgId) {
        logger.debug("开始删除系统管理->系统管理-机构表信息……");
        return orgService.deleteById(orgId);
    }

    /**
     * 批量删除系统管理->系统管理-机构表
     */
    @DeleteMapping
    public Message delete(@RequestBody List<String> ids) {
        logger.debug("开始批量删除系统管理->系统管理-机构表信息……");
        return orgService.deleteByIds(ids);
    }

    /**
     * 查询系统管理->系统管理-机构表信息列表
     */
    @GetMapping
    public Message queryPageList(@RequestParam(value = "limit", required = false) int limit, @RequestParam(value = "offset", required = false) int offset, @RequestParam(required = false) Org params) {
        logger.debug("开始查询系统管理->系统管理-机构表列表信息……");
        return orgService.findForPageList(limit, offset, params);
    }


    /**
     * 根据主键查找系统管理->系统管理-机构表信息
     */
    @GetMapping("/{orgId}/{type}")
    public Message getById(@PathVariable("orgId") String orgId, @PathVariable("type") String type) {
        logger.debug("开始查询系统管理->系统管理-机构表信息……");
        Message org;
        if (StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("tree")) {
            org = orgService.getOrgTree(orgId);
        } else {
            org = orgService.getById(orgId);
        }
        return org;
    }
}
