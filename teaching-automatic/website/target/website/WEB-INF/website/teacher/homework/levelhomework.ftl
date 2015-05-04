<#include "/uccyou.ftl">

<@body title="批改作业">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>批改作业</h1>
        <div class="ta-usercenter">
            <form action='<@url href="/teacher/homework/level" />' method="post">
                <dl>
                    <dd>姓名&nbsp;&nbsp;${response.studentName!}</dd>
                    <dd>学号&nbsp;&nbsp;${response.studentCode!}</dd>
                    <dd>班级&nbsp;&nbsp;${response.adminClassName!}</dd>
                    <dd>时间&nbsp;&nbsp;${response.createDate?string("yyyy-MM-dd HH:mm:ss")}</dd>
                    <#if response.resLocation ??>
                        <dd>附件&nbsp;&nbsp;<a href='<@url href="${response.resLocation!}" />'>点击下载附件</a></dd>
                    </#if>
                    <dd>答案</dd>
                    <dd style="height:auto;width:800px;font-size:12px;color:#666;">${response.content!}</dd>
                    <dd>评分&nbsp;&nbsp;<input type="text" name="level" value="${(response.level)!}" class="input-number"/></dd>
                    <input type="hidden" name="id" value="${id!}" />
                    <dd></dd>
                    <dd><input type="submit" value="提交" class="input-submit"/><span class="tip">${tip!}</span></dd>
                </dl>
            </form>
        </div>
    </div>
</div>
</@body>