/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Categoria;
import Modelo.Controller;
import Modelo.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruno Sandoval
 */
public class SvCrearModificarCategorias extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Controller control = new Controller();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvCrearModificarCategorias</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvCrearModificarCategorias at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String btnCrear = request.getParameter("btnCrear");
        String btnActualizar = request.getParameter("btnActualizar");

        if (btnCrear != null) {
            String nombre = request.getParameter("nombre");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            String[] serviciosSeleccionados = request.getParameterValues("serviciosSeleccionados");
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

            // Obtén los nombres de los servicios seleccionados
            // Lista para almacenar los servicios asociados
            List<Servicio> serviciosAsociados = new ArrayList<>();

            // Itera a través de los nombres y asocia los servicios con la categoría
            if (serviciosSeleccionados != null && serviciosSeleccionados.length > 0) {
                serviciosAsociados = new ArrayList<>();
                for (String servicioId : serviciosSeleccionados) {
                    int idServicio = Integer.parseInt(servicioId);
                    Servicio serviObj = control.traerServicio(idServicio);
                    serviciosAsociados.add(serviObj);
                }
                cate.setServicios(serviciosAsociados);
            }
            // Verifica si el usuario está repetido
            List<Categoria> categoriasRepetidas = control.buscarCategoriasPorNombre(nombre);

            if (!categoriasRepetidas.isEmpty()) {
                String errorMessage = "La categoría ya existe"; // Mensaje de error
                request.getSession().setAttribute("error", errorMessage); // Guarda el mensaje de error en la sesión
                response.sendRedirect("SvCategorias?Op=Listar");
            } else {
                if (fechaActual != null) {
                    cate.setFechaAlta(fechaActual);
                }
                control.crearCategoria(cate);
                response.sendRedirect("SvCategorias?Op=Listar");
            }
        } else if (btnActualizar != null) {
            // Lógica para actualizar una categoría (si es necesario)
        }
   
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
