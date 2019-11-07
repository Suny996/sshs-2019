package com.spreada.utils.chinese;

import com.spreada.utils.chinese.ZHConverter;
public class Test{
	public static void main(String[] args){
		ZHConverter converter = ZHConverter.getInstance(ZHConverter.TRADITIONAL);
		String simplifiedStr = converter.convert("乔");
		System.out.println(simplifiedStr);
	}
}
