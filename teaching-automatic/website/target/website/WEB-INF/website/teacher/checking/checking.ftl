<#include "/uccyou.ftl">

<@body title="考勤系统">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>开始考勤&nbsp;<a href='<@url href="/teacher/clazz/manage/${classId}"/>'>返回</a></h1>
        <div class="ta-checkingway">
            <dl>
                <dd><a href='<@url href="/teacher/checking/all/${classId}"/>'>全班考勤</a></dd>
                <#if adminClass??>
                    <#list adminClass as admin>
                        <dd><a href='<@url href="/teacher/checking/admin/${admin.adminClassId}/${classId}"/>'>考勤${admin.adminClassName}</a></dd>
                    </#list>
                </#if>
                <form action='<@url href="/teacher/checking/random"/>' method="post">
                    <input type="hidden" name="classId" value="${classId!}"/>
                    <dd>随机考勤&nbsp;<input type="text" name="count" class="input-number"/>&nbsp;人&nbsp;<input type="submit" class="input-submit" value="开始"/></dd>
                </form>
            </dl>
        </div>
    </div>
</div>
</@body>