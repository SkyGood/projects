<#include "/uccyou.ftl">

<@body title="系统用户管理">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>系统用户管理&nbsp;<a href="#" id="addAdminUser">新增</a></h1>
        <div class="ta-table-search">
            <form action='<@url href="/admin/user/search"/>' method="post">
                编号：<input type="text" name="identityCode" value="${(user.identityCode)!}" class="input-contact" />&nbsp;
                姓名：<input type="text" name="name" value="${(user.name)!}" class="input-number" />&nbsp;
                电话：<input type="text" name="phone" value="${(user.phone)!}" class="input-contact" />&nbsp;
                邮箱：<input type="text" name="email" value="${(user.email)!}" class="input-contact" /><br/>
                扣扣：<input type="text" name="qq" value="${(user.qq)!}" class="input-contact" />&nbsp;
                身份：<select name="identity" class="input-select">
                        <option value="A" <#if (user.identity)?? ><#if user.identity=="A"> selected="selected" </#if></#if>>所有用户</option>
                        <option value="T" <#if (user.identity)?? ><#if user.identity=="T"> selected="selected" </#if></#if>>教师</option>
                        <option value="S" <#if (user.identity)?? ><#if user.identity=="S"> selected="selected" </#if></#if>>学生</option>
                </select>&nbsp;
                状态：<select name="alive" class="input-select">
                        <option value="Y" <#if (user.alive)?? ><#if user.alive=="Y"> selected="selected" </#if></#if>>活动</option>
                        <option value="N" <#if (user.alive)?? ><#if user.alive=="N"> selected="selected" </#if></#if>>冻结</option>
                </select>&nbsp;&nbsp;
                <input type="submit" value="搜索" class="input-submit" />
            </form>
        </div>
        <table class="ta-table-commons" id="adminUser">
            <tr>
                <th>编号</th>
                <th>姓名</th>
                <th>身份</th>
                <th>电话</th>
                <th>邮箱</th>
                <th>QQ</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            
            <#if (pageModel.records)??>
                <#list pageModel.records as record>
                    <tr>
                        <td>${(record.identityCode)!}</td>
                        <td>${(record.name)!}</td>
                        <td identity="${record.identity}">${(record.identity == "T")?string("教师","学生")}</td>
                        <td>${(record.phone)!}</td>
                        <td>${(record.email)!}</td>
                        <td>${(record.qq)!}</td>
                        <td>
                            <#if (record.alive) == "Y">
                                <a href="${record.id}" status="N">冻结</a>
                            <#else>
                                <a href="${record.id}" status="Y">激活</a>
                            </#if>
                        </td>
                        <td><a href="${record.id}" class="edit">编辑</a> | <a href="${record.id}" class="reset">重置密码</a> | <a href="${record.id}" class="userDel">删除</a></td>
                    </tr>
                </#list>
            </#if>
        </table>
        <@tag.paging requestUrl="${ctx}/admin/user/search" pageNo=pageModel.pageNo pageSize=pageModel.pageSize totalRecords=pageModel.totalRecords/>
    </div>
</div>

<div class="changeadmin-pannel">
    <form action="#" class="userchangeadmin" id="userchangeadmin">
        <label>
            <span></span>
            <span><img class="Imgclose" src='<@url href="/site-static/images/close.png"/>' alt=""></span>
        </label>
        <table>
            <tr>
                <td>编号</td>
                <td><input type="text" name="identityCode"></td>
            </tr>
            <tr>
                <td>姓名</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>身份</td>
                <td>
                    <select name="identity">
                        <option value="T">教师</option>
                        <option value="S">学生</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input  type="text" name="phone"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input  type="text" name="email"/></td>
            </tr>
            <tr>
                <td>QQ</td>
                <td><input type="text" name="qq"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="确定"></td>
            </tr>
        </table>
    </form>
</div>
</@body>
