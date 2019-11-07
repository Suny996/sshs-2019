package ${coder.packageName}.service.impl;

import com.scai.core.base.service.impl.BaseServiceImpl;
import com.scai.core.exception.BusinessException;
import com.scai.core.message.Message;
import com.scai.core.page.Page;
import com.scai.core.util.UuidUtil;
import ${coder.packageName}.mapper.${coder.className}Mapper;
import ${coder.packageName}.model.${coder.className};
import ${coder.packageName}.service.I${coder.className}Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hsqldb.lib.StringUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Map;
import java.util.List;


 /**
 * ${coder.title}service实现类
 * @author ${coder.systemUser}
 * @date ${coder.crtDate?string("yyyy/MM/dd")}
 */
@Service("${coder.classDeclare}Service")
public class ${coder.className}ServiceImpl extends BaseServiceImpl<${coder.className}> implements I${coder.className}Service {
    Log logger = LogFactory.getLog(${coder.className}ServiceImpl.class);
    @Resource
    private ${coder.className}Mapper mapper;

   /**
   * 保存${coder.title}数据方法
   * @param ${coder.classDeclare}
   * @return Message
   */
   @Override
   public Message save(${coder.className} ${coder.classDeclare}){
       ${coder.classDeclare}.set${coder.idNameCapital}(UuidUtil.get32UUID());
       try {
           return super.save(${coder.classDeclare});
       } catch (Exception e) {
           e.printStackTrace();
           logger.error("保存${coder.title}信息异常！");
           throw new BusinessException("-110002");
       }
   }

   /**
   * 批量保存${coder.title}数据方法
   * @param ${coder.classDeclare}s
   * @return Message
   */
   @Override
   public Message save(List<${coder.className}> ${coder.classDeclare}s){
       try {
            for (${coder.className} ${coder.classDeclare} : ${coder.classDeclare}s) {
                ${coder.classDeclare}.set${coder.idNameCapital}(UuidUtil.get32UUID());
            }
           return super.save(${coder.classDeclare}s);
       } catch (Exception e) {
           e.printStackTrace();
           logger.error("批量保存${coder.title}信息异常！");
           throw new BusinessException("-110002");
       }
   }

   /**
   * 查询${coder.title}列表信息
   * @param limit
   */
   @Override
   public Message queryPageList(String limit, String offset, Map<String, Object> parameter) {
      if (StringUtil.isEmpty(limit)) {
         return Message.success(queryList(parameter));
      } else {
         Page<${coder.className}> page = new Page<${coder.className}>(Integer.valueOf(limit, 10), Integer.valueOf(offset, 10), parameter);
         return queryPageList(page);
      }
   }
     /**
     * 查询${coder.title}列表信息
     *
     * @param parameter
     */
    @Override
    public List<${coder.className}> queryList(Map<String, Object> parameter) {
            return super.findForList("${coder.packageName}.mapper.${coder.className}Mapper.findForList", parameter);
    }
   /**
   * 分页查询${coder.title}信息
   */
   @Override
   public Message queryPageList(Page<${coder.className}> page) {
        return findForPageList("${coder.packageName}.mapper.${coder.className}Mapper.findForPageList", page);
      }
   }
