const lang = {
    locale: 'zh-CN',
    ${coder.subModelName}: {
        $title : '{0}${coder.title}信息',
<#list coder.fields as field>
        ${field.propertyName} : '${field.columnComment}',
</#list>
    }
}