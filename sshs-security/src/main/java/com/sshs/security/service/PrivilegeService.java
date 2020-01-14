package com.sshs.security.service;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.message.Message;
import com.sshs.security.mapper.PrivilegeMapper;
import com.sshs.security.model.Privilege;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Suny
 * @date 2018-01-28
 */
@Service
public class PrivilegeService extends BaseServiceImpl<PrivilegeMapper, Privilege> {

    @Resource
    private PrivilegeMapper privilegeMapper;

    public Message<List<Privilege>> findPrivilegeByUserId(String userId) throws Exception {
        return Message.success(privilegeMapper.findPrivilegeListByUserId(userId));
    }

    public Message<List<Privilege>>  findPrivilegeByUrl(String url) throws Exception {
        return Message.success(privilegeMapper.findPrivilegeListByUrl(url));
    }
}
