package com.sshs.system.org.mapper;

import com.sshs.core.page.Page;
import com.sshs.system.org.model.Org;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
* 系统管理->系统管理-机构表类
* @author 61910
* @date 2018/11/07
*/
public interface OrgMapper extends Mapper<Org> {
    public Page<Org> findForPageList();
    public Org findOrgById(String rootId);
    public List<Org> findOrgAll();
    public String findLastChildCodeById(String orgCode);
}