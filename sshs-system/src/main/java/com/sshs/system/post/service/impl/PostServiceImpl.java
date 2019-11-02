package com.sshs.system.post.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.util.UuidUtil;
import com.sshs.system.post.mapper.PostMapper;
import com.sshs.system.post.model.Post;
import com.sshs.system.post.service.IPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 系统管理->系统管理-岗位表service实现类
 *
 * @author 61910
 * @date 2018/11/08
 */
@Service("postService")
public class PostServiceImpl extends BaseServiceImpl<Post> implements IPostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    @Resource
    private PostMapper mapper;

    /**
     * 保存系统管理->系统管理-岗位表数据方法
     *
     * @param post
     * @return Message
     */
    @Override
    public Message save(Post post) {
        post.setPostId(UuidUtil.get32UUID());
        try {
            return super.save(post);
        } catch (Exception e) {
            logger.error("保存系统管理->系统管理-岗位表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

}