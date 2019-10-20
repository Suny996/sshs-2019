package com.sshs.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 代码中获取bean工具类
 * 
 * @author Suny
 * @date 2017-12-01
 */
@Component
public class SpringUtil implements ApplicationContextAware {
	static Logger logger = LoggerFactory.getLogger(SpringUtil.class);
	/**
	 * Spring应用上下文环境
	 */
	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的回调方法。设置上下文环境
	 * 
	 * @param applicationContext
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringUtil.applicationContext = applicationContext;
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取对象
	 * 
	 * @param name
	 * @return Object
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	public static Object getComponent(String name) {
		Object object = null;
		try {
			object = applicationContext.getBean(name);
		} catch (BeansException e) {
			logger.warn("获取Component[" + name + "]错误!" + e.getMessage());
		}
		return object;
	}
}