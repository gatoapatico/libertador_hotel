<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>LIBERTADOR</title>
        <link rel="stylesheet" href="styles.css">
        <!-- BOOTSTRAP ICONS CDN -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    </head>
    <body>
        <div class="body-container">
            <header class="header">
                <img class="logo" src="assets/images/libertador_logo.png" alt="Libertador Logo" />
                <nav class="navbar">
                    <ul>
                        <li>Habitaciones</li>
                        <li>Salones</li>
                        <li>Promociones</li>
                        <li>Actividades</li>
                        <li>Galeria</li>
                    </ul>
                </nav>
                <button class="btn-reserva">RESERVA YA</button>
            </header>
            <section class="landing">
                <div class="landing-content">
                    <h1><span>B</span>IENVENIDO AL <span>L</span>IBERTADOR</h1>
                    <h3>descubre el lujo que trasciende el tiempo</h3>
                    <div class="availability-section">
                        <h2>Reserva directa</h2>
<%--                        <input type="date" />--%>
                        <h2 class="date-select"><i class="bi bi-calendar-week-fill"></i>fecha llegada - fecha salida</h2>
                        <h2 class="btn-availability">Comprobar disponibilidad</h2>
                    </div>
                </div>
            </section>
            <section class="services">
                <h1>Nuestros servicios</h1>
                <div class="services-cards">
                    <div class="service">
                        <img src="assets/images/services/24-hour.png" alt="24 horas" draggable="false"/>
                        <h3>Recepción 24h</h3>
                    </div>
                    <div class="service">
                        <img src="assets/images/services/icon-breakfast.png" alt="24 horas" draggable="false"/>
                        <h3>Desayuno</h3>
                    </div>
                    <div class="service">
                        <img src="assets/images/services/icon-restaurant.png" alt="24 horas" draggable="false"/>
                        <h3>Restaurante</h3>
                    </div>
                    <div class="service">
                        <img src="assets/images/services/icon-security.png" alt="24 horas" draggable="false"/>
                        <h3>Seguridad</h3>
                    </div>
                    <div class="service">
                        <img src="assets/images/services/icon-wifi.png" alt="24 horas" draggable="false"/>
                        <h3>Wifi</h3>
                    </div>
                    <div class="service">
                        <img src="assets/images/services/room_service.png" alt="24 horas" draggable="false"/>
                        <h3>Bar</h3>
                    </div>
                    <div class="service">
                        <img src="assets/images/services/smart-payment.png" alt="24 horas" draggable="false"/>
                        <h3>Pagos digitales</h3>
                    </div>
                </div>
            </section>
            <section class="rooms">
                <h1>Nuestras Habitaciones</h1>
                <div class="room-cards">
                    <div class="room">
                        <img src="assets/images/rooms/deluxe-king-1.png" alt="Deluxe King 1" />
                        <h2>Deluxe, Habitación, 1 King</h2>
                    </div>
                    <div class="room">
                        <img src="assets/images/rooms/deluxe-queen-2.png" alt="Deluxe Queen 2" />
                        <h2>Deluxe, Habitación, 2 Queen</h2>
                    </div>
                    <div class="room">
                        <img src="assets/images/rooms/standard-doble.jpg" alt="Standard Doble" />
                        <h2>Standard, Habitación, Doble</h2>
                    </div>
                    <div class="room">
                        <img src="assets/images/rooms/standard-matrimonial.jpg" alt="Standard Matrimonial" />
                        <h2>Standard, Habitación, Matrimonial</h2>
                    </div>
                </div>
            </section>
            <footer class="footer">
                <div class="footer-content">
                    <div class="footer-section1">
                        <div class="info info1">
                            <img class="logo" src="assets/images/libertador_logo.png" alt="Libertador Logo" />
                            <h3><i class="bi bi-telephone-inbound-fill"></i>+51 942 654 789</h3>
                            <h3><i class="bi bi-envelope-fill"></i>reservas@libertadorhotel.pe</h3>
                            <h3><i class="bi bi-geo-alt-fill"></i>Av. La libertad 472, Lima, Perú</h3>
                        </div>
                        <div class="info info2">
                            <ul>
                                <li>Inicio</li>
                                <li>Filosofia</li>
                                <li>Habitaciones</li>
                                <li>Promociones</li>
                                <li>Contacto</li>
                                <li>Libro de reclamaciones</li>
                            </ul>
                            <ul>
                                <li>Blog</li>
                                <li>Galería</li>
                                <li>Actividades</li>
                                <li>Bar Restaurante</li>
                                <li>Políticas del Hotel</li>
                            </ul>
                        </div>
                        <div class="info info3">
                            <h2>Informes</h2>
                            <input type="text" placeholder="Tus Nombres"/>
                            <input type="email" placeholder="Tu correo electrónico"/>
                            <input type="text" pattern="[1-9]{3,}" placeholder="Tu teléfono"/>
                            <button>Enviar</button>
                        </div>
                    </div>
                    <div class="footer-section2">
                        <div class="info4">
                            <div class="medios-pagos">
                                <img src="assets/images/Visa.png" alt="Visa Logo" />
                                <img src="assets/images/Master-Card.png" alt="Master Card Logo" />
                                <img src="assets/images/Wester-Union.png" alt="Wester Union Logo" />
                                <img src="assets/images/American-Express.png" alt="American Express Logo" />
                            </div>
                            <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d1951.2342049081587!2d-76.93831617289761!3d-12.011239963224858!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9105c5d06dc8b717%3A0x9f9bdb97c1ad6b1f!2sHostal%20Libertador!5e0!3m2!1sen!2spe!4v1699021347924!5m2!1sen!2spe" ></iframe>
                        </div>
                    </div>
                    <h4>© 2023 LIBERTADOR. Todos los derechos reservados.</h4>
                </div>
            </footer>
        </div>
    </body>
</html>