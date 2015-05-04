<#include "/uccyou.ftl">

<@body title="作业详情">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>作业完成情况&nbsp;<a href='<@url href="/teacher/homework/search/${classId!}"/>'>返回</a></h1>
        <div class="ta-table-search">
            <form action="#" method="post">
                状态：
                <select name="status" class="input-select" id="homeworkcommit">
                    <option value="commit" <#if type??><#if type=="commit"> selected="selected" </#if></#if> >已交</option>
                    <option value="uncommit" <#if type??><#if type=="uncommit"> selected="selected" </#if></#if> >未交</option>
                </select>
            </form>
        </div>
        <table class="ta-table-commons">
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>行政班</th>
                <th>成绩</th>
                <th>操作</th>
            </tr>

            <#if (pageModel.records)??>
                <#list pageModel.records as record>
                    <tr>
                        <td>${record.studentCode!}</td>
                        <td>${record.studentName!}</td>
                        <td>${record.studentAdminClassName!}</td>
                        <td>${(record.level)!}</td>
                        <td>
                            <#if type == "commit">
                                <a href='<@url href="/teacher/homework/level/${record.stuHomeworkId!}"/>'>批阅</a>
                            </#if>
                            <#if type == "uncommit">
                                待完成
                            </#if>
                        </td>
                    </tr>
                </#list>
            </#if>
        </table>
        
        <#if type == "uncommit">
            <@tag.paging requestUrl="${ctx}/teacher/homework/uncommit" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
        </#if>
        
        <#if type == "commit">
            <@tag.paging requestUrl="${ctx}/teacher/homework/commit" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
        </#if>
        
    </div>
</div>
</@body>