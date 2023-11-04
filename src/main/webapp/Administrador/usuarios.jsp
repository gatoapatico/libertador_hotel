<%@include file="include/headerAdministrador.jsp" %>
<div class="container">
    <div class="container-table">
        <div class="titulo">Usuarios</div>
        <table class="table ">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Contrasena</th>
                    <th>DNI</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Telefono</th>
                    <th>Tipo</th>
                    <th>Fecha de Creacion</th>
                    <th>Fecha de Baja</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td data-label="id">1</td>
                    <td data-label="email">bruno@gmail.com</td>
                    <td data-label="contrasena">admin</td>
                    <td data-label="dni">12345678</td>
                    <td data-label="nombre">bruno</td>
                    <td data-label="apellido">sandoval</td>
                    <td data-label="telefono">987654321</td>
                    <td data-label="tipo">Administrador</td>
                    <td data-label="fech de Alta">23-05-2023</td>
                    <td data-label="fecha de Baja">23-05-2023</td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="#" id="boton1" onclick="toggleColor(this)">Desactivar</a>
                        <a class="buttonTabla" href="#">Modificar</a>
                    </td>
                </tr>

            </tbody>
        </table>
    </div>
    <div class="form-container">
        <form action="">
            <h2>Crea-Modifica Usuarios</h2>
            <div class="form-group numberHabitacion">
                <label for="email">Email</label>
                <input type="email" id="email" placeholder="Ingrese un email" required>
            </div>
            <div class="form-group password">
                <label for="password">Password</label>
                <input type="password" id="password" placeholder="Ingrese una contraseña">
                <i id="pass-toggle-btn" class="bx bx-show"></i>
            </div>
            <div class="form-group dni">
                <label for="dni">DNI</label>
                <input type="number" id="dni" placeholder="Ingrese un numero de DNI" maxlength="8" required>
            </div>
            <div class="form-group nombre">
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" placeholder="Ingrese un nombre" required>
            </div>
            <div class="form-group apellido">
                <label for="apellido">Apellido</label>
                <input type="text" id="apellido" placeholder="Ingrese un apellido" required>
            </div>
            <div class="form-group telefono">
                <label for="telefono">Telefono</label>
                <input type="tel" id="telefono" placeholder="Ingrese un numero de telefono" maxlength="9" required>
            </div>

            <div class="form-group Tipo">
                <label for="Tipo">Tipo de usuario</label>
                <select id="Tipo" required>
                    <option value="" selected disabled>Seleccione el tipo usuario</option>
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
<%@include file="include/footerAdministrador.jsp" %>