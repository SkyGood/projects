<#include "/uccyou.ftl">

<@body title="班级资源">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>班级资源</h1>
        <div class="ta-table-search">
            <form action='<@url href="/student/resources/search" />' method="post">
                <input name="classId" type="hidden" value="${(res.classId)!}" />
                资源名称：<input type="text" name="resName" value="${(res.resName)!}" class="input-contact" />&nbsp;&nbsp;
                <input type="submit" value="搜索" class="input-submit" />
            </form>
        </div>
        <table class="ta-table-commons">
            <tr>
                <th>资源名称</th>
                <th>下载</th>
            </tr>
            <#if pageModel??>
                <#list pageModel.records as record>
                    <tr>
                        <td>${(record.resName)!}</td>
                        <td><a href='<@url href="${(record.resLocation)!}" />'>下载</a></td>
                    </tr>
                </#list>
            </#if>
        </table>
        <@tag.paging requestUrl="${ctx}/student/resources/search" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
    </div>
</div>
</@body>