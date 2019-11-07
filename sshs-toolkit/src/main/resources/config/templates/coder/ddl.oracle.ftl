-- Create table ${coder.tableComment}

create table ${coder.tableName}
(
<#list coder.fields as field>
	${field.columnName}	${field.columnType} <#if (field.columnSize > 0)> (${field.columnSize}) </#if>,
</#list>
);
-- Add comments to the table 
comment on table ${coder.tableName}
  is '${coder.tableComment}';
-- Add comments to the columns 
<#list coder.fields as field>
comment on column ${coder.tableName}.${field.columnName}
  is '${field.columnComment}';
</#list>
<#list coder.fields as field>
	<#if field.primaryKeyFlag == 'Y'>
-- Create/Recreate primary, unique and foreign key constraints
alter table ${coder.tableName}
  add constraint PK_${coder.tableName} primary key (${field.columnName});
  </#if>
</#list>
