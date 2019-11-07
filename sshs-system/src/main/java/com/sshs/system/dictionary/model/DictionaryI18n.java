package com.sshs.system.dictionary.model;

import com.sshs.core.util.UUIdGenId;
import org.apache.ibatis.type.Alias;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典
 * 
 * @author Suny
 * 
 */
@Alias("DictionaryI18n")
@Table(name = "SYS_DICTIONARIES_I18N")
public class DictionaryI18n implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@KeySql(genId = UUIdGenId.class)
	private String i18nId;
	private String dictId;
	private String dictCode;
	private String language;
	private String country;
	private String dictName;
	private String dictDesc;
	private String status;
	private String legalOrg;
	private String updUserCode;
	private Date updDate;

	public String getI18nId() {
		return i18nId;
	}

	public void setI18nId(String i18nId) {
		this.i18nId = i18nId;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getLegalOrg() {
		return legalOrg;
	}

	public void setLegalOrg(String legalOrg) {
		this.legalOrg = legalOrg;
	}

	public String getUpdUserCode() {
		return updUserCode;
	}

	public void setUpdUserCode(String updUserCode) {
		this.updUserCode = updUserCode;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

}
