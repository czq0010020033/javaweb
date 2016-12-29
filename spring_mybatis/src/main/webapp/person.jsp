<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'person.jsp' starting page</title>

</head>

<body>
	<c:choose>
		<c:when test="${list==null}">
			<tr>
				<td>list为空</td>
			</tr>
		</c:when>
		<c:otherwise>
			<table border="1">
				<tr>
					<th>id</th>
					<th>accountId</th>
					<th>realName</th>
					<th>age</th>
					<th>sex</th>
					<th>cardId</th>
					<th>address</th>
					<th>telephone</th>
				</tr>
				<c:forEach items="${list}" var="item" varStatus="status">
					<tr>
						<td>${item.id}</td>
						<td>${item.accountId}</td>
						<td>${item.realName}</td>
						<td>${item.age}</td>
						<td>${item.sex}</td>
						<td>${item.cardId}</td>
						<td>${item.address}</td>
						<td>${item.telephone}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>

	<c:if test="${person != null}">
		<table border="1">
			<tr>
				<th>id</th>
				<th>accountId</th>
				<th>realName</th>
				<th>age</th>
				<th>sex</th>
				<th>cardId</th>
				<th>address</th>
				<th>telephone</th>
			</tr>
			<tr>
				<td>${person.id}</td>
				<td>${person.accountId}</td>
				<td>${person.realName}</td>
				<td>${person.age}</td>
				<td>${person.sex}</td>
				<td>${person.cardId}</td>
				<td>${person.address}</td>
				<td>${person.telephone}</td>
			</tr>
		</table>
	</c:if>
</body>
</html>
