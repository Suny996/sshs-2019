  


package com.sshs.core.util;
/**
 * 文件名：BusiUtil.java
 *
 * 创建人：barry.wang
 * 创建时间：2018年5月25日 下午2:40:54
 * 版本信息：   v1.0
 * 日期：2018年5月25日
 * 版权所有
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**   
*      
* 类名称：BusiUtil
* 类描述：   
* 创建人：barry.wang 
* 创建时间：2018年5月25日 下午2:40:54   
* 修改人： 
* 修改时间：   
* 修改备注：   
* @version v1.0   
*   
*/
public class BusiUtil {

	private static final Logger logger = LoggerFactory.getLogger(BusiUtil.class);

	/**
	 * 金额类精度
	 */
	private static final int MONEY_SCALE = 3;

	/**
	 * 利率类精度
	 */
	private static final int RATE_SCALE = 7;

	/**
	 * 默认除法精度
	 */
	private static final int DIV_SCALE = 50;

	/**
	 * 零金额
	 */
	private static final BigDecimal MONEY_ZERO = BigDecimal.ZERO;

	/**
	 * 零利率
	 */
	private static final BigDecimal RATE_ZERO = new BigDecimal(0.0000001);

	/**
	 * 空串
	 */
	private static final String STRING_NULL = "";

	/**
	 * 空整型
	 */
	private static final Long LONG_NULL = new Long(0);
	

	/**
	 * 判断字符串是否为数字型。
	 * 
	 * @param str
	 * @return true/false
	 */
	public static boolean isNumeric(String str) {
		if (str == null || str.length() == 0)
			return false;
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断value是否为空
	 * 
	 * @param value
	 * @return true/false
	 */
	public static boolean isNull(String value) {
		return null == value;
	}

	/**
	 * 判断Object是否为空
	 * 
	 * @param object
	 * @return true/false
	 */
	public static boolean isEmpty(Object object) {
		return null == object;
	}

	/**
	 * 判断Object数组是否为空
	 * 
	 * @param objects
	 * @return true/false
	 */
	public static boolean isEmpty(Object[] objects) {
		return null == objects || objects.length == 0;
	}

	/**
	 * 判断Object是否不为空
	 * 
	 * @param object
	 * @return true/false
	 */
	public static boolean isNotEmpty(Object object) {
		return !isEmpty(object);
	}

	/**
	 * 判断Object数组是否不为空
	 * 
	 * @param objects
	 * @return true/false
	 */
	public static boolean isNotEmpty(Object[] objects) {
		return !isEmpty(objects);
	}

	/**
	 * 判断String是否为空
	 * 
	 * @param str
	 * @return true/false
	 */
	public static boolean isEmpty(String str) {
		return null == str || 0 == str.trim().length();
	}

	/**
	 * 判断String是否不为空
	 * 
	 * @param str
	 * @return true/false
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && str.trim().length() > 0;
	}

	/**
	 * 判断List是否为空
	 * 
	 * @param list
	 * @return true/false
	 */
	public static boolean isEmpty(List list) {
		return null == list || 0 == list.size();
	}

	/**
	 * 判断List是否不为空
	 * 
	 * @param list
	 * @return true/false
	 */
	public static boolean isNotEmpty(List list) {
		return !isEmpty(list);
	}

	/**
	 * 判断Map是否为空
	 * 
	 * @param map
	 * @return true/false
	 */
	public static boolean isEmpty(Map map) {
		return null == map || 0 == map.size();
	}

	/**
	 * 判断Map是否不为空
	 * 
	 * @param map
	 * @return true/false
	 */
	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

	/**
	 * 组成字符串方法
	 * 
	 * @param value 变参
	 * @return String
	 */
	public static BigDecimal toBigDecimal(Object value) {
		if (value == null)
			return null;
		if (value instanceof BigDecimal) {
			return (BigDecimal) value;
		} else if (value instanceof Double) {
			String s = String.format("%f", (Double) value);
			return new BigDecimal(s);
		} else if (value instanceof Float) {
			String s = String.format("%f", (Float) value);
			return new BigDecimal(s);
		} else if (value instanceof Long) {
			String s = String.format("%d", (Long) value);
			return new BigDecimal(s);
		} else if (value instanceof Integer) {
			String s = String.format("%d", (Integer) value);
			return new BigDecimal(s);
		} else if (value instanceof String) {
			return new BigDecimal((String) value);
		} else {
			return new BigDecimal(value.toString());
		}
	}

	/**
	 * 转换成Integer
	 * 
	 * @param value value
	 * @return Integer
	 */
	public static Integer toInteger(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof Integer) {
			return (Integer) value;
		} else {
			try {
				return new Integer(value.toString());
			} catch (Exception e) {
				return null;
			}
		}
	}

	/**
	 * BigDecimal转换成Long, 如果BigDecimal包含有效小数位，则会报错。
	 * 
	 * @param value value
	 * @return Long
	 */
	public static Long toLong(BigDecimal value) {
		if (value == null) {
			return null;
		}
		return value.longValueExact();
	}

	/**
	 * 转换成Long
	 * 
	 * @param value value
	 * @return Long
	 */
	public static Long toLong(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof Long) {
			return (Long) value;
		} else {
			try {
				return new Long(value.toString());
			} catch (Exception e) {
				return null;
			}
		}
	}

	/**
	 * 转换成Boolean
	 * 
	 * @param str value
	 * @return boolean
	 */
	public final static boolean toBoolean(String str) {
		return toBoolean(str, false);
	}

	/**
	 * 转换成Boolean,为空时赋值缺省值.
	 * 
	 * @param
	 * @return boolean
	 */
	public final static boolean toBoolean(String str, boolean defaultValue) {
		if (isEmpty(str)) {
			return defaultValue;
		} else
			return new Boolean(str.trim()).booleanValue();

	}

	/**
	 * 转换成int.
	 * 
	 * @param  str
	 * @return int
	 */
	public static final int toInt(String str) {
		return toInt(str, 0);
	}

	/**
	 * 转换成int,为空则赋缺省值.
	 * 
	 * @param str
	 * @param defaultValue
	 * @return int
	 */
	public static final int toInt(String str, int defaultValue) {
		if (isEmpty(str)) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(str.trim());
		} catch (Throwable t) {
			return defaultValue;
		}
	}

	/**
	 * Array的toString方法
	 * 
	 * @param array
	 * @return String
	 */
	public static String toString(Object[] array) {
		if (array == null)
			return null;
		String out = array.getClass().getSimpleName() + " [";
		for (int ii = 0; ii < array.length; ii++) {
			out += array[ii];
			if (ii + 1 < array.length)
				out += ",";
		}
		out += "]";
		return out;
	}

	/**
	 * Map的toString方法
	 * 
	 * @param map
	 * @return String
	 */
	public static String toString(Map<?, ?> map) {
		if (map == null)
			return null;
		String out = map.getClass().getSimpleName() + " {";
		Iterator<?> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			Object value = map.get(key);
			out += key + "=" + value;
			if (iter.hasNext())
				out += ",";
		}
		out += "}";
		return out;
	}

	/**
	 * List的toString方法
	 * 
	 * @param list
	 * @return String
	 */
	public static String toString(List list) {
		if (list == null)
			return null;
		String out = list.getClass().getSimpleName() + " [";
		for (int ii = 0; ii < list.size(); ii++) {
			out += list.get(ii);
			if (ii + 1 < list.size())
				out += ",";
		}
		out += "]";
		return out;
	}

	/**
	 * Set的toString方法
	 * 
	 * @param set
	 * @return String
	 */
	public static String toString(Set set) {
		if (set == null)
			return null;
		String out = set.getClass().getSimpleName() + " [";
		int ii = 0;
		for (Object o : set) {
			out += o;
			if (++ii + 1 < set.size())
				out += ",";
		}
		out += "]";
		return out;
	}

	public static List toList(Object[] array) {
		if (array == null)
			return null;
		return Arrays.asList(array);
	}

	public static <T> T[] toArray(List<T> list, Class<? extends T> newType) {
		if (list == null || list.size() == 0)
			return null;
		return (T[]) list.toArray((T[]) Array.newInstance(newType, list.size()));

		// Array.new
		// return (T[]) Arrays.copyOf(list.toArray(), list.size(), newType);
	}

	/**
	 * 获取BigDecimal整数位值
	 * 
	 * @param bd
	 * @return Long
	 */
	public static Long longValue(BigDecimal bd) {
		return bd.longValue();
	}

	/**
	 * 获取BigDecimal小数位值
	 * 
	 * @param bd
	 * @return igDecimal
	 */
	public static BigDecimal decimalValue(BigDecimal bd) {
		return bd.subtract(new BigDecimal(bd.longValue()));
	}

	/**
	 * 去除BigDecimal末尾无效0.<br>
	 * 
	 * @param de BigDecimal
	 * @return
	 */
	public static BigDecimal stripTrailingZeros(BigDecimal de) {
		if (de == null)
			return null;

		/**
		 * 去除小数点后无效多余0.
		 */
		String ds = (de.stripTrailingZeros()).toPlainString();
		if (ds.indexOf(".") > 0) {
			String c = ds.substring(ds.length() - 1, ds.length());
			while (c != null && ("0".equals(c) || ".".equals(c))) {
				ds = ds.substring(0, ds.length() - 1);
				if (".".equals(c))
					break;
				if (ds != null && ds.length() > 0)
					c = ds.substring(ds.length() - 1, ds.length());
				else
					c = null;
			}
		}
		return new BigDecimal(ds);
	}

	/**
	 * 数组转成List. (List转Array则直接使用List.toArray())
	 * 
	 * @param array
	 * @return List
	 */
	public static final List<Object> arrayToList(Object[] array) {
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.length; i++)
			list.add(array[i]);
		return list;
	}

	/**
	 * String对象比较.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return 0 equal,-n 小于, n 大于。
	 */
	public static int compare(String obj1, String obj2) {
		if (obj1 == null && obj2 == null)
			return 0;
		if (obj1 == null)
			obj1 = "";
		if (obj2 == null)
			obj2 = "";
		return obj1.compareTo(obj2);
	}

	/**
	 * Long对象比较.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return 0 equal,-n 小于, n 大于。
	 */
	public static int compare(Long obj1, Long obj2) {
		if (obj1 == null && obj2 == null)
			return 0;
		if (obj1 == null)
			return -1;
		if (obj2 == null)
			return 1;
		return obj1.compareTo(obj2);
	}

	/**
	 * Integer对象比较.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return 0 equal,-n 小于, n 大于。
	 */
	public static int compare(Integer obj1, Integer obj2) {
		if (obj1 == null && obj2 == null)
			return 0;
		if (obj1 == null)
			return -1;
		if (obj2 == null)
			return 1;
		return obj1.compareTo(obj2);
	}

	/**
	 * BigDecimal对象比较.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return 0 equal,-n 小于, n 大于。
	 */
	@Deprecated
	public static int compare(BigDecimal obj1, BigDecimal obj2) {
		if (obj1 == null && obj2 == null)
			return 0;
		if (obj1 == null)
			return -1;
		if (obj2 == null)
			return 1;
		return obj1.compareTo(obj2);
	}

	/**
	 * 金额对象比较，BigDecimal，常量MONEY_ZERO表示金额0.00。
	 * 
	 * @param money1
	 * @param money2
	 * @return 0 equal,-n 小于, n 大于。
	 */
	public static int compareMoney(BigDecimal money1, BigDecimal money2) {
		if (money1 == null && money2 == null)
			return 0;
		if (money1 == null)
			return -1;
		if (money2 == null)
			return 1;

		BigDecimal m1 = money1.setScale(MONEY_SCALE, BigDecimal.ROUND_HALF_UP);
		BigDecimal m2 = money2.setScale(MONEY_SCALE, BigDecimal.ROUND_HALF_UP);
		return m1.compareTo(m2);
	}

	/**
	 * 利率对象比较，BigDecimal，常量RATE_ZERO表示利率0.0000000。
	 * 
	 * @param rate1
	 * @param rate2
	 * @return 0 equal,-n 小于, n 大于。
	 */
	public static int compareRate(BigDecimal rate1, BigDecimal rate2) {
		if (rate1 == null && rate2 == null)
			return 0;
		if (rate1 == null)
			return -1;
		if (rate2 == null)
			return 1;

		BigDecimal m1 = rate1.setScale(RATE_SCALE, BigDecimal.ROUND_HALF_UP);
		BigDecimal m2 = rate2.setScale(RATE_SCALE, BigDecimal.ROUND_HALF_UP);
		return m1.compareTo(m2);
	}

	/**
	 * 指定精度比较，BigDecimal
	 * 
	 * @param money1
	 * @param money2
	 * @param scale 精度
	 * @return 0 equal,-n 小于, n 大于。
	 */
	public static int compareWithScale(BigDecimal money1, BigDecimal money2, int scale) {
		if (money1 == null && money2 == null)
			return 0;
		if (money1 == null)
			return -1;
		if (money2 == null)
			return 1;

		if (scale < 0)
			scale = DIV_SCALE;
		BigDecimal m1 = money1.setScale(scale, BigDecimal.ROUND_HALF_UP);
		BigDecimal m2 = money2.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return m1.compareTo(m2);
	}

	/**
	 * 对象比较.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return 0 equal,-n 小于, n 大于。
	 */
	private static int compareX(Object obj1, Object obj2) {
		if (obj1 == null && obj2 == null)
			return 0;
		if (BigDecimal.class.isAssignableFrom(obj1.getClass()) && BigDecimal.class.isAssignableFrom(obj2.getClass())) {
			if ((obj1 == null && obj2 != null) || (obj1 != null && obj2 == null))
				throw new RuntimeException(
						"Invalid argument object1[" + obj1 + "] and object1[" + obj2 + "] to compare, the other one is null.");
			return ((BigDecimal) obj1).compareTo((BigDecimal) obj2);
		} else if (String.class.isAssignableFrom(obj1.getClass()) && String.class.isAssignableFrom(obj2.getClass())) {
			return ((String) obj1).compareTo((String) obj2);
		} else if (Integer.class.isAssignableFrom(obj1.getClass()) && Integer.class.isAssignableFrom(obj2.getClass())) {
			return (Integer) obj1 - (Integer) obj2;
		} else if (Long.class.isAssignableFrom(obj1.getClass()) && Long.class.isAssignableFrom(obj2.getClass())) {
			return (int) ((Long) obj1 - (Long) obj2);
		} else if (Boolean.class.isAssignableFrom(obj1.getClass()) && Boolean.class.isAssignableFrom(obj2.getClass())) {
			return (boolean) obj1 == (boolean) obj2 ? 0 : 1;
		}
		throw new RuntimeException("Invalid argument object1[" + obj1 + "] and object1[" + obj2 + "] to compare.");
	}

	/**
	 * 判断两个对象是否相等.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	// public static boolean equals(Object obj1, Object obj2) {
	// return compare(obj1, obj2) == 0;
	// }

	/**
	 * 判断两个String是否相等.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean equals(String obj1, String obj2) {
		return compare(obj1, obj2) == 0;
	}

	/**
	 * 判断两个String是否相等.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean equalsIgnoreCase(String obj1, String obj2) {
		if (obj1 == null && obj2 == null)
			return true;
		if (obj1 == null)
			obj1 = "";
		if (obj2 == null)
			obj2 = "";
		return obj1.equalsIgnoreCase(obj2);
	}

	/**
	 * 判断两个Long是否相等.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean equals(Long obj1, Long obj2) {
		return compare(obj1, obj2) == 0;
	}

	/**
	 * 判断两个Integer是否相等.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean equals(Integer obj1, Integer obj2) {
		return compare(obj1, obj2) == 0;
	}

	/**
	 * 判断两个BigDecimal是否相等.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	@Deprecated
	public static boolean equals(BigDecimal obj1, BigDecimal obj2) {
		return compare(obj1, obj2) == 0;
	}

	/**
	 * 判断两个金额是否相等.
	 * 
	 * @param money1
	 * @param money2
	 * @return true/false
	 */
	public static boolean equalsMoney(BigDecimal money1, BigDecimal money2) {
		return compareMoney(money1, money2) == 0;
	}

	/**
	 * 判断金额是否为零(MONEY_ZERO).
	 * 
	 * @param money1
	 * @return true/false
	 */
	public static boolean equalsMoneyZero(BigDecimal money1) {
		return compareMoney(money1, MONEY_ZERO) == 0;
	}

	/**
	 * 判断两个利率是否相等.
	 * 
	 * @param rate1
	 * @param rate2
	 * @return true/false
	 */
	// public static boolean equalsRate(BigDecimal rate1, BigDecimal rate2) {
	// return compareRate(rate1, rate2) == 0;
	// }

	/**
	 * 判断两个对象是否不相等.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	// public static boolean notEquals(Object obj1, Object obj2) {
	// return compare(obj1, obj2) != 0;
	// }

	/**
	 * 判断两个BigDecimal是否不相等.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean notEquals(BigDecimal obj1, BigDecimal obj2) {
		return compareMoney(obj1, obj2) != 0;
	}

	/**
	 * 判断两个String是否不相等.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean notEquals(String obj1, String obj2) {
		return compare(obj1, obj2) != 0;
	}

	/**
	 * 判断两个Long是否不相等.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean notEquals(Long obj1, Long obj2) {
		return compare(obj1, obj2) != 0;
	}

	/**
	 * 判断两个Integer是否不相等.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean notEquals(Integer obj1, Integer obj2) {
		return compare(obj1, obj2) != 0;
	}

	/**
	 ** 计算数字各位的和
	 ** 
	 * @param number
	 ** @return
	 **/
	private static int calcSum(int number) {
		String str = String.valueOf(number);
		int total = 0;
		for (int i = 0; i < str.length(); i++) {
			total += Integer.valueOf(Integer.parseInt(String.valueOf(str.charAt(i))));
		}
		return total;
	}

	/**
	 * 字符串转成16进制字符
	 * 
	 * @param str
	 * @return String 十六进制串
	 */
	public static String toHexString(String str) {

		StringBuilder stringBuilder = new StringBuilder();
		char[] chars = str.toCharArray();
		if (str == null || str.length() <= 0) {
			return null;
		}
		for (int i = 0; i < chars.length; i++) {
			int iChr = (int) chars[i];
			String chr = Integer.toHexString(iChr);
			stringBuilder.append(chr);
		}
		return stringBuilder.toString();

	}

	/**
	 * 16进制字符串转换为字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String hexToString(String s) {
		if (s == null || s.equals("")) {
			return null;
		}
		s = s.replace(" ", "");
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				logger.error("hexToString failed." + e);
			}
		}
		try {
			s = new String(baKeyword, "utf-8");
		} catch (Exception e1) {
			logger.error("hexToString failed." + e1);
		}
		return s;
	}

	/**
	 * 使用千分符格式化
	 * 
	 * @param sAmount 金额串
	 * @param iPoint 小数点位数
	 * @return String
	 */
	public static String formatAmount(String sAmount, int iPoint) {
		if (BusiUtil.isNull(sAmount)) {
			return null;
		}

		NumberFormat formater = null;
		double dAmount = Double.parseDouble(sAmount);

		if (iPoint == 0) {
			formater = new DecimalFormat("###,###,###,###");
		} else {
			StringBuffer buff = new StringBuffer();
			buff.append("###,###,###,###.");
			for (int i = 0; i < iPoint; i++) {
				buff.append("#");
			}
			formater = new DecimalFormat(buff.toString());
		}
		String ret = formater.format(dAmount);
		if (ret.indexOf(".") != -1) {
			String subffix = ret.substring(ret.indexOf(".") + 1);
			String tmp = subffix;
			for (int i = 0; i < (iPoint - tmp.length()); i++) {
				subffix += "0";
			}
			return ret.substring(0, ret.indexOf(".")) + "." + subffix;
		} else {
			ret += ".";
			for (int i = 0; i < iPoint; i++) {
				ret += "0";
			}
			return ret;
		}
	}

	/**
	 * 按指定大小进行字符串分割成数组.
	 * 
	 * @param str 原字符串
	 * @param averageSize 长度
	 * @return 分割后的字符串数组
	 */
	public static String[] splitToArrayBySize(String str, int averageSize) {
		if (str == null || str.length() == 0)
			return new String[0];
		if (averageSize >= str.length())
			return new String[] { str };
		int count = str.length() / averageSize + 1;
		String[] ret = new String[count];
		for (int i = 0; i < count; i++) {
			if ((i + 1) * averageSize < str.length())
				ret[i] = str.substring(i * averageSize, (i + 1) * averageSize);
			else
				ret[i] = str.substring(i * averageSize);
		}
		return ret;
	}

	/**
	 * 按照分隔符拆分字符串
	 * 
	 * @param V 原字符串
	 * @param splitor 分隔串
	 * @param ignoreEmpty 是否过滤空单元
	 * @return 分割后的List
	 */
	public static List<String> splitToList(String V, String splitor, boolean ignoreEmpty) {
		List<String> arr = new ArrayList<String>();
		int index = -1;
		String Value = V;
		String v = "";
		index = Value.indexOf(splitor);
		while (Value.length() > 0 || index >= 0) {
			v = "";
			if (index >= 0) {
				v = Value.substring(0, index);
				Value = Value.substring(index + splitor.length());
			} else {
				v = Value;
				Value = "";
			}
			if (!ignoreEmpty || !isEmpty(v))
				arr.add(v);
			index = Value.indexOf(splitor);
		}
		return arr;
	}

	/**
	 * 按照分隔符拆分后取指定序号的字段值
	 * 
	 * @param V 原字符串
	 * @param splitor 分隔串
	 * @param index 单元序号:1-n
	 * @return 分割后的String
	 */
	public static String getFieldBySplitor(String V, String splitor, int index) {
		if (index < 1)
			return null;
		List<String> list = splitToList(V, splitor, false);
		if (isEmpty(list) || index > list.size())
			return null;
		return list.get(index - 1);
	}

	/**
	 * 按照匹配模式拆分字符串 (过滤空单元)
	 * 
	 * @param V 原字符串
	 * @param regex 正则表达式(单字符的分隔符模式)
	 * @param ignoreEmpty 是否过滤空单元
	 * @return 分割后的List
	 */
	public static List<String> splitToListByRegex(String V, String regex, boolean ignoreEmpty) {
		String[] flds = V.split(regex);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < flds.length; i++)
			if (!ignoreEmpty || !isEmpty(flds[i].trim()))
				list.add(flds[i]);
		return list;
	}

	/**
	 * BigDecimal的加法
	 * 
	 * @param args BigDecimal可变参数
	 * @return BigDecimal
	 */
	public static BigDecimal plus(BigDecimal... args) {
		if (args == null)
			return null;
		BigDecimal bd = null;

		for (BigDecimal bigD : args) {
			if (bigD == null)
				continue;
			if (bd == null)
				bd = bigD;
			else
				bd = bd.add(bigD);
		}
		return bd;
	}

	/**
	 * BigDecimal的减法
	 * 
	 * @param args BigDecimal可变参数
	 * @return BigDecimal
	 */
	public static BigDecimal subtract(BigDecimal... args) {
		if (args == null)
			return null;
		BigDecimal bd = null;

		for (BigDecimal bigD : args) {
			if (bigD == null)
				continue;
			if (bd == null)
				bd = bigD;
			else
				bd = bd.subtract(bigD);
		}
		return bd;
	}

	/**
	 * BigDecimal的乘法
	 * 
	 * @param args BigDecimal可变参数
	 * @return BigDecimal
	 */
	public static BigDecimal multiply(BigDecimal... args) {
		if (args == null)
			return null;
		BigDecimal bd = null;

		for (BigDecimal bigD : args) {
			if (bigD == null)
				continue;
			if (bd == null)
				bd = bigD;
			else
				bd = bd.multiply(bigD);
		}
		return bd;
	}

	/**
	 * BigDecimal的乘法
	 * 
	 * @param scale 结果精度.
	 * @param args BigDecimal可变参数
	 * @return BigDecimal
	 */
	public static BigDecimal multiply(int scale, BigDecimal... args) {
		BigDecimal bdo = null;
		BigDecimal bd = multiply(args);
		if (bd != null)
			bdo = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
		else
			bdo = bd;
		return bdo;
	}

	/**
	 * BigDecimal的除法。
	 * 
	 * @param args BigDecimal可变参数
	 * @return BigDecimal
	 */
	public static BigDecimal divide(BigDecimal... args) {
		if (args == null)
			return null;
		BigDecimal bd = null;

		for (BigDecimal bigD : args) {
			if (bigD == null)
				continue;
			if (bd == null)
				bd = bigD;
			else
				bd = bd.divide(bigD, DIV_SCALE, BigDecimal.ROUND_HALF_UP);
		}
		return bd;
	}

	/**
	 * BigDecimal的除法。
	 * 
	 * @param scale 结果精度
	 * @param args BigDecimal可变参数
	 * @return BigDecimal
	 */
	public static BigDecimal divide(int scale, BigDecimal... args) {
		BigDecimal bdo = null;
		BigDecimal bd = divide(args);
		if (bd != null)
			bdo = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
		else
			bdo = bd;
		return bdo;
	}

	/**
	 * 检查srcStr串中是否包含subStr串
	 * 
	 * @param srcStr 源字符串
	 * @param subStr 子字符串
	 * @return true/false
	 */
	public static boolean contains(String srcStr, String subStr) {
		if (srcStr == null || subStr == null)
			return false;
		return srcStr.indexOf(subStr) > -1;
	}

	public static boolean contains(String[] arr, String str) {
		if (isEmpty(arr))
			return false;
		if (isEmpty(str))
			return false;
		for (String s : arr) {
			if (s.equals(str))
				return true;
		}
		return false;
	}

	public static boolean contains(Object[] arr, Object obj) {
		Arrays.sort(arr);
		return Arrays.binarySearch(arr, obj) > -1;
	}

	/**
	 * TODO 检查临时户是否到期 移到dp acct下
	 */
	public static int ChkTempAcct(String dpacct) {
		return 0;
	}

	/**
	 * 组成字符串方法
	 * 
	 * @param args 变参
	 * @return String
	 */
	public static String buildString(Object... args) {
		if (args == null)
			return null;
		StringBuffer buffer = new StringBuffer();

		for (Object object : args) {
			if (object != null) {
				buffer.append(object);
			}
		}
		return buffer.toString();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// 字符串相关操作的代码移动到StringUtil中，暂时标为废弃，以后删除

	/**
	 * 字符串首字母改为大写。
	 * 
	 * @param s
	 * @return
	 * @deprecated 移到StringUtil中，请调用者自行修改
	 */
	static public final String upCaseFirstChar(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 将字符串的第n位替换为其他字符
	 * 
	 * @param str 原字符串
	 * @param length 需要返回字符串的长度
	 * @param pos 替换字符位置 从0开始
	 * @param character 要替换的字符
	 * @return
	 * @deprecated 移到StringUtil中，请调用者自行修改
	 */
	public static String strReplaceChar(String str, int length, int pos, char character) {
		if (str == null)
			str = "";
		while (str.length() < length)
			str += " ";
		if (pos >= length)
			return str;
		else
			return str.substring(0, pos) + character + str.substring(pos + 1);
	}

	/**
	 * 连接两个对象为字符串
	 * 
	 * @param obj1
	 * @param obj2
	 * @return String
	 */
	public static String contactString(Object obj1, Object obj2) {
		StringBuffer buffer = new StringBuffer();
		if (!isEmpty(obj1))
			buffer.append(obj1);
		if (!isEmpty(obj2))
			buffer.append(obj2);
		return buffer.toString();
	}

	/**
	 * 替换字符串str中 replaced为replaceWith.
	 * 
	 * @param str 源字符串
	 * @param replaced
	 * @param replaceWith
	 * @return Long
	 */
	public static String replace(String str, String replaced, String replaceWith) {
		if (str == null) {
			return null;
		}
		if (replaced == null || replaceWith == null) {
			return str;
		}
		StringBuffer buf = new StringBuffer();
		int pos = str.indexOf(replaced);
		if (pos > -1) {
			String left = str.substring(0, pos);
			String right = str.substring(pos + replaced.length());
			buf.append(left);
			buf.append(replaceWith);
			buf.append(replace(right, replaced, replaceWith));
		} else {
			buf.append(str);
		}
		return buf.toString();
	}

	/**
	 * 将字符串的第n位替换为其他字符
	 * 
	 * @param str 原字符串
	 * @param length 需要返回字符串的长度
	 * @param pos 替换字符位置 从0开始
	 * @param character 要替换的字符
	 * @return
	 */
	public static String replace(String str, int length, int pos, char character) {
		if (str == null)
			str = "";
		while (str.length() < length)
			str += " ";
		if (pos >= length)
			return str;
		else
			return str.substring(0, pos) + character + str.substring(pos + 1);
	}

	/**
	 * 将字符串填充到len长,不足len长前面加0,超过截断
	 * 
	 * @param object 源对象:字符串,Long,int等
	 * @param len 字节长度
	 * @return
	 */
	public static String formatToFront(Object object, int len) {
		return formatToSide(object, len, '0', true);
	}

	/**
	 * 将字符串填充到len长,不足len的前补指定字符,超过截断
	 * 
	 * @param object 源对象:字符串,Long,int等
	 * @param len 字节长度
	 * @param strin 填充字符
	 * @param onLeftSide 是否填充在左边,否则填充在右边
	 * @return String
	 */
	public static String formatToSide(Object object, int len, char strin, boolean onLeftSide) {
		String str = (object == null) ? "" : object.toString().trim();
		while (str.getBytes().length > len) {
			str = str.substring(0, str.length() - 1);
		}
		int strLen = str.getBytes().length;
		String fillStr = repeat(strin + "", len - strLen);
		if (onLeftSide)
			str = fillStr + str;
		else
			str = str + fillStr;
		return str;
	}

	/**
	 * 重复串num次.
	 * 
	 * @param str 源串
	 * @param num 次数
	 * @return String
	 */
	public static String repeat(String str, int num) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < num; i++) {
			buf.append(str);
		}
		return buf.toString();
	}

	/**
	 * 字符串的正则匹配检查.
	 * 
	 * @param sRpx 合法正则串
	 * @param sData 待核查串
	 * @return int 如果匹配合法返回true，否则返回false。
	 */
	public static boolean regexMatch(String sRpx, String sData) {
		Pattern p = Pattern.compile(sRpx);
		Matcher m = p.matcher(sData);
		boolean b = m.matches();
		if (b) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * String的trim方法:去除首尾空格(空格、制表符、换行、换页符等空白字符)。
	 * 
	 * @param str
	 * @return String
	 */
	public static String trim(String str) {
		if (str == null)
			return BusiUtil.STRING_NULL;
		StringBuffer buffer = new StringBuffer();
		char[] sa = str.toCharArray();
		int ii = 0, jj = 0;
		for (ii = sa.length - 1; ii >= 0; ii--) {
			if (sa[ii] == ' ' || sa[ii] == '\t' || sa[ii] == '\f' || sa[ii] == '\n' || sa[ii] == '\r')
				sa[ii] = '\0';
			else
				break;
		}
		for (ii = 0, jj = 0; ii < sa.length; ii++) {
			if (sa[ii] == '\0') {
				break;
			} else if (sa[ii] != ' ' && sa[ii] != '\t' && sa[ii] != '\f' && sa[ii] != '\n' && sa[ii] != '\r' && sa[ii] != '\0') {
				jj++;
				buffer.append(sa[ii]);
			} else if (jj > 0) {
				buffer.append(sa[ii]);
			}
		}
		return buffer.toString();
	}

	/**
	 * String的trim方法:去除串中所有空格(空格、制表符、换页符等空白字符)
	 * 
	 * @param str
	 * @return String
	 */
	public static String trimAll(String str) {
		return str == null ? STRING_NULL : str.replaceAll("\\s*", "");
	}

	/**
	 * 对字符串trim后取长度.
	 * 
	 * @param str
	 * @return
	 */
	public static int trimLength(String str) {
		return str == null ? 0 : str.trim().length();
	}

	/**
	 * Map的trim方法:将Map中value为String的对象trim操作后put回map.
	 * 
	 * @param map
	 * @return String
	 */
	public static void trim(Map map) {
		if (BusiUtil.isEmpty(map))
			return;
		Object key;
		Object value;
		Iterator iter = map.keySet().iterator();
		while (iter.hasNext()) {
			key = iter.next();
			value = map.get(key);
			if (BusiUtil.isEmpty(value))
				continue;
			if (value instanceof String) {
				value = value == null ? BusiUtil.STRING_NULL : ((String) value).trim();
				map.put(key, value);
			}
		}
	}

	/**
	 * List的trim方法:将List中value为String的对象trim操作后set回List.
	 * 
	 * @param list
	 * @return String
	 */
	public static void trim(List list) {
		if (BusiUtil.isEmpty(list))
			return;
		Object value;
		for (int ii = 0; ii < list.size(); ii++) {
			value = list.get(ii);
			if (BusiUtil.isEmpty(value))
				continue;
			if (value instanceof String) {
				value = value == null ? BusiUtil.STRING_NULL : ((String) value).trim();
				list.set(ii, value);
			}
		}
	}

	/**
	 * 获取字符串的字节长度.
	 * 
	 * @param str
	 * @return
	 */
	public static int length(String str) {
		if (str == null)
			return 0;
		return str.getBytes().length;
	}

	/**
	 * 字符串截取（从第几位截取到结尾）。
	 * 
	 * @param str 源字符串
	 * @param beginIndex 起始位置
	 * @return String
	 */
	public static String substring(String str, int beginIndex) {
		return substring(str, beginIndex, -1);
	}

	/**
	 * 字符串截取（从第几位截取到第几位前）。
	 * 
	 * @param str 源字符串
	 * @param beginIndex 起始位置
	 * @param endIndex 结束位置（返回结果中不包含该位置值）
	 * @return String
	 */
	public static String substring(String str, int beginIndex, int endIndex) {
		return substring(str, beginIndex, endIndex, null);
	}

	/**
	 * 字符串截取（从第几位截取到第几位前）,为空则返回默认值。
	 * 
	 * @param str 源字符串
	 * @param beginIndex 起始位置
	 * @param endIndex 结束位置（返回结果中不包含该位置值）
	 * @param nullDefaultValue 结果为空串时（null/""），则返回的默认值; 为null时无效。
	 * @return String
	 */
	public static String substring(String str, int beginIndex, int endIndex, String nullDefaultValue) {
		if ((str == null || str.length() == 0) && nullDefaultValue != null)
			return nullDefaultValue;
		int s = str.length();
		if (beginIndex < 0)
			beginIndex = 0;
		if (endIndex < 0)
			endIndex = s;
		if (beginIndex > s)
			beginIndex = s;
		if (endIndex > s)
			endIndex = s;
		if (beginIndex > endIndex)
			beginIndex = endIndex;
		String i = str.substring(beginIndex, endIndex);
		if ((i == null || i.length() == 0) && nullDefaultValue != null)
			return nullDefaultValue;
		else
			return i;
	}

	/**
	 * 格式化加密密钥串: 16位,且只有数字(非数字的做发散映射到数字). 参见 MIFormatEncrpytionSecretKeyRSHash CM->MI->
	 * miformatencrpytionsecretkey.ec->MIFormatEncrpytionSecretKey
	 * 
	 * @param panBlock
	 * @return
	 */
	public static int formatEncrpytionSecretKey(String panBlock) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 添加的方法 CM->MI-> miformatencrpytionsecretkey.ec->MIFormatEncrpytionSecretKeyRSHash
	 * 
	 * @param str
	 * @return
	 */
	public static int formatEncrpytionSecretKeyRSHash(String str) {
		throw new UnsupportedOperationException();
	}


	private static String toUnderlineCase(String name) {
		if (isEmpty(name)) {
			return name;
		}
		while (name.startsWith("_")) {
			name = name.substring(1);
		}
		while (name.endsWith("_"))
			name = name.substring(0, name.length() - 1);

		int po = name.indexOf("_");
		if (po >= 0)
			return name.toUpperCase();
		String wd = "";
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (i > 0 && (Character.isDigit(c) || Character.isUpperCase(c))) {
				wd += "_" + Character.toUpperCase(c);
			} else {
				wd += Character.toUpperCase(c);
			}
		}
		return wd;
	}

	private static String toCamelCase(String name) {
		if (isEmpty(name))
			return name;
		while (name.startsWith("_"))
			name = name.substring(1);
		while (name.endsWith("_"))
			name = name.substring(0, name.length() - 1);
		int po = name.indexOf("_");

		if (po < 0)
			return name;

		String wd = name.substring(0, po);
		String left = name.substring(po + 1);
		wd = wd.substring(0, 1).toUpperCase() + wd.substring(1).toLowerCase();
		name = wd;
		po = left.indexOf("_");
		while (po >= 0) {
			wd = left.substring(0, po);
			wd = wd.substring(0, 1).toUpperCase() + wd.substring(1).toLowerCase();
			name += wd;

			left = left.substring(po + 1);
			po = left.indexOf("_");
		}
		if (!isEmpty(left)) {
			wd = left;
			wd = wd.substring(0, 1).toUpperCase() + wd.substring(1).toLowerCase();
			name += wd;
		}
		return name;
	}

	/**
	 * 数据字典名转换：转换成小写驼峰命名.
	 * 
	 * @param name
	 * @return
	 */
	public static String toLowerCamelCase(String name) {
		if (isEmpty(name))
			return name;
		while (name.startsWith("_"))
			name = name.substring(1);
		while (name.endsWith("_"))
			name = name.substring(0, name.length() - 1);
		int po = name.indexOf("_");
		if (po >= 0)
			name = toCamelCase(name);
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}

	/**
	 * 数据字典名转换：转换成大写驼峰命名.
	 * 
	 * @param name
	 * @return
	 */
	public static String toUpperCamelCase(String name) {
		if (isEmpty(name))
			return name;
		while (name.startsWith("_"))
			name = name.substring(1);
		while (name.endsWith("_"))
			name = name.substring(0, name.length() - 1);
		int po = name.indexOf("_");
		if (po >= 0)
			name = toCamelCase(name);
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	/**
	 * 数据字典名转换：转换成小写下划线连字命名.<br>
	 * 注意：转换规则，碰到大写字母、数字就增加连字符"_". <br>
	 * 
	 * @param name
	 * @return
	 */
	public static String toLowerUnderlineCase(String name) {
		if (isEmpty(name))
			return name;
		return toUnderlineCase(name).toLowerCase();
	}

	/**
	 * 数据字典名转换：转换成大写下划线连字命名. <br>
	 * 注意：转换规则，碰到大写字母、数字就增加连字符"_". <br>
	 * 
	 * @param name
	 * @return
	 */
	public static String toUpperUnderlineCase(String name) {
		if (isEmpty(name))
			return name;
		return toUnderlineCase(name).toUpperCase();
	}

	public static String getIPV4() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			InetAddress[] addresses = InetAddress.getAllByName(address.getHostName());
			for (InetAddress addr : addresses) {
				if (addr instanceof Inet4Address) {
					return addr.getHostAddress();
				}
			}
		} catch (UnknownHostException e) {
			logger.warn("Get local address with ipv4 failed, {}", e);
		}
		return null;
	}
	
	
	   /**
     * 将字条串用指定的字符分开
     * @param strText 输入客串
     * @param character 分隔符号(如:",")
     * @return List
     */
    public static List<String> split(String strText, String character) {
        
        if(strText == null) return null;
        List<String> arr = new ArrayList<String>();
        int index = strText.indexOf(character);
        while(index != -1) {
            String temp = strText.substring(0,index);
            strText = strText.substring(index + 1, strText.length());
            arr.add(temp);
            index = strText.indexOf(character);
        }
        arr.add(strText);
        
        return arr;
    }
    
    /**
     * 表名转换成类名
    * @param tableName  
    * @param ignore 0-不忽略 1-忽略第一个"_"前的
    * @return String
     */
    public static String tblNameToClass(String tableName, String ignore) {
        if ("1".equals(ignore)) {
            tableName = tableName.substring(tableName.indexOf("_"));
        }

        String tmp = tableName.toLowerCase();
        List<String> tmpList = BusiUtil.split(tmp, "_");

        StringBuilder tmpResult = new StringBuilder();

        int size = tmpList.size();
        for (int i = 0; i < size; i++) {
            String tmpStr = tmpList.get(i);
            if (0 == i) {
                tmpResult.append(tmpStr);
            } else {
                tmpResult.append(tmpStr.substring(0, 1).toUpperCase() + tmpStr.substring(1));
            }
        }

        return tmpResult.toString();
    }
    
    /**
     * 去掉左空格
    * leftTrim     
    * @param str  
    * @return String
     */
    public static String leftTrim(String str) {
        if (BusiUtil.isEmpty(str)) {
            return str;
        } else {
            return str.replaceAll("^[　 ]+", "");
        }
    }
    
    /**
     * 去掉右空格
    * leftTrim     
    * @param str  
    * @return String
     */
    public static String rightTrim(String str) {
        if (BusiUtil.isEmpty(str)) {
            return str;
        } else {
            return str.replaceAll("[　 ]+$", "");
        }
    }
    /**
     * 把对象转成String
     * 
     * @param val
     * @return String
     */
    public static String obj2Str(Object val) {
        String value = null;
        try {
            value = val.toString();
        } catch (Exception e) {
            //Logger.warn("把“" + val + "”从对象转成String型失败！");
        }
        return value;
    }

    /**
     * 把字符串转成Integer
     * 
     * @param val
     * @return Integer
     */
    public static Integer str2Integer(String val) {
        Integer value = null;
        try {
            value = Integer.valueOf(val);
        } catch (Exception e) {
            //Logger.warn("把“" + val + "”从字符串转成Integer型失败！");
        }
        return value;
    }

    /**
     * 把字符串转成Short
     * 
     * @param val
     * @return Short
     */
    public static Short str2Short(String val) {
        Short value = null;
        try {
            value = Short.valueOf(val);
        } catch (Exception e) {
            //Logger.warn("把“" + val + "”从字符串转成Long型失败！");
        }
        return value;
    }
    
    /**
     * 把字符串转成Float
     * 
     * @param val
     * @return Float
     */
    public static Float str2Float(String val) {
        Float value = null;
        try {
            value = Float.valueOf(val);
        } catch (Exception e) {
            //Logger.warn("把“" + val + "”从字符串转成Long型失败！");
        }
        return value;
    }
    
    /**
     * 把字符串转成Byte
     * 
     * @param val
     * @return Byte
     */
    public static Byte str2Byte(String val) {
        Byte value = null;
        try {
            value = Byte.valueOf(val);
        } catch (Exception e) {
            //Logger.warn("把“" + val + "”从字符串转成Long型失败！");
        }
        return value;
    }

   /**
    *
    * 功能描述:判断对象是否为空，为空则返回dest，否则返回该对象source
    *
    * @param:
    * @return:
    * @auther:huangnan
    * @date: 2018/8/15 17:48
    */
    public static <T> T nvl(T source, T dest) {
        return isNullObj(source) ? dest : source;
    }

   /**
    *
    * 功能描述:
    * @param:
    * @return:
    * @auther: huangnan
    * @date: 2018/8/15 17:50
    */
    private static boolean isNullObj(Object obj) {
        if (null == obj) {
            return true;
        }
        if (String.class.isInstance(obj)) {
            return StringUtils.isEmpty(obj);
        } else if (List.class.isInstance(obj)) {
            return ((List) obj).size() == 0;
        } else {
            return obj == null;
        }
    }

	/**
	 * map中key大小写转化
	 * @param orgMap
	 * @param flag
	 * @return
	 */
    public static Map<String, Object> tranMapKey(Map<String, ?> orgMap, String flag) {
        Map<String, Object> resultMap = new HashMap<>();

        if (orgMap == null || orgMap.isEmpty()) {
            return resultMap;
        }

        Set<String> keySet = orgMap.keySet();
        for (String key : keySet) {
            String newKey = key.toUpperCase();
            if ("1".equals(flag)) {
                newKey = key.toLowerCase();
            }
            resultMap.put(newKey, orgMap.get(key));
        }

        return resultMap;
    }
}
