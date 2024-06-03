package org.example;



import org.example.Excepciones.DniExistenteException;
import org.example.Excepciones.EmailExistenteException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Gestor {
    ArrayList<Usuario> listado_usuarios = new ArrayList<>();
    ArrayList<Evento> listado_evento = new ArrayList<>();
    ArrayList<Reserva> listado_reserva = new ArrayList<>();
    Sala listado_salas[] = new Sala[5];

    public ArrayList<Usuario> getListado_asistente() {
        return listado_usuarios;
    }

    public void setlistado_usuarios(ArrayList<Usuario> listado_usuarios) {
        this.listado_usuarios = listado_usuarios;
    }

    public ArrayList<Evento> getListado_evento() {
        return listado_evento;
    }

    public void setListado_evento(ArrayList<Evento> listado_evento) {
        this.listado_evento = listado_evento;
    }

    public ArrayList<Reserva> getListado_reserva() {
        return listado_reserva;
    }

    public void setListado_reserva(ArrayList<Reserva> listado_reserva) {
        this.listado_reserva = listado_reserva;
    }

    public Sala[] getListado_salas() {
        return listado_salas;
    }

    public void setListado_salas(Sala[] listado_salas) {
        this.listado_salas = listado_salas;
    }

    private void info_inicial() throws IOException {
        System.out.println("INICIALIZAR  VALORES");

        //CREAR LISTA USUARIOS
        listado_usuarios.add(new Administrador("Alba", "Carrion", "alba@gmail.com", "606988287", "48736902V", LocalDate.of(1998, 04, 29), true, "12345678"));
        listado_usuarios.add(new Asistente("Sasa", "Vicente", "sasa@gmail.com", "606988287", "48736902V", LocalDate.of(1998, 04, 29), false, "12345678"));
        listado_usuarios.add(new Asistente("Judith", "Martinez", "judith@gmail.com", "699838377", "48728298J", LocalDate.of(1991, 10, 10), false, "12345678"));
        listado_usuarios.add(new Asistente("Saul", "Martinez", "saul@gmail.com", "699838377", "48728296J", LocalDate.of(1996, 06, 15), false, "12345678"));

        // Escribir los usuarios en el archivo "usuarios.dat"
        try (FileOutputStream fos = new FileOutputStream("DataBase/usuarios.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Usuario u : listado_usuarios) {
                oos.writeObject(u);
            }
        }

        // Leer los usuarios del archivo "usuarios.dat" y añadirlos al listado
        try (FileInputStream fis = new FileInputStream("DataBase/usuarios.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                Usuario u = (Usuario) ois.readObject();
                listado_usuarios.add(u);
                System.out.println("Usuario leído del archivo: " + u);
            }
        } catch (EOFException eof) {
            System.out.println("Fin del archivo alcanzado");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }


        for (int i = 0; i < 5; i++) {
            //Generar butacas
            ArrayList<Butaca> mis_butacas = new ArrayList<>();
            int identificador = 0;
            for (char fila = 'A'; fila <= 'F'; fila++) {
                for (int columna = 1; columna <= 6; columna++) {
                    String pos = fila + "" + columna + "";
                    identificador++;
                    mis_butacas.add(new Butaca(identificador, pos, false, true));
                }
            }
            listado_salas[i] = new Sala("Sala " + (i + 1), 200, mis_butacas, 100.0);
        }
        //RESERVAR BUTACAS
        listado_salas[1].getLista_butacas_disponible().get(0).setDisponible(false);

        listado_evento.add(new Evento("Concierto Skrillex", "Skrillex", listado_salas[1], LocalDate.of(2024, 07, 19), "21:00", 30.0, "Concierto"));
        listado_evento.add(new Evento("La Traviata", "Traviata", listado_salas[0], LocalDate.of(2028, 05, 15), "19:00", 35.0, "Opera"));
        listado_evento.add(new Evento("Billy Elliot", "Billy Elliot", listado_salas[3], LocalDate.now(), "19:00", 35.0, "Teatro"));
        listado_evento.add(new Evento("Película Dune 2", "Dune 2", listado_salas[0], LocalDate.of(2024, 06, 15), "19:00", 35.0, "Pelicula"));

        listado_reserva.add(new Reserva(listado_usuarios.get(1), listado_evento.get(0), listado_evento.get(0).getSala().getLista_butacas_disponible().get(5), listado_evento.get(0).getFecha(), listado_evento.get(0).getHora(), listado_evento.get(0).getTipoEvento()));
        listado_evento.get(0).getSala().getLista_butacas_disponible().get(5).setDisponible(false);
        listado_reserva.add(new Reserva(listado_usuarios.get(1), listado_evento.get(1), listado_evento.get(1).getSala().getLista_butacas_disponible().get(8), listado_evento.get(1).getFecha(), listado_evento.get(1).getHora(), listado_evento.get(1).getTipoEvento()));
        listado_evento.get(1).getSala().getLista_butacas_disponible().get(8).setDisponible(false);
    }

    public void mostrar_eventos() {
        System.out.println("EVENTOS DISPONIBLES");
        System.out.println();
        for (Evento e : listado_evento) {
            int fecha = e.getFecha().compareTo(LocalDate.now());
            if (fecha >= 0) {
                System.out.println(e.getNombre().toUpperCase());
            }
        }
    }

    public void mostrar_reservas(String email) {
        boolean reserva = false;
        for (Reserva r : listado_reserva) {
            if (r.getAsistente().getEmail().equals(email)) {
                r.mostrar_reserva();
                System.out.println("----------------------------------");
                System.out.println();
                reserva = true;
            }
        }
        if (!reserva) {
            System.out.println("No tiene reservas");
        }
    }

    public void menu_reserva(String email) {
        String nombre_evento, opcion_reserva;
        boolean evento_existe = false;
        Evento evento = new Evento();
        Usuario usuario = null;
        Scanner sc = new Scanner(System.in);
        do {
            mostrar_eventos();
            System.out.println("Seleccione el nombre del evento para comprobar la disponibilidad");
            nombre_evento = sc.nextLine();
            for (Evento e : listado_evento) {
                if (e.getNombre().equalsIgnoreCase(nombre_evento)) {
                    evento = e;
                    e.mostrar_evento();
                    evento_existe = true;
                }
            }
            if (evento_existe) {
                if (evento.getSala().getCapacidad_maxima() > evento.getSala().getLista_butacas_disponible().size()) {
                    do {
                        System.out.println("¿Quiere realizar una reserva?");
                        System.out.println("1.⭕ Realizar reserva");
                        System.out.println("2.❌ Salir");
                        opcion_reserva = sc.nextLine();
                        switch (opcion_reserva) {
                            case "1":
                                String reserva, pago;
                                Butaca butaca;
                                boolean reservado = false;
                                Scanner entrada = new Scanner(System.in);
                                do {
                                    System.out.println("Disposición de los asientos: ");
                                    System.out.println("A1  A2  A3  A4  A5  A6");
                                    System.out.println("B1  B2  B3  B4  B5  B6");
                                    System.out.println("C1  C2  C3  C4  C5  C6");
                                    System.out.println("D1  D2  D3  D4  D5  D6");
                                    System.out.println("F1  F2  F3  F4  F5  F6");
                                    System.out.println("Por favor, seleccione un asiento (fila número): ");
                                    reserva = entrada.nextLine();
                                    reserva = reserva.toUpperCase();
                                    if (Validaciones.comprobarAsiento(reserva, evento)) {
                                        usuario = devuelve_asistente(email);
                                        butaca = evento.getSala().butaca_reserva(reserva);
                                        do {
                                            pago = metodo_de_pago();
                                        } while (!(pago.equals("Cancelado") || pago.equals("Tranferencia bancaria") || pago.equals("Bizum") || pago.equals("Paypal")));

                                        if (!pago.equals("Cancelado")) {
                                            reservar(usuario, evento, butaca, pago);
                                            reservado = true;
                                        } else {
                                            reservado = true;
                                        }
                                    }
                                } while (!reservado);
                                break;
                            case "2":
                                System.out.println("Saliendo");
                                break;
                            default:
                                System.out.println("Opcion no válida");
                                break;
                        }
                    } while (!opcion_reserva.equals("2"));
                } else {
                    System.out.println("El evento no tiene disponibilidad, seleccione otro evento");
                }
            } else {
                System.out.println("Evento no encontrado");
            }
        } while (!evento_existe);
    }

    public void login_usuario() {
        Scanner sc = new Scanner(System.in);
        String email, contraseña;
        boolean logeado = false;
        System.out.println("Correo electrónico");
        email = sc.nextLine();
        System.out.println("Contraseña");
        contraseña = sc.nextLine();

        for (Usuario u : listado_usuarios) {
            if (u.getEmail().equals(email) && u.getContrasena().equals(contraseña)) {
                System.out.println("Bienvenido " + u.getNombre());
                logeado = true;
                if (u.getPermisos()) {
                    // Menú administrador
                    String opcion_admin;
                    do {
                        System.out.println("PANEL DE ADMINISTRADOR");
                        System.out.println("1. Gestionar Eventos");
                        System.out.println("2. Gestionar Asistentes");
                        System.out.println("3. Gestionar Reservas");
                        System.out.println("4. Salir");
                        System.out.print("Selecciona una opción: ");
                        opcion_admin = sc.nextLine();
                        switch (opcion_admin) {
                            case "1":
                                menu_gestionar_eventos();
                                break;
                            case "2":
                                // Menú para gestionar asistentes
                                menu_gestionar_asistentes();
                                break;
                            case "3":
                                // Menú para gestionar reservas
                                menu_gestionar_reservas();
                                break;
                            case "4":
                                System.out.println("Saliendo...");
                                break;
                            default:
                                System.out.println("Opción no válida");
                                break;
                        }
                    } while (!opcion_admin.equals("4"));
                } else {
                    // Menú asistente
                    String opcion_asistente;
                    do {
                        System.out.println("Menú asistente:");
                        System.out.println("1. Mostrar eventos");
                        System.out.println("2. Realizar reserva");
                        System.out.println("3. Mostrar reservas");
                        System.out.println("4. Salir");
                        opcion_asistente = sc.nextLine();
                        switch (opcion_asistente) {
                            case "1":
                                mostrar_eventos();
                                break;
                            case "2":
                                menu_reserva(email);
                                break;
                            case "3":
                                mostrar_reservas(email);
                                break;
                            case "4":
                                System.out.println("Saliendo...");
                                break;
                            default:
                                System.out.println("Opción no válida");
                                break;
                        }
                    } while (!opcion_asistente.equals("4"));
                }
                break;
            }
        }
        if (!logeado) {
            System.out.println("Correo electrónico o contraseña incorrectos");
        }
    }

    public void menu_gestionar_eventos() {
        Scanner sc = new Scanner(System.in);
        String opcion_evento;
        do {
            System.out.println("## EVENTOS ##");
            System.out.println("1. Agregar evento");
            System.out.println("2. Modificar evento");
            System.out.println("3. Eliminar evento");
            System.out.println("4. Mostrar un evento");
            System.out.println("5. Listado de eventos");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
            opcion_evento = sc.nextLine();
            switch (opcion_evento) {
                case "1":
                    crear_evento();
                    break;
                case "2":
                    modificar_evento();
                    break;
                case "3":
                    eliminar_evento();
                    break;
                case "4":
                    mostrar_evento();
                    break;
                case "5":
                    listado_eventos();
                    break;
                case "6":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (!opcion_evento.equals("6"));
    }

    public void menu_gestionar_asistentes() {
        // Implementar gestión de asistentes
        System.out.println("Funcionalidad para gestionar asistentes no implementada.");
    }

    public void menu_gestionar_reservas() {
        // Implementar gestión de reservas
        System.out.println("Funcionalidad para gestionar reservas no implementada.");
    }

    public void crear_evento() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Crear nuevo evento:");
        System.out.print("Nombre del evento: ");
        String nombre = sc.nextLine();
        System.out.print("Nombre del artista: ");
        String artista = sc.nextLine();
        System.out.print("Número de sala (1-5): ");
        int numSala = sc.nextInt();
        sc.nextLine(); // Consumir la línea restante
        System.out.print("Fecha del evento (YYYY-MM-DD): ");
        String fechaStr = sc.nextLine();
        LocalDate fecha = LocalDate.parse(fechaStr);
        System.out.print("Hora del evento (HH:MM): ");
        String hora = sc.nextLine();
        System.out.print("Precio del evento: ");
        double precio = sc.nextDouble();
        sc.nextLine(); // Consumir la línea restante
        System.out.print("Tipo de evento: ");
        String tipo = sc.nextLine();

        Evento nuevoEvento = new Evento(nombre, artista, listado_salas[numSala - 1], fecha, hora, precio, tipo);
        listado_evento.add(nuevoEvento);
        System.out.println("Evento creado exitosamente");
    }

    public void modificar_evento() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Modificar evento:");
        System.out.print("Nombre del evento a modificar: ");
        String nombre = sc.nextLine();
        Evento evento = null;

        for (Evento e : listado_evento) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                evento = e;
                break;
            }
        }

        if (evento != null) {
            System.out.print("Nuevo nombre del evento: ");
            evento.setNombre(sc.nextLine());
            System.out.print("Nuevo nombre del artista: ");
            evento.setArtista(sc.nextLine());
            System.out.print("Nuevo número de sala (1-5): ");
            int numSala = sc.nextInt();
            sc.nextLine(); // Consumir la línea restante
            evento.setSala(listado_salas[numSala - 1]);
            System.out.print("Nueva fecha del evento (YYYY-MM-DD): ");
            evento.setFecha(LocalDate.parse(sc.nextLine()));
            System.out.print("Nueva hora del evento (HH:MM): ");
            evento.setHora(sc.nextLine());
            System.out.print("Nuevo precio del evento: ");
            evento.setPrecio(sc.nextDouble());
            sc.nextLine(); // Consumir la línea restante
            System.out.print("Nuevo tipo de evento: ");
            evento.setTipoEvento(sc.nextLine());

            System.out.println("Evento modificado exitosamente");
        } else {
            System.out.println("Evento no encontrado");
        }
    }

    public void eliminar_evento() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Eliminar evento:");
        System.out.print("Nombre del evento a eliminar: ");
        String nombre = sc.nextLine();

        Evento eventoAEliminar = null;
        for (Evento e : listado_evento) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                eventoAEliminar = e;
                break;
            }
        }

        if (eventoAEliminar != null) {
            listado_evento.remove(eventoAEliminar);
            System.out.println("Evento eliminado exitosamente");
        } else {
            System.out.println("Evento no encontrado");
        }
    }

    public void mostrar_evento() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre del evento a mostrar: ");
        String nombre = sc.nextLine();

        for (Evento e : listado_evento) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                e.mostrar_evento();
                return;
            }
        }
        System.out.println("Evento no encontrado");
    }

    public void listado_eventos() {
        for (Evento e : listado_evento) {
            e.mostrar_evento();
        }
    }

    public void reservar(Usuario usuario, Evento evento, Butaca butaca, String metodo_pago) {
        Reserva reserva = new Reserva(usuario, evento, butaca, evento.getFecha(), evento.getHora(), evento.getTipoEvento());
        listado_reserva.add(reserva);
        butaca.setDisponible(false);
        System.out.println("Reserva realizada exitosamente con el método de pago: " + metodo_pago);
    }

    public String metodo_de_pago() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Métodos de pago disponibles:");
        System.out.println("1. Transferencia bancaria");
        System.out.println("2. Bizum");
        System.out.println("3. Paypal");
        System.out.println("4. Cancelar");
        String opcion_pago = sc.nextLine();
        switch (opcion_pago) {
            case "1":
                return "Tranferencia bancaria";
            case "2":
                return "Bizum";
            case "3":
                return "Paypal";
            case "4":
                return "Cancelado";
            default:
                System.out.println("Opción no válida");
                return metodo_de_pago();
        }
    }

    public Usuario devuelve_asistente(String email) {
        for (Usuario u : listado_usuarios) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }



    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        try {
            gestor.info_inicial();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gestor.login_usuario();
    }
    /**
     * Funcion que crea un usuario
     */
    public void registro_usuario() {
        String nombre_usuario, apellidos, dni, email, pass, telefono, fecha_nacimiento;
        boolean registro = true;
        Scanner sc = new Scanner(System.in);
        do {
            do {
                System.out.println("Introduzca nombre.");
                nombre_usuario = sc.nextLine();
            } while (!Validaciones.comprobarNombre(nombre_usuario));
            do {
                System.out.println("Introduzca apellidos");
                apellidos = sc.nextLine();
            } while (!Validaciones.comprobarNombre(apellidos));
            do {
                System.out.println("Introduzca Dni");
                dni = sc.nextLine();
                try{
                    for(Usuario usu: listado_usuarios) {
                        if(usu.getDni().equals(dni)) {
                            throw new DniExistenteException();
                        }
                    }
                }catch (DniExistenteException e) {
                    System.out.println(e.getMessage());
                }
            } while (!Validaciones.comprobarDni(dni));
            do {
                System.out.println("Introduzca correo electronico");
                email = sc.nextLine();
                try{
                    for(Usuario usu: listado_usuarios) {
                        if(usu.getEmail().equals(email)) {
                            throw new EmailExistenteException();
                        }
                    }
                }catch (EmailExistenteException e) {
                    System.out.println(e.getMessage());
                }
            } while (!Validaciones.comprobarCorreo(email));
            do {
                System.out.println("Introduzca contraseña (debe tener al menos una longitud 8 caracteres)");
                pass = sc.nextLine();
            } while (!Validaciones.comprobarPass(pass));
            do {
                System.out.println("Introduce telefono");
                telefono = sc.nextLine();
            } while (!Validaciones.comprobarTelefono(telefono));
            do {
                System.out.println("Introduce fecha de nacimiento");
                fecha_nacimiento = sc.nextLine();
                fecha_nacimiento = Validaciones.formateoFecha(fecha_nacimiento);
            } while (!Validaciones.comprobarFecha(fecha_nacimiento));
            if (Validaciones.comprobarNombre(nombre_usuario) && Validaciones.comprobarNombre(apellidos) && Validaciones.comprobarDni(dni) && Validaciones.comprobarCorreo(email) && Validaciones.comprobarTelefono(telefono) && Validaciones.comprobarFecha(fecha_nacimiento)) {
                listado_usuarios.add(new Usuario(nombre_usuario, apellidos, dni, email, pass, telefono, LocalDate.parse(fecha_nacimiento)));
                registro = true;
                System.out.println("Registro exitoso");
            } else {
                registro = false;
            }
        } while (!registro);
    }
}
