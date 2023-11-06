<%@page import="Modelo.Controller"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Servicio"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Modelo.Categoria"%>
<%@page import="java.util.List"%>
<%@include file="include/headerAdministrador.jsp" %>

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
                    <%  List<Categoria> listaCategorias = (List<Categoria>) request.getSession().getAttribute("listaCategorias");
                        if (listaCategorias != null) {
                            for (Categoria cate : listaCategorias) {
                    %>
                    <td data-label="id"><%=cate.getId()%></td>
                    <td data-label="nombre"><%=cate.getNombre()%></td>
                    <td data-label="costo">s/<%=cate.getCostoServicios()%></td>     
                    <td data-label="servicio">
                        <div class="servicios-lista">
                            <%
                                List<Servicio> servicios = cate.getServicios(); // Obtén la lista de servicios de la categoría
                                for (Servicio servicio : servicios) {
                            %>
                            <div>
                                <%= servicio.getNombre()%> <!-- Imprime el nombre del servicio -->
                                <a class="servicioslista" href="#"><i class="bx bx-x"></i></a>
                            </div>
                            <%
                                }
                            %>
                        </div>
                    </td>
                    <td data-label="cantidad"><%=cate.getCantPersonas()%></td>
                    <td data-label="fech de Alta"><%
                        Date fechaAlta = cate.getFechaAlta();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String fechaAltaFormateada = dateFormat.format(fechaAlta);
                        out.print(fechaAltaFormateada);
                        %></td>
                    <td data-label="fecha de Baja"><%
                        Date fechaBaja = cate.getFechaBaja();
                        if (fechaBaja != null) {
                            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                            String fechaBajaFormateada = dateFormat1.format(fechaBaja);
                            out.print(fechaBajaFormateada);
                        } else {
                            out.print("No hay fecha de baja, servicio activo");
                        }
                        %></td>
                    <td data-label="estado">
                        <a class="buttonTabla" href="../SvCategorias?Op=Eliminar&Id=<%=cate.getId()%>" ><%=cate.getEstado()%></a>
                        <a class="buttonTabla" href="../SvCategorias?Op=Modificar&Id=<%=cate.getId()%>">Modificar</a>
                    </td>
                </tr>
                <%  }
                } else { %>
            <td data-label="id"></td>
            <td data-label="nombre"></td>
            <td data-label="costo">s/</td>
            <td data-label="servicio">
                <div class="servicios-lista">
                    <div>
                        Esta vacio
                        <a class="servicioslista" href="#"><i class="bx bx-x"></i></a>
                    </div>
                </div>
            </td>                        
            <td data-label="cantidad"></td>
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
        <%  Categoria cate = (Categoria) request.getSession().getAttribute("DatoCategoriaEditar");
            if (cate != null) {%>
        <form action="../SvCategorias" method="post">
            <h2>Modifica Categoria</h2>
            <div class="form-group nombre">
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" value="<%=cate.getNombre()%>">
            </div>
            <div class="form-group cantidad">
                <label for="cantidad">Cantidad</label>
                <input type="number" id="cantidad" name="cantidad" value="<%=cate.getCantPersonas()%>">
            </div>
            <div class="form-group servicios">
                <label for="servicios">Servicios</label>
                <select id="servicios" name="servicios" >
                    <option value="" selected disabled >Seleccione los servicios</option>
                    <%  Controller control = new Controller();
                        List<Servicio> listaServicios = new ArrayList<>();
                        listaServicios = control.traerServicios();

                        for (Servicio servi : listaServicios) {
                            if (servi.getEstado().equals("Activo")) {
                    %>
                    <option value="<%= servi.getNombre()%>"><%= servi.getNombre()%></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <textarea class="form-group" name="serviciosSeleccionados" >
                <%
                    List<Servicio> servicios = cate.getServicios(); // Obtén la lista de servicios de la categoría
                    for (Servicio servicio : servicios) {
                %>
                            <div>
                    <%= servicio.getNombre()%> <!-- Imprime el nombre del servicio -->
                                <a class="servicioslista" href="#"><i class="bx bx-x"></i></a>
                            </div>
                <%
                    }
                %>
            </textarea>
            <input type="hidden" id="txtEstado" name="txtEstado" value="Activo">
            <input type="hidden" id="fechaActual" name="fechaActual">
            <div class="form-group submit-btn">
                <input type="submit" name="btnActualizar" value="Actualizar">
                <input type="hidden" name="Id" value="<%= cate.getId()%>">
            </div>
            <div class="form-group submit-btn">
                <a href="../SvCategorias?Op=Nuevo&Id=<%=cate.getId()%>">Nuevo Servicio</a>
            </div>
        </form>
        <%} else {%>
        <form action="../SvCategorias" method="post">
            <h2>Crea Categoria</h2>
            <div class="form-group nombre">
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" placeholder="Ingrese el nombre de la categoria" required>
            </div>
            <div class="form-group cantidad">
                <label for="cantidad">Cantidad</label>
                <input type="number" id="cantidad" name="cantidad" placeholder="Ingrese la cantidad de personas" required>
            </div>
            <div class="form-group servicios">
                <label for="servicios">Servicios</label>
                <select id="servicios" name="serviciosSeleccionados" required>
                    <option value="" selected disabled >Seleccione los servicios</option>
                    <%  Controller control = new Controller();
                        List<Servicio> listaServicios = new ArrayList<>();
                        listaServicios = control.traerServicios();

                        for (Servicio servi : listaServicios) {
                            if (servi.getEstado().equals("Activo")) {
                    %>
                    <option value="<%= servi.getId()%>"><%= servi.getNombre()%></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <div class="form-group selected-services">
                <label for="selectedServices">Servicios Seleccionados</label>
                <textarea id="selectedServices" name="selectedServices" readonly></textarea>
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
<script>
    document.getElementById("servicios").addEventListener("change", function() {
        var selectedServices = document.getElementById("servicios");
        var selectedServicesText = document.getElementById("selectedServices");
        var selectedOptions = selectedServices.selectedOptions;

        var selectedServiceNames = [];
        for (var i = 0; i < selectedOptions.length; i++) {
            selectedServiceNames.push(selectedOptions[i].text);
        }

        selectedServicesText.value = selectedServiceNames.join("\n");
    });
</script>

<%@include file="include/footerAdministrador.jsp" %>