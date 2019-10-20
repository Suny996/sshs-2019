package com.sshs.system.post.controller;

import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.system.post.model.Post;
import com.sshs.system.post.service.IPostService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;
import com.sshs.core.base.controller.BaseController;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


 /** 
 * 系统管理->系统管理-岗位表controller类
 * @author 61910
 * @date 2018/11/08
 */
@RestController
@RequestMapping("/system/posts")
public class PostController extends BaseController {
    @Resource
    private IPostService postService;

    Logger logger = LoggerFactory.getLogger(PostController.class);

    /**
    * 保存系统管理->系统管理-岗位表数据
    */
    @PostMapping
    public Mono<Message> save(@RequestBody Post post) {
       try {
           logger.debug("开始保存系统管理->系统管理-岗位表信息……");
           return Mono.justOrEmpty(postService.save(post));
       } catch (Exception e) {
           e.printStackTrace();
           logger.error("保存系统管理->系统管理-岗位表信息异常！");
           throw new BusinessException("SY0001");
       }
    }

    /**
    * 修改系统管理->系统管理-岗位表数据
    */
    @PutMapping
    public Mono<Message> update(@RequestBody Post post) {
        try {
            logger.debug("开始更新系统管理->系统管理-岗位表信息……");
            return Mono.justOrEmpty(postService.update(post));
        } catch (Exception e) {
             e.printStackTrace();
             logger.error("更新系统管理->系统管理-岗位表信息异常！");
             throw new BusinessException("SY0002");
        }
    }
    /**
    * 根据主键删除系统管理->系统管理-岗位表数据
    */
    @DeleteMapping("/{postId}")
    public Mono<Message> delete(@PathVariable("postId") String postId) {
        try {
            logger.debug("开始删除系统管理->系统管理-岗位表信息……");
            return Mono.justOrEmpty(postService.deleteById(postId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除系统管理->系统管理-岗位表信息异常！");
            throw new BusinessException("SY0003");
        }
    }

   /**
   * 批量删除系统管理->系统管理-岗位表数据
   */
   @DeleteMapping
   public Mono<Message> delete(@RequestBody List<String> ids) {
       try {
           logger.debug("开始批量删除系统管理->系统管理-岗位表信息……");
           return Mono.justOrEmpty(postService.deleteByIds(ids));
       } catch (Exception e) {
       e.printStackTrace();
       logger.error("批量删除系统管理->系统管理-岗位表信息异常！");
       throw new BusinessException("SY0003");
       }
   }

   /**
   * 根据主键查找系统管理->系统管理-岗位表信息
   */
   @GetMapping("/{postId}")
   public Mono<Message> getById(@PathVariable("postId") String postId) {
       try {
           logger.debug("开始查询系统管理->系统管理-岗位表信息……");
           Message message = Message.success(postService.getById(postId));
           return Mono.justOrEmpty(message);
       } catch (Exception e) {
       e.printStackTrace();
       logger.error("查询系统管理->系统管理-岗位表信息异常！");
       throw new BusinessException("SY0001");
       }
   }

   /**
   * 查询系统管理->系统管理-岗位表信息列表
   */
   @GetMapping
   public Mono<Message> queryList(@RequestParam (value = "limit", required = false) String limit, @RequestParam String offset, @RequestParam Map<String , Object> params) {
       try {
           logger.debug("开始查询系统管理->系统管理-岗位表列表信息……");
           Message message = postService.queryPageList(limit, offset, params);
           return Mono.justOrEmpty(message);
        } catch (Exception e) {
       e.printStackTrace();
       logger.error("查询系统管理->系统管理-岗位表信息异常！");
       throw new BusinessException("SY0001");
       }
   }


   /**
   * 分页查询系统管理->系统管理-岗位表信息
   */
   @PostMapping("/pageList")
   public Mono<Message> queryPageList(@RequestBody Page<Post> page) {
      try {
          logger.debug("开始分页查询系统管理->系统管理-岗位表信息……");
          Message message = postService.queryPageList(page);
          return Mono.justOrEmpty(message);
      } catch (Exception e) {
          e.printStackTrace();
          logger.error("分页查询系统管理->系统管理-岗位表信息异常！");
          throw new BusinessException("SY0005");
      }
   }
}
