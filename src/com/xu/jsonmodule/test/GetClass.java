package com.xu.jsonmodule.test;

import com.xu.jsonmodule.util.FileUtil;

public class GetClass {
	public static void formatClass(String file){
		StringBuffer sb = new StringBuffer();
		//TODO 获取解析模板
		String str="";
		try {
			str = FileUtil.readFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(str);
		boolean can = false;
		int i =0;
		while(i < str.length()){
			if(i+2 < str.length() && "/*".equals(str.substring(i, i+2))){
				can = false;
				i+=2;
				continue;
			}else if(i+2 < str.length() && "*/".equals(str.substring(i, i+2))){
				can = true;
				i+=2;
				continue;
			}
			if(can){
				sb.append(str.charAt(i));
			}
			i++;
		}
		try {
			System.out.println(sb.toString());
			FileUtil.writeFile(file, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
