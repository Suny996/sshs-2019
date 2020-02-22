package com.sshs.toolkit.coder.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.base.controller.BaseController;
import com.sshs.core.message.Message;
import com.sshs.toolkit.coder.model.Coder;
import com.sshs.toolkit.coder.model.Column;
import com.sshs.toolkit.coder.service.ICoderService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 用户管理控制类
 *
 * @author Suny
 * @date 2017年7月5日 上午10:40:10
 */
@Api(tags = "代码生成工具")
@RestController
@RequestMapping("/v1/toolkit/coders")
public class CoderController extends BaseController {
    @Resource
    private ICoderService coderService;

    final private static Logger logger = LoggerFactory.getLogger(CoderController.class);

    /**
     * 保存代码
     */
    @ApiOperation(value = "代碼生成")
    @ApiImplicitParam(paramType = "body", dataType = "Coder", name = "coder", value = "表信息")
    @PostMapping("/run")
    public Message<Coder> save(@RequestBody Coder coder) {
        logger.debug("开始保存代码信息……");
        return coderService.save(coder);
    }

    /**
     * 保存代码
     */
    @ApiOperation(value = "代碼生成(快速版)")
    @ApiOperationSort(0)
    @ApiImplicitParam(paramType = "path", name = "tableName", value = "表名", required = true)
    @GetMapping("/generate/{tableName}")
    public Message generate(@PathVariable("tableName") String tableName) {
        logger.debug("开始生成代码信息……");
        return coderService.run(tableName);
    }

    /**
     * 修改代码
     */
    @ApiOperation(value = "代碼生成修改")
    @ApiImplicitParam(paramType = "body", dataType = "Coder", name = "coder", value = "表信息")
    @PutMapping
    public Message<Coder> update(@RequestBody Coder coder) {
        logger.debug("开始更新代码信息……");
        return coderService.update(coder);
    }

    /**
     * 删除代码
     */
    @ApiOperation(value = "代碼生成记录删除")
    @ApiImplicitParam(paramType = "path", name = "coderId", value = "代码ID", required = true)
    @DeleteMapping("/{coderId}")
    public Message<Integer> delete(@PathVariable("coderId") String coderId) {
        logger.debug("开始删除代码信息……");
        return coderService.deleteById(coderId);
    }

    /**
     * 根据主键查找代码信息
     */
    @ApiOperation(value = "代碼生成记录查询")
    @ApiImplicitParam(paramType = "path", name = "coderId", value = "代码ID", required = true)
    @GetMapping("/{coderId}")
    public Message<Coder> getById(@PathVariable("coderId") String coderId) {
        logger.debug("开始查询代码信息……");
        return coderService.getById(coderId);
    }

    /**
     * 查询表信息列表
     */
    @ApiOperation(value = "代碼生成列信息查询")
    @ApiImplicitParam(paramType = "path", name = "tableName", value = "表名", required = true)
    @GetMapping("/columnList/{tableName}")
    public Message queryList(@PathVariable("tableName") String tableName) {
        logger.debug("开始查询表列表信息……");
        List<Column> columnList = coderService.findColumnForList(tableName);
        return Message.success(columnList);
    }

    /**
     * 分页查表户信息
     */
    @ApiOperation(value = "代碼生成列信息查询")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "limit", value = "页大小"),
            @ApiImplicitParam(paramType = "query", name = "offset", value = "页码")})
    @GetMapping("/tableList")
    public Message<IPage<Coder>> queryPageList(@RequestParam(value = "limit", required = false) int limit, @RequestParam(value = "offset", required = false) int offset, @RequestParam(required = false) Map<String, Object> params) {
        logger.debug("开始分页查询表信息……");
        return coderService.findDbTableForPageList(limit, offset, params);
    }
}
