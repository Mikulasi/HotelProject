<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>

<fmt:setBundle basename="by.bsu.ino.carrent.properties.resources.config" var="path" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="by.bsu.ino.carrent.properties.languages.Resources" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="customerorders.title" /></title>
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
			<a class="brand"><fmt:message key="customerorders.title" /></a>
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
			<input type="hidden" name="command" value="page">
            <input type="hidden" name="page" value="customerpage">
			<button class="btn btn-sm btn-success" type="submit" name="login">
				<fmt:message key="customerbill.return" />
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
					<th><fmt:message key="customerorders.orders" /></th>
				</tr>
			</thead>
			<tr class="info">
				<td><span class="badge badge-inverse"><fmt:message
							key="customerorders.carnumber" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="customerorders.categorycar" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="customerorders.descriptioncar" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="customerorders.orderdate" /></span></td>
                <td><span class="badge badge-inverse"><fmt:message
                            key="customerorders.daynumber" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="customerorders.totalcost" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message
							key="customerorders.orderstate" /></span></td>
			</tr>
			<c:forEach var="order" items="${customerOrder}">
				<tr>
					<ht:diffday lastDate="${order.lastDate}" firstDate="${order.firstDate}" />
					<td><c:out value="${order.car.carNumber}" /></td>
					<td><c:out value="${order.car.carType.carType}" /></td>
					<td><c:out value="${order.car.carType.carDescription }" /></td>
					<td><c:out value="${order.dateBookingMade}" /></td>
					<td><c:out value="${diffDay}" /></td>
					<td><ht:totalcost differenceDay="${diffDay}" carPrice="${order.car.carType.carPrice}" /></td>
					<td><c:out value="${order.state}" /></td>
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