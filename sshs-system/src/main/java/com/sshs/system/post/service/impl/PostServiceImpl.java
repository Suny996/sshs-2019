package com.sshs.system.post.service.impl;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.core.util.UuidUtil;
import com.sshs.system.post.mapper.PostMapper;
import com.sshs.system.post.model.Post;
import com.sshs.system.post.service.IPostService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hsqldb.lib.StringUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Map;


 /**
 * 系统管理->系统管理-岗位表service实现类
 * @author 61910
 * @date 2018/11/08
 */
@Service("postService")
public class PostServiceImpl extends BaseServiceImpl<Post> implements IPostService {
    Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    @Resource
    private PostMapper mapper;

   /**
   * 保存系统管理->系统管理-岗位表数据方法
   * @param post
   * @return Message
   */
   @Override
   public Message save(Post post){
       post.setPostId(UuidUtil.get32UUID());
       try {
           return super.save(post);
       } catch (Exception e) {
           e.printStackTrace();
           logger.error("保存系统管理->系统管理-岗位表信息异常！");
           throw new BusinessException("SY0001");
       }
   }

   /**
   * 查询系统管理->系统管理-岗位表列表信息
   * @param limit
   */
   @Override
   public Message queryPageList(String limit, String offset, Map<String, Object> parameter) {
      if (StringUtil.isEmpty(limit)) {
         return Message.success(findForList("com.sshs.system.post.mapper.PostMapper.findForList", parameter));
      } else {
         Page<Post> page = new Page<Post>(Integer.valueOf(limit, 10), Integer.valueOf(offset, 10), parameter);
         return queryPageList(page);
      }
   }

   /**
   * 分页查询系统管理->系统管理-岗位表信息
   */
   @Override
   public Message queryPageList(Page<Post> page) {
      return findForPageList("com.sshs.system.post.mapper.PostMapper.findForPageList", page);
      }
   }
