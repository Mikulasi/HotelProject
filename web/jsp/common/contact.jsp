<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="by.bsu.ino.carrent.properties.resources.config" var="path" />
<fmt:setLocale value="${lang }" />
<fmt:setBundle basename="by.bsu.ino.carrent.properties.languages.Resources" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="contact.title" /></title>
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
			<a class="brand"><fmt:message key="contact.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="<c:url value="../../index.jsp"/>"><i
                        class="icon-home"></i> <fmt:message key="login.home" /></a></li>
			</ul>
		</nav>
	</div>
	<div>
		<div class="container pull-left" id="container-wraper">
			<table class="table table-striped">
				<thead>
					<tr>
						<th><fmt:message key="contact.info" /></th>
					</tr>
				</thead>
				<tr class="info">
                    <td><span class="badge"><fmt:message key="contact.name" /></span></td>
					<td><span class="badge"><fmt:message key="contact.curr-name" /></span></td>
				</tr>
				<tr class="info">
					<td><span class="badge"><fmt:message key="contact.phone" /></span></td>
					<td><span class="badge"><fmt:message key="contact.curr-phone" /></span></td>
				</tr>
				<tr class="info">
					<td><span class="badge"><fmt:message key="contact.address" /></span></td>
					<td><span class="badge"><fmt:message key="contact.curr-address" /></span></td>
				</tr>
			</table>
		</div>
	</div>
	<footer class="white navbar-fixed-bottom">
		<jsp:include page="footer.jsp" flush="true" />
	</footer>
	<!-- JavaScript -->
	<script type="text/javascript"
		src="<c:url value="../../js/jquery-1.11.0.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="../../js/bootstrap.js"/>"></script>
</body>
</html>