package com.sshs.core.util;

import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * 数据字典工具类
 *
 * @author Suny
 * @date 2017-11-20
 */
public class DictionaryUtil {
    /**
     * 字典下拉列表缓存
     */
    public static Map<String, List<Map<String, Object>>> DICTLISTS = new HashMap<String, List<Map<String, Object>>>();

    /**
     *
     */
    public static final String DICTIONARY_DESC_SUFFIX = "_desc";

    /**
     * @param dictCode
     * @return
     */
    public static List<Map<String, Object>> getList(String dictCode, String subCode, String locale) {
        List<Map<String, Object>> list = DICTLISTS.get(dictCode);
        if (StringUtils.isNotEmpty(subCode)) {
            for (Map<String, Object> e : list) {
                if (subCode.equals(e.get("value"))) {
                    list = (List<Map<String, Object>>) e.get("children");
                    break;
                }
            }
        }
        if (list == null) {
            list = new ArrayList<Map<String, Object>>();
        } else {
            if (StringUtils.isNotEmpty(locale)) {
                locale = locale.replace("-", "_");
            }
            /*List<Map<String, Object>> list2 = new ArrayList();
            Map<String, Object>[] list3 = new HashMap[list.size()];
            System.arraycopy(list.toArray(), 0, list3, 0, list.size());
            list2 = Arrays.asList(list3);*/
            return getLocaleList(list, locale);
        }
        return null;
    }

    /**
     * 获取国际化后字典
     *
     * @param dict
     * @param locale
     * @return
     */
    static List<Map<String, Object>> getLocaleList(List<Map<String, Object>> dict, String locale) {
        for (Map<String, Object> e : dict) {
            String localeLabel = (String) e.get(locale);
            String label = (String) e.get("label");
            String value = (String) e.get("value");
            List<Map<String, Object>> children = (List<Map<String, Object>>) e.get("children");
            if (children != null && children.size() > 0) {
                children = getLocaleList((List) e.get("children"), locale);
            }
            //e.clear();
            e.put("value", value);
            if (children != null) {
                e.put("children", children);
            }
            if (StringUtils.isNotEmpty(localeLabel)) {
                e.put("label", localeLabel);
            } else {
                e.put("label", label);
            }
        }
        return dict;
    }

    /**
     * @param
     * @return
     */
    public static Map<String, List<Map<String, Object>>> getAllList() {
        return DICTLISTS;
    }
}
