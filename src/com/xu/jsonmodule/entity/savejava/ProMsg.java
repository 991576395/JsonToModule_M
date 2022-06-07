package com.xu.jsonmodule.entity.savejava;


import com.xu.jsonmodule.util.StringUtils;

/**
 * @author xuzhenyao
 * @ClassName: ClassMsg
 * @Description: 生产类的实体
 * @date 2015-7-15 下午2:16:51
 */
public class ProMsg {
    public interface ClassType {
        /**
         * 字段
         */
        public int STRING = 0;
        /**
         * 集合
         */
        public int ARRAY = 1;
        /**
         * 对象
         */
        public int OBJECT = 2;
        /**
         * boolean类型
         */
        public int BOOELAN = 3;
        /**
         * int
         */
        public int INTEGER = 4;
        /**
         * DOUBLE
         */
        public int DOUBLE = 5;
    }

    private String pro;
    private String prodaxie;
    private int className;

    /**
     * 注释
     */
    private String commentsStr;

    /**
     * 集合类型
     */
    private String arrayType = "String";

    /**
     * 字段类型  默认字符串
     */
    private Class fieldType = String.class;

    //
    public ProMsg(String pro, int className, String commentsStr) {
        super();
        if (!StringUtils.isEmpty(pro)) {
            pro = pro.trim();
        }
        this.className = className;
        this.pro = pro;
        if (pro != null && pro.length() > 0) {
            this.prodaxie = pro.substring(0, 1).toUpperCase() + pro.substring(1, pro.length());
        }
        this.commentsStr = commentsStr;
    }

    //
    public ProMsg(String pro, String commentsStr) {
        super();
        if (!StringUtils.isEmpty(pro)) {
            pro = pro.trim();
        }
        this.className = ClassType.STRING;
        this.pro = pro;
        if (pro != null && pro.length() > 0) {
            this.prodaxie = pro.substring(0, 1).toUpperCase() + pro.substring(1, pro.length());
        }
        this.commentsStr = commentsStr;
    }

    public Class getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class fieldType) {
        this.fieldType = fieldType;
    }

    public String getCommentsStr() {
        return commentsStr;
    }

    public void setCommentsStr(String commentsStr) {
        this.commentsStr = commentsStr;
    }

    public String getArrayType() {
        return arrayType;
    }

    public void setArrayType(String arrayType) {
        this.arrayType = arrayType;
    }

    public int getClassName() {
        return className;
    }

    public void setClassName(int className) {
        this.className = className;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getProdaxie() {
        return prodaxie;
    }

    public void setProdaxie(String prodaxie) {
        this.prodaxie = prodaxie;
    }

    @Override
    public String toString() {
        return "ProMsg{" +
                "pro='" + pro + '\'' +
                ", prodaxie='" + prodaxie + '\'' +
                ", className=" + className +
                ", commentsStr='" + commentsStr + '\'' +
                ", arrayType='" + arrayType + '\'' +
                '}';
    }
}
