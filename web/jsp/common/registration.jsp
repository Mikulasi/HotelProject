<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<fmt:setBundle basename="by.bsu.ino.carrent.properties.resources.config" var="path" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="by.bsu.ino.carrent.properties.languages.Resources" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="registration.title" /></title>
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="../../css/login.css"/>" />
    <link rel="stylesheet" type="text/css" media="screen"
          href="<c:url value="../../css/glyph.css"/>" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="../../css/bootstrap.css"/>" />
<link rel="stylesheet" type="text/css" media="screen"
    href="<c:url value="../../css/bootstrap1.css"/>" />
<link rel="stylesheet" type="text/css" media="screen"
    href="<c:url value="../../css/bootstrapValidator.css"/>" />
<script type="text/javascript"
	src="<c:url value="../../js/jquery-1.11.0.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="../../js/bootstrap.js"/>"></script>
<script type="text/javascript"
    src="<c:url value="../../js/bootstrapValidator.js"/>"></script>
<script type="text/javascript" src="../../js/booy.js"></script>
</head>
    <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
		<nav class="navbar-inner">
			<a class="brand"><fmt:message key="registration.title" /></a>
			<ul class="nav">
				<li class="divider-vertical"></li>
				<li><a href="<c:url value="../../index.jsp"/>"><i class="icon-home"></i> <fmt:message
							key="login.home" /></a></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<div id="register-wraper">
			<form class="login-form register-form" method="post" action="controller">
				<fieldset>
                    <legend>
                        <a class="brand"><fmt:message key="registration.title" /></a>
                    </legend>
                </fieldset>
				<div class="body">
					<!-- Login validation -->
					<div class="form-group">
						<label for="login"> <fmt:message key="registration.login" /></label><br>
						<input type="text" id="login"
                            required
                            autofocus
							name="login"
                            pattern="^[а-яА-ЯёЁa-zA-Z]{5,20}$"
                            data-bv-login-message = "The login can consist of 5-20 alphabetical characters only"
							placeholder="<fmt:message key="registration.login" />" />
					</div>
					<!--  Password validation -->
					<div class="form-group">
						<label for="password"><fmt:message key="registration.password" /></label><br>
                        <input type="password" id="password"
                            required
                            name="password"
							pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
                            data-bv-password-message = "Password should start with a letter and consist of 1-20 characters"
							placeholder="<fmt:message key="registration.password" />" />
					</div>
					<!--  Password confirmed validation -->
					<div class="form-group">
						<label class="style" for="passwordagain"><fmt:message key="registration.passwordagain" /></label><br>
                        <input type="password" required name="password_again" id="passwordagain"
							data-validation="custom" data-validation-matches-match="password"
							pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
                            data-bv-passwordagain-message = "Must match email address entered above"
							placeholder="<fmt:message key="registration.passwordagain" />" />
					</div>
					<!-- Email validation -->
					<div class="form-group">
						<label for="email"><fmt:message key="registration.email" /></label><br>
						<input type="email" required name="email" id="email"
                            data-bv-emailaddress-message = "The value is not a valid email address"
							placeholder="<fmt:message key="registration.email" />" />
					</div>
                    <!-- First name validation -->
					<div class="form-group">
						<label for="firstname"><fmt:message key="registration.firstname" /></label><br>
                        <input type="text" required name="first_name" id="firstname" data-validation="custom"
							pattern="^[а-яА-ЯёЁa-zA-Z]{1,20}$"
                            data-bv-firstname-message = "First name should consists of 1-20 characters"
							placeholder="<fmt:message key="registration.firstname" />" />
					</div>
					<!--  Last name validation -->
					<div class="form-group">
						<label for="lastname"> <fmt:message key="registration.lastname" /></label><br>
                        <input type="text" required name="last_name" id="lastname" data-validation="custom"
							pattern = "^[а-яА-ЯёЁa-zA-Z]{1,20}$"
                            data-bv-lastname-message="Last name should consist of 1-20 characters"
							placeholder="<fmt:message key="registration.lastname" />" />
					</div>
					<!-- Street validation -->
                    <div class="form-group">
                        <label for="street"> <fmt:message key="registration.street" /></label><br>
                        <input type="text" required name="address_street" id="street" data-validation="custom"
                            pattern="^[a-zA-Z0-9\s,'-]*$"
                            data-bv-street-message = "Street should consist of 1-20 characters"
                            placeholder="<fmt:message key="registration.street" />" />
                    </div>
                    <!-- City-->
                    <div class="form-group">
                        <label for="city"> <fmt:message key="registration.city" /></label><br>
                        <input type="text" required name="address_city" id="city" data-validation="custom"
                           pattern="^[а-яА-ЯёЁa-zA-Z]{1,20}$"
                           data-bv-city-message = "City should consist of 1-20 characters"
                           placeholder="<fmt:message key="registration.city" />" />
                    </div>
                    <!-- Country-->
                    <div class="form-group">
                        <label for="country"> <fmt:message key="registration.country" /></label><br>
                        <input type="text" required name="address_country" id="country" data-validation="custom"
                           pattern = "^[а-яА-ЯёЁa-zA-Z]{1,20}$"
                           data-bv-country-message="Country should consist of 1-20 characters"
                           placeholder="<fmt:message key="registration.country" />" />
                    </div>
                   <!-- DOB-->
                    <div class="form-group">
                        <label for="dob"> <fmt:message key="registration.dob" /></label><br>
                        <input type="date" required name="DOB" id="dob"
                           data-bv-dob-message = "Birth date should be in format dd/mm/yyyy"
                           placeholder="<fmt:message key="registration.dob" />" />
                    </div>
                    <!-- Mobile phone-->
                    <div class="form-group">
                        <label for="phone"> <fmt:message key="registration.mobilephone" /></label><br>
                        <input type="text" required name="phone" id="phone"
                           pattern="^\d{3}-\d{2}-\d{3}-\d{2}-\d{2}$"
                           data-bv-phone-message = "Phone number should be in format 375292268016"
                           placeholder="<fmt:message key="registration.mobilephone" />" />
                    </div>
				</div>
				<div class="footer">
					<input type="hidden" name="command" value="registration" />
					<div class="btnfirst">
						<button class="btn btn-access" type="submit" name="registration">
							<fmt:message key="registration.registration" />
						</button>
					</div>
				</div>
			</form>
			<c:if test="${not empty errorMessage }">
				<div class="alert alert-danger">${errorMessage}</div>
			</c:if>
		</div>
	</div>
	<footer class="white navbar-fixed-bottom">
		<jsp:include page="footer.jsp" flush="true" />
	</footer>
</body>
</html>

