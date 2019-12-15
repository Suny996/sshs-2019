package com.sshs.core.model;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public abstract class BaseEntity implements Serializable {
    @ApiModelProperty(value = "创建人编号", dataType = "String", example = "020001")
    @Column(name = "CRT_USER_CODE", length = 32)
    @TableField(fill = FieldFill.INSERT)
    private String crtUserCode;
    @ApiModelProperty(value = "创建人机构号", dataType = "String", example = "10010")
    @Column(name = "CRT_ORG_CODE", length = 32)
    @TableField(fill = FieldFill.INSERT)
    private String crtOrgCode;
    @ApiModelProperty(value = "创建时间", dataType = "Date")
    @Column(name = "CRT_DATE")
    @TableField(fill = FieldFill.INSERT)
    private Date crtDate;
    @ApiModelProperty(value = "最后修改人编号", dataType = "String", example = "020002")
    @Column(name = "UPD_USER_CODE", length = 32)
    @TableField(fill = FieldFill.UPDATE)
    private String updUserCode;
    @ApiModelProperty(value = "最后修改人机构号", dataType = "String", example = "10010")
    @Column(name = "UPD_ORG_CODE", length = 32)
    @TableField(fill = FieldFill.UPDATE)
    private String updOrgCode;
    @ApiModelProperty(value = "最后修改时间", dataType = "Date")
    @Column(name = "UPD_DATE")
    @TableField(fill = FieldFill.UPDATE)
    private Date updDate;
    /**
     * 查询条件匹配方式
     */
    @TableField(exist = false)
    public Object operators = new HashMap();
    @TableField(exist = false)
    String _orgAuth;
    @TableField(exist = false)
    String _userAuth;


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

    public Object getOperators() {
        return operators;
    }

    public void setOperators(Object operators) {
        if (operators != null && operators instanceof String) {
            this.operators = JSON.parse(operators.toString());
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("crtUserCode", crtUserCode)
                .append("crtOrgCode", crtOrgCode)
                .append("crtDate", crtDate)
                .append("updUserCode", updUserCode)
                .append("updOrgCode", updOrgCode)
                .append("updDate", updDate)
                .toString();
    }
}
