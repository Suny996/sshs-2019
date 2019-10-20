package com.sshs.system.userRole.service;

import com.sshs.core.base.service.IBaseService;
import com.sshs.core.message.Message;
import com.sshs.system.user.model.User;
import com.sshs.system.userRole.model.UserRole;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 系统管理->系统管理-用户角色对照表service类
 *
 * @author 61910
 * @date 2018/11/16
 */
public interface IUserRoleService extends IBaseService<UserRole> {
    public Message save(User user);
    public Message queryForList(Map<String , Object> params);
}