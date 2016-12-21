<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:import url="header.jsp"/>
<c:import url="misc/analytics.jsp"/>
<div class="container">
    <div class="span12" style="margin: 0; float: left;">
        <div class="mainFormBox">
            <div class="formBox box">

                <h2 class="formTittle">Recordar contraseña</h2>

                <c:if test="${empty ok}">
                    <p class="formTextInfo">Introduzca su email y pulse en recordar contraseña.</p>
                    <c:if test="${not empty error}">
                        <div class="alert alert-error">Debe introducir un email válido</div>
                    </c:if>

                    <form:form id="forgetPasswordForm" action="forgetPassword" commandName="user">
                        <div class="control-group">
                            <label for="email">Email</label>

                            <div class="controls">
                                <form:input path="email" type="text" id="email" name="email"
                                            data-error-style="inline" tabindex="2"/>
                            </div>
                        </div>
                        <button id="rememberPassword" class="button" type="submit">Recordar contraseña</button>
                    </form:form>
                </c:if>

                <c:if test="${not empty ok}">
                    <p class="formTextInfo">Le hemos enviado un email con su nueva contraseña.</p>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>