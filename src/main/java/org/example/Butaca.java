package org.example;

public class Butaca {
    //ATRIBUTOS
    public int id;
    public String posicion;
    private boolean accesibilidad;
    private boolean disponible;

    //CONSTRUCTORES
    public Butaca() {

    }

    public Butaca(int id, String posicion, boolean accesibilidad, boolean disponible) {
        this.id = id;
        this.posicion = posicion;
        this.accesibilidad = accesibilidad;
        this.disponible = disponible;
    }

    //GETERS Y SETERS


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public boolean isAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(boolean accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    //METODOS

    /**
     * Función que devuelve los valores disponibles de una Butaca
     */
    public String toString() {
        String datosButaca;
        datosButaca = "ID de la Butaca: " + id + "\nDisponibilidad: " + disponible + "\nAccesibilidad: " + accesibilidad;
        return datosButaca;
    }


    /**
     * Función que comprueba la disponibilidad de una Butaca y la desocupa si esta ocupada
     */
    public void desocuparButaca() {
        if (this.disponible == false) {
            this.disponible = true;
            System.out.println("🎉La butaca ha sido liberada🎉");
        } else {
            System.out.println("❗La butaca estaba libre❗");
        }
    }
}