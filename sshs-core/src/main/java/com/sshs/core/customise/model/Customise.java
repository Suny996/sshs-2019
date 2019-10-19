package com.sshs.core.customise.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * 自定义查询
 *
 * @author Suny
 * @date 2017-12-10
 */
@Alias("Customise")
@TableName("CORE_CUSTOMISE_QUERY")
public class Customise extends Model<Customise> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "customise_id", type = IdType.UUID)
    private String customiseId;
    private String userCode;

    @Column(name = "org_code", length = 32)
    private String orgCode;
    private String pageId;
    private String customiseName;

    private String fieldContents;

    private String fieldAddons;

    private String crtUserCode;

    private String crtOrgCode;
    private Date crtDate;
    private String updUserCode;
    private String updOrgCode;
    private Date updDate;

    public String getCustomiseId() {
        return customiseId;
    }

    public void setCustomiseId(String customiseId) {
        this.customiseId = customiseId;
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

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
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

}
