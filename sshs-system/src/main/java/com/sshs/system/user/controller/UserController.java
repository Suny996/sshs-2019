package com.sshs.system.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.base.controller.BaseController;
import com.sshs.core.message.Message;
import com.sshs.core.version.Version;
import com.sshs.system.user.model.User;
import com.sshs.system.user.service.IUserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;


/**
 * 用户管理控制类
 *
 * @author Suny
 * @date 2017年7月5日 上午10:40:10
 */
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.POST})
@Api(tags = "系统管理-用户管理")
@RestController
@Version(1)
@RequestMapping(value = "/{version:v\\d+}/system/users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 保存用户
     */
    @ApiOperation(value = "用户信息新增保存", nickname = "save", notes = "Adds an User to the system", tags = {"save",})
    @PostMapping
    public Message<User> save(@ApiParam(name = "user", value = "用户对象") @Validated @RequestBody User user) {
        logger.debug("开始保存用户信息……");
        return userService.save(user);
    }

    /**
     * 修改用户
     */
    @ApiOperation(value = "用户信息修改保存", nickname = "save", notes = "Adds an User to the system", tags = {"update",})
    @PutMapping
    public Message update(@ApiParam(name = "user", value = "用户对象") @Validated @RequestBody User user) {
        logger.debug("开始更新用户信息……");
        return userService.update(user);
    }

    /**
     * 删除用户
     */
    @ApiOperation(httpMethod = "DELETE", value = "根据主键删除用户", notes = "根据主键删除用户")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", dataType = "String", name = "userId", value = "用户ID")})
    @DeleteMapping("/{userId}")
    public Message delete(@PathVariable("userId") String userId) {
        logger.debug("开始删除用户信息……");
        return userService.deleteById(userId);
    }

    /**
     * 批量删除用户
     */
    @DeleteMapping
    public Message delete(@NotEmpty(message = "${user.ids.notEmpty}") @RequestBody List<String> ids) {
        logger.debug("开始批量删除用户信息……");
        return userService.deleteByIds(ids);
    }

    /**
     * 根据主键查找用户信息
     */
    @ApiOperation(httpMethod = "GET", value = "根据主键查询用户", notes = "根据主键查询用户")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", dataType = "String", name = "userId", value = "用户ID")})
    @GetMapping("/{userId}")
    public Message<User> getById(@PathVariable("userId") String userId) {
        logger.debug("开始查询用户信息……");
        return userService.getById(userId);
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
     * 查询用户信息列表
     */
    @ApiOperation(httpMethod = "GET", value = "查询用户列表", notes = "查询用户列表")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "User", name = "user", value = "查询条件")})
    @GetMapping("/list")
    public Message<List<User>> queryList(@ModelAttribute User user) {
        logger.debug("开始查询用户列表信息……");
        return userService.findForList(user);
    }

    /**
     * 重置密码
     */
    @ApiOperation(value = "重置密码", nickname = "resetPassword")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", dataType = "String", name = "userId", value = "用户编号"),
            @ApiImplicitParam(paramType = "query", dataType = "List", name = "roles", value = "角色列表")})
    @PutMapping("/resetPassword/{userId}")
    public Message<User> auth(@PathVariable("userId") String userId) {
        logger.debug("开始重置密码……");
        return userService.resetPassword(userId);
    }

    /**
     * 分配角色给用户
     */
    @ApiOperation(value = "分配角色给用户", nickname = "auth")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", dataType = "String", name = "userCode", value = "用户编号"),
            @ApiImplicitParam(paramType = "query", dataType = "List", name = "roles", value = "角色列表")})
    @PostMapping("/auth/{userCode}")
    public Message<User> auth(@PathVariable("userCode") String userCode, @RequestBody List<String> roles) {
        logger.debug("开始保存用户角色信息……");
        return userService.auth(userCode, roles);
    }
}
