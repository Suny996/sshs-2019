package com.sshs.core.customise.handler;

import com.sshs.core.customise.mapper.CustomiseMapper;
import com.sshs.core.customise.model.Customise;
import com.sshs.core.message.Message;
import com.sshs.core.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * 类名称： 自定义查询
 *
 * @author Suny
 * @version 1.0
 * @date 2017年10月23日
 */

@Component
public class CustomiseHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomiseHandler.class);
    @Resource
    CustomiseMapper customiseMapper;

    /**
     * 添加自定义查询方法
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> saveCustomise(ServerRequest request) {
        return ServerResponse.ok()
                .body(request.body(BodyExtractors.toMono(Customise.class)).map(c -> {
                    c.setId(UuidUtil.get32UUID());
                    c.setUserCode("admin");
                    c.setCrtDate(new Date());
                    customiseMapper.insert(c);
                    return c;
                }), Customise.class);
    }

    /**
     * 删除自定义查询方法
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> deleteCustomise(ServerRequest request) {
        //System.out.println(">>>>>>>>>del");
        String customiseId = request.pathVariable("customiseId");
        customiseMapper.deleteByPrimaryKey(customiseId);
        return ServerResponse.ok()//.contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(new Message("100000")));
    }

    /**
     * 自定义查询-查询方法
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getCustomiseByPageId(ServerRequest request) {
        String pageId = request.pathVariable("pageId");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Customise custom = new Customise();
        custom.setPageId(pageId);
        custom.setUserCode("admin");
        List<Customise> customises = customiseMapper.select(custom);

        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(customises)).switchIfEmpty(notFound);
    }

    /*public Mono<ServerResponse> getTimePerSec(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(  // 1
                Flux.interval(Duration.ofSeconds(10)).   // 2
                        map(l -> new SimpleDateFormat("HH:mm:ss").format(new Date())),
                String.class);
    }*/
}
