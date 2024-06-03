package org.example;


import org.example.Excepciones.NombreIsEmptyException;

public class Validaciones {

    /**
     * Funcion que comprueba que el String solo contenga letras y espacios.
     *
     * @param nombre Cadena de texto
     * @return comprobacionNombre Boolean
     */
    public static Boolean comprobarNombre(String nombre) {
        char letra;
        boolean comprobacionNombre = false;

        try {
            if (nombre.equals("")) {//Comprobación cadena vacia.
                throw new NombreIsEmptyException();
            } else if (!nombre.equals("")) {//Si no esta vacia, comprueba el contenido.
                for (int i = 0; i < nombre.length(); i++) {//Recorremos la cadena de texto posicion por posicion para comparar si el nombre introducido tiene algo que no sean letras o espacios que lo separen.
                    letra = nombre.charAt(i);
                    if ((letra >= 'a' && letra <= 'z') || (letra >= 'A' && letra <= 'Z') || letra == ' ' || letra == 'á' || letra == 'é' || letra == 'í' || letra == 'ó' || letra == 'ú' || letra == 'Á' || letra == 'É' || letra == 'Í' || letra == 'Ó' || letra == 'Ú' || letra == 'ü' || letra == 'Ü' || letra == 'ñ') {
                        comprobacionNombre = true;
                    } else {
                        System.out.println("❗El nombre introducido no es válido❗");
                        i = nombre.length() + 1;//Forzar la salida del bucle en caso de error.
                        comprobacionNombre = false;
                    }
                }
            }
            return comprobacionNombre;
        } catch (NombreIsEmptyException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Función que comprueba que el dni introducido sea valido
     *
     * @param dni Cadena de texto que contiene un DNI
     * @return Validacion del DNI
     */
    /**
     * Función que comprueba que el DNI introducido sea válido.
     *
     * @param dni Cadena de texto que contiene un DNI
     * @return Validación del DNI
     */
    public static boolean comprobarDni(String dni) {
        if (dni.length() != 9) {
            return false;
        }

        dni = dni.toUpperCase();
        String num = dni.substring(0, 8);
        char letra = dni.charAt(8);

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int index = Integer.parseInt(num) % 23;
        char letraControl = letras.charAt(index);

        return letra == letraControl;
    }

    /**
     * Función que comprueba la validez del correo electrónico introducido.
     *
     * @param correo Cadena de texto con un email
     * @return Validación del correo
     */
    public static boolean comprobarCorreo(String correo) {
        if (correo.indexOf("@") == -1) {
            System.out.println("❗Vuelve a introducir el correo❗");
            return false;
        } else {
            int indice = correo.indexOf("@");
            if (correo.indexOf("@", indice + 1) != -1) {
                System.out.println("❗Vuelve a introducir el correo❗");
                return false;
            } else {
                if (correo.indexOf(".", indice) == -1) {
                    System.out.println("❗Vuelve a introducir el correo❗");
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    /**
     * Funcion que comprueba que el telefono introducido sea correcto
     *
     * @param telefono
     * @return
     */
    public static Boolean comprobarTelefono(String telefono) {
        char valorPosicionCadena;
        boolean comprobacionTelefono = false;

        if (telefono.length() == 9) {
            for (int i = 0; i < 9; i++) {
                valorPosicionCadena = telefono.charAt(i);
                if (valorPosicionCadena >= '0' && valorPosicionCadena <= '9') {
                    comprobacionTelefono = true;
                } else {
                    comprobacionTelefono = false;
                    System.out.println("❗Telefono mal introducido❗");
                    i = 10;//Forzar salida del bucle for.
                }
            }
        } else if (telefono.length() == 11) {
            telefono = telefono.replace(" ", "");
            for (int i = 0; i < 9; i++) {
                valorPosicionCadena = telefono.charAt(i);
                if (valorPosicionCadena >= '0' && valorPosicionCadena <= '9') {
                    comprobacionTelefono = true;
                } else {
                    comprobacionTelefono = false;
                    System.out.println("❗Telefono mal introducido❗");
                    i = 10;//Forzar salida del bucle for.
                }
            }
        } else {
            System.out.println("❗Teléfono mal introducido, vuelve a intentarlo❗");
        }
        return comprobacionTelefono;
    }

    /**
     * Función que muestra al usuario los asientos que puede eligir para realizar una reserva y comprueba su disponibilidad.
     */
    public static Boolean comprobarAsiento(String reserva, Evento evento) {

        Butaca butaca;
        boolean comprobacionAsiento = false;

        if (reserva.equals("")) {//Comprobar cadena vacia.
            System.out.println("❗Asiento no elegido, elija un asiento❗");
        } else if (!(reserva.equals(""))) { //Si no esta vacia, entonces comprueba el contenido.
            if ((reserva.charAt(0) >= 'A' && reserva.charAt(0) <= 'F') && (reserva.charAt(1) >= '1' && reserva.charAt(1) <= '6')) {
                butaca = evento.getSala().butaca_reserva(reserva);
                if (butaca.isDisponible()) {
                    System.out.println("🎉El asiento introducido esta disponible🎉");
                    comprobacionAsiento = true;
                } else {
                    System.out.println("❗El asiento introducido no esta disponible, vuelva a intentarlo❗");
                }
            } else {
                System.out.println("❗El asiento introducido no existe, prueba con un asiento válido❗");
            }
        }
        return comprobacionAsiento;
    }

    /**
     * Funcion que formatea la fecha para que sea adecuado al formato de LocalDate
     *
     * @param fecha Cadena de texto que contiene la fecha a formatear
     * @return Cadena de texto con la fecha en formato AAAA-MM-DD
     */
    public static String formateoFecha(String fecha) {
        String fecha_formateada = "", anno = "", mes = "", dia = "";
        if (fecha.length() <= 10 && fecha.length() >= 8) {
            dia = fecha.substring(0, fecha.indexOf("-"));
            mes = fecha.substring(fecha.indexOf("-") + 1, fecha.lastIndexOf("-"));
            anno = fecha.substring(fecha.lastIndexOf("-") + 1);

            if (mes.length() == 1 && Validaciones.comprobarNumero(mes)) {
                mes = "0" + mes;
            }
            if (dia.length() == 1 && Validaciones.comprobarNumero(dia)) {
                dia = "0" + dia;
            }
        }
        if (anno.length() == 4 && mes.length() == 2 && dia.length() == 2) {
            fecha_formateada = anno + "-" + mes + "-" + dia;
        }
        return fecha_formateada;
    }

    /**
     * Función que comprueba que la fecha sea correcta
     *
     * @param fecha Cadena de texto con la fecha a comprobar
     * @return Validación de la fecha en formato AAAA-MM-DD
     */
    public static Boolean comprobarFecha(String fecha) {
        String anno, mes, dia;
        boolean comprobar_fecha = false;

        if (fecha.length() == 10) {
            anno = fecha.substring(0, fecha.indexOf("-"));
            mes = fecha.substring(fecha.indexOf("-") + 1, fecha.lastIndexOf("-"));
            dia = fecha.substring(fecha.lastIndexOf("-") + 1);

            if (anno.length() == 4 && mes.length() == 2 && dia.length() == 2 && Validaciones.comprobarNumero(anno) && Validaciones.comprobarNumero(mes) && Validaciones.comprobarNumero(dia)) {
                if (Validaciones.comprobarBisiesto(anno)) {
                    if ((mes.equals("01") || mes.equals("03") || mes.equals("05") || mes.equals("07") || mes.equals("08") || mes.equals("10") || mes.equals("12") && (Integer.parseInt(dia) >= 1 || Integer.parseInt(dia) <= 31)) || ((mes.equals("04") || mes.equals("06") || mes.equals("09") || mes.equals("11")) && (Integer.parseInt(dia) >= 1 && Integer.parseInt(dia) <= 30)) || (mes.equals("02") && (Integer.parseInt(dia) >= 1 && Integer.parseInt(dia) <= 29))) {
                        comprobar_fecha = true;
                    } else {
                        comprobar_fecha = false;
                    }
                } else {
                    if ((mes.equals("01") || mes.equals("03") || mes.equals("05") || mes.equals("07") || mes.equals("08") || mes.equals("10") || mes.equals("12") && (Integer.parseInt(dia) >= 1 || Integer.parseInt(dia) <= 31)) || ((mes.equals("04") || mes.equals("06") || mes.equals("09") || mes.equals("11")) && (Integer.parseInt(dia) >= 1 && Integer.parseInt(dia) <= 30)) || (mes.equals("02") && (Integer.parseInt(dia) >= 1 && Integer.parseInt(dia) <= 28))) {
                        comprobar_fecha = true;
                    } else {
                        comprobar_fecha = false;
                    }
                }
            }
        } else {
            System.out.println("❗La fecha introducida no es correcta, comprueba el formato y vuelve a intentarlo❗");
        }
        return comprobar_fecha;
    }

    /**
     * Función que comprueba si un año es bisisesto
     *
     * @param anno Cadena de texto con un año
     * @return Validación de año bisisesto
     */
    public static Boolean comprobarBisiesto(String anno) {
        int anno_bisiesto;
        boolean comprobar_anno;
        anno_bisiesto = Integer.parseInt(anno);
        if (anno_bisiesto % 4 == 0 && anno_bisiesto % 100 != 0 && anno_bisiesto % 400 == 0) {
            comprobar_anno = true;
        } else {
            comprobar_anno = false;
        }
        return comprobar_anno;
    }

    /**
     * Funcion que comprueba si la cadena de texto introducida es numerica
     *
     * @param numero Cadena de texto para comprobar
     * @return Validación de que la cadena solamente contiene numeros.
     */
    public static Boolean comprobarNumero(String numero) {
        char aux;
        boolean comprobar_numero = false;

        for (int i = 0; i < numero.length(); i++) {
            aux = numero.charAt(i);
            if (aux >= '0' && aux <= '9') {
                comprobar_numero = true;
            } else {
                comprobar_numero = false;
            }
        }
        return comprobar_numero;
    }

    /**
     * Función que comprueba la longitud de la contraseña
     */
    public static Boolean comprobarPass(String pass){
        boolean comprobarContrasenna=false;
        if (pass.length()>=8){
            comprobarContrasenna=true;
        }else {
            System.out.println("❗La contraseña no cumple con los criterios de seguridad❗");
        }
        return comprobarContrasenna;
    }
}