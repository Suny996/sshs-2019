package com.sshs.system.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.system.user.model.User;
import com.sshs.system.user.service.IUserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @ApiOperation(value = "用户管理新增", nickname = "save", notes = "Adds an User to the system", tags = {"save",})
    @PostMapping(consumes = {"application/json"})
    public Message<User> save(@ApiParam(value = "User item to add") @RequestBody User user) {
            /*User u = userService.getByUserCode(user.getUserCode());
            if (u != null) {
                logger.error("保存用户信息异常！");
                throw new BusinessException("US3002");
            }*/
        logger.debug("开始保存用户信息……");
        return userService.save(user);
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
    @ApiOperation(httpMethod = "DELETE", value = "根据主键删除用户", notes = "根据主键删除用户")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", dataType = "String", name = "userId", value = "用户ID")})
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
    @ApiOperation(httpMethod = "GET", value = "根据主键查询用户", notes = "根据主键查询用户")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", dataType = "String", name = "userId", value = "用户ID")})
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
     * 查询用户信息列表(分页)
     */
    @ApiOperation(httpMethod = "GET", value = "查询用户列表可分页", notes = "查询用户列表可分页")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "Integer", name = "limit", value = "每页条数"),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "offset", value = "页码"),
            @ApiImplicitParam(paramType = "query", dataType = "User", name = "user", value = "查询条件")})
    @GetMapping
    public Message<IPage<User>> queryPageList(@RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "offset", required = false) Integer offset, @ModelAttribute User user) {
        logger.debug("开始查询用户列表信息……");
        return userService.findForPageList(limit, offset, user);
    }

    /**
     * 查询用户信息列表(分页)
     */
    @ApiOperation(httpMethod = "GET", value = "查询用户列表可分页", notes = "查询用户列表可分页")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "User", name = "user", value = "查询条件")})
    @GetMapping("/list")
    public Message<List<User>> queryList(@ModelAttribute User user) {
        logger.debug("开始查询用户列表信息……");
        return userService.findForList(user);
    }
}
