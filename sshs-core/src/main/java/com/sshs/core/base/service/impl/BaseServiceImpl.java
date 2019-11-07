package com.sshs.core.base.service.impl;


import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.core.base.service.IBaseService;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.core.util.SystemUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础服务类
 *
 * @author Suny
 * @date 2017-10-20
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
    /**
     * 日志工具
     */
    private final static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired(required = false)
    BaseMapper<T> mapper;

    /**
     * 新增
     *
     * @param model 待保存的对象
     * @return 保存后的对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<T> save(T model) {
        setCrtProperties(model);
        if (mapper.insertSelective(model) >= 1) {
            return Message.success(model);
        } else {
            return Message.failure("-100001");
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
    public Message<Integer> save(List<T> models) {
        if (models == null || models.isEmpty()) {
            logger.error("记录不能为空");
            throw new BusinessException("-10005");
        }
        for (T model : models) {
            setCrtProperties(model);
        }
        for (List<T> list : Lists.partition(models, 1000)) {
            mapper.insertList(list);
        }
        return Message.success(models.size());
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
        setUpdProperties(model);
        if (mapper.updateByPrimaryKey(model) >= 1) {
            return Message.success(model);
        } else {
            return Message.failure("-20001");
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
    public Message<Integer> update(List<T> models) {
        if (models == null || models.isEmpty()) {
            logger.error("记录不能为空");
            throw new BusinessException("-10005");
        }
        if (models.size() > 2000) {
            logger.error("批量插入记录数不能大于2000");
            throw new BusinessException("-10005");
        }
        int i = 0;
        for (T model : models) {
            i += mapper.updateByPrimaryKey(model);
        }
        return Message.success(i);
    }

    /**
     * 删除
     *
     * @param model 要删除的对象
     * @return 返回删除的对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<Integer> delete(T model) {
        return Message.success(mapper.delete(model));
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
        return Message.success(mapper.deleteByPrimaryKey(id));
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
        return Message.success(mapper.deleteByIds(StringUtils.join(ids, ',')));
    }

    /**
     * 根据主键查询单笔记录
     *
     * @param id ID
     * @return 查询到的对象
     */
    @Override
    public Message<T> getById(String id) {
        return Message.success((T) mapper.selectByPrimaryKey(id));
    }

    /**
     * 根据主键查询单笔记录
     *
     * @param id ID
     * @return 查询到的对象
     */
    @Override
    public T getEntityById(String id) {
        return (T) mapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询方法
     *
     * @param page      分页信息
     * @param parameter 查询条件
     * @return 分页查询结果
     */
    @Override
    public Message<Page<T>> findForPageList(Page<T> page, Object parameter) {
        /**
         * 加入数据权限过滤条件
         */
        //parameter.put("_orgAuth", SystemUtil.getOrgAuthStatement());
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<T> list = mapper.findForList(parameter);
        return Message.success(new Page(list));
    }

    /**
     * 公共列表查询方法
     *
     * @param parameter 查询条件
     * @return 查询结果
     */
    @Override
    public Message<List<T>> findForList(Object parameter) {
        Map<String, Object> params = new HashMap<String, Object>();
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
        Map<String, Object> params = new HashMap<String, Object>();
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
    public Message<Page<T>> queryPageList(int limit, int offset, Map<String, Object> parameter) {
        int pageSize = 10;
        int pageNumber = 1;
        if (limit > 0) {
            pageSize = Integer.min(1000, limit);
        }
        Page<T> page = new Page<>(pageSize, offset);
        return findForPageList(page, parameter);
    }

    /**
     * @param object
     */
    private void setCrtProperties(T object) {
        try {
            Class<?> clazz = object.getClass();
            try {
                Method setCrtUserCode = clazz.getDeclaredMethod("setCrtUserCode", String.class);
                if (setCrtUserCode != null) {
                    setCrtUserCode.invoke(object, SystemUtil.getCurrentUser().getUserCode());
                }
            } catch (Exception e) {
                logger.warn("设置新增相关公共字段出错可以忽略{}", "setCrtUserCode");
            }
            try {
                Method setCrtOrgCode = clazz.getDeclaredMethod("setCrtOrgCode", String.class);
                if (setCrtOrgCode != null) {
                    setCrtOrgCode.invoke(object, SystemUtil.getCurrentUser().getOrgCode());
                }
            } catch (Exception e) {
                logger.warn("设置新增相关公共字段出错可以忽略{}", "setCrtOrgCode");
            }
            try {
                Method setCrtDate = clazz.getDeclaredMethod("setCrtDate", java.util.Date.class);
                if (setCrtDate != null) {
                    setCrtDate.invoke(object, new Date());
                }
            } catch (Exception e) {
                logger.warn("设置新增相关公共字段出错可以忽略{}", "setCrtDate");
            }
            // 处理法人行问题，全省权限时不设置法人行号
            /*String authType = "01";
            if (authType != null && !authType.contains(Global.AUTH_LEVEL_TOP)) {
                Method getLegalOrg = clazz.getDeclaredMethod("getLegalOrg");
                if (getLegalOrg != null && getLegalOrg.invoke(object) == null) {
                    Method setLegalOrg = clazz.getDeclaredMethod("setLegalOrg", String.class);
                    if (setLegalOrg != null) {
                        setLegalOrg.invoke(object, "admin");
                    }
                }
            }*/

        } catch (SecurityException e) {
            logger.warn("设置新增相关公共字段出错可以忽略");
        } catch (IllegalArgumentException e) {
            logger.warn("设置新增相关公共字段出错可以忽略");
        }
    }

    /**
     * @param object
     */
    private void setUpdProperties(T object) {
        try {
            Class<?> clazz = object.getClass();
            try {
                Method setUpdUserCode = clazz.getDeclaredMethod("setUpdUserCode", String.class);
                if (setUpdUserCode != null) {
                    setUpdUserCode.invoke(object, SystemUtil.getCurrentUser().getUserCode());
                }
            } catch (Exception e) {
                logger.warn("设置修改相关公共字段出错可以忽略{}", "setUpdUserCode");
            }
            try {
                Method setUpdOrgCode = clazz.getDeclaredMethod("setUpdOrgCode", String.class);
                if (setUpdOrgCode != null) {
                    setUpdOrgCode.invoke(object, SystemUtil.getCurrentUser().getOrgCode());
                }
            } catch (Exception e) {
                logger.warn("设置修改相关公共字段出错可以忽略{}", "setUpdOrgCode");
            }
            try {
                Method setUpdDate = clazz.getDeclaredMethod("setUpdDate", java.util.Date.class);
                if (setUpdDate != null) {
                    setUpdDate.invoke(object, new Date());
                }
            } catch (Exception e) {
                logger.warn("设置修改相关公共字段出错可以忽略{}", "setUpdDate");
            }
        } catch (SecurityException e) {
            logger.warn("设置修改相关公共字段出错可以忽略");
        } catch (IllegalArgumentException e) {
            logger.warn("设置修改相关公共字段出错可以忽略");
        }
    }
}
