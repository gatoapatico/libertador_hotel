package Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class DetalleReserva implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "detalleReservaHabitacion")
    private List<Habitacion> Listahabitaciones;
    @OneToMany(mappedBy = "detalleReservaSalon")
    private List<Salon> listaSalones;
    private Date checkIn;
    private Date chackOut;
    @OneToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    public DetalleReserva() {
    }

    public DetalleReserva(int id, List<Habitacion> Listahabitaciones, List<Salon> listaSalones, Date checkIn, Date chackOut, Reserva reserva) {
        this.id = id;
        this.Listahabitaciones = Listahabitaciones;
        this.listaSalones = listaSalones;
        this.checkIn = checkIn;
        this.chackOut = chackOut;
        this.reserva = reserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Habitacion> getListahabitaciones() {
        return Listahabitaciones;
    }

    public void setListahabitaciones(List<Habitacion> Listahabitaciones) {
        this.Listahabitaciones = Listahabitaciones;
    }

    public List<Salon> getListaSalones() {
        return listaSalones;
    }

    public void setListaSalones(List<Salon> listaSalones) {
        this.listaSalones = listaSalones;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getChackOut() {
        return chackOut;
    }

    public void setChackOut(Date chackOut) {
        this.chackOut = chackOut;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    
}
