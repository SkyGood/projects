<#include "/uccyou.ftl">

<@body title="子管理员">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>子管理员&nbsp;<a href="#">新增</a></h1>
        <table class="ta-table-commons" id="alladmin">
            <tr>
                <th>用户名</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <#if admins??>
                <#list admins as admin>
                    <tr>
                        <td>${admin.username}</td>
                        <td>
                            <#if admin.alive = "Y">
                                <a href="${(admin.adminId)?c}" status="N">禁用</a>
                            <#else>
                                <a href="${(admin.adminId)?c}" status="Y">启用</a>
                            </#if>
                        </td>
                        <td><a href="${(admin.adminId)?c}" class="reset">重置密码</a> | <a href="${(admin.adminId)?c}" class="adminDel">删除</a></td>
                    </tr>
                </#list>
            </#if>
        </table>
    </div>
</div>

<div class="addadmin-pannel" id="addadminpannel">
    <span>添加用户<img src="teacher/img/close.png"></span>
    <p>用户名</p><input type="text" name="adminName">
    <input type="submit" value="添加">
</div>
</@body>