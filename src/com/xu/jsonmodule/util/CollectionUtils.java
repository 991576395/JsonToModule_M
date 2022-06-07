package com.xu.jsonmodule.util;


import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 作者 徐珍耀 on 2016/6/16 09:52
 * 邮箱：991576395@qq.com
 */
public class CollectionUtils {
    /**
     * 集合非空
     * @param collection
     * @return
     */
    public static boolean isNotEntity(Collection collection){
        return collection != null && collection.size() > 0;
    }

    /**
     * 集合非空
     * @param collection
     * @return
     */
    public static boolean isNotEntity(Map collection){
        return collection != null && collection.size() > 0;
    }

    public static boolean isNotEntity(Object[] collection){
        return collection != null && collection.length > 0;
    }

    /**
     * 两个集合是否相同
     * @param one
     * @param two
     * @return
     */
    public static  boolean twoListIsSame(List one, List two){
        if(one.size() == two.size() && one.containsAll(two)){
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Collection collection) {
        return !isNotEntity(collection);
    }
}
