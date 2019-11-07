const lang = {
    locale: 'en-US',
    ${coder.subModelName}: {
        $title : '{0}${coder.functionName} info',
<#list coder.fields as field>
        ${field.propertyName} : '${field.propertyName}',
</#list>
    }
}