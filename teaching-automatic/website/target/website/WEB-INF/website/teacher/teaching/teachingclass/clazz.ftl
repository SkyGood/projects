<#include "/uccyou.ftl">
<@body title="教学班级列表">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>教学班管理&nbsp;<a href='<@url href="/teacher/clazz/add"/>'>新增</a></h1>
        <table class="ta-table-commons">
            <tr>
                <th>班级名称</th>
                <th>课程</th>
                <th>教室</th>
                <th>课程类型</th>
                <th>开始周</th>
                <th>结束周</th>
                <th>上课时间</th>
                <th>学分</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            <#if pageModel.records??>
                <#list pageModel.records as record>
                    <tr>
                        <td>${record.className!}</td>
                        <td>${record.courseName!}</td>
                        <td>${record.classRoom!}</td>
                        <td>${(record.courseType == "T")?string("考试","考查")}</td>
                        <td>${record.startWeek!}</td>
                        <td>${record.endWeek!}</td>
                        <td>${record.teachingTime!}</td>
                        <td>${record.credit!}</td>
                        <td>${record.createDate?string("yyyy-MM-dd")}</td>
                        <td><a href='<@url href="/teacher/clazz/change/${record.classId}" />'>编辑</a> | <a href='<@url href="/teacher/clazz/remove/${record.classId}"/>'>删除</a> | <a href='<@url href="/teacher/clazz/manage/${record.classId}" />'>控制面板</a></td>
                    </tr>
                </#list>
            </#if>
        </table>
        <@tag.paging requestUrl="${ctx}/teacher/clazz" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
    </div>
</div>
</@body>