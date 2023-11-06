<%@page import="Modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

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
                <%  List<Usuario> listaUsuarios = (List<Usuario>) request.getSession().getAttribute("listaUsuarios");
                    if (listaUsuarios != null) { // Verifica si listaUsuarios no es nulo
                        for (Usuario usu : listaUsuarios) {
                %>
                <tr>
                    <td data-label="id"><%= usu.getId()%></td>
                    <td data-label="email"><%= usu.getEmail()%></td>
                    <td data-label="contrasena"><%= usu.getContrasena()%></td>
                    <td data-label="dni"><%= usu.getDNI()%></td>
                    <td data-label="nombre"><%= usu.getNombre()%></td>
                    <td data-label="apellido"><%= usu.getApellido()%></td>
                    <td data-label="telefono"><%= usu.getTelefono()%></td>
                    <td data-label="tipo"><%= usu.getTipo()%></td>
                    <td data-label="fecha de Alta">
                        <%
                            Date fechaAlta = usu.getFechaAlta();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String fechaAltaFormateada = dateFormat.format(fechaAlta);
                            out.print(fechaAltaFormateada);
                        %>
                    </td>
                    <td data-label="fecha de Baja">
                        <%
                            Date fechaBaja = usu.getFechaBaja();
                            if (fechaBaja != null) {
                                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                                String fechaBajaFormateada = dateFormat1.format(fechaBaja);
                                out.print(fechaBajaFormateada);
                            } else {
                                out.print("No hay fecha de baja, usuario activo");
                            }
                        %>
                    </td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="../SvUsuarios?Op=Eliminar&Id=<%=usu.getId()%>" ><%=usu.getEstado()%></a>
                        <a class="buttonTabla" href="../SvUsuarios?Op=Modificar&Id=<%=usu.getId()%>">Modificar</a>
                    </td>
                </tr>
                <%  }
                } else { %>
                <tr>
                    <td data-label="id"></td>
                    <td data-label="email"></td>
                    <td data-label="contrasena"></td>
                    <td data-label="dni"></td>
                    <td data-label="nombre"></td>
                    <td data-label="apellido"></td>
                    <td data-label="telefono"></td>
                    <td data-label="tipo"></td>
                    <td data-label="fecha de Alta"></td>
                    <td data-label="fecha de Baja"></td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="#" >Desactivar</a>
                        <a class="buttonTabla" href="#" >Modificar</a>


                    </td>
                </tr>

                <%}
                %>


            </tbody>
        </table>
    </div>
    <div class="form-container">
        <%  Usuario usu = (Usuario) request.getSession().getAttribute("DatoUsuarioEditar");
            if (usu != null) {%>
        <form action="../SvUsuarios" method="post" id="miFormulario">
            <h2>Modificar Usuario</h2>
            <div class="form-group email">
                <label for="email">Email</label>
                <input type="email" id="txtEmail" name="txtEmail"  value="<%= usu.getEmail()%>" required>
            </div>
            <div class="form-group password">
                <label for="password">Contraseña</label>
                <input type="password" id="txtContrasena" name="txtContrasena" value="<%= usu.getContrasena()%>">
                <i id="pass-toggle-btn" class="bx bx-show"></i>
            </div>
            <div class="form-group nombre">
                <label for="nombre">Nombre</label>
                <input type="text" id="txtNombre" name="txtNombre" value="<%= usu.getNombre()%>" required>
            </div>
            <div class="form-group apellido">
                <label for="apellido">Apellido</label>
                <input type="text" id="txtApellido" name="txtApellido" value="<%= usu.getApellido()%>"  required>
            </div>
            <div class="form-group telefono">
                <label for="telefono">Telefono</label>
                <input type="tel" id="txtTelefono" name="txtTelefono" value="<%= usu.getTelefono()%>" maxlength="9" required>
            </div>

            <div class="form-group Tipo">
                <label for="Tipo">Tipo de usuario</label>
                <select id="txtTipo" name="txtTipo" required>
                    <option value="<%= usu.getTipo()%>" ><%= usu.getTipo()%></option>
                    <option value="Administrador">Administrador</option>
                    <option value="Recepcionista">Recepcionista</option>
                    <option value="Cliente">Cliente</option>
                </select>
            </div>

            <div class="form-group submit-btn">
                <input type="submit" name="btnActualizar" value="Actualizar">
                <input type="hidden" name="Id" value="<%= usu.getId()%>">
            </div>
            <div class="form-group submit-btn">
                <a href="../SvUsuarios?Op=Nuevo&Id=<%=usu.getId()%>">Nuevo Usuario</a>
            </div>


        </form>
        <%} else {%>
        <form action="../SvRegistrarUsuario" method="post">
            <h2>Crear Usuario</h2>
            <div class="form-group email">
                <label for="email">Email</label>
                <input type="email" id="txtEmail" name="txtEmail" placeholder="Ingrese un email" required>
            </div>
            <div class="form-group password">
                <label for="password">Contraseña</label>
                <input type="password" id="txtContrasena" name="txtContrasena" placeholder="Ingrese una contraseña">
                <i id="pass-toggle-btn" class="bx bx-show"></i>
            </div>
            <div class="form-group dni">
                <label for="dni">DNI</label>
                <input type="tel" id="txtDNI" name="txtDNI" placeholder="Ingrese un numero de DNI" maxlength="8" required>
            </div>
            <div class="form-group nombre">
                <label for="nombre">Nombre</label>
                <input type="text" id="txtNombre" name="txtNombre" placeholder="Ingrese un nombre" required>
            </div>
            <div class="form-group apellido">
                <label for="apellido">Apellido</label>
                <input type="text" id="txtApellido" name="txtApellido" placeholder="Ingrese un apellido" required>
            </div>
            <div class="form-group telefono">
                <label for="telefono">Telefono</label>
                <input type="tel" id="txtTelefono" name="txtTelefono" placeholder="Ingrese un numero de telefono" maxlength="9" required>
            </div>

            <div class="form-group Tipo">
                <label for="Tipo">Tipo de usuario</label>
                <select id="txtTipo" name="txtTipo" required>
                    <option value="" selected disabled>Seleccione el tipo usuario</option>
                    <option value="Administrador">Administrador</option>
                    <option value="Recepcionista">Recepcionista</option>
                    <option value="Cliente">Cliente</option>
                </select>
            </div>
            <input type="hidden" id="txtEstado" name="txtEstado" value="Activo">
            <input type="hidden" id="fechaActual" name="fechaActual">

            <div class="form-group submit-btn">
                <input type="submit" name="btnCrear" value="Crear">
            </div>
        </form>
        <% String errorMessage = (String) request.getSession().getAttribute("error"); %>
        <% if (errorMessage != null) {%>
        <div class="error-message">
            <%= errorMessage%>
        </div>
        <% }
            }%>

    </div>
</div>


<%@include file="include/footerAdministrador.jsp" %>