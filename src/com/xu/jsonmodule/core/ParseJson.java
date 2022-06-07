package com.xu.jsonmodule.core;

import com.xu.jsonmodule.entity.BaseEntity;
import com.xu.jsonmodule.entity.DakuohaoEntity;
import com.xu.jsonmodule.entity.ZhongkuohaoEntity;
import com.xu.jsonmodule.main.Main;
import com.xu.jsonmodule.util.CharContents;
import com.xu.jsonmodule.util.StringUtils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: ParseJson
 * @Description: 解析json字符
 * @author xuzhenyao
 * @date 2015-7-15 下午2:33:16
 */
public class ParseJson {
	private String json;


	public BaseEntity paseJson(String json){
		// 生产第一层对象
		BaseEntity entity = null;
		// 解析符号
		MyJsonReader jsonReader = new MyJsonReader(new StringReader(json));
		try{
			entity = jsonReader.begin();
		}catch (Exception e){
			e.printStackTrace();
		}
		return entity;
	}

	/**
	 * @Title: paseJson
	 * @Description:解析核心
	 * @param @param json
	 * @param @return
	 * @return BaseEntity
	 * @throws
	 */
//	public BaseEntity paseJson(String json) {
//		// 解析符号
//		ParsingSymbol ps = new ParsingSymbol();
//		ps.getDakuohao(json);
//		// 生产第一层对象
//		BaseEntity entity = new BaseEntity();
//		// 第一层
//		entity.setLevel(1);
//		entity.setValue(json);
//		entity.setType(EntityType.OBJECT);
//		this.json = json;
//		return getJsonEntity(entity, -1);
//	}

	/**
	 * @Title: getJsonEntity
	 * @Description:组建对象实体
	 * @param @param json
	 * @param @return
	 * @return BaseEntity
	 * @throws
	 */
	private BaseEntity getJsonEntity(BaseEntity entity, int i) {
		List<BaseEntity> baseEntities = new ArrayList<BaseEntity>();
		// if(Main.kuohaoEntities != null && Main.kuohaoEntities.size() > 0){
		// 最原始对象结构
		if (entity.getValue().indexOf(CharContents.LEFT_DAKUOHAO) == -1
				&& entity.getValue().indexOf(CharContents.LEFT_ZHONGKUOHAO) == -1) {
			getBaseKeys(entity, i);
			return entity;
		} else {
			// 非基本对象的keys解析 为对象时解析
			if (entity.getType() == BaseEntity.EntityType.OBJECT) {
				getKeys(entity, i+1);
			}
		}
		do {
			i++;
			// 开始的大括号
			int start = Main.kuohaoEntities.get(i).getIndex();
			// 开始的大括号配对的括号
			int end = Main.kuohaoEntities.get(i).getPairing().getIndex();
			if (start > end) {
				break;
			}
			if (Main.kuohaoEntities.get(i) instanceof ZhongkuohaoEntity) {
				// 中括号
				BaseEntity otherEntity = new BaseEntity();
				otherEntity.setLevel(entity.getLevel() + 1);
				otherEntity.setValue(json.substring(start + 1, end));
				otherEntity.setKey(getKey(i));
				otherEntity.setType(BaseEntity.EntityType.LIST);
				otherEntity = getJsonEntity(otherEntity, i);
				baseEntities.add(otherEntity);
				i = Main.kuohaoEntities.indexOf(Main.kuohaoEntities.get(i)
						.getPairing());
			} else if (Main.kuohaoEntities.get(i) instanceof DakuohaoEntity) {
				// 大括号
				BaseEntity otherEntity = new BaseEntity();
				otherEntity.setLevel(entity.getLevel() + 1);
				otherEntity.setValue(json.substring(start + 1, end));
				otherEntity.setKey(getKey(i));
				otherEntity.setType(BaseEntity.EntityType.OBJECT);
				otherEntity = getJsonEntity(otherEntity, i);
				baseEntities.add(otherEntity);
				i = Main.kuohaoEntities.indexOf(Main.kuohaoEntities.get(i)
						.getPairing());
			}
		} while (Main.kuohaoEntities.indexOf(Main.kuohaoEntities.get(i)
				.getPairing()) < Main.kuohaoEntities.size() - 1
				&& i < Main.kuohaoEntities.size() - 1);
		// }
		if (baseEntities != null && baseEntities.size() > 0) {
			entity.setEntitys(baseEntities);
		}
		return entity;
	}

	/**
	 * @Title: getKeys
	 * @Description:获得对象中的key
	 * @param @param entity
	 * @return void
	 * @throws
	 */
	private void getKeys(BaseEntity entity,int i) {
//		boolean firstFlag = true;
		System.out.println("i 的值:"+i);
		if(i == -1 ){
			return;
		}
		//结束标记
		int k = 0;
		if(i != 0){
			 k = Main.kuohaoEntities.indexOf(Main.kuohaoEntities.get(i - 1).getPairing());
		}
		
		do {
			// 开始的大括号
			int start = 0;
			int end = Main.kuohaoEntities.get(i).getIndex();
			if(i != 0){
				start = Main.kuohaoEntities.get(i - 1).getIndex();
			}
			String value = json.substring(start,end);
			System.out.println("匹配:   " + value);
			
			parseKey(entity,value,i);
			if(i == 0){
				value = json.substring(Main.kuohaoEntities.get(i).getPairing().getIndex(),json.length());
				System.out.println("匹配:   " + value);
				parseKey(entity,value,i);
			}
			i = Main.kuohaoEntities.indexOf(Main.kuohaoEntities.get(i).getPairing());
			i++;
			if(i != 0 && i <=  Main.kuohaoEntities.size() - 1){
				if(i >= k){
					value = json.substring(Main.kuohaoEntities.get(i-1).getIndex(),Main.kuohaoEntities.get(i).getIndex());
					System.out.println("匹配:   " + value);
					parseKey(entity,value,i);
					break;
				}
			}
		} while (i <=  Main.kuohaoEntities.size() - 1);
		
//		System.out.println("entity.getValue():--"+entity.getValue());
	}
	
	/**
	 * @Title: parseKey 
	 * @Description: 解析字符串中的key 
	 * @param @param entity
	 * @param @param value
	 * @param @param i 
	 * @return void
	 * @throws
	 */
	private void parseKey(BaseEntity entity, String value,int i) {
//		String[] values = value.split(":");
//		if (values != null && values.length == 1 && value.indexOf(":") != -1) {
//			if (entity.getSubKeys() == null) {
//				Set<String> list = new HashSet<String>();
//				entity.setSubKeys(list);
//			}
//			entity.getSubKeys().add(getKeyString(values[0],i));
//		}else if(values != null && values.length >= 2){
//			Pattern pattern = Pattern
//					.compile("\"(.*?)\":((\"(.*?)\")|(\\[(.*?)\\])|(\\{(.*?)\\})|(.*?))");
//			Matcher matcher = pattern.matcher(value);
//			while (matcher.find()) {
//				System.out.println("匹配:   " + matcher.group(0));
//				int styleIndex = json.indexOf(matcher.group(0));
//				String[] values1 = matcher.group(0).split(":");
//				if (values1 != null && values1.length > 0
//						&& (styleIndex > Main.kuohaoEntities.get(i).getPairing().getIndex() || styleIndex < Main.kuohaoEntities.get(i).getIndex())) {
//					if (entity.getSubKeys() == null) {
//						Set<String> list = new HashSet<String>();
//						entity.setSubKeys(list);
//					}
//					entity.getSubKeys().add(getKeyString(values1[0],i));
//				}
//			}
//		}
	}

	/**
	 * @Title: getKeys 
	 * @Description: 获得对象中属性的key
	 * @param @param entity
	 * @param @param i 
	 * @return void
	 * @throws
	 */
	private void getBaseKeys(BaseEntity entity, int i) {
			if(i != -1 && Main.kuohaoEntities.get(i) instanceof ZhongkuohaoEntity){
				entity.setArray(true);
			}
			String value = entity.getValue();
			if(value != null && value.length() > 0){
				String[] values = value.split(",");
				if(values != null && values.length > 0){
					String ca = null;
					for(String str:values){
						if(!StringUtils.isEmpty(ca)){
							str = ca + str;
							ca = null;
						}else{
							try {
								int index = value.indexOf(str);
								if(!((int)value.charAt(index+str.length() + 1) == 34)){
									ca = str;
									continue;
								}
							} catch (Exception e) {
							}
						}
//						if(entity.getSubKeys() == null){
//							Set<String> list = new HashSet<String>();
//							entity.setSubKeys(list);
//						}
//						entity.getSubKeys().add(getKeyString(str,i));
					}
				}
			}
	}

	/**
	 * @Title: getKey
	 * @Description: 获得对象键值
	 * @param @param i
	 * @param @return
	 * @return String
	 * @throws
	 */
	private String getKey(int i) {
		DakuohaoEntity stop = Main.kuohaoEntities.get(i);
		if (stop.getType() == DakuohaoEntity.DakuohaoType.RIGHT) {
			return null;
		}
		// 第一个
		int stopIndex = stop.getIndex();
		String value = "";
		if (i == 0) {
			value = json.substring(0, stopIndex);
			value = getKeyString(value,i);
		} else {
			// 后面
			DakuohaoEntity start = Main.kuohaoEntities.get(i - 1);
			value = json.substring(start.getIndex() + 1, stopIndex);
			value = getKeyString(value,i);
		}
		return value;
	}

	private String getKeyString(String value,int i) {
		Pattern pattern = Pattern.compile("\"(.*?)\":((\"(.*?)\")|(\\[(.*?)\\])|(\\{(.*?)\\})|(.*?))");
		Matcher matcher = pattern.matcher(value+"\"\"");
		while(matcher.find()){
			value = matcher.group(0);
		}
		String[] values = value.split(":");
		if (values != null && values.length == 2) {
			value = values[0];
		}
		value = value.replaceAll(CharContents.RIGHT_DAKUOHAO, "");
		value = value.replaceAll(CharContents.RIGHT_ZHONGKUOHAO, "");
		value = value.replaceAll(",", "");
		value = value.replaceAll("\\[", "");
		value = value.replaceAll("\\{", "");
		value = value.replaceAll("\"", "");
		//防止值中包含 ：
		String[] vs = value.split(":");
		if(vs != null && value.length() >= 2){
			value = vs[0];
		}
		return value;
	}
	
	/**
	 * @Title: getFormatJson 
	 * @Description: 获得格式化后的数据
	 * @param @param json
	 * @param @return 
	 * @return String
	 * @throws
	 */
	public String getFormatJson(String json){
		StringBuffer format = new StringBuffer("{\n  ");
		//前后补全“{}”
		if(!json.startsWith("{")){
			json = "{"+json;
		}
		if(!json.endsWith("}")){
			json = json+"}";
		}
		int index = 2;
		for(int i = 1; i < json.length() ;i++){
			char end = json.charAt(i-1);
			char ch =  json.charAt(i);
			char next = 0;
			if(i+1 < json.length()){
				next =  json.charAt(i+1);
			}
			switch (ch) {
			case '{':
				format.append("{\n");
				index += 2;
				for(int k = 0; k < index;k++){
					format.append(" ");
				}
				break;
			case '}':
				format.append("\n");
				index -= 2;
				for(int k = 0; k < index;k++){
					format.append(" ");
				}
				format.append("}");
				break;
			case '[':
				format.append("[\n");
				index += 2;
				for(int k = 0; k < index;k++){
					format.append(" ");
				}
				break;
			case ']':
				format.append("\n");
				index -= 2;
				for(int k = 0; k < index;k++){
					format.append(" ");
				}
				format.append("]");
				break;
			case ',':
				if(("\"".charAt(0) == end && "\"".charAt(0)==next)
						|| ("}".charAt(0) == end && "{".charAt(0)==next)
						|| "]".charAt(0) == end
						|| "}".charAt(0) == end){
					format.append(",\n");
				}else{
					format.append(",");
				}
				for(int k = 0; k < index;k++){
					format.append(" ");
				}
				break;
			default:
				format.append(ch);
				break;
			}
		}
		return format.toString();
	}
}
