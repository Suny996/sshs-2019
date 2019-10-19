package com.sshs.core.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.sshs.core.base.service.IBaseService;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.core.util.SystemUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
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
    @Autowired
    private BaseMapper<T> dao;

    @Resource(name = "sqlSessionTemplate")
    protected SqlSessionTemplate sqlSessionTemplate;

    /**
     * @return
     */
    public Mapper<T> getDao() {
        return this.dao;
    }

    /**
     * 新增
     *
     * @param model
     * @return
     * @throws Exception
     */
    @Override
    public Message save(T model) throws Exception {
        setCrtProperties(model);
        if (dao.insert(model) > 0) {
            return Message.success(model);
        } else {
            return null;
        }
    }

    /**
     * 新增
     *
     * @param models
     * @return
     * @throws Exception
     */
    @Override
    public Message save(List<T> models) throws Exception {
        int i = 0;
        for (T model : models) {
            setCrtProperties(model);
            i += dao.insert(model);
        }
        return Message.success(i);
    }


    /**
     * 修改
     *
     * @param model
     * @return
     * @throws Exception
     */
    @Override
    public Message update(T model) throws Exception {
        setUpdProperties(model);
        if (dao.updateById(model) > 0) {
            return Message.success(model);
        } else {
            return Message.failure("-20001");
        }
    }

    /**
     * 批量修改
     *
     * @param models
     * @return
     * @throws Exception
     */
    @Override
    public int update(List<T> models) throws Exception {
        int i = 0;
        for (T model : models) {
            setUpdProperties(model);
            i += dao.updateById(model);
        }
        return i;
    }

    /**
     * 删除
     *
     * @param model
     * @return
     */
    @Override
    public Message delete(Wrapper<T> model) {
        if (dao.delete(model) > 0) {
            return Message.success(model);
        } else {
            return Message.failure("-30001");
        }
    }

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Message deleteById(String id) throws Exception {
        if (dao.deleteById(id) > 0) {
            return Message.success(id);
        } else {
            return Message.failure("-30001");
        }
    }

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return
     */
    @Override
    public Message deleteByIds(List<String> ids) {
        int i = dao.deleteBatchIds(ids);
        if (i > 0) {
            return Message.success(ids);
        } else {
            return Message.failure("-30001");
        }
    }

    /**
     * 根据主键查询单笔记录
     *
     * @param id
     * @return
     */
    @Override
    public T getById(String id) {
        return dao.selectById(id);
    }

    /**
     * 公共分页查询方法
     *
     * @param sqlId
     * @param page
     * @return
     */
    @Override
    public Message findForPageList(String sqlId, Page<T> page) {
        /**
         * 加入数据权限过滤条件
         */
        page.getVariables().put("_orgAuth", SystemUtil.getOrgAuthStatement());
        page.setRows(sqlSessionTemplate.selectList(sqlId, page));
        return Message.success(page);
    }

    /**
     * 公共列表查询方法
     *
     * @param sqlId
     * @param parameter
     * @return
     */
    @Override
    public List<T> findForList(String sqlId, Object parameter) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("variables", parameter);
        params.put("operators", new HashMap<String, Object>(0));
        return (List<T>) sqlSessionTemplate.selectList(sqlId, params);
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
            }
            try {
                Method setCrtOrgCode = clazz.getDeclaredMethod("setCrtOrgCode", String.class);
                if (setCrtOrgCode != null) {
                    setCrtOrgCode.invoke(object, SystemUtil.getCurrentUser().getOrgCode());
                }
            } catch (Exception e) {
            }
            try {
                Method setCrtDate = clazz.getDeclaredMethod("setCrtDate", java.util.Date.class);
                if (setCrtDate != null) {
                    setCrtDate.invoke(object, new Date());
                }
            } catch (Exception e) {
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
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
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

            }
            try {
                Method setUpdOrgCode = clazz.getDeclaredMethod("setUpdOrgCode", String.class);
                if (setUpdOrgCode != null) {
                    setUpdOrgCode.invoke(object, SystemUtil.getCurrentUser().getOrgCode());
                }
            } catch (Exception e) {

            }
            try {
                Method setUpdDate = clazz.getDeclaredMethod("setUpdDate", java.util.Date.class);
                if (setUpdDate != null) {
                    setUpdDate.invoke(object, new Date());
                }
            } catch (Exception e) {

            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 虚方法，要用该方法必须在实体类中实现
     *
     * @param limit
     * @param offset
     * @param parameter
     * @return
     * @throws Exception
     */
    @Override
    public Message queryPageList(String limit, String offset, Map<String, Object> parameter) {
        return null;
    }

    /**
     * 虚方法，要用该方法必须在实体类中实现
     *
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public Message queryPageList(Page<T> page) {
        return null;
    }

    /**
     * @param parameter
     * @return
     */
    @Override
    public List<T> queryList(Map<String, Object> parameter) {
        return null;
    }
}
