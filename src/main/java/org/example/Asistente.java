package org.example;

import java.time.LocalDate;

public class Asistente extends Usuario {

    //CONSTRUCTORES
    public Asistente(String nombre, String apellidos, String email, String telefono, String dni, LocalDate fechaNacimiento, boolean permisos, String contrasena) {
        super(nombre, apellidos, email, contrasena, telefono, dni, permisos, fechaNacimiento);
    }

}
