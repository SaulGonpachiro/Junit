package org.example;

import java.time.LocalDate;

// La clase Asistente extiende la clase Usuario
public class Asistente extends Usuario {

    // Constructor de Asistente que inicializa los campos heredados de Usuario
    public Asistente(String nombre, String apellidos, String email, String telefono, String dni, LocalDate fechaNacimiento, boolean permisos, String contrasena) {
        super(nombre, apellidos, email, contrasena, telefono, dni, permisos, fechaNacimiento);
    }
}

