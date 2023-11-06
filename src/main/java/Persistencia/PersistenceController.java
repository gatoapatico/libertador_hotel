package Persistencia;

import Modelo.*;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PersistenceController {

    UsuarioJpaController usuJPA = new UsuarioJpaController();
    ServicioJpaController serviJPA=new ServicioJpaController();

    public void crearUsuario(Usuario usu) {
        usuJPA.create(usu);
    }
    
    public List<Usuario> traerUsuarios() {
        return usuJPA.findUsuarioEntities();
    }
    
    public void borrarUsuario(int id_eliminar) {
        try {
            usuJPA.destroy(id_eliminar);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Usuario traerUsuario(int id_editar) {
        return usuJPA.findUsuario(id_editar);
    }

    public void editarUsuario(Usuario usu) {
        try {
            usuJPA.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cambiarEstadoUsuario(int id) {
        try {
            usuJPA.cambiarEstadoUsuario(id);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Usuario> buscarUsuarioRepetido(String email, int dni) {
        try {
            return usuJPA.buscarUsuariosPorEmailYDNI(email, dni);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
            return null; // 
        }
    }
    //Metodos para Servicios
    public void crearServicio(Servicio servi) {
        serviJPA.create(servi);
    }
    
    public List<Servicio> traerServicios() {
        return serviJPA.findServicioEntities();
    }
    
    public void borrarServicio(int id_eliminar) {
        try {
            serviJPA.destroy(id_eliminar);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Servicio traerServicio(int id_editar) {
        return serviJPA.findServicio(id_editar);
    }

    public void editarServicio(Servicio servi) {
        try {
            serviJPA.edit(servi);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cambiarEstadoServicio(int id) {
        try {
            serviJPA.cambiarEstadoServicio(id);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Servicio> buscarServiciosPorNombre(String nombre) {
        try {
            return serviJPA.buscarServiciosPorNombre(nombre);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
            return null; // 
        }
    }
}
