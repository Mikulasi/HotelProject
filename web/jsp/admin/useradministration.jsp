<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<fmt:setBundle basename="by.bsu.ino.carrent.properties.resources.config" var="path" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="by.bsu.ino.carrent.properties.languages.Resources" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="customeradministration.title" /></title>
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="../../css/user.css"/>" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="../../css/bootstrap.css"/>" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="../../css/tabs.css"/>" />
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<nav class="navbar-inner">
			<a class="brand"><fmt:message key="customeradministration.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="<c:url value="../../index.jsp"/>"><i class="icon-home"></i> <fmt:message key="login.home" /></a></li>
			</ul>
			<form class="pull-right" action="controller" method="post">
				<input type="hidden" name="command" value="logout" />
				<button class="btn btn-danger btn-sm" type="submit">
					<fmt:message key="customeradministration.logout" />
				</button>
			</form>
		</nav>
	</div>
	<div class="btn-group pull-right">
		<form class="navbar-form pull-right" action="controller" method="post">
			<input type="hidden" name="command" value="page"><input
				type="hidden" name="page" value="adminpage">
			<button class="btn btn-sm btn-success" type="submit" name="login">
				<fmt:message key="customeradministration.return" />
			</button>
		</form>
	</div>
	<c:if test="${not empty errorMessage }">
		<div class="alert alert-danger">${errorMessage}</div>
	</c:if>
	<c:if test="${not empty actionMessage }">
		<div class="alert alert-success">${actionMessage }</div>
	</c:if>
	<div class="container pull-center" id="container-wraper">
		<table class="table table-striped">
			<thead>
				<tr>
					<th><fmt:message key="customeradministration.page" /></th>
				</tr>
			</thead>
			<tr class="info">
				<td><span class="badge badge-inverse"><fmt:message key="customeradministration.id" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="customeradministration.firstname" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="customeradministration.lastname" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="customeradministration.login" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="customeradministration.email" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="customeradministration.accesslevel" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="customeradministration.action" /></span></td>
			</tr>
			<c:forEach var="customer" items="${customerList}">
				<tr>
					<td><c:out value="${customer.id}" /></td>
					<td><c:out value="${customer.firstName}" /></td>
					<td><c:out value="${customer.lastName}" /></td>
					<td><c:out value="${customer.login}" /></td>
					<td><c:out value="${customer.email}" /></td>
					<td><c:out value="${customer.level}" /></td>
					<td><form action="controller" method="post">
                        <label>
                            <select name="level">
                                <option value="empty">
                                    <fmt:message key="customeradministration.empty"/>
                                </option>
                                <option value="ADMIN">ADMIN</option>
                                <option value="CUSTOMER">CUSTOMER</option>
                            </select>
                            <input type="hidden" name="command" value="changeLevel" />
                            <input type="hidden" name="idCustomer" value="${customer.id }" />
                            <button type="submit" class="btn btn-warning">
                                <fmt:message key="customeradministration.changeaccesslevel" />
                            </button>
                        </label>
						</form></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<footer class="white navbar-fixed-bottom">
		<jsp:include page="../common/footer.jsp" flush="true" />
	</footer>
	<script type="text/javascript"
		src="<c:url value="../../js/jquery-1.11.0.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="../../js/bootstrap.js"/>"></script>
</body>
</html>

