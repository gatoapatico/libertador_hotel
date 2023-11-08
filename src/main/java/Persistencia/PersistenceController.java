package Persistencia;

import Modelo.*;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PersistenceController {

    UsuarioJpaController usuJPA = new UsuarioJpaController();
    ServicioJpaController serviJPA=new ServicioJpaController();
    CategoriaJpaController cateJPA=new CategoriaJpaController();
    HabitacionJpaController habiJPA=new HabitacionJpaController();

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
    public Servicio buscarServicioPorNombre(String nombre) {
        return serviJPA.buscarServicioPorNombre(nombre);
    }
    // Metodos categorias
    public void crearCategoria(Categoria cate) {
        cateJPA.create(cate);
    }
    
    public List<Categoria> traerCategorias() {
        return cateJPA.findCategoriaEntities();
    }
    
    public void borrarCategoria(int id_eliminar) {
        try {
            cateJPA.destroy(id_eliminar);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Categoria traerCategoria(int id_editar) {
        return cateJPA.findCategoria(id_editar);
    }

    public void editarCategoria(Categoria cate) {
        try {
            cateJPA.edit(cate);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cambiarEstadoCategoria(int id) {
        try {
            cateJPA.cambiarEstadoCategoria(id);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Categoria> buscarCategoriasPorNombre(String nombre) {
        try {
            return cateJPA.buscarCategoriasPorNombre(nombre);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
            return null; // 
        }
    }
    public Categoria buscarCategoriaPorNombre(String nombre) {
        return cateJPA.buscarCategoriaPorNombre(nombre);
    }
    //Metodos habitaciones
    
    public void crearHabitacion(Habitacion habi) {
        habiJPA.create(habi);
    }
    
    public List<Habitacion> traerHabitaciones() {
        return habiJPA.findHabitacionEntities();
    }
    
    public void borrarHabitacion(int id_eliminar) {
        try {
            habiJPA.destroy(id_eliminar);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Habitacion traerHabitacion(int id_editar) {
        return habiJPA.findHabitacion(id_editar);
    }

    public void editarHabitacion(Habitacion habi) {
        try {
            habiJPA.edit(habi);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cambiarEstadoHabitacion(int id) {
        try {
            habiJPA.cambiarEstadoHabitacion(id);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Habitacion> buscarHabitacionesPorNumeroHabitacion(int numero) {
        try {
            return habiJPA.buscarHabitacionesPorNumeroHabitacion(numero);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
            return null; // 
        }
    }
    public Habitacion buscarHabitacionPorNumeroHabitacion(int numero) {
        return habiJPA.buscarHabitacionPorNumeroHabitacion(numero);
    }
}
