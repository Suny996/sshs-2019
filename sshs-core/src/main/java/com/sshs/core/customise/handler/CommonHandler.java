package com.sshs.core.customise.handler;

import com.sshs.core.util.DictionaryUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * 类名称： 通用handler
 *
 * @author Suny
 * @version 1.0
 * @date 2017年10月23日
 */

@Component
public class CommonHandler {
    Log logger = LogFactory.getLog(CommonHandler.class);
    //@Resource(name = "sqlSessionTemplate")
    //private SqlSessionTemplate sqlSessionTemplate;


    /**
     * plist字典-查询方法
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getList(ServerRequest request) {
        String dictCode = request.pathVariable("dictCode");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(DictionaryUtil.getList(dictCode, null, "zh_CN"))).switchIfEmpty(notFound);
    }

    /**
     * plist字典-查询所有方法
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllList(ServerRequest request) {
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(DictionaryUtil.getAllList())).switchIfEmpty(notFound);
    }
}
