<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="../header.jsp"/>
<spring:url value="/resources/assets/images/logoNav.png" var="logoNav"/>
<spring:url value="/resources/assets/css/bootstrap.css" var="bootstrap_css"/>
<spring:url value="/resources/assets/css/style.css" var="style_css"/>
<spring:url value="/resources/favicon.ico" var="favicon_ico"/>
<spring:url value="/resources/assets/js/jquery.validate.min.js" var="jquery_validate_min_js"/>
<spring:url value="/resources/assets/js/bootstrap.min.js" var="boostrap_min_js"/>
<spring:url value="/resources/assets/js/register/fromLanding.js" var="register_js"/>
<spring:url value="/login" var="login"/>
<spring:url value="/registerRec" var="register"/>
<spring:url value="/v1/utilities/email_exist/" var="emailVerify"/>
<script type="text/javascript" src="${register_js}"></script>
<c:import url="../misc/analytics.jsp"/>
<div class="container">
    <div class="span12" style="margin: 0; float: left;">
        <div class="mainFormBox box" style="margin-top: 80px">
            <div class="formBox" style="margin: 0 10px">
                <h2 class="formTittle">Registrarse</h2>

                <p class="formTextInfo">Puedes registrarte haciendo uso de tu cuenta de Facebook</p>

                <div style="width: 50%; margin: 0 auto;">
                    <a id="fbBtn" style="margin: 15px 10px 15px 14px; float:left" href="javascript:registerFacebook()" class="button button-facebook"><i class=" aw-icon-facebook"></i> |
                        Registrarse</a>
                </div>
            </div>
            <div class="formBox" style="margin: 0 10px">
                <p class="formTextInfo">Si no déjanos tu email y te avisaremos.</p>

                <form id="mc-embedded-subscribe-form"
                      action="https://recomendar.us5.list-manage2.com/subscribe/post?u=1655b15e2978b2c775733e435&amp;id=d93af2ce0d"
                      method="post" name="mc-embedded-subscribe-form" class="validate" novalidate="">
                    <input id="subscriptionMail" type="text" name="EMAIL" placeholder="Introduce tu email ;)" data-original-title="">
                    <button id="subscriptionMailButton" class="button button-green">Registrarse</button>
                    <p class="optionLoginText"> Ya tienes cuenta? <a href="/login">Inicia sesión</a>.</p>
                </form>
            </div>

            <p class="termsPolicyText"> Al registrarte, aceptas los <a id="signup_terms">Términos de Uso</a> y la <a id="signup_privacy">Política de Privacidad</a> de Recomendar.</p>
        </div>
        <form id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
            <input type="hidden" name="scope" value="publish_stream,offline_access"/>
        </form>
    </div>
</div>

<c:import url="../misc/modalTerms.jsp"/>
<c:import url="../misc/modalPrivacy.jsp"/>

</body>
</html>