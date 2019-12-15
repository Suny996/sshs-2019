package com.sshs.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充 创建与更新信息
 *
 * @author Suny
 * @date 2019-12-13
 */
@Component
public class SshsMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SshsMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.debug("start insert fill ....");
        this.setInsertFieldValByName("crtUserCode", "admin", metaObject);//版本号3.0.6以及之前的版本
        this.setFieldValByName("crtDate", new Date(), metaObject);//版本号3.0.6以及之前的版本
        this.setFieldValByName("crtOrg", "001", metaObject);//@since 快照：3.0.7.2-SNAPSHOT， @since 正式版暂未发布3.0.7
        this.setFieldValByName("crtUser", "admin", metaObject);//@since 快照：3.0.7.2-SNAPSHOT， @since 正式版暂未发布3.0.7
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.debug("start update fill ....");
        this.setUpdateFieldValByName("updUserCode", "admin", metaObject);//版本号3.0.6以及之前的版本
        this.setFieldValByName("updDate", new Date(), metaObject);//版本号3.0.6以及之前的版本
        this.setFieldValByName("updOrg", "001", metaObject);//@since 快照：3.0.7.2-SNAPSHOT， @since 正式版暂未发布3.0.7
        //this.setUpdateFieldValByName("operator", "Tom", metaObject);//@since 快照：3.0.7.2-SNAPSHOT， @since 正式版暂未发布3.0.7
    }
}