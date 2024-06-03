package org.example;

import java.time.LocalDate;
import java.util.UUID;

public class Reserva {
    //ATRIBUTOS
    private String id;
    private Usuario usuario;
    private Evento evento;
    private Butaca butaca;
    private LocalDate fecha;
    private String hora;
    private String metodo_pago;

    //CONSTRUCTORES

    Reserva(Usuario usuario, Evento evento) {
        this.usuario = usuario;
        this.evento = evento;
    }

    public Reserva(Usuario usuario, Evento evento, Butaca butaca, LocalDate fecha, String hora, String metodo_pago) {
        this.id = generartoken();
        this.usuario = usuario;
        this.evento = evento;
        this.butaca = butaca;
        this.fecha = fecha;
        this.hora = hora;
        this.metodo_pago = metodo_pago;

    }
    //METODOS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getAsistente() {
        return usuario;
    }

    public void setAsistente(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Butaca getButaca() {
        return butaca;
    }

    public void setButaca(Butaca butaca) {
        this.butaca = butaca;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    /**
     * Funcion que genera un token para la reserva.
     */
    public String generartoken() {
        return UUID.randomUUID().toString().substring(0, 17);
    }

    /**
     * Funcion  que muestra los datos de una Reserva
     */
    public void mostrar_reserva() {
        System.out.println("Estos son los datos de tu reserva: ");
        System.out.println("ID Reserva: " + id);
        System.out.println("Nombre Asistente: " + usuario.getNombre());
        System.out.println("Dni Asistente: " + usuario.getDni());
        System.out.println("Evento: " + evento.getNombre());
        System.out.println("Fecha Evento: " + fecha);
        System.out.println("Hora Evento: " + evento.getHora());
        System.out.println("Butaca: " + butaca);
        System.out.println("Fecha del Evento: " + fecha);
    }
}
