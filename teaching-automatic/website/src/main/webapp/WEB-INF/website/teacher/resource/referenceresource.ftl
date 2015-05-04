<#include "/uccyou.ftl">

<@body title="资源引用">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>资源引用&nbsp;<a href='<@url href="/teacher/resource/search/${(res.classId)!}"/>'>返回</a></h1>
        <div class="ta-table-search">
            <form action='<@url href="/teacher/resource/reference"/>' method="post">
               	 资源名称：  <input type="text" name="resName" value="${(res.resName)!}" class="input-contact" />&nbsp;&nbsp;
	   	             	<input type="hidden" name="teacherId" value="${(res.teacherId)!}"/>
     	        		<input type="hidden" name="classId" value="${(res.classId)!}"/>
               	 		<input type="submit" value="搜索" class="input-submit" />
            </form>
        </div>
        <table class="ta-table-commons" id="reference">
            <tr>
                <th>资源名称</th>
                <th>操作</th>
            </tr>

            <#if (pageModel.records)??>
                <#list (pageModel.records) as record>
                    <tr>
                        <td>${record.resName!}</td>
                        <td><a href="${record.resId}">引用</a></td>
                    </tr>
                </#list>
            </#if>
        </table>
        <@tag.paging requestUrl="${ctx}/teacher/resource/reference" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
    </div>
</div>
</@body>