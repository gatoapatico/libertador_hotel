/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Categoria;
import Modelo.Controller;
import Modelo.Servicio;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvCategorias", urlPatterns = {"/SvCategorias"})
public class SvCategorias extends HttpServlet {

    Controller control = new Controller();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();
        String Op = request.getParameter("Op");
        switch (Op) {
            case "Listar":
                List<Categoria> listaCategorias = new ArrayList<>();
                listaCategorias = control.traerCategorias();
                misesion = request.getSession();
                misesion.setAttribute("listaCategorias", listaCategorias);
                response.sendRedirect("Administrador/categorias.jsp");

                break;
            case "Modificar":
                int id_editar = Integer.parseInt(request.getParameter("Id"));
                Categoria cate = control.traerCategoria(id_editar);

                misesion = request.getSession();
                misesion.setAttribute("DatoCategoriaEditar", cate);

                response.sendRedirect("SvCategorias?Op=Listar");
                break;
            case "Eliminar":
                int id_eliminar = Integer.parseInt(request.getParameter("Id"));
                cate = control.traerCategoria(id_eliminar);
                control.cambiarEstadoCategoria(id_eliminar);
                response.sendRedirect("SvCategorias?Op=Listar");
                break;
            case "Nuevo":
                int id_nuevo = Integer.parseInt(request.getParameter("Id"));
                cate = null;
                misesion = request.getSession();
                misesion.setAttribute("DatoCategoriaEditar", cate);
                response.sendRedirect("SvCategorias?Op=Listar");
                break;
            case "AgregarServicios":
                int idservi = Integer.parseInt(request.getParameter("id"));
                Servicio servicio = control.traerServicio(idservi);

                if (servicio != null) {
                    List<Servicio> listaServiciosSeleccionados = (List<Servicio>) misesion.getAttribute("serviciosSeleccionados");

                    if (listaServiciosSeleccionados == null) {
                        listaServiciosSeleccionados = new ArrayList<>();
                    }
                    listaServiciosSeleccionados.add(servicio);
                    // Guardar la lista de servicios seleccionados en la sesión
                    misesion.setAttribute("serviciosSeleccionados", listaServiciosSeleccionados);
                    response.sendRedirect("SvCategorias?Op=Listar");
                }
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();
        String btnCrear = request.getParameter("btnCrear");
        String btnActualizar = request.getParameter("btnActualizar");

        if (btnActualizar != null) {
            actualizarCategoria(request, misesion, response);
        } else if (btnCrear != null) {
            crearCategoria(request, misesion, response);
        }
    }

    private void actualizarCategoria(HttpServletRequest request, HttpSession misesion, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        List<Servicio> listaServiciosSeleccionados = (List<Servicio>) misesion.getAttribute("serviciosSeleccionados");
        String estado = request.getParameter("txtEstado");
        String fechaActualStr = request.getParameter("fechaActual");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = null; // Inicializa la fecha como nula

        try {
                fechaActual = dateFormat.parse(fechaActualStr); // Intenta analizar la fecha
            } catch (ParseException e) {
                // Maneja la excepción, por ejemplo, muestra un mensaje de error o registra el problema
                e.printStackTrace();
            }
            Categoria cate = new Categoria();
            cate.setNombre(nombre);
            cate.setCantPersonas(cantidad);
            cate.setEstado(estado);
            List<Servicio> serviciosAsociados = new ArrayList<>();

            // Itera a través de los nombres y asocia los servicios con la categoría
            if (listaServiciosSeleccionados != null) {
                double sumaCostoServicios = 0.0;
                for (Servicio servicio : listaServiciosSeleccionados) {
                    int idServicio = servicio.getId(); // Obtén el ID del servicio directamente
                    sumaCostoServicios += servicio.getCosto();
                    Servicio serviObj = control.traerServicio(idServicio);
                    serviciosAsociados.add(serviObj);

                }
                cate.setCostoServicios(sumaCostoServicios);
                cate.setServicios(serviciosAsociados);
            }
            List<Categoria> categoriasRepetidas = control.buscarCategoriasPorNombre(nombre);
            if (!categoriasRepetidas.isEmpty()) {
                String errorMessage = "La categoría ya existe"; // Mensaje de error
                request.getSession().setAttribute("error", errorMessage); // Guarda el mensaje de error en la sesión
                response.sendRedirect("SvCategorias?Op=Listar");
            } else {
                if (fechaActual != null) {
                    cate.setFechaAlta(fechaActual);
                }
                misesion.removeAttribute("serviciosSeleccionados");
                control.crearCategoria(cate);
                response.sendRedirect("SvCategorias?Op=Listar");
            }
    }

    private void crearCategoria(HttpServletRequest request, HttpSession misesion, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        List<Servicio> listaServiciosSeleccionados = (List<Servicio>) misesion.getAttribute("serviciosSeleccionados");
        String estado = request.getParameter("txtEstado");
        String fechaActualStr = request.getParameter("fechaActual");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = null; // Inicializa la fecha como nula
        try {
                fechaActual = dateFormat.parse(fechaActualStr); // Intenta analizar la fecha
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Categoria cate = new Categoria();
            cate.setNombre(nombre);
            cate.setCantPersonas(cantidad);
            cate.setEstado(estado);
            List<Servicio> serviciosAsociados = new ArrayList<>();

            // Itera a través de los nombres y asocia los servicios con la categoría
            if (listaServiciosSeleccionados != null) {
                double sumaCostoServicios = 0.0;
                for (Servicio servicio : listaServiciosSeleccionados) {
                    int idServicio = servicio.getId(); // Obtén el ID del servicio directamente
                    sumaCostoServicios += servicio.getCosto();
                    Servicio serviObj = control.traerServicio(idServicio);
                    serviciosAsociados.add(serviObj);

                }
                cate.setCostoServicios(sumaCostoServicios);
                cate.setServicios(serviciosAsociados);
            }
            List<Categoria> categoriasRepetidas = control.buscarCategoriasPorNombre(nombre);
            if (!categoriasRepetidas.isEmpty()) {
                String errorMessage = "La categoría ya existe"; // Mensaje de error
                request.getSession().setAttribute("error", errorMessage); // Guarda el mensaje de error en la sesión
                response.sendRedirect("SvCategorias?Op=Listar");
            } else {
                if (fechaActual != null) {
                    cate.setFechaAlta(fechaActual);
                }
                misesion.removeAttribute("serviciosSeleccionados");
                control.crearCategoria(cate);
                response.sendRedirect("SvCategorias?Op=Listar");
            }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
