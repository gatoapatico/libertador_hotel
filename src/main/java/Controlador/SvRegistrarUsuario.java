/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Controller;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvRegistrarUsuario", urlPatterns = {"/SvRegistrarUsuario"})
public class SvRegistrarUsuario extends HttpServlet {

    Controller control = new Controller();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("txtEmail");
        String contrasena = request.getParameter("txtContrasena");
        int dni = Integer.parseInt(request.getParameter("txtDNI"));
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        int telefono = Integer.parseInt(request.getParameter("txtTelefono"));
        String tipo = request.getParameter("txtTipo");
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

        Usuario usu = new Usuario();
        usu.setEmail(email);
        usu.setContrasena(contrasena);
        usu.setDNI(dni);
        usu.setNombre(nombre);
        usu.setApellido(apellido);
        usu.setTelefono(telefono);
        usu.setTipo(tipo);
        usu.setEstado(estado);

        // Verifica si el usuario está repetido
        List<Usuario> usuariosRepetidos = control.buscarUsuarioRepetido(email, dni);

        if (!usuariosRepetidos.isEmpty()) {
            // Usuario repetido, establece un mensaje de error
            request.getSession().setAttribute("registroError", "El usuario ya está registrado.");
            response.sendRedirect("login.jsp");
        } else {
            // Usuario no repetido, crea el usuario y redirige a la página de inicio de sesión
            if (fechaActual != null) {
                usu.setFechaAlta(fechaActual);
            }
            control.crearUsuario(usu);
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
