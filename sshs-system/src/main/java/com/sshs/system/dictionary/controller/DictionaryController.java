package com.sshs.system.dictionary.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.base.controller.BaseController;
import com.sshs.core.message.Message;
import com.sshs.core.version.Version;
import com.sshs.system.dictionary.model.Dictionary;
import com.sshs.system.dictionary.service.IDictionaryService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 系统管理->系统管理-数据字典表controller类
 *
 * @author 61910
 * @date 2018/11/12
 */
@Api(tags = "系统管理-字典管理接口")
@RestController
@Version(1)
@RequestMapping("/{version:v\\d+}/system/dictionarys")
public class DictionaryController extends BaseController {
    @Resource
    private IDictionaryService dictionaryService;

    private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    /**
     * 保存系统管理->系统管理-数据字典表数据
     */
    @PostMapping
    public Message<Dictionary> save(@RequestBody Dictionary dictionary) {
        logger.debug("开始保存系统管理->系统管理-数据字典表信息……");
        return dictionaryService.save(dictionary);
    }

    /**
     * 修改系统管理->系统管理-数据字典表数据
     */
    @PutMapping
    public Message<Dictionary> update(@RequestBody Dictionary dictionary) {
        logger.debug("开始更新系统管理->系统管理-数据字典表信息……");
        return dictionaryService.update(dictionary);
    }

    /**
     * 根据主键删除系统管理->系统管理-数据字典表数据
     */
    @DeleteMapping("/{dictionaryId}")
    public Message<Integer> delete(@PathVariable("dictionaryId") String dictionaryId) {
        logger.debug("开始删除系统管理->系统管理-数据字典表信息……");
        return dictionaryService.deleteById(dictionaryId);
    }

    /**
     * 批量删除系统管理->系统管理-数据字典表数据
     */
    @DeleteMapping
    public Message<Integer> delete(@RequestBody List<String> ids) {
        logger.debug("开始批量删除系统管理->系统管理-数据字典表信息……");
        return dictionaryService.deleteByIds(ids);
    }

    /**
     * 根据主键查找系统管理->系统管理-数据字典表信息
     */
    @GetMapping("/id/{dictionaryId}")
    public Message<Dictionary> getById(@PathVariable("dictionaryId") String dictionaryId) {
        logger.debug("开始查询系统管理->系统管理-数据字典表信息……");
        return dictionaryService.getById(dictionaryId);
    }

    /**
     * 查询系统管理->系统管理-数据字典表信息列表
     */
    @GetMapping
    public Message<IPage<Dictionary>> queryPageList(@RequestParam(value = "limit", required = false) int limit, @RequestParam(value = "offset", required = false) int offset, @RequestParam Dictionary params) {
        logger.debug("开始查询系统管理->系统管理-数据字典表列表信息……");
        return dictionaryService.findForPageList(limit, offset, params);
    }

    /**
     * 根据主键查找系统管理->系统管理-数据字典表信息
     */
    @GetMapping("/{dictCode}")
    public Dictionary getDictionarys(@PathVariable("dictCode") String dictCode) {
        logger.debug("开始查询系统管理->系统管理-数据字典表信息……");
        return dictionaryService.getDictionaryByCode(dictCode);
    }
}
