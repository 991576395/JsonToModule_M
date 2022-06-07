package com.xu.jsonmodule.entity.cache;

import java.util.List;

/**
 * @ClassName: Cache 
 * @Description: 缓存实体
 * @author xuzhenyao
 * @date 2015-11-2 上午10:07:24 
 *
 */
public class Cache {
	/**
	 * 缓存目录
	 */
	private String path;
	/**
	 * andorid 控件读取属性
	 */
	private List<String> andoridWidgetPros;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getAndoridWidgetPros() {
		return andoridWidgetPros;
	}

	public void setAndoridWidgetPros(List<String> andoridWidgetPros) {
		this.andoridWidgetPros = andoridWidgetPros;
	}
	
}
