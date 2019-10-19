package com.scai.core.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过反射copy两个javabean
 * 
 * @author lifeng
 *
 */
public class BeanUtil {

	/**
	 * 把第一个对象的相同字段值(经过处理)复制到第二个对应的相同的属性值
	 * 
	 * @param oldObj
	 *            被复制处理的对象
	 * @param newObj
	 *            得到放射后的对象
	 */
	public static Object copyProperties(Object oldObj, Object newObj) throws Exception {
		Method[] method1 = oldObj.getClass().getMethods();
		Method[] method2 = newObj.getClass().getMethods();
		String methodName1;
		String methodFix1;
		String methodName2;
		String methodFix2;
		for (int i = 0; i < method1.length; i++) {
			methodName1 = method1[i].getName();
			methodFix1 = methodName1.substring(3, methodName1.length());
			if (methodName1.startsWith("get")) {
				for (int j = 0; j < method2.length; j++) {
					methodName2 = method2[j].getName();
					methodFix2 = methodName2.substring(3, methodName2.length());
					if (methodName2.startsWith("set")) {
						if (methodFix2.equals(methodFix1)) {
							Object[] objs1 = new Object[0];
							Object[] objs2 = new Object[1];
							// 激活obj1的相应的get的方法，objs1数组存放调用该方法的参数,此例中没有参数，该数组的长度为0
							objs2[0] = method1[i].invoke(oldObj, objs1);
							// 激活obj2的相应的set的方法，objs2数组存放调用该方法的参数
							method2[j].invoke(newObj, objs2);
							continue;
						}
					}
				}
			}
		}
		return newObj;
	}

	/**
	 * 两个相同对象属性对比
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static Map<String, Boolean> contrastObj(Object obj1, Object obj2) {
		Map<String, Boolean> mapList = new HashMap<String, Boolean>(10);
		String methodString = "";
		try {
			Class<? extends Object> clazz = obj1.getClass();
			Field[] fields = obj1.getClass().getDeclaredFields();
			for (Field field : fields) {
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
				Method getMethod = pd.getReadMethod();
				Object o1 = getMethod.invoke(obj1);
				Object o2 = getMethod.invoke(obj2);

				methodString = getMethod.getName().substring(3, 4).toLowerCase();
				methodString = methodString + getMethod.getName().substring(4, getMethod.getName().length());

				if (!o1.toString().equals(o2.toString())) {
					mapList.put(methodString, false);
				} else {
					mapList.put(methodString, true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapList;
	}

}