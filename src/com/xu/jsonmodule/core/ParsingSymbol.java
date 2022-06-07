package com.xu.jsonmodule.core;

import java.util.Stack;

import com.xu.jsonmodule.entity.DakuohaoEntity;
import com.xu.jsonmodule.entity.ZhongkuohaoEntity;
import com.xu.jsonmodule.main.Main;
import com.xu.jsonmodule.util.CharContents;

/**
 * @ClassName: ParsingSymbol
 * @Description:解析符号
 * @author xuzhenyao
 * @date 2015-7-15 上午9:26:41
 * 
 */
public class ParsingSymbol {
	// 未配对的大括号集合
	private Stack<DakuohaoEntity> noDakuohao;
	// 未匹配的中括号集合
	private Stack<ZhongkuohaoEntity> noZhongkuohao;

	/**
	 * @Title: getDakuohao
	 * @Description: 解析大括号
	 * @param
	 * @return void
	 * @throws
	 */
	public void getDakuohao(String json) {
		noDakuohao = new Stack<DakuohaoEntity>();
		noZhongkuohao = new Stack<ZhongkuohaoEntity>();
		//引号拦截
		boolean flag = true;
		for (int i = 0; i < json.length(); i++) {
			String value = json.substring(i, i + 1);
			if(flag && "\"".equals(value)){
				flag = false;
			}else if(!flag && "\"".equals(value)){
				flag = true;
			}
			if(flag){
				if (CharContents.LEFT_DAKUOHAO.equals(value)) {
					// 左大括号
					DakuohaoEntity d = new DakuohaoEntity(DakuohaoEntity.DakuohaoType.LEFT, i);
					pairingDa(d);
					Main.kuohaoEntities.add(d);
				}
				if (CharContents.RIGHT_DAKUOHAO.equals(value)) {
					// 右大括号
					DakuohaoEntity d = new DakuohaoEntity(DakuohaoEntity.DakuohaoType.RIGHT, i);
					pairingDa(d);
					Main.kuohaoEntities.add(d);
				}
				if (CharContents.LEFT_ZHONGKUOHAO.equals(value)) {
					// 左中括号
					ZhongkuohaoEntity d = new ZhongkuohaoEntity(DakuohaoEntity.DakuohaoType.LEFT,
							i);
					pairingZhong(d);
					Main.kuohaoEntities.add(d);
				}
				if (CharContents.RIGHT_ZHONGKUOHAO.equals(value)) {
					// 右中括号
					ZhongkuohaoEntity d = new ZhongkuohaoEntity(DakuohaoEntity.DakuohaoType.RIGHT,
							i);
					pairingZhong(d);
					Main.kuohaoEntities.add(d);
				}
			}
		}
	}

	/**
	 * @Title: pairingZhong
	 * @Description: 匹配中括号
	 * @param @param d
	 * @return void
	 * @throws
	 */
	private void pairingZhong(ZhongkuohaoEntity d) {
		if (noZhongkuohao.size() > 0) {
			// 遍历检测最后一个
			if (noZhongkuohao.peek().getType() != d
					.getType()) {
				noZhongkuohao.peek().setPairing(d);
				d.setPairing(noZhongkuohao.pop());
			} else {
				// 添加到未配对表单
				noZhongkuohao.add(d);
			}
		} else {
			// 添加到未配对表单
			noZhongkuohao.add(d);
		}

	}

	/**
	 * @Title: pairing
	 * @Description: 大括号配对校验
	 * @param @param i
	 * @param @param left
	 * @return void
	 * @throws
	 */
	private void pairingDa(DakuohaoEntity d) {
		if (noDakuohao.size() > 0) {
			// 遍历检测最后一个
			if (noDakuohao.peek().getType() != d.getType()) {
				noDakuohao.peek().setPairing(d);
				d.setPairing(noDakuohao.pop());
			} else {
				// 添加到未配对表单
				noDakuohao.add(d);
			}
		} else {
			// 添加到未配对表单
			noDakuohao.add(d);
		}
	}
}
