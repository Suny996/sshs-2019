package com.sshs.system.user.model;

import com.sshs.core.model.BaseEntity;
import com.sshs.core.util.UUIdGenId;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.ibatis.type.Alias;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统管理->系统管理-用户表bean User类
 *
 * @author Suny
 * @date 2018/01/09
 */
@Alias("User")
@Table(name = "SYS_USER")
public class User extends BaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @Id
    @KeySql(genId = UUIdGenId.class)
    @Column(name = "USER_ID", length = 32)
    private String userId;

    /**
     * 用户编号
     */
    @Column(name = "USER_CODE")
    private String userCode;

    /**
     * 姓名
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 英文名
     */
    @Column(name = "USER_NAME_EN")
    private String userNameEn;

    /**
     * 拼音码
     */
    @Column(name = "USER_NAME_PY")
    private String userNamePy;

    /**
     * 曾用名
     */
    @Column(name = "USER_NAME_FR")
    private String userNameFr;

    /**
     * 机构编号:到具体的部门、支行和二级中心
     */
    @Column(name = "ORG_CODE")
    private String orgCode;

    /**
     * 岗位
     */
    @Column(name = "POST_NO")
    private String postNo;

    /**
     * 性别:1－男性，2－女性，9－未说明性别
     */
    @Column(name = "USER_SEX")
    private String userSex;

    /**
     * 国籍
     */
    @Column(name = "NATIONALITY")
    private String nationality;

    /**
     * 民族:01=汉族
     */
    @Column(name = "ETHNIC")
    private String ethnic;

    /**
     * 籍贯
     */
    @Column(name = "NATIVE_PLACE")
    private String nativePlace;

    /**
     * 出生地
     */
    @Column(name = "BIRTH_PLACE")
    private String birthPlace;

    /**
     * 出生日期
     */
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    /**
     * 身份证号码
     */
    @Column(name = "ID_DECIMAL")
    private String idDecimal;

    /**
     * 证件照
     */
    @Column(name = "PHOTO_ID")
    private String photoId;

    /**
     * 入职日期
     */
    @Column(name = "JOIN_DATE")
    private Date joinDate;

    /**
     * 用户状态:0-无效，1-有效
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 柜员编号
     */
    @Column(name = "TELLER_ID")
    private String tellerId;

    /**
     * 现居住地址
     */
    @Column(name = "RESIDENTIAL_ADDRESS")
    private String residentialAddress;

    /**
     * 手机
     */
    @Column(name = "MOBILE_PHONE")
    private String mobilePhone;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 盐（密码）
     */
    @Column(name = "SALT")
    private String salt;

    /**
     * 在线状态:在线、离线、锁定
     */
    @Column(name = "ONLINE_STATUS")
    private String onlineStatus;

    /**
     * IP地址
     */
    @Column(name = "IP_ADDR")
    private String ipAddr;

    /**
     * 用户皮肤
     */
    @Column(name = "USER_THEME")
    private String userTheme;

    /**
     * 密码输入次数
     */
    @Column(name = "PD_COUNT")
    private String pdCount;

    /**
     * 密码修改时间
     */
    @Column(name = "PD_MODTIME")
    private String pdModtime;

    /**
     * 密码锁定时间
     */
    @Column(name = "PD_LOCKTIME")
    private String pdLocktime;

    /**
     * 最后一次登陆时间
     */
    @Column(name = "LAST_SIGNON_TIME")
    private Date lastSignonTime;

    /**
     * 最后一次退出时间
     */
    @Column(name = "LAST_SIGNOUT_TIME")
    private Date lastSignoutTime;

    /**
     * 法人机构编号
     */
    @Column(name = "LEGAL_ORG")
    private String legalOrg;

    /**
     * role角色
     */
    @Transient
    private List<String> roleCodes;


    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String id) {
        this.userId = id;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNameEn() {
        return this.userNameEn;
    }

    public void setUserNameEn(String userNameEn) {
        this.userNameEn = userNameEn;
    }

    public String getUserNamePy() {
        return this.userNamePy;
    }

    public void setUserNamePy(String userNamePy) {
        this.userNamePy = userNamePy;
    }

    public String getUserNameFr() {
        return this.userNameFr;
    }

    public void setUserNameFr(String userNameFr) {
        this.userNameFr = userNameFr;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getPostNo() {
        return this.postNo;
    }

    public void setPostNo(String postNo) {
        this.postNo = postNo;
    }

    public String getUserSex() {
        return this.userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEthnic() {
        return this.ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getNativePlace() {
        return this.nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdDecimal() {
        return this.idDecimal;
    }

    public void setIdDecimal(String idDecimal) {
        this.idDecimal = idDecimal;
    }

    public String getPhotoId() {
        return this.photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public Date getJoinDate() {
        return this.joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTellerId() {
        return this.tellerId;
    }

    public void setTellerId(String tellerId) {
        this.tellerId = tellerId;
    }

    public String getResidentialAddress() {
        return this.residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getOnlineStatus() {
        return this.onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getIpAddr() {
        return this.ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getUserTheme() {
        return this.userTheme;
    }

    public void setUserTheme(String userTheme) {
        this.userTheme = userTheme;
    }

    public String getPdCount() {
        return this.pdCount;
    }

    public void setPdCount(String pdCount) {
        this.pdCount = pdCount;
    }

    public String getPdModtime() {
        return this.pdModtime;
    }

    public void setPdModtime(String pdModtime) {
        this.pdModtime = pdModtime;
    }

    public String getPdLocktime() {
        return this.pdLocktime;
    }

    public void setPdLocktime(String pdLocktime) {
        this.pdLocktime = pdLocktime;
    }

    public Date getLastSignonTime() {
        return this.lastSignonTime;
    }

    public void setLastSignonTime(Date lastSignonTime) {
        this.lastSignonTime = lastSignonTime;
    }

    public Date getLastSignoutTime() {
        return this.lastSignoutTime;
    }

    public void setLastSignoutTime(Date lastSignoutTime) {
        this.lastSignoutTime = lastSignoutTime;
    }

    public String getLegalOrg() {
        return this.legalOrg;
    }

    public void setLegalOrg(String legalOrg) {
        this.legalOrg = legalOrg;
    }

    public List<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(List<String> roleCodes) {
        this.roleCodes = roleCodes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("userCode", userCode)
                .append("userName", userName)
                .append("userNameEn", userNameEn)
                .append("userNamePy", userNamePy)
                .append("userNameFr", userNameFr)
                .append("orgCode", orgCode)
                .append("postNo", postNo)
                .append("userSex", userSex)
                .append("nationality", nationality)
                .append("ethnic", ethnic)
                .append("nativePlace", nativePlace)
                .append("birthPlace", birthPlace)
                .append("birthDate", birthDate)
                .append("idDecimal", idDecimal)
                .append("photoId", photoId)
                .append("joinDate", joinDate)
                .append("status", status)
                .append("tellerId", tellerId)
                .append("residentialAddress", residentialAddress)
                .append("mobilePhone", mobilePhone)
                .append("remark", remark)
                .append("password", password)
                .append("salt", salt)
                .append("onlineStatus", onlineStatus)
                .append("ipAddr", ipAddr)
                .append("userTheme", userTheme)
                .append("pdCount", pdCount)
                .append("pdModtime", pdModtime)
                .append("pdLocktime", pdLocktime)
                .append("lastSignonTime", lastSignonTime)
                .append("lastSignoutTime", lastSignoutTime)
                .append("legalOrg", legalOrg)
                .append("roleCodes", roleCodes)
                .toString();
    }
}