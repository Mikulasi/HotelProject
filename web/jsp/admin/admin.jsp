
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="by.bsu.ino.carrent.properties.languages.Resources" />

<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="admin.title" /></title>
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="../../css/user.css"/>" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="../../css/bootstrap.css"/>" />
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<nav class="navbar-inner">
			<a class="brand"><fmt:message key="admin.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="<c:url value="../../index.jsp"/>"><i class="icon-home"></i> <fmt:message key="login.home" /></a></li>
			</ul>
			<form class="pull-right" action="controller" method="post">
				<input type="hidden" name="command" value="logout" />
				<button class="btn btn-danger btn-sm" type="submit">
					<fmt:message key="admin.logout" />
				</button>
			</form>
		</nav>
	</div>
	<div class="btn-group pull-right">
		<form class="navbar-form pull-right" action="controller" method="post">
			<input type="hidden" name="command" value="caradministration" />
			<button class="btn btn-sm btn-default" type="submit">
				<i class="glyphicon glyphicon-star"></i>
				<fmt:message key="admin.caradministration" />
			</button>
		</form>
		<form class="navbar-form pull-right" action="controller" method="post">
			<input type="hidden" name="command" value="orderadministration" />
			<button class="btn  btn-sm btn-info " type="submit">
				<fmt:message key="admin.orderadministration" />
			</button>
		</form>
		<form class="navbar-form pull-right" action="controller" method="post">
			<input type="hidden" name="command" value="useradministration" />
			<button class="btn btn-sm btn-warning " type="submit">
				<fmt:message key="admin.useradministration" />
			</button>
		</form>
	</div>
	<div class="container pull-left" id="container-wraper">
		<table class="table table-striped">
			<thead>
				<tr>
					<th><fmt:message key="admin.info" /></th>
				</tr>
			</thead>
			<tr class="info">
				<td><span class="badge"><fmt:message key="admin.firstname" /></span></td>
                <td>${customer.firstName}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="admin.lastname" /></span></td>
				<td>${customer.lastName}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="admin.login" /></span></td>
				<td>${customer.login}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="admin.email" /></span></td>
				<td>${customer.email}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="admin.access" /></span></td>
                <td>${customer.level}</td>
			</tr>
		</table>
	</div>
	<div class="navbar-form pull-right wraper-date">
		<ht:today format="dd MMMM YYYY" />
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

