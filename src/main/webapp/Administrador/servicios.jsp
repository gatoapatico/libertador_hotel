<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Modelo.Servicio"%>
<%@page import="java.util.List"%>
<%@include file="include/headerAdministrador.jsp" %>
<div class="container">
    <div class="container-table">	
        <div class="titulo">Servicios</div>	
        <table class="table ">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Costo</th>
                    <th>Fecha de Creacion</th>
                    <th>Fecha de Baja</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <%  List<Servicio> listaServicios = (List<Servicio>) request.getSession().getAttribute("listaServicios");
                    if (listaServicios != null) { 
                        for (Servicio servi : listaServicios) {
                %>
                <tr>
                    <td data-label="id"><%=servi.getId()%></td>
                    <td data-label="nombre"><%=servi.getNombre()%></td>
                    <td data-label="costo"><%=servi.getCosto()%></td>
                    <td data-label="fech de Alta"><%
                            Date fechaAlta = servi.getFechaAlta();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String fechaAltaFormateada = dateFormat.format(fechaAlta);
                            out.print(fechaAltaFormateada);
                        %></td>
                    <td data-label="fecha de Baja"><%
                            Date fechaBaja = servi.getFechaBaja();
                            if (fechaBaja != null) {
                                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                                String fechaBajaFormateada = dateFormat1.format(fechaBaja);
                                out.print(fechaBajaFormateada);
                            } else {
                                out.print("No hay fecha de baja, servicio activo");
                            }
                        %></td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="../SvServicios?Op=Eliminar&Id=<%=servi.getId()%>" ><%=servi.getEstado()%></a>
                        <a class="buttonTabla" href="../SvServicios?Op=Modificar&Id=<%=servi.getId()%>">Modificar</a>
                    </td>
                </tr>
                <%  }
                } else { %>
                <tr>
                    <td data-label="id"></td>
                    <td data-label="nombre"></td>
                    <td data-label="costo"></td>
                    <td data-label="fech de Alta"></td>
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
        <%  Servicio servi = (Servicio) request.getSession().getAttribute("DatoServicioEditar");
            if (servi != null) {%>
            <form action="../SvServicios" method="post">
            <h2>Modificar Servicio</h2>
            <div class="form-group nombre">
                <label for="nombre">Nombre del servicio</label>
                <input type="text" id="nombre" name="nombre" value="<%=servi.getNombre()%>">
            </div>
            <div class="form-group precio">
                <label for="precio">Precio del servicio</label>
                <input type="number" id="precio" min="0" name="precio" value="<%=servi.getCosto()%>">
            </div>
            <input type="hidden" id="txtEstado" name="txtEstado" value="Activo">
            <input type="hidden" id="fechaActual" name="fechaActual">
            <div class="form-group submit-btn">
                <input type="submit" name="btnActualizar" value="Actualizar">
                <input type="hidden" name="Id" value="<%= servi.getId()%>">
            </div>
            <div class="form-group submit-btn">
                <a href="../SvServicios?Op=Nuevo&Id=<%=servi.getId()%>">Nuevo Servicio</a>
            </div>
        </form>
        <%} else {%>
        <form action="../SvServicios" method="post">
            <h2>Crea Servicio</h2>
            <div class="form-group nombre">
                <label for="nombre">Nombre del servicio</label>
                <input type="text" id="nombre" name="nombre" placeholder="Ingrese el nombre del servicio" required>
            </div>
            <div class="form-group precio">
                <label for="precio">Precio del servicio</label>
                <input type="number" id="precio" name="precio" placeholder="Ingrese el precio del servicio" min="0"required>
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