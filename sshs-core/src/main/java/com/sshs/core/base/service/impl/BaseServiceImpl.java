package com.sshs.core.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.core.base.service.IBaseService;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.exception.CommonErrorCode;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 基础服务类
 *
 * @author Suny
 * @date 2017-10-20
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> implements IBaseService<T> {
    /**
     * 日志工具
     */
    private final static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired(required = false)
    private M mapper;

    @Autowired
    ServiceImpl<M, T> service;


    /**
     * 新增
     *
     * @param model 待保存的对象
     * @return 保存后的对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<T> save(T model) {
        if (mapper.insert(model) >= 1) {
            return Message.success(model);
        } else {
            return Message.failure(CommonErrorCode.SAVE_FAILURE);
        }
    }

    /**
     * 批量新增(不推荐使用，且记录数不要超过2000)
     *
     * @param models 待保存的对象列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<List<T>> save(List<T> models) {
        if (models == null || models.isEmpty()) {
            logger.error("记录不能为空");
            return Message.failure(CommonErrorCode.NO_INPUT_PARAMETER);
        }
        service.saveBatch(models, 1000);
        return Message.success(models);
    }


    /**
     * 修改
     *
     * @param model 要更新的对象
     * @return 更新后的对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<T> update(T model) {
        if (mapper.updateById(model) >= 1) {
            return Message.success(model);
        } else {
            return Message.failure(CommonErrorCode.NO_UPDATE_ENTITY);
        }
    }

    /**
     * 批量修改(不推荐使用，且记录数不要超过2000)
     *
     * @param models 要更新的记录列表
     * @return 更新成功的记录数
     */
    @Override
    @Deprecated
    @Transactional(rollbackFor = Exception.class)
    public Message<List<T>> update(List<T> models) {
        if (models == null || models.isEmpty()) {
            logger.error("记录不能为空");
            return Message.failure(CommonErrorCode.NO_INPUT_PARAMETER);
        }
        if (models.size() > 2000) {
            logger.error("批量更新记录数不能大于2000");
            return Message.failure(CommonErrorCode.MAX_INPUT_PARAMETER);
        }
        service.updateBatchById(models, 1000);
        return Message.success(models);
    }

    /**
     * 删除
     *
     * @param wrapper 要删除的对象
     * @return 返回删除的对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<Integer> delete(com.baomidou.mybatisplus.core.conditions.Wrapper<T> wrapper) {
        return Message.success(mapper.delete(wrapper));
    }

    /**
     * 根据主键删除
     *
     * @param id 待删除对象的主键
     * @return 删除成功的数量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<Integer> deleteById(String id) {
        return Message.success(mapper.deleteById(id));
    }

    /**
     * 根据主键批量删除
     *
     * @param ids 要删除的记录主键列表
     * @return 返回删除记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<Integer> deleteByIds(List<String> ids) {
        return Message.success(mapper.deleteBatchIds(ids));
    }

    /**
     * 根据主键查询单笔记录
     *
     * @param id ID
     * @return 查询到的对象
     */
    @Override
    public Message<T> getById(String id) {
        T e = mapper.selectById(id);
        if (e == null) {
            return Message.failure(CommonErrorCode.NO_DATA_FOUND, id);
        }
        return Message.success(e);
    }

    /**
     * 根据主键查询单笔记录
     *
     * @param id ID
     * @return 查询到的对象
     */
    @Override
    public T getEntityById(String id) {
        T e = mapper.selectById(id);
        if (e == null) {
            throw new BusinessException(CommonErrorCode.NO_DATA_FOUND, id);
        }
        return e;
    }


    /**
     * 公共列表查询方法
     *
     * @param parameter 查询条件
     * @return 查询结果
     */
    @Override
    public Message<List<T>> findForList(Object parameter) {
        //Map<String, Object> params = new HashMap<String, Object>();
        return Message.success(mapper.findForList(parameter));
    }

    /**
     * 公共列表查询方法
     *
     * @param parameter 查询条件
     * @return 查询结果
     */
    @Override
    public List<T> findList(Object parameter) {
        // Map<String, Object> params = new HashMap<String, Object>();
        return mapper.findForList(parameter);
    }

    /**
     * 分页查询方法
     *
     * @param limit     分页信息
     * @param offset    页码
     * @param parameter 查询条件
     * @return 分页查询结果
     */
    @Override
    public Message<IPage<T>> findForPageList(Integer limit, Integer offset, T parameter) {
        long pageSize = 10;
        if (limit != null && limit > 0) {
            pageSize = Long.min(1000, limit);
        }
        long current = 1;
        if (offset != null && offset > 0) {
            current = offset;
        }
        IPage<T> page = new Page<>(current, pageSize);
        return Message.success(mapper.findForList(page, parameter));
    }
}
