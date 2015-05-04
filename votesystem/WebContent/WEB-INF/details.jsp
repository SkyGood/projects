<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.details-return {
		text-decoration: none;
	}
</style>
</head>
<body>
	<table border="1px" align="center">
		<caption>候选人信息</caption>
		<tr>
			<th>人名</th>
			<td>${info.honerName}</td>
		</tr>
		<tr>
			<th>年龄</th>
			<td>${info.age}</td>
		</tr>
		<tr>
			<th>描述</th>
			<td>${info.description}</td>
		</tr>
	</table>
	<span><a href="/votesystem/AllServlet" class="details-return">返回</a></span>
</body>
</html>