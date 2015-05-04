<#include "/uccyou.ftl">

<@body title="我的班级">
${tip!}
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>我的班级</h1>
        
        <div class="ta-table-search">
            <form action='<@url href="/student/subject/search" />' method="post">
                课程名称：<input type="text" name="courseName" value="${(subject.courseName)!}" class="input-contact" />&nbsp;&nbsp;
                类型：
                <select name="courseType" class="input-select">
                    <option <#if (subject.courseType)??> <#if subject.courseType=="T"> selected="selected" </#if> </#if> value="T">考试</option>
                    <option <#if (subject.courseType)??> <#if subject.courseType=="C"> selected="selected" </#if> </#if> value="C">考查</option>
                </select>&nbsp;&nbsp;
                <input name="identityCode" type="hidden" value="${(subject.identityCode)!}"/>
                <input type="submit" value="搜索" class="input-submit" />
            </form>
        </div>
        
        <table class="ta-table-commons" id="studentSubject">
            <tr>
                <th>课程</th>
                <th>教师</th>
                <th>教室</th>
                <th>类型</th>
                <th>周期</th>
                <th>上课时间</th>
                <th>学分</th>
                <th>操作</th>
            </tr>
            
            <#if pageModel??>
                <#list pageModel.records as record> 
                    <tr>
                        <td>${(record.courseName)!}<input name="notice" type="hidden" value="${(record.notice)!}" /></td>
                        <td>${(record.teacherName)!}</td>
                        <td>${(record.classRoom)!}</td>
                        <td>
                            <#if (record.courseType) == "C">
                                                                                考查
                            <#else>
                                                                                考试
                            </#if>
                        </td>
                        <td>${(record.startWeek)!}&nbsp;-&nbsp;${(record.endWeek)!}</td>
                        <td>${(record.teachingTime)!}</td>
                        <td>${(record.credit)!}</td>
                        <td><a href='#' class="notice">公告</a> | <a href='<@url href="/student/homework/search" />'>作业</a> | <a href='<@url href="/student/resources/search/${record.classId!}" />'>资源</a></td>
                    </tr>
                </#list>
            </#if>
        </table>
        <@tag.paging requestUrl="${ctx}/student/subject/search" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
    </div>
</div>


<div class="notice-dialog" id="noticeDialog">
    <span>班级公告<img src='<@url href="/site-static/images/close.png"/>' alt=""></span>
    <p></p>
</div>
</@body>