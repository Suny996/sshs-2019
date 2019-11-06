package com.sshs.system.user.controller;

import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.system.user.model.User;
import com.sshs.system.user.service.IUserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 用户管理控制类
 *
 * @author Suny
 * @date 2017年7月5日 上午10:40:10
 */
@Api(tags = "系统管理-用户管理")
@RestController
@RequestMapping("/api/v1/system/users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 保存用户
     */
    @PostMapping
    public Message save(@RequestBody User user) {
        try {
            User u = userService.getByUserCode(user.getUserCode());
            if (u != null) {
                logger.error("保存用户信息异常！");
                throw new BusinessException("US3002");
            }
            logger.debug("开始保存用户信息……");
            return userService.save(user);

        } catch (BusinessException e) {
            logger.error("保存用户信息异常！", e);
            throw e;
        } catch (Exception e) {
            logger.error("保存用户信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 修改用户
     */
    @PutMapping
    public Message update(@RequestBody User user) {
        try {
            logger.debug("开始更新用户信息……");
            return userService.update(user);
        } catch (Exception e) {
            logger.error("更新用户信息异常！", e);
            throw new BusinessException("SY0002");
        }
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{userId}")
    public Message delete(@PathVariable("userId") String userId) {
        try {
            logger.debug("开始删除用户信息……");
            return userService.deleteById(userId);
        } catch (Exception e) {
            logger.error("删除用户信息异常！", e);
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 批量删除用户
     */
    @DeleteMapping
    public Message delete(@RequestBody List<String> ids) {
        try {
            logger.debug("开始批量删除用户信息……");
            return userService.deleteByIds(ids);
        } catch (Exception e) {
            logger.error("批量删除用户信息异常！", e);
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 根据主键查找用户信息
     */
    @GetMapping("/{userId}")
    public Message getById(@PathVariable("userId") String userId) {
        try {
            logger.debug("开始查询用户信息……");
            return Message.success(userService.getById(userId));
        } catch (Exception e) {
            logger.error("查询用户信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 查询用户信息列表
     */
    @GetMapping
    public Message<Page<User>> queryList(@RequestParam(value = "limit", required = false) int limit, @RequestParam(value = "offset", required = false) int offset, @RequestParam(required = false) Map<String, Object> params) {
        try {
            logger.debug("开始查询用户列表信息……");
            return userService.queryPageList(limit, offset, params);
        } catch (Exception e) {
            logger.error("查询用户信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

}
