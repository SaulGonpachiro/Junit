package org.example.Excepciones;

public class EmailExistenteException extends Exception{

    public EmailExistenteException() {
        super("El correo electronico ya existe");
    }

    public EmailExistenteException(String mensaje){
        super(mensaje);
    }
}
