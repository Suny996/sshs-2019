package com.sshs.system.post.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sshs.core.base.controller.BaseController;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.system.post.model.Post;
import com.sshs.system.post.service.IPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 系统管理->系统管理-岗位表controller类
 *
 * @author 61910
 * @date 2018/11/08
 */
@RestController
@RequestMapping("/system/posts")
public class PostController extends BaseController {
    @Resource
    private IPostService postService;

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    /**
     * 保存系统管理->系统管理-岗位表数据
     */
    @PostMapping
    public Message<Post> save(@RequestBody Post post) {
        try {
            logger.debug("开始保存系统管理->系统管理-岗位表信息……");
            return postService.save(post);
        } catch (Exception e) {
            logger.error("保存系统管理->系统管理-岗位表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 修改系统管理->系统管理-岗位表数据
     */
    @PutMapping
    public Message<Post> update(@RequestBody Post post) {
        try {
            logger.debug("开始更新系统管理->系统管理-岗位表信息……");
            return postService.update(post);
        } catch (Exception e) {
            logger.error("更新系统管理->系统管理-岗位表信息异常！", e);
            throw new BusinessException("SY0002");
        }
    }

    /**
     * 根据主键删除系统管理->系统管理-岗位表数据
     */
    @DeleteMapping("/{postId}")
    public Message<Integer> delete(@PathVariable("postId") String postId) {
        try {
            logger.debug("开始删除系统管理->系统管理-岗位表信息……");
            return postService.deleteById(postId);
        } catch (Exception e) {
            logger.error("删除系统管理->系统管理-岗位表信息异常！", e);
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 批量删除系统管理->系统管理-岗位表数据
     */
    @DeleteMapping
    public Message<Integer> delete(@RequestBody List<String> ids) {
        try {
            logger.debug("开始批量删除系统管理->系统管理-岗位表信息……");
            return postService.deleteByIds(ids);
        } catch (Exception e) {
            logger.error("批量删除系统管理->系统管理-岗位表信息异常！", e);
            throw new BusinessException("SY0003");
        }
    }

    /**
     * 根据主键查找系统管理->系统管理-岗位表信息
     */
    @GetMapping("/{postId}")
    public Message<Post> getById(@PathVariable("postId") String postId) {
        try {
            logger.debug("开始查询系统管理->系统管理-岗位表信息……");
            return postService.getById(postId);
        } catch (Exception e) {
            logger.error("查询系统管理->系统管理-岗位表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 查询系统管理->系统管理-岗位表信息列表
     */
    @GetMapping
    public Message<IPage<Post>> queryPageList(@RequestParam(value = "limit", required = false) int limit, @RequestParam(value = "offset", required = false) int offset, @RequestParam(required = false) Post params) {
        try {
            logger.debug("开始查询系统管理->系统管理-岗位表列表信息……");
            return postService.findForPageList(limit, offset, params);
        } catch (Exception e) {
            logger.error("查询系统管理->系统管理-岗位表信息异常！", e);
            throw new BusinessException("SY0001");
        }
    }

}
