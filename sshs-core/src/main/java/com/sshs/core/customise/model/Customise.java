package com.sshs.core.customise.model;

import com.sshs.core.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 自定义查询
 *
 * @author Suny
 * @date 2017-12-10
 */
@ApiModel(description = "自定义查询")
@Alias("Customise")
@Table(name = "CORE_CUSTOMISE_QUERY")
public class Customise extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "customise_id")
    private String id;
    private String userCode;

    @ApiModelProperty(value = "机构编号", dataType = "String", example = "10010")
    @Column(name = "org_code", length = 32)
    private String orgCode;
    @ApiModelProperty(value = "字段备注", dataType = "String")
    private String pageId;
    @ApiModelProperty(value = "查询条件名称", dataType = "String", example = "简易查询")
    private String customiseName;

    private String fieldContents;

    private String fieldAddons;


    public String getId() {
        return id;
    }

    public void setId(String customiseId) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getCustomiseName() {
        return customiseName;
    }

    public void setCustomiseName(String customiseName) {
        this.customiseName = customiseName;
    }

    public String getFieldContents() {
        return fieldContents;
    }

    public void setFieldContents(String fieldContents) {
        this.fieldContents = fieldContents;
    }

    public String getFieldAddons() {
        return fieldAddons;
    }

    public void setFieldAddons(String fieldAddons) {
        this.fieldAddons = fieldAddons;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userCode", userCode)
                .append("orgCode", orgCode)
                .append("pageId", pageId)
                .append("customiseName", customiseName)
                .append("fieldContents", fieldContents)
                .append("fieldAddons", fieldAddons)
                .append(super.toString())
                .toString();
    }
}
