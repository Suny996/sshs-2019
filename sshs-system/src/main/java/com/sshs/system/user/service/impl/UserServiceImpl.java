package com.sshs.system.user.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.system.user.mapper.UserMapper;
import com.sshs.system.user.model.User;
import com.sshs.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;


/**
 * 系统用户实现类
 *
 * @author barry.wang
 * @date 2017年7月5日 上午9:46:09
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper mapper;
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
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userCode", userCode);
        return mapper.selectOneByExample(example);
    }

    /**
     * 保存方法
     *
     * @param user
     * @return
     */
    @Override
    public Message<User> save(User user) {
        user.setPassword(defaultPassword);
        try {
            return super.save(user);
        } catch (Exception e) {
            logger.error("保存用户信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }
}
