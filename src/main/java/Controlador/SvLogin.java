package Controlador;

import Modelo.Controller;
import Modelo.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    Controller control = new Controller();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listaUsuarios = control.traerUsuarios();
        HttpSession misesion = request.getSession();
        String usuario = request.getParameter("txtNombre");
        String contrasena = request.getParameter("txtContra");

        boolean usuarioEncontrado = false;
        for (Usuario usu : listaUsuarios) {
            if (usu.getEmail().equals(usuario) && usu.getContrasena().equals(contrasena)) {
                misesion.setAttribute("Email", usuario);
                misesion.setAttribute("tipo", usu.getTipo());
                misesion.setAttribute("Nombre", usu.getNombre());
                response.sendRedirect("SvUsuarios?Op=Listar");
                
                usuarioEncontrado = true;
                break;
            }
        }
        if (!usuarioEncontrado) {
            // Usuario no encontrado, muestra un mensaje de error y redirige de nuevo al inicio de sesión.
            response.sendRedirect("login.jsp?error=Credenciales incorrectas. Intente nuevamente.");
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
