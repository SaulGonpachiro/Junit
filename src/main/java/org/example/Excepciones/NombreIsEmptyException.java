package org.example.Excepciones;

public class NombreIsEmptyException extends Exception{

    public NombreIsEmptyException() {
        super("El nombre esta vacío");
    }

    public NombreIsEmptyException(String mensaje){
        super(mensaje);
    }
}
