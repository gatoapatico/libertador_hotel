<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Modelo.Servicio"%>
<%@page import="Modelo.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Habitacion"%>
<%@page import="Modelo.Controller"%>
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
                    <%  List<Habitacion> listaHabitaciones = (List<Habitacion>) request.getSession().getAttribute("listaHabitaciones");
                        if (listaHabitaciones != null) {
                            for (Habitacion habi : listaHabitaciones) {
                    %>
                    <td data-label="id"><%=habi.getId()%></td>
                    <td data-label="numero"><%=habi.getNumHabitacion()%></td>
                    <td data-label="costo"><%=habi.getCostohabitacion()%></td>
                    <% Categoria categoriaselecionada = habi.getTipoHabitacion();%>
                    <td data-label="costo servicios"><%=categoriaselecionada.getCostoServicios()%></td>
                    <td data-label="servicio">
                        <div class="servicios-lista">
                            <%List<Servicio> listaServicioHabitacion = categoriaselecionada.getServicios();
                                for (Servicio servi : listaServicioHabitacion) {%>
                            <div>
                                <%= servi.getNombre()%> 
                            </div>

                            <% }%>
                        </div>
                    </td>
                    <td data-label="tipo"><%=categoriaselecionada.getNombre()%></td>
                    <td data-label="numero de personas"><%=categoriaselecionada.getCantPersonas()%></td>
                    <td data-label="fech de Alta"><%
                        Date fechaAlta = habi.getFechaAlta();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String fechaAltaFormateada = dateFormat.format(fechaAlta);
                        out.print(fechaAltaFormateada);
                        %></td>
                    <td data-label="fecha de Baja"><%
                        Date fechaBaja = habi.getFechaBaja();
                        if (fechaBaja != null) {
                            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                            String fechaBajaFormateada = dateFormat1.format(fechaBaja);
                            out.print(fechaBajaFormateada);
                        } else {
                            out.print("No hay fecha de baja, servicio activo");
                        }
                        %></td>
                    <td data-label="cliente actual">
                        <input type="text">
                        <a class="buttonTabla" href="#">Actualizar</a>
                        <a class="buttonTabla" href="#">Liberar</a>
                    </td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="../SvHabitaciones?Op=Eliminar&Id=<%=habi.getId()%>" ><%=habi.getEstado()%></a>
                        <a class="buttonTabla" href="../SvHabitaciones?Op=Modificar&Id=<%=habi.getId()%>">Modificar</a>
                    </td>
                </tr>
                <%  }
                } else { %>
                <tr>
                    <td data-label="id"></td>
                    <td data-label="numero"></td>
                    <td data-label="costo"></td>
                    <td data-label="costo servicios"></td>
                    <td data-label="servicio"></td>
                    <td data-label="tipo"></td>
                    <td data-label="numero de personas"> </td>
                    <td data-label="fech de Alta"></td>
                    <td data-label="fecha de Baja"></td>
                    <td data-label="cliente actual">
                        <input type="text">
                        <a class="buttonTabla" href="#">Actualizar</a>
                        <a class="buttonTabla" href="#">Liberar</a>
                    </td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="#">Eliminar</a>
                        <a class="buttonTabla" href="#">Modificar</a>
                    </td>
                </tr>
                <%  } %>
            </tbody>
        </table>
    </div>
    <div class="form-container">
        <%  Habitacion habi = (Habitacion) request.getSession().getAttribute("DatoHabitacionoEditar");
            if (habi != null) {%>
        <form action="../SvHabitaciones" method="post">
            <h2>Modifica Habitacion</h2>
            <div class="form-group numberHabitacion">
                <label for="numberHabitacion">Número de habitación</label>
                <input type="number" id="numberHabitacion" name="numberHabitacion" value="<%=habi.getNumHabitacion()%>" >
            </div>
            <div class="form-group precio">
                <label for="precio">Precio de la habitación</label>
                <input type="number" id="precio" value="<%=habi.getCostohabitacion()%>">
            </div>
            <div class="form-group Tipo">
                <label for="Tipo">Tipo de habitacion</label>
                <select id="Tipo" name="Tipo">
                    <option value="<%=habi.getTipoHabitacion()%>" selected disabled >Seleccione el tipo de habitación</option>
                    <option value="" selected disabled >Seleccione el tipo de habitación</option>
                    <%Controller control = new Controller();
                        List<Categoria> listaCategoria = control.traerCategorias();

                        for (Categoria cate : listaCategoria) {
                            if (cate.getEstado().equals("Activo")) {%>
                    <option value="<%=cate.getId()%>"><%=cate.getNombre()%></option>
                    <%   }
                        }
                    %>
                </select>
            </div>
            <input type="hidden" id="txtEstado" name="txtEstado" value="Activo">
            <input type="hidden" id="fechaActual" name="fechaActual" value="<%=habi.getFechaAlta()%>">
            <div class="form-group submit-btn">
                <input type="submit" name="btnActualizar" value="Modificar">
            </div>
            <div class="form-group submit-btn">
                <a href="../SvHabitaciones?Op=Nuevo&Id=<%=habi.getId()%>">Nuevo Servicio</a>
            </div>
        </form>
        <%} else {%>
        <form action="../SvHabitaciones" method="post">
            <h2>Crea Habitacion</h2>
            <div class="form-group numberHabitacion">
                <label for="numberHabitacion">Número de habitación</label>
                <input type="number" id="numberHabitacion" name="numberHabitacion" placeholder="Ingrese numero de habitacion" required>
            </div>
            <div class="form-group precio">
                <label for="precio">Precio de la habitación</label>
                <input type="number" id="precio" name="precio" placeholder="Ingrese el precio de la habitacion" required>
            </div>
            <div class="form-group Tipo">
                <label for="Tipo">Tipo de habitacion</label>
                <select id="Tipo" name="Tipo" required>
                    <option value="" selected disabled >Seleccione el tipo de habitación</option>
                    <%Controller control = new Controller();
                        List<Categoria> listaCategoria = control.traerCategorias();

                        for (Categoria cate : listaCategoria) {
                            if (cate.getEstado().equals("Activo")) {%>
                    <option value="<%=cate.getId()%>"><%=cate.getNombre()%></option>
                    <%   }
                        }
                    %>
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
        <%}
            }%>
    </div>
</div>
<%@include file="../Administrador/include/footerAdministrador.jsp" %>