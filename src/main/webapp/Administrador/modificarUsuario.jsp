<%@page import="Modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@include file="include/headerAdministrador.jsp" %>
<div class="container">
  
    <div class="form-container">
        <%  Usuario usu = (Usuario)request.getSession().getAttribute("DatoUsuarioEditar");
            if (usu != null) {%>
        <form action="../SvUsuarios" method="post">

            <h2>Modificar Usuario</h2>

            <div class="form-group numberHabitacion">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="<%=usu.getEmail()%>" >
            </div>
            <div class="form-group password">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="<%=usu.getContrasena()%>">
                <i id="pass-toggle-btn" class="bx bx-show"></i>
            </div>
            <div class="form-group dni">
                <label for="dni">DNI</label>
                <input type="tel" id="dni" name="dni" placeholder="<%=usu.getDNI()%>" maxlength="8" >
            </div>
            <div class="form-group nombre">
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" placeholder="<%=usu.getNombre()%>" >
            </div>
            <div class="form-group apellido">
                <label for="apellido">Apellido</label>
                <input type="text" id="apellido" name="apellido" placeholder="<%=usu.getApellido()%>" >
            </div>
            <div class="form-group telefono">
                <label for="telefono">Telefono</label>
                <input type="tel" id="telefono" dame="telefono" placeholder="<%=usu.getTelefono()%>" maxlength="9" >
            </div>

            <div class="form-group Tipo">
                <label for="Tipo">Tipo de usuario</label>
                <select id="Tipo" name="Tipo" required>
                    <option value="" selected disabled><%=usu.getTipo()%></option>
                    <option value="" selected disabled>Seleccione el tipo usuario</option>
                    <option value="Administrador">Administrador</option>
                    <option value="Recepcionista">Recepcionista</option>
                    <option value="Cliente">Cliente</option>
                </select>
            </div>
            <div class="form-group dechaAlta">
                <label for="dechaAlta">Fecha de Alta</label>
                <input type="date" id="dechaAlta" name="dechaAlta" placeholder="<%=usu.getFechaAlta()%>" required>
            </div>
            <input type="hidden" id="estado" name="estado" value="Activo">
            <div class="form-group submit-btn">
                <input type="submit"  value="Actualizar">
            </div>
        </form>
       <%} else{%>
       <p>La lista esta vacia</p>
        <%}%>
    </div>
</div>

<%@include file="include/footerAdministrador.jsp" %>