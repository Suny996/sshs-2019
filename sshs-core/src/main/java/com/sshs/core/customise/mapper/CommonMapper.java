package com.sshs.core.customise.mapper;

import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.core.customise.model.Customise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通用mapper接口
 *
 * @author Suny
 * @date 2017-10-15
 */
@Mapper
public interface CommonMapper extends BaseMapper<Customise> {
    /**
     * 根据机构号查询其下级机构列表
     *
     * @param orgCode
     * @return
     */
    List<Map<String, Object>> findOrgListByOrgCode(String orgCode);

    /**
     * 根据机构号查询机构信息
     *
     * @param orgCode
     * @return
     */
    Map<String, Object> findOrgInfoByOrgCode(String orgCode);

    /**
     * 根据机构号查询上级机构
     *
     * @param orgCode
     * @return
     */
    Map<String, Object> findParentOrgByOrgCode(String orgCode);

    /**
     * 查询岗位列表
     *
     * @return
     */
    List<Map<String, Object>> findPosts();

    /**
     * 查询用户菜单树
     *
     * @param params
     * @return
     */
    List<Map<String, Object>> findUserMenus(Map<String, Object> params);

    /**
     * 根据用户ID查询用户密码信息
     *
     * @param username
     * @return
     */
    String getPasswordByUserName(@Param("username") String username);

    /**
     * 根据用户ID修改用户密码信息
     *
     * @param username
     * @param password
     * @return
     */
    int setNewPassword(@Param("username") String username, @Param("password") String password, @Param("usercode") String usercode, @Param("date") Date date);

    /**
     * 根据用户编号及资源ID查询数据权限
     *
     * @param
     * @return
     */
    String getDataAuthByUserCode(@Param("url") String url, @Param("userCode") String userCode);


    /**
     * 保存操作日志
     *
     * @param log
     * @return
     */
    //void saveLog(SysLog log);
}
