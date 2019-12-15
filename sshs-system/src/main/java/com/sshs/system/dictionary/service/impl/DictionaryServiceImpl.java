package com.sshs.system.dictionary.service.impl;

import com.sshs.core.wrapper.QueryWrapper;
import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.constant.Global;
import com.sshs.core.util.DictionaryUtil;
import com.sshs.system.dictionary.mapper.DictionaryI18nMapper;
import com.sshs.system.dictionary.mapper.DictionaryMapper;
import com.sshs.system.dictionary.model.Dictionary;
import com.sshs.system.dictionary.model.DictionaryI18n;
import com.sshs.system.dictionary.service.IDictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DictionaryServiceImpl extends BaseServiceImpl<DictionaryMapper,Dictionary> implements IDictionaryService {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    /**
     * 字典缓存。
     */
    public static Map<String, Dictionary> dictionarys = new HashMap<String, Dictionary>(100);

    @Resource
    private DictionaryMapper mapper;

    @Resource
    private DictionaryI18nMapper dictionaryI18nMapper;

    /**
     * 查询子节点
     *
     * @param parentId
     * @return
     */
    @Override
    public List<Dictionary> findByParentId(String parentId) {
        QueryWrapper<Dictionary> example = new QueryWrapper<>();
        example.eq("parentId", parentId);
        example.orderByAsc("sortNo");
        return mapper.selectList(example);
    }

    @Override
    public Dictionary getDictionaryByCode(String dictCode) {
        Dictionary dict = dictionarys.get(dictCode);
        if (dict != null) {
            return dict;
        } else {
            QueryWrapper<Dictionary> example = new QueryWrapper<>();
            example.eq("dictType", "1").eq("dictCode", dictCode);
            List<Dictionary> dicts = mapper.selectList(example);
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
        QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
        wrapper.eq("dictType", "1").eq("status", "1");
        List<Dictionary> dictCodes = mapper.selectList(wrapper);
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
            List<Dictionary> children = findByParentId(parent.getDictId());
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
     * 查询国际化信息
     *
     * @param dictId
     * @return
     */
    private List<DictionaryI18n> findI18nByDictId(String dictId) {
        QueryWrapper<DictionaryI18n> example = new QueryWrapper<>();
        example.eq("dictId", dictId);
        return dictionaryI18nMapper.selectList(example);
    }

    /**
     * 迭代初始化字典项
     *
     * @param dict
     * @return
     */
    private void initI18n(Dictionary dict) {
        List<DictionaryI18n> i18ns = findI18nByDictId(dict.getDictId());
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
}
