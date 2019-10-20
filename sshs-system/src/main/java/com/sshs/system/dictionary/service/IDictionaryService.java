package com.sshs.system.dictionary.service;

import com.sshs.core.base.service.IBaseService;
import com.sshs.core.page.Page;
import com.sshs.system.dictionary.model.Dictionary;

import java.util.List;

/**
 * 
 * @author Suny
 *
 */
public interface IDictionaryService extends IBaseService<Dictionary> {
	/**
	 * 根据dictCode查询字典项
	 * 
	 * @param dictCode
	 * @return
	 */
	Dictionary getDictionaryByCode(String dictCode);

	/**
	 * 根据parentId查询子列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<Dictionary> findByParentId(String parentId);

	/**
	 * 数据字典初始化
	 */
    void initDictList();
}
