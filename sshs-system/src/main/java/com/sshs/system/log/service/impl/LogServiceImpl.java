package com.sshs.system.log.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.core.util.UuidUtil;
import com.sshs.system.log.mapper.LogMapper;
import com.sshs.system.log.model.Log;
import com.sshs.system.log.service.ILogService;
import org.apache.commons.logging.LogFactory;
import org.hsqldb.lib.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.List;


/**
 * 系统管理->系统管理-系统操作日志表service实现类
 *
 * @author allen
 * @date 2019/02/21
 */
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements ILogService {
    org.apache.commons.logging.Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
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
            return Message.success(queryList(parameter));
        } else {
            Page<Log> page = new Page<Log>(Integer.valueOf(limit, 10), Integer.valueOf(offset, 10), parameter);
            return queryPageList(page);
        }
    }

    /**
     * 查询系统管理->系统管理-系统操作日志表列表信息
     *
     * @param parameter
     */
    @Override
    public List<Log> queryList(Map<String, Object> parameter) {
        return super.findForList("com.sshs.system.log.mapper.LogMapper.findForList", parameter);
    }

    /**
     * 分页查询系统管理->系统管理-系统操作日志表信息
     */
    @Override
    public Message queryPageList(Page<Log> page) {
        return findForPageList("com.sshs.system.log.mapper.LogMapper.findForPageList", page);
    }
}
