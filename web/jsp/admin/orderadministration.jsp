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
<title><fmt:message key="orderadministration.title" /></title>
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
			<a class="brand"><fmt:message key="orderadministration.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="<c:url value="../../index.jsp"/>"><i class="icon-home"></i> <fmt:message key="login.home" /></a></li>
			</ul>
			<form class="pull-right" action="controller" method="post">
				<input type="hidden" name="command" value="logout" />
				<button class="btn btn-danger btn-sm" type="submit">
					<fmt:message key="orderadministration.logout" />
				</button>
			</form>
		</nav>
	</div>
	<div class="btn-group pull-right">
		<form class="navbar-form pull-right" action="controller" method="post">
			<input type="hidden" name="command" value="page"><input
				type="hidden" name="page" value="adminpage">
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
					<th><fmt:message key="orderadministration.orders" /></th>
				</tr>
			</thead>
			<tr class="info">
				<td><span class="badge badge-inverse"><fmt:message key="orderadministration.iduser" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="orderadministration.lastname" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="orderadministration.numbercar" /></span></td>
                <td><span class="badge badge-inverse"><fmt:message key="orderadministration.description" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="orderadministration.cartype" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="orderadministration.orderdate" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="orderadministration.firstdate" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="orderadministration.lastdate" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="orderadministration.totalcost" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="orderadministration.commentsorder" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="orderadministration.stateorder" /></span></td>
				<td><span class="badge badge-inverse"><fmt:message key="orderadministration.changestateorder" /></span></td>
			</tr>
			<c:forEach var="booking" items="${bookingList}">
				<tr>
					<ht:diffday lastDate="${booking.lastDate}" firstDate="${booking.firstDate}" />
					<td><c:out value="${booking.customer.id}" /></td>
					<td><c:out value="${booking.customer.lastName}" /></td>
					<td><c:out value="${booking.car.carNumber}" /></td>
					<td><c:out value="${booking.car.carType.carDescription}" /></td>
                    <td><c:out value="${booking.car.carType.carType}" /></td>
					<td><c:out value="${booking.dateBookingMade}" /></td>
					<td><c:out value="${booking.firstDate}" /></td>
					<td><c:out value="${booking.lastDate}" /></td>
					<td><ht:totalcost differenceDay="${diffDay}" carPrice="${booking.room.carType.carPrice}" /></td>
					<td><c:out value="${booking.comments}" /></td>
					<td><c:out value="${booking.state}" /></td>
					<td><form action="controller" method="post">
                        <label>
                            <select name="bookingState">
                                <option value="empty">
                                    <fmt:message key="orderadministration.empty"/>
                                </option>
                                <option value="CONFIRMED">
                                    <fmt:message key="orderadministration.confirmed"/>
                                </option>
                                <option value="OPEN">
                                    <fmt:message key="orderadministration.open"/>
                                </option>
                                <option value="DECLINED">
                                    <fmt:message key="orderadministration.declined"/>
                                </option>
                            </select>
                        </label> <input type="hidden" name="command" value="changebookingstate" />
                        <input type="hidden" name="idBooking" value="${booking.id }" />
							<button class="btn btn-warning" type="submit">
								<fmt:message key="orderadministration.changestateorder" />
							</button>
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