<#include "/uccyou.ftl">
<html>
<head>
<meta charset="UTF-8">
<title>教学助手</title>
<link rel="stylesheet" type="text/css" href='<@url href='/site-static/style/login' />.css'>
<script type="text/javascript" src='<@url href="/site-static/js/jquery.js"/>'></script>
<script type="text/javascript" src='<@url href="/site-static/js/login.js"/>'></script>
</head>
<body style="background:url(<@url href="/site-static/images/bg_a.jpg"/>)">
    <div class="head">
        <div class="head_title">
            <img src='<@url href="/site-static/images/title_1.png"/>'>
            <p>Teaching Assistant</p>
        </div>
    </div>
    
    <div class="content"><!--中间部分  表单内容-->
        <div class="content_middle">
            <form action='#' method="post" id="loginForm">
                <table class="ta-login-table">
                    <tr>
                        <td>用户名</td>
                        <td><input type="text" name="identityCode"/></td>
                        <td><span>*请再次确认用户名</span></td>
                    </tr>
                    <tr>
                        <td>密&nbsp;&nbsp;&nbsp;码</td>
                        <td><input type="password" name="passWord" /></td>
                        <td><span>*请再次确认密码</span></td>
                    </tr>
                    <tr>
                        <td>身&nbsp;&nbsp;&nbsp;份</td>
                        <td colspan="2">
                            <select name="identity" class="identity">
                                <option value="S">学生</option>
                                <option value="T">教师</option>
                                <option value="A">管理员</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <input type="submit" value="登录" id="submit"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    
    <div class="footer">
        <p>Copyright ©2008 [使用者网站]  Powered By [网站程序名称] Version 1.0.0</p>
    </div>

    <script type="text/javascript">
        var bgimg = document.getElementsByTagName("body");
        function changBg(){
            bgimg.scripttyle.width = Math.min(document.documentElement.clientWidth,document.body.clientWidth) + "px";
            bgimg.style.height = Math.min(document.documentElement.clientHeight,document.body.clientHeight) + "px";
            bgimg.style.left = (document.body.scrollLeft || document.documentElement.scrollLeft || window.pageXOffset) + "px";
            bgimg.style.top = (document.body.scrollTop || document.documentElement.scrollTop || window.pageYOffset) + "px";
        }
    </script>
</body>
</html>