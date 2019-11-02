package com.sshs.system.user.mapper;

import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.core.page.Page;
import com.sshs.system.user.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 *
 * @author
 * @date 2017年7月5日 上午9:34:11
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    Page<User> findForPageList();

    User getByUserCode(String userCode);
}