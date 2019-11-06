package com.sshs.system.org.model;

import org.apache.ibatis.type.Alias;

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
 * 系统管理->系统管理-机构表bean Org类
 *
 * @author 61910
 * @date 2018/11/07
 */
@Alias("Org")
@Table(name = "SYS_ORG")
public class Org implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     * 机构ID
     */
    @Id
    @Column(name = "ORG_ID")
    private String id;

    /**
     * 状态：01--启用,02--停用
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 国家或地区:中国、中国香港、美国等,字典表数据
     */
    @Column(name = "COUNTRY")
    private String country;

    /**
     * 联系邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 办公地址
     */
    @Column(name = "OFFICE_ADDR")
    private String officeAddr;

    /**
     * 邮政编码
     */
    @Column(name = "POST")
    private String post;

    /**
     * 联系电话
     */
    @Column(name = "TEL")
    private String tel;

    /**
     * 联系人
     */
    @Column(name = "CONTACT")
    private String contact;

    /**
     * 金融机构编码（人行）
     */
    @Column(name = "ORG_FI_NO")
    private String orgFiNo;

    /**
     * 支付行号（人行）
     */
    @Column(name = "ORG_PAY_NO")
    private String orgPayNo;

    /**
     * 机构信用代码（人行）
     */
    @Column(name = "ORG_CREDIT_NO")
    private String orgCreditNo;

    /**
     * 机构编码
     */
    @Column(name = "ORG_CODE")
    private String orgCode;

    /**
     * 金融许可证号码（银监）
     */
    @Column(name = "ORG_PERMIT_NO")
    private String orgPermitNo;

    /**
     * 营业执照注册号（工商）
     */
    @Column(name = "ORG_REG_NO")
    private String orgRegNo;

    /**
     * 组织机构代码证号码
     */
    @Column(name = "ORG_ID_NUMBER")
    private String orgIdNumber;

    /**
     * 税务登记证号码
     */
    @Column(name = "ORG_TAX_NO")
    private String orgTaxNo;

    /**
     * 成立日期
     */
    @Column(name = "ORG_CREATE_DATE")
    private Date orgCreateDate;

    /**
     * 营业开始时间
     */
    @Column(name = "ORG_START_DATE")
    private String orgStartDate;

    /**
     * 营业终止时间
     */
    @Column(name = "ORG_END_DATE")
    private String orgEndDate;

    /**
     * 机构负责人
     */
    @Column(name = "ORG_HEADER")
    private String orgHeader;

    /**
     * 网点号
     */
    @Column(name = "NODE_NO")
    private String nodeNo;

    /**
     * 是否最末端
     */
    @Column(name = "IS_LEAF")
    private String isLeaf;

    /**
     * 机构名称
     */
    @Column(name = "ORG_NAME")
    private String orgName;

    /**
     * 营业状态
     */
    @Column(name = "BUSI_STATUS")
    private String busiStatus;

    /**
     * 行政区划代码
     */
    @Column(name = "AREA_CODE")
    private String areaCode;

    /**
     * 顺序号
     */
    @Column(name = "ORDERS")
    private BigDecimal orders;

    /**
     * 所属条线
     */
    @Column(name = "OWN_LINE")
    private String ownLine;

    /**
     * 版本号
     */
    @Column(name = "VER_NO")
    private BigDecimal verNo;

    /**
     * 审批流ID
     */
    @Column(name = "PROCESS_INSTANCE_ID")
    private String processInstanceId;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

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
     * 机构简称
     */
    @Column(name = "ORG_BRIEF")
    private String orgBrief;

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
     * 直接上级机构ID
     */
    @Column(name = "PARENT_ORG_CODE")
    private String parentOrgCode;

    /**
     * 机构类别:01--机构,02--部门
     */
    @Column(name = "ORG_CATEGORY")
    private String orgCategory;

    /**
     * 机构层级（树形结构层级）
     */
    @Column(name = "ORG_LEVEL")
    private String orgLevel;

    /**
     * 机构级别：总行，分行，支行等
     */
    @Column(name = "ORG_GRADE")
    private String orgGrade;

    /**
     * 中文路径：用‘.’隔开各层级路径名称:.a.b.c.
     */
    @Column(name = "PATH_NAME")
    private String pathName;
    @Transient
    private List<Org> children;

    public String getId() {
        return this.id;
    }

    public void setId(String orgId) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficeAddr() {
        return this.officeAddr;
    }

    public void setOfficeAddr(String officeAddr) {
        this.officeAddr = officeAddr;
    }

    public String getPost() {
        return this.post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getOrgFiNo() {
        return this.orgFiNo;
    }

    public void setOrgFiNo(String orgFiNo) {
        this.orgFiNo = orgFiNo;
    }

    public String getOrgPayNo() {
        return this.orgPayNo;
    }

    public void setOrgPayNo(String orgPayNo) {
        this.orgPayNo = orgPayNo;
    }

    public String getOrgCreditNo() {
        return this.orgCreditNo;
    }

    public void setOrgCreditNo(String orgCreditNo) {
        this.orgCreditNo = orgCreditNo;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgPermitNo() {
        return this.orgPermitNo;
    }

    public void setOrgPermitNo(String orgPermitNo) {
        this.orgPermitNo = orgPermitNo;
    }

    public String getOrgRegNo() {
        return this.orgRegNo;
    }

    public void setOrgRegNo(String orgRegNo) {
        this.orgRegNo = orgRegNo;
    }

    public String getOrgIdNumber() {
        return this.orgIdNumber;
    }

    public void setOrgIdNumber(String orgIdNumber) {
        this.orgIdNumber = orgIdNumber;
    }

    public String getOrgTaxNo() {
        return this.orgTaxNo;
    }

    public void setOrgTaxNo(String orgTaxNo) {
        this.orgTaxNo = orgTaxNo;
    }

    public Date getOrgCreateDate() {
        return this.orgCreateDate;
    }

    public void setOrgCreateDate(Date orgCreateDate) {
        this.orgCreateDate = orgCreateDate;
    }

    public String getOrgStartDate() {
        return this.orgStartDate;
    }

    public void setOrgStartDate(String orgStartDate) {
        this.orgStartDate = orgStartDate;
    }

    public String getOrgEndDate() {
        return this.orgEndDate;
    }

    public void setOrgEndDate(String orgEndDate) {
        this.orgEndDate = orgEndDate;
    }

    public String getOrgHeader() {
        return this.orgHeader;
    }

    public void setOrgHeader(String orgHeader) {
        this.orgHeader = orgHeader;
    }

    public String getNodeNo() {
        return this.nodeNo;
    }

    public void setNodeNo(String nodeNo) {
        this.nodeNo = nodeNo;
    }

    public String getIsLeaf() {
        return this.isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getBusiStatus() {
        return this.busiStatus;
    }

    public void setBusiStatus(String busiStatus) {
        this.busiStatus = busiStatus;
    }

    public String getAreaCode() {
        return this.areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public BigDecimal getOrders() {
        return this.orders;
    }

    public void setOrders(BigDecimal orders) {
        this.orders = orders;
    }

    public String getOwnLine() {
        return this.ownLine;
    }

    public void setOwnLine(String ownLine) {
        this.ownLine = ownLine;
    }

    public BigDecimal getVerNo() {
        return this.verNo;
    }

    public void setVerNo(BigDecimal verNo) {
        this.verNo = verNo;
    }

    public String getProcessInstanceId() {
        return this.processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCrtUserCode() {
        return this.crtUserCode;
    }

    public void setCrtUserCode(String crtUserCode) {
        this.crtUserCode = crtUserCode;
    }

    public String getCrtOrgCode() {
        return this.crtOrgCode;
    }

    public void setCrtOrgCode(String crtOrgCode) {
        this.crtOrgCode = crtOrgCode;
    }

    public Date getCrtDate() {
        return this.crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getOrgBrief() {
        return this.orgBrief;
    }

    public void setOrgBrief(String orgBrief) {
        this.orgBrief = orgBrief;
    }

    public String getUpdUserCode() {
        return this.updUserCode;
    }

    public void setUpdUserCode(String updUserCode) {
        this.updUserCode = updUserCode;
    }

    public String getUpdOrgCode() {
        return this.updOrgCode;
    }

    public void setUpdOrgCode(String updOrgCode) {
        this.updOrgCode = updOrgCode;
    }

    public Date getUpdDate() {
        return this.updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public String getParentOrgCode() {
        return this.parentOrgCode;
    }

    public void setParentOrgCode(String parentOrgCode) {
        this.parentOrgCode = parentOrgCode;
    }

    public String getOrgCategory() {
        return this.orgCategory;
    }

    public void setOrgCategory(String orgCategory) {
        this.orgCategory = orgCategory;
    }

    public String getOrgLevel() {
        return this.orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getOrgGrade() {
        return this.orgGrade;
    }

    public void setOrgGrade(String orgGrade) {
        this.orgGrade = orgGrade;
    }

    public String getPathName() {
        return this.pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }


    public List<Org> getChildren() {
        return children;
    }

    public void setChildren(List<Org> children) {
        this.children = children;
    }

    public void addChild(Org child) {
        if (this.children == null) {
            this.children = new ArrayList<Org>();
        }
        this.children.add(child);
    }
}