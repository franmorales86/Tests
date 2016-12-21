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
                required: true
            }
        },
        messages: {
            name: 'El nombre debe tener más de 3 letras',
            email: 'Debe especificar un email válido',
            username: 'Será el nombre visible por los usuarios',
            password: 'Debe tener al menos 3 letras'
        }
    });

    $("#signup_terms").click(function () {
        $('#termsModal').modal('show');
    });
    $("#signup_privacy").click(function () {
        $('#policyModal').modal('show');
    });


});

function sendForm() {
    var validForm = $("#registerFormItem").validate().form();
    if (validForm) {
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