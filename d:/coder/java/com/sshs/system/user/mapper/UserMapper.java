package com.sshs.system.user.mapper;

import com.scai.core.page.Page;
import com.sshs.system.user.model.User;
import tk.mybatis.mapper.common.Mapper;


/**
* SYS_USER->null类
* @author sunyonggang
* @date 2019/11/07
*/
public interface UserMapper extends Mapper<User> {
    public Page<User> findForPageList();
}