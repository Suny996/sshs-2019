package com.sshs.system.log.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.base.controller.BaseController;
import com.sshs.core.message.Message;
import com.sshs.system.log.model.Log;
import com.sshs.system.log.service.ILogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


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
    public Message save1(@RequestBody Log log) {
        logger.debug("开始保存系统管理->系统管理-系统操作日志表信息……");
        return logService.save(log);
    }

    /**
     * 修改系统管理->系统管理-系统操作日志表数据
     */
    @PutMapping
    public Message<Log> update1(@RequestBody Log log) {
        logger.debug("开始更新系统管理->系统管理-系统操作日志表信息……");
        return logService.update(log);
    }

    /**
     * 根据主键删除系统管理->系统管理-系统操作日志表数据
     */
    @DeleteMapping("/{logId}")
    public Message<Integer> delete(@PathVariable("logId") String logId) {
        logger.debug("开始删除系统管理->系统管理-系统操作日志表信息……");
        return logService.deleteById(logId);
    }

    /**
     * 批量删除系统管理->系统管理-系统操作日志表数据
     */
    @DeleteMapping
    public Message<Integer> delete(@RequestBody List<String> ids) {
        logger.debug("开始批量删除系统管理->系统管理-系统操作日志表信息……");
        return logService.deleteByIds(ids);
    }

    /**
     * 根据主键查找系统管理->系统管理-系统操作日志表信息
     */
    @GetMapping("/{logId}")
    public Message<Log> getById(@PathVariable("logId") String logId) {
        logger.debug("开始查询系统管理->系统管理-系统操作日志表信息……");
        return logService.getById(logId);
    }

    /**
     * 查询系统管理->系统管理-系统操作日志表信息列表
     */
    @GetMapping
    public Message<IPage<Log>> queryList(@RequestParam(value = "limit", required = false) int limit, @RequestParam(value = "offset", required = false) int offset, @RequestParam(required = false) Log params) {
        logger.debug("开始查询系统管理->系统管理-系统操作日志表列表信息……");
        return logService.findForPageList(limit, offset, params);
    }
}
