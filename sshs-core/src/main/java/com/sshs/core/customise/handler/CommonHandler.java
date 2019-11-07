package com.sshs.core.customise.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 类名称： 通用handler
 *
 * @author Suny
 * @version 1.0
 * @date 2017年10月23日
 */

@Component
public class CommonHandler {
    Logger logger = LoggerFactory.getLogger(CommonHandler.class);
    //@Resource(name = "sqlSessionTemplate")
    //private SqlSessionTemplate sqlSessionTemplate;


    /**
     * plist字典-查询方法
     *
     * @param request
     * @return
     */
    /*public Mono<ServerResponse> getList(ServerRequest request) {
        String dictCode = request.pathVariable("dictCode");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(DictionaryUtil.getList(dictCode, null, "zh_CN"))).switchIfEmpty(notFound);
    }*/

    /**
     * plist字典-查询所有方法
     *
     * @param request
     * @return
     */
    /*public Mono<ServerResponse> getAllList(ServerRequest request) {
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(DictionaryUtil.getAllList())).switchIfEmpty(notFound);
    }*/
}
