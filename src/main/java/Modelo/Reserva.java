package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Reserva implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    private double totalPagar;
    @OneToOne(mappedBy = "reserva")
    private DetalleReserva detalleReserva;

    public Reserva() {
    }

    public Reserva(int id, Usuario usuario, double totalPagar, DetalleReserva detalleReserva) {
        this.id = id;
        this.usuario = usuario;
        this.totalPagar = totalPagar;
        this.detalleReserva = detalleReserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public DetalleReserva getDetalleReserva() {
        return detalleReserva;
    }

    public void setDetalleReserva(DetalleReserva detalleReserva) {
        this.detalleReserva = detalleReserva;
    }
    
}
