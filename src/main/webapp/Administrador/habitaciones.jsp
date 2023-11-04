<%@include file="../Administrador/include/headerAdministrador.jsp" %>
<div class="container">
    <div class="container-table">	
        <div class="titulo">Habitaciones</div>	
        <table class="table ">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>N Habitacion</th>
                    <th>Costo</th>
                    <th>Costo servicios</th>
                    <th>Servicios</th>
                    <th>Tipo Hab.</th>
                    <th>Max personas</th>
                    <th>Fecha de Creacion</th>
                    <th>Fecha de Baja</th>
                    <th>Cliente Actual</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td data-label="id">1</td>
                    <td data-label="numero">345</td>
                    <td data-label="costo">s/100</td>
                    <td data-label="costo servicios">s/100</td>
                    <td data-label="servicio">desayuno</td>
                    <td data-label="tipo">king</td>
                    <td data-label="numero de personas">2 </td>
                    <td data-label="fech de Alta">23-05-2023</td>
                    <td data-label="fecha de Baja">23-05-2023</td>
                    <td data-label="cliente actual">
                        <input type="text">
                        <a class="buttonTabla" href="#">Actualizar</a>
                        <a class="buttonTabla" href="#">Liberar</a>
                    </td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="#" id="boton1" onclick="toggleColor(this)">Desactivar</a>
                        <a class="buttonTabla" href="#" >Modificar</a>
                    </td>
                </tr>
                <tr>
                    <td data-label="id">2</td>
                    <td data-label="numero">433</td>
                    <td data-label="costo">s/120</td>
                    <td data-label="costo servicios">s/100</td>
                    <td data-label="servicio">desayuno</td>
                    <td data-label="tipo">king</td>
                    <td data-label="numero de personas">2 </td>
                    <td data-label="fech de Alta">23-05-2023</td>
                    <td data-label="fecha de Baja">23-05-2023</td>
                    <td data-label="cliente actual">
                        <input type="text">
                        <a class="buttonTabla" href="#">Actualizar</a>
                        <a class="buttonTabla" href="#">Liberar</a>
                    </td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="#" id="boton1" onclick="toggleColor(this)">Desactivar</a>
                        <a class="buttonTabla" href="#" >Modificar</a>
                    </td>
                </tr>
                <tr>
                    <td data-label="id">3</td>
                    <td data-label="numero">323</td>
                    <td data-label="costo">s/100</td>
                    <td data-label="costo servicios">s/100</td>
                    <td data-label="servicio">desayuno</td>
                    <td data-label="tipo">king</td>
                    <td data-label="numero de personas">2 </td>
                    <td data-label="fech de Alta">23-05-2023</td>
                    <td data-label="fecha de Baja">23-05-2023</td>
                    <td data-label="cliente actual">
                        <input type="text">
                        <a class="buttonTabla" href="#">Actualizar</a>
                        <a class="buttonTabla" href="#">Liberar</a>
                    </td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="#" id="boton1" onclick="toggleColor(this)">Desactivar</a>
                        <a class="buttonTabla" href="#" >Modificar</a>
                    </td>
                </tr>
                <tr>
                    <td data-label="id">4</td>
                    <td data-label="numero">454</td>
                    <td data-label="costo">s/123</td>
                    <td data-label="costo servicios">s/100</td>
                    <td data-label="servicio">desayuno</td>
                    <td data-label="tipo">king</td>
                    <td data-label="numero de personas">2 </td>
                    <td data-label="fech de Alta">23-05-2023</td>
                    <td data-label="fecha de Baja">23-05-2023</td>
                    <td data-label="cliente actual">
                        <input type="text">
                        <a class="buttonTabla" href="#">Actualizar</a>
                        <a class="buttonTabla" href="#">Liberar</a>
                    </td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="#" id="boton1" onclick="toggleColor(this)">Desactivar</a>
                        <a class="buttonTabla" href="#" >Modificar</a>
                    </td>
                </tr>
                <tr>
                    <td data-label="id">5</td>
                    <td data-label="numero">765</td>
                    <td data-label="costo">s/110</td>
                    <td data-label="costo servicios">s/100</td>
                    <td data-label="servicio">desayuno</td>
                    <td data-label="tipo">king</td>
                    <td data-label="numero de personas">2 </td>
                    <td data-label="fech de Alta">23-05-2023</td>
                    <td data-label="fecha de Baja">23-05-2023</td>
                    <td data-label="cliente actual">
                        <input type="text">
                        <a class="buttonTabla" href="#">Actualizar</a>
                        <a class="buttonTabla" href="#">Liberar</a>
                    </td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="#" id="boton1" onclick="toggleColor(this)">Desactivar</a>
                        <a class="buttonTabla" href="#" >Modificar</a>
                    </td>
                </tr>
                <tr>
                    <td data-label="id">6</td>
                    <td data-label="numero">122</td>
                    <td data-label="costo">s/150</td>
                    <td data-label="costo servicios">s/100</td>
                    <td data-label="servicio">desayuno</td>
                    <td data-label="tipo">matrimonial</td>
                    <td data-label="numero de personas">2 </td>
                    <td data-label="fech de Alta">23-05-2023</td>
                    <td data-label="fecha de Baja">23-05-2023</td>
                    <td data-label="cliente actual">
                        <input type="text">
                        <a class="buttonTabla" href="#">Actualizar</a>
                        <a class="buttonTabla" href="#">Liberar</a>
                    </td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="#" id="boton1" onclick="toggleColor(this)">Desactivar</a>
                        <a class="buttonTabla" href="#" >Modificar</a>
                    </td>
                </tr>
                <tr>
                    <td data-label="id">7</td>
                    <td data-label="numero">376</td>
                    <td data-label="costo">s/120</td>
                    <td data-label="costo servicios">s/100</td>
                    <td data-label="servicio">Almuerzo</td>
                    <td data-label="tipo">cama doble</td>
                    <td data-label="numero de personas">2 </td>
                    <td data-label="fech de Alta">23-05-2023</td>
                    <td data-label="fecha de Baja">23-05-2023</td>
                    <td data-label="cliente actual">
                        <input type="text">
                        <a class="buttonTabla" href="#">Actualizar</a>
                        <a class="buttonTabla" href="#">Liberar</a>
                    </td>
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
            <h2>Crea-Modifica Habitaciones</h2>
            <div class="form-group numberHabitacion">
                <label for="numberHabitacion">Número de habitación</label>
                <input type="number" id="numberHabitacion" placeholder="Ingrese numero de habitacion" required>
            </div>
            <div class="form-group precio">
                <label for="precio">Precio de la habitación</label>
                <input type="number" id="precio" placeholder="Ingrese el precio de la habitacion" required>
            </div>
            <div class="form-group Tipo">
                <label for="Tipo">Tipo de habitacion</label>
                <select id="Tipo" required>
                    <option value="" selected disabled >Seleccione el tipo de habitación</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>
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
<%@include file="../Administrador/include/footerAdministrador.jsp" %>