package com.sshs.system.log.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.util.UuidUtil;
import com.sshs.system.log.model.Log;
import com.sshs.system.log.service.ILogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 系统管理->系统管理-系统操作日志表service实现类
 *
 * @author Suny
 * @date 2019/02/21
 */
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements ILogService {
    Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    /**
     * 保存系统管理->系统管理-系统操作日志表数据方法
     *
     * @param log
     * @return Message
     */
    @Override
    public Message<Log> save1(Log log) {
        log.setLogId(UuidUtil.get32UUID());
        try {
            return super.save1(log);
        } catch (Exception e) {
            logger.error("保存系统管理->系统管理-系统操作日志表信息异常！", e);
            throw new BusinessException("-110002");
        }
    }
}
