package com.sshs.system.org.service;

import com.sshs.core.base.service.IBaseService;
import com.sshs.core.message.Message;
import com.sshs.system.org.model.Org;

 /**
 * 系统管理->系统管理-机构表service类
 * @author 61910
 * @date 2018/11/07
 */
public interface IOrgService extends IBaseService<Org> {
      public Message getOrgTree(String rootId);

      public Org findOrgById(String orgCode);
    }