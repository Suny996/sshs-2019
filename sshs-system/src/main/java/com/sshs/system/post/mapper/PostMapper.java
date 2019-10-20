package com.sshs.system.post.mapper;

import com.sshs.core.page.Page;
import com.sshs.system.post.model.Post;
import tk.mybatis.mapper.common.Mapper;


/**
* 系统管理->系统管理-岗位表类
* @author 61910
* @date 2018/11/08
*/
public interface PostMapper extends Mapper<Post> {
    public Page<Post> findForPageList();
}