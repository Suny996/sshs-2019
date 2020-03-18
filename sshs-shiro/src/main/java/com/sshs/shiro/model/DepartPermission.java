package com.sshs.shiro.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 部门权限表
 * @Author: jeecg-boot
 * @Date:   2020-02-11
 * @Version: V1.0
 */
@Data
@TableName("sys_depart_permission")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sys_depart_permission对象", description="部门权限表")
public class DepartPermission {

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private String id;
	/**部门id*/
	//@Excel(name = "部门id", width = 15)
    @ApiModelProperty(value = "部门id")
	private String departId;
	/**权限id*/
	//@Excel(name = "权限id", width = 15)
    @ApiModelProperty(value = "权限id")
	private String permissionId;
	/**数据规则id*/
	@ApiModelProperty(value = "数据规则id")
	private String dataRuleIds;

	public DepartPermission() {

	}

	public DepartPermission(String departId, String permissionId) {
		this.departId = departId;
		this.permissionId = permissionId;
	}
}
