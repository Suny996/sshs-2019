package com.sshs.system.org.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.message.Message;
import com.sshs.core.util.SystemUtil;
import com.sshs.system.org.mapper.OrgMapper;
import com.sshs.system.org.model.Org;
import com.sshs.system.org.service.IOrgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 系统管理->系统管理-机构表Service实现类
 *
 * @author 61910
 * @date 2018/11/07
 */
@Service("orgService")
public class OrgServiceImpl extends BaseServiceImpl<Org> implements IOrgService {
    Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class);
    @Resource
    private OrgMapper mapper;


    /**
     * 查询指定orgCode及向下菜单树
     *
     * @param rootId
     * @return
     */
    @Override
    public Message getOrgTree(String rootId) {
        if ("0".equalsIgnoreCase(rootId)) {
            rootId = SystemUtil.getAuthRootOrgCode();
        }
        Org org = mapper.findOrgById(rootId);
        if (org != null) {
            List<Org> orgs = mapper.findOrgAll();
            org = initChildren(org, orgs);
        }
        return Message.success(org);
    }

    /**
     * 初始化子节点
     *
     * @param org
     * @param orgs
     * @return
     */
    private Org initChildren(Org org, List<Org> orgs) {
        for (Org o : orgs) {
            if (org.getOrgCode().equals(o.getParentOrgCode())) {
                o = initChildren(o, orgs);
                org.addChild(o);
            }
        }
        return org;
    }

    @Override
    public Org findOrgById(String orgCode) {
        return mapper.findOrgById(orgCode);
    }


}
