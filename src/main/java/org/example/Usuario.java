package org.example;

import java.io.Serializable;
import java.time.LocalDate;

public class Usuario implements Serializable {
    // Atributos comunes
    private String nombre;
    private String apellidos;
    private String email;
    private String contrasena;
    private String telefono;
    private String dni;
    private LocalDate fecha_nacimiento;
    private boolean permisos;

    // Constructor
    public Usuario(String nombre, String apellidos, String email, String contrasena, String telefono, String dni, boolean permisos, LocalDate fecha_nacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.dni = dni;
        this.permisos = permisos;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Usuario(String nombre, String apellidos, String email, String contrasena, String telefono, String dni, LocalDate fechaNacimiento) {
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public boolean getPermisos() {
        return permisos;
    }

    public void setAdmin(boolean permisos) {
        this.permisos = permisos;
    }

    //METODOS
    /**
     * Funcion que muestra todos los datos disponibles sobre un Asistente
     */
    public String toString() {
        String datosAsistente;
        datosAsistente = "Nombre: " + nombre + "\nApellidos: " + apellidos + "\nCorreo electrónico: " + email + "\nTelefono: " + telefono + "\nDni: " + dni + "\nFecha de nacimiento: " + fecha_nacimiento;
        return datosAsistente;
    }
}
