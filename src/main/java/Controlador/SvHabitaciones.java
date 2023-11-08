package Controlador;

import Modelo.Categoria;
import Modelo.Controller;
import Modelo.Habitacion;
import Modelo.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name = "SvHabitaciones", urlPatterns = {"/SvHabitaciones"})
public class SvHabitaciones extends HttpServlet {

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
                List<Habitacion> listaHabitaciones = new ArrayList<>();
                listaHabitaciones = control.traerHabitaciones();
                misesion = request.getSession();
                misesion.setAttribute("listaHabitaciones", listaHabitaciones);
                response.sendRedirect("Administrador/habitaciones.jsp");

                break;
            case "Modificar":
                int id_editar = Integer.parseInt(request.getParameter("Id"));
                Habitacion habi = control.traerHabitacion(id_editar);

                misesion = request.getSession();
                misesion.setAttribute("DatoHabitacionoEditar", habi);
                response.sendRedirect("SvHabitaciones?Op=Listar");
                break;
            case "Eliminar":
                int id_eliminar = Integer.parseInt(request.getParameter("Id"));
                control.cambiarEstadoHabitacion(id_eliminar);
                response.sendRedirect("SvHabitaciones?Op=Listar");
                break;
            case "Nuevo":
                int id_nuevo = Integer.parseInt(request.getParameter("Id"));
                habi = null;

                misesion = request.getSession();
                misesion.setAttribute("DatoHabitacionoEditar", habi);
                response.sendRedirect("SvHabitaciones?Op=Listar");

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
            //actualizarHabitacion(request, misesion, response);
        } else if (btnCrear != null) {
            crearCategoria(request, misesion, response);
        }
    }
     private void crearCategoria(HttpServletRequest request, HttpSession misesion, HttpServletResponse response) throws IOException {
        int numeroHabitacion=Integer.parseInt(request.getParameter("numberHabitacion"));
        double precioHabitacion= Double.parseDouble(request.getParameter("precio"));
        int tipo =Integer.parseInt(request.getParameter("Tipo"));
        Categoria tipoHabitacion=control.traerCategoria(tipo);
        String estado = request.getParameter("txtEstado");
        String fechaActualStr = request.getParameter("fechaActual");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = null; // Inicializa la fecha como nula
        try {
            fechaActual = dateFormat.parse(fechaActualStr); // Intenta analizar la fecha
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Habitacion habi = new Habitacion();
        //habi.setCostohabitacion(precioHabitacion);
        habi.setNumHabitacion(numeroHabitacion);
        habi.setTipoHabitacion(tipoHabitacion);
        habi.setEstado(estado);

        double costototalhabitacion = 0.0;
        double costoServicios=tipoHabitacion.getCostoServicios();
        costototalhabitacion=costoServicios+precioHabitacion;
        habi.setCostohabitacion(costototalhabitacion);

        
        List<Habitacion> habitacionesrepetidas = control.buscarHabitacionesPorNumeroHabitacion(numeroHabitacion);
        if (habitacionesrepetidas != null && !habitacionesrepetidas.isEmpty()) {
            String errorMessage = "La habitacion ya existe"; // Mensaje de error
            request.getSession().setAttribute("error", errorMessage); // Guarda el mensaje de error en la sesión
            response.sendRedirect("SvHabitaciones?Op=Listar");
        } else {
            if (fechaActual != null) {
                habi.setFechaAlta(fechaActual);
            }

            control.crearHabitacion(habi);
            response.sendRedirect("SvHabitaciones?Op=Listar");
        }
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
