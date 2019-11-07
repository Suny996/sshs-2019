package com.sshs.toolkit.coder.helper;

import com.sshs.core.constant.Global;
import com.sshs.core.util.Configure;
import com.sshs.core.util.ReflectHelper;
import com.sshs.toolkit.coder.model.Coder;
import com.sshs.toolkit.coder.model.Column;
import com.sshs.toolkit.configuration.ToolkitConfigProp;
import com.sshs.toolkit.util.Freemarker;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * 代码生成工具类
 *
 * @author Suny
 * @date 2017-10-23
 */
@Component
public class CoderGenerator {
    @Resource
    Freemarker freemarker;
    @Resource
    Configure configure;

    @Resource
    ToolkitConfigProp toolkitConfigProp;

    /**
     * 代码生成主方法
     *
     * @param coder
     * @throws Exception
     */
    public void generate(Coder coder) throws Exception {
        processProperties(coder);
        String xml = freemarker.printFreemarkerString("/templates/coder/config.xml", toMap(coder),
                "UTF-8");
        Document document = null;
        SAXReader reader = null;
        reader = new SAXReader();
        document = reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));

        Element root = document.getRootElement();
        @SuppressWarnings("unchecked")
        Iterator<Element> iterator = root.elementIterator();
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            String packageName = element.element("packageName").getText();
            String className = element.element("className").getText();
            String templateFileName = element.element("template").getText();
            String encoding = element.elementText("encoding");
            if (StringUtils.isEmpty(encoding)) {
                encoding = root.attributeValue("encoding");
            }
            String outFileName = "";
            if ("view".equalsIgnoreCase(packageName)) {
                outFileName = toolkitConfigProp.getPathView() == null ? "coder/view" : toolkitConfigProp.getPathView() + "/"
                        + coder.getModelName() + "/" + coder.getFunctionName() + "/" + className;
            } else {
                outFileName = toolkitConfigProp.getPathView() == null ? "coder/java" : toolkitConfigProp.getPathView() + "/"
                        + coder.getPackageName().replaceAll("\\.", "/") + "/" + packageName.replaceAll("\\.", "/") + "/"
                        + className;
            }
            if (templateFileName != null && templateFileName.toLowerCase().endsWith(".ftl")) {
                freemarker.printFreemarkerFile("/templates/coder/" + templateFileName, outFileName,
                        toMap(coder), encoding);
            }
            if (templateFileName != null && templateFileName.toLowerCase().endsWith(".vm")) {
                freemarker.printVelocityFile("/templates/coder/" + templateFileName, outFileName,
                        toMap(coder));
            }
        }
    }

    /**
     * 准备map对象
     *
     * @param coder
     * @return
     */
    public static Map<String, Object> toMap(Coder coder) {
        Map<String, Object> map = new HashMap<String, Object>(50);
        map.put("coder", coder);
        map.put("fields", coder.getFields());
        return map;
    }

    /*private static Map<String, String> map;

    static {
        map = new HashMap<String, String>();
        map.put("varchar2", "String");
        map.put("varchar", "String");
        map.put("int", "Integer");
        map.put("datetime", "Date");
        map.put("date", "Date");
        map.put("timestamp", "Date");
        map.put("nvarchar", "String");
        map.put("char", "String");
        map.put("uniqueidentifier", "String");
        map.put("number", "BigDecimal");
        map.put("decimal", "BigDecimal");
        map.put("bigint", "Long");
        map.put("tinyint", "Integer");
        map.put("blob", "Blob");
        map.put("clob", "String");
        map.put("text", "String");
    }*/

    public String getPropertyType(String dataType) {
        String tmp = dataType.toLowerCase();
        StringTokenizer st = new StringTokenizer(tmp);
        tmp = st.nextToken();
        String prop = (String) toolkitConfigProp.getColumnTypeMapping().get(tmp);
        if (StringUtils.isEmpty(prop) && tmp.contains("(")) {
            tmp = tmp.substring(0, tmp.indexOf("(")).trim();
            prop = (String) toolkitConfigProp.getColumnTypeMapping().get(tmp);
        }
        if (StringUtils.isEmpty(prop)) {
            prop = "String";
        }
        return prop;
    }

    /*private static Map<String, String> modelMapping;

    static {
        modelMapping = new HashMap<String, String>();
        // 系统管理模块
        modelMapping.put("sys", "system");
        // 开发中心模块
        modelMapping.put("dev", "develop");
        // 配置管理模块
        modelMapping.put("cfg", "config");
        // 指标管理模块
        modelMapping.put("ie", "index");
        // 关系管理
        modelMapping.put("prm", "relation");
    }*/

    public String getModel(String model) {
        String model1 = toolkitConfigProp.getModelMapping().get(model.toLowerCase());
        if (model1 == null) {
            model1 = model;
        }
        return model1;
    }

    /**
     * 处理部分关键字段
     *
     * @param coder
     */
    public void processProperties(Coder coder) {
        processClassName(coder);
        processModelName(coder);
        processFunctionName(coder);
        processPackageName(coder);
        processFields(coder);
        processTitle(coder);
        if (coder.getCrtDate() == null) {
            coder.setCrtDate(new Date());
        }
        if (coder.getSystemUser() == null) {
            coder.setSystemUser(System.getProperty("user.name"));
        }
        if (coder.getTableComment() == null) {
            coder.setTableComment(coder.getTableName());
        }
    }

    /**
     * 当类名为空时根据表名设置类名
     *
     * @param coder
     * @return
     */
    public static void processClassName(Coder coder) {
        String tableName = coder.getTableName();
        if (StringUtils.isEmpty(coder.getClassName())) {
            coder.setClassName(ReflectHelper
                    .getPropertyName(tableName.substring(tableName.indexOf(Global.CHARACTER_UNDERLINE) + 1), false));
            coder.setClassDeclare(ReflectHelper
                    .getPropertyName(tableName.substring(tableName.indexOf(Global.CHARACTER_UNDERLINE) + 1)));
        }
    }

    /**
     * 当模块名为空时根据表名设置模块名
     *
     * @param coder
     */
    public void processModelName(Coder coder) {
        String tableName = coder.getTableName();
        if (StringUtils.isEmpty(coder.getModelName())) {
            String modelPrefix = tableName;
            if (tableName.indexOf("_") > 0) {
                modelPrefix = tableName.substring(0, tableName.indexOf("_"));
            } else if (tableName.length() > 3) {
                modelPrefix = tableName.substring(0, 3);
            }
            coder.setModelName(ReflectHelper
                    .getPropertyName(getModel(modelPrefix).toLowerCase()));
        }
        String tableComment = coder.getTableComment();
        if (tableComment == null) {
            tableComment = tableName;
        }
        if (StringUtils.isEmpty(coder.getModelNameCn())) {
            String modelNameCn = tableComment;
            if (tableComment.indexOf(Global.CHARACTER_STICK) > 0) {
                modelNameCn = tableComment.substring(0, tableComment.indexOf(Global.CHARACTER_STICK));
            }
            coder.setModelNameCn(modelNameCn);
        }
    }

    /**
     * 当功能名为空时根据表名设置功能名
     *
     * @param coder
     */
    public void processFunctionName(Coder coder) {
        String tableName = coder.getTableName();
        if (StringUtils.isEmpty(coder.getFunctionName())) {
            coder.setFunctionName(ReflectHelper.getPropertyName(tableName.substring(tableName.indexOf("_") + 1)));
        }
    }

    /**
     * 当标题为空时根据表名设置标题
     *
     * @param coder
     */
    public void processTitle(Coder coder) {
        if (StringUtils.isEmpty(coder.getTitle())) {
            coder.setTitle(coder.getModelNameCn() + "->" + coder.getTableComment());
        }
    }

    /**
     * 当包名为空时根据表名设置包名
     *
     * @param coder
     */
    public void processPackageName(Coder coder) {
        String tableName = coder.getTableName();
        String subPackageName = tableName.substring(tableName.indexOf("_") + 1);
        if (subPackageName != null && subPackageName.indexOf("_") > 0) {
            subPackageName = subPackageName.substring(0, subPackageName.indexOf("_"));
        }
        subPackageName = ReflectHelper.getPropertyName(subPackageName).toLowerCase();
        coder.setSubModelName(subPackageName);
        if (StringUtils.isEmpty(coder.getPackageName())) {
            coder.setPackageName(configure.getProperty("coder.package.prefix", "com.sshs") + "." + coder.getModelName()
                    + "." + subPackageName);
        }
    }

    /**
     * 处理字段名称首字母大写
     *
     * @param coder
     */
    public void processFields(Coder coder) {
        for (Column column : coder.getFields()) {
            column.setPropFuncName(ReflectHelper.capitalName(column.getPropertyName()));
            if ("1".equals(column.getPrimaryKeyFlag())) {
                coder.setIdName(column.getPropertyName());
                coder.setIdNameCapital(ReflectHelper.capitalName(column.getPropertyName()));
            }
            if (column.getColumnSize() == null) {
                column.setColumnSize(0);
            }
        }
    }
}
