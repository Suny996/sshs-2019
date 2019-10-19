package com.sshs.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * @author Suny
 *
 */
public class Serializabler implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 把对象转换为byte[]类型，以便保存到数据库
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] object2Bytes(Object obj) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream outputStream = new ObjectOutputStream(out);
			outputStream.writeObject(obj);
			byte[] bytes = out.toByteArray();
			outputStream.close();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将blob转换为对象
	 * 
	 * @param decBlob
	 * @return
	 */
	public static Object blob2Object(java.sql.Blob decBlob) {
		try {
			ObjectInputStream in = new ObjectInputStream(decBlob.getBinaryStream());
			Object obj = in.readObject();
			in.close();
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}