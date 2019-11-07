package com.sshs.toolkit.coder.service.impl;

import com.github.pagehelper.PageHelper;
import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.toolkit.coder.mapper.CoderMapper;
import com.sshs.toolkit.coder.model.Coder;
import com.sshs.toolkit.coder.model.Column;
import com.sshs.toolkit.coder.service.ICoderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 类名称：CreateCodeService 代码生成器
 *
 * @author Suny
 * @version 1.0
 * @date 2015年11月24日
 */
@Service("coderService")
public class CoderServiceImpl extends BaseServiceImpl<Coder> implements ICoderService {

    Log logger = LogFactory.getLog(CoderServiceImpl.class);
    @Resource
    private CoderMapper coderMapper;

    /**
     * 新增
     *
     * @param coder
     */
    @Override
    public Message save(Coder coder) {
        coderMapper.deleteByTableName(coder.getTableName());
        // coder.setCoderId(UuidUtil.get32UUID());
        try {
            return super.save(coder);
        } catch (Exception e) {
            logger.error("保存代码信息异常！",e);
            return Message.failure("-110002");
        }
    }

    /**
     * 列表(主表)
     *
     * @param tableName
     */
    @Override
    public List<Column> findColumnForList(String tableName) {
        return coderMapper.findColumnAll(tableName);
    }

    public Message<Page<Coder>> findDbTableForPageList(int limit, int offset, Map<String, Object> params) {
        PageHelper.startPage(offset, limit);
        List<Coder> list = coderMapper.findDbTableForPageList(params);
        return Message.success(new Page(list));
    }

}
