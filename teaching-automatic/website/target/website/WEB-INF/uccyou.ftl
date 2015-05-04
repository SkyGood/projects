<#assign ctx=basePath.contextPath />

<#assign tag=JspTaglibs["/WEB-INF/uccyou.tld"] />

<#macro url href>${ctx}${href}</#macro>

<#macro selected p t>
    <#if p == t>selected="selected"</#if>
</#macro>

<#macro css res>
<#if res??>
<#list res as r>
<link rel="stylesheet" type="text/css" href='<@url href='/site-static/style/${r}' />.css'>
</#list></#if></#macro>

<#macro js res>
<#if res??>
<#list res as r>
<script type="text/javascript" src='<@url href='/site-static/js/${r}'/>.js'></script>
</#list></#if></#macro>

<#assign ctx=basePath.contextPath />

<#assign tag=JspTaglibs["/WEB-INF/uccyou.tld"] />

<#macro url href>${ctx}${href}</#macro>

<#macro selected p t>
    <#if p == t>selected="selected"</#if>
</#macro>

<#macro css res>
<#if res??>
<#list res as r>
<link rel="stylesheet" type="text/css" href='<@url href='/site-static/style/${r}' />.css'>
</#list></#if></#macro>

<#macro js res>
<#if res??>
<#list res as r>
<script type="text/javascript" src='<@url href='/site-static/js/${r}'/>.js'></script>
</#list></#if></#macro>

<#macro body title>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title} - 教学助手</title>
<@css res=["tag","index","public"] />
<@js res=["jquery","public","admin"] />
</head>
<body>
    <div class="ta-header">
        <p class="ta-header-title">教学助手管理系统</p>
        <p><a href="/website/" class="ta-header-logout">注销</a></p>
    </div>
    <div class="ta-container">
        <div class="ta-navbar" id="navbar">
            <div class="ta-navbar-hidden">
                <p id="hidden"><<</p>
            </div>
            <ul class="ta-navbar-ul">
                <li class="ta-navbar-li"><a href='<@url href="/system/homepage"/>' class="ta-navbar-li-a">欢迎</a></li>
                <#if identity != "A">
                    <li class="ta-navbar-li"><a href='<@url href="/user/center"/>' class="ta-navbar-li-a">个人中心</a></li>
                </#if>
                <#if identity == "T">
                    <li class="ta-navbar-li"><a href='<@url href="/teacher/clazz"/>' class="ta-navbar-li-a">班级管理</a></li>
                </#if>
                <#if identity == "S">
                    <li class="ta-navbar-li"><a href='<@url href="/student/subject/search"/>' class="ta-navbar-li-a">我的班级</a></li>
                </#if>
                <#if identity == "A">
                    <li class="ta-navbar-li"><a href='<@url href="/admin/admin"/>' class="ta-navbar-li-a">管理员</a></li>
                    <li class="ta-navbar-li"><a href='<@url href="/admin/user/search"/>' class="ta-navbar-li-a">用户管理</a></li>
                </#if>
                
                <li class="ta-navbar-li"><a href="/website/" class="ta-navbar-li-a">注销系统</a></li>
            </ul>
        </div>
        <#nested>
    </div>
    <div class="ta-footer">
         <p class="ta-footer-copyright">copyright<span class="ta-copyright">©</span>2014-2015</p>
    </div>
    <p id="topTip"></p>
</body>
</html>
</#macro>