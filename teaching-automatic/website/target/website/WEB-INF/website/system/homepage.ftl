<#include "/uccyou.ftl">

<@body title="欢迎">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <div class="ta-welcome">
                            欢迎您，${name}[${identityCode}]&nbsp;${(identity == "T")?string("教师","学生")}
        </div>

        <table class="ta-table-homepage">
            <tr><td colspan="2" style="border-bottom:1px solid #00a797;">系统公告</td></tr>
            <#if notice??>
                <#list notice as n>
                    <tr>
                        <td>
                            <a href="#">${n.title}</a>
                        </td>
                    </tr>
                </#list>
            </#if>
        </table>
    </div>
</div>
</@body>