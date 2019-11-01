package com.sshs.system.log.mapper;

import com.sshs.core.page.Page;
import com.sshs.system.log.model.Log;
import com.sshs.core.base.mapper.BaseMapper;


/**
* 系统管理->系统管理-系统操作日志表类
* @author allen
* @date 2019/02/21
*/
public interface LogMapper extends BaseMapper<Log> {
    public Page<Log> findForPageList();
}