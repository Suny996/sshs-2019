package com.sshs.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sshs.shiro.model.PermissionDataRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限规则 Mapper 接口
 * </p>
 *
 * @Author huangzhilin
 * @since 2019-04-01
 */
@Mapper
public interface PermissionDataRuleMapper extends BaseMapper<PermissionDataRule> {

	/**
	  * 根据用户名和权限id查询
	 * @param username
	 * @param permissionId
	 * @return
	 */
	public List<String> queryDataRuleIds(@Param("username") String username, @Param("permissionId") String permissionId);

}
