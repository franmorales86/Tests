<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML>
<spring:url value="/resources/favicon.ico" var="favicon_ico"/>
<spring:url value="/signin/twitter" var="twitterLogin"/>
<html class="csstransforms no-csstransforms3d csstransitions" lang="en">
<head>
    <meta charset="utf-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta content="width=device-width,initial-scale=1" name="viewport">
    <title>Recomendar</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <link href="${favicon}" rel="shortcut icon">
    <script type="text/javascript">
        $(document).ready(function () {
            $("#tw_signin").submit();
        });
    </script>
    <c:import url="misc/analytics.jsp"/>
</head>
<body>
<div class="container">
    Iniciando el proceso...
    <form id="tw_signin" action="<c:url value='/signin/twitter'/>" method="POST"></form>
</div>
</body>
</html>