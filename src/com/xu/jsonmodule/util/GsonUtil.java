package com.xu.jsonmodule.util;

import java.lang.reflect.Type;
import java.net.URLDecoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * gson帮助类
 * @author Administrator
 *
 */
public class GsonUtil {
	private static Gson gson;
	private static Gson expGson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
	/**
	 * 返回gson对象
	 * @return
	 */
	public static Gson getInstent(){
		if(gson == null){
			gson = new Gson();
		}
		return gson;
	}
	
	
	  /**
     *  json转化成 java bean
     * 
     * @param json
     * @return  java bean of beanClass
     */
    @SuppressWarnings({ "unchecked" })
    public static <T> T toObject(String json, Type type) {
        if (json == null) {
            return null;
        }
        if (type == null) {
            return null;
        }
        Gson gsonMap = new Gson();
        T object;
        try {
            object = (T) gsonMap.fromJson(json, type);
        } catch (RuntimeException e) {
            throw e;
        }
        return object;
    }

    /**
     *  json转化成 java bean
     * 
     * @param json
     * @param beanClass 
     * @return  java bean of beanClass
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T toObject(String json, Class beanClass) {
        if (json == null) {
            return null;
        }
        if (beanClass == null) {
            return null;
        }
        
        @SuppressWarnings("unused")
		T object;
        try {
            json = URLDecoder.decode(json,"UTF-8");
           return (T) getInstent().fromJson(json, beanClass);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }

    /**
     * java bean 转json
     * 
     * @param object java bean
     * @param beanClass  需要转化成json的bean对象的class
     * @return  json string
     */
    @SuppressWarnings("rawtypes")
    public static String toJson(Object object, Class beanClass) {
        if (object == null) {
            return null;
        }
        if (beanClass == null) {
            return null;
        }
        return gson.toJson(object, beanClass);
    }
    
    /**
     * java bean 转json 排除属性
     * 
     * @param object java bean
     * @param beanClass  需要转化成json的bean对象的class
     * @return  json string
     */
    @SuppressWarnings("rawtypes")
    public static String toJsonExp(Object object, Class beanClass) {
        if (object == null) {
            return null;
        }
        if (beanClass == null) {
            return null;
        }
        return expGson.toJson(object, beanClass);
    }
}
