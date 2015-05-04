<#include "/uccyou.ftl">

<@body title="导入学生">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>导入学生&nbsp;<a href='<@url href="/teacher/clazz/manage/${classId}"/>'>返回</a></h1>
        <div class="ta-usercenter">
            <form action='<@url href="/teacher/clazz/import"/>' method="post" enctype="multipart/form-data" id="importForm">
                <input type="hidden" name="classId" value="${classId!}" />
                <dl>
                    <dd>行政班：
                        <select name="adminClassId" class="input-select">
                            <#if adminClass??>
                                <#list adminClass as admin>
                                    <option value="${admin.adminClassId!}">${admin.adminClassName!}</option>
                                </#list>
                            </#if>
                        </select>
                    </dd>
                    <dd></dd>
                    <dd>文件：<input type="file" name="excel" /><span class="tip">查看Excel文件格式要求</span></dd>
                    <dd></dd>
                    <dd><input type="submit" value="导入" class="input-submit"/>
                        <span class="tip"><#if tip??>${tip}</#if></span>
                    </dd>
                </dl>
            </form>
            <div id="loading">数据导入中...<img src='<@url href="/site-static/images/loading.gif"/>' alt=""></div>
        </div>
    </div>
</div>
</@body>