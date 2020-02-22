package com.sshs.core.util;

import com.sshs.core.base.model.GlobalUser;
import com.sshs.core.constant.Global;
import com.sshs.core.customise.mapper.CommonMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Suny
 * @date 2019-1-21
 */
public class SystemUtil {
    static CommonMapper commonMapper;

    //static HttpServletRequest request;

    static {
        commonMapper = (CommonMapper) com.sshs.core.util.SpringUtil.getBean("commonMapper");
        //request = (HttpServletRequest) SpringUtil.getBean(HttpServletRequest.class);
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static GlobalUser getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (GlobalUser) request.getSession().getAttribute(Global.USER);
    }

    /**
     * 获取数据权限类型
     *
     * @return
     */
    public static String getAuthType() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getHeader("client_page");
        String dataAuthType = commonMapper.getDataAuthByUserCode(url, getCurrentUser().getUserCode());
        if (StringUtils.isEmpty(dataAuthType)) {
            Map<String, Object> org = commonMapper.findOrgInfoByOrgCode(getCurrentUser().getOrgCode());
            dataAuthType = (String) org.get("ORG_LEVEL");
        }
        return dataAuthType;
    }

    /**
     * 查询当前用户机构权限sql串
     *
     * @return
     */
    public static String getOrgAuthStatement() {
        String dataAuthType = getAuthType();
        if ("0".equals(dataAuthType)) {
            return " !='-_-'";
        } else {
            return getWhereSQL(getAuthOrgCodes());
        }
        // return " = '" + getCurrentUser().getOrgCode() + "'";
    }

    /**
     * 根据数据集拼接where片段
     *
     * @param authDatas
     * @return
     */
    public static String getWhereSQL(List<String> authDatas) {
        StringBuffer filterBuffer = new StringBuffer();
        if (authDatas != null) {
            filterBuffer.append(" in (");
            for (String inStr : authDatas) {
                filterBuffer.append("'" + inStr + "',");
            }
            filterBuffer.append("'-_-')");
        }
        return filterBuffer.toString();
    }

    /**
     * 根据所属机构号和权限层级查询所有有权限的顶层机构编号
     *
     * @return
     */
    public static String getAuthRootOrgCode() {
        String orgCode = getCurrentUser().getOrgCode();
        String dataAuthType = getAuthType();
        Map<String, Object> org = commonMapper.findOrgInfoByOrgCode(orgCode);
        while (dataAuthType.compareTo((String) org.get("ORG_LEVEL")) < 0 && !"0".equalsIgnoreCase((String) org.get("PARENT_ORG_CODE"))) {
            org = commonMapper.findParentOrgByOrgCode((String) org.get("ORG_CODE"));
        }
        return (String) org.get("ORG_CODE");
    }


    /**
     * 根据所属机构号和权限层级查询所有有权限的机构编号
     *
     * @return
     */
    public static List<String> getAuthOrgCodes() {
        List<String> orgCodes = new ArrayList<>();
        String rootOrgCode = getAuthRootOrgCode();
        orgCodes.add(rootOrgCode);
        orgCodes = getChildrenOrgs(rootOrgCode, orgCodes);
        return orgCodes;
    }

    /**
     * 查询所有下级机构信息
     *
     * @param orgCode
     * @param orgs
     * @return
     */
    public static List<String> getChildrenOrgs(String orgCode, List<String> orgs) {
        List<Map<String, Object>> children = commonMapper.findOrgListByOrgCode(orgCode);
        for (Map<String, Object> child : children) {
            String code = (String) child.get("ORG_CODE");
            orgs.add(code);
            getChildrenOrgs(code, orgs);
        }
        return orgs;
    }

    /**
     * 获取当前语言
     *
     * @return
     */
    public static String getLocale() {
        String locale = null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            locale = request.getParameter("locale");
            if (StringUtils.isBlank(locale)) {
                locale = (String) request.getAttribute("locale");
            }

            if (StringUtils.isBlank(locale)) {
                locale = request.getHeader("locale");
            }
            if (StringUtils.isBlank(locale)) {
                locale = request.getLocale().toString();
            }
        }
        if (StringUtils.isBlank(locale)) {
            locale = "zh_CN";
        }
        locale = locale.replace("-", "_");
        return locale;
    }

    /**
     * 获取客户端IP
     *
     * @return
     */
    public static String getRemoteId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        return request.getRemoteHost();
    }

    /**
     * 获取文件路径
     *
     * @return
     */
    public static String getRootPath(String path) {
        if (path != null && path.startsWith("/")) {
            return path;
        } else {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String root = request.getServletContext().getRealPath(path);
            if (StringUtils.isBlank(root)) {
                root = path;
            }
            return root;
        }
    }
}
