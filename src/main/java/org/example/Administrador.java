package org.example;

import java.time.LocalDate;

// La clase Administrador extiende la clase Usuario, hereda sus propiedades y métodos.
public class Administrador extends Usuario {
    // Campo privado que indica si el administrador tiene permisos especiales.
    private boolean permisos;

    // Constructor de la clase Administrador que inicializa los campos heredados de Usuario y el campo permisos.
    public Administrador(String nombre, String apellidos, String email, String telefono, String dni, LocalDate fechaNacimiento, boolean permisos, String contrasena) {
        super(nombre, apellidos, email, contrasena, telefono, dni, permisos, fechaNacimiento);
        this.permisos = permisos;
    }

    // Proporciona una representación en forma de cadena del objeto Administrador.
    @Override
    public String toString() {
        return "Administrador{" +
                "permisos=" + permisos +
                "} " + super.toString();
    }
}


