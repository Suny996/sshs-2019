package com.sshs.core.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.core.base.service.IBaseService;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.core.util.SystemUtil;
import com.sshs.core.util.UuidUtil;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public abstract class BaseServiceImpl<T> extends ServiceImpl<BaseMapper<T>, T> implements IBaseService<T> {
    /**
     * 日志工具
     */
    private final static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    /**
     * 新增
     *
     * @param model 待保存的对象
     * @return 保存后的对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<T> save1(T model) {
        setCrtProperties(model);
        if (super.save(model)) {
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
    @Deprecated
    @Transactional(rollbackFor = Exception.class)
    public Message<Boolean> saveList(List<T> models) {
        Assert.notEmpty(models, "error: entityList must not be empty");
        if (models.size() > 2000) {
            logger.error("批量插入记录数不能大于2000");
            throw new BusinessException("-10005");
        }
        for (T model : models) {
            setCrtProperties(model);
        }
        return Message.success(super.saveBatch(models, 1000));
    }


    /**
     * 修改
     *
     * @param model 要更新的对象
     * @return 更新后的对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<T> update1(T model) {
        setUpdProperties(model);
        if (super.updateById(model)) {
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
    public Message<Integer> updateList(List<T> models) {
        Assert.notEmpty(models, "error: entityList must not be empty");
        if (models.size() > 2000) {
            logger.error("批量插入记录数不能大于2000");
            throw new BusinessException("-10005");
        }
        String sqlStatement = SqlHelper.table((Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 1)).getSqlStatement(SqlMethod.UPDATE_BY_ID.getMethod());
        try (SqlSession batchSqlSession = SqlHelper.sqlSessionBatch((Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 1))) {
            int i = 0;
            for (T anEntityList : models) {
                MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                param.put(Constants.ENTITY, anEntityList);
                batchSqlSession.update(sqlStatement, param);
                if (i >= 1 && i % 300 == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
            return Message.success(i);
        }
    }

    /**
     * 删除
     *
     * @param model 要删除的对象
     * @return 返回删除的对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<T> delete1(T model) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>(model);
        if (super.remove(queryWrapper)) {
            return Message.success(model);
        } else {
            return Message.failure("-30001");
        }
    }

    /**
     * 根据主键删除
     *
     * @param id 待删除对象的主键
     * @return 删除成功的数量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<Boolean> deleteById(String id) {
        return Message.success(super.removeById(id));
    }

    /**
     * 根据主键批量删除
     *
     * @param ids 要删除的记录主键列表
     * @return 返回删除记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message<Boolean> deleteByIds(List<String> ids) {
        return Message.success(super.removeByIds(ids));
    }

    /**
     * 根据主键查询单笔记录
     *
     * @param id ID
     * @return 查询到的对象
     */
    @Override
    public Message<T> getById(String id) {
        return Message.success((T) super.getById(id));
    }

    /**
     * 根据主键查询单笔记录
     *
     * @param id ID
     * @return 查询到的对象
     */
    @Override
    public T getEntityById(String id) {
        return super.getById(id);
    }

    /**
     * 分页查询方法
     *
     * @param page      分页信息
     * @param parameter 查询条件
     * @return 分页查询结果
     */
    @Override
    public Message<IPage<T>> findForPageList(Page<T> page, Object parameter) {
        /**
         * 加入数据权限过滤条件
         */
        //parameter.put("_orgAuth", SystemUtil.getOrgAuthStatement());
        return Message.success(baseMapper.findForList(page, parameter));
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
        return Message.success(baseMapper.findForList(parameter));
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
    public Message<IPage<T>> queryPageList(String limit, String offset, Map<String, Object> parameter) {
        int pageSize = 10;
        int pageNumber = 1;
        if (limit != null) {
            pageSize = Integer.min(1000, Integer.valueOf(limit, 10));
        }
        if (offset != null) {
            pageNumber = Integer.valueOf(offset, 10);
        }
        Page<T> page = new Page<>(pageSize, pageNumber);
        return findForPageList(page, parameter);
    }

    /**
     * @param object
     */
    private void setCrtProperties(T object) {
        try {
            Class<?> clazz = object.getClass();
            try {
                Method setId = clazz.getDeclaredMethod("setId", String.class);
                if (setId != null) {
                    setId.invoke(object, UuidUtil.get32UUID());
                }
            } catch (Exception e) {
                logger.warn("设置新增相关公共字段出错可以忽略{}", "setId");
            }
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
