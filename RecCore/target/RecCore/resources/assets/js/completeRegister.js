$(document).ready(function () {
    $("#registerForm").validate({
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
});

function sendForm() {
    var validForm = $("#registerForm").validate().form();
    if (validForm) {
        var parameters = {};
        var email = $("#email").val();
        if (email != null) {
            var url = $("#emailVerifyUrl").val() + email + '/';
            $.get(url, parameters, function (result) {
                if (result != null) {
                    if (result.code == "200" && result.message == "true") {
                        alert("El email introducido ya está en uso");
                    } else if (result.code == "200" && result.message == "false") {
                        verifyUsernameAndSend();
                    }
                }
            });
        } else {
            alert("El email introducido ya está en uso");
        }
    }
}

function verifyUsernameAndSend() {
    var username = $("#username").val();
    var oldUsername = $("#usernameOld").val();
    if (username === oldUsername) {
        $("#registerForm").submit();
    } else if (username != null) {
        var url = $("#usernameVerifyUrl").val() + username + '/';
        $.get(url, {}, function (result) {
            if (result != null) {
                if (result.code == "200" && result.message == "true") {
                    alert("Nombre de usuario en uso, escoja otro");
                    return false;
                } else if (result.code == "200" && result.message == "false") {
                    $("#registerForm").submit();
                }
            }
        });
    } else {
        alert("Nombre de usuario en uso, escoja otro");
    }
}