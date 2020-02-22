package com.sshs.core.page;

import org.apache.ibatis.type.Alias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 分页类
 *
 * @author Suny
 * @date 2017年9月28日
 */
@Alias("Page")
public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> {
    private static final Logger logger = LoggerFactory.getLogger(Page.class);
    /**
     * 每页显示记录数
     */
    @Value("${sshs.page.size}")
    private int pageSize;

    public Page() {
        super();
    }

    public Page(long current, long size) {
        super(current, size);
    }

    public Page(long current, long size, long total) {
        super(current, size, total);
    }

    public Page(long current, long size, boolean isSearchCount) {
        super(current, size, isSearchCount);
    }

    public Page(long current, long size, long total, boolean isSearchCount) {
        super(current, size, total, isSearchCount);
    }
}
