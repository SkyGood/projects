<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加班级</title>
<style type="text/css">
.sh-import{
	width: 800px;
	left: 400px;
	top:100px;
	margin:0px auto;
	background: #fafafa;
	margin-top: 32px;
}
.sh-import form dl{
	margin-top: 16px;
	text-indent: 8px;
	font-weight: bolder;
	font-family: 微软雅黑;
	font-size: 15px;
}
.sh-import form dl:nth-child(1){
	height: 80px;
}
.sh-import form dl:nth-child(2){
	height: 40px;
}
.sh-import form dl:nth-child(1) dd{
	height: 40px;
	line-height: 40px;
}
.sh-import form dd input[type=submit] {
	cursor:pointer;
	width: 80px;
	height: 30px;
	border: none;
	color: white;
	background-color: #5cb85c;
	background-image: -webkit-gradient(linear,left top,left bottom,from(#5cb85c),to(#3d8b5f));
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
	<div class="sh-import">
		<form action="/sh/system/import" method="POST" enctype="Multipart/form-data">
				<dl>
					<dd>班级excel表</dd>
					<dd><input type="file" name="file" /> </dd>
				</dl>
				<dl>
					<dd><input value="提交" type="submit" /></dd>
				</dl>
				<dl>
					<dd>${!empty info ? info : ""}</dd>
				</dl>
				<dl>
					表数据格式如下:
					<ul>
						<li>test1</li>
						<li>test2</li>
						<li>test3</li>
						<li>test4</li>
						<li>test5</li>
					</ul>
				</dl>
		</form>
	</div>
</body>
</html>