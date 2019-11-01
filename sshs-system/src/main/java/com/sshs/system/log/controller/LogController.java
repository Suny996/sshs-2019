package com.sshs.system.log.controller;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.system.log.model.Log;
import com.sshs.system.log.service.ILogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 系统管理->系统管理-系统操作日志表controller类
 *
 * @author allen
 * @date 2019/02/21
 */
@RestController
@RequestMapping("/system/logs")
public class LogController extends BaseController {
    @Resource
    private ILogService logService;

    Logger logger = LoggerFactory.getLogger(LogController.class);

    /**
     * 保存系统管理->系统管理-系统操作日志表数据
     */
    @PostMapping
    public Mono<Message> save(@RequestBody Log log) {
        try {
            logger.debug("开始保存系统管理->系统管理-系统操作日志表信息……");
            return Mono.justOrEmpty(logService.save(log));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存系统管理->系统管理-系统操作日志表信息异常！");
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 修改系统管理->系统管理-系统操作日志表数据
     */
    @PutMapping
    public Mono<Message> update(@RequestBody Log log) {
        try {
            logger.debug("开始更新系统管理->系统管理-系统操作日志表信息……");
            return Mono.justOrEmpty(logService.update(log));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新系统管理->系统管理-系统操作日志表信息异常！");
            throw new BusinessException("SY0002");
        }
    }

    /**
     * 根据主键删除系统管理->系统管理-系统操作日志表数据
     */
    @DeleteMapping("/{logId}")
    public Mono<Message> delete(@PathVariable("logId") String logId) {
        try {
            logger.debug("开始删除系统管理->系统管理-系统操作日志表信息……");
            return Mono.justOrEmpty(logService.deleteById(logId));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除系统管理->系统管理-系统操作日志表信息异常！");
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 批量删除系统管理->系统管理-系统操作日志表数据
     */
    @DeleteMapping
    public Mono<Message> delete(@RequestBody List<String> ids) {
        try {
            logger.debug("开始批量删除系统管理->系统管理-系统操作日志表信息……");
            return Mono.justOrEmpty(logService.deleteByIds(ids));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("批量删除系统管理->系统管理-系统操作日志表信息异常！");
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 根据主键查找系统管理->系统管理-系统操作日志表信息
     */
    @GetMapping("/{logId}")
    public Mono<Message> getById(@PathVariable("logId") String logId) {
        try {
            logger.debug("开始查询系统管理->系统管理-系统操作日志表信息……");
            Message message = Message.success(logService.getById(logId));
            return Mono.justOrEmpty(message);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询系统管理->系统管理-系统操作日志表信息异常！");
            throw new BusinessException("SY0005");
        }
    }

    /**
     * 查询系统管理->系统管理-系统操作日志表信息列表
     */
    @GetMapping
    public Mono<Message> queryList(@RequestParam(value = "limit", required = true) String limit, @RequestParam(value = "offset", required = false) String offset, @RequestParam Map<String, Object> params) {
        try {
            logger.debug("开始查询系统管理->系统管理-系统操作日志表列表信息……");
            Message message = logService.queryPageList(limit, offset, params);
            return Mono.justOrEmpty(message);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询系统管理->系统管理-系统操作日志表信息异常！");
            throw new BusinessException("SY0005");
        }
    }
}