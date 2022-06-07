package com.xu.jsonmodule.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.xu.jsonmodule.main.Main;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkertUtil {
	public static void analysisTemplate(String templateName,
			 Map<?, ?> root,String pathName) {
		try {
			/**
			 * 创建Configuration对象
			 */
			Configuration config = new Configuration();
			/**
			 * 指定模板路径
			 */
			File file = new File(Tools.class.getResource("/").getPath()+File.separator+ "templates"+File.separator+"create");
			/**
			 * 设置要解析的模板所在的目录，并加载模板文件
			 */
			config.setDirectoryForTemplateLoading(file);
			/**
			 * 设置包装器，并将对象包装为数据模型
			 */
			config.setObjectWrapper(new DefaultObjectWrapper());

			/**
			 * 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			 */
			Template template = config.getTemplate(templateName,
					"UTF-8");
			/**
			 * 合并数据模型与模板
			 */
			Writer out = new OutputStreamWriter(new FileOutputStream(pathName,false));
//			Writer out = new OutputStreamWriter(System.out);
			template.process(root, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

	}

	public static void analysisTemplate(String templateName,Object root,String pathName) throws Exception{
//		String value = FileUtil.readFileByResources(templateName);
		String res = setStringFormatNoCachFile(Tools.jsontojava,root);
		FileUtil.writeFileApp(LoadUtils.LOG_PATH+"Log.txt",Main.javaPath+"/"+pathName+".java");
		FileUtil.writeFile(Main.javaPath+"/"+pathName+".java",res);
	}

	public static void analysisTemplate(String templateName,
			String templateEncoding, Object root,String pathName) throws Exception {
			/**
			 * 创建Configuration对象
			 */
			Configuration config = new Configuration();
			/**
			 * 指定模板路径
			 */
			File file = new File("templates");
			/**
			 * 设置要解析的模板所在的目录，并加载模板文件
			 */
			config.setDirectoryForTemplateLoading(file);
			/**
			 * 设置包装器，并将对象包装为数据模型
			 */
			config.setObjectWrapper(new DefaultObjectWrapper());

			/**
			 * 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			 */
			Template template = config.getTemplate(templateName,
					templateEncoding);
			/**
			 * 合并数据模型与模板
			 */
			File file1 = new File(Main.javaPath);
			if(!file1.exists()){
				file1.mkdirs();
			}
			Writer out = new OutputStreamWriter(new FileOutputStream(Main.javaPath+"/"+pathName+".java",false));
//			Writer out = new OutputStreamWriter(System.out);
			template.process(root, out);
			out.flush();
			out.close();
		
	}
	
	public static void analysisTemplateAll(String templateName,
			 Object root,String pathName) throws Exception {
			/**
			 * 创建Configuration对象
			 */
			Configuration config = new Configuration();
			/**
			 * 指定模板路径
			 */
			File file = new File(LoadUtils.getResourcePath("templates/"));
			/**
			 * 设置要解析的模板所在的目录，并加载模板文件
			 */
			config.setDirectoryForTemplateLoading(file);
			/**
			 * 设置包装器，并将对象包装为数据模型
			 */
			config.setObjectWrapper(new DefaultObjectWrapper());

			/**
			 * 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			 */
			Template template = config.getTemplate(templateName,
					"utf-8");
			/**
			 * 合并数据模型与模板
			 */
			Writer out = new OutputStreamWriter(new FileOutputStream(pathName,false));
//			Writer out = new OutputStreamWriter(System.out);
			template.process(root, out);
			out.flush();
			out.close();
		
	}
	
	public static String setStringFormat(String templateString,Object root) throws Exception{
		String path = LoadUtils.getResourceCachPath()+File.separator+"cachstring";
//		String path = "D:\\ceshi1\\a.txt";
		Configuration cfg = new Configuration();
//		cfg.setDirectoryForTemplateLoading(new File("com.ceshi.xu.util.templates"));
		StringTemplateLoader stringLoader = new StringTemplateLoader();  
		String templateContent=templateString;  
		stringLoader.putTemplate("myTemplate",templateContent);  
		cfg.setTemplateLoader(stringLoader);  
		Template template = cfg.getTemplate("myTemplate","utf-8");
		Writer writer = new OutputStreamWriter(new FileOutputStream(path,false), "utf-8");
		template.process(root, writer);
		writer.flush();
		writer.close();
		return FileUtil.readFileHaveFormat(path);
	}
	
	public static String setStringFormatNoCachFile(String templateString,Object root) throws Exception{
		Configuration cfg = new Configuration();
		StringTemplateLoader stringLoader = new StringTemplateLoader();  
		String templateContent=templateString;  
		stringLoader.putTemplate("myTemplate",templateContent);  
		cfg.setTemplateLoader(stringLoader);  
		Template template = cfg.getTemplate("myTemplate","utf-8");
		final StringBuffer buffer =new StringBuffer();

		CharArrayWriter writer = new CharArrayWriter();
		template.process(root, writer);
		writer.flush();
		writer.close();

		return new String(writer.toCharArray());
	}

	/**
	 * 表达式 获取boolean 值
	 * @param templateString
	 * @param root
     * @return
     */
	public static boolean  evalStringBoolean(String templateString,Object root){
		try {
			String value = setStringFormatNoCachFile(templateString,root);
			return Boolean.parseBoolean(value.trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	public static void main(String[] args) {
		Map<String, Object> root = new HashMap<>();

//		List<Element> elements = new ArrayList<>();
//		Element element = new Element("TextView","@+id/R.id.text_view",true);
//		element.ifClick = true;
//		element.fieldName = "textView";
//		elements.add(element);
//
//		Element element1 = new Element("TextView","@+id/R.id.text_view",true);
//		element1.ifClick = true;
//		element1.fieldName = "textView";
//		elements.add(element1);
//
//		root.put("elements",elements);
		root.put("engtuoName","ADFASASAdsd peip");


		try {
//			String value = setStringFormatNoCachFile("<#if elements??>\n" +
//					"<#list elements as ele>a:${ele.fieldName}\n" +
//					"<#if ele.used??>\n" +
//					"${ele.fieldName}=(${ele.nameFull})view.findViewById(R.id.${ele.id});\n" +
//					"</#if>\n" +
//					"</#list>\n" +
//					"</#if>",root);

//			System.out.println(GsonUtil.getInstent().toJson(elements));

//			String value = setStringFormatNoCachFile("<#if elements??>\n" +
//					"<#list elements as ele>\n" +
//					"<#if (ele.used == true) &&  (ele.isClick == true) >\n" +
//					"${ele.fieldName}.setOnClickListener(this);\n" +
//					"</#if>\n" +
//					"</#list>\n" +
//					"\n" +
//					"</#if>",root);
//			String value = setStringFormatNoCachFile("<#list elements as k>${k}</#list>",root);
//			String value = setStringFormatNoCachFile("${elements[0].id}",root);
//			System.out.println(evalStringBoolean("<#list elements as ele><#if (ele.used == true) &&  (ele.ifClick == true) >true<#break></#if></#list>",root));
//			System.out.println("a:"+value);
			String value = setStringFormatNoCachFile("<#assign ifImageUpload = false>\n" +
					"<#assign ifFileUpload = false>\n" +
					"<#assign ifEditor = false>\n" +
					"<#assign ifDate = false>\n" +
					"<#assign ifDate = true> <#if ifDate >aaa</#if>",root);

			System.out.println(value);

//			String value = setStringFormatNoCachFile("<#if elements??>\n" +
//					"<#list elements as ele>\n" +
//					"<#if (ele.used == true) &&  (ele.isClick == true) >\n" +
//					"${ele.fieldName}.setOnClickListener(this);\n" +
//					"</#if>\n" +
//					"</#list>\n" +
//					"\n" +
//					"</#if>",root);
//			String value = setStringFormatNoCachFile("<#list elements as k>${k}</#list>",root);
//			String value = setStringFormatNoCachFile("${elements[0].id}",root);
//			System.out.println(evalStringBoolean("<#list elements as ele><#if (ele.used == true) &&  (ele.ifClick == true) >true<#break></#if></#list>",root));
//			System.out.println("a:"+value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}