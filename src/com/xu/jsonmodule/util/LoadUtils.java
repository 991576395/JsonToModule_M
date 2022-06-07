package com.xu.jsonmodule.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class LoadUtils {
	/**
	 * 模板保存地址
	 */
	public static String template_url = "";
	public static String cachPth="";
	public static String cachUrl="";

	/**
	 * 主目录
     */
	public static final String HOME_PATH =System.getProperty("user.home")+ File.separator+"jsonToModule"+File.separator;
	/**
	 * 缓存目录
     */
	public static final String CACH_PATH = HOME_PATH +"cach"+File.separator;
	public static final String LOG_PATH = HOME_PATH +"log"+File.separator;

	static {
		init();
	}

	public static void init() {
		Field[] fields = LoadUtils.class.getDeclaredFields();
		for (Field field :fields) {
			if(field.getName().endsWith("_PATH")){
				try {
					String value = (String) field.get(LoadUtils.class);
					System.out.println(value);
					File file = new File(value);
					if(!file.exists()){
						file.mkdirs();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args){
		init();
	}



	public static String getResourcePath(String re){
		return LoadUtils.class.getClassLoader().getResource(re).getPath();
	}
	
	/**
	 * @Title: getResourcePath 
	 * @Description: 获得资源路径
	 * @param @param resource
	 * @param @return 
	 * @return String
	 * @throws
	 */
	public static String getResourceCachUrl(){
		if(StringUtils.isEmpty(cachUrl)){
			Properties pps = new Properties();
			try {
				pps.load(new FileInputStream(LoadUtils.class.getClassLoader().getResource("commons.properties").getPath()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			cachUrl = pps.getProperty("cach_url");
		}
		return cachUrl;
	}
	
	/**
	 * @Title: getResourcePath 
	 * @Description: 获得资源路径
	 * @param @param resource
	 * @param @return 
	 * @return String
	 * @throws
	 */
	public static String getResourceCachPath(){
		if(StringUtils.isEmpty(cachPth)){
			Properties pps = new Properties();
			try {
				pps.load(new FileInputStream(LoadUtils.class.getClassLoader().getResource("commons.properties").getPath()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			cachPth = pps.getProperty("cach_path");
		}
		return cachPth;
	}
}
