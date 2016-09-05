package com.dragon.utils;

import java.io.IOException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
/**
 * 关于Spring的资源类Resource的工具类
 * @author yezq
 *
 */
public class ResourceUtils {
	/**
	 * 资源加载类
	 */
	private static ResourceLoader rloader = new DefaultResourceLoader();
	
	/**
	 * 支持正则表达式的资源搜索类
	 */
	private static  ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	
	/**
	 * 根据路径获得资源，路径支持类路径、文件路径等
	 * @param path
	 * @return
	 */
	public static Resource getResource(String path){
		return rloader.getResource(path);
	}
	
	/**
	 * 根据路径获得资源数组，路径支持正则表达式（如conf-*.ftl：以conf-开头，.ftl结尾 的所有文件）
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static Resource[] getResources(String path) throws IOException{
		return resolver.getResources(path);
	}

}
