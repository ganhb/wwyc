package com.dragon.utils;

import com.google.gson.Gson;

public class GsonUtil {
	/**
	 * 把对象打包成json数据串
	 * @param src
	 * @return
	 */
	public static String getJsonString (Object src) {
		Gson gs = new Gson();
		return gs.toJson(src);
	}
	/**
	 *  将Json 数据打包成对象
	 * @param json
	 * @param cls
	 * @return
	 */
	public static Object getJsonObject(String json, Class<?> cls) {
		Gson gs = new Gson();
		return gs.fromJson(json, cls);
	}
}
