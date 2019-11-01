package com.sshs.system.log.mapper;

import com.sshs.core.page.Page;
import com.sshs.system.log.model.Log;
import tk.mybatis.mapper.common.Mapper;


/**
* 系统管理->系统管理-系统操作日志表类
* @author allen
* @date 2019/02/21
*/
public interface LogMapper extends Mapper<Log> {
    public Page<Log> findForPageList();
}