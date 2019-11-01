package com.sshs.system.dictionary.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.system.dictionary.model.Dictionary;
import com.sshs.system.dictionary.service.IDictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 系统管理->系统管理-数据字典表controller类
 *
 * @author 61910
 * @date 2018/11/12
 */
@RestController
@RequestMapping("/system/dictionarys")
public class DictionaryController extends BaseController {
    @Resource
    private IDictionaryService dictionaryService;

    private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    /**
     * 保存系统管理->系统管理-数据字典表数据
     */
    @PostMapping
    public Message<Dictionary> save(@RequestBody Dictionary dictionary) {
        try {
            logger.debug("开始保存系统管理->系统管理-数据字典表信息……");
            return dictionaryService.save1(dictionary);
        } catch (Exception e) {
            logger.error("保存系统管理->系统管理-数据字典表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 修改系统管理->系统管理-数据字典表数据
     */
    @PutMapping
    public Message<Dictionary> update(@RequestBody Dictionary dictionary) {
        try {
            logger.debug("开始更新系统管理->系统管理-数据字典表信息……");
            return dictionaryService.update1(dictionary);
        } catch (Exception e) {
            logger.error("更新系统管理->系统管理-数据字典表信息异常！", e);
            throw new BusinessException("SY0002");
        }
    }

    /**
     * 根据主键删除系统管理->系统管理-数据字典表数据
     */
    @DeleteMapping("/{dictionaryId}")
    public Message<Boolean> delete(@PathVariable("dictionaryId") String dictionaryId) {
        try {
            logger.debug("开始删除系统管理->系统管理-数据字典表信息……");
            return dictionaryService.deleteById(dictionaryId);
        } catch (Exception e) {
            logger.error("删除系统管理->系统管理-数据字典表信息异常！", e);
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 批量删除系统管理->系统管理-数据字典表数据
     */
    @DeleteMapping
    public Message<Boolean> delete(@RequestBody List<String> ids) {
        try {
            logger.debug("开始批量删除系统管理->系统管理-数据字典表信息……");
            return dictionaryService.deleteByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("批量删除系统管理->系统管理-数据字典表信息异常！");
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 根据主键查找系统管理->系统管理-数据字典表信息
     */
    @GetMapping("/{dictionaryId}")
    public Message<Dictionary> getById(@PathVariable("dictionaryId") String dictionaryId) {
        try {
            logger.debug("开始查询系统管理->系统管理-数据字典表信息……");
            return dictionaryService.getById(dictionaryId);
        } catch (Exception e) {
            logger.error("查询系统管理->系统管理-数据字典表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 查询系统管理->系统管理-数据字典表信息列表
     */
    @GetMapping
    public Message<IPage<Dictionary>> queryList(@RequestParam(value = "limit", required = false) String limit, @RequestParam String offset, @RequestParam Map<String, Object> params) {
        try {
            logger.debug("开始查询系统管理->系统管理-数据字典表列表信息……");
            return dictionaryService.queryPageList(limit, offset, params);
        } catch (Exception e) {
            logger.error("查询系统管理->系统管理-数据字典表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 根据主键查找系统管理->系统管理-数据字典表信息
     */
    @GetMapping("/{dictCode}")
    public Dictionary getDictionarys(@PathVariable("dictCode") String dictCode) {
        try {
            logger.debug("开始查询系统管理->系统管理-数据字典表信息……");
            return dictionaryService.getDictionaryByCode(dictCode);
        } catch (Exception e) {
            logger.error("查询系统管理->系统管理-数据字典表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }
}
