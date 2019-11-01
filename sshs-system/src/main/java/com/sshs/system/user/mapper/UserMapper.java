package com.sshs.system.user.mapper;

import com.sshs.core.page.Page;
import com.sshs.system.user.model.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * 系统用户
 *
 * @author
 * @date 2017年7月5日 上午9:34:11
 */
public interface UserMapper extends Mapper<User> {
    public Page<User> findForPageList();
    public User getByUserCode(String userCode);
}