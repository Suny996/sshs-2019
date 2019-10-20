package com.sshs.system.log.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.util.UuidUtil;
import com.sshs.system.log.mapper.LogMapper;
import com.sshs.system.log.model.Log;
import com.sshs.system.log.service.ILogService;
import org.hsqldb.lib.StringUtil;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 系统管理->系统管理-系统操作日志表service实现类
 *
 * @author Suny
 * @date 2019/02/21
 */
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements ILogService {
    org.slf4j.Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    @Resource
    private LogMapper mapper;

    /**
     * 保存系统管理->系统管理-系统操作日志表数据方法
     *
     * @param log
     * @return Message
     */
    @Override
    public Message save(Log log) {
        log.setLogId(UuidUtil.get32UUID());
        try {
            return super.save(log);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存系统管理->系统管理-系统操作日志表信息异常！");
            throw new BusinessException("-110002");
        }
    }

    /**
     * 批量保存系统管理->系统管理-系统操作日志表数据方法
     *
     * @param logs
     * @return Message
     */
    @Override
    public Message save(List<Log> logs) {
        try {
            for (Log log : logs) {
                log.setLogId(UuidUtil.get32UUID());
            }
            return super.save(logs);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("批量保存系统管理->系统管理-系统操作日志表信息异常！");
            throw new BusinessException("-110002");
        }
    }

    /**
     * 查询系统管理->系统管理-系统操作日志表列表信息
     *
     * @param limit
     */
    @Override
    public Message queryPageList(String limit, String offset, Map<String, Object> parameter) {
        if (StringUtil.isEmpty(limit)) {
            return super.findForList(parameter);
        } else {
            return super.queryPageList(limit, offset, parameter);
        }
    }


}
