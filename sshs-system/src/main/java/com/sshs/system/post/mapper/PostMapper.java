package com.sshs.system.post.mapper;

import com.sshs.core.base.mapper.BaseMapper;
import com.sshs.system.post.model.Post;
import org.apache.ibatis.annotations.Mapper;


/**
 * 系统管理->系统管理-岗位表类
 *
 * @author 61910
 * @date 2018/11/08
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {
}