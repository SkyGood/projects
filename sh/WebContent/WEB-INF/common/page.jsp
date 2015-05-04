<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link  rel="stylesheet" href="${initParam.resUrl}/common/css/page.css"/>
<div class="page-outer">
	<div class="page-normal">
		<c:choose>
			<c:when test="${pageModel.totalPages<=10}">
				<c:forEach var="no" begin="1" step="1" end="${pageModel.totalPages}">	
					<a href="/sh/${requestScope.pageUrl}/${no}/${pageModel.pageSize}" ${(pageModel.pageNo==no) ? ('class="page-current"') : "" }>${no}</a>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:if test="${pageModel.pageNo>9}">
					<a href="/sh/${requestScope.pageUrl}/${pageModel.pageNo-1}/${pageModel.pageSize}"><span class=".page-prev">&lt;</span></a>
				</c:if>
				<c:if test="${(pageModel.pageNo)<10}">
					<c:forEach var="no" begin="1" step="1" end="10">
							<a href="/sh/${requestScope.pageUrl}/${no}/${pageModel.pageSize}" ${(pageModel.pageNo==no) ? ('class="page-current"') : "" }>${no}</a>
					</c:forEach>
				</c:if>
				<c:if test="${(pageModel.pageNo>=10) && (pageModel.pageNo<=(pageModel.totalPages-9))}">
					<c:forEach var="no" begin="${pageModel.pageNo-5}" step="1" end="${pageModel.pageNo+4}">
						<a href="/sh/${requestScope.pageUrl}/${no}/${pageModel.pageSize}" ${(pageModel.pageNo==no) ? ('class="page-current"') : "" } >${no}</a>
					</c:forEach>
				</c:if>
				<c:if test="${(pageModel.pageNo)>(pageModel.totalPages-9)}">
					<c:forEach var="no" begin="${pageModel.totalPages-9}" step="1" end="${pageModel.totalPages}">
						<a href="/sh/${requestScope.pageUrl}/${no}/${pageModel.pageSize}" ${(pageModel.pageNo==no) ? ('class="page-current"') : "" } >${no}</a>
					</c:forEach>
				</c:if>
				<c:if test="${pageModel.totalPages-8>pageModel.pageNo}">
					<a href="/sh/${requestScope.pageUrl}/${pageModel.pageNo+1}/${pageModel.pageSize}"><span class=".page-next">&gt;</span></a>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>
</div>