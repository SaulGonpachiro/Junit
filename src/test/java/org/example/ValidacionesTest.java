package org.example;

import static org.junit.jupiter.api.Assertions.*;

class ValidacionesTest {

    @org.junit.jupiter.api.Test
    void comprobarNombre() {
        assertTrue(Validaciones.comprobarNombre("Saul"));
        assertFalse(Validaciones.comprobarNombre(null));
        assertFalse(Validaciones.comprobarNombre("Saul1"));
    }

    @org.junit.jupiter.api.Test
    void comprobarDni() {
        assertFalse(Validaciones.comprobarDni("12345678"));
        assertTrue(Validaciones.comprobarDni("48728296J"));
    }

    @org.junit.jupiter.api.Test
    void comprobarCorreo() {
        assertFalse(Validaciones.comprobarCorreo("Juan@gmal.com"));
        assertTrue(Validaciones.comprobarCorreo("saul@gmail.com"));
    }

    @org.junit.jupiter.api.Test
    void comprobarTelefono() {
        assertFalse(Validaciones.comprobarCorreo("12345678"));
        assertTrue(Validaciones.comprobarCorreo("699838377"));
    }

    @org.junit.jupiter.api.Test
    void comprobarAsiento() {
    }

    @org.junit.jupiter.api.Test
    void formateoFecha() {
    }

    @org.junit.jupiter.api.Test
    void comprobarFecha() {
    }

    @org.junit.jupiter.api.Test
    void comprobarBisiesto() {
    }

    @org.junit.jupiter.api.Test
    void comprobarNumero() {
    }

    @org.junit.jupiter.api.Test
    void comprobarPass() {
    }
}