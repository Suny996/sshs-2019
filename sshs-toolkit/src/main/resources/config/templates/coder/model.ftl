package ${coder.packageName}.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import org.apache.ibatis.type.Alias;

/** 
 * ${coder.title}bean ${coder.className}类
 * @author ${coder.systemUser}
 * @date ${coder.crtDate?string("yyyy/MM/dd")}
 */
@Alias("${coder.className}")
@Table(name="${coder.tableName}")
public class ${coder.className} implements Serializable {

private static final long serialVersionUID = 1L;

<#list coder.fields as field>

    /**
    * ${field.columnComment}
    */
	<#if field.primaryKeyFlag=="1">
	@Id
	//@GeneratedValue(generator = "UUID")
	</#if>
	@Column(name="${field.columnName}")
	private ${field.propertyType} ${field.propertyName};
</#list>

<#list coder.fields as field> 

	public ${field.propertyType} get${field.propFuncName}(){
		return this.${field.propertyName};
	}
	
	public void set${field.propFuncName}(${field.propertyType} ${field.propertyName}){
		this.${field.propertyName} = ${field.propertyName};
	}
</#list>
}