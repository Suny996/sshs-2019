package com.sshs.security.service;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.customise.mapper.CommonMapper;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.util.BusiUtil;
import com.sshs.core.util.SystemUtil;
import com.sshs.security.error.SecurityErrorCode;
import com.sshs.security.mapper.SecurityUserMapper;
import com.sshs.security.model.Privilege;
import com.sshs.security.model.SecurityUser;
import com.sshs.security.model.SshsGrantedAuthority;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Suny
 * @date 2018/12/28.
 */
@Service("sshsUserService")
public class SshsUserServiceImpl extends BaseServiceImpl<SecurityUserMapper, SecurityUser> implements UserDetailsService {
    final static Logger logger = LoggerFactory.getLogger(SshsUserServiceImpl.class);
    @Resource
    SecurityUserMapper SecurityUserMapper;
    @Resource
    CommonMapper commonMapper;
    @Resource
    private PrivilegeService privilegeService;
    @Value("${core.defaultPassword:{bcrypt}$2a$10$Adv2Bz4PzCL5.kYUK6Wx8.YmN4BGIShRhD/T18Xhcmeidr9D3NqjC}")
    private String defaultPassword;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new BusinessException(SecurityErrorCode.USERNAME_CAN_NOT_NULL);
        }
        SecurityUser userDetails;
        List<SecurityUser> list = SecurityUserMapper.findSecurityUserByUserName(username);
        if (list == null || list.isEmpty()) {
            throw new BusinessException(SecurityErrorCode.USER_NOT_EXISTS);
        }
        SecurityUser user = list.get(0);
        userDetails = new SecurityUser(user.getUsername(), user.getUserNameCn(), user.getPassword(), user.getOrgCode(), true, true, true, true,
                findUserAuthorities(user.getUsername()));
        return userDetails;
    }

    /**
     * 根据用户编号获取角色信息
     *
     * @param userName
     * @return
     */
    public Collection<GrantedAuthority> findUserAuthorities(String userName) {
        // 定义一个接收GrantedAuthority类型的List
        List<GrantedAuthority> autthorities = new ArrayList<GrantedAuthority>();
        // 查询一个user的所有的权限
        List<Privilege> privilegeList = null;
        try {
            privilegeList = privilegeService.findPrivilegeByUserId(userName);
            if (privilegeList == null || privilegeList.isEmpty()) {
                throw new BusinessException(SecurityErrorCode.NO_PRIVILEGE_FOUND);
            } else {
                SshsGrantedAuthority sga = new SshsGrantedAuthority(privilegeList);
                Map<String, List<Map<String, Object>>> menuButtons = findUserMenus(userName, null);
                List<Map<String, Object>> menus = menuButtons.get("menus");
                List<Map<String, Object>> buttons = menuButtons.get("buttons");
                sga.setMenus(menus);
                if (buttons == null || buttons.isEmpty()) {
                    sga.setButtons(new HashMap(1));
                } else {
                    sga.setButtons(buttons.get(0));
                }
                autthorities.add(sga);
                return autthorities;
            }
        } catch (Exception e) {
            logger.error("验证身份异常", e);
            throw new BusinessException(SecurityErrorCode.AUTHORISE_EXCEPTION);
        }
    }

    /**
     * 根据用户编号获取角色信息
     *
     * @param userName
     * @return
     */
    public Map<String, List<Map<String, Object>>> findUserMenus(String userName, String locale) {
        if (StringUtils.isEmpty(locale)) {
            locale = SystemUtil.getLocale();
        }
        if (StringUtils.isNotEmpty(locale)) {
            locale = locale.replace("-", "_");
        }
        String language = locale.split("_")[0];
        String country = locale.split("_")[1];
        Map<String, List<Map<String, Object>>> data = new HashMap<String, List<Map<String, Object>>>();
        initMenu(data, null, userName, "0", language, country);
        return data;
    }

    /**
     * 初始化菜单
     *
     * @param userName
     * @param parentId
     * @return
     */
    void initMenu(Map<String, List<Map<String, Object>>> data, Map<String, Object> currentMenu, String userName, String parentId, String language, String country) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("parentId", parentId);
        params.put("userCode", userName);
        params.put("language", language);
        params.put("country", country);
        List<Map<String, Object>> userMenus = commonMapper.findUserMenus(params);
        List<Map<String, Object>> menus = new ArrayList<>();
        for (Map<String, Object> menu : userMenus) {
            menu = BusiUtil.tranMapKey(menu, "1");
            menus.add(menu);
        }
        if (currentMenu == null || "0".equalsIgnoreCase((String) currentMenu.get("type"))) {
            if (data.isEmpty() || data.get("menus") == null) {
                data.put("menus", menus);
            } else {
                currentMenu.put("children", menus);
            }
            for (Map<String, Object> menu : menus) {
                initMenu(data, menu, userName, (String) menu.get("code"), language, country);
            }
        } else if ("1".equalsIgnoreCase((String) currentMenu.get("type"))) {
            List<Map<String, Object>> buttons = data.get("buttons");
            if (buttons == null) {
                buttons = new ArrayList<>();
                buttons.add(new HashMap<>());
                data.put("buttons", buttons);
            }
            String menuUrl = (String) currentMenu.get("name");
            for (Map<String, Object> menu : menus) {
                //String code = (String) menu.get("code");
                //String type = (String) menu.get("type");
                String url = (String) menu.get("name");
                buttons.get(0).put(menuUrl + "/" + url, "1");
            }
        }
    }
}
