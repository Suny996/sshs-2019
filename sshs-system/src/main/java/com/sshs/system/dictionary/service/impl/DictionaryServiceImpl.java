package com.sshs.system.dictionary.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.constant.Global;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.core.util.DictionaryUtil;
import com.sshs.core.util.UuidUtil;
import com.sshs.system.dictionary.mapper.DictionaryMapper;
import com.sshs.system.dictionary.model.Dictionary;
import com.sshs.system.dictionary.model.DictionaryI18n;
import com.sshs.system.dictionary.service.IDictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hsqldb.lib.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 数据字典管理服务类 .
 *
 * @author Suny
 * @date 2017-12-01
 */
@Service
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary> implements IDictionaryService {
    Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    /**
     * 字典缓存。
     */
    public static Map<String, Dictionary> dictionarys = new HashMap<String, Dictionary>(100);

    @Resource
    private DictionaryMapper dao;

    /**
     * @param parameter
     * @return Message
     */
    public Message queryList(Object parameter) {
        return Message.success(findForList("com.sshs.system.dictionary.mapper.DictionaryMapper.findForList", parameter));
    }

    /**
     * @param page
     */
    @Override
    public Message queryPageList(Page<Dictionary> page) {
        return findForPageList("com.sshs.system.dictionary.mapper.DictionaryMapper.findForPageList", page);
    }

    @Override
    public List<Dictionary> findByParentId(String parentId) {
        return dao.findByParentId(parentId);
    }

    @Override
    public Dictionary getDictionaryByCode(String dictCode) {
        Dictionary dict = dictionarys.get(dictCode);
        if (dict != null) {
            return dict;
        } else {
            List<Dictionary> dicts = dao.findByDictCode(dictCode);
            if (dicts != null && !dicts.isEmpty()) {
                for (Dictionary d : dicts) {
                    initChildren(d);
                    dictionarys.put(d.getDictCode(), d);
                    return d;
                }
            }
        }
        return null;
    }

    /**
     * 初始化字典列表,系统启动时执行缓存
     */
    //@PostConstruct
    @Override
    public void initDictList() {
        List<Dictionary> dictCodes = dao.findAllDictCodes();
        // 字典项
        for (Dictionary dict : dictCodes) {
            initChildren(dict);
            DictionaryUtil.DICTLISTS.put(dict.getDictCode(), dict2Map(dict));
        }
    }

    /**
     * 迭代初始化字典项
     *
     * @param parent
     * @return
     */
    private void initChildren(Dictionary parent) {
        if (!Global.DICTIONARY_DICTTYPE_KEYVALUE.equals(parent.getDictType())) {
            List<Dictionary> children = dao.findByParentId(parent.getDictId());
            if (children != null && !children.isEmpty()) {
                for (Dictionary d : children) {
                    initI18n(d);
                    initChildren(d);
                    parent.addChild(d);
                }
            }
        }
    }

    /**
     * 迭代初始化字典项
     *
     * @param dict
     * @return
     */
    private void initI18n(Dictionary dict) {
        List<DictionaryI18n> i18ns = dao.findI18nByDictId(dict.getDictId());
        if (i18ns != null && !i18ns.isEmpty()) {
            for (DictionaryI18n i : i18ns) {
                dict.addI18n(i);
            }
        }
    }

    /**
     * @param dict
     * @return
     */
    private List<Map<String, Object>> dict2Map(Dictionary dict) {
        List<Map<String, Object>> dictProj = new ArrayList<Map<String, Object>>();
        // 字典码值或 子字典项目
        for (Dictionary dgrp : dict.getChildren()) {
            Map<String, Object> dictGrp = new LinkedHashMap<String, Object>();
            dictGrp.put("value", dgrp.getDictCode());
            dictGrp.put("label", dgrp.getDictName());
            dictGrp.put("desc", dgrp.getDictDesc());
            dictGrp.put("key", dgrp.getDictId());
            dictGrp.put("status", dgrp.getStatus());
            if (dgrp.getI18ns() != null && !dgrp.getI18ns().isEmpty()) {
                for (DictionaryI18n i18n : dgrp.getI18ns()) {
                    dictGrp.put(i18n.getLanguage() + Global.CHARACTER_UNDERLINE + i18n.getCountry(),
                            i18n.getDictName());
                    dictGrp.put(i18n.getLanguage() + Global.CHARACTER_UNDERLINE + i18n.getCountry()
                            + DictionaryUtil.DICTIONARY_DESC_SUFFIX, i18n.getDictDesc());
                }
            }
            // 字典子项
            if (dgrp.getChildren() != null && !dgrp.getChildren().isEmpty()) {
              /*  List children = new ArrayList();
                for (Dictionary dv : dgrp.getChildren()) {
                    children.add(dict2Map(dv));
                }*/
                dictGrp.put("children", dict2Map(dgrp));
            }
            dictProj.add(dictGrp);
        }
        return dictProj;
    }

    /**
     * @param dict
     * @return
     */
    private List<Map<String, Object>> dict2Map2(Dictionary dict) {
        List<Map<String, Object>> dictProj = new ArrayList<Map<String, Object>>();
        // 字典码值或 子字典项目
        for (Dictionary dgrp : dict.getChildren()) {
            Map<String, Object> dictGrp = new LinkedHashMap<String, Object>();
            // 字典子项
            if (!Global.DICTIONARY_DICTTYPE_KEYVALUE.equals(dgrp.getDictType())) {
                List children = new ArrayList();
                for (Dictionary dv : dgrp.getChildren()) {
                    Map<String, Object> dictVal = new LinkedHashMap<String, Object>();
                    dictVal.put("value", dv.getDictCode());
                    dictVal.put("label", dv.getDictName());
                    dictVal.put("key", dv.getDictId());
                    dictVal.put("desc", dv.getDictDesc());
                    dictVal.put("status", dv.getStatus());
                    if (dv.getI18ns() != null && !dv.getI18ns().isEmpty()) {
                        for (DictionaryI18n i18n : dv.getI18ns()) {
                            dictVal.put(i18n.getLanguage() + Global.CHARACTER_UNDERLINE + i18n.getCountry(),
                                    i18n.getDictName());
                            dictVal.put(i18n.getLanguage() + Global.CHARACTER_UNDERLINE + i18n.getCountry()
                                    + DictionaryUtil.DICTIONARY_DESC_SUFFIX, i18n.getDictDesc());
                        }
                    }
                    children.add(dictVal);
                }
                dictGrp.put("children", children);
            }
            dictGrp.put("value", dgrp.getDictCode());
            dictGrp.put("label", dgrp.getDictName());
            dictGrp.put("desc", dgrp.getDictDesc());
            dictGrp.put("key", dgrp.getDictId());
            dictGrp.put("status", dgrp.getStatus());
            if (dgrp.getI18ns() != null && !dgrp.getI18ns().isEmpty()) {
                for (DictionaryI18n i18n : dgrp.getI18ns()) {
                    dictGrp.put(i18n.getLanguage() + Global.CHARACTER_UNDERLINE + i18n.getCountry(),
                            i18n.getDictName());
                    dictGrp.put(i18n.getLanguage() + Global.CHARACTER_UNDERLINE + i18n.getCountry()
                            + DictionaryUtil.DICTIONARY_DESC_SUFFIX, i18n.getDictDesc());
                }
            }
            dictProj.add(dictGrp);
        }
        return dictProj;
    }
    @Override
    public Message save(Dictionary dictionary){
        dictionary.setDictId(UuidUtil.get32UUID());
        try {
            return super.save(dictionary);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存系统管理->系统管理-数据字典表信息异常！");
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 查询系统管理->系统管理-数据字典表列表信息
     * @param limit
     */
    @Override
    public Message queryPageList(String limit, String offset, Map<String, Object> parameter) {
        if (StringUtil.isEmpty(limit)) {
            return Message.success(findForList("com.sshs.system.dictionary.mapper.DictionaryMapper.findForList", parameter));
        } else {
            Page<Dictionary> page = new Page<Dictionary>(Integer.valueOf(limit, 10), Integer.valueOf(offset, 10), parameter);
            return queryPageList(page);
        }
    }
}
