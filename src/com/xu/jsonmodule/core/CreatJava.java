package com.xu.jsonmodule.core;

import java.util.*;

import com.xu.jsonmodule.entity.BaseEntity;
import com.xu.jsonmodule.entity.savejava.ClassMsg;
import com.xu.jsonmodule.entity.savejava.ProMsg;
import com.xu.jsonmodule.main.Main;
import com.xu.jsonmodule.util.CollectionUtils;
import com.xu.jsonmodule.util.FreeMarkertUtil;
import com.xu.jsonmodule.util.StringUtils;

/**
 * @author xuzhenyao
 * @ClassName: CreatJava
 * @Description: 生产java文件
 * @date 2015-7-15 下午2:41:59
 */
public class CreatJava {
    private BaseEntity baseEntity;

    private Map<String, Integer> classNameMap;

    public CreatJava(BaseEntity baseEntity) throws Exception {
        super();
        this.baseEntity = baseEntity;
        classNameMap = new HashMap<>();
        System.out.println(baseEntity.toString());
        toCreate();
    }

    private void toCreate() throws Exception {
        parseBaseEntity(baseEntity, null,"");
        start(Main.classMsg);
    }

    private void start(List<ClassMsg> ps) throws Exception {
        for (ClassMsg root : ps) {
            System.out.println(root.toString());
            FreeMarkertUtil.analysisTemplate("jsontojava.ftl", root,
                    root.getClassname());
        }
    }

    /**
     * @param @return
     * @return String
     * @throws
     * @Title: getClassName
     * @Description: 获得类名
     */
    private String getClassName(String keyName) {
        if(keyName == null){
            keyName = "RootEntity";
        }else{
            String start = keyName.substring(0,1).toUpperCase();
            if(keyName.length() > 1){
                keyName = start + keyName.substring(1);
            }
            keyName = keyName + "Entity";
        }

        //首字母大写
        Integer integer = classNameMap.get(keyName);
        if(integer != null){
            integer += 1;
            keyName += integer;
            classNameMap.put(baseEntity.getKey(),integer);
        }else{
            classNameMap.put(baseEntity.getKey(),0);
        }
        return keyName;
    }

    /**
     * @param @param baseEntity
     * @return void
     * @throws
     * @Title: parseBaseEntity
     * @Description: 核心解析
     */
    private String parseBaseEntity(BaseEntity baseEntity, ClassMsg msg,String keyName) {
        if (baseEntity == null) {
            return "";
        }
        if (baseEntity.getType() == BaseEntity.EntityType.OBJECT) {
            // 是对象
            String className = "";
            if(!StringUtils.isEmpty(keyName)){
                className = getClassName(keyName);
            }else{
                className = getClassName(baseEntity.getKey());
            }

            msg = new ClassMsg(className, new ArrayList<ProMsg>());
            List<BaseEntity> filds = baseEntity.getEntitys();
            if (CollectionUtils.isNotEntity(filds)) {
                for (BaseEntity fild : filds) {
                    if (fild.getType() == BaseEntity.EntityType.FIELD) {
                        ProMsg proMsg = new ProMsg(fild.getKey(), fild.getCommentsStr());
                        proMsg.setClassName(fild.getFieldType());
                        msg.getPros().add(proMsg);
                        continue;
                    } else if (fild.isArray()) {
                        // 是字符串集合
                        msg.getPros().add(
                                new ProMsg(fild.getKey(),
                                        ProMsg.ClassType.ARRAY,fild.getCommentsStr()));
                    } else {
                        // 为对象集合
                        // 第一步先创建对象
                        String cName = parseBaseEntity(fild, msg,null);
                        if (fild.getType() == BaseEntity.EntityType.OBJECT) {
                            ProMsg pro = new ProMsg(fild.getKey(), ProMsg.ClassType.OBJECT,fild.getCommentsStr());
                            pro.setArrayType(cName);
                            if (!StringUtils.isEmpty(fild.getKey())) {
                                msg.getPros().add(pro);
                            }
                        }
                    }
                }
            }
            Main.classMsg.add(msg);
            return msg.getClassname();
        } else if (baseEntity.getType() == BaseEntity.EntityType.LIST) {
            // 是集合
            // 包含是判断类型
            if (baseEntity.isArray()) {
                // 是字符串集合
                msg.getPros().add(
                        new ProMsg(baseEntity.getKey(), ProMsg.ClassType.ARRAY,baseEntity.getCommentsStr()));
            } else if (baseEntity.getEntitys() != null) {
                for (BaseEntity fild : baseEntity.getEntitys()) {
                    // 为对象集合
                    String cName = parseBaseEntity(fild,msg,baseEntity.getKey());
                    ProMsg pro = new ProMsg(baseEntity.getKey(), ProMsg.ClassType.ARRAY,baseEntity.getCommentsStr());
                    pro.setArrayType(cName);
                    if (!StringUtils.isEmpty(baseEntity.getKey())) {
                        msg.getPros().add(pro);
                    }
                }
            }
        }
        return "";
    }

}
