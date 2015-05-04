<#include "/uccyou.ftl">
<@body title="资源管理">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>资源管理&nbsp;<a href='<@url href="/teacher/resource/upload/${classId}" />'>新增</a></h1>
        <div class="ta-table-search">
            <form action='<@url href="/teacher/resource/search/${classId!}" />' method="post">
                资源名称：<input type="text" name="resName" value="${(res.resName)!}" class="input-contact" />&nbsp;&nbsp;
                <input type="submit" value="搜索" class="input-submit" />
            </form>
        </div>
        <table class="ta-table-commons" id="resourcesList">
            <input type="hidden" name="teachingClassId" value="${classId}" />
            <tr>
                <th>名称</th>
                <th>状态</th>
                <th>操作</th>
            </tr>

            <#if (pageModel.records)??>
                <#list pageModel.records as record>
                    <tr>
                        <td>${record.resName!}</td>
                        <td>
                            <#if record.alive = "Y">
                                <a href="${record.resId}" status="N" class="resourcesStatus">禁用</a>
                            <#else>
                                <a href="${record.resId}" status="Y" class="resourcesStatus">启用</a>
                            </#if>
                        </td>
                        <td><a href="${record.resId}" class="Rdel">删除</a></td>
                    </tr>
                </#list>
            </#if>
        </table>
        <@tag.paging requestUrl="${ctx}/teacher/resource/search/${classId!}" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
    </div>
</div>
</@body>