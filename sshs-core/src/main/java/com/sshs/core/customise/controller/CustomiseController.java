package com.sshs.core.customise.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sshs.core.base.controller.BaseController;
import com.sshs.core.customise.mapper.CustomiseMapper;
import com.sshs.core.customise.model.Customise;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
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
@RestController
@RequestMapping(value = "/core/custmise")
public class CustomiseController extends BaseController {
    Logger logger = LoggerFactory.getLogger(CustomiseController.class);
    @Resource
    CustomiseMapper customiseMapper;

    /**
     * 自定义查询条件-添加
     *
     * @param customise
     * @throws Exception
     */
    @PostMapping
    public Message saveCustomise(@RequestBody Customise customise) throws Exception {
        try {
            //customise.setCustomiseId(UuidUtil.get32UUID());
            customise.setUserCode("admin");
            customise.setCrtDate(new Date());
            customiseMapper.insert(customise);
            return Message.success(customise);
        } catch (Exception e) {
            e.printStackTrace();
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
    public Message deleteCustomise(@PathVariable("pageId") String pageId, @PathVariable("customiseName") String customiseName) throws Exception {
        try {
            QueryWrapper wrapper = new QueryWrapper<Customise>();
            Customise customise = new Customise();
            customise.setPageId(pageId);
            customise.setCustomiseName(customiseName);
            wrapper.setEntity(customise);
            int cnt = customiseMapper.delete(wrapper);
            return Message.success(cnt);
        } catch (Exception e) {
            logger.error("删除失败pageId:{},customiseName:{}", pageId, customiseName);
            throw new BusinessException("SY0001");
        }
    }

    /**
     * 自定义查询-查询方法
     *
     * @param pageId
     * @return
     */
    @GetMapping("/{pageId}")
    public Message<List<Customise>> getCustomiseByPageId(@PathVariable("pageId") String pageId) {
        QueryWrapper wrapper = new QueryWrapper<Customise>();
        Customise customise = new Customise();
        customise.setPageId(pageId);
        customise.setUserCode("admin");
        wrapper.setEntity(customise);

        List<Customise> customises = customiseMapper.selectList(wrapper);
        //return customises;

        //Page<Customise> page = new Page<>();
        //customiseMapper.getCustomises(page, wrapper);
        return Message.success(customises);
    }
}