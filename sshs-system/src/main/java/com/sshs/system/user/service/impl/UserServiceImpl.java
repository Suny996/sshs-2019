package com.sshs.system.user.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.core.util.UuidUtil;
import com.sshs.system.user.mapper.UserMapper;
import com.sshs.system.user.model.User;
import com.sshs.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 系统用户实现类
 *
 * @author barry.wang
 * @date 2017年7月5日 上午9:46:09
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
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
    public User getByUserCode(String userCode){
        return mapper.getByUserCode(userCode);
    }

    /**
     * 保存方法
     *
     * @param user
     * @return
     */
    @Override
    public Message save(User user) {
        user.setUserId(UuidUtil.get32UUID());
        user.setPassword(defaultPassword);
        try {
            return super.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存用户信息异常！");
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 分页查询用户信息
     */
    @Override
    public Message queryPageList(String limit, String offset, Map<String, Object> parameter) {
        if (StringUtil.isEmpty(limit)) {
            return Message.success(findForList("com.sshs.system.user.mapper.UserMapper.findForList", parameter));
        } else {
            Page<User> page = new Page<User>(Integer.valueOf(limit, 10), Integer.valueOf(offset, 10), parameter);
            return queryPageList(page);
        }
    }

    /**
     * 分页查询用户信息
     */
    @Override
    public Message queryPageList(Page<User> page) {
        return findForPageList("com.sshs.system.user.mapper.UserMapper.findForPageList", page);
    }
}
