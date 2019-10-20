package com.sshs.system.dictionary.controller;

import com.sshs.core.customise.model.Customise;
import com.sshs.core.exception.BusinessException;
import com.sshs.core.message.Message;
import com.sshs.core.page.Page;
import com.sshs.core.util.UuidUtil;
import com.sshs.system.dictionary.model.Dictionary;
import com.sshs.system.dictionary.service.IDictionaryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.bind.annotation.*;
import com.sshs.core.base.controller.BaseController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyExtractors.toMono;


/**
 * 系统管理->系统管理-数据字典表controller类
 * @author 61910
 * @date 2018/11/12
 */
@RestController
@RequestMapping("/system/dictionarys")
public class DictionaryController extends BaseController {
    @Resource
    private IDictionaryService dictionaryService;

    Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    /**
    * 保存系统管理->系统管理-数据字典表数据
    */
    @PostMapping
    public Mono<Message> save(@RequestBody Dictionary dictionary) {
       try {
           logger.debug("开始保存系统管理->系统管理-数据字典表信息……");
           return Mono.justOrEmpty(dictionaryService.save(dictionary));
       } catch (Exception e) {
           e.printStackTrace();
           logger.error("保存系统管理->系统管理-数据字典表信息异常！");
           throw new BusinessException("SY0001");
       }
    }

    /**
    * 修改系统管理->系统管理-数据字典表数据
    */
    @PutMapping
    public Mono<Message> update(@RequestBody Dictionary dictionary) {
        try {
            logger.debug("开始更新系统管理->系统管理-数据字典表信息……");
            return Mono.justOrEmpty(dictionaryService.update(dictionary));
        } catch (Exception e) {
             e.printStackTrace();
             logger.error("更新系统管理->系统管理-数据字典表信息异常！");
             throw new BusinessException("SY0002");
        }
    }
    /**
    * 根据主键删除系统管理->系统管理-数据字典表数据
    */
    @DeleteMapping("/{dictionaryId}")
    public Mono<Message> delete(@PathVariable("dictionaryId") String dictionaryId) {
        try {
            logger.debug("开始删除系统管理->系统管理-数据字典表信息……");
            return Mono.justOrEmpty(dictionaryService.deleteById(dictionaryId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除系统管理->系统管理-数据字典表信息异常！");
            throw new BusinessException("SY0003");
        }
    }

   /**
   * 批量删除系统管理->系统管理-数据字典表数据
   */
   @DeleteMapping
   public Mono<Message> delete(@RequestBody List<String> ids) {
       try {
           logger.debug("开始批量删除系统管理->系统管理-数据字典表信息……");
           return Mono.justOrEmpty(dictionaryService.deleteByIds(ids));
       } catch (Exception e) {
       e.printStackTrace();
       logger.error("批量删除系统管理->系统管理-数据字典表信息异常！");
       throw new BusinessException("SY0003");
       }
   }

   /**
   * 根据主键查找系统管理->系统管理-数据字典表信息
   */
   @GetMapping("/{dictionaryId}")
   public Mono<Message> getById(@PathVariable("dictionaryId") String dictionaryId) {
       try {
           logger.debug("开始查询系统管理->系统管理-数据字典表信息……");
           Message message = Message.success(dictionaryService.getById(dictionaryId));
           return Mono.justOrEmpty(message);
       } catch (Exception e) {
       e.printStackTrace();
       logger.error("查询系统管理->系统管理-数据字典表信息异常！");
       throw new BusinessException("SY0001");
       }
   }

   /**
   * 查询系统管理->系统管理-数据字典表信息列表
   */
   @GetMapping
   public Mono<Message> queryList(@RequestParam (value = "limit", required = false) String limit, @RequestParam String offset, @RequestParam Map<String , Object> params) {
       try {
           logger.debug("开始查询系统管理->系统管理-数据字典表列表信息……");
           Message message = dictionaryService.queryPageList(limit, offset, params);
           return Mono.justOrEmpty(message);
        } catch (Exception e) {
       e.printStackTrace();
       logger.error("查询系统管理->系统管理-数据字典表信息异常！");
       throw new BusinessException("SY0001");
       }
   }


   /**
   * 分页查询系统管理->系统管理-数据字典表信息
   */
   @PostMapping("/pageList")
   public Mono<Message> queryPageList(@RequestBody Page<Dictionary> page) {
      try {
          logger.debug("开始分页查询系统管理->系统管理-数据字典表信息……");
          Message message = dictionaryService.queryPageList(page);
          return Mono.justOrEmpty(message);
      } catch (Exception e) {
          e.printStackTrace();
          logger.error("分页查询系统管理->系统管理-数据字典表信息异常！");
          throw new BusinessException("SY0005");
      }
   }

    /**
     * 数据字典-查询方法
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getDictionarys(ServerRequest request) {
        String dictCode = request.pathVariable("dictCode");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Dictionary dictionary = dictionaryService.getDictionaryByCode(dictCode);

        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(dictionary.getChildren())).switchIfEmpty(notFound);
    }

    /**
     * 根据主键查找系统管理->系统管理-数据字典表信息
     */
    @GetMapping("/{dictCode}")
    public Dictionary getDictionarys(@PathVariable("dictCode") String dictCode) {
        try {
            logger.debug("开始查询系统管理->系统管理-数据字典表信息……");
            Dictionary dictionary = dictionaryService.getDictionaryByCode(dictCode);
            return dictionary;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询系统管理->系统管理-数据字典表信息异常！");
            throw new BusinessException("SY0001");
        }
    }
}
