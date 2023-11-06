
package Modelo;

import Persistencia.PersistenceController;
import java.util.List;


public class Controller {
    
    PersistenceController controlP = new PersistenceController();
    
    //Seccion Usuario
    public void crearUsuario(Usuario usu){
        controlP.crearUsuario(usu);
    }
    
    public List<Usuario> traerUsuarios(){
        return controlP.traerUsuarios();
    }
    
    public void borrarUsuario(int id_eliminar) {
        controlP.borrarUsuario(id_eliminar);
    }

    public Usuario traerUsuario(int id_editar) {
        return controlP.traerUsuario(id_editar);
    }

    public void editarUsuario(Usuario usu) {
        controlP.editarUsuario(usu);
    }
    public void cambiarEstadoUsuario(int id_editar) {
        controlP.cambiarEstadoUsuario(id_editar);
    }
    
    public List<Usuario> buscarUsuarioRepetido(String email, int dni){
        return controlP.buscarUsuarioRepetido(email,dni);
    }
    public void crearServicio(Servicio servi){
        controlP.crearServicio(servi);
    }
    
    public List<Servicio> traerServicios(){
        return controlP.traerServicios();
    }
    
    public void borrarServicio(int id_eliminar) {
        controlP.borrarServicio(id_eliminar);
    }

    public Servicio traerServicio(int id_editar) {
        return controlP.traerServicio(id_editar);
    }

    public void editarServicio(Servicio servi) {
        controlP.editarServicio(servi);
    }
    public void cambiarEstadoServicio(int id_editar) {
        controlP.cambiarEstadoServicio(id_editar);
    }
    
    public List<Servicio> buscarServiciosPorNombre(String nombre){
        return controlP.buscarServiciosPorNombre(nombre);
    }
     
}
