package com.sshs.system.post.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.system.post.mapper.PostMapper;
import com.sshs.system.post.model.Post;
import com.sshs.system.post.service.IPostService;
import org.springframework.stereotype.Service;


/**
 * 系统管理->系统管理-岗位表service实现类
 *
 * @author 61910
 * @date 2018/11/08
 */
@Service("postService")
public class PostServiceImpl extends BaseServiceImpl<PostMapper,Post> implements IPostService {
    //private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
}