<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="by.bsu.ino.carrent.properties.languages.Resources"/>
<fmt:setBundle basename="by.bsu.ino.carrent.properties.resources.config" var="path" />

<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="customer.title" /></title>
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="../../css/user.css"/>" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="../../css/bootstrap.css"/>" />
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<nav class="navbar-inner">
			<a class="brand"><fmt:message key="customer.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="<c:url value="../../index.jsp"/>"><i class="icon-home"></i> <fmt:message key="login.home" /></a></li>
			</ul>
			<form class="pull-right" action="controller" method="post">
				<input type="hidden" name="command" value="logout" />
				<button class="btn btn-danger btn-sm" type="submit">
					<fmt:message key="customer.logout" />
				</button>
			</form>
		</nav>
	</div>
	<div class="btn-group pull-right">
		<form class="navbar-form pull-right" action="controller" method="post">
			<input type="hidden" name="command" value="SHOWFREECAR" />
			<button class="btn btn-sm btn-success" type="submit">
				<i class="glyphicon glyphicon-star"></i>
				<fmt:message key="customer.createorder" />
			</button>
		</form>
		<form class="navbar-form pull-right" action="controller" method="post">
			<input type="hidden" name="command" value="SHOWMYORDER" />
			<button class="btn  btn-sm btn-info" type="submit">
				<fmt:message key="customer.showorder" />
			</button>
		</form>
		<form class="navbar-form pull-right" action="controller" method="post">
			<input type="hidden" name="command" value="SHOWMYBILL" />
			<button class="btn btn-sm btn-warning" type="submit">
				<fmt:message key="customer.showbill" />
			</button>
		</form>
	</div>
	<c:if test="${not empty errorMessage }">
		<div class="alert alert-danger">${errorMessage}</div>
	</c:if>
	<c:if test="${not empty actionMessage }">
		<div class="alert alert-success">${actionMessage }</div>
	</c:if>
	<div class="container pull-left" id="container-wraper">
		<table class="table table-striped">
			<thead>
				<tr>
					<th><fmt:message key="customer.info" /></th>
				</tr>
			</thead>
			<tr class="info">
				<td><span class="badge"><fmt:message key="customer.firstname" /></span></td>
				<td>${customer.firstName}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="customer.lastname" /></span></td>
                <td>${customer.lastName}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="customer.login" /></span></td>
                <td>${customer.login}</td>
			</tr>
            <tr class="info">
                <td><span class="badge"><fmt:message key="customer.dob"/></span> </td>
                <td>${customer.dob}</td>
            </tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="customer.email" /></span></td>
                <td>${customer.email}</td>
			</tr>
			<tr class="info">
				<td><span class="badge"><fmt:message key="customer.access" /></span></td>
                <td>${customer.level}</td>
			</tr>
            <tr class="info">
                <td><span class="badge"><fmt:message key="customer.city"/></span> </td>
                <td>${customer.addressCity}</td>
            </tr>
            <tr class="info">
                <td><span class="badge"><fmt:message key="customer.country"/></span> </td>
                <td>${customer.addressCountry}</td>
            </tr>
            <tr class="info">
                <td><span class="badge"><fmt:message key="customer.street"/></span> </td>
                <td>${customer.addressStreet}</td>
            </tr>
            <tr class="info">
                <td><span class="badge"><fmt:message key="customer.mobilephone"/></span> </td>
                <td>${customer.mobilephone}</td>
            </tr>
		</table>
	</div>
	<div class="navbar-form pull-right wraper-date calendar">
		<ht:today format="dd MMM yyyy" />
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

