<#include "/uccyou.ftl">

<@body title="作业列表">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>作业列表&nbsp;<a href='<@url href="/teacher/homework/add/${classId!}"/>'>新增</a></h1>
        <div class="ta-table-search">
            <form action='<@url href="/teacher/homework/search/${classId!}" />' method="post">
                作业名称：<input type="text" name="workName" value="${(homework.workName)!}" class="input-contact" />&nbsp;&nbsp;
                <input type="submit" value="搜索" class="input-submit" />
            </form>
        </div>
        <table class="ta-table-commons" id="homeworkList">
            <tr>
                <th>名称</th>
                <th>创建时间</th>
                <th>详情</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            
            <#if (pageModel.records)??>
                <#list pageModel.records as record>
                    <tr>
                        <td>${record.workName!}</td>
                        <td>${record.createDate?string("yyyy-MM-dd")}</td>
                        <td><a href='<@url href="/teacher/homework/detail/${classId!}/${record.workId!}"/>'>查看</a></td>
                        <td>
                            <#if record.alive="Y">
                                <a href="${record.workId!}" class="homeworkStatus" status="N">关闭</a>
                            <#else> 
                                <a href="${record.workId!}" class="homeworkStatus" status="Y">开启</a>
                            </#if>
                        </td>
                        <td><a href='<@url href="/teacher/homework/update/${classId!}/${record.workId!}"/>'>编辑</a> | <a href="${record.workId!}" class="del">删除</a></td>
                    </tr>
                </#list>
            </#if>
        </table>
        <@tag.paging requestUrl="${ctx}/teacher/homework/search/${classId!}" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
    </div>
</div>  
</@body>