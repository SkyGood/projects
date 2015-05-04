<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.welcome {
		text-align:center;
		width:100px;
		height:30px;
	}
	.list {
		margin: 30px auto;
		width:900px;
		height:700px;
	}
	.border {
		width: 700px;
		height:50px;
	}
	.login {
		float: left;
		margin-top:10px;
		margin-left:90px;
	}
	.votelist{
		float: right;
		margin-top:10px;
		margin-right:90px;
	}
	a {
		text-decoration: none;
	}
</style>
</head>
<body>
	<div class="welcome">欢迎${!empty sessionScope.username ? sessionScope.username : "游客"}光临</div>
	<div class="list">
		<table align="center" border="1px" class="border">
			<caption><h1>候选人基本信息</h1></caption>
			<tr>
				<th>人名</th>
				<th>票数</th>
				<th>操作</th>
			</tr>
			<c:forEach var="row" items="${requestScope.list}">
				<tr>
					<td><a href="${pageContext.request.contextPath}/Details?id=${row.id}">${row.honerName}</a></td>
					<td>${row.ticketNums}</td>
					<td>
						<c:if test="${!empty sessionScope.username}">
							<a href="${pageContext.request.contextPath}/VoteServlet?id=${row.id}">投票</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<span class="login">
			<c:choose>
				<c:when test="${empty sessionScope.username}"><a href="/votesystem/login">登录</a></c:when>
				<c:otherwise><a href="${pageContext.request.contextPath}/logout">注销</a></c:otherwise>
			</c:choose>
		</span>
		<span class="votelist">
			<a href="${pageContext.request.contextPath}/VoteListServlet">查看投票人详情</a>
		</span>
	</div>
</body>
</html>