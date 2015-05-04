<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/votesystem/LoginServlet" method="post">
		<table align="center" border="1px">
			<caption>用户登录</caption>
			<tr>
				<th>用户名</th>
				<td><input type="text" name="username"/></td>
			</tr>
			<tr>
				<th></th>
				<td><input type="password" name="password"></td>
			</tr>
			${!empty requestScope.message ? requestScope.message : "" }
			<tr>
				<td colspan="2"><input type="submit" value="登录" /></td>
			</tr>
		</table>
	</form>
</body>
</html>