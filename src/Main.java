import java.io.*;
import java.util.Arrays;
import java.util.Date;

/**
 * Clase Main
 *
 * La clase principal del codigo
 *
 * @author Juan Brito
 * @version 1.0
 */

public class Main {

    // Variables constantes con el numero de Preguntas totales, los mensajes que se mostraran para los resultados
    // y el mensaje de error, usado en caso de que el usuario introduzca un valor no valido
    final static int NUMERO_PREGUNTAS = 20;
    final static String RESULTADOS_BAJOS = "You didn't even tried";
    final static String RESULTADOS_MEDIOS = "You have average results";
    final static String RESULTADOS_ALTAS = "You are above the average";
    final static String RESULTADOS_PERFECTOS = "Wow, you are a genius";
    final static String MENSAJE_ERROR = "ERROR!!!\n pleasy say yes or no";

    /**
     * Metodo principal de la clase
     *
     * @param args
     */
    public static void main(String[] args) {

        // Se crean los arrays con las preguntas, las posibilidades y las respuestas
        String[] preguntas = preguntas();
        String[] posiblesRespuestas = posiblesRespuestas();
        char[] respuestasCorrectas = respuestasCorrectas();

        // variables
        boolean noJugar, salir;
        int numeroPreguntasUsuario, aciertos, fallos;
        String nombre;
        int[] preguntasElegidas;

        salir = false;

        noJugar = empezarJuego();
        // Si el usuario responde que no quiere jugar se mostrara un mensaje y se cerrara el programa
        if (!noJugar) {
            while (!salir) {
                nombre = askName();
                numeroPreguntasUsuario = preguntarNumero();
                preguntasElegidas = elegirPreguntas(numeroPreguntasUsuario);
                reglas();
                aciertos = jugar(preguntasElegidas, preguntas, posiblesRespuestas, respuestasCorrectas);
                fallos = numeroPreguntasUsuario - aciertos; // Se calcula el numero de fallos para mostrarselo al usuario
                salir = cerrarJuego(aciertos, fallos, numeroPreguntasUsuario, nombre);
            }
        }else {
            System.out.println("Well maybe next time");
        }
    }

    private static String askName() {

        System.out.println("Enter your name");

        return ToolsBrito.leerString();
    }

    /**
     * Metodo que crea un array de chars y lo devuelve para almacenarlo
     *
     * @return Array con las respuestas correctas para las preguntas
     */
    private static char[] respuestasCorrectas() {
        char[] respuestas = new char[NUMERO_PREGUNTAS];

        String fileName = "src/resources/respuestas.txt";
        String line;
        int i = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while ( (line = bufferedReader.readLine()) != null){
                respuestas[i] = line.charAt(0);
                i++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("THE FILE WAS NOT FOUND");
        } catch (IOException e) {
            System.out.println("THERE WAS AN ERROR DURING READING THE FILE");
        }

        return respuestas;
    }

    /**
     * Metodo que crea un array con las posibilidades que pueden mostrarse al usuario
     *
     * @return El array de Strings creado y lo almacena en el Main
     */
    private static String[] posiblesRespuestas() {
        String[] posiblesRespuestas = new String[NUMERO_PREGUNTAS];

        String fileName = "src/resources/posibilidades.txt";
        String line;
        int i = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while ( (line = bufferedReader.readLine()) != null){
                posiblesRespuestas[i] = line;
                i++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("THE FILE WAS NOT FOUND");
        } catch (IOException e) {
            System.out.println("THERE WAS AN ERROR DURING READING THE FILE");
        }

        return posiblesRespuestas;
    }

    /**
     * Metodo que crea un array con todas las preguntas posibles
     *
     * @return el array y lo almacena
     */
    private static String[] preguntas() {
        String[] preguntas = new String[NUMERO_PREGUNTAS];
        String fileName = "src/resources/preguntas.txt";
        String line;
        int i = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while ( (line = bufferedReader.readLine()) != null){
                preguntas[i] = line;
                i++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("THE FILE WAS NOT FOUND");
        } catch (IOException e) {
            System.out.println("THERE WAS AN ERROR DURING READING THE FILE");
        }

        return preguntas;
    }

    /**
     * Metodo del juego principal donde se muestra al usuario las preguntas para que responda y se comprueba con la
     * respuesta correcta
     *
     * @param preguntasElegidas El array con el indice de preguntas que se haran
     * @param preguntas El array de las preguntas
     * @param posiblesRespuestas El array de las posibilidades (a, b, c o d) que se mostraran al usuario
     * @param respuestas El array de chars con las respuestas correctas de las preguntas
     * @return El numero de aciertos del usuario, usado para los resultados finales
     *
     * @exception No estan limitadas las respuestas que da el usuario por lo que puede responder, por ejemplo, una e
     * en una pregunta con solo 3 opciones y no lo verifica y se marca directamente como incorrecto
     */

    private static int jugar(int[] preguntasElegidas, String[] preguntas, String[] posiblesRespuestas, char[] respuestas) {
        int contador, fallos, aciertos;
        char respuesta;
        boolean salir;

        salir = false;
        contador = 1;
        aciertos = 0;
        fallos = 0;
        for (int i = 0; i < preguntasElegidas.length && !salir; i++) {
            System.out.println(contador + ". " + preguntas[preguntasElegidas[i]]);
            System.out.println(posiblesRespuestas[preguntasElegidas[i]]);
            respuesta = ToolsBrito.leerChar();
            if(respuesta >= 'A' && respuesta <= 'Z') {              // CONVIERTE CHAR EN MINUSCULAS
                respuesta = (char) (respuesta + 32);
            }
            if (respuesta == respuestas[preguntasElegidas[i]]){
                System.out.println("Correct :)");
                fallos = 0;
                aciertos++;
            }else {
                System.out.println("Incorrect :(\nThe right option was " + respuestas[preguntasElegidas[i]]);
                fallos++;
            }
            if (fallos == 3){
                System.out.println("Oh no, you failed three questions in a row, you have lost :(");
                salir = true;
            }
            contador++;
        }
        return aciertos;
    }

    /**
     * Metodo que pregunta al usuario si quiere ver las reglas y dependiendo de la respuesta las muestra o pasa a lo
     * siguiente
     */

    private static void reglas() {
        String verReglas;
        boolean salir, repetirReglas;

        salir = false;
        repetirReglas = false;
        while (!salir) {
            System.out.println("Do u wanna see the rules?");
            verReglas = ToolsBrito.leerString();
            if (verReglas.equalsIgnoreCase("yes")) {
                do {
                    System.out.println("Basically you will see one question with diverse options and you need to put the right option below.\nYour results will be shown at the end, but if you fail more than 3 questions on a row you will instantly lose");
                    System.out.println("Did you understand everything?");
                    verReglas = ToolsBrito.leerString();
                    if (verReglas.equalsIgnoreCase("yes")) {
                        System.out.println("Then let's start");
                        salir = true;
                        repetirReglas = true;
                    } else if (verReglas.equalsIgnoreCase("no")) {
                        System.out.println("Okay i can repeat so there's no problem");
                    }else {
                        System.out.println(MENSAJE_ERROR);
                    }
                }while(!repetirReglas);
            } else if (verReglas.equalsIgnoreCase("no")) {
                System.out.println("okay then let's start");
                salir = true;
            } else {
                System.out.println(MENSAJE_ERROR);
            }
        }
    }

    /**
     * Metodo que pregunta al usuario cuantas preguntas quiere hacer y si introduce un valor no valido muestra mensaje
     * de error y pasa a lo siguiente
     *
     * @return Un int con el numero de preguntas que se realizaran
     */

    private static int preguntarNumero() {
        int numeroPreguntas;
        boolean salir = false;

        do {
            System.out.println("How many questions do you wanna try?");
            numeroPreguntas = ToolsBrito.leerInt();
            if (numeroPreguntas < 5 || numeroPreguntas > 20){
                System.out.println("ERROR!!!\nPlease choose a value between 5 and 20");
            }else {
                System.out.println("Okay so you will try " + numeroPreguntas + " questions");
                salir = true;
            }
        }while (!salir);
        return numeroPreguntas;
    }

    /**
     * Metodo donde se elijen aleatoriamente el indice de las preguntas que se haran y se asegura que no se repitan
     *
     * @param numeroPreguntas el numero de preguntas que quiere realizar el usuario
     * @return un array de ints donde estan los indices de las preguntas
     */

    private static int[] elegirPreguntas(int numeroPreguntas) {
        int[] preguntasElegidas = new int[numeroPreguntas];
        Arrays.fill(preguntasElegidas, -1);

        boolean elegida;
        int opcionElegida, posicion;

        posicion = 0;
        while (posicion != numeroPreguntas) {
            elegida = false;
            int i = 0;
            opcionElegida = (int) (Math.random() * NUMERO_PREGUNTAS);
            do {
                if (opcionElegida == preguntasElegidas[i]) {
                    elegida = true;
                }
                i++;
            } while (i < numeroPreguntas && !elegida);
            if (!elegida) {
                preguntasElegidas[posicion] = opcionElegida;
                posicion++;
            }
        }
        return preguntasElegidas;
    }

    /**
     * Metodo que muestra los resultados finales y pregunta al usuario si quiere jugar otra vez
     *
     * @param aciertos               cantidad de aciertos
     * @param fallos                 cantidad de preguntas fallidas
     * @param numeroPreguntasUsuario El numero de preguntas totales que se iban a hacer
     * @param nombre
     * @return Un boolean segun la respuesta del usuario para jugar otra vez o no
     */

    private static boolean cerrarJuego(int aciertos, int fallos, int numeroPreguntasUsuario, String nombre) {
        double porcentaje;
        boolean salir, cerrar;
        String respuesta;

        cerrar = false;
        salir = false;
        porcentaje = (double) (aciertos * 100) / numeroPreguntasUsuario;
        System.out.printf("You get an %.2f of the answers right\n", porcentaje);
        System.out.print("with " + aciertos + "right and " + fallos + " fails");
        if (porcentaje >= 0 && porcentaje <= 33){
            System.out.println(RESULTADOS_BAJOS);
        } else if (porcentaje >= 34 && porcentaje <= 66) {
            System.out.println(RESULTADOS_MEDIOS);
        } else if (porcentaje >= 67 && porcentaje <= 99) {
            System.out.println(RESULTADOS_ALTAS);
        }else {
            System.out.println(RESULTADOS_PERFECTOS);
        }
        while (!cerrar){
            System.out.println("Do you wanna play again");
            respuesta = ToolsBrito.leerString();
            if (respuesta.equalsIgnoreCase("yes")){
                System.out.println("Until next time.");
                cerrar = true;
            } else if (respuesta.equalsIgnoreCase("no")) {
                cerrar = true;
                salir = true;
            }else{
                System.out.println(MENSAJE_ERROR);
            }
        }
        saveData(nombre, aciertos, fallos);
        return salir;
    }

    private static void saveData(String nombre, int aciertos, int fallos) {
        Date fechaActual = new Date();
        String line = nombre + ';' + aciertos + ';' + fallos + ';' + fechaActual;
        File fileName = new File("src/resources/datos.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName.getAbsoluteFile(), true))){
            bw.write(line);
            bw.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("AN ERROR HAS OCURRED");
        }
    }

    /**
     * Metodo que pregunta al usuario si quiere jugar y en caso de responder algo que no sea si o no muestra un mensaje
     * de error
     *
     * @return Un boolean con el que se accede al resto del programa
     */
    private static boolean empezarJuego() {
        boolean salir, noJugar;
        String jugar;

        salir = false;
        noJugar = false;
        while (!salir) {
            System.out.println("Do you wanna play a quiz about java?");
            jugar = ToolsBrito.leerString();
            if (jugar.equalsIgnoreCase("yes")) {
                salir = true;
            } else if (jugar.equalsIgnoreCase("no")) {
                salir = true;
                noJugar = true;
            } else {
                System.out.println(MENSAJE_ERROR);
            }
        }
        return noJugar;
    }
}
