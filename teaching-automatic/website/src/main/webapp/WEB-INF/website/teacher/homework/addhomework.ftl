<#include "/uccyou.ftl">

<@body title="布置作业">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>布置作业&nbsp;<a href='<@url href="/teacher/clazz/manage/${classId}"/>'>返回</a></h1>
        <div class="ta-usercenter">
            <form action='<@url href="/teacher/homework/add" />' method="post" enctype="multipart/form-data" id="addHomeWork">
                <input name="classId" type="hidden" value="${classId!}" />
                <input name="teacherId" type="hidden" value="${teacherId!}"/>
                <dl>
                    <dd>名称：<input type="text" name="workName" class="input-info"/></dd>
                    <dd></dd>
                    <dd>附件：<input type="file" name="res" /><span class="tip">请上传10M以内的文件</span></dd>
                    <dd></dd>
                    <dd style="height:100px;line-height:100px;">描述：<textarea name="content" class="input-textarea"></textarea></dd>
                    <dd><input type="submit" value="发布" class="input-submit"/><span class="tip"><#if tip??>${tip}</#if></span></dd>
                </dl>
            </form>
        </div>
    </div>
</div>
</@body>