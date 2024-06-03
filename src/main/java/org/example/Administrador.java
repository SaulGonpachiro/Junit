package org.example;

import java.time.LocalDate;

public class Administrador extends Usuario {
    private boolean permisos;

    public Administrador(String nombre, String apellidos, String email, String telefono, String dni, LocalDate fechaNacimiento, boolean permisos, String contrasena) {
        super(nombre, apellidos, email, contrasena, telefono, dni, permisos, fechaNacimiento);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "permisos=" + permisos +
                "} " + super.toString();
    }
}

