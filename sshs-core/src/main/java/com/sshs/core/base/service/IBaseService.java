package com.sshs.core.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;

import java.util.List;
import java.util.Map;

/**
 * 基础服务接口
 *
 * @param <T>
 * @author Suny
 */
public interface IBaseService<T> {
    /**
     * 新增
     *
     * @param model
     * @return
     * @throws Exception
     */
    public Message save(T model) throws Exception;

    /**
     * 批量新增
     *
     * @param models
     * @return
     * @throws Exception
     */
    public Message save(List<T> models) throws Exception;

    /**
     * 修改
     *
     * @param model
     * @return
     * @throws Exception
     */
    Message update(T model) throws Exception;

    /**
     * 批量修改
     *
     * @param models
     * @return
     * @throws Exception
     */
    public int update(List<T> models) throws Exception;

    /**
     * 删除
     *
     * @param model
     * @return
     * @throws Exception
     */
    Message delete(Wrapper<T> model) throws Exception;

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return
     * @throws Exception
     */
    Message deleteByIds(List<String> ids) throws Exception;

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    Message deleteById(String id) throws Exception;

    /**
     * 根据主键查询单笔记录
     *
     * @param id
     * @return
     * @throws Exception
     */
    T getById(String id) throws Exception;

    /**
     * 分页查询
     *
     * @param sqlId
     * @param page
     * @return
     */
    Message findForPageList(String sqlId, Page<T> page);

    /**
     * 公共列表查询方法
     *
     * @param sqlId
     * @param parameter
     * @return
     */
    List<T> findForList(String sqlId, Object parameter);

    /**
     * 查询列表
     *
     * @param parameter
     * @param limit
     * @param offset
     * @return Message
     */
    public Message queryPageList(String limit, String offset, Map<String, Object> parameter);

    /**
     * 查询列表
     *
     * @param parameter
     * @return Message
     */
    public List<T> queryList(Map<String, Object> parameter);

    /**
     * 分页查询列表
     *
     * @param page
     * @return Message
     */
    Message queryPageList(Page<T> page);
}
