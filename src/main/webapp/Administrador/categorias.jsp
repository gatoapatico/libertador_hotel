<%@include file="include/headerAdministrador.jsp" %>
<div class="navbar">
    <i class='bx bx-menu'></i>
    <div class="logo"><a href="#">Hotel El Libertador</a></div>
    <div class="nav-links">
        <div class="sidebar-logo">
            <span class="logo-name">Administracion</span>
            <i class='bx bx-x'></i>
        </div>
        <ul class="links">
            <li>
                <a href="#">Habitaciones</a>
                <i class='bx bxs-chevron-down habitacion-arrow arrow  '></i>
                <ul class="habitacion-sub-menu sub-menu">
                    <li><a href="#">Crear habitacion</a></li>
                    <li><a href="#">Modificar Habitacion</a></li>
                    <li><a href="#">Habitacion</a></li>

                </ul>
            </li>
            <li>
                <a href="#">Salones</a>
                <i class='bx bxs-chevron-down salon-arrow arrow '></i>
                <ul class="salon-sub-menu sub-menu">
                    <li><a href="#">Dynamic Clock</a></li>
                    <li><a href="#">Form Validation</a></li>
                    <li><a href="#">Card Slider</a></li>
                    <li><a href="#">Complete Website</a></li>
                </ul>
            </li>
            <li>
                <a href="#">Categorias</a>
                <i class='bx bxs-chevron-down categoria-arrow arrow '></i>
                <ul class="categoria-sub-menu sub-menu">
                    <li><a href="#">Dynamic Clock</a></li>
                    <li><a href="#">Form Validation</a></li>
                    <li><a href="#">Card Slider</a></li>
                    <li><a href="#">Complete Website</a></li>
                </ul>
            </li>
            <li>
                <a href="#">Servicios</a>
                <i class='bx bxs-chevron-down servicio-arrow arrow '></i>
                <ul class="servicio-sub-menu sub-menu">
                    <li><a href="#">Dynamic Clock</a></li>
                    <li><a href="#">Form Validation</a></li>
                    <li><a href="#">Card Slider</a></li>
                    <li><a href="#">Complete Website</a></li>
                </ul>
            </li>
            <li>
                <a href="#">Usuarios</a>
                <i class='bx bxs-chevron-down usuario-arrow arrow '></i>
                <ul class="usuario-sub-menu sub-menu">
                    <li><a href="#">Dynamic Clock</a></li>
                    <li><a href="#">Form Validation</a></li>
                    <li><a href="#">Card Slider</a></li>
                    <li><a href="#">Complete Website</a></li>
                </ul>
            </li>
            <li>
                <a href="#">Log Out</a>
                <i class='bx bxs-user logout-arrow arrow '></i>
                <ul class="logout-sub-menu sub-menu">
                    <li><a href="#">Ver Datos</a></li>
                    <li><a href="#">Cerrar Sesion</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
</header>
<div class="container">
    <div class="container-table">	
        <div class="titulo">Categorias de Habitaciones y Salones</div>	
        <table class="table ">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Costo servicios</th>
                    <th>Servicios</th>
                    <th>Cantidad personas</th>
                    <th>Fecha de Creacion</th>
                    <th>Fecha de Baja</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td data-label="id">1</td>
                    <td data-label="nombre">King</td>
                    <td data-label="costo">s/100</td>
                    <td data-label="servicio">
                        <div class="servicios-lista">
                            <div>
                                Desayuno
                                <a class="servicioslista" href="#"><i class="bx bx-x"></i></a>
                            </div>
                            <div>
                                Desayuno
                                <a class="servicioslista" href="#"><i class="bx bx-x"></i></a>
                            </div>
                            <div>
                                Desayuno
                                <a class="servicioslista" href="#"><i class="bx bx-x"></i></a>
                            </div>
                        </div>
                    </td>                        
                    <td data-label="cantidad">2</td>
                    <td data-label="fech de Alta">23-05-2023</td>
                    <td data-label="fecha de Baja">23-05-2023</td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="#" id="boton1" onclick="toggleColor(this)">Desactivar</a>
                        <a class="buttonTabla" href="#" >Modificar</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="form-container">
        <form action="">
            <h2>Crea-Modifica Categorias</h2>
            <div class="form-group nombre">
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" placeholder="Ingrese el nombre de la categoria" required>
            </div>
            <div class="form-group cantidad">
                <label for="cantidad">Cantidad</label>
                <input type="number" id="cantidad" placeholder="Ingrese la cantidad de personas" required>
            </div>
            <div class="form-group servicios">
                <label for="servicios">Servicios</label>
                <select id="servicios" required>
                    <option value="" selected disabled >Seleccione los servicios</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            <textarea class="form-group" name="serviciosSelecionados" ></textarea>
            <div class="form-group dechaAlta">
                <label for="dechaAlta">Fecha de Alta</label>
                <input type="date" id="dechaAlta" placeholder="Seleccione la fecha de hoy" required>
            </div>
            <div class="form-group submit-btn">
                <input type="submit" value="Crear/Actualizar">
            </div>
        </form>
    </div>
</div>
<%@include file="include/footerAdministrador.jsp" %>