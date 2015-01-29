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
<title><fmt:message key="createorder.addorder" /></title>
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
			<a class="brand"><fmt:message key="createorder.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="<c:url value="../../index.jsp"/>">
                    <i class="icon-home"></i> <fmt:message key="login.home" /></a></li>
			</ul>
			<form class="pull-right" action="controller" method="post">
				<input type="hidden" name="command" value="logout" />
				<button class="btn btn-danger btn-sm" type="submit">
					<fmt:message key="customer.logout" />
				</button>
			</form>
		</nav>
	</div>
	<!-- Control btn -->
	<div class="btn-group pull-right">
		<form class="navbar-form pull-right" action="controller" method="post">
			<input type="hidden" name="command" value="page"><input
				type="hidden" name="page" value="customerpage">
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
	<form action="controller" method="post">
		<div class="container pull-center" id="container-wraper">
			<table class="table table-striped">
				<thead>
					<tr>
						<th><fmt:message key="createorder.dateinfo" /></th>
					</tr>
				</thead>
				<tr class="info">
					<td><span class="badge badge-inverse"><fmt:message key="createorder.firstdate" /></span><br> <label>
                        <input type="date" name="firstDate">
                    </label></td>
					<td><span class="badge badge-inverse"><fmt:message key="createorder.lastdate" /></span><br> <label>
                        <input type="date" name="lastDate">
                    </label></td>
				</tr>
			</table>
			<span class="badge badge-inverse"><fmt:message key="createorder.addorderinfo" /></span><br> <label>
            <input type="text" name="description" size="1000">
        </label>
            <hr />
			<table class="table table-striped">
				<thead>
					<tr>
						<th><fmt:message key="customerorders.free-car-info" /></th>
					</tr>
				</thead>
				<tr class="info">
					<td><span class="badge badge-inverse"> <fmt:message
								key="createorder.carnumber" /></span></td>
					<td><span class="badge badge-inverse"> <fmt:message
								key="createorder.cost" /></span></td>
					<td><span class="badge badge-inverse"> <fmt:message
								key="createorder.cartype" /></span></td>
					<td><span class="badge badge-inverse"> <fmt:message
								key="createorder.descriptioncar" /></span></td>
					<td><span class="badge badge-inverse"><fmt:message
								key="createorder.check" /></span></td>
				</tr>
				<c:forEach var="car" items="${freeCar}">
					<tr>
						<td><c:out value="${car.carNumber}" /></td>
						<td><c:out value="${car.carType.carPrice}" /></td>
						<td><c:out value="${car.carType.carType }" /></td>
						<td><c:out value="${car.carType.carDescription }" /></td>
						<td><label>
                            <input type="radio" name="idVehicle" value="${car.id}"/>
                        </label></td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" name="command" value="CREATEORDER" />
			<button type="submit" class="btn btn-success btn-lg">
				<fmt:message key="createorder.createorder" />
			</button>
		</div>
	</form>
	<footer class="white navbar-fixed-bottom">
		<jsp:include page="../common/footer.jsp" flush="true" />
	</footer>
	<script type="text/javascript"
		src="<c:url value="../../js/jquery-1.11.0.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="../../js/bootstrap.js"/>"></script>
</body>
</html>