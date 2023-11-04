package Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Habitacion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numHabitacion;
    private double costohabitacion;
    @ManyToOne
    @JoinColumn(name ="Id_categoria_habitacion" )
    private Categoria tipoHabitacion; 
    private int maxPersonas;
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    //Falta modificar
    private String ocupantes;
    private String estado;
    
    
    @ManyToOne
    @JoinColumn(name= "Id_detalleReserva")
    private DetalleReserva detalleReservasHabitaciones;

    public Habitacion() {
    }

    public Habitacion(int id, int numHabitacion, double costohabitacion, Categoria tipoHabitacion, int maxPersonas, Date fechaAlta, Date fechaBaja, String ocupantes, String estado, DetalleReserva detalleReservasHabitaciones) {
        this.id = id;
        this.numHabitacion = numHabitacion;
        this.costohabitacion = costohabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.maxPersonas = maxPersonas;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.ocupantes = ocupantes;
        this.estado = estado;
        this.detalleReservasHabitaciones = detalleReservasHabitaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public double getCostohabitacion() {
        return costohabitacion;
    }

    public void setCostohabitacion(double costohabitacion) {
        this.costohabitacion = costohabitacion;
    }

    public Categoria getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(Categoria tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getMaxPersonas() {
        return maxPersonas;
    }

    public void setMaxPersonas(int maxPersonas) {
        this.maxPersonas = maxPersonas;
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

    public String getOcupantes() {
        return ocupantes;
    }

    public void setOcupantes(String ocupantes) {
        this.ocupantes = ocupantes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DetalleReserva getDetalleReservasHabitaciones() {
        return detalleReservasHabitaciones;
    }

    public void setDetalleReservasHabitaciones(DetalleReserva detalleReservasHabitaciones) {
        this.detalleReservasHabitaciones = detalleReservasHabitaciones;
    }
    
}
