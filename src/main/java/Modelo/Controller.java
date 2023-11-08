
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
    //Seccion servicios
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
    public Servicio buscarServicioPorNombre(String nombre) {
        return controlP.buscarServicioPorNombre(nombre);
    }
    //Seccion categoria
    public void crearCategoria(Categoria cate){
        controlP.crearCategoria(cate);
    }
    
    public List<Categoria> traerCategorias(){
        return controlP.traerCategorias();
    }
    
    public void borrarCategoria(int id_eliminar) {
        controlP.borrarCategoria(id_eliminar);
    }

    public Categoria traerCategoria(int id_editar) {
        return controlP.traerCategoria(id_editar);
    }

    public void editarCategoria(Categoria cate) {
        controlP.editarCategoria(cate);
    }
    public void cambiarEstadoCategoria(int id_editar) {
        controlP.cambiarEstadoCategoria(id_editar);
    }
    
    public List<Categoria> buscarCategoriasPorNombre(String nombre){
        return controlP.buscarCategoriasPorNombre(nombre);
    }
    public Categoria buscarCategoriaPorNombre(String nombre) {
        return controlP.buscarCategoriaPorNombre(nombre);
    }
     
    //Seccion Habitacion
    public void crearHabitacion(Habitacion habi){
        controlP.crearHabitacion(habi);
    }
    
    public List<Habitacion> traerHabitaciones(){
        return controlP.traerHabitaciones();
    }
    
    public void borrarHabitacion(int id_eliminar) {
        controlP.borrarHabitacion(id_eliminar);
    }

    public Habitacion traerHabitacion(int id_editar) {
        return controlP.traerHabitacion(id_editar);
    }

    public void editarHabitacion(Habitacion habi) {
        controlP.editarHabitacion(habi);
    }
    public void cambiarEstadoHabitacion(int id_editar) {
        controlP.cambiarEstadoHabitacion(id_editar);
    }
    
    public List<Habitacion> buscarHabitacionesPorNumeroHabitacion(int numero){
        return controlP.buscarHabitacionesPorNumeroHabitacion(numero);
    }
    public Habitacion buscarHabitacionPorNumeroHabitacion(int numero) {
        return controlP.buscarHabitacionPorNumeroHabitacion(numero);
    }
}
