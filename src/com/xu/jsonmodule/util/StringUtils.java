package com.xu.jsonmodule.util;

import java.io.File;

/**
 * @ClassName: StringUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author xuzhenyao
 * @date 2015-7-22 上午9:38:06 
 *
 */
public class StringUtils {
	public static boolean isEmpty(String str) {
		boolean ret = false;
		if (str == null || str.trim().length() == 0) {
			ret = true;
		}
		return ret;
	}
	
	public static String formatPath(String str){
		return str.replaceAll("//", File.separator);
	}
}
