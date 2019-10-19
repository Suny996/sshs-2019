package com.sshs.core.customise.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sshs.core.aop.SysLog;
import com.sshs.core.customise.model.Customise;
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
public interface CommonMapper extends BaseMapper<Customise> {
    /**
     * 根据机构号查询其下级机构列表
     *
     * @param orgCode
     * @return
     */
    public List<Map<String, Object>> findOrgListByOrgCode(String orgCode);

    /**
     * 根据机构号查询机构信息
     *
     * @param orgCode
     * @return
     */
    public Map<String, Object> findOrgInfoByOrgCode(String orgCode);

    /**
     * 根据机构号查询上级机构
     *
     * @param orgCode
     * @return
     */
    public Map<String, Object> findParentOrgByOrgCode(String orgCode);

    /**
     * 查询岗位列表
     *
     * @return
     */
    public List<Map<String, Object>> findPosts();

    /**
     * 查询用户菜单树
     *
     * @param params
     * @return
     */
    public List<Map<String, Object>> findUserMenus(Map<String, Object> params);

    /**
     * 根据用户ID查询用户密码信息
     *
     * @param username
     * @return
     */
    public String getPasswordByUserName(@Param("username") String username);

    /**
     * 根据用户ID修改用户密码信息
     *
     * @param username
     * @param password
     * @return
     */
    public int setNewPassword(@Param("username") String username, @Param("password") String password, @Param("usercode") String usercode, @Param("date") Date date);

    /**
     * 根据用户编号及资源ID查询数据权限
     *
     * @param
     * @return
     */
    public String getDataAuthByUserCode(@Param("url") String url, @Param("userCode") String userCode);


    /**
     * 保存操作日志
     *
     * @param log
     * @return
     */
    public void saveLog(SysLog log);
}
