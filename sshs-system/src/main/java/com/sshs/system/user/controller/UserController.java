package com.sshs.system.user.controller;

import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.system.user.model.User;
import com.sshs.system.user.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sshs.core.base.controller.BaseController;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;


/**
 * 用户管理控制类
 *
 * @author Suny
 * @date 2017年7月5日 上午10:40:10
 */
@RestController
@RequestMapping("/system/users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 保存用户
     */
    @PostMapping
    public Mono<Message> save(@RequestBody User user) {
        try {
            User u=userService.getByUserCode(user.getUserCode());
            if(u!=null){
                throw new BusinessException("US3002");
            }

                logger.debug("开始保存用户信息……");
                return Mono.justOrEmpty(userService.save(user));

        }catch (BusinessException e) {
            e.printStackTrace();
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("保存用户信息异常！");
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 修改用户
     */
    @PutMapping
    public Mono<Message> update(@RequestBody User user) {
        try {
            logger.debug("开始更新用户信息……");
            return Mono.justOrEmpty(userService.update(user));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新用户信息异常！");
            throw new BusinessException("SY0002");
        }
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{userId}")
    public Mono<Message> delete(@PathVariable("userId") String userId) {
        try {
            logger.debug("开始删除用户信息……");
            return Mono.justOrEmpty(userService.deleteById(userId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除用户信息异常！");
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 批量删除用户
     */
    @DeleteMapping
    public Mono<Message> delete(@RequestBody List<String> ids) {
        try {
            logger.debug("开始批量删除用户信息……");
            return Mono.justOrEmpty(userService.deleteByIds(ids));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("批量删除用户信息异常！");
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 根据主键查找用户信息
     */
    @GetMapping("/{userId}")
    public Mono<Message> getById(@PathVariable("userId") String userId) {
        try {
            logger.debug("开始查询用户信息……");
            Message message = Message.success(userService.getById(userId));
            return Mono.justOrEmpty(message);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询用户信息异常！");
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 查询用户信息列表
     */
    @GetMapping
    public Mono<Message> queryList(@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset, @RequestParam Map<String, Object> params) {
        try {
            logger.debug("开始查询用户列表信息……");
            Message message = userService.queryPageList(limit, offset, params);
            return Mono.justOrEmpty(message);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询用户信息异常！");
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 分页查询用户信息
     */
    @PostMapping("/pageList")
    public Mono<Message> queryPageList(@RequestBody Page<User> page) {
        try {
            logger.debug("开始分页查询用户信息……");
            Message message = userService.queryPageList(page);
            return Mono.justOrEmpty(message);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("分页查询用户信息异常！");
            throw new BusinessException("SY0005");
        }
    }
}
