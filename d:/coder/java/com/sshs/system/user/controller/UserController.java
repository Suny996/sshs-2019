package com.sshs.system.user.controller;

import com.scai.core.exception.BusinessException;
import com.scai.core.message.Message;
import com.scai.core.page.Page;
import com.sshs.system.user.model.User;
import com.sshs.system.user.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;
import com.scai.core.base.controller.BaseController;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


 /** 
 * SYS_USER->nullcontroller类
 * @author sunyonggang
 * @date 2019/11/07
 */
@RestController
@RequestMapping("/system/users")
public class UserController extends BaseController {
    @Resource
    private IUserService userService;

    Log logger = LogFactory.getLog(UserController.class);

    /**
    * 保存SYS_USER->null数据
    */
    @PostMapping
    public Mono<Message> save(@RequestBody User user) {
       try {
           logger.debug("开始保存SYS_USER->null信息……");
           return Mono.justOrEmpty(userService.save(user));
       } catch (Exception e) {
           e.printStackTrace();
           logger.error("保存SYS_USER->null信息异常！");
           throw new BusinessException("SY0001");
       }
    }

    /**
    * 修改SYS_USER->null数据
    */
    @PutMapping
    public Mono<Message> update(@RequestBody User user) {
        try {
            logger.debug("开始更新SYS_USER->null信息……");
            return Mono.justOrEmpty(userService.update(user));
        }  catch (BusinessException e) {
        throw e;
        } catch (Exception e) {
             e.printStackTrace();
             logger.error("更新SYS_USER->null信息异常！");
             throw new BusinessException("SY0002");
        }
    }
    /**
    * 根据主键删除SYS_USER->null数据
    */
    @DeleteMapping("/{userId}")
    public Mono<Message> delete(@PathVariable("userId") String userId) {
        try {
            logger.debug("开始删除SYS_USER->null信息……");
            return Mono.justOrEmpty(userService.deleteById(userId));
        } catch (BusinessException e) {
            throw e;
            }  catch (Exception e) {
            e.printStackTrace();
            logger.error("删除SYS_USER->null信息异常！");
            throw new BusinessException("SY0003");
        }
    }

   /**
   * 批量删除SYS_USER->null数据
   */
   @DeleteMapping
   public Mono<Message> delete(@RequestBody List<String> ids) {
       try {
           logger.debug("开始批量删除SYS_USER->null信息……");
           return Mono.justOrEmpty(userService.deleteByIds(ids));
       }  catch (BusinessException e) {
            throw e;
       } catch (Exception e) {
       e.printStackTrace();
       logger.error("批量删除SYS_USER->null信息异常！");
       throw new BusinessException("SY0003");
       }
   }

   /**
   * 根据主键查找SYS_USER->null信息
   */
   @GetMapping("/{userId}")
   public Mono<Message> getById(@PathVariable("userId") String userId) {
       try {
           logger.debug("开始查询SYS_USER->null信息……");
           Message message = Message.success(userService.getById(userId));
           return Mono.justOrEmpty(message);
       }  catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
       e.printStackTrace();
       logger.error("查询SYS_USER->null信息异常！");
       throw new BusinessException("SY0001");
       }
   }

   /**
   * 查询SYS_USER->null信息列表
   */
   @GetMapping
   public Mono<Message> queryList(@RequestParam (value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset, @RequestParam Map<String , Object> params) {
       try {
           logger.debug("开始查询SYS_USER->null列表信息……");
           Message message = userService.queryPageList(limit, offset, params);
           return Mono.justOrEmpty(message);
        } catch (BusinessException e) {
           throw e;
        } catch (Exception e) {
       e.printStackTrace();
       logger.error("查询SYS_USER->null信息异常！");
       throw new BusinessException("SY0001");
       }
   }


   /**
   * 分页查询SYS_USER->null信息
   */
   @PostMapping("/pageList")
   public Mono<Message> queryPageList(@RequestBody Page<User> page) {
      try {
          logger.debug("开始分页查询SYS_USER->null信息……");
          Message message = userService.queryPageList(page);
          return Mono.justOrEmpty(message);
      } catch (BusinessException e) {
          throw e;
      } catch (Exception e) {
          e.printStackTrace();
          logger.error("分页查询SYS_USER->null信息异常！");
          throw new BusinessException("SY0005");
      }
   }
}
