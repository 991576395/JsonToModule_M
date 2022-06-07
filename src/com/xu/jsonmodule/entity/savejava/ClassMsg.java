package com.xu.jsonmodule.entity.savejava;

import java.util.List;

import com.xu.jsonmodule.main.Main;

/**
 * @ClassName: ClassMsg 
 * @Description: 类信息
 * @author xuzhenyao
 * @date 2015-7-20 上午9:38:09 
 *
 */
public class ClassMsg {
	private String classname;
	private List<ProMsg> pros;
	private String packagename;
	
	public ClassMsg(String classname, List<ProMsg> pros) {
		super();
		this.classname = classname;
		this.pros = pros;
		if(Main.packgePath != null && Main.packgePath.length() > 0){
			this.packagename = Main.packgePath;
		}
	}
	
	
	public String getPackagename() {
		return packagename;
	}


	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}


	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public List<ProMsg> getPros() {
		return pros;
	}
	public void setPros(List<ProMsg> pros) {
		this.pros = pros;
	}


	@Override
	public String toString() {
		return "ClassMsg [classname=" + classname + ", pros=" + pros
				+ ", packagename=" + packagename + "]";
	}
}
