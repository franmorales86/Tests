<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
                <c:import url="../misc/terms.jsp"/>
            </div>
        </div>
        <div class="flush">

        </div>
    </div>
</div>
