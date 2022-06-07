package com.xu.jsonmodule.main;

import com.xu.jsonmodule.core.CreatJava;
import com.xu.jsonmodule.core.ParseJson;
import com.xu.jsonmodule.entity.DakuohaoEntity;
import com.xu.jsonmodule.entity.cache.Cache;
import com.xu.jsonmodule.entity.savejava.ClassMsg;
import com.xu.jsonmodule.util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Main 
 * @Description: 启动程序
 * @author xuzhenyao
 * @date 2015-7-21 上午9:40:46 
 *
 */
public class Main {
	/**
	 * 生产类的路径
	 */
	public static String javaPath = "G:\\plugins\\JsonToModule_M\\demo\\entity";
	/**
	 * 生产的包名
	 */
	public static String packgePath = "com.ceshi.xu.test";
	/**
	 * 要解析的json文件路径
	 */
	public static String fileName = "json3.txt";
	
	public static List<DakuohaoEntity> kuohaoEntities;
	/**
	 * 需要生成类的信息 
	 */
	public static List<ClassMsg> classMsg = new ArrayList<ClassMsg>();

	/**
	 * 缓存实体
	 */
	public static Cache cache = new Cache();
	public static String cachePath =System.getProperty("user.home")+File.separator+"create"+File.separator+"cache"+File.separator+"cache.bak";



	public static void main(String[] args) throws Exception {
		//实例化大括号
//		initPath();
//		new MainFragme();

//		Properties properties = System.getProperties();
//		Set<String> strs = properties.stringPropertyNames();
//		for (String name :strs) {
//			System.out.println(name+" : "+properties.get(name));
//		}
		//   == idea.plugins.path

//		System.out.println(System.getProperty("idea.platform.prefix"));
	}

	private static void initPath() {
		File file = new File("com/xu/jsonmodule/util/templates/cache");
		if(!file.exists()){
			file.mkdirs();
		}
	}
	@Test
	public void testOne() throws Exception {
		ParseJson parseJson = new ParseJson();

		new CreatJava(parseJson.paseJson(FileUtil.readFile("demo/1.json")));
	}

	@Test
	public void testObject() throws Exception {
		ParseJson parseJson = new ParseJson();

		new CreatJava(parseJson.paseJson(FileUtil.readFile("demo/2.json")));
	}


	@Test
	public void testArrayObject() throws Exception {
		ParseJson parseJson = new ParseJson();

		new CreatJava(parseJson.paseJson(FileUtil.readFile("demo/3.json")));
	}

	@Test
	public void testObjArrayObject() throws Exception {
		ParseJson parseJson = new ParseJson();

		new CreatJava(parseJson.paseJson(FileUtil.readFile("demo/4.json")));
	}


	@Test
	public void testArrayObjectRoot() throws Exception {
		ParseJson parseJson = new ParseJson();

		new CreatJava(parseJson.paseJson(FileUtil.readFile("demo/5.json")));
	}

	@Test
	public void testTwe() throws Exception {
		ParseJson parseJson = new ParseJson();

		new CreatJava(parseJson.paseJson(FileUtil.readFile("demo/6.json")));
	}


	@Test
	public void testArrayArray() throws Exception {
		ParseJson parseJson = new ParseJson();

		new CreatJava(parseJson.paseJson(FileUtil.readFile("demo/7.json")));
	}

	@Test
	public void test8() throws Exception {
		ParseJson parseJson = new ParseJson();

		new CreatJava(parseJson.paseJson(FileUtil.readFile("demo/8.json")));
	}

	@Test
	public void test9() throws Exception {
	}
}
