<#include "/uccyou.ftl">
<@body title="个人中心">
<div class="ta-content-outer" id="content">
    <div class="ta-right">
        <h1>个人中心</h1>
        <div class="ta-usercenter">
            <form action='<@url href="/user/change"/>' method="post" id="userInfo">
                <input type="hidden" id="userId" name="userId" value="${user.userId!}" />
                <dl>
                    <dd>账号：${user.identityCode!}</dd>
                    <dd id="changePwd">密码：<a href="#">点击修改密码</a></dd>
                    <dd>身份：${(user.identity == "T")?string("教师","学生")}</dd>
                    <dd>姓名：${user.name!}</dd>
                    <dd>电话：<input type="text" name="phone" value="${user.phone!}" class="input-contact"/><span class="userInfo-error"></span></dd>
                    <dd>邮箱：<input type="text" name="email" value="${user.email!}" class="input-contact"/><span class="userInfo-error"></span></dd>
                    <dd>扣扣：<input type="text" name="qq" value="${user.qq!}" class="input-contact"/><span class="userInfo-error"></span></dd>
                    <dd></dd>
                    <dd><input type="submit" value="保存" class="input-submit"/></dd>
                </dl>
            </form>
        </div>
    </div>
</div>

<div class="changepwd-panel">
    <form action="#" class="userchangepwd">
        <label><span>修改密码</span><span><img class="Imgclose" src='<@url href="/site-static/images/close.png"/>' alt=""></span></label>
        <table>
            <tr>
                <td>旧密码</td>
                <td><input type="password" name="oldPwd"></td>
            </tr>
            <tr>
                <td>新密码</td>
                <td><input type="password" name="newPwd" maxlength="18"></td>
                <td><span></span></td>
            </tr>
            <tr>
                <td>重复密码</td>
                <td><input type="password" name="reNewPwd" maxlength="18"></td>
                <td><span></span></td>
            </tr>
            <tr>
                <td colspan="2"><input id="submitChangePwd" type="button" value="提交"></td>
            </tr>
        </table>
    </form>
</div>
</@body>