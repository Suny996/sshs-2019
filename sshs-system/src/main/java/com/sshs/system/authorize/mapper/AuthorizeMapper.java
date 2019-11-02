package com.sshs.system.authorize.mapper;

import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.system.authorize.model.Authorize;
import org.apache.ibatis.annotations.Mapper;


/**
 * 系统管理->系统管理-角色权限表类
 *
 * @author 61910
 * @date 2018/11/16
 */
@Mapper
public interface AuthorizeMapper extends BaseMapper<Authorize> {
}