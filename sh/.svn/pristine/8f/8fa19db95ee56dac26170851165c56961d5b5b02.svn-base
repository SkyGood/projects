<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<title>评论</title>
<script type="text/javascript">
var delUrl = "http://localhost:8080/sh/system/post/comment/delete/";
$(document).ready(function(){
	var DelA = $("#commentslist").find("a[class=del]");
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
<style type="text/css">
.comments{
	width: 1000px;
	left: 400px;
	top:100px;
	margin:0px auto;
	background: #fafafa;
	margin-top: 32px;
}
.comments ul{
	width: 100%;
	margin: 0px auto;
	margin-top: 16px;
	background: #efefef;
	padding-top: 2px;
}
.comments ul li{
	height: 200px;
	width: 100%;
	margin-top:26px;
	background: #fff;
}
.comment p:nth-child(1){
	float: left;
	width: 100%;
	height: 30px;
	font-size: 16px;
	line-height: 30px;
	font-weight: bolder;
	/*border-bottom: 1px solid green;*/
}
.comment p:nth-child(2){
	float: left;
	height: 142px;
	width: 100%;
	padding-top: 6px;
	text-indent: 33px;
	overflow-y: auto;
}
.comment p a{
	float: right;
	margin-right: 8px;
	font-size: 12px;
	letter-spacing: 2px;
	font-weight: normal;
	color: #0097a7;
}
.comment span{
	float: right;
	height: 20px;
	font-size: 14px;
	margin:2px 8px 0px 0px;
}
#back{
	position: absolute;
	height: 36px;
	width: 36px;
	right: 40px;
	top: 130px;
}
</style>
</head>
<body>
	<a href="/sh/system/center" id="back"><img src="${initParam.resUrl}/system/img/backurl.png" alt=""></a>
	<c:import url="/WEB-INF/common/systemheader.jsp" />
	<div class="comments">
		<ul id="commentslist">
			<c:forEach var="comment" items="${requestScope.comments}">
				<li class="comment">
					<p>${comment.commentBy}&nbsp;进行评论<a href="${comment.commentId}" class="del">删除</a></p>
					<p>${comment.content}</p>
					<span>评论时间&nbsp;${comment.createDate}</span>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>