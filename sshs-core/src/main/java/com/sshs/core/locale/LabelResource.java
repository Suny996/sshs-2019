package com.sshs.core.locale;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 页面标签信息国际化
 * 
 * @author Suny
 * @date 2017-11-20
 */
public class LabelResource {
	ResourceBundle pubResource = null;
	ResourceBundle privateResource = null;
	Locale locale = new Locale("zh", "CN");
	String pageId="";

	public LabelResource(Locale locale, ResourceBundle pubResource, ResourceBundle privateResource) {
		super();
		if (locale != null) {
			this.locale = locale;
		}
		this.pubResource = pubResource;
		this.privateResource = privateResource;
	}

	/**
	 * 获取label
	 * 
	 * @param name
	 * @param defaultName
	 * @return
	 */
	public String getLabel(String name, String defaultName) {
		String value = null;
		if (this.privateResource != null) {
			try {
				value = new String(this.privateResource.getString(name).getBytes("ISO-8859-1"), "UTF-8");
			} catch (MissingResourceException e) {
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (value == null && this.pubResource != null) {
			try {
				value = new String(this.pubResource.getString(name).getBytes("ISO-8859-1"), "UTF-8");
			} catch (MissingResourceException e) {
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (value == null) {
			value = defaultName;
		}
		return value;
	}

	/**
	 * 获取label
	 * 
	 * @param name
	 * @return
	 */
	public String getLabel(String name) {
		return this.getLabel(name, name);
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
}
