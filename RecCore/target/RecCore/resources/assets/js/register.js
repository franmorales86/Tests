$(document).ready(function () {
    $("#registerFormItem").validate({
        rules: {
            name: {
                required: true,
                minlength: 3
            },
            email: {
                required: true,
                email: true
            },
            username: {
                required: true,
                minlength: 3
            },
            password: {
                required: true,
                minlength: 5
            }
        },
        messages: {
            name: 'Debe tener al menos 3 caracteres',
            email: 'Debe especificar un email válido',
            username: 'Debe tener al menos 3 caracteres',
            password: 'Debe tener al menos 5 caracteres'
        }
    });

});

function sendForm() {
    var validForm = $("#registerFormItem").validate().form();
    if (validForm) {
        if ($("#termsAndConditions").is(":checked")) {
            var parameters = {};
            var email = $("#email").val();
            if (email != null) {
                var url = $("#emailVerifyUrl").val() + email + '/';
                $.get(url, parameters, function (result) {
                    if (result != null) {
                        if (result.code == "200" && result.message == "true") {
                            alert("Mail repetido");
                            return false;
                        }
                    }
                    $("#controlEmail").removeClass("controlValid").addClass("controlInvalid");
                    //Enviamos el email...
                    $("#registerFormItem").submit();
                });
            } else {
                alert("Mail no válido");
            }
        } else {
            alert("Condiciones de servicio no verifico");
        }
    }
}

/**
 * Validamos el email de usuario
 */
function validateEmail() {
    var parameters = {};
    var email = $("#email").val();
    if (email != null) {
        var url = $("#emailVerifyUrl").val() + email + '/';
        $.get(url, parameters, function (result) {
            if (result != null) {
                if (result.code == "200" && result.message == "true") {
                    alert("Mail repetido");
                    // $("#controlEmail").removeClass("controlInvalid").addClass("controlValid");
                    return false;
                }
            }
            alert("Mail correcto");
            $("#controlEmail").removeClass("controlValid").addClass(
                "controlInvalid");
            return true;
        });
    }
}


function registerTwitter() {
    $("#tw_signin").submit();
}

function registerFacebook() {
    $("#fb_signin").submit();
}