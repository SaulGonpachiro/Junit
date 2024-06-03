package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class Evento {

    //ATRIBUTOS
    private String nombre;
    private String invitado;
    private Sala sala;
    private LocalDate fecha;
    private String hora;
    private double precio;
    private String tipoEvento;
    public ArrayList<Usuario> listado_usuarios = new ArrayList<>();

    public Evento() {

    }

    public Evento(String nombre, String invitado, Sala sala, LocalDate fecha, String hora, double precio, String tipoEvento) {
        this.nombre = nombre;
        this.invitado = invitado;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.tipoEvento = tipoEvento;
    }

    //METODOS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInvitado() {
        return invitado;
    }

    public void setInvitado(String invitado) {
        this.invitado = invitado;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public ArrayList<Usuario> getListado_usuarios() {
        return listado_usuarios;
    }

    public void setListado_usuarios(ArrayList<Usuario> listado_usuarios) {
        this.listado_usuarios = listado_usuarios;
    }

    /**
     * Funcion que muestra los asistentes de un Evento
     */
    public void mostrar_asistentes() {
        for (Usuario b : listado_usuarios) {
            b.toString();
        }
    }

    /**
     * Funcion que muestra la información de un Evento
     */
    public void mostrar_evento() {
        System.out.println("Evento: " + nombre + "\nSala: " + sala.getNombre() + "\nPrecio: " + precio + "\nTipo Evento: " + tipoEvento + "\nCapacidad de la Sala: " + listado_usuarios.size() + "/" + sala.getCapacidad_maxima());
    }


    public void setArtista(String s) {

    }
}
