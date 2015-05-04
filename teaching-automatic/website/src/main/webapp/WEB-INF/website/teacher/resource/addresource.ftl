<#include "/uccyou.ftl">

<@body title="添加资源">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>添加资源&nbsp;<a href='<@url href="/teacher/resource/reference/${classId}"/>'>[引用已上传资源]</a>&nbsp;<a href='<@url href="/teacher/clazz/manage/${classId}"/>'>返回</a></h1>
        <div class="ta-usercenter">
            <form action='<@url href="/teacher/resource/upload" />' method="post" enctype="multipart/form-data" id="addresources">
                <input type="hidden" name="teacherId" value="${teacherId}" />
                <input type="hidden" name="classId", value="${classId}" />
                <dl>
                    <dd>名称：<input type="text" name="resName" class="input-info"/></dd>
                    <dd></dd>
                    <dd>附件：<input type="file" name="res" /><span class="tip">请上传10M以内的文件</span></dd>
                    <dd></dd>
                    <dd><input type="submit" value="添加" class="input-submit"/><span class="tip"><#if tip??>${tip}</#if></span></dd>
                </dl>
            </form>
        </div>
    </div>
</div>
</@body>