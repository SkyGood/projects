<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${initParam.resUrl}/system/css/noticelist.css">
	<link  rel="stylesheet" href="${initParam.resUrl}/common/css/public.css"/>
	<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<title>贴子</title>
<script type="text/javascript">
var delUrl = "http://localhost:8080/sh/system/post/delete/";
$(document).ready(function(){
	var DelA = $("#postslist").find("a[class=del]");
	DelA.bind("click",function(event){
		event.preventDefault();
		var thisA = $(this);
		$.get(delUrl+$(this).attr("href"),function(flag){
			if(flag == true){
				thisA.parents("tr").remove();
			}else{
				alert(flag);
			}
		});
	});
});
</script>
</head>
<body>
	<c:import url="/WEB-INF/common/systemheader.jsp" />
	<div class="sh-system-search">
		<form action="http://localhost:8080/sh/system/post/all" method="post">
			贴子内容&nbsp;&nbsp;<input type="text" name="content" value=""/>&nbsp;&nbsp;
			创建者&nbsp;&nbsp;<input  type="text" name="createBy" value=""/>&nbsp;&nbsp;
			<input type="submit" value="搜索"/>
		</form>
		<a href="/sh/system/center">中心<img src="${initParam.resUrl}/common/img/back.png" alt="返回"></a>
	</div>	
	<div class="container">
		<table class="table" id="postslist">
			<tr>
				<th>贴子头</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>操作</th>
				<th>操作</th>
			</tr>
			<c:forEach var="post" items="${requestScope.pageModel.list}">
			<tr>
				<td>${post.content}</td>
				<td>${post.createBy}</td>
				<td>${post.createDate}</td>
				<td><a href="/sh/system/post/comment/${post.postId}">管理</a></td>
				<td><a href="${post.postId}" class="del">删除</a></td>
			</tr>		
			</c:forEach>
		</table>
	</div>
	<c:import url="/WEB-INF/common/page.jsp" />
</body>
</html>