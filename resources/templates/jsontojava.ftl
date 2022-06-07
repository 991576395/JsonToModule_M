package ${packagename};
<#list pros as pro>
<#if pro.className==1>import java.util.List;<#break/></#if>
</#list>
<#list pros as pro>
<#if (pro.className>=3)>import java.lang.*;<#break/></#if>
</#list>

public class ${classname} {
	<#list pros as pro>
	    <#if pro.commentsStr??>
    	            /**
            	    ${pro.commentsStr}
            	    **/
    	</#if>
		private <#if pro.className==0>String</#if><#if pro.className==1>List<${pro.arrayType}></#if><#if pro.className==2>${pro.arrayType}</#if><#if pro.className==3>Boolean</#if><#if pro.className==4>Integer</#if><#if pro.className==5>Double</#if> ${pro.pro};
	</#list>
	
	<#list pros as pro>
		public <#if pro.className==0>String</#if><#if pro.className==1>List<${pro.arrayType}></#if><#if pro.className==2>${pro.arrayType}</#if><#if pro.className==3>Boolean</#if><#if pro.className==4>Integer</#if><#if pro.className==5>Double</#if> get${pro.prodaxie}(){
			return ${pro.pro};
		}
		public void set${pro.prodaxie}(<#if pro.className==0>String</#if><#if pro.className==1>List<${pro.arrayType}></#if><#if pro.className==2>${pro.arrayType}</#if><#if pro.className==3>Boolean</#if><#if pro.className==4>Integer</#if><#if pro.className==5>Double</#if> ${pro.pro}) {
			this.${pro.pro} = ${pro.pro};
		}
	</#list>
}
