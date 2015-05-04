<#include "/uccyou.ftl">

<@body title="家庭作业">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>班级作业</h1>
        <div class="ta-table-search">
            <form action='<@url href="/student/homework/search" />' method="post">
                <input name="identityCode" type="hidden" value="${(homework.identityCode)!}" />
                课程名称：<input type="text" name="workName" value="${(homework.workName)!}" class="input-contact" />&nbsp;&nbsp;
                类型：
                <select name="status" class="input-select">
                    <option <#if (homework.status)??><#if homework.status == "Y"> selected="selected" </#if></#if> value="Y">已交</option>
                    <option <#if (homework.status)??><#if homework.status == "N"> selected="selected" </#if></#if> value="N">未交</option>
                </select>&nbsp;&nbsp;
                <input type="submit" value="搜索" class="input-submit" />
            </form>
        </div>
        <table class="ta-table-commons">
            <tr>
                <th>作业名称</th>
                <th>操作</th>
            </tr>
            <#if pageModel??>
                <#list pageModel.records as record>
                    <tr>
                        <td>${(record.workName)!}</td>
                        <td><a href='<@url href="/student/homework/commit/${(record.workId)!}"/>'>做作业</a></td>
                    </tr>
                </#list>
            </#if>
        </table>
        <@tag.paging requestUrl="${ctx}/student/homework/search" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
    </div>
</div>

</@body>