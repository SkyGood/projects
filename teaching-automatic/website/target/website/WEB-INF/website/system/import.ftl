<#include "/uccyou.ftl">

<@body title="导入用户">

<body>
<h1>
<#if importCount??>
数据导入成功，本次导入数据${importCount}条！
</#if>

<#if tip??>
${tip}
</#if>
</h1>
导入师生工号/学号：
    <form action='<@url href="/admin/import"/>' method="post" enctype="multipart/form-data">
    用户类型：
        <select name="identity">
            <option value="T">教师</option>
            <option value="S">学生</option>
        </select>
        <br/><br/>
        电子表格：<input type="file" name="excel" />
        <br/><br/>
        <input type="submit" value="导入" />
    </form>
</body>

</@body>