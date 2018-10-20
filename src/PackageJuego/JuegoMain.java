package PackageJuego;

import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JuegoMain {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        iniciarMenu(sistema);
    }

    private static void iniciarMenu(Sistema sistema) {
        Scanner scan = new Scanner(System.in);
        int opcionElegida = 0;
        while (opcionElegida != 5) {
            imprimirMenu();
            opcionElegida = pedirUnIntValidado(1, 5, "Eliga una Opción de el 1 al 5 :",
                    "Ingrese nuevamente! (1-5) :");
            switch (opcionElegida) {
                case 1: //BIEN
                    System.out.println("\n- Registrar un nuevo jugador -\n");
                    altaJugador(sistema);
                    break;
                case 2://BIEN
                    System.out.println("\n- Jugar-\n");
                    if (sistema.getListaJugadores().size() >= 2) {
                        jugar(sistema, 8, 9);
                    } else {
                        System.out.println("No hay suficientes jugadores! Deben haber almenos 2");
                    }
                    break;
                case 3://BIEN
                    System.out.println("\n- Historial -\n");
                    historial(sistema);
                    break;
                case 4://BIEN
                    System.out.println("\n- Ranking -\n");
                    imprimirRanking(sistema);
                    break;
                case 5://BIEN
                    System.out.println("\n- Cerrar aplicacion -\n");
                    break;

            }//Fin del Switch
        }//Fin del While
    }//Fin metodo InicioMenu

    private static void imprimirMenu() {
        java.lang.System.out.println("\n*******************************");
        java.lang.System.out.println("*     Main Menu     *");
        java.lang.System.out.println("*******************************");
        java.lang.System.out.println("\nMenú de Opciones :\n");
        java.lang.System.out.println("1 - Nuevo registro de jugador.");
        java.lang.System.out.println("2 - Jugar");
        java.lang.System.out.println("3 - Historial");
        java.lang.System.out.println("4 - Ranking");
        java.lang.System.out.println("5 - Cerrar aplicacion \n");
    }

    public static int pedirUnIntValidado(int desde, int hasta, String textoInstruccion, String textoError) {
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        int dato = 0;
        while (!done) {
            try {
                System.out.println(textoInstruccion);
                dato = scan.nextInt();

                while (!validarPorRango(dato, desde, hasta)) {
                    System.out.println(textoError);
                    dato = scan.nextInt();
                }
                done = true;
            } catch (InputMismatchException e) {
                System.out.println("Ingrese solo Números!");
                scan.nextLine();
            }
        }
        return dato;
    }

    public static void altaJugador(Sistema system) {
        String nombre = pedirUnString("Ingrese el nombre del jugador :");
        String alias = pedirUnString("Ingrese el alias del jugador:");
        int edad = pedirUnIntValidado(0, 100, "Ingrese la edad del Jugador :", "Edad incorrecta (0-100) Ingrese nuevamente :");
        while (system.existeAlias(alias)) {
            System.out.println("---Ya existe un Jugador con Dicho Alias!---");
            alias = pedirUnString("Ingrese Nuevamente el alias :");
        }
        system.agregarJugador(nombre, alias, edad);
        System.out.println("\nJugador Agregado con Exito!");
    }

    public static String pedirUnString(String textoInstruccion) {
        java.lang.System.out.println(textoInstruccion);
        Scanner scan = new Scanner(java.lang.System.in);
        return scan.nextLine();
    }

    public static boolean validarPorRango(int dato, int desde, int hasta) {
        return desde <= dato && dato <= hasta;
    }//Fin metodo validarPorRango

    public static int[][] crearTableroPred(int unaCantidadFilas, int unaCantidadColumnas) {
        int cantidadfilas = unaCantidadFilas;
        int cantidadcolumnas = unaCantidadColumnas;
        int[][] tablero = new int[cantidadfilas][cantidadcolumnas];
        for (int i = 0; i < tablero[0].length; i++) {
            tablero[0][i] = i;
        }
        for (int i = 0; i < tablero[0].length; i++) {
            tablero[7][i] = 8 - i;
        }
        return tablero;
    }

    public static void imprimirTablero(int[][] unTablero) {
        for (int[] unTablero1 : unTablero) {
            for (int j = 0; j < unTablero[0].length; j++) {
                System.out.print(unTablero1[j] + " ");
            }
            System.out.println();
        }

    }

    public static int[][] movimientoRojoParm(int[][] unTablero, String mov) {
        int[][] tablero = unTablero;
        String in = mov;
        if (in.contains("D")) {
            int posicioni = 0;
            int posicionj = 0;
            int posicioniNueva = 0;
            int posicionjNueva = 0;
            String result = in.replaceAll("[^0-9]+", "");
            int resultado = Integer.parseInt(result);
            for (int i = 2; i < tablero.length; i++) {
                for (int j = 0; j < tablero[0].length; j++) {
                    if (tablero[i][j] == resultado) {
                        posicioni = i;
                        posicionj = j;
                    }

                }

            }
            posicioniNueva = posicioni - 1;
            posicionjNueva = posicionj + 1;

            tablero[posicioniNueva][posicionjNueva] = tablero[posicioni][posicionj];
            tablero[posicioni][posicionj] = 0;

        }

        if (in.contains("A")) {
            int posicioni = 0;
            int posicionj = 0;
            String result = in.replaceAll("[^0-9]+", "");
            int resultado = Integer.parseInt(result);
            for (int i = 2; i < tablero.length; i++) {
                for (int j = 0; j < tablero[0].length; j++) {
                    if (tablero[i][j] == resultado) {
                        posicioni = i;
                        posicionj = j;
                    }

                }

            }

            System.out.println(posicioni + " " + posicionj);
            System.out.println(tablero[posicioni][posicionj]);
            tablero[posicioni - 1][posicionj] = tablero[posicioni][posicionj];
            tablero[posicioni][posicionj] = 0;
        }

        if (in.contains("I")) {
            int posicioni = 0;
            int posicionj = 0;
            String result = in.replaceAll("[^0-9]+", "");
            int resultado = Integer.parseInt(result);
            for (int i = 2; i < tablero.length; i++) {
                for (int j = 0; j < tablero[0].length; j++) {
                    if (tablero[i][j] == resultado) {
                        posicioni = i;
                        posicionj = j;
                    }

                }

            }

            System.out.println(posicioni + " " + posicionj);
            System.out.println(tablero[posicioni][posicionj]);

            if (posicionj != 0) {
                tablero[posicioni - 1][posicionj - 1] = tablero[posicioni][posicionj];
                tablero[posicioni][posicionj] = 0;

            } else {
                System.out.println("No se puede hacer el movimiento");
            }

        }
        return tablero;
    }
    
    public static void setearPosiciones(Ficha ficha, int numficha,int posicioninueva,int posicionjnueva){
        int [] nuevasposicionesi = ficha.getPosicionesi();
                        int[] nuevasposicionesj = ficha.getPosicionesj();
                        nuevasposicionesi[numficha]= posicioninueva;
                        nuevasposicionesj[numficha]= posicionjnueva;
                        ficha.setPosicionesi(nuevasposicionesi);
                        ficha.setPosicionesj(nuevasposicionesj);
        
    }

    public static void movimiento(int[][] unTablero, int[] fichasiinvalidas, int[] fichasjinvalidas, int color,int[]fichasvalidas,int[]fichasjvalidas,Ficha ficha) {
        if (color == 0) {
            boolean turno = true;
            while (turno) {
                int[][] tablero = unTablero;
                System.out.println("Jugador rojo, eliga un movimiento");
                String in;
                Scanner intro = new Scanner(System.in);
                in = intro.nextLine();
                String result = in.replaceAll("[^0-9]+", "");
                int resultado = Integer.parseInt(result);
                int posicioni = 0;
                int posicionj = 0;
                int posicioniNueva = 0;
                int posicionjNueva = 0;
                if (in.contains("D")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    posicioniNueva = posicioni - 1;
                    posicionjNueva = posicionj + 1;

                    if (posicionj != 8 && tablero[posicioniNueva][posicionjNueva] == 0) {
                        tablero[posicioniNueva][posicionjNueva] = tablero[posicioni][posicionj];
                        tablero[posicioni][posicionj] = 0;
                        setearPosiciones(ficha,resultado,posicioniNueva,posicionjNueva);
                        imprimirTablero(tablero);
                    } else {
                        System.out.println("La posicion a donde desea moverse ya esta ocupada");
                    }
                }

                if (in.contains("A")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    if (posicioni != 0 && tablero[posicioni - 1][posicionj] == 0) {
                        tablero[posicioni - 1][posicionj] = tablero[posicioni][posicionj];
                        tablero[posicioni][posicionj] = 0;
                        setearPosiciones(ficha,resultado,posicioni - 1,posicionj);
                        imprimirTablero(tablero);
                        posiblesMovimientos(posicioni, posicionj, tablero);
                    } else {
                        System.out.println("Este movimiento no es posible, porque ya esta ocupado");
                    }
                }

                if (in.contains("I")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    if (posicionj != 0 && tablero[posicioni - 1][posicionj - 1] == 0) {
                        tablero[posicioni - 1][posicionj - 1] = tablero[posicioni][posicionj];
                        tablero[posicioni][posicionj] = 0;
                        setearPosiciones(ficha,resultado,posicioni - 1,posicionj-1);
                        imprimirTablero(tablero);
                    } else {
                        System.out.println("No se puede hacer el movimiento");
                    }

                }
                turno = posiblesMovimientos(posicioni, posicionj, tablero);
            }
        } else {
            boolean turno = true;
            while (turno) {
                int[][] tablero = unTablero;
                System.out.println("Jugador azul, eliga un movimiento");
                String in;
                Scanner intro = new Scanner(System.in);
                in = intro.nextLine();
                String result = in.replaceAll("[^0-9]+", "");
                int resultado = Integer.parseInt(result);
                int posicioni = 0;
                int posicionj = 0;
                int posicioniNueva = 0;
                int posicionjNueva = 0;
                if (in.contains("D")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    posicioniNueva = posicioni + 1;
                    posicionjNueva = posicionj + 1;

                    if (posicionj != 8 && tablero[posicioniNueva][posicionjNueva] == 0) {
                        tablero[posicioniNueva][posicionjNueva] = tablero[posicioni][posicionj];
                        tablero[posicioni][posicionj] = 0;
                        setearPosiciones(ficha,resultado,posicioniNueva,posicionjNueva);
                        imprimirTablero(tablero);
                        posiblesMovimientos(posicioniNueva, posicionjNueva, tablero);
                    } else {
                        System.out.println("El movimiento no es posible");
                    }
                }

                if (in.contains("A")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    if (posicioni != 7 && tablero[posicioni + 1][posicionj] == 0) {
                        tablero[posicioni + 1][posicionj] = tablero[posicioni][posicionj];
                        tablero[posicioni][posicionj] = 0;
                        setearPosiciones(ficha,resultado,posicioni +1,posicionj);
                        imprimirTablero(tablero);
                        posiblesMovimientos(posicioni, posicionj, tablero);
                    } else {
                        System.out.println("Este movimiento no es posible, porque ya esta ocupado");
                    }
                }

                if (in.contains("I")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    if (posicionj != 0 && tablero[posicioni + 1][posicionj - 1] == 0) {
                        tablero[posicioni + 1][posicionj - 1] = tablero[posicioni][posicionj];
                        tablero[posicioni][posicionj] = 0;
                        setearPosiciones(ficha,resultado,posicioni +1,posicionj-1);
                        imprimirTablero(tablero);
                    } else {
                        System.out.println("No se puede hacer el movimiento");
                    }

                }
                turno = posiblesMovimientos(posicioni, posicionj, tablero);
            }
        }
    }

    public static Jugador[] elegirJugador(Sistema sistema, String color1, String color2) {
        Jugador[] jugadoresElegidos = new Jugador[3];
        ArrayList<Jugador> auxiliar = sistema.getListaJugadores();
        System.out.println("Quien sera el jugador" + color1 + "?");
        for (int i = 0; i < auxiliar.size(); i++) {
            System.out.println("-" + i + auxiliar.get(i));
        }
        int elegido1 = pedirUnIntValidado(0, auxiliar.size() - 1, "Seleccione de la lista l jugador" + color1, "Jugador no valido, ingrese denuevo");
        jugadoresElegidos[1] = auxiliar.get(elegido1);
        auxiliar.remove(auxiliar.get(elegido1));
        for (int i = 0; i < auxiliar.size(); i++) {
            System.out.println("-" + i + auxiliar.get(i));
        }
        int elegido2 = pedirUnIntValidado(0, auxiliar.size() - 1, "Seleccione de la lista l jugador" + color2, "Jugador no valido,ingrese denuevo");
        jugadoresElegidos[2] = auxiliar.get(elegido2);
        return jugadoresElegidos;
    }

    public static int[] generarPosicionesAbajoFila() {
        int[] posiciones = new int[9];
        for (int i = 0; i < posiciones.length; i++) {
            posiciones[i] = 7;
        }
        return posiciones;
    }

    public static int[] generarPosicionesAbajoColumnas() {
        int[] posiciones = new int[9];
        for (int i = 0; i < posiciones.length; i++) {
            posiciones[i] = 8 - i;
        }
        return posiciones;
    }

    public static int[] generarPosicionesArribaFila() {
        int[] posiciones = new int[9];
        for (int i = 0; i < posiciones.length; i++) {
            posiciones[i] = 0;
        }
        return posiciones;
    }

    public static int[] generarPosicionesArribaColumnas() {
        int[] posiciones = new int[9];
        for (int i = 0; i < posiciones.length; i++) {
            posiciones[i] = i;
        }
        return posiciones;
    }

    public static int[] recorrerMatrizValida(int[][] matriz, int[] posicionesinvalidas, int[] posicionesjinvalidas, int numero) {
        int posicioni = 0;
        int posicionj = 0;
        int[] resultado = new int[2];
        resultado[0] = posicioni;
        resultado[1] = posicionj;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                for (int k = 0; k < posicionesinvalidas.length; k++) {
                    if (i != posicionesinvalidas[k] || j != posicionesjinvalidas[k]) {
                        if (matriz[i][j] == numero) {
                            posicioni = i;
                            posicionj = j;

                        }

                    }
                }
            }
        }
        return resultado;
    }

    public static void jugar(Sistema sistema, int unaCantidadFilas, int unaCantidadColumnas) {
        Jugador[] jugadoresElegidos = elegirJugador(sistema, "rojo", "azul");
        Jugador jugadorRojo = jugadoresElegidos[1];
        Jugador jugadorAzul = jugadoresElegidos[2];
        int[][] tablero = crearTableroPred(unaCantidadFilas, unaCantidadColumnas);
        int opcion = elegirTerminacion();
        Partida partida = new Partida(jugadorRojo, jugadorAzul);
        Ficha rojas = new Ficha("rojo", generarPosicionesAbajoFila(), generarPosicionesAbajoColumnas());
        Ficha azules = new Ficha("azul", generarPosicionesArribaFila(), generarPosicionesArribaColumnas());
        
        if (opcion == 1) {
            System.out.println("Con cuantos movimientos desea jugar?");
            Scanner in = new Scanner(System.in);
            int maximo = in.nextInt();
            int contador = 0;
            while (contador < maximo) {
                imprimirTablero(tablero);
                movimiento(tablero, azules.getPosicionesi(), azules.getPosicionesj(),0,rojas.getPosicionesi(),rojas.getPosicionesj(),rojas);
                contador++;
                if (contador < maximo) {
                    imprimirTablero(tablero);
                    movimiento(tablero,rojas.getPosicionesi(),rojas.getPosicionesj(),1,azules.getPosicionesi(),azules.getPosicionesj(),azules);
                    contador++;
                }
            }
        }
        if (opcion == 2) {
            boolean nopaso = false;
            while (!nopaso) {
                imprimirTablero(tablero);
                movimiento(tablero,rojas.getPosicionesi(),rojas.getPosicionesj(),1,azules.getPosicionesi(),azules.getPosicionesj(),azules);
                verificarPaso(tablero, 1);
                imprimirTablero(tablero);
                movimiento(tablero, azules.getPosicionesi(), azules.getPosicionesj(),0,rojas.getPosicionesi(),rojas.getPosicionesj(),rojas);
            }
        }
        if (opcion == 3) {

        }
    }

    public static boolean verificarPaso(int mat[][], int lado) {
        boolean paso = false;
        if (lado == 1) {
            for (int i = 6; i > 0; i--) {
                for (int j = 0; j < mat[0].length; j++) {
                }
            }

        }
        return paso;
    }

    public static void diagonal(int filaconsulta, int columnaconsulta, int mat[][]) {
        int suma = 0;
        int consulta = mat[filaconsulta][columnaconsulta];
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 9; k++) {
                if (i != filaconsulta && k != columnaconsulta) {
                    if (Math.abs(i - filaconsulta) == Math.abs(k - columnaconsulta)) {

                    }
                }

            }
        }
    }

    public static void imprimirRanking(Sistema unSistema) {
        boolean vacia = unSistema.getListaJugadores().isEmpty();
        if (!vacia) {
            for (int i = 0; i < unSistema.getListaJugadores().size(); i++) {
                System.out.println(unSistema.getListaJugadores().get(i));
            }
        } else {
            System.out.println("No hay jugadores registrados!");
        }
    }

    public static void replicarPartida(Sistema unSistema, Partida unaPartida) {
        ArrayList<String> listamovimientosx = unaPartida.getListaMovimientos();
        System.out.println(unSistema.getListaPartidas());

    }

    public static void cambiarVisualizacion(int[][] unaMat) {

    }

    public static boolean posiblesMovimientos(int unNroFila, int unNroColumna, int[][] matriz) {
        boolean esPosible=false;
        int filas = 8;
        int columnas = 9;
        int cont = 1;
        int sumaDiagonalD = 0;
        int sumaDiagonalI = 0;
        int sumaHorizontal = 0;
        int sumaVertical = 0;
        if (unNroFila < filas && unNroColumna < columnas) {
            int elem = matriz[unNroFila][unNroColumna];
            int x = 0;
            if (columnas > filas) {
                x = columnas;
            } else {
                x = filas;
            }

            for (int i = 0; i < x; i++) {
                for (int j = 1; j < 9; j++) {
                    if (unNroFila + cont < filas && unNroColumna + cont < columnas) {
                        if (matriz[unNroFila + cont][unNroColumna + cont] == j) {
                            sumaDiagonalD = sumaDiagonalD + j;
                        }
                    }

                    if (unNroFila + cont < filas && unNroColumna - cont >= 0) {
                        if (matriz[unNroFila + cont][unNroColumna - cont] == elem) {
                            sumaDiagonalD = sumaDiagonalD + j;
                        }
                    }

                    if (unNroColumna + cont < columnas && unNroFila - cont >= 0) {
                        if (matriz[unNroFila - cont][unNroColumna + cont] == elem) {
                            sumaDiagonalI = sumaDiagonalI + j;
                        }
                    }

                    if (unNroFila - cont >= 0 && unNroColumna - cont >= 0) {
                        if (matriz[unNroFila - cont][unNroColumna - cont] == elem) {
                            sumaDiagonalI = sumaDiagonalI + j;
                        }
                    }
                }
                cont++;
            }

            if (sumaDiagonalD != elem) {
                System.out.println("Puede mover " + sumaDiagonalD);
            }

            if (sumaDiagonalI != elem) {
                System.out.println("Puede mover " + sumaDiagonalI);

            }

            if (sumaHorizontal != elem) {
                System.out.println("Puede mover " + sumaHorizontal);

            }
            if (sumaVertical != elem) {
                System.out.println("Puede mover " + sumaVertical);
            }
            if (sumaVertical == elem && sumaHorizontal == elem && sumaDiagonalD == elem && sumaDiagonalI == elem) {
                System.out.println("No puede realizar movimientos, siguiente turno");
            } else {
            }
        }
        return esPosible;
    }

    public static void historial(Sistema sistema) {
        boolean continuar = true;
        int[][] tablero = crearTableroPred(8, 9);
        ArrayList<Partida> partidas = sistema.getListaPartidas();
        boolean vacia = partidas.isEmpty();
        if (!vacia) {
            for (int i = 0; i < partidas.size(); i++) {
                System.out.println(partidas.get(i));
            }
            Scanner in = new Scanner(System.in);
            Partida elegida = partidas.get(in.nextInt());
            System.out.println("Partida elegida" + elegida);
            while (continuar) {
                for (int i = 0; i < elegida.getListaMovimientos().size(); i++) {
                    System.out.println("Movimiento numero" + i + 1);
                    tablero = movimientoRojoParm(tablero, elegida.getListaMovimientos().get(i));
                    imprimirTablero(tablero);
                    enterContinuar();
                }
                continuar = false;
                System.out.println("Todos los movimientos mostrados");
            }
        } else {
            System.out.println("No hay partidas en su historial!");
        }
    }

    public static void enterContinuar() {
        System.out.println("Press \"ENTER\" to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
        }
    }

    public static int elegirTerminacion() {
        Scanner in = new Scanner(System.in);
        System.out.println("METODOS DE TERMINACION");
        System.out.println("1- Movimientos totales a eleccion");
        System.out.println("2- Alcanzar con una ficha el lado opuesto");
        System.out.println("3- Alcanzar con todas las fichas el lado opuesto");
        int opcion = pedirUnIntValidado(1, 3, "Eliga un metodo de terminacion", "Valor fuera de rango, intente denuevo");
        return opcion;
    }
}
