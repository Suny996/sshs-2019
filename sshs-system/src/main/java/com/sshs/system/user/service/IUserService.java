package com.sshs.system.user.service;

import com.sshs.core.base.service.IBaseService;
import com.sshs.core.message.Message;
import com.sshs.system.user.model.User;

import java.util.List;


/**
 * 用户管理服务接口
 *
 * @author Suny
 * @date 2017年7月5日 上午9:43:39
 */
public interface IUserService extends IBaseService<User> {
    /**
     * 根据用户编号查询用户信息
     *
     * @param userCode
     * @return
     */
    User getByUserCode(String userCode);

    /**
     * 保存用户角色信息
     *
     * @param userCode
     * @param roles
     */
    Message<User> auth(String userCode, List<String> roles);

    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    Message<User> resetPassword(String userId);
}