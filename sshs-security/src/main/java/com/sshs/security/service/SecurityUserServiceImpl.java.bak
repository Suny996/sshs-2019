package com.sshs.security.service;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.security.mapper.SecurityUserMapper;
import com.sshs.security.model.Privilege;
import com.sshs.security.model.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Suny
 * @date 2018-01-02
 */
//@Component
public class SecurityUserServiceImpl extends BaseServiceImpl<SecurityUserMapper,SecurityUser> implements ReactiveUserDetailsService {
    @Resource
    private PrivilegeService privilegeService;

    @Resource
    SecurityUserMapper securityUserMapper;

    @Override
    public Mono<UserDetails> findByUsername(String username) throws UsernameNotFoundException {
        SecurityUser userDetails;
        List<SecurityUser> list = (List<SecurityUser>) securityUserMapper.findSecurityUserByUserName(username);
        if (list == null || list.isEmpty()) {
            throw new BusinessException("US3000");
        }
        SecurityUser user = list.get(0);
        userDetails = new SecurityUser(user.getUsername(),user.getUserNameCn(), user.getPassword(), user.getOrgCode(), true, true, true, true,
                findUserAuthorities(user.getUsername()));
        return Mono.justOrEmpty(userDetails);
    }

    public Collection<GrantedAuthority> findUserAuthorities(String userName) {
        // 定义一个接收GrantedAuthority类型的List
        List<GrantedAuthority> autthorities = new ArrayList<GrantedAuthority>();
        // 查询一个user的所有的权限
        List<Privilege> privilegeList = null;
        try {
            Message message = privilegeService.findPrivilegeByUserId(userName);
            Object data = message.getData();
            if (data == null) {
                throw new BusinessException("US4000");
            } else {
                privilegeList = (List<Privilege>) message.getData();
            }
            if (privilegeList == null || privilegeList.size() == 0) {
                throw new BusinessException("US4000");
            } else {
                for (Privilege p : privilegeList) {
                    autthorities.add(new SimpleGrantedAuthority(p.getId()));
                }
                return autthorities;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("US4000");
        }
    }
}
