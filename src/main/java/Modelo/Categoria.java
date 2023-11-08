package Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Categoria implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private double costoServicios;
    private int cantPersonas;
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    private String estado;
    
    @ManyToMany(mappedBy = "categorias")
    private List<Servicio> servicios;
    
    
    @ManyToMany
    @JoinTable(
        name = "Categoria_Salon",
        joinColumns = @JoinColumn(name = "id_categoria"),
        inverseJoinColumns = @JoinColumn(name = "id_salon")
    )
    private List<Salon> salones;

    @ManyToMany
    @JoinTable(
        name = "Categoria_Habitacion",
        joinColumns = @JoinColumn(name = "id_categoria"),
        inverseJoinColumns = @JoinColumn(name = "id_habitacion")
    )
    private List<Habitacion> habitaciones;

    public Categoria() {
    }

    public Categoria(int id, String nombre, double costoServicios, int cantPersonas, Date fechaAlta, Date fechaBaja, String estado, List<Servicio> servicios, List<Salon> salones, List<Habitacion> habitaciones) {
        this.id = id;
        this.nombre = nombre;
        this.costoServicios = costoServicios;
        this.cantPersonas = cantPersonas;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.servicios = servicios;
        this.salones = salones;
        this.habitaciones = habitaciones;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCostoServicios() {
        return costoServicios;
    }

    public void setCostoServicios(double costoServicios) {
        this.costoServicios = costoServicios;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Salon> getSalones() {
        return salones;
    }

    public void setSalones(List<Salon> salones) {
        this.salones = salones;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nombre=" + nombre + ", costoServicios=" + costoServicios + ", cantPersonas=" + cantPersonas + ", fechaAlta=" + fechaAlta + ", fechaBaja=" + fechaBaja + ", estado=" + estado + ", servicios=" + servicios + ", salones=" + salones + ", habitaciones=" + habitaciones + '}';
    }
    
    
}
