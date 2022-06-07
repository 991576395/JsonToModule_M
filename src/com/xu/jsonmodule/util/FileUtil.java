package com.xu.jsonmodule.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileUtil {
	/**
	 * @throws FileNotFoundException 
	 * @Title: readFile 
	 * @Description: 读取文件
	 * @param @param fileName
	 * @param @return 
	 * @return String
	 * @throws
	 */
	public static String readFile(String fileName) throws Exception {
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
			String str = "";
			while((str = br.readLine()) != null){
				sb.append(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			try {
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public static String readFileHaveFormat(String fileName) throws Exception {
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
			String str = "";
			while((str = br.readLine()) != null){
				sb.append(str+"\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			try {
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String readFileByResources(String fileName) throws Exception {
		InputStream is= Tools.class.getResourceAsStream(fileName);
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String str = "";
			while((str = br.readLine()) != null){
				sb.append(str+"\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			try {
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * @Title: writeFile
	 * @Description: 写入文件
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public static void writeFile(String fileName,String content) throws Exception {
		File file = new File(fileName);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		if(!file.exists()){
			file.createNewFile();
		}
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
			bufferedWriter.write(content);
		} catch (Exception e) {
			throw e;
		} finally{
			if(bufferedWriter != null){
				bufferedWriter.close();
			}
		}
	}

	/**
	 * @Title: writeFile
	 * @Description: 写入文件
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public static void writeFileApp(String fileName,String content) throws Exception {
		File file = new File(fileName);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		if(!file.exists()){
			file.createNewFile();
		}
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"));
			bufferedWriter.write(content);
		} catch (Exception e) {
			throw e;
		} finally{
			if(bufferedWriter != null){
				bufferedWriter.close();
			}
		}
	}

	/**
	 * @Title: writeFile
	 * @Description: 写入文件
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public static void writeFile(String fileName,Exception content){
		File file = new File(fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"));
			PrintWriter printWriter = new PrintWriter(bufferedWriter);
//			bufferedWriter.write(new BufferedReader(new InputStreamReader(content)).readLine());
			content.printStackTrace(printWriter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(bufferedWriter != null){
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
