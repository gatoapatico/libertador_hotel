<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inicia session</title>
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="css/loginStyle.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    </head>

    <body>
        <%-- Verifica si hay un mensaje de error en la variable de sesión --%>
    <c:if test="${not empty sessionScope.registroError}">
        <div class="Usuario ya registrado">
            ${sessionScope.registroError}
        </div>
    </c:if>
    <header>
        <div class="navbar">
            <div class="nav-links">
                <ul class="links">
                    <button class="login-btn">LOG IN</button>
                </ul>
            </div>
        </div>
    </header>
    <div class="blur-bg-overlay"></div>
    <div class="form-popup">
        <span class="close-btn bx bx-x"></span>

        <div class="form-box login">
            <!--Titulo -->
            <div class="form-details">
                <h2>Bienvenido al hotel El Libertador</h2>
                <p>Inicie sesión con su información personal</p>
            </div>

            <!--Empieza el formulario -->
            <div class="form-content">
                <h2>Iniciar sesión</h2>
                <form class="login-form" id="login-form" action="SvLogin" method="post" >
                    <div class="input-field">
                        <input type="text" id="txtNombre" name="txtNombre" >
                        <label>Email</label>
                    </div>
                    <div class="input-field">
                        <input type="password" id="txtContra" name="txtContra">
                        <label>Contraseña</label>
                    </div>
                    <br>
                    <div class="captcha">
                        <label for="captcha-input">Captcha</label>
                        <div class="preview"></div>
                        <div class="captcha-form">
                            <input type="text" id="captcha-form" placeholder="Ingresa el captcha" >
                            <button class="captcha-refresh" type="button">
                                <i class="bx bx-refresh icon"></i>
                            </button>
                        </div>
                    </div>
                    <button type="submit" id="login-btn" value="Inicia sesion">Inicia sesion</button>
                    <input type="hidden" name="formSubmitted" value="true">
                </form>
                <a href="#" class="forgot-pass-link">¿Olvido su contraseña?</a>
                <div class="bottom-link">
                    ¿No tiene cuenta?
                    <a href="#" id="signup-link">Registrate</a>
                </div>
            </div>
        </div>




        <div class="form-box signup">
            <div class="form-details">
                <h2>Forma parte nuestra familia</h2>
                <p>Para formar parte de nuestra comunidad, regístrese utilizando su información personal.</p>
            </div>
            <div class="form-content">
                <h2>Registro</h2>
                <form class="registro-form" method="post" action="SvRegistrarUsuario"> 
                    <div class="input-field">
                        <input type="email" id="txtEmail" name="txtEmail" required>
                        <label>Email</label>
                    </div>
                    <div class="input-field">
                        <input type="password" id="txtContrasena" name="txtContrasena" required>
                        <label>Contraseña</label>
                    </div>
                    <div class="input-field">
                        <input type="tel" maxlength="8" id="txtDNI" name="txtDNI" required>
                        <label>Documento de identidad</label>
                    </div>
                    <div class="input-field">
                        <input type="text" id="txtNombre" name="txtNombre" required>
                        <label>Nombre</label>
                    </div>
                    <div class="input-field">
                        <input type="text" id="txtApellido" name="txtApellido" required>
                        <label>Apellidos</label>
                    </div>
                    <div class="input-field">
                        <input type="tel" maxlength="9" id="txtTelefono" name="txtTelefono" required>
                        <label>Telefono</label>
                    </div>
                    <input type="hidden" id="txtTipo" name="txtTipo" value="Cliente">
                    <input type="hidden" id="txtEstado" name="txtEstado" value="Activo">
                    <input type="hidden" id="fechaActual" name="fechaActual">
                    <div class="policy-text">
                        <input type="checkbox" id="policy" required >
                        <label for="policy">
                            Estoy deacuerdo con los
                            <a href="terminosYcondiciones.jsp" class="option">Términos y condiciones</a>
                        </label>
                    </div>
                    <button id="registro-btn" type="submit">Registrate</button>
                </form>
                <div class="bottom-link">
                    ¿Ya eres parte de nosotros? 
                    <a href="#" id="login-link">Inicia Sesión</a>
                </div>
            </div>
        </div>
    </div>
    <script>
        //Fecha Actual
        document.addEventListener("DOMContentLoaded", function () {
            // Obtén la fecha actual
            var fechaHoy = new Date();

            // Formatea la fecha como "YYYY-MM-DD" (puedes ajustar el formato según tus necesidades)
            var fechaFormateada = fechaHoy.getFullYear() + "-" + (fechaHoy.getMonth() + 1).toString().padStart(2, "0") + "-" + fechaHoy.getDate().toString().padStart(2, "0");

            // Establece la fecha formateada como valor del campo de entrada oculto
            document.getElementById("fechaActual").value = fechaFormateada;
        });
        
    </script>
    <script src="js/loginScript.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>

</body>
</html>