package com.sshs.core.customise.controller;

import com.sshs.core.wrapper.QueryWrapper;
import com.sshs.core.base.controller.BaseController;
import com.sshs.core.customise.mapper.CustomiseMapper;
import com.sshs.core.customise.model.Customise;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 类名称： 自定义查询
 *
 * @author Suny
 * @date 2017年10月23日
 */
@Api(tags = "公共-自定义查询条件接口")
@RestController
@RequestMapping("/v1/core/customise")
public class CustomiseController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CustomiseController.class);
    @Resource
    CustomiseMapper customiseMapper;

    /**
     * 自定义查询条件-添加
     *
     * @param customise
     * @throws Exception
     */
    @ApiOperation(value = "自定义查询保存")
    @ApiImplicitParam(name = "customise", value = "查询对象", required = true, dataType = "Customise", paramType = "body")
    @PostMapping
    public Message<Customise> saveCustomise(@RequestBody Customise customise) {
        try {
            //customise.setCustomiseId(UuidUtil.get32UUID());
            customise.setUserCode("admin");
            customise.setCrtDate(new Date());
            customiseMapper.insert(customise);
            return Message.success(customise);
        } catch (Exception e) {
            logger.error("保存失败", e);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 自定义查询条件-删除
     *
     * @param pageId
     * @param customiseName
     * @throws Exception
     */
    @DeleteMapping("/{pageId}/{customiseName}")
    public Message<Integer> deleteCustomiseByName(@PathVariable("pageId") String pageId, @PathVariable("customiseName") String customiseName) {
        try {
            QueryWrapper<Customise> customise = new QueryWrapper<>();
            customise.eq("pageId", pageId);
            customise.eq("customiseName",customiseName);
            int cnt = customiseMapper.delete(customise);
            return Message.success(cnt);
        } catch (Exception e) {
            logger.error("删除失败pageId:{},customiseName:{}", pageId, customiseName);
            throw new BusinessException("SY0001");
        }
    }
    /**
     * 自定义查询条件-删除
     *
     * @param customiseId
     * @throws Exception
     */
    @DeleteMapping("/{customiseId}")
    public Message<Integer> deleteCustomise(@PathVariable("customiseId") String customiseId) {
        try {
            int cnt = customiseMapper.deleteById(customiseId);
            return Message.success(cnt);
        } catch (Exception e) {
            logger.error("删除失败，customiseId:{}", customiseId);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 自定义查询-查询方法
     *
     * @param pageId
     * @return
     */
    @ApiImplicitParam(paramType = "path", name = "pageId", value = "页面ID", dataType = "String", required = true)
    @GetMapping("/{pageId}")
    public Message<List<Customise>> getCustomiseByPageId(@PathVariable("pageId") String pageId) {
        QueryWrapper<Customise> customise = new QueryWrapper<>();
        customise.eq("pageId", pageId);
        customise.eq("userCode","admin");

        List<Customise> customises = customiseMapper.selectList(customise);
        return Message.success(customises);
    }
}