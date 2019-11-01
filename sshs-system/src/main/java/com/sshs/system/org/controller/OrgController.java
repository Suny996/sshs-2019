package com.sshs.system.org.controller;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.system.org.model.Org;
import com.sshs.system.org.service.IOrgService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 系统管理->系统管理-机构表controller类
 *
 * @author 61910
 * @date 2018/11/07
 */
@RestController
@RequestMapping("/system/orgs")
public class OrgController extends BaseController {
    @Resource
    private IOrgService orgService;

    Logger logger = LoggerFactory.getLogger(OrgController.class);

    /**
     * 保存系统管理->系统管理-机构表
     */
    @PostMapping
    public Mono<Message> save(@RequestBody Org org) {
        try {
            logger.debug("开始保存系统管理->系统管理-机构表信息……");
            return Mono.justOrEmpty(orgService.save(org));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存系统管理->系统管理-机构表信息异常！");
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 修改系统管理->系统管理-机构表
     */
    @PutMapping
    public Mono<Message> update(@RequestBody Org org) {
        try {
            logger.debug("开始更新系统管理->系统管理-机构表信息……");
            return Mono.justOrEmpty(orgService.update(org));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新系统管理->系统管理-机构表信息异常！");
            throw new BusinessException("SY0002");
        }
    }

    /**
     * 删除系统管理->系统管理-机构表
     */
    @DeleteMapping("/{orgId}")
    public Mono<Message> delete(@PathVariable("orgId") String orgId) {
        try {
            logger.debug("开始删除系统管理->系统管理-机构表信息……");
            return Mono.justOrEmpty(orgService.deleteById(orgId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除系统管理->系统管理-机构表信息异常！");
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 批量删除系统管理->系统管理-机构表
     */
    @DeleteMapping
    public Mono<Message> delete(@RequestBody List<String> ids) {
        try {
            logger.debug("开始批量删除系统管理->系统管理-机构表信息……");
            return Mono.justOrEmpty(orgService.deleteByIds(ids));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("批量删除系统管理->系统管理-机构表信息异常！");
            throw new BusinessException("SY0003");
        }
    }

//    /**
//     * 根据主键查找系统管理->系统管理-机构表信息
//     */
//    @GetMapping("/{orgId}")
//    public Mono<Message> getById(@PathVariable("orgId") String orgId) {
//        try {
//            logger.debug("开始查询系统管理->系统管理-机构表信息……");
//            Message message = Message.success(orgService.getById(orgId));
//            return Mono.justOrEmpty(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("查询系统管理->系统管理-机构表信息异常！");
//            throw new BusinessException("SY0001");
//        }
//    }

    /**
     * 查询系统管理->系统管理-机构表信息列表
     */
    @GetMapping
    public Mono<Message> queryList(@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset, @RequestParam Map<String, Object> params) {
        try {
            logger.debug("开始查询系统管理->系统管理-机构表列表信息……");
            Message message = orgService.queryPageList(limit, offset, params);
            return Mono.justOrEmpty(message);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询系统管理->系统管理-机构表信息异常！");
            throw new BusinessException("SY0000");
        }
    }


    /**
     * 根据主键查找系统管理->系统管理-机构表信息
     */
    @GetMapping("/{orgId}/{type}")
    public Mono<Message> getById(@PathVariable("orgId") String orgId, @PathVariable("type") String type) {
        try {
            logger.debug("开始查询系统管理->系统管理-机构表信息……");
            Message org = null;
            if (StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("tree")) {
                org = orgService.getOrgTree(orgId);
            } else {
                org = Message.success(orgService.getById(orgId));
            }
            return Mono.justOrEmpty(org);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询系统管理->系统管理-机构表信息异常！");
            throw new BusinessException("SY0001");
        }
    }
}
