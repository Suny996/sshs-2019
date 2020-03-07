package com.sshs.system.user.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.message.Message;
import com.sshs.core.wrapper.QueryWrapper;
import com.sshs.system.user.mapper.UserMapper;
import com.sshs.system.user.model.User;
import com.sshs.system.user.service.IUserService;
import com.sshs.system.userrole.model.UserRole;
import com.sshs.system.userrole.service.IUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 系统用户实现类
 *
 * @author barry.wang
 * @date 2017年7月5日 上午9:46:09
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper mapper;

    @Resource
    IUserRoleService uerRoleService;
    /**
     * 密码
     */
    @Value("${core.defaultPassword:{bcrypt}$2a$10$Adv2Bz4PzCL5.kYUK6Wx8.YmN4BGIShRhD/T18Xhcmeidr9D3NqjC}")
    private String defaultPassword;

    /***
     * 根据userCode查询User
     */
    @Override
    public User getByUserCode(String userCode) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("userCode", userCode);
        return mapper.selectOne(wrapper);
    }

    /**
     * 保存用户角色信息
     *
     * @param userCode
     * @param roles
     */
    @Override
    public Message<User> auth(String userCode, List<String> roles) {
        uerRoleService.deleteByUserCode(userCode);
        for (String roleCode : roles) {
            UserRole userRole = new UserRole();
            userRole.setRoleCode(roleCode);
            userRole.setUserCode(userCode);
            uerRoleService.save(userRole);
        }
        return Message.success();
    }

    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    @Override
    public Message<User> resetPassword(String userId) {
        User user = new User();
        user.setUserId(userId);
        user.setPassword(defaultPassword);
        return update(user);
    }
}
