//author Valentin Sanchez Ignacio Pigatto
package PackageJuego;

import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import paqueteInterfaz.MenuPrincipal;

public class JuegoMain {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        iniciarMenu(sistema);
        MenuPrincipal menu = new MenuPrincipal(sistema);
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
        java.lang.System.out.println("\n  *****************************");
        java.lang.System.out.println("  *      MENU PRINCIPAL       *");
        java.lang.System.out.println("  *****************************");
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

    // Metodo para agregar un jugador al sistema, corrobora que no hayan dos con el mismo alias
    public static void altaJugador(Sistema system) {
        String nombre = pedirUnString("Ingrese el nombre del jugador :");
        String alias = pedirUnString("Ingrese el alias del jugador:");
        int edad = pedirUnIntValidado(0, 100, "Ingrese la edad del Jugador :", "Edad incorrecta (0-100) Ingrese nuevamente :");
        while (system.existeAlias(alias)) {
            System.out.println("---Ya existe un Jugador con Dicho Alias!---");
            alias = pedirUnString("Ingrese Nuevamente el alias :");
        }
        system.agregarJugador(nombre, alias, edad, 0, new Ficha());
        System.out.println("\nJugador Agregado con Exito!");
    }

    public static String pedirUnString(String textoInstruccion) {
        System.out.println(textoInstruccion);
        Scanner scan = new Scanner(System.in);
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

    public static void setearPosiciones(Ficha ficha, int numficha, int posicioninueva, int posicionjnueva) {
        int[] nuevasposicionesi = ficha.getPosicionesi();
        int[] nuevasposicionesj = ficha.getPosicionesj();
        nuevasposicionesi[numficha] = posicioninueva;
        nuevasposicionesj[numficha] = posicionjnueva;
        ficha.setPosicionesi(nuevasposicionesi);
        ficha.setPosicionesj(nuevasposicionesj);

    }

    public static Posibles movimiento(int[][] unTablero, int[] fichasiinvalidas, int[] fichasjinvalidas, int color, int[] fichasvalidas, int[] fichasjvalidas, Ficha ficha, Partida partida,Boolean verParm) {
        boolean salir = false;
        int[][] tablero = unTablero;
        boolean ver = verParm;
        boolean pasar = false;
        int contador = 0;
        ArrayList<Integer> fichasPosibles = new ArrayList<Integer>();
        if (color == 0) {
            boolean esPosible = true;
            while (esPosible) {
                System.out.println("Jugador rojo, eliga un movimiento");
                String in;
                Scanner intro = new Scanner(System.in);
                in = intro.nextLine();
                String result = in.replaceAll("[^0-9]+", "");
                if (in.length() == 4) {
                    if (in.equals("VERR")) {
                        System.out.println("Verr");
                        ver = true;
                        result = "";
                    }
                    if (in.equals("VERN")) {
                        System.out.println("VERN");
                        ver = false;
                        result = "";
                    }
                }

                boolean esx = in.equals("X");
                if (in.length() == 1) {
                    if (in.equals("X")) {
                        System.out.println("Termino la partida");
                        esPosible = false;
                        salir = true;
                    }

                   
                    if (!pasar) {
                        if (in.length() == 1 && (!esx)) {
                            if ((in.equals("p"))) {
                                System.out.println("Necesita hacer un movimiento para poder pasar");

                            } else {
                                System.out.println("Valor no valido, ingrese denuevo");
                            }

                        }

                    }
                }

                if (pasar) {
                    if (in.length() == 1 && (!esx)) {
                        if (in.equals("p")) {
                            esPosible=false;
                            
                            
                        } else {
                            System.out.println("Valor no valido, ingrese denuevo");
                        }
                    }
                }

                if (in.length() == 3) {
                    System.out.println("Palabra incorrecta");
                }

                if (in.length() > 4) {
                    System.out.println("Palabra incorrecta");
                }
                int resultado = 0;
                if (!result.equals("")) {
                    resultado = Integer.parseInt(result);
                }
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
                        setearPosiciones(ficha, resultado, posicioniNueva, posicionjNueva);
                        partida.getListaMovimientos().add(in);
                        partida.getColores().add(color);
                        imprimir(tablero, ver);
                        contador++;
                    } else {
                        System.out.println("La posicion a donde desea moverse ya esta ocupada");
                    }

                    esPosible = posiblesMovimientos(posicioniNueva, posicionjNueva, tablero).getEsPosible();
                    if (esPosible) {
                        contador++;
                    }

                    pasar = true;

                }

                if (in.contains("A")) {

                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    if (posicioni != 0 && tablero[posicioni - 1][posicionj] == 0) {
                        tablero[posicioni - 1][posicionj] = tablero[posicioni][posicionj];
                        tablero[posicioni][posicionj] = 0;
                        setearPosiciones(ficha, resultado, posicioni - 1, posicionj);
                        partida.getListaMovimientos().add(in);
                        partida.getColores().add(color);
                        imprimir(tablero, ver);
                        esPosible = posiblesMovimientos(posicioni - 1, posicionj, tablero).getEsPosible();
                        if (esPosible) {
                            contador++;
                        }

                        contador++;
                        pasar = true;
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
                        setearPosiciones(ficha, resultado, posicioni - 1, posicionj - 1);
                        partida.getListaMovimientos().add(in);
                        partida.getColores().add(color);
                        imprimir(tablero, ver);
                        esPosible = posiblesMovimientos(posicioni - 1, posicionj - 1, tablero).getEsPosible();
                        contador++;
                        if (esPosible) {
                            contador++;
                        }

                        pasar = true;
                    } else {
                        System.out.println("No se puede hacer el movimiento");
                    }

                }
            }
        } else {
            boolean turno = true;
            while (turno) {
                System.out.println("Jugador azul, eliga un movimiento");
                String in;
                Scanner intro = new Scanner(System.in);
                in = intro.nextLine();
                String result = in.replaceAll("[^0-9]+", "");
                if (in.length() == 4) {
                    if (in.equals("VERR")) {
                        ver = true;
                        result = "";
                    }
                    if (in.equals("VERN")) {
                        ver = false;
                        result = "";
                    }

                }
                boolean esx = in.equals("X");
                if (in.length() == 1) {
                    if (in.equals("X")) {
                        System.out.println("Termino la partida");
                        turno = false;
                        salir = true;
                    }

                   
                    if (!pasar) {
                        if (in.length() == 1 && (!esx)) {
                            if ((in.equals("p"))) {
                                System.out.println("Necesita hacer un movimiento para poder pasar");

                            } else {
                                System.out.println("Valor no valido, ingrese denuevo");
                            }

                        }

                    }
                }

                if (pasar) {
                    if (in.length() == 1 && (!esx)) {
                        if (in.equals("p")) {
                            turno =false;
                            
                            
                        } else {
                            System.out.println("Valor no valido, ingrese denuevo");
                        }
                    }
                }
                if (in.length() == 3) {
                    System.out.println("Palabra incorrecta");
                }

                if (in.length() > 4) {
                    System.out.println("Palabra incorrecta");
                }
                int resultado = 0;
                if (!result.equals("")) {
                    resultado = Integer.parseInt(result);
                }

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
                        setearPosiciones(ficha, resultado, posicioniNueva, posicionjNueva);
                        partida.getListaMovimientos().add(in);
                        partida.getColores().add(color);
                        imprimir(tablero, ver);
                        turno = posiblesMovimientos(posicioniNueva, posicionjNueva, tablero).getEsPosible();
                        contador++;
                        pasar = true;
                        if (turno) {
                            contador++;
                        }

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
                        setearPosiciones(ficha, resultado, posicioni + 1, posicionj);
                        partida.getListaMovimientos().add(in);
                        partida.getColores().add(color);
                        imprimir(tablero, ver);
                        turno = posiblesMovimientos(posicioni + 1, posicionj, tablero).getEsPosible();
                        contador++;
                        if (turno) {
                            contador++;
                        }
                        pasar = true;
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
                        setearPosiciones(ficha, resultado, posicioni + 1, posicionj - 1);
                        partida.getListaMovimientos().add(in);
                        partida.getColores().add(color);
                        imprimir(tablero, ver);
                        turno = posiblesMovimientos(posicioni + 1, posicionj - 1, tablero).getEsPosible();
                        contador++;
                        pasar = true;
                        if (turno) {
                            contador++;
                        }
                    } else {
                        System.out.println("No se puede hacer el movimiento");
                    }

                }
            }

        }
        Posibles unPosibles = new Posibles(salir,fichasPosibles,tablero,contador);
        unPosibles.setVer(ver);
        return unPosibles;
        
    }

    public static Jugador[] elegirJugador(Sistema sistema, String color1, String color2) {
        Jugador[] jugadoresElegidos = new Jugador[3];
        ArrayList<Jugador> auxiliar = sistema.getListaJugadores();
        System.out.println("Quien sera el jugador " + " " + color1 + " " + "?");
        for (int i = 0; i < auxiliar.size(); i++) {
            System.out.println(" - " + i + " " + auxiliar.get(i));
        }
        int elegido1 = pedirUnIntValidado(0, auxiliar.size() - 1, "Seleccione de la lista un jugador " + " " + color1 + " ", " Jugador no valido, ingrese denuevo");
        jugadoresElegidos[1] = auxiliar.get(elegido1);
        Jugador jugadorRemovido = auxiliar.get(elegido1);
        auxiliar.remove(auxiliar.get(elegido1));
        for (int i = 0; i < auxiliar.size(); i++) {
            System.out.println(" - " + i + " " + auxiliar.get(i));
        }
        int elegido2 = pedirUnIntValidado(0, auxiliar.size() - 1, "Seleccione de la lista un jugador " + " " + color2 + " ", " Jugador no valido, ingrese denuevo");
        jugadoresElegidos[2] = auxiliar.get(elegido2);
        auxiliar.add(jugadorRemovido);
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
        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[0].length; j++) {

                if (matriz[i][j] == numero) {
                    boolean invalida = false;
                    for (int k = 0; k < posicionesinvalidas.length; k++) {
                        if ((i == posicionesinvalidas[k]) && (j == posicionesjinvalidas[k])) {
                            invalida = true;
                        }
                    }
                    if (!invalida) {
                        posicioni = i;
                        posicionj = j;
                    }
                }
            }
        }

        resultado[0] = posicioni;
        resultado[1] = posicionj;
        return resultado;
    }

    public static Jugador ganador(Jugador jugadoruno, Jugador jugadordos, int condicion, int[][] tablero) {
        Jugador elganador = jugadoruno;
        if (condicion == 1) {
            int puntosJugadorUno = 0;
            int puntosJugadorDos = 0;
            for (int i = 1; i < 9; i++) {
                int[] posicionesrojas = recorrerMatrizValida(tablero, jugadordos.getFichasJugador().getPosicionesi(), jugadordos.getFichasJugador().getPosicionesj(), i);
                int[] posicionesazules = recorrerMatrizValida(tablero, jugadoruno.getFichasJugador().getPosicionesi(), jugadoruno.getFichasJugador().getPosicionesj(), i);
                if (posicionesrojas[0] <= 3) {
                    puntosJugadorUno++;
                }

                if (posicionesazules[0] > 3) {
                    puntosJugadorDos++;
                }

            }
            if (puntosJugadorUno != puntosJugadorDos) {
                if (puntosJugadorUno > puntosJugadorDos) {
                    elganador = jugadoruno;
                } else {
                    elganador = jugadordos;
                }
            } else {
                elganador = new Jugador("Empate", "Empate", 0, 0, new Ficha());
            }
        }
        if (condicion == 2) {
            System.out.println("Condicion 2");
            for (int i = 1; i < 9; i++) {
                int[] posicionesrojas = recorrerMatrizValida(tablero, jugadordos.getFichasJugador().getPosicionesi(), jugadordos.getFichasJugador().getPosicionesj(), i);
                int[] posicionesazules = recorrerMatrizValida(tablero, jugadoruno.getFichasJugador().getPosicionesi(), jugadoruno.getFichasJugador().getPosicionesj(), i);
                if (posicionesrojas[0] <= 3) {
                    elganador = jugadoruno;
                } else {
                    if (posicionesazules[0] > 3) {
                        elganador = jugadordos;
                    }
                }
            }
        }
        if (condicion == 3) {
            System.out.println("Condicion 3");
            int rojaspasadas = 0;
            int azulespasadas = 0;
            for (int i = 1; i < 9; i++) {
                int[] posicionesrojas = recorrerMatrizValida(tablero, jugadordos.getFichasJugador().getPosicionesi(), jugadordos.getFichasJugador().getPosicionesj(), i);
                int[] posicionesazules = recorrerMatrizValida(tablero, jugadoruno.getFichasJugador().getPosicionesi(), jugadoruno.getFichasJugador().getPosicionesj(), i);
                if (posicionesrojas[0] <= 3) {
                    rojaspasadas++;
                }
                if (posicionesazules[0] > 3) {
                    azulespasadas++;
                }
            }
            if (rojaspasadas == 8) {
                elganador = jugadoruno;
            }
            if (azulespasadas == 8) {
                elganador = jugadordos;
            }
        }

        return elganador;
    }

    public static void jugar(Sistema sistema, int unaCantidadFilas, int unaCantidadColumnas) {
        Jugador[] jugadoresElegidos = elegirJugador(sistema, "rojo", "azul");
        Jugador jugadorRojo = jugadoresElegidos[1];
        Jugador jugadorAzul = jugadoresElegidos[2];
        int[][] tablero = crearTableroPred(unaCantidadFilas, unaCantidadColumnas);
        int opcion = elegirTerminacion();
        Partida partida = new Partida(jugadorRojo, jugadorAzul);
        Ficha rojas = new Ficha("rojo", generarPosicionesAbajoFila(), generarPosicionesAbajoColumnas());
        sistema.getListaFichas().add(rojas);
        Ficha azules = new Ficha("azul", generarPosicionesArribaFila(), generarPosicionesArribaColumnas());
        sistema.getListaFichas().add(azules);
        jugadorRojo.setFichasJugador(rojas);
        jugadorAzul.setFichasJugador(azules);
        int ganador = 0;
        Jugador jugadorGanador = new Jugador();
        boolean ver = false;
        if (opcion == 1) {
            System.out.println("Con cuantos movimientos desea jugar?");
            Scanner in = new Scanner(System.in);
            int maximo = in.nextInt();
            int contador = 0;
            boolean salir = false;
            while (contador < maximo && !salir) {
                imprimir(tablero, ver);
                Posibles unPosibles = movimiento(tablero, azules.getPosicionesi(), azules.getPosicionesj(), 0, rojas.getPosicionesi(), rojas.getPosicionesj(), rojas, partida,ver);
                salir = unPosibles.getEsPosible();
                tablero = unPosibles.getMatriz();
                ver = unPosibles.getVer();
                contador++;
                if (contador < maximo && !salir) {
                    imprimir(tablero, ver);
                    Posibles unPosible2 = movimiento(tablero, rojas.getPosicionesi(), rojas.getPosicionesj(), 1, azules.getPosicionesi(), azules.getPosicionesj(), azules, partida,ver);
                    tablero = unPosible2.getMatriz();
                    salir = unPosible2.getEsPosible();
                    ver = unPosible2.getVer();
                    contador++;
                }
            }
            jugadorGanador = ganador(jugadorRojo, jugadorAzul, 1, tablero);
        }
        if (opcion == 2) {
            boolean nopaso = true;
            boolean seguir = true;
            while (nopaso) {
                imprimir(tablero, ver);
                Posibles esPosible = movimiento(tablero, rojas.getPosicionesi(), rojas.getPosicionesj(), 1, azules.getPosicionesi(), azules.getPosicionesj(), azules, partida,ver);
                tablero = esPosible.getMatriz();
                ver = esPosible.getVer();
                nopaso = verificarPaso(tablero, jugadorRojo, jugadorAzul);
                if (!nopaso) {
                    jugadorGanador = jugadorRojo;
                }
                imprimir(tablero, ver);
                seguir = nopaso;
                if (seguir) {
                    Posibles esPosible2 = movimiento(tablero, azules.getPosicionesi(), azules.getPosicionesj(), 0, rojas.getPosicionesi(), rojas.getPosicionesj(), rojas, partida,ver);
                    tablero = esPosible2.getMatriz();
                    ver = esPosible2.getVer();
                    nopaso = verificarPaso(tablero, jugadorRojo, jugadorAzul);
                }
                if (!nopaso) {
                    jugadorGanador = jugadorAzul;
                }
            }
        }
        if (opcion == 3) {
            boolean paso = false;
            boolean seguir = false;
            while (!paso && !seguir) {
                int rojaspasadas = 0;
                int azulespasadas = 0;
                imprimir(tablero, ver);
                Posibles esPosible = movimiento(tablero, rojas.getPosicionesi(), rojas.getPosicionesj(), 1, azules.getPosicionesi(), azules.getPosicionesj(), azules, partida,ver);
                tablero = esPosible.getMatriz();
                seguir = esPosible.getEsPosible();
                ver = esPosible.getVer();
                imprimir(tablero, ver);
                jugadorGanador = ganador(jugadorRojo, jugadorAzul, 3, tablero);
                if (!seguir) {
                    Posibles esPosible2 = movimiento(tablero, azules.getPosicionesi(), azules.getPosicionesj(), 0, rojas.getPosicionesi(), rojas.getPosicionesj(), rojas, partida,ver);
                    tablero = esPosible2.getMatriz();
                    seguir = esPosible2.getEsPosible();
                    ver = esPosible2.getVer();
                    jugadorGanador = ganador(jugadorRojo, jugadorAzul, 3, tablero);
                    imprimir(tablero, ver);
                }
                for (int i = 1; i < 9; i++) {

                    int[] posicionesrojas = recorrerMatrizValida(tablero, jugadorAzul.getFichasJugador().getPosicionesi(), jugadorAzul.getFichasJugador().getPosicionesj(), i);
                    int[] posicionesazules = recorrerMatrizValida(tablero, jugadorRojo.getFichasJugador().getPosicionesi(), jugadorRojo.getFichasJugador().getPosicionesj(), i);
                    if (posicionesrojas[0] <= 3) {
                        rojaspasadas++;
                    }
                    if (posicionesazules[0] > 3) {
                        azulespasadas++;
                    }
                }
                if (rojaspasadas == 8) {
                    jugadorGanador = jugadorRojo;
                    paso = true;
                }
                if (azulespasadas == 8) {
                    jugadorGanador = jugadorAzul;
                    paso = true;
                }
            }
        }

        System.out.println(" La partida acabo ");
        if (!jugadorGanador.getAlias().equals("Empate")) {
            System.out.println(" El ganador es : " + " " + jugadorGanador);
            if (jugadorGanador.getAlias().equals(jugadorRojo.getAlias())) {
                int partidasGanadas = jugadorRojo.getPartidasGanadas();
                jugadorRojo.setPartidasGanadas(partidasGanadas + 1);
            } else {
                int partidasGanadas = jugadorAzul.getPartidasGanadas();
                jugadorAzul.setPartidasGanadas(partidasGanadas + 1);
            }
        } else {
            System.out.println("La partida acabo en empate! ");
        }

        System.out.println(" GRACIAS POR JUGAR!");
        sistema.getListaPartidas().add(partida);
    }

    public static boolean verificarPaso(int mat[][], Jugador rojo, Jugador azul) {
        boolean nopaso = true;
        for (int i = 1; i < 9; i++) {
            int[] posicionesrojas = recorrerMatrizValida(mat, azul.getFichasJugador().getPosicionesi(), azul.getFichasJugador().getPosicionesj(), i);
            int[] posicionesazules = recorrerMatrizValida(mat, rojo.getFichasJugador().getPosicionesi(), rojo.getFichasJugador().getPosicionesj(), i);
            if (posicionesrojas[0] <= 3) {
                nopaso = false;
            }
            if (posicionesazules[0] > 3) {
                nopaso = false;
            }
        }
        return nopaso;
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

    public static Posibles posiblesMovimientos(int unNroFila, int unNroColumna, int[][] matriz) {
        Posibles instancia;
        ArrayList<Integer> fichasPosibles = new ArrayList<>();
        boolean esPosible = true;
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

            while (cont < 10) {
                if (unNroFila + cont < filas && unNroColumna + cont < columnas) {
                    sumaDiagonalD = sumaDiagonalD + matriz[unNroFila + cont][unNroColumna + cont];
                }
                if (unNroFila + cont < filas && unNroColumna - cont >= 0) {
                    sumaDiagonalI = sumaDiagonalI + matriz[unNroFila + cont][unNroColumna - cont];
                }
                if (unNroColumna + cont < columnas && unNroFila - cont >= 0) {
                    sumaDiagonalI = sumaDiagonalI + matriz[unNroFila - cont][unNroColumna + cont];
                }
                if (unNroColumna - cont >= 0 && unNroFila - cont >= 0) {
                    sumaDiagonalD = sumaDiagonalD + matriz[unNroFila - cont][unNroColumna - cont];
                }
                cont++;
            }

            for (int n = 0; n < 9; n++) {
                sumaHorizontal = sumaHorizontal + matriz[unNroFila][n];
            }

            for (int p = 0; p < 8; p++) {
                sumaVertical = sumaVertical + matriz[p][unNroColumna];
            }

            sumaDiagonalI = sumaDiagonalI + elem;
            sumaDiagonalD = sumaDiagonalD + elem;
            if (sumaDiagonalD != elem && sumaDiagonalD > 0 && sumaDiagonalD < 9) {
                System.out.println("Puede mover " + sumaDiagonalD);
                fichasPosibles.add(sumaDiagonalD);
            }

            if (sumaDiagonalI != elem && sumaDiagonalI > 0 && sumaDiagonalI < 9) {
                System.out.println("Puede mover " + sumaDiagonalI);
                fichasPosibles.add(sumaDiagonalI);

            }

            if (sumaHorizontal != elem && sumaHorizontal > 0 && sumaHorizontal < 9) {
                System.out.println("Puede mover " + sumaHorizontal);
                fichasPosibles.add(sumaHorizontal);

            }
            if (sumaVertical != elem && sumaVertical > 0 && sumaVertical < 9) {
                System.out.println("Puede mover " + sumaVertical);
                fichasPosibles.add(sumaVertical);
            }
            if ((sumaVertical == elem || sumaVertical == 0 || sumaVertical > 8) && (sumaHorizontal == elem || sumaHorizontal == 0 || sumaHorizontal > 8)
                    && (sumaDiagonalD == elem || sumaDiagonalD == 0 || sumaDiagonalD > 8) && (sumaDiagonalI == elem || sumaDiagonalI == 0 || sumaDiagonalI > 8)) {
                System.out.println("No puede realizar movimientos, siguiente turno");
                esPosible = false;
            }
        }
        instancia = new Posibles(esPosible, fichasPosibles, new int[8][9], 0);

        return instancia;
    }

    public static void historial(Sistema sistema) {
        boolean vacia;
        vacia = sistema.getListaFichas().isEmpty() && sistema.getListaPartidas().isEmpty();
        if (!vacia) {
            boolean fichasVacias = false;
            Ficha rojas = sistema.getListaFichas().get(0);
            Ficha azules = sistema.getListaFichas().get(1);
            int[][] tablero = crearTableroPred(8, 9);
            imprimir(tablero, false);
            ArrayList<Partida> partidas = sistema.getListaPartidas();
            for (int i = 0; i < partidas.size(); i++) {
                System.out.println("Partida numero " + i);
                System.out.println(partidas.get(i));
            }
            Partida elegida = partidas.get(pedirUnIntValidado(0, partidas.size(), "Elegir una de las partidas", "Partida no valida, ingrese de nuevo"));
            System.out.println("Partida elegida" + elegida);
            imprimir(tablero, false);
            if (!elegida.getListaMovimientos().isEmpty()) {
                System.out.println("Movimientos de la partida, presione enter para mostrar cada movimiento");
                for (int i = 0; i < elegida.getListaMovimientos().size(); i++) {
                    System.out.println("Movimiento numero" + i + 1);
                    tablero = movimientoParm(tablero, elegida, rojas, azules);
                }
                System.out.println("Todos los movimientos mostrados");

            } else {

                System.out.println("No hay movimientos para mostrar");
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

    public static int[][] movimientoParm(int[][] unTablero, Partida partida, Ficha rojas, Ficha azules) {
        int[][] tablero = unTablero;
        ArrayList<String> movimientos = partida.getListaMovimientos();
        ArrayList<Integer> colores = partida.getColores();
        for (int i = 0; i < movimientos.size(); i++) {
            enterContinuar();
            String mov = movimientos.get(i);
            int color = colores.get(i);
            System.out.println(mov);
            if (color == 0) {
                int[] fichasiinvalidas = azules.getPosicionesi();
                int[] fichasjinvalidas = azules.getPosicionesj();
                String result = mov.replaceAll("[^0-9]+", "");
                int resultado = Integer.parseInt(result);
                int posicioni = 0;
                int posicionj = 0;
                int posicioniNueva = 0;
                int posicionjNueva = 0;
                if (mov.contains("D")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    posicioniNueva = posicioni - 1;
                    posicionjNueva = posicionj + 1;
                    tablero[posicioniNueva][posicionjNueva] = tablero[posicioni][posicionj];
                    tablero[posicioni][posicionj] = 0;
                    imprimir(tablero, false);
                    rojas.getPosicionesi()[resultado] = posicioniNueva;
                    rojas.getPosicionesj()[resultado] = posicionjNueva;
                }

                if (mov.contains("A")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    tablero[posicioni - 1][posicionj] = tablero[posicioni][posicionj];
                    tablero[posicioni][posicionj] = 0;
                    imprimir(tablero, false);
                    rojas.getPosicionesi()[resultado] = posicioniNueva;
                    rojas.getPosicionesj()[resultado] = posicionjNueva;

                }

                if (mov.contains("I")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    posicioniNueva = posicioni - 1;
                    posicionjNueva = posicionj - 1;
                    tablero[posicioni - 1][posicionj - 1] = tablero[posicioni][posicionj];
                    tablero[posicioni][posicionj] = 0;
                    imprimir(tablero, false);
                    rojas.getPosicionesi()[resultado] = posicioniNueva;
                    rojas.getPosicionesj()[resultado] = posicionjNueva;

                }
            } else {
                System.out.println("Entra al segundo if, de las azules");
                int[] fichasiinvalidas = rojas.getPosicionesi();
                int[] fichasjinvalidas = rojas.getPosicionesj();
                String result = mov.replaceAll("[^0-9]+", "");
                int resultado = Integer.parseInt(result);
                int posicioni = 0;
                int posicionj = 0;
                int posicioniNueva = 0;
                int posicionjNueva = 0;
                String in;
                if (mov.contains("D")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    posicioniNueva = posicioni + 1;
                    posicionjNueva = posicionj + 1;
                    tablero[posicioniNueva][posicionjNueva] = tablero[posicioni][posicionj];
                    tablero[posicioni][posicionj] = 0;
                    imprimir(tablero, false);
                    azules.getPosicionesi()[resultado] = posicioniNueva;
                    azules.getPosicionesj()[resultado] = posicionjNueva;
                }

                if (mov.contains("A")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    tablero[posicioni + 1][posicionj] = tablero[posicioni][posicionj];
                    tablero[posicioni][posicionj] = 0;
                    imprimir(tablero, false);
                    azules.getPosicionesi()[resultado] = posicioni + 1;
                    azules.getPosicionesj()[resultado] = posicionj;
                }

                if (mov.contains("I")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    for (int j = 0; j < 9; j++) {
                        System.out.println(fichasiinvalidas[j]);
                        System.out.println(fichasjinvalidas[j]);
                    }
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    tablero[posicioni + 1][posicionj - 1] = tablero[posicioni][posicionj];
                    tablero[posicioni][posicionj] = 0;
                    imprimir(tablero, false);
                    azules.getPosicionesi()[resultado] = posicioni + 1;
                    azules.getPosicionesj()[resultado] = posicionj - 1;
                }

            }
        }
        return tablero;
    }

    public static void imprimirTableroVERN(int[][] unTablero) {
        for (int i = 0; i < unTablero.length; i++) {
            System.out.println("+-+-+-+-+-+-+-+-+-+");
            for (int j = 0; j < unTablero[0].length; j++) {

                if (unTablero[i][j] != 0) {

                    System.out.print("|" + unTablero[i][j]);
                } else {
                    System.out.print("| ");
                }
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.println("+-+-+-+-+-+-+-+-+-+");
    }

    public static void imprimirTableroVERR(int[][] unTablero) {
        for (int i = 0; i < unTablero.length; i++) {
            for (int j = 0; j < unTablero[0].length; j++) {
                if (unTablero[i][j] != 0) {
                    System.out.print(unTablero[i][j]);
                } else {
                    System.out.print("-");

                }
                System.out.print(" ");
            }

            System.out.println();
        }
    }

    public static void imprimir(int[][] matriz, boolean unVer) {
        System.out.println("-----JUEGO SUMA-------");
        if (unVer) {
            imprimirTableroVERR(matriz);
        } else {
            imprimirTableroVERN(matriz);

        }

    }
    
    
    public static void Frame (){
       Frame f = new Frame("Tutorialspoint");
      Component text = new TextArea("Sairamkrishna Mamamahe");
      Component button = new Button("Button");
      f.add(text, BorderLayout.NORTH);
      f.add(button, BorderLayout.SOUTH);
      int width = 300;
      int height = 300;
      f.setSize(width, height);
      f.setVisible(true);
    }

}
