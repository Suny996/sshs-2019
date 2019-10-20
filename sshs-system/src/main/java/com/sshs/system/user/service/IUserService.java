package com.sshs.system.user.service;

import com.sshs.core.base.service.IBaseService;
import com.sshs.system.user.model.User;


/**
 * 用户管理服务接口
 *
 * @author Suny
 * @date 2017年7月5日 上午9:43:39
 */
public interface IUserService extends IBaseService<User> {
    public User getByUserCode(String userCode);
}
