<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<spring:url value="/resources/assets/images/logoNav.png" var="logoNav"/>
<spring:url value="/resources/assets/css/bootstrap.css" var="bootstrap_css"/>
<spring:url value="/resources/assets/css/style.css" var="style_css"/>
<spring:url value="/resources/favicon.ico" var="favicon_ico"/>
<spring:url value="/resources/assets/js/jquery.validate.min.js" var="jquery_validate_min_js"/>
<spring:url value="/resources/assets/js/bootstrap.min.js" var="boostrap_min_js"/>
<spring:url value="/resources/assets/js/register.js" var="register_js"/>
<spring:url value="/login" var="login"/>
<spring:url value="/registerRec" var="register"/>
<spring:url value="/v1/utilities/email_exist/" var="emailVerify"/>
<html class="csstransforms no-csstransforms3d csstransitions" lang="en">
<head>
    <meta charset="utf-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta content="width=device-width,initial-scale=1" name="viewport">
    <title>Recomendar</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="${jquery_validate_min_js}"></script>
    <script type="text/javascript" src="${boostrap_min_js}"></script>
    <script type="text/javascript" src="${register_js}"></script>
    <link href="${bootstrap_css}" rel="stylesheet">
    <link href="${style_css}" rel="stylesheet">
    <link href="${favicon}" rel="shortcut icon">
    <c:import url="misc/analytics.jsp"/>
</head>
<body>
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

<div class="container">

    <div class="span12" style="margin: 0; float: left;">

        <div class="mainFormBox">

            <div class="formBox box">

                <h2 class="formTittle">Registrarse</h2>

                <p class="formTextInfo">Puedes registrarte con cualquiera de tus redes sociales favoritas:</p>

                <a id="loginTwitter" href="javascript:registerTwitter()" class="button button-twitter"><i
                        class=" aw-icon-twitter"></i> | registrar con twitter</a>
                <a id="loginFacebook" href="javascript:registerFacebook()" class="button button-facebook"><i
                        class=" aw-icon-facebook"></i> | registrar con facebook</a>

                <p class="formTextInfo">Si lo prefieres, puedes registrarte con tu email.</p>

                <form:form id="registerFormItem" action="registerRec" commandName="user">
                    <fieldset>
                        <div class="control-group">
                            <label class="control-label" for="name">Nombre Completo</label>

                            <div class="controls">
                                <form:input path="name" type="text" id="name" name="name"
                                            data-error-style="inline"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="username">Nombre de Usuario</label>

                            <div class="controls">
                                <form:input path="username" type="text" id="username" name="username"
                                            data-error-style="inline"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="email">Email</label>

                            <div class="controls">
                                <form:input path="email" type="text" id="email" name="email"
                                            data-error-style="inline"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="password">Contraseña</label>

                            <div class="controls">
                                <form:input path="password" type="password" id="password"
                                            name="password" data-error-style="inline"/>
                            </div>
                        </div>

                        <button type="button" onclick="sendForm()" class="button">Registrarse</button>
                        <p class="optionLoginText"> Ya tienes cuenta? <a href="/login">Inicia sesión</a>.</p>

                    </fieldset>
                </form:form>
                <input id="emailVerifyUrl" type="hidden" value="${emailVerify}"/>

            </div>

            <p class="termsPolicyText">
                Al registrarte, aceptas los <a id="signup_terms" href="#">Términos de Uso</a> y la <a id="signup_privacy" href="#">Política de Privacidad</a> de Recomendar.
            </p>
            <script>
                $("#signup_terms").click(function () {
                    $('#termsModal').modal('show');
                });
                $("#signup_privacy").click(function () {
                    $('#policyModal').modal('show');
                });
            </script>
        </div>
        <form id="tw_signin" action="<c:url value="/signin/twitter"/>" method="POST"></form>
        <form id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST"></form>
    </div>

</div>

<div class="modal hide fade" id="termsModal">
    <a class="button-close modalClose" data-dismiss="modal">
        <i class="aw-icon-remove"></i>
    </a>

    <div class="modal-content">
        <div class="primary-heading">
            <h1>Términos de Uso</h1>
        </div>
        <div class="flush">
            <div class="module-set" style="padding:15px;">
                <div id="termsText" style="max-height:230px; overflow-y:scroll; padding:10px;">

                    <p style="color: #4090ba; font-size: 18px; font-weight: bold;">Información General</p>

                    <p style="font-size:13px;">RECOMENDAR LABS, S.L. es una sociedad mercantil cuyo domicilio social se
                        encuentra en C\ Acequia de Tafira Nº4, 35300 Santa Brígida, Las Palmas, provista de C.I.F.
                        B-76136902 y debidamente inscrita en el Registro Mercantil de Las Palmas al Tomo 2034, Libro 0,
                        Folio 107, Sección 1, Hoja GC-46180 e Inscripción 1ª (“RECOMENDAR”). Y su correo electrónico de
                        contacto es: contacto@recomendar.com.</p>

                    <p style="font-size:13px;">Los presentes Términos de Uso regulan el acceso y la utilización del
                        sitio web accesible a través de la url www.recomendar.com (el “Sitio Web”) titularidad de
                        RECOMENDAR LABS, S.L. cuya finalidad es encontrar todas las recomendaciones sobre cualquier
                        producto y encontrar las mejores recomendaciones sobre cada tema interesado por sus Usuarios. A
                        su vez, los productos que se citen en el Sitio Web serán fabricados por terceros ajenos a
                        RECOMENDAR.</p>

                    <p style="font-size:13px;">El acceso al Sitio Web o su uso por el Usuario implica necesariamente, y
                        sin reservas, el sometimiento y aceptación de los presentes Términos de Uso y Política de
                        Privacidad. Por tanto, se recomienda que el Usuario las lea de detenidamente cada vez que quiera
                        acceder al Sitio Web.</p>


                    <p style="color: #4090ba; font-size: 18px; font-weight: bold;">Descripción del Servicio</p>

                    <p style="font-size:13px;">El Sitio Web está configurado como una plataforma destinada al
                        intercambio de opiniones sobre cualquier producto o afición de sus Usuarios de tal modo que
                        pudiera llegar a darse en un futuro una promoción y comercialización de productos para su
                        adquisición por parte de los Usuarios. El Usuario que desease adquirir productos deberá leer y
                        aceptar las Condiciones Generales de Contratación del Sitio Web mediante la validación de la
                        casilla habilitada a tal efecto en el propio Sitio Web cuando esté habilitada dicha función.</p>

                    <p style="font-size:13px;">RECOMENDAR pondrá los medios razonables a disposición del Usuario para
                        que los posibles productos incluidos en el Sitio Web sean exactos y estén actualizados. No
                        obstante, RECOMENDAR no está en condiciones de poder garantizar esta circunstancia en todo
                        momento. Asimismo, RECOMENDAR no será responsable del funcionamiento de las redes de
                        comunicación que posibilitan el funcionamiento del Sitio Web, ya sean fijas o móviles, cuya
                        responsabilidad incumbe enteramente a prestadores ajenos a RECOMENDAR.</p>

                    <p style="font-size:13px;">Si lo considera oportuno, RECOMENDAR podrá suspender de forma inmediata
                        la prestación del servicio y, en su caso, retirar los contenidos que estime incorrectos o
                        ilegales, ya lo realice a su exclusivo criterio, ya a petición de tercero afectado o de
                        autoridad competente, sin que dicha suspensión pueda dar lugar a indemnización alguna.</p>


                    <p style="color: #4090ba; font-size: 18px; font-weight: bold;"> Utilización del Sitio Web</p>

                    <p style="font-size:13px;">Los Usuarios serán enteramente responsables del correcto uso y de las
                        opiniones e imágenes que introduzcan en el Sitio Web con sujeción a la legalidad vigente, sea
                        nacional o internacional, así como a los principios de buena fe, la moral, las buenas costumbres
                        y el orden público, y con el compromiso de observar diligentemente cualquier instrucción
                        adicional que pueda serle impartida por RECOMENDAR.</p>

                    <p style="font-size:13px;">Los Usuarios se comprometen a abstenerse de realizar comportamientos
                        contrarios a la legalidad vigente, incluyendo, entre otros y sin ánimo de ser exhaustivos, los
                        siguientes: menoscabar injustificadamente la calidad de productos citados en el Sitio Web; los
                        derechos de Propiedad Intelectual sobre imágenes de terceros que se pudieran haber introducido
                        en el Sitio Web; alterar o modificar parte alguna del Sitio Web, eludiendo, desactivando o
                        manipulando de cualquier forma las funciones del Sitio Web; introducir virus informáticos,
                        archivos defectuosos, o cualquier otro programa informático que pueda provocar daños o
                        alteraciones en los contenidos, programas o sistemas de RECOMENDAR.</p>

                    <p style="font-size:13px;">Nuestro sistema antivirus es razonablemente seguro. Por todo ello,
                        RECOMENDAR no se hace responsable de los potenciales daños o errores que, debidos a la presencia
                        de cualquier virus, pueda sufrir el sistema informático (hardware o software) de los Usuarios
                        cuando éstos accedan al Sitio Web o lo utilicen.</p>


                    <p style="color: #4090ba; font-size: 18px; font-weight: bold;">Enlaces a Terceros</p>

                    <p style="font-size:13px;">RECOMENDAR no se responsabiliza de aquellos contenidos, productos o
                        servicios alojados en sitios web de terceros que sean accesibles, directa o indirectamente, por
                        medio de enlaces desde el Sitio Web.

                    <p style="font-size:13px;">RECOMENDAR se reserva el derecho a retirar, de manera unilateral y sin
                        previo aviso, los enlaces que aparecen en el Sitio Web.</p>


                    <p style="color: #4090ba; font-size: 18px; font-weight: bold;">Propiedad Itelectual e Industrial</p>

                    <p style="font-size:13px;">Todos los derechos de propiedad intelectual sobre el Sitio Web pertenecen
                        en exclusiva a RECOMENDAR. Se prohíbe expresamente toda forma de reproducción, distribución,
                        comunicación pública, puesta a disposición, modificación, transformación y cualquier otro acto
                        de explotación no consentido por RECOMENDAR de los contenidos que integran el Sitio Web, así
                        como de las bases de datos y del programa informático que permiten el funcionamiento del
                        mismo.</p>

                    <p style="font-size:13px;">Todas las marcas y logotipos que aparecen en el Sitio Web son propiedad
                        de RECOMENDAR o de empresas proveedoras de RECOMENDAR, y se hallan protegidas por derechos de
                        propiedad industrial e intelectual. Queda terminantemente prohibido cualquier uso de las marcas,
                        nombres comerciales, denominaciones sociales, rótulos de establecimiento, logotipos o
                        cualesquiera otros signos distintivos que aparezcan en el Sitio Web.</p>


                    <p style="color: #4090ba; font-size: 18px; font-weight: bold;">Modificación de los Términos de
                        Uso</p>

                    <p style="font-size:13px;">RECOMENDAR se reserva el derecho de modificar los presentes Términos de
                        Uso, de acuerdo con la legislación aplicable en cada momento, de lo que informará debidamente en
                        el Sitio Web, por lo que se recomienda a los Usuarios que revisen periódicamente los presentes
                        Términos de Uso.</p>
                </div>
            </div>
        </div>
        <div class="flush">

        </div>
    </div>
</div>


<div class="modal hide fade" id="policyModal">
    <a class="button-close modalClose" data-dismiss="modal">
        <i class="aw-icon-remove"></i>
    </a>

    <div class="modal-content">
        <div class="primary-heading">
            <h1>Política de Privacidad</h1>
        </div>
        <div class="flush">
            <div class="module-set" style="padding:15px;">
                <div id="termsText" style="max-height:230px; overflow-y:scroll; padding:10px;">

                    <p style="font-size:13px;">La presente Política de Privacidad se aplica al Sitio Web titularidad de
                        RECOMENDAR, cuya finalidad es encontrar todas las recomendaciones sobre cualquier producto y
                        encontrar las mejores recomendaciones sobre cada tema interesado por sus Usuarios.</p>


                    <p style="color: #4090ba; font-size: 18px; font-weight: bold;">Tratamiento de los Datos de Carácter
                        General</p>

                    <p style="font-size:13px;">De conformidad con la legislación vigente en materia de protección de
                        datos de carácter personal y en particular, la Ley Orgánica 15/1999 de 13 de Diciembre de
                        Protección de Datos de Carácter Personal y su Reglamento de Desarrollo 1720/2007, de 21 de
                        diciembre, así como toda la normativa europea y española aplicable a esta materia, los datos que
                        voluntariamente facilite el Usuario del Sitio Web, serán incorporados para su tratamiento a un
                        fichero debidamente inscrito por RECOMENDAR en la Agencia Española de Protección de Datos con la
                        finalidad de poder identificarle y contactarle, así como facilitarle cualquier información que
                        solicite.</p>

                    <p style="font-size:13px;">En cualquier caso, el Usuario podrá ejercer sus derechos de acceso,
                        rectificación, oposición y cancelación, los cuales podrán ser ejercidos por él y, en su caso,
                        por quien lo represente, mediante solicitud escrita y firmada dirigida a:</p>

                    <p style="margin:0;"><strong>RECOMENDAR LABS, S.L.</strong></p>

                    <p style="margin:0;"><strong>C.I.F.: B-76136902</strong></p>

                    <p style="margin:0;"><strong>C\ Acequia de Tafira Nº4</strong></p>

                    <p style="margin:0;"><strong>35300 Santa Brígida</strong></p>

                    <p><strong>Las Palmas</strong></p>

                    <p style="font-size:13px;">Dicha solicitud deberá ir acompañada de los siguientes datos: nombre y
                        apellidos del Usuario, domicilio a efectos de notificaciones y fotocopia del documento nacional
                        de identidad o del pasaporte. En el caso de representación, deberá probarse ésta mediante
                        documento fehaciente.</p>

                    <p style="font-size:13px;">RECOMENDAR podrá también recopilar la dirección IP del Usuario así como
                        los datos de navegación dentro del Sitio Web con el fin de identificar posibles usos
                        fraudulentos del Sitio Web.</p>

                    <p style="font-size:13px;">RECOMENDAR se compromete a mantener la privacidad de los datos
                        facilitados por el Usuario. Asimismo, RECOMENDAR garantiza que ha adoptado los niveles de
                        seguridad de protección de los datos personales legalmente requeridos, y ha instalado todos los
                        medios y medidas técnicas a su alcance para evitar la pérdida, mal uso, alteración, acceso no
                        autorizado y robo de los datos personales facilitados.</p>

                    <p style="font-size:13px;">El Usuario deberá notificar a RECOMENDAR cualquier modificación que se
                        produzca en los datos que haya facilitado, respondiendo en cualquier caso de la veracidad y
                        exactitud de los datos suministrados en cada momento.</p>


                    <p style="color: #4090ba; font-size: 18px; font-weight: bold;">Envío de Comunicaciones
                        Comerciales</p>

                    <p style="font-size:13px;">Mediante la validación de la casilla habilitada al efecto en el Sitio
                        Web, el Usuario consiente la recepción de comunicaciones comerciales vía correo electrónico por
                        parte de RECOMENDAR. El Usuario podrá renunciar en cualquier momento a recibir dichas
                        comunicaciones enviando un correo electrónico a notificaciones@recomendar.com manifestando dicha
                        intención de renuncia. Asimismo, esta posibilidad le será ofrecida al Usuario en cada
                        comunicación comercial que reciba.</p>


                    <p style="color: #4090ba; font-size: 18px; font-weight: bold;">Menores de Edad</p>

                    <p style="font-size:13px;">Los productos que pudieran ofertarse en el Sitio Web, si es el caso, sólo
                        estarán disponibles para mayores de edad. Por lo tanto, aquellos que no cumplan con esta
                        condición deberán abstenerse de suministrar información personal en el Sitio Web para ser
                        incluida en las bases de datos de RECOMENDAR. Sin embargo, con el previo consentimiento de sus
                        padres o tutores podrán proceder a la inclusión de sus datos personales en los formularios del
                        Sitio Web.</p>


                    <p style="color: #4090ba; font-size: 18px; font-weight: bold;">Uso de Cookies</p>

                    <p style="font-size:13px;">El Sitio Web utiliza cookies para ayudarle a personalizar su experiencia
                        en línea. Las cookies son pequeños ficheros de datos que se alojan en el terminal del Usuario y
                        que contienen cierta información de la visita al Sitio Web. El único propósito de las cookies es
                        proporcionar funcionalidades para ahorrarle tiempo, sin que en ningún caso sea posible asociar
                        cookies a los datos personales concretos de los Usuarios ni identificar a éstos a través de
                        aquellas.</p>


                </div>
            </div>
        </div>
        <div class="flush">

        </div>
    </div>
</div>


</body>
</html>