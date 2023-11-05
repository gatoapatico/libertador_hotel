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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvUsuarios", urlPatterns = {"/SvUsuarios"})
public class SvUsuarios extends HttpServlet {

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
                List<Usuario> listaUsuarios = new ArrayList<>();
                listaUsuarios = control.traerUsuarios();

                misesion = request.getSession();
                misesion.setAttribute("listaUsuarios", listaUsuarios);

                response.sendRedirect("Administrador/usuarios.jsp");

                break;
            case "Modificar":
                int id_editar = Integer.parseInt(request.getParameter("Id"));
                Usuario usu = control.traerUsuario(id_editar);

                misesion = request.getSession();
                misesion.setAttribute("DatoUsuarioEditar", usu);

                // Redirige a la página de usuarios.jsp
                response.sendRedirect("Administrador/usuarios.jsp");
                break;
            case "Eliminar":
                int id_eliminar = Integer.parseInt(request.getParameter("Id"));
                usu = control.traerUsuario(id_eliminar);
                control.cambiarEstadoUsuario(id_eliminar);
                response.sendRedirect("Administrador/usuarios.jsp");
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("txtEmail");
        String contrasena = request.getParameter("txtContrasena");
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        int telefono = Integer.parseInt(request.getParameter("txtTelefono"));
        String tipo = request.getParameter("txtTipo");

        Usuario usu = new Usuario();
        usu.setEmail(email);
        usu.setContrasena(contrasena);
        usu.setNombre(nombre);
        usu.setApellido(apellido);
        usu.setTelefono(telefono);
        usu.setTipo(tipo);

        control.editarUsuario(usu);
        response.sendRedirect("SvUsuarios?Op=Listar");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
