<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生列表</title>
</head>
<body>
<div class="container">
	<table class="table">
		<tr>
			<th>学号</th>
			<th>真实姓名</th>
			<th>性别</th>
			<th>班级</th>
			<th>用户名</th>
			<th>管理</th>
		</tr>
		<c:forEach var= "student" items = "${requestScope.pageModel.list}" >
		<tr>
			<td>${student.code}</td>
			<td>${student.realName}</td>
			<td>${student.gender}</td>
			<td>${student.className}</td>
			<td>${!empty student.userName ? student.userName : "未注册"}</td>
			<td><a href="#">删除</a></td>
		</tr>
		</c:forEach>
	</table>
</div>
	<c:import url="/WEB-INF/common/page.jsp" />
</body>
</html>