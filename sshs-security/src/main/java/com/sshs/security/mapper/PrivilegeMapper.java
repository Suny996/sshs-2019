package com.sshs.security.mapper;

import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.security.model.Privilege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 安全管理->安全管理-权限表dao接口类
 *
 * @author Suny
 * @date 2018/01/09
 */
@Mapper
public interface PrivilegeMapper extends BaseMapper<Privilege> {
    List<Privilege> findPrivilegeListByUserId(@Param("userId") String userId);
    List<Privilege> findPrivilegeListByUrl(@Param("url") String url);
}