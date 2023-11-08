<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>LIBERTADOR</title>
        <link rel="stylesheet" href="styles.css">
        <link rel="stylesheet" href="css/loginStyle.css">
        <!-- BOOTSTRAP ICONS CDN -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    </head>
    <body>
        <div class="main-reservas">
            <header class="header">
                <a href="index.jsp"><img class="logo" src="assets/images/libertador_logo.png" alt="Libertador Logo" /></a>
                <nav class="navbar">
                    <ul>
                        <li>Mis reservas</li>
                        <li><button class="btn-login"><i class="bi bi-person-fill"></i>INICIAR SESIÓN</button></li>
                    </ul>
                </nav>
            </header>
            <main class="reservas-container">
                <div class="reservas-user">
                    <div class="inputs-user">
                        <div class="input">
                            <i class="bi bi-person-standing"></i>
                            <div class="input-info">
                                <h4>Huéspedes</h4>
                                <p>1 adulto</p>
                            </div>
                        </div>
                        <div class="input">
                            <i class="bi bi-calendar-week-fill"></i>
                            <div class="input-info">
                                <h4>Fecha de entrada</h4>
                                <p>lun, 6 nov 2023</p>
                            </div>
                        </div>
                        <div class="input">
                            <i class="bi bi-calendar-week-fill"></i>
                            <div class="input-info">
                                <h4>Fecha de salida</h4>
                                <p>mar, 7 nov 2023</p>
                            </div>
                        </div>
                    </div>
                    <h2 class="subtitulo">Seleccione una habitación</h2>
                    <div class="habitaciones">
                        <div class="habitacion">
                            <div class="habitacion-imagen">
                                <img src="assets/images/rooms/deluxe-king-1.png" alt="Deluxe, King 1">
                            </div>
                            <div class="habitacion-info">
                                <h3>Deluxe, King 1</h3>
                                <p>Solo quedan 2 habitaciones</p>
                                <ul>
                                    <li>1 cama de tamaño king - De 19 a 22m² / de 205 a 236 pies²</li>
                                    <li>Zona de esta con sofá de una plaza, escritorio con silla y guardarropa y armario clásicos</li>
                                </ul>
                                <button class="btn-detalles">Detalles de la habitación</button>
                                <div class="reserva-info">
                                    <div class="info-adicional">
                                        <button class="btn-terminos">Términos y condiciones</button>
                                        <h4><i class="bi bi-credit-card-fill"></i>Depósito obligatorio</h4>
                                    </div>
                                    <div class="precio-reserva">
                                        <h1>S/228.00</h1>
                                        <p>Por noche</p>
                                        <p>Impuestos y tasas excluidos</p>
                                        <button class="btn-reservar">RESERVAR AHORA</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="habitacion">
                            <div class="habitacion-imagen">
                                <img src="assets/images/rooms/deluxe-queen-2.png" alt="Deluxe, Queen 2">
                            </div>
                            <div class="habitacion-info">
                                <h3>Deluxe, Queen 2</h3>
                                <p>Solo quedan 3 habitaciones</p>
                                <ul>
                                    <li>Baño de marmol</li>
                                    <li>Bañera y ducha por separado</li>
                                </ul>
                                <button class="btn-detalles">Detalles de la habitación</button>
                                <div class="reserva-info">
                                    <div class="info-adicional">
                                        <button class="btn-terminos">Términos y condiciones</button>
                                        <h4><i class="bi bi-credit-card-fill"></i>Depósito obligatorio</h4>
                                    </div>
                                    <div class="precio-reserva">
                                        <h1>S/308.00</h1>
                                        <p>Por noche</p>
                                        <p>Impuestos y tasas excluidos</p>
                                        <button class="btn-reservar">RESERVAR AHORA</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="habitacion">
                            <div class="habitacion-imagen">
                                <img src="assets/images/rooms/standard-doble.jpg" alt="Standard Doble">
                            </div>
                            <div class="habitacion-info">
                                <h3>Standard, Doble</h3>
                                <p></p>
                                <ul>
                                    <li>1 cama 2 plazas + 1 Cama 1.5 plaza</li>
                                    <li>Servicio al cuarto</li>
                                </ul>
                                <button class="btn-detalles">Detalles de la habitación</button>
                                <div class="reserva-info">
                                    <div class="info-adicional">
                                        <button class="btn-terminos">Términos y condiciones</button>
                                        <h4><i class="bi bi-credit-card-fill"></i>Depósito obligatorio</h4>
                                    </div>
                                    <div class="precio-reserva">
                                        <h1>S/158.00</h1>
                                        <p>Por noche</p>
                                        <p>Impuestos y tasas excluidos</p>
                                        <button class="btn-reservar">RESERVAR AHORA</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="habitacion">
                            <div class="habitacion-imagen">
                                <img src="assets/images/rooms/standard-matrimonial.jpg" alt="Standard Matrimonial">
                            </div>
                            <div class="habitacion-info">
                                <h3>Standard, Matrimonial</h3>
                                <p></p>
                                <ul>
                                    <li>1 cama 2 plazas</li>
                                    <li>Agua caliente</li>
                                </ul>
                                <button class="btn-detalles">Detalles de la habitación</button>
                                <div class="reserva-info">
                                    <div class="info-adicional">
                                        <button class="btn-terminos">Términos y condiciones</button>
                                        <h4><i class="bi bi-credit-card-fill"></i>Depósito obligatorio</h4>
                                    </div>
                                    <div class="precio-reserva">
                                        <h1>S/158.00</h1>
                                        <p>Por noche</p>
                                        <p>Impuestos y tasas excluidos</p>
                                        <button class="btn-reservar">RESERVAR AHORA</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="reservas-resumen">
                    <h3>Su estancia</h3>
                    <div class="fechas">
                        <div class="fecha">
                            <p><span>Fecha de entrada</span></p>
                            <p>Después de 15:00</p>
                        </div>
                        <div class="fecha">
                            <p><span>Fecha de salida</span></p>
                            <p>antes de 12:00</p>
                        </div>
                    </div>
                    <p>lun, 6 nov 2023 - mar, 7 nov 2023</p>
                    <p>1 adulto</p>
                    <div class="resumen-total">
                        <p>Total: <span>S/0.00</span></p>
                    </div>
                </div>
            </main>
        </div>
        
        <div class="blur-bg-overlay"></div>
        <div class="form-popup">
            <span class="close-btn bx bx-x">X</span>

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
                        <button id="registro-btn" name="btnRegistrar" type="submit">Registrate</button>
                    </form>
                    <div class="bottom-link">
                        ¿Ya eres parte de nosotros? 
                        <a href="#" id="login-link">Inicia Sesión</a>
                    </div>
                </div>
            </div>
        </div>


        <script src="js/loginScript.js"></script>
        <script src="js/asignarFechaScript.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
    </body>
</html>
