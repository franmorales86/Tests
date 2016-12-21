<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:import url="../header.jsp"/>
<c:import url="../misc/analytics.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {
    });
</script>
<div class="container">
    <div class="span12" style="margin: 0; float: left;">
        <div class="box">
            <c:if test="${result == true}">
                <h1>Verificación de email correcta</h1>

                <p>Le hemos enviado un email de confirmación, revise su correo incluyendo el filtro de spam</p>
            </c:if>
            <c:if test="${result == false}">
                <h1>¡Oops! Verificación de email incorrecta</h1>

                <p>Ha ocurrido un error al verificar su correo, por favor verifique los datos.</p>

                <p>Si necesita más ayuda contacte con nosotros en <a href="mailto:soporte@recomendar.com">soporte@recomendar.com</a>
                </p>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>