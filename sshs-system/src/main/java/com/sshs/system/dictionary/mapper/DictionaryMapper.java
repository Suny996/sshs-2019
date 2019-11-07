package com.sshs.system.dictionary.mapper;

import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.system.dictionary.model.Dictionary;

/**
 * @author Suny
 * @date 2017-10-23
 */
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    /**
     * 根据dictCode查询字典项
     *
     * @param dictCode
     * @return
     */
    //List<Dictionary> findByDictCode(String dictCode);


    //Page<Dictionary> findForPageList();
}
