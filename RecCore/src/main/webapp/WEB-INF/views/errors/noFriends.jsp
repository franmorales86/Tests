<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="../header.jsp"/>
<spring:url value="/resources/assets/images/logoNav.png" var="logoNav"/>
<spring:url value="/resources/assets/css/bootstrap.css" var="bootstrap_css"/>
<spring:url value="/resources/assets/css/style.css" var="style_css"/>
<spring:url value="/resources/favicon.ico" var="favicon_ico"/>
<spring:url value="/resources/assets/js/bootstrap.min.js" var="boostrap_min_js"/>
<c:import url="../misc/analytics.jsp"/>
<div class="container">
    <div class="span12" style="margin: 0; float: left;">
        <div id="errorContainer">
            <img src="/resources/assets/images/404.png">

            <h1>¡Oops! No tienes ningún amigo de Facebook en Recomendar.</h1>

            <p>Pero tranquilo, dentro de poco te enviaremos un email para que puedas ser parte de Recomendar.</p>
        </div>
    </div>
</div>
</body>
</html>