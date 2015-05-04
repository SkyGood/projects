<#include "/uccyou.ftl">

<@body title="考勤表">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>考勤表&nbsp;<a href='<@url href="/teacher/checking/${classId}"/>'>返回</a></h1>
        <table class="ta-table-commons" id="checkingtable">
            <input type="hidden" value="${classId}" name="teachingClassId" />
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>缺席</th>
                <th>请假</th>
                <th>操作</th>
            </tr>

            <#if pageModel??>
                <#list pageModel.records as stu>
                    <tr>
                        <td>${stu.studentCode!}</td>
                        <td>${stu.studentName!}</td>
                        <td>${stu.absentTime!}</td>
                        <td>${stu.noteTime!}</td>
                        <td><a href="${stu.studentId}" class="absent">缺席</a> | 
                            <a href="${stu.studentId}" class="note">请假</a>
                        </td>
                    </tr>
                </#list>
            </#if>
        </table>
        
        <#if type == "a">
            <@tag.paging requestUrl="${ctx}/teacher/checking/all" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
        </#if>
        
        <#if type == "m">
            <@tag.paging requestUrl="${ctx}/teacher/checking/admin/p" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
        </#if>
        
        <#if type == "r">
            <@tag.paging requestUrl="${ctx}/teacher/checking/random" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
            <a href='<@url href="/teacher/checking/close"/>' class="ta-close-checking">点名结束</a>    
        </#if>
    </div>
</div>
</@body>