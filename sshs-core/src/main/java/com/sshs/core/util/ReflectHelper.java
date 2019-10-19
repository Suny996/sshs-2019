package com.sshs.core.util;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明：反射工具
 * 
 * @date 2017年9月20日
 * @author Suny
 * @version 1.0
 */
public class ReflectHelper {

	private static Pattern patternProperty = Pattern.compile("([A-Za-z\\d]+)(_)?");
	
	private static Pattern patternColumn = Pattern.compile("[A-Z]([a-z\\d]+)?");

	/**
	 * 获取obj对象fieldName的Field
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */

	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if (field != null) {
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName, Object value)
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}

	/**
	 * 下划线转小驼峰法
	 * 
	 * @param columnName
	 *            源字符串
	 * @return 转换后的字符串
	 */
	public static String getPropertyName(String columnName) {
		return getPropertyName(columnName, true);
	}

	/**
	 * 下划线转驼峰法
	 * 
	 * @param columnName
	 *            源字符串
	 * @param smallCamel
	 *            大小驼峰,是否为小驼峰
	 * @return 转换后的字符串
	 */
	public static String getPropertyName(String columnName, boolean smallCamel) {
		if (columnName == null || "".equals(columnName)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		// Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
		Matcher matcher = patternProperty.matcher(columnName);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0))
					: Character.toUpperCase(word.charAt(0)));
			int index = word.lastIndexOf('_');
			if (index > 0) {
				sb.append(word.substring(1, index).toLowerCase());
			} else {
				sb.append(word.substring(1).toLowerCase());
			}
		}
		return sb.toString();
	}

	/**
	 * 驼峰法转下划线
	 * 
	 * @param property
	 *            源字符串
	 * @return 转换后的字符串
	 */
	public static String getColumnName(String property) {
		if (property == null || "".equals(property)) {
			return "";
		}
		property = String.valueOf(property.charAt(0)).toUpperCase().concat(property.substring(1));
		StringBuffer sb = new StringBuffer();
		// Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
		Matcher matcher = patternColumn.matcher(property);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(word.toUpperCase());
			sb.append(matcher.end() == property.length() ? "" : "_");
		}
		return sb.toString();
	}



	/**
	 * 首字母大写转换
	 * 
	 * @param name
	 * @return
	 */
	public static String capitalName(String name) {
		if (StringUtils.isNotEmpty(name)) {
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		return name;

	}

	/*public static void main(String[] args) {
		String line = "I_HAVE_AN_IPANG3_PIG";
		String camel = getPropertyName(line, true);
		System.out.println(camel);
		System.out.println(getColumnName(camel));
	}*/
}
