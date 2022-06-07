package com.xu.jsonmodule.entity;

import com.xu.jsonmodule.entity.savejava.ProMsg;

import java.util.*;

/**
 * @ClassName: BaseEntity 
 * @Description: 基本对象 {} 内
 * @author xuzhenyao
 * @date 2015-7-15 下午2:39:53 
 *
 */
public class BaseEntity {
	/**
	 * @ClassName: EntityType 
	 * @Description: 类型
	 * @author xuzhenyao
	 * @date 2015-7-15 下午1:45:07 
	 *
	 */
	public interface EntityType{
		/**
		 * 对象
		 */
		public int OBJECT = 1;
		/**
		 * 集合
		 */
		public int LIST = 2;

		/**
		 * 字段
         */
		public int FIELD = 3;
	}




	/**
	 * 直属父层级
     */
	private BaseEntity faterEntity;

	/**
	 * 级别
	 */
	private int level;
	/**
	 * 对象的键值
	 */
	private String key;
	/**
	 * 是否为数组
	 */
	private boolean isArray = false;
	
	/**
	 * 值 大括号的内容
	 */
	private String value;
	/**
	 * 子对象内容
	 */
	private List<BaseEntity> entitys = new ArrayList<>();

	/**
	 * 字段类型  默认字符串
	 */
	private int fieldType = ProMsg.ClassType.STRING;
	
	/**
	 * EntityType 类型
	 */
	private int type;

	/**
	 * 注释
	 */
	private String commentsStr;


	public String getCommentsStr() {
		return commentsStr;
	}

	public void setCommentsStr(String commentsStr) {
		this.commentsStr = commentsStr;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<BaseEntity> getEntitys() {
		return entitys;
	}
	public void setEntitys(List<BaseEntity> entitys) {
		this.entitys = entitys;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public boolean isArray() {
		return isArray;
	}
	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}

	public BaseEntity getFaterEntity() {
		return faterEntity;
	}

	public void setFaterEntity(BaseEntity faterEntity) {
		this.faterEntity = faterEntity;
	}

	public int getFieldType() {
		return fieldType;
	}

	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}

	@Override
	public String toString() {
		return "BaseEntity{" +
				", level=" + level +
				", key='" + key + '\'' +
				", isArray=" + isArray +
				", value='" + value + '\'' +
				", entitys=" + entitys +
				", fieldType=" + fieldType +
				", type=" + type +
				", commentsStr='" + commentsStr + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseEntity that = (BaseEntity) o;
		return Objects.equals(key, that.key);
	}

	@Override
	public int hashCode() {
		return Objects.hash(key);
	}

}
