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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PruebaCategoria", urlPatterns = {"/PruebaCategoria"})
public class PruebaCategoria extends HttpServlet {

    Controller control = new Controller();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String op = request.getParameter("Op");
        int idservi;
        Servicio servicio;

        switch (op) {
            case "Listar":
                List<Categoria> listaCategorias = new ArrayList<>();
                listaCategorias = control.traerCategorias();
                session.setAttribute("listaCategorias", listaCategorias);
                request.getRequestDispatcher("Administrador/Pruebacategorias.jsp").forward(request, response);

                break;

            case "AgregarServicios":
                idservi = Integer.parseInt(request.getParameter("id"));
                servicio = control.traerServicio(idservi);

                if (servicio != null) {
                    List<Servicio> listaServiciosSeleccionados = (List<Servicio>) session.getAttribute("serviciosSeleccionados");

                    if (listaServiciosSeleccionados == null) {
                        listaServiciosSeleccionados = new ArrayList<>();
                    }
                    listaServiciosSeleccionados.add(servicio);
                    // Guardar la lista de servicios seleccionados en la sesión
                    session.setAttribute("serviciosSeleccionados", listaServiciosSeleccionados);
                    request.getRequestDispatcher("PruebaCategoria?Op=Listar").forward(request, response);
                }
                break;
            case "Eliminar":
                int id_eliminar = Integer.parseInt(request.getParameter("Id"));
                Categoria cate = control.traerCategoria(id_eliminar);
                control.cambiarEstadoCategoria(id_eliminar);
                request.getRequestDispatcher("PruebaCategoria?Op=Listar").forward(request, response);
                break;
            case "Nuevo":
                int id_nuevo = Integer.parseInt(request.getParameter("Id"));
                cate = null;
                session = request.getSession();
                session.setAttribute("DatoCategoriaEditar", cate);
                request.getRequestDispatcher("PruebaCategoria?Op=Listar").forward(request, response);
                break;
            case "Modificar":
                int id_editar = Integer.parseInt(request.getParameter("Id"));
                cate = control.traerCategoria(id_editar);

                session.setAttribute("serviciosSeleccionadosModificar", cate);
                request.getRequestDispatcher("PruebaCategoria?Op=Listar").forward(request, response);
                break;
            case "AgregarServicioModificar":
                int servicioId = Integer.parseInt(request.getParameter("servicioId"));
                Categoria categoriaModificar = (Categoria) session.getAttribute("serviciosSeleccionadosModificar");
                servicio = control.traerServicio(servicioId);

                if (categoriaModificar != null && servicio != null) {
                    // Agrega el servicio a la categoría
                    categoriaModificar.getServicios().add(servicio);

                    // Puedes redirigir a la página de listado inmediatamente
                    request.getRequestDispatcher("PruebaCategoria?Op=Listar").forward(request, response);
                }
                break;

           /* case "AgregarServicioModificar":
              
                int id_editar = Integer.parseInt(request.getParameter("Id"));
                cate = control.traerCategoria(id_editar);
                session.setAttribute("serviciosSeleccionadosModificar", cate);
                request.getRequestDispatcher("PruebaCategoria?Op=Listar").forward(request, response);

                int servicioId = Integer.parseInt(request.getParameter("servicioId"));
                //Categoria categoriaModificar = (Categoria) session.getAttribute("serviciosSeleccionadosModificar");
                servicio = control.traerServicio(servicioId);

                if (cate != null && servicio != null) {
                    // Agrega el servicio a la categoría
                    cate.getServicios().add(servicio);

                    // Puedes redirigir a la página de listado inmediatamente
                    request.getRequestDispatcher("PruebaCategoria?Op=Listar").forward(request, response);
                } else {
                    // Manejo de errores si es necesario
                }
                break;*/

            default:
                throw new AssertionError();
        }
        // Resto de la lógica
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
        //List<Servicio> listaServiciosSeleccionados = (List<Servicio>) misesion.getAttribute("serviciosSeleccionadosMo");
        Servicio servis=new Servicio();
        servis=control.traerServicio(2);
        List<Servicio> listaServiciosSeleccionados= new ArrayList<>();
        listaServiciosSeleccionados.add(servis);
        

        Categoria cate = new Categoria();
        cate.setNombre(nombre);
        cate.setCantPersonas(cantidad);
        cate.setServicios(listaServiciosSeleccionados);

        // Itera a través de los nombres y asocia los servicios con la categoría
       /* if (listaServiciosSeleccionados != null) {
            double sumaCostoServicios = 0.0;
            for (Servicio servicio : listaServiciosSeleccionados) {
                int idServicio = servicio.getId(); // Obtén el ID del servicio directamente
                sumaCostoServicios += servicio.getCosto();
                Servicio serviObj = control.traerServicio(idServicio);
                serviciosAsociados.add(serviObj);

            }
            cate.setCostoServicios(sumaCostoServicios);
            cate.setServicios(serviciosAsociados);
        }*/
        misesion.removeAttribute("serviciosSeleccionadosMo");
        control.crearCategoria(cate);
        response.sendRedirect("PruebaCategoria?Op=Listar");

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
            response.sendRedirect("PruebaCategoria?Op=Listar");
        } else {
            if (fechaActual != null) {
                cate.setFechaAlta(fechaActual);
            }
            misesion.removeAttribute("serviciosSeleccionados");
            control.crearCategoria(cate);
            response.sendRedirect("PruebaCategoria?Op=Listar");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
