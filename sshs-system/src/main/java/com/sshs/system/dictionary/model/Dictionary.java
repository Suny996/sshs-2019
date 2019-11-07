package com.sshs.system.dictionary.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sshs.core.util.UUIdGenId;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.ibatis.type.Alias;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据字典
 *
 * @author Suny
 */
@Alias("Dictionary")
@Table(name = "SYS_DICTIONARIES")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Dictionary implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @Id
    @KeySql(genId = UUIdGenId.class)
    @Column(name = "DICT_ID", length = 32)
    private String id;

    /**
     * 模块
     */
    @Column(name = "MODULE")
    private String module;

    /**
     * 法人行社
     */
    @Column(name = "LEGAL_ORG")
    private String legalOrg;

    /**
     * 创建人
     */
    @Column(name = "CRT_USER_CODE")
    private String crtUserCode;

    /**
     * 创建机构
     */
    @Column(name = "CRT_ORG_CODE")
    private String crtOrgCode;

    /**
     * 创建日期
     */
    @Column(name = "CRT_DATE")
    private Date crtDate;

    /**
     * 修改人
     */
    @Column(name = "UPD_USER_CODE")
    private String updUserCode;

    /**
     * 修改机构
     */
    @Column(name = "UPD_ORG_CODE")
    private String updOrgCode;

    /**
     * 修改日期
     */
    @Column(name = "UPD_DATE")
    private Date updDate;

    /**
     * 上级ID
     */
    @Column(name = "PARENT_ID")
    private String parentId;

    /**
     * 类型:0-分类,1-字典项,2-字典子项,3-字典码值
     */
    @Column(name = "DICT_TYPE")
    private String dictType;

    /**
     * 代码
     */
    @Column(name = "DICT_CODE")
    private String dictCode;

    /**
     * 名称
     */
    @Column(name = "DICT_NAME")
    private String dictName;

    /**
     * 描述
     */
    @Column(name = "DICT_DESC")
    private String dictDesc;

    /**
     * 状态1:有效0:无效
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 排序
     */
    @Column(name = "SORT_NO")
    private BigDecimal sortNo;

    /**
     * 是否系统类1:系统类0:业务类
     */
    @Column(name = "IS_SYSTEM")
    private String isSystem;

    @Transient
    private List<Dictionary> children = new ArrayList<Dictionary>();
    @Transient
    private List<DictionaryI18n> i18ns = new ArrayList<DictionaryI18n>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getSortNo() {
        return sortNo;
    }

    public void setSortNo(BigDecimal sortNo) {
        this.sortNo = sortNo;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getLegalOrg() {
        return legalOrg;
    }

    public void setLegalOrg(String legalOrg) {
        this.legalOrg = legalOrg;
    }

    public String getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getCrtUserCode() {
        return crtUserCode;
    }

    public void setCrtUserCode(String crtUserCode) {
        this.crtUserCode = crtUserCode;
    }

    public String getCrtOrgCode() {
        return crtOrgCode;
    }

    public void setCrtOrgCode(String crtOrgCode) {
        this.crtOrgCode = crtOrgCode;
    }

    public String getUpdUserCode() {
        return updUserCode;
    }

    public void setUpdUserCode(String updUserCode) {
        this.updUserCode = updUserCode;
    }

    public String getUpdOrgCode() {
        return updOrgCode;
    }

    public void setUpdOrgCode(String updOrgCode) {
        this.updOrgCode = updOrgCode;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }


    public List<Dictionary> getChildren() {
        return children;
    }

    public void addChild(Dictionary dictionary) {
        if (children == null) {
            children = new ArrayList<Dictionary>();
        }
        children.add(dictionary);
    }

    public List<DictionaryI18n> getI18ns() {
        return i18ns;
    }

    public void addI18n(DictionaryI18n i18n) {
        if (i18ns == null) {
            i18ns = new ArrayList<DictionaryI18n>();
        }
        i18ns.add(i18n);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("module", module)
                .append("legalOrg", legalOrg)
                .append("crtUserCode", crtUserCode)
                .append("crtOrgCode", crtOrgCode)
                .append("crtDate", crtDate)
                .append("updUserCode", updUserCode)
                .append("updOrgCode", updOrgCode)
                .append("updDate", updDate)
                .append("parentId", parentId)
                .append("dictType", dictType)
                .append("dictCode", dictCode)
                .append("dictName", dictName)
                .append("dictDesc", dictDesc)
                .append("status", status)
                .append("sortNo", sortNo)
                .append("isSystem", isSystem)
                .append("children", children)
                .append("i18ns", i18ns)
                .toString();
    }
}
