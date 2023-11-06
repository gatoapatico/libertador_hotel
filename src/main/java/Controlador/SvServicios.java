package Controlador;

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

@WebServlet(name = "SvServicios", urlPatterns = {"/SvServicios"})
public class SvServicios extends HttpServlet {

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
                List<Servicio> listaServicios = new ArrayList<>();
                listaServicios = control.traerServicios();
                misesion = request.getSession();
                misesion.setAttribute("listaServicios", listaServicios);
                response.sendRedirect("Administrador/servicios.jsp");

                break;
            case "Modificar":
                int id_editar = Integer.parseInt(request.getParameter("Id"));
                Servicio servi = control.traerServicio(id_editar);

                misesion = request.getSession();
                misesion.setAttribute("DatoServicioEditar", servi);
                response.sendRedirect("SvServicios?Op=Listar");
                break;
            case "Eliminar":
                int id_eliminar = Integer.parseInt(request.getParameter("Id"));
                servi = control.traerServicio(id_eliminar);
                control.cambiarEstadoServicio(id_eliminar);
                response.sendRedirect("SvServicios?Op=Listar");
                break;
            case "Nuevo":
                int id_nuevo = Integer.parseInt(request.getParameter("Id"));
                servi = null;

                misesion = request.getSession();
                misesion.setAttribute("DatoServicioEditar", servi);
                response.sendRedirect("SvServicios?Op=Listar");

                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String btnCrear = request.getParameter("btnCrear");
        String btnActualizar = request.getParameter("btnActualizar");
        if (btnCrear != null) {
            String nombre = request.getParameter("nombre");
            Double precio = Double.parseDouble(request.getParameter("precio"));
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

            
            Servicio servi = new Servicio();
            servi.setNombre(nombre);
            servi.setCosto(precio);
            servi.setEstado(estado);

            // Verifica si el usuario está repetido
            List<Servicio> serviciosRepetidos = control.buscarServiciosPorNombre(nombre);

            if (!serviciosRepetidos.isEmpty()) {
                String errorMessage = "El servicio ya existe"; // Mensaje de error
                request.getSession().setAttribute("error", errorMessage); // Guarda el mensaje de error en la sesión
                response.sendRedirect("SvServicios?Op=Listar");
            } else {
                if (fechaActual != null) {
                    servi.setFechaAlta(fechaActual);
                }
                control.crearServicio(servi);
                response.sendRedirect("SvServicios?Op=Listar");
            }

        } else if (btnActualizar != null) {
            int idServicio = Integer.parseInt(request.getParameter("Id"));
            Servicio servicioEditar = control.traerServicio(idServicio);

            // Actualiza los datos del usuario con los valores del formulario
            servicioEditar.setNombre(request.getParameter("nombre"));
            servicioEditar.setCosto(Double.parseDouble(request.getParameter("precio")));
 
         
            control.editarServicio(servicioEditar);

            // Redirige a la página de usuarios.jsp
            response.sendRedirect("SvServicios?Op=Listar");

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
