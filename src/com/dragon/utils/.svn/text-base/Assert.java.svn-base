package com.dragon.utils;

public class Assert {

	/**
	 * 断言 字符串不能为空
	 * @param str		字符串
	 * @param message   异常消息
	 */
	public static void notNull(String str, String message) {
		if(null == str){
			throw new NullPointerException(message);
		}
	}
	
	/**
	 * 断言 文本不能为空
	 * @param str		字符串
	 * @param message   异常消息
	 */
	public static void hasText(String str, String message) {
		if(null == str || str.equals("")){
			throw new NullPointerException(message);
		}
	}


	/**
	 * 断言 文本不能为空
	 * @param str		字符串
	 */
	public static void hasText(String str) {
		if(null == str || str.equals("")){
			throw new NullPointerException(str+"：不能为空");
		}
	}
	/**
	 * 断言 文本不能为空
	 * @param str		字符串
	 */
	public static void notNull(String str) {
		if(null == str){
			throw new NullPointerException(str+"：不能为空");
		}
	}
}
