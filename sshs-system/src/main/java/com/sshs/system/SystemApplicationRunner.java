package com.sshs.system;

import com.sshs.system.dictionary.service.IDictionaryService;
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
    @Resource
    private IDictionaryService dictionaryService;
    @Override
    public void run(ApplicationArguments var1) {
        //System.out.println("==========>SystemApplicationRunner!");
        dictionaryService.initDictList();
    }

}