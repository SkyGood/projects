<#include "/uccyou.ftl">

<@body title="完成作业">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>完成作业&nbsp;<a href='<@url href="/student/homework/search" />'>返回</a></h1>
        <div class="ta-usercenter">
            <form action='<@url href="/student/homework/commit" />' method="post" enctype="multipart/form-data">
                <input name="workId" type="hidden" value="${workId!}" />
                <input name="userId" type="hidden" value="${userId!}" />
                <input name="studentCode" type="hidden" value=${studentCode!}>
                <input name="resName" type="hidden" value=${(homework.workName)!}>
                <dl>
                    <dd>作业名称&nbsp;&nbsp;${(homework.workName)!}</dd>
                    <#if (homework.resLocation) ??>
                        <dd>作业附件&nbsp;&nbsp;<a href='<@url href="${(homework.resLocation)!}"/>'>点击下载附件</a></dd>
                    </#if>
                    <dd>作业描述</dd>
                    <dd style="height:auto;width:800px;font-size:12px;color:#666;">${(homework.content)!}</dd>
                    <dd>你的答案</dd>
                    <dd style="height:200px;">
                    <textarea name="content" style="height:200px;width:800px;border:1px solid #999;"></textarea>
                    </dd>
                    <dd></dd>
                    <dd>答案附件&nbsp;&nbsp;<input type="file" name="res" /><span class="tip">请上传10M以内的文件</span></dd>
                    <dd></dd>
                    <dd><input type="submit" value="提交" class="input-submit"/></dd>
                </dl>
            </form>
        </div>
    </div>
</div>
</@body>