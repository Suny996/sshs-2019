package com.sshs.security.mapper;

import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.security.model.SecurityUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 安全管理->安全管理-用户表dao接口类
 *
 * @author Suny
 * @date 2018/01/09
 */
@Mapper
public interface SecurityUserMapper extends BaseMapper<SecurityUser> {
    List<SecurityUser> findSecurityUserByUserName(@Param("userId") String userId);
}