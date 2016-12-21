<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<spring:url value="/resources/assets/images/logoNav.png" var="logoNav"/>
<header>
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div id="navbarHeader">
            <div class="container">
                <ul class="nav pull-left">
                    <li>
                        <div class="logoNav">
                            <a href="http://recomendar.com"><img src="${logoNav}"></a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>