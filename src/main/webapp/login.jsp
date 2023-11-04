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
                <form class="login-form" method="post" action="SvLogin">
                    <div class="input-field">
                        <input type="text" name="txtNombre" >
                        <label>Email</label>
                    </div>
                    <div class="input-field">
                        <input type="password" name="txtContra">
                        <label>Contraseña</label>
                    </div>
                    <br>
                    <div class="captcha">
                        <label for="captcha-input">Captcha</label>
                        <div class="preview"></div>
                        <div class="captcha-form">
                            <input type="text" id="captcha-form" placeholder="Ingresa el captcha" >
                            <button class="captcha-refresh">
                                <i class="bx bx-refresh icon"></i>
                            </button>
                        </div>
                    </div>
               
                    <a href="#" class="forgot-pass-link">¿Olvido su contraseña?</a>
                    <input type="submit" id="login-btn" name="btnIngresar" value="Inicia sesion"></input>
                </form>

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
                <form action="#">
                    <div class="input-field">
                        <input type="email" required>
                        <label>Email</label>
                    </div>
                    <div class="input-field">
                        <input type="password" required>
                        <label>Contraseña</label>
                    </div>
                    <div class="input-field">
                        <input type="tel" maxlength="8" required>
                        <label>Documento de identidad</label>
                      </div>
                    <div class="input-field">
                        <input type="text" required>
                        <label>Nombre</label>
                    </div>
                    <div class="input-field">
                        <input type="text" required>
                        <label>Apellidos</label>
                    </div>
                    <div class="input-field">
                        <input type="tel" maxlength="9" required>
                        <label>Telefono</label>
                    </div>
                    <div class="policy-text">
                        <input type="checkbox" id="policy" required >
                        <label for="policy">
                            Estoy deacuerdo con los
                            <a href="terminosYcondiciones.html" class="option">Términos y condiciones</a>
                        </label>
                    </div>
                    <button type="submit">Registrate</button>
                </form>
                <div class="bottom-link">
                    ¿Ya eres parte de nosotros? 
                    <a href="#" id="login-link">Inicia Sesión</a>
                </div>
            </div>
        </div>
    </div>
    <script src="js/loginScript.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>

</body>
</html>