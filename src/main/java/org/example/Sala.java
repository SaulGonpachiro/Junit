package org.example;

import java.util.ArrayList;

public class Sala {
    //ATRIBUTOS

    private String nombre;
    private int capacidad_maxima;
    private ArrayList<Butaca> lista_butacas_disponible = new ArrayList<>();
    private double dimension_en_m2;
    private boolean accesibilidad;
    private String caracteristicas_acusticas;
    private String observaciones;

    //CONSTRUCTORES

    public Sala(String nombre, int capacidad_maxima, ArrayList<Butaca> lista_butacas_disponible, double dimension_en_m2) {
        this.nombre = nombre;
        this.capacidad_maxima = capacidad_maxima;
        this.lista_butacas_disponible = lista_butacas_disponible;
        this.dimension_en_m2 = dimension_en_m2;
    }


    //METODOS

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad_maxima() {
        return capacidad_maxima;
    }

    public void setCapacidad_maxima(int capacidad_maxima) {
        this.capacidad_maxima = capacidad_maxima;
    }

    public ArrayList<Butaca> getLista_butacas_disponible() {
        return lista_butacas_disponible;
    }

    public void setLista_butacas_disponible(ArrayList<Butaca> lista_butacas_disponible) {
        this.lista_butacas_disponible = lista_butacas_disponible;
    }

    public double getDimension_en_m2() {
        return dimension_en_m2;
    }

    public void setDimension_en_m2(double dimension_en_m2) {
        this.dimension_en_m2 = dimension_en_m2;
    }

    public boolean isAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(boolean accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public String getCaracteristicas_acusticas() {
        return caracteristicas_acusticas;
    }

    public void setCaracteristicas_acusticas(String caracteristicas_acusticas) {
        this.caracteristicas_acusticas = caracteristicas_acusticas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Funcion que comprueba la capacidad de una Sala
     *
     * @param capacidad
     */
    public void comprobarCapacidad(int capacidad) {
        if (this.capacidad_maxima < capacidad) {
            System.out.println("❗La capacidad de la sala es inferior a lo que necesita❗");
        } else {
            System.out.println("🎉La Sala cumple con sus necesidades🎉");
        }
    }

    /**
     * Funcion que muestra las butacas de una sala
     */
    public void mostrar_butacas() {
        for (Butaca b : lista_butacas_disponible) {
            System.out.print(b.posicion + "(" + b.id + ")");
        }
    }

    /**
     * Funcion que devuelve un Butaca de una Sala para usarla en una Reserva
     *
     * @param posicion Posicion de la Butaca con respecto a la Sala que la contine
     * @return Devuelve la Butaca que va a ser usada en una Reserva
     */
    public Butaca butaca_reserva(String posicion) {
        Butaca butaca_reserva = new Butaca();
        for (Butaca br : lista_butacas_disponible) {
            if (br.getPosicion().equals(posicion)) {
                butaca_reserva = br;
            }
        }
        return butaca_reserva;
    }

    public void ocuparButaca(Butaca butaca) {
        for (Butaca b : lista_butacas_disponible) {
            if (b.equals(butaca)) {
                butaca.setDisponible(false);
            }
        }
    }
}