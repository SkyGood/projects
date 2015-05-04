<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${initParam.resUrl}/system/css/noticelist.css">
	<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
	<title>admin-大学生助手</title>
	<script type="text/javascript">
		var delUrl = "http://localhost:8080/sh/admin/notice/delete/";
		$(document).ready(function(){
			var DelA = $("#noticeslist").find("a[class=del]");
			DelA.bind("click",function(event){
				event.preventDefault();
				var thisA = $(this);
				$.get(delUrl+$(this).attr("href"),function(flag){
					if(flag == true){
						thisA.parents("li").remove();
					}else{
						alert(flag);
					}
				});
			});
		});
	</script>
</head>
<body>
	
	<c:import url="/WEB-INF/common/adminheader.jsp" />
	
	<div class="sh-admin-container">
		<div class="admin-title">所有公告<a href="add"> 添加公告</a></div>
		<div class="admin-notices">
			<ul id="noticeslist">
			<c:forEach var="notice" items="${requestScope.noticesList}">
				<li class="notice">
					<p>	${notice.topic}
						<a href="${notice.noticeId}" class="del">删除</a>
					</p>
					<p>${notice.content}</p>
					<span>${notice.createDate}</span>
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>