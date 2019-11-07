package com.sshs.toolkit.coder.controller;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.util.ReflectHelper;
import com.sshs.core.util.Serializabler;
import com.sshs.core.util.UuidUtil;
import com.sshs.toolkit.coder.helper.CoderGenerator;
import com.sshs.toolkit.coder.model.Coder;
import com.sshs.toolkit.coder.model.Column;
import com.sshs.toolkit.coder.service.ICoderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/toolkit/coders")
public class CoderController extends BaseController {
    @Autowired
    private ICoderService coderService;

    Log logger = LogFactory.getLog(CoderController.class);

    /**
     * 保存代码
     */
    @ApiOperation(value = "代碼生成", notes = "")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "tableName", value = "表名", required = true),})
    @PostMapping("/run")
    public Message<Coder> save(@RequestBody Coder coder) {
        try {
            logger.debug("开始保存代码信息……");
            coder.setCoderId(UuidUtil.get32UUID());
            logger.debug("===========>" + coder.getTableName());
            for (Column col : coder.getFields()) {
                if (StringUtils.isEmpty(coder.getTableName())) {
                    coder.setTableName(col.getTableName());
                    coder.setTableComment(col.getTableComment());
                }
                col.setPropertyName(ReflectHelper.getPropertyName(col.getColumnName()));
                col.setPropertyType(CoderGenerator.getPropertyType(col.getColumnType()));
            }

            coder.setColumns(Serializabler.object2Bytes(coder.getFields()));

            CoderGenerator.generate(coder);
            coderService.save(coder);
            return Message.success(coder);
        } catch (Exception e) {
            logger.error("保存代码信息异常！",e);
            throw new BusinessException("SY0001");
        }
    }
    /**
     * 保存代码
     */
    @ApiOperation(value = "代碼生成(快速版)", notes = "")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "tableName", value = "表名", required = true),})
    @GetMapping("/gen")
    public Message run(String tableName) {
        try {
            logger.debug("开始生成代码信息……");
            Coder coder =new Coder();
            coder.setCoderId(UuidUtil.get32UUID());
            coder.setTableName(tableName);
            logger.debug("===========>" + coder.getTableName());
            coder.setFields(coderService.findColumnForList(tableName));
            for (Column col : coder.getFields()) {
                if (StringUtils.isEmpty(coder.getTableName())) {
                    coder.setTableName(col.getTableName());
                    coder.setTableComment(col.getTableComment());
                }
                col.setPropertyName(ReflectHelper.getPropertyName(col.getColumnName()));
                col.setPropertyType(CoderGenerator.getPropertyType(col.getColumnType()));
            }

            coder.setColumns(Serializabler.object2Bytes(coder.getFields()));

            CoderGenerator.generate(coder);
           // coderService.save(coder);
            return Message.success(coder);
        } catch (Exception e) {
            logger.error("保存代码信息异常！",e);
            throw new BusinessException("SY0001");
        }
    }
    /**
     * 修改代码
     */
    @PutMapping
    public Message<Coder> update(@RequestBody Coder coder) {
        try {
            logger.debug("开始更新代码信息……");
            return coderService.update(coder);
        } catch (Exception e) {
            logger.error("更新代码信息异常！", e);
            throw new BusinessException("SY0002");
        }
    }

    /**
     * 删除代码
     */
    @DeleteMapping("/{coderId}")
    public Message<Integer> delete(@PathVariable("coderId") String coderId) {
        try {
            logger.debug("开始删除代码信息……");
            return coderService.deleteById(coderId);
        } catch (Exception e) {
            logger.error("删除代码信息异常！", e);
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 根据主键查找代码信息
     */
    @GetMapping("/{coderId}")
    public Message<Coder> getById(@PathVariable("coderId") String coderId) {
        try {
            logger.debug("开始查询代码信息……");
            return coderService.getById(coderId);
        } catch (Exception e) {
            logger.error("查询代码信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 查询表信息列表
     */
    @GetMapping("/columnList/{tableName}")
    public Message queryList(@PathVariable("tableName") String tableName) {
        try {
            logger.debug("开始查询表列表信息……");
            List<Column> columnList = coderService.findColumnForList(tableName);
            return Message.success(columnList);
        } catch (Exception e) {
            logger.error("查询表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 分页查表户信息
     */
    @GetMapping("/tableList")
    public Message queryPageList(@RequestParam(value = "limit", required = false) int limit, @RequestParam(value = "offset", required = false) int offset, @RequestParam(required = false) Map<String, Object> params) {
        try {
            logger.debug("开始分页查询表信息……");
            return coderService.findDbTableForPageList(limit, offset, params);
        } catch (Exception e) {
            logger.error("分页查询表信息异常！", e);
            throw new BusinessException("SY0005");
        }
    }
}
