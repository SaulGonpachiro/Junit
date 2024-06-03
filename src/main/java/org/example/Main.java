package org.example;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String opcion_registro;
        Scanner sc = new Scanner(System.in);
        Gestor gestor_eventos = new Gestor();

        do {
            System.out.println("\n\n🌟🌟🌟🌟🌟 DELECTARE MULTIEVENTOS 🌟🌟🌟🌟🌟\n<<------------------------------------------>>\n\n   1.  🚀 [Acceder]\n   2.  📝 [Registrarse]\n   3.  🚪 [Finalizar]\n\n<<----------------------------------------\n   Por favor, selecciona una opción: \n---------------------------------------->>\n");
            opcion_registro = sc.nextLine();
            switch (opcion_registro) {
                case "1":
                    gestor_eventos.login_usuario(); // Llamada al método para iniciar sesión
                    break;
                case "2":
                    gestor_eventos.registro_usuario(); // Llamada al método para registrarse
                    break;
                case "3":
                    System.out.println("Saliendo"); // Finaliza el programa
                    break;
                default:
                    System.out.println("❗ Introduce una opción válida❗");
                    // Abre un enlace en el navegador en caso de opción inválida
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        try {
                            Desktop.getDesktop().browse(new URI("https://youtu.be/6W5AMPjtcns"));
                        } catch (IOException | URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
            }
        } while (!opcion_registro.equals("3")); // El bucle se repite hasta que el usuario elija la opción "3"
    }
}
