package com.xu.jsonmodule.entity;

/**
 * @ClassName: DakuohaoEntity 
 * @Description: 大括号对象
 * @author xuzhenyao
 * @date 2015-7-15 下午3:23:03 
 *
 */
public class DakuohaoEntity {
	public interface DakuohaoType{
		/**
		 * 左括号
		 */
		int LEFT = 1;
		/**
		 * 右括号
		 */
		int RIGHT = 2;
	}
	private int type;
	private int index;
	/**
	 * 是否配对
	 */
	private boolean ifPairing = false;
	/**
	 * 配对符号
	 */
	private DakuohaoEntity pairing;
	
	public DakuohaoEntity(int type, int index) {
		super();
		this.type = type;
		this.index = index;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isIfPairing() {
		return ifPairing;
	}
	public void setIfPairing(boolean ifPairing) {
		this.ifPairing = ifPairing;
	}
	public DakuohaoEntity getPairing() {
		return pairing;
	}
	public void setPairing(DakuohaoEntity pairing) {
		this.pairing = pairing;
	}
}
