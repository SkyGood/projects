<#include "/uccyou.ftl">

<@body title="编辑作业">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>编辑作业&nbsp;<a href='<@url href="/teacher/homework/search/${classId}"/>'>返回</a></h1>
        <div class="ta-usercenter">
            <form action='<@url href="/teacher/homework/update/${classId!}" />' method="post" enctype="multipart/form-data">
                <input name="workId" type="hidden" value="${(homework.workId)!}" />
                <dl>
                    <dd>名称：<input type="text" name="workName" value="${(homework.workName)!}" class="input-info"/></dd>
                    <dd></dd>
                    <dd>附件：<input type="file" name="res" /><span class="tip">上传新文件将删除原文件</span></dd>
                    <dd></dd>
                    <dd style="height:100px;line-height:100px;">描述：<textarea name="content" class="input-textarea">${(homework.content)!}</textarea></dd>
                    <dd><input name="teacherId" type="hidden" value="${teacherId!}"/></dd>
                    <dd><input type="submit" value="提交" class="input-submit"/></dd>
                </dl>
            </form>
        </div>
    </div>
</div>
</@body>