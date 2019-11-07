package ${coder.packageName}.controller;

import com.scai.core.exception.BusinessException;
import com.scai.core.message.Message;
import com.scai.core.page.Page;
import ${coder.packageName}.model.${coder.className};
import ${coder.packageName}.service.I${coder.className}Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;
import com.scai.core.base.controller.BaseController;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


 /** 
 * ${coder.title}controller类
 * @author ${coder.systemUser}
 * @date ${coder.crtDate?string("yyyy/MM/dd")}
 */
@RestController
@RequestMapping("/${coder.modelName}/${coder.functionName}s")
public class ${coder.className}Controller extends BaseController {
    @Resource
    private I${coder.className}Service ${coder.classDeclare}Service;

    Log logger = LogFactory.getLog(${coder.className}Controller.class);

    /**
    * 保存${coder.title}数据
    */
    @PostMapping
    public Mono<Message> save(@RequestBody ${coder.className} ${coder.classDeclare}) {
       try {
           logger.debug("开始保存${coder.title}信息……");
           return Mono.justOrEmpty(${coder.classDeclare}Service.save(${coder.classDeclare}));
       } catch (Exception e) {
           e.printStackTrace();
           logger.error("保存${coder.title}信息异常！");
           throw new BusinessException("SY0001");
       }
    }

    /**
    * 修改${coder.title}数据
    */
    @PutMapping
    public Mono<Message> update(@RequestBody ${coder.className} ${coder.classDeclare}) {
        try {
            logger.debug("开始更新${coder.title}信息……");
            return Mono.justOrEmpty(${coder.classDeclare}Service.update(${coder.classDeclare}));
        }  catch (BusinessException e) {
        throw e;
        } catch (Exception e) {
             e.printStackTrace();
             logger.error("更新${coder.title}信息异常！");
             throw new BusinessException("SY0002");
        }
    }
    /**
    * 根据主键删除${coder.title}数据
    */
    @DeleteMapping("/{${coder.classDeclare}Id}")
    public Mono<Message> delete(@PathVariable("${coder.classDeclare}Id") String ${coder.classDeclare}Id) {
        try {
            logger.debug("开始删除${coder.title}信息……");
            return Mono.justOrEmpty(${coder.classDeclare}Service.deleteById(${coder.classDeclare}Id));
        } catch (BusinessException e) {
            throw e;
            }  catch (Exception e) {
            e.printStackTrace();
            logger.error("删除${coder.title}信息异常！");
            throw new BusinessException("SY0003");
        }
    }

   /**
   * 批量删除${coder.title}数据
   */
   @DeleteMapping
   public Mono<Message> delete(@RequestBody List<String> ids) {
       try {
           logger.debug("开始批量删除${coder.title}信息……");
           return Mono.justOrEmpty(${coder.classDeclare}Service.deleteByIds(ids));
       }  catch (BusinessException e) {
            throw e;
       } catch (Exception e) {
       e.printStackTrace();
       logger.error("批量删除${coder.title}信息异常！");
       throw new BusinessException("SY0003");
       }
   }

   /**
   * 根据主键查找${coder.title}信息
   */
   @GetMapping("/{${coder.classDeclare}Id}")
   public Mono<Message> getById(@PathVariable("${coder.classDeclare}Id") String ${coder.classDeclare}Id) {
       try {
           logger.debug("开始查询${coder.title}信息……");
           Message message = Message.success(${coder.classDeclare}Service.getById(${coder.classDeclare}Id));
           return Mono.justOrEmpty(message);
       }  catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
       e.printStackTrace();
       logger.error("查询${coder.title}信息异常！");
       throw new BusinessException("SY0001");
       }
   }

   /**
   * 查询${coder.title}信息列表
   */
   @GetMapping
   public Mono<Message> queryList(@RequestParam (value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset, @RequestParam Map<String , Object> params) {
       try {
           logger.debug("开始查询${coder.title}列表信息……");
           Message message = ${coder.classDeclare}Service.queryPageList(limit, offset, params);
           return Mono.justOrEmpty(message);
        } catch (BusinessException e) {
           throw e;
        } catch (Exception e) {
       e.printStackTrace();
       logger.error("查询${coder.title}信息异常！");
       throw new BusinessException("SY0001");
       }
   }


   /**
   * 分页查询${coder.title}信息
   */
   @PostMapping("/pageList")
   public Mono<Message> queryPageList(@RequestBody Page<${coder.className}> page) {
      try {
          logger.debug("开始分页查询${coder.title}信息……");
          Message message = ${coder.classDeclare}Service.queryPageList(page);
          return Mono.justOrEmpty(message);
      } catch (BusinessException e) {
          throw e;
      } catch (Exception e) {
          e.printStackTrace();
          logger.error("分页查询${coder.title}信息异常！");
          throw new BusinessException("SY0005");
      }
   }
}
