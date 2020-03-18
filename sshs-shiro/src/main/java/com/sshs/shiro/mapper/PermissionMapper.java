package com.sshs.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sshs.shiro.model.Permission;
import com.sshs.shiro.model.TreeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 通过父菜单ID查询子菜单
     *
     * @param parentId
     * @return
     */
    public List<TreeModel> queryListByParentId(@Param("parentId") String parentId);

    /**
     * 根据用户查询用户权限
     */
    public List<Permission> queryByUser(@Param("username") String username);

    /**
     * 修改菜单状态字段： 是否子节点
     */
    @Update("update sys_permission set is_leaf=#{leaf} where id = #{id}")
    public int setMenuLeaf(@Param("id") String id, @Param("leaf") int leaf);

    /**
     * 获取模糊匹配规则的数据权限URL
     */
    @Select("SELECT url FROM sys_permission WHERE del_flag = 0 and menu_type = 2 and url like '%*%'")
    public List<String> queryPermissionUrlWithStar();

}
