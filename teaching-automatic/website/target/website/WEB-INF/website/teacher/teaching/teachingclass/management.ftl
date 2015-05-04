<#include "/uccyou.ftl">

<@body title="教学班控制面板"> 
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>控制面板&nbsp;<a href='<@url href="/teacher/clazz"/>'>返回</a></h1>
        <table cellspacing="1" class="ta-controll">
            <tr>
                <td>班级名称</td><td>${clazz.className!}</td>
                <td>课程名称</td><td>${clazz.courseName!}</td>
                <td>上课教室</td><td>${clazz.classRoom!}</td>
                <td>课程类型</td><td>${(clazz.courseType == "T")?string("考试","考查")}</td>
            </tr>
            <tr>
                <td>开课周数</td><td>${clazz.startWeek!}</td>
                <td>结课周数</td><td>${clazz.endWeek!}</td>
                <td>上课时间</td><td>${clazz.teachingTime!}</td>
                <td>课程学分</td><td>${clazz.credit!}</td>
            </tr>
            <tr><td colspan="8" style="text-align:left;text-indent:10px;height:auto;">班级公告：${clazz.notice!}</td></tr>
        </table>

        <h2>行政班&nbsp;<a href='<@url href="/teacher/clazz/admin/add"/>' class="ta-adminclass-addclass" id="adminAddClass">新增</a></h2>
        <div class="ta-adminclass">
            <form id="adminClass">
                <#list list as admin>
                    <span><p>${admin.adminClassName}</p>[<a href="${admin.adminClassId}" name="modify">修改</a>][<a href="${admin.adminClassId}" name="clean">清空</a>][<a href="${admin.adminClassId}" name="del">删除</a>]</span>
                </#list>
            </form>
        </div>

        <table class="ta-controll-cards">
            <tr>
                <td><a href='<@url href="/teacher/clazz/import/${clazz.classId!}" />'><img src='<@url href="/site-static/icon/importstu.png"/>' /></a></td>
                <td><a href='<@url href="/teacher/checking/${clazz.classId!}"/>'><img src='<@url href="/site-static/icon/checking.png"/>' /></a></td>
                <td><a href='<@url href="/teacher/homework/add/${clazz.classId!}"/>'><img src='<@url href="/site-static/icon/addhomework.png"/>' /></a></td>
                <td><a href='<@url href="/teacher/homework/search/${clazz.classId!}" />'><img src='<@url href="/site-static/icon/managehomework.png"/>' /></a></td>
                <td><a href='<@url href="/teacher/resource/upload/${clazz.classId!}" />'><img src='<@url href="/site-static/icon/uploadres.png"/>' /></a></td>
                <td><a href='<@url href="/teacher/resource/search/${clazz.classId!}"/>'><img src='<@url href="/site-static/icon/manageres.png"/>' /></a></td>
            </tr>
        </table>
    </div>
</div>

<div class="adminclass-addclass-dialog">
    <form action="#" id="classDialog">
        <dl>
            <dd><span>班级名称</span>
                <span><img class="Imgclose" src='<@url href="/site-static/images/close.png"/>' alt=""></span>
            </dd>
            <dd>班级名称<input type="text" name="adminClassName" /></dd>
            <dd><input type="hidden" name="teachingClassId" value="${clazz.classId!}" /><input type="submit" value="确定" /></dd>
        </dl>
    </form>
</div>    
</@body>