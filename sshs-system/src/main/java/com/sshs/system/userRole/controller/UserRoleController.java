package com.sshs.system.userRole.controller;

import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.system.user.model.User;
import com.sshs.system.userRole.model.UserRole;
import com.sshs.system.userRole.service.IUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.sshs.core.base.controller.BaseController;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


 /** 
 * 系统管理->系统管理-用户角色对照表controller类
 * @author 61910
 * @date 2018/11/16
 */
@RestController
@RequestMapping("/system/userRoles")
public class UserRoleController extends BaseController {
    @Resource
    private IUserRoleService userRoleService;

    Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    /**
    * 保存系统管理->系统管理-用户角色对照表数据
    */
    @PostMapping
    public Mono<Message> save(@RequestBody User user) {
       try {
           logger.debug("开始保存系统管理->系统管理-用户角色对照表信息……");
           return Mono.justOrEmpty(userRoleService.save(user));
       } catch (Exception e) {
           e.printStackTrace();
           logger.error("保存系统管理->系统管理-用户角色对照表信息异常！");
           throw new BusinessException("SY0001");
       }
    }

    /**
    * 修改系统管理->系统管理-用户角色对照表数据
    */
    @PutMapping
    public Mono<Message> update(@RequestBody UserRole userRole) {
        try {
            logger.debug("开始更新系统管理->系统管理-用户角色对照表信息……");
            return Mono.justOrEmpty(userRoleService.update(userRole));
        } catch (Exception e) {
             e.printStackTrace();
             logger.error("更新系统管理->系统管理-用户角色对照表信息异常！");
             throw new BusinessException("SY0002");
        }
    }
    /**
    * 根据主键删除系统管理->系统管理-用户角色对照表数据
    */
    @DeleteMapping("/{userRoleId}")
    public Mono<Message> delete(@PathVariable("userRoleId") String userRoleId) {
        try {
            logger.debug("开始删除系统管理->系统管理-用户角色对照表信息……");
            return Mono.justOrEmpty(userRoleService.deleteById(userRoleId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除系统管理->系统管理-用户角色对照表信息异常！");
            throw new BusinessException("SY0003");
        }
    }

   /**
   * 批量删除系统管理->系统管理-用户角色对照表数据
   */
   @DeleteMapping
   public Mono<Message> delete(@RequestBody List<String> ids) {
       try {
           logger.debug("开始批量删除系统管理->系统管理-用户角色对照表信息……");
           return Mono.justOrEmpty(userRoleService.deleteByIds(ids));
       } catch (Exception e) {
       e.printStackTrace();
       logger.error("批量删除系统管理->系统管理-用户角色对照表信息异常！");
       throw new BusinessException("SY0003");
       }
   }

   /**
   * 根据主键查找系统管理->系统管理-用户角色对照表信息
   */
   @GetMapping("/{userRoleId}")
   public Mono<Message> getById(@PathVariable("userRoleId") String userRoleId) {
       try {
           logger.debug("开始查询系统管理->系统管理-用户角色对照表信息……");
           Message message = Message.success(userRoleService.getById(userRoleId));
           return Mono.justOrEmpty(message);
       } catch (Exception e) {
       e.printStackTrace();
       logger.error("查询系统管理->系统管理-用户角色对照表信息异常！");
       throw new BusinessException("SY0001");
       }
   }

   /**
   * 查询系统管理->系统管理-用户角色对照表信息列表
   */
   @GetMapping
   public Mono<Message> queryList(@RequestParam Map<String , Object> params) {
       try {
           logger.debug("开始查询系统管理->系统管理-用户角色对照表列表信息……");
           Message message = userRoleService.queryForList(params);
           return Mono.justOrEmpty(message);
        } catch (Exception e) {
       e.printStackTrace();
       logger.error("查询系统管理->系统管理-用户角色对照表信息异常！");
       throw new BusinessException("SY0001");
       }
   }


   /**
   * 分页查询系统管理->系统管理-用户角色对照表信息
   */
   @PostMapping("/pageList")
   public Mono<Message> queryPageList(@RequestBody Page<UserRole> page) {
      try {
          logger.debug("开始分页查询系统管理->系统管理-用户角色对照表信息……");
          Message message = userRoleService.queryPageList(page);
          return Mono.justOrEmpty(message);
      } catch (Exception e) {
          e.printStackTrace();
          logger.error("分页查询系统管理->系统管理-用户角色对照表信息异常！");
          throw new BusinessException("SY0005");
      }
   }
}
