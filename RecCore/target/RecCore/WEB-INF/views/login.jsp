<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<spring:url value="/resources/assets/css/bootstrap.css" var="bootstrap_css"/>
<spring:url value="/resources/assets/css/style.css" var="style_css"/>
<spring:url value="/resources/favicon.ico" var="favicon_ico"/>
<spring:url value="/resources/assets/js/jquery.validate.min.js" var="jquery_validate_min_js"/>
<spring:url value="/resources/assets/js/bootstrap.min.js" var="boostrap_min_js"/>
<spring:url value="/register" var="register"/>
<spring:url value="/signin/twitter" var="twitterLogin"/>
<spring:url value="/start" var="start"/>
<html class="csstransforms no-csstransforms3d csstransitions" lang="en">
<head>
    <meta charset="utf-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta content="width=device-width,initial-scale=1" name="viewport">
    <title>Recomendar</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="${jquery_validate_min_js}"></script>
    <script type="text/javascript" src="${boostrap_min_js}"></script>
    <link href="${bootstrap_css}" rel="stylesheet">
    <link href="${style_css}" rel="stylesheet">
    <link href="${favicon}" rel="shortcut icon">

    <script type="text/javascript">
        $(document).ready(function () {
            $("#loginFormItem").validate({
                rules: {
                    j_username: {
                        required: true,
                        minlength: 3
                    },
                    j_password: {
                        required: true,
                        minlength: 3
                    }
                },
                messages: {
                    j_username: 'El nombre debe tener más de 3 letras',
                    j_password: 'La contraseña debe tener más de 3 letras'
                }
            });
        });
    </script>
    <c:import url="misc/analytics.jsp"/>
</head>
<body>
<c:import url="navBar.jsp"/>
<div class="container">

    <div class="span12" style="margin: 0; float: left;">

        <div class="mainFormBox">

            <div class="formBox box">

                <c:if test="${param['login_error'] == 1}">
                    <div class="alert alert-error">
                        <strong>¡Error!</strong> Usuario y/o contraseña incorrectos
                    </div>
                </c:if>
                <c:if test="${param['login_error'] == 2}">
                    <div class="alert alert-danger">
                        <strong>¡Oops!</strong> Para loguearte, primero debes confirmar tu email.
                    </div>
                </c:if>

                <form id="loginFormItem" action="/j_spring_security_check" method="post">
                    <div class="control-group">
                        <label for="j_username">Usuario</label>

                        <div class="controls">
                            <input id="j_username" type="text" name="j_username" value=""
                                   tabindex="1">
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="j_password">Contraseña</label>

                        <div class="controls">
                            <input id="j_password" type="password" name="j_password" tabindex="2"
                                   value="">
                        </div>
                    </div>

                    <button id="signin_button" class="button button-green" type="submit">Entrar</button>
                    <p class="optionLoginText">
                        <a id="forgot_password" style="display: none;" href="/password_resets/new" tabindex="5">¿Olvidaste tu contraseña?</a>
                        <a href="/forgetPassword" style="float:left;">¿Olvidaste tu contraseña?</a>
                    </p>
                </form>
                <p class="termsPolicyText" style="text-align: center; width: 100%">¿Aún no tienes tu cuenta? <a href="${start}" style="font-weight: bold">¡Regístrate!</a></p>
            </div>
        </div>
    </div>
</div>
</body>
</html>