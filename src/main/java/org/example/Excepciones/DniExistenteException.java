package org.example.Excepciones;

public class DniExistenteException extends Exception{
    public DniExistenteException() {
        super("El DNI ya existe");
    }

    public DniExistenteException(String mensaje){
        super(mensaje);
    }
}
