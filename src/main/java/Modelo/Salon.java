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

@Entity
public class Salon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double costo;
    @ManyToOne
    @JoinColumn(name = "Id_categoria_salon")
    private Categoria tipoSalon;
    private int maxPersonas;
    private Date fechaAlta;
    private Date fechaBaja;
    private int clienteActual;
    private String estado;
    
    
    @ManyToOne
    @JoinColumn(name= "Id_detalleReserva")
    private DetalleReserva detalleReservasSalones;

    public Salon() {
    }

    public Salon(int id, double costo, Categoria tipoSalon, int maxPersonas, Date fechaAlta, Date fechaBaja, int clienteActual, String estado, DetalleReserva detalleReservasSalones) {
        this.id = id;
        this.costo = costo;
        this.tipoSalon = tipoSalon;
        this.maxPersonas = maxPersonas;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.clienteActual = clienteActual;
        this.estado = estado;
        this.detalleReservasSalones = detalleReservasSalones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Categoria getTipoSalon() {
        return tipoSalon;
    }

    public void setTipoSalon(Categoria tipoSalon) {
        this.tipoSalon = tipoSalon;
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

    public int getClienteActual() {
        return clienteActual;
    }

    public void setClienteActual(int clienteActual) {
        this.clienteActual = clienteActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DetalleReserva getDetalleReservasSalones() {
        return detalleReservasSalones;
    }

    public void setDetalleReservasSalones(DetalleReserva detalleReservasSalones) {
        this.detalleReservasSalones = detalleReservasSalones;
    }
    
}
