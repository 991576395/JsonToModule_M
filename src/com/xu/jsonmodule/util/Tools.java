package com.xu.jsonmodule.util;

import com.intellij.openapi.project.Project;

import java.io.File;
import java.io.FileFilter;

/**
 */
public class Tools {
    public static final String jsontojava = "package ${packagename};\n" +
            "<#list pros as pro>\n" +
            "<#if pro.className==1>import java.util.List;<#break/></#if>\n" +
            "</#list>\n" +
            "<#list pros as pro>\n" +
            "<#if (pro.className>=3)>import java.lang.*;<#break/></#if>\n" +
            "</#list>\n" +
            "\n" +
            "public class ${classname} {\n" +
            "\t<#list pros as pro>\n" +
            "\t    <#if pro.commentsStr??>\n" +
            "    \t            /**\n" +
            "            \t    ${pro.commentsStr}\n" +
            "            \t    **/\n" +
            "    \t</#if>\n" +
            "\t\tprivate <#if pro.className==0>String</#if><#if pro.className==1>List<${pro.arrayType}></#if><#if pro.className==2>${pro.arrayType}</#if><#if pro.className==3>Boolean</#if><#if pro.className==4>Integer</#if><#if pro.className==5>Double</#if> ${pro.pro};\n" +
            "\t</#list>\n" +
            "\t\n" +
            "\t<#list pros as pro>\n" +
            "\t\tpublic <#if pro.className==0>String</#if><#if pro.className==1>List<${pro.arrayType}></#if><#if pro.className==2>${pro.arrayType}</#if><#if pro.className==3>Boolean</#if><#if pro.className==4>Integer</#if><#if pro.className==5>Double</#if> get${pro.prodaxie}(){\n" +
            "\t\t\treturn ${pro.pro};\n" +
            "\t\t}\n" +
            "\t\tpublic void set${pro.prodaxie}(<#if pro.className==0>String</#if><#if pro.className==1>List<${pro.arrayType}></#if><#if pro.className==2>${pro.arrayType}</#if><#if pro.className==3>Boolean</#if><#if pro.className==4>Integer</#if><#if pro.className==5>Double</#if> ${pro.pro}) {\n" +
            "\t\t\tthis.${pro.pro} = ${pro.pro};\n" +
            "\t\t}\n" +
            "\t</#list>\n" +
            "}\n";


    public static String getFileNameByProjectAndPackage(Project project,String packageName){
        StringBuffer sb = new StringBuffer();
        String projectFilePath = project.getBasePath();
        File file = new File(projectFilePath);
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return "src".equals(pathname.getName());
            }
        });
        if(CollectionUtils.isNotEntity(files)){
            file = files[0];
        }
        files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return "main".equals(pathname.getName());
            }
        });
        if(CollectionUtils.isNotEntity(files)){
           //是android
            files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return "java".equals(pathname.getName());
                }
            });
            if(CollectionUtils.isNotEntity(files)){
                sb.append(files[0].getAbsoluteFile());
            }
        }else{
            sb.append(file.getAbsolutePath());
        }
        packageName  = packageName.replace(".", File.separator);
        sb.append(File.separator+packageName);
        return sb.toString();
    }



}
