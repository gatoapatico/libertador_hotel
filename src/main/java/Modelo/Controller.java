
package Modelo;

import Persistencia.PersistenceController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


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
   
}