<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<fmt:setBundle basename="by.bsu.ino.carrent.properties.resources.config" var="path" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="by.bsu.ino.carrent.properties.languages.Resources" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title><fmt:message key="error.title" /></title>
</head>

<body>
	<jsp:expression>
        (request.getAttribute("errorMessage") != null) ? (String) request.getAttribute("errorMessage") : "unknown error"
    </jsp:expression>
	<hr />
	<form
		action="${pageContext.request.contextPath}<fmt:message key="index.page.path" bundle="${path}"/>">
		<input type="submit"
			value="<fmt:message key="error.returntoindexpage" />" />
	</form>
</body>
</html>