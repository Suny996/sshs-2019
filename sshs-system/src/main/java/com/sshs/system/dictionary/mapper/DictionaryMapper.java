package com.sshs.system.dictionary.mapper;

import com.sshs.core.page.Page;
import com.sshs.system.dictionary.model.DictionaryI18n;
import com.sshs.system.dictionary.model.Dictionary;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * @author Suny
 * @date 2017-10-23
 */
public interface DictionaryMapper extends Mapper<Dictionary> {
	/**
	 * 新增
	 * 
	 * @param dictionary
	 * @return
	 * @throws Exception
	 */
	int save(Dictionary dictionary) throws Exception;

	/**
	 * 修改
	 * 
	 * @param dictionary
	 * @return
	 * @throws Exception
	 */
	int update(Dictionary dictionary) throws Exception;

	/**
	 * 刪除
	 * 
	 * @param dictId
	 * @return
	 * @throws Exception
	 */
	int delete(String dictId) throws Exception;

	/**
	 * 根据主键查询对象
	 * 
	 * @param dictId
	 * @return
	 * @throws Exception
	 */
	Dictionary findById(String dictId) throws Exception;

	/**
	 * 根据dictCode查询字典项
	 * 
	 * @param dictCode
	 * @return
	 */
	List<Dictionary> findByDictCode(String dictCode);

	/**
	 * 根据parentId查询子列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<Dictionary> findByParentId(String parentId);

	/**
	 * 查询国际化信息
	 * 
	 * @param dictId
	 * @return
	 */
	List<DictionaryI18n> findI18nByDictId(String dictId);

	/**
	 * 查询所有下拉列表字典项目
	 * 
	 * @return
	 */
	List<Dictionary> findAllDictCodes();

	public Page<Dictionary> findForPageList();
}
