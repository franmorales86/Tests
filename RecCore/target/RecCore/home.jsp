<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<spring:url value="/logout" var="goToLogoutURL"/>
<spring:url value="/" var="goToLoginURL"/>
<!DOCTYPE HTML>
<html>
<head>
    <title>Recomendar.com</title>
    <style type="text/css">
        header {
            color: white;
            background-color: #4499aa;
            padding: .5em;
        }
    </style>
</head>
<body>
<header>
    <h1>Recomendar.com</h1>
</header>
<div id="contenido">
    <security:authorize ifNotGranted="ROLE_USER">
        <p>Sessión no iniciada.</p>

        <p>
            <a href="${goToLoginURL}">Iniciar sessión</a>
        </p>
    </security:authorize>
    <security:authorize ifAllGranted="ROLE_USER">
        <p>Ha iniciado sessión correctamente.</p>

        <p>
            <a href="${goToLogoutURL}">Cerrar sessión</a>
        </p>
    </security:authorize>
</div>
</body>
</html>