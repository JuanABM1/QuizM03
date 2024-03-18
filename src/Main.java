import java.util.Arrays;

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
        int[] preguntasElegidas;

        salir = false;

        noJugar = empezarJuego();
        // Si el usuario responde que no quiere jugar se mostrara un mensaje y se cerrara el programa
        if (!noJugar) {
            while (!salir) {
                numeroPreguntasUsuario = preguntarNumero();
                preguntasElegidas = elegirPreguntas(numeroPreguntasUsuario);
                reglas();
                aciertos = jugar(preguntasElegidas, preguntas, posiblesRespuestas, respuestasCorrectas);
                fallos = numeroPreguntasUsuario - aciertos; // Se calcula el numero de fallos para mostrarselo al usuario
                salir = cerrarJuego(aciertos, fallos, numeroPreguntasUsuario);
            }
        }else {
            System.out.println("Well maybe next time");
        }
    }

    /**
     * Metodo que crea un array de chars y lo devuelve para almacenarlo
     *
     * @return Array con las respuestas correctas para las preguntas
     */
    private static char[] respuestasCorrectas() {
        char[] respuestas = new char[NUMERO_PREGUNTAS];
        respuestas[0] = 'c';
        respuestas[1] = 'b';
        respuestas[2] = 'b';
        respuestas[3] = 'a';
        respuestas[4] = 'a';
        respuestas[5] = 'b';
        respuestas[6] = 'a';
        respuestas[7] = 'c';
        respuestas[8] = 'b';
        respuestas[9] = 'a';
        respuestas[10] = 'c';
        respuestas[11] = 'c';
        respuestas[12] = 'c';
        respuestas[13] = 'a';
        respuestas[14] = 'c';
        respuestas[15] = 'b';
        respuestas[16] = 'a';
        respuestas[17] = 'b';
        respuestas[18] = 'c';
        respuestas[19] = 'a';

        return respuestas;
    }

    /**
     * Metodo que crea un array con las posibilidades que pueden mostrarse al usuario
     *
     * @return El array de Strings creado y lo almacena en el Main
     */
    private static String[] posiblesRespuestas() {
        String[] posiblesRespuestas = new String[NUMERO_PREGUNTAS];
        posiblesRespuestas[0] = "a. echo(\"Hello World\");\nb. print (\"Hello World\");\nc. System.out.println(\"Hello World\");";
        posiblesRespuestas[1] = "a. /* This is a comment\nb. // This is a comment\nc. # This is a comment";
        posiblesRespuestas[2] = "a. string\nb. String\nc. Txt";
        posiblesRespuestas[3] = "a. int x = 5;\nb. float x = 5\nc. num x = 5";
        posiblesRespuestas[4] = "a. length()\nb. len()\nc. getLength()";
        posiblesRespuestas[5] = "a. The * sign\nb. The + sign";
        posiblesRespuestas[6] = "a. False\nb. True";
        posiblesRespuestas[7] = "a. touppercase()\nb. upperCase()\nc. toUpperCase()";
        posiblesRespuestas[8] = "a. {}\nb. []";
        posiblesRespuestas[9] = "a. 0\nb. 1";
        posiblesRespuestas[10] = "a. x\nb. %.\nc. *";
        posiblesRespuestas[11] = "a. if x > y then:\nb. if x > y:\nc. if (x > y)";
        posiblesRespuestas[12] = "a. while x > y:\nb. while x > y {\nc. while (x > y)";
        posiblesRespuestas[13] = "a. return\nb. get\nc. void";
        posiblesRespuestas[14] = "a. (methodName)\nb. methodName[]\nc. methodName()";
        posiblesRespuestas[15] = "a. methodName;\nb. methodName();\nc. methodName[];";
        posiblesRespuestas[16] = "a. ==\nb. =\nc. ><";
        posiblesRespuestas[17] = "a. for\nb. while\nc. do-while";
        posiblesRespuestas[18] = "a. While\nb. do-while\nc. for";
        posiblesRespuestas[19] = "a. toLowerCase()\nb. tolowercase()\nc. low()";

        return posiblesRespuestas;
    }

    /**
     * Metodo que crea un array con todas las preguntas posibles
     *
     * @return el array y lo almacena
     */
    private static String[] preguntas() {
        String[] preguntas = new String[NUMERO_PREGUNTAS];
        preguntas[0] = "What is a correct syntax to output \"Hello World\" in Java?";
        preguntas[1] = "How do you insert COMMENTS in Java code?";
        preguntas[2] = "Which data type is used to create a variable that should store text?";
        preguntas[3] = "How do you create a variable with the numeric value 5?";
        preguntas[4] = "Which method can be used to find the length of a string?";
        preguntas[5] = "Which operator is used to add together two values?";
        preguntas[6] = "The value of a string variable can be surrounded by single quotes.";
        preguntas[7] = "Which method can be used to return a string in upper case letters?";
        preguntas[8] = "To declare an array in Java, define the variable type with:";
        preguntas[9] = "Array indexes start with:";
        preguntas[10] = "Which operator is used to multiply numbers?";
        preguntas[11] = "How do you start writing an if statement in Java?";
        preguntas[12] = "How do you start writing a while loop in Java?";
        preguntas[13] = "Which keyword is used to return a value inside a method?";
        preguntas[14] = "How do you create a method in Java?";
        preguntas[15] = "How do you call a method in Java?";
        preguntas[16] = "Which operator can be used to compare two values?";
        preguntas[17] = "Which of the following loops can contain code that may never be executed:";
        preguntas[18] = "Which of the following loops is separated in three parts: initialization, condition and increment?";
        preguntas[19] = "Which method can be used to return a string in lower case letters?";

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
            respuesta = teclat.Teclat.llegirChar();
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
            verReglas = teclat.Teclat.llegirString();
            if (verReglas.equalsIgnoreCase("yes")) {
                do {
                    System.out.println("Basically you will see one question with diverse options and you need to put the right option below.\nYour results will be shown at the end, but if you fail more than 3 questions on a row you will instantly lose");
                    System.out.println("Did you understand everything?");
                    verReglas = teclat.Teclat.llegirString();
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
            numeroPreguntas = teclat.Teclat.llegirInt();
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
     * @param aciertos cantidad de aciertos
     * @param fallos cantidad de preguntas fallidas
     * @param numeroPreguntasUsuario El numero de preguntas totales que se iban a hacer
     * @return Un boolean segun la respuesta del usuario para jugar otra vez o no
     */

    private static boolean cerrarJuego(int aciertos, int fallos, int numeroPreguntasUsuario) {
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
            respuesta = teclat.Teclat.llegirString();
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
        return salir;
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
            jugar = teclat.Teclat.llegirString();
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
