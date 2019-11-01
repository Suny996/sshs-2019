package com.sshs.system.org.mapper;

import com.sshs.core.page.Page;
import com.sshs.system.org.model.Org;
import com.sshs.core.base.mapper.BaseMapper;

import java.util.List;


/**
 * 系统管理->系统管理-机构表类
 *
 * @author 61910
 * @date 2018/11/07
 */
public interface OrgMapper extends BaseMapper<Org> {
    Page<Org> findForPageList();

    Org findOrgById(String rootId);

    List<Org> findOrgAll();

    String findLastChildCodeById(String orgCode);
}