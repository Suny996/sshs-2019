package ${coder.packageName}.mapper;

import com.scai.core.page.Page;
import ${coder.packageName}.model.${coder.className};
import tk.mybatis.mapper.common.Mapper;


/**
* ${coder.title}类
* @author ${coder.systemUser}
* @date ${coder.crtDate?string("yyyy/MM/dd")}
*/
public interface ${coder.className}Mapper extends Mapper<${coder.className}> {
    public Page<${coder.className}> findForPageList();
}