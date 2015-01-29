<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>

<fmt:setBundle basename="by.bsu.ino.carrent.properties.resources.config" var="path" />
<fmt:setLocale value="${lang }" />
<fmt:setBundle basename="by.bsu.ino.carrent.properties.languages.Resources" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="index.title" /></title>
	<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="css/index.css"/>" />
	<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="css/bootstrap.css"/>" />
</head>
<body>
	<div class="navbar navbar-inverse navbar-static-top">
		<nav class="navbar-inner">
			<a class="brand"><fmt:message key="index.companyname" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="<c:url value="jsp/common/contact.jsp"/>">
                    <i class="icon-phone"></i> <fmt:message key="index.contact" /></a></li>
			</ul>
			<div class="btn-group btn-group-sm pull-right">
				<form class="navbar-form pull-right" action="controller" method="post">
					<input type="hidden" name="command" value="LANGUAGE">
                    <input type="hidden" name="lang" value="ru_RU">
					<button class="btn btn-info btn-mini" type="submit" name="rus">
				    <fmt:message key="index.russian" />
					</button>
				</form>
				<form class="navbar-form pull-right" action="controller" method="post">
					<input type="hidden" name="command" value="LANGUAGE">
                    <input type="hidden" name="lang" value="rn_EN">
					<button class="btn  btn-info btn-mini" type="submit" name="eng">
						<fmt:message key="index.english" />
					</button>
				</form>
				<form class="navbar-form pull-right" action="controller" method="post">
					<input type="hidden" name="command" value="PAGE">
                    <input type="hidden" name="page" value="login">
					<button class="btn btn-success" type="submit" name="login">
						<i class="icon-user icon-large"></i>
						<fmt:message key="index.login" />
					</button>
				</form>
				<c:if test="${not empty user }">
					<form class="navbar-form pull-right" action="controller" method="post">
						<input type="hidden" name="command" value="PAGE">
                        <input type="hidden" name="page" value="cabinet">
						<button class="btn btn-warning" type="submit" name="login">
							<i class="icon-user icon-large"></i>
							<fmt:message key="index.to-personal-page" />
						</button>
					</form>
				</c:if>
			</div>
		</nav>
	</div>
	<div class="container">
		<header>
			<h1>
				<fmt:message key="index.companyname" />
			</h1>
		</header>
		<article>
			<c:if test="${not empty errorMessage }">
				<div class="alert alert-danger">${errorMessage}</div>
			</c:if>
			<c:if test="${not empty actionMessage }">
				<div class="alert alert-success">${actionMessage }</div>
			</c:if>
			<p>
				<fmt:message key="index.info1" />
			</p>
			<p>
				<fmt:message key="index.info2" />
			</p>
			<div class="carousel slide" id="myCarousel">
				<ol class="carousel-indicators">
					<li class="active" data-target="#myCarousel" data-slide-to="0"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
					<li data-target="#myCarousel" data-slide-to="3"></li>
					<li data-target="#myCarousel" data-slide-to="4"></li>
					<li data-target="#myCarousel" data-slide-to="5"></li>
                    <li data-target="#myCarousel" data-slide-to="6"></li>
                    <li data-target="#myCarousel" data-slide-to="7"></li>
                    <li data-target="#myCarousel" data-slide-to="8"></li>
				</ol>
				<div class="carousel-inner">
					<div class="item active">
						<img src="<c:url value="images/1.jpg"/>">
					</div>
					<div class="item">
						<img src="<c:url value="images/2.jpg"/>">
					</div>
					<div class="item">
						<img src="<c:url value="images/3.jpg"/>">
					</div>
					<div class="item">
						<img src="<c:url value="images/4.jpg"/>">
					</div>
					<div class="item">
						<img src="<c:url value="images/5.jpg"/>">
					</div>
					<div class="item">
						<img src="<c:url value="images/6.jpg"/>">
					</div>
                    <div class="item">
                        <img src="<c:url value="images/7.jpg"/>">
                    </div>
                    <div class="item">
                        <img src="<c:url value="images/8.jpg"/>">
                    </div>
				</div>
				<a class="carousel-control left" data-slide="prev"
					href="#myCarousel">&lsaquo;</a> <a class="carousel-control right"
					data-slide="next" href="#myCarousel">&rsaquo;</a>
			</div>
		</article>
	</div>
	<footer>
		<jsp:include page="jsp/common/footer.jsp" flush="true" />
	</footer>
	<script type="text/javascript"
		src="<c:url value="js/jquery-1.11.0.js"/>"></script>
	<script type="text/javascript"
		src="<c:url value="js/bootstrap.js"/>"></script>
</body>
</html>