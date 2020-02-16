package com.sshs.system;

import com.sshs.system.dictionary.service.IDictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Suny
 */
@Component
@Order(value = 88)
public class SystemApplicationRunner  implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(SystemApplicationRunner.class);
    @Resource
    private IDictionaryService dictionaryService;
    @Override
    public void run(ApplicationArguments var1) {
        logger.info("==========>开始初始化数据字典……");
        dictionaryService.initDictList();
        logger.info("==========>数据字典初始化完成!");
    }

}