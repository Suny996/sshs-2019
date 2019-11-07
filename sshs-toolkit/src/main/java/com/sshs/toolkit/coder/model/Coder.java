package com.sshs.toolkit.coder.model;

import com.sshs.core.model.BaseEntity;
import com.sshs.core.util.UUIdGenId;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.Alias;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * 代码生成
 *
 * @author Suny
 * @date 2017-11-10
 */
@Alias("Coder")
@Table(name = "TOO_CODER")
public class Coder extends BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @KeySql(genId = UUIdGenId.class)
    private String coderId;
    @ApiModelProperty(value = "包名", dataType = "String", example = "com.sshs.system")
    private String packageName;
    @ApiModelProperty(value = "类名", dataType = "String", example = "User")
    private String className;
    /**
     * 首字母小写
     */
    @Transient
    @ApiModelProperty(value = "类名声明", dataType = "String", example = "user")
    private String classDeclare;

    @ApiModelProperty(value = "模块名", dataType = "String", example = "System")
    private String modelName;
    @ApiModelProperty(value = "模块名", dataType = "String", example = "系统管理")
    private String modelNameCn;
    @Transient
    @ApiModelProperty(value = "子模块名", dataType = "String", example = "User")
    private String subModelName;
    /**
     * 功能
     */
    @ApiModelProperty(value = "功能名称", dataType = "String", example = "User")
    private String functionName;
    @ApiModelProperty(value = "表名", dataType = "String", example = "SYS_USER")
    private String tableName;
    @ApiModelProperty(value = "表描述", dataType = "String", example = "用户基本信息表")
    private String tableComment;
    @ApiModelProperty(value = "标题", dataType = "String", example = "用户管理")
    private String title;

    private String coderType;
    @Transient
    private List<com.sshs.toolkit.coder.model.Column> fields;

    @javax.persistence.Column(name = "FIELDS")
    private byte[] columns;

    @Transient
    private String createDate;
    @Transient
    private String systemUser;
    @Transient
    private String idName;


    @Transient
    private String idNameCapital;
    @Transient
    private String coderFlag;


    public String getCoderId() {
        return coderId;
    }

    public void setCoderId(String coderId) {
        this.coderId = coderId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(String systemUser) {
        this.systemUser = systemUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoderType() {
        return coderType;
    }

    public void setCoderType(String coderType) {
        this.coderType = coderType;
    }

    public List<com.sshs.toolkit.coder.model.Column> getFields() {
        return this.fields;
    }

    public void setFields(List<com.sshs.toolkit.coder.model.Column> fields) {
        this.fields = fields;
    }

    public byte[] getColumns() {
        return columns;
    }

    public void setColumns(byte[] columns) {
        this.columns = columns;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDeclare() {
        return classDeclare;
    }

    public void setClassDeclare(String classDeclare) {
        this.classDeclare = classDeclare;
    }

    public String getSubModelName() {
        return subModelName;
    }

    public void setSubModelName(String subModelName) {
        this.subModelName = subModelName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelNameCn() {
        return modelNameCn;
    }

    public void setModelNameCn(String modelNameCn) {
        this.modelNameCn = modelNameCn;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getCoderFlag() {
        return coderFlag;
    }

    public void setCoderFlag(String coderFlag) {
        this.coderFlag = coderFlag;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdNameCapital() {
        return idNameCapital;
    }

    public void setIdNameCapital(String idNameCapital) {
        this.idNameCapital = idNameCapital;
    }
}

