<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<table border="1px" align="center">
		<tr>
			<th>IP</th>
			<th>最后一次投票时间</th>
		</tr>
		<c:forEach var="vote" items="${requestScope.list}">
			<tr>
				<td>${vote.ip}</td>
				<td><fmt:formatDate value="${vote.lastTime}" type="both" dateStyle="full" timeStyle="default" /></td>
			</tr>
		</c:forEach>
	</table>
		<span style="float:right;"><a href="/votesystem/AllServlet" class="details-return">返回</a></span>
	</center>
</body>
</html>