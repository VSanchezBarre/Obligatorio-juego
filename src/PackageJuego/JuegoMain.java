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

    // Metodo para agregar un jugador al sistema, corrobora que no hayan dos con el mismo alias
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

    public static int[][] movimiento(int[][] unTablero, int[] fichasiinvalidas, int[] fichasjinvalidas, int color, int[] fichasvalidas, int[] fichasjvalidas, Ficha ficha, Partida partida) {
        int[][] tablero = unTablero;
        boolean ver = false;
        boolean pasar = false;
        ArrayList<Integer> fichasPosibles = new ArrayList<Integer>();
        if (!pasar) {
            if (color == 0) {

                boolean esPosible = true;
                while (esPosible) {
                    System.out.println("Jugador rojo, eliga un movimiento");
                    String in;
                    Scanner intro = new Scanner(System.in);
                    in = intro.nextLine();
                    String result = in.replaceAll("[^0-9]+", "");

                    if (in.length() == 4) {
                        System.out.println("Entro");
                        if (in.equals("VERR")) {
                            System.out.println("Verr");
                            ver = false;
                            result = "";
                        }
                        if (in.equals("VERN")) {
                            System.out.println("VERN");
                            ver = true;
                            result = "";
                        }

                    }
                    if (!pasar) {
                        if (in.length() == 1) {
                            if (in.equals("p")) {
                                System.out.println("Necesita hacer un movimiento para poder pasar");
                            } else {
                                System.out.println("Valor no valido, ingrese denuevo");
                            }
                        }
                    }

                    if (pasar) {
                        if (in.length() == 1) {
                            if (in.equals("p")) {
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
                        } else {
                            System.out.println("La posicion a donde desea moverse ya esta ocupada");
                        }

                        esPosible = posiblesMovimientos(posicioniNueva, posicionjNueva, tablero).getEsPosible();
                        fichasPosibles = posiblesMovimientos(posicioniNueva, posicionjNueva, tablero).getPosibles();
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
                            fichasPosibles = posiblesMovimientos(posicioni - 1, posicionj, tablero).getPosibles();
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
                            fichasPosibles = posiblesMovimientos(posicioni - 1, posicionj - 1, tablero).getPosibles();
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
                            ver = false;
                            result = "";
                        }
                        if (in.equals("VERN")) {
                            ver = true;
                            result = "";
                        }

                    }
                    if (!pasar) {
                        if (in.length() == 1) {
                            if (in.equals("p")) {
                                System.out.println("Necesita hacer un movimiento para poder pasar");
                            } else {
                                System.out.println("Valor no valido, ingrese denuevo");
                            }
                        }
                    }

                    if (pasar) {
                        if (in.length() == 1) {
                            if (in.equals("p")) {
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
                            fichasPosibles = posiblesMovimientos(posicioniNueva, posicionjNueva, tablero).getPosibles();
                            pasar = true;
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
                            fichasPosibles = posiblesMovimientos(posicioni + 1, posicionj, tablero).getPosibles();
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
                            fichasPosibles = posiblesMovimientos(posicioni + 1, posicionj - 1, tablero).getPosibles();
                            pasar = true;
                        } else {
                            System.out.println("No se puede hacer el movimiento");
                        }

                    }
                }

            }
        } else {
            if (color == 0) {
                boolean esPosible = true;
                while (esPosible) {
                    String in;
                    Scanner intro = new Scanner(System.in);
                    in = intro.nextLine();
                    String result = in.replaceAll("[^0-9]+", "");
                    if (in.length() == 4) {
                        if (in.equals("VERR")) {
                            ver = false;
                            result="";
                        }
                        if (in.equals("VERN")) {
                            ver = true;
                            result="";
                        }

                    }
                    if (!pasar) {
                        if (in.length() == 1) {
                            if (in.equals("p")) {
                                System.out.println("Necesita hacer un movimiento para poder pasar");
                            } else {
                                System.out.println("Valor no valido, ingrese denuevo");
                            }
                        }
                    }

                    if (pasar) {
                        if (in.length() == 1) {
                            if (in.equals("p")) {
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
                    int resultado=0;
                    if(!result.equals("")){
                    resultado = Integer.parseInt(result);
                    }
              
                    int posicioni = 0;
                    int posicionj = 0;
                    int posicioniNueva = 0;
                    int posicionjNueva = 0;
                    if (fichasPosibles.contains(resultado)) {
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
                            } else {
                                System.out.println("La posicion a donde desea moverse ya esta ocupada");
                            }
                            esPosible = posiblesMovimientos(posicioniNueva, posicionjNueva, tablero).getEsPosible();

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
                                esPosible = posiblesMovimientos(posicioni, posicionj, tablero).getEsPosible();
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
                            } else {
                                System.out.println("No se puede hacer el movimiento");
                            }

                        }
                    } else {
                        System.out.println("Este movimiento no es posible");
                    }
                }
            } else {
                boolean turno = true;
                while (turno) {
                    String in;
                    Scanner intro = new Scanner(System.in);
                    in = intro.nextLine();
                    String result = in.replaceAll("[^0-9]+", "");
                    if (in.length() == 4) {
                        if (in.equals("VERR")) {
                            ver = false;
                            result="";
                        }
                        if (in.equals("VERN")) {
                            ver = true;
                            result="";
                        }

                    }
                    if (!pasar) {
                        if (in.length() == 1) {
                            if (in.equals("p")) {
                                System.out.println("Necesita hacer un movimiento para poder pasar");
                            } else {
                                System.out.println("Valor no valido, ingrese denuevo");
                            }
                        }
                    }

                    if (pasar) {
                        if (in.length() == 1) {
                            if (in.equals("p")) {
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
                    int resultado=0;
                    if(!result.equals("")){
                    resultado = Integer.parseInt(result);
                    }
                   
                    int posicioni = 0;
                    int posicionj = 0;
                    int posicioniNueva = 0;
                    int posicionjNueva = 0;
                    if (fichasPosibles.contains(resultado)) {
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
                                pasar = true;
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
                                pasar = true;
                            } else {
                                System.out.println("Este movimiento no es posible, porque ya esta ocupado");
                            }
                        }

                        if (in.contains("I")) {
                            int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                            posicioni = posiciones[0];
                            posicionj = posiciones[1];
                            System.out.println(posicioni);
                            System.out.println(posicionj);
                            if (posicionj != 0 && tablero[posicioni + 1][posicionj - 1] == 0) {
                                tablero[posicioni + 1][posicionj - 1] = tablero[posicioni][posicionj];

                                tablero[posicioni][posicionj] = 0;
                                setearPosiciones(ficha, resultado, posicioni + 1, posicionj - 1);

                                partida.getListaMovimientos().add(in);

                                partida.getColores().add(color);
                                imprimir(tablero, ver);
                                turno = posiblesMovimientos(posicioni + 1, posicionj - 1, tablero).getEsPosible();
                            } else {
                                System.out.println("No se puede hacer el movimiento");
                            }

                        }
                    } else {
                        System.out.println("Este movimiento no es posible");
                    }
                }

            }
        }
        return tablero;
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
                if (posicionesazules[0] >= 3) {
                    puntosJugadorDos++;
                }
            }
            if (puntosJugadorUno != puntosJugadorUno) {
                if (puntosJugadorUno > puntosJugadorDos) {
                    elganador = jugadoruno;
                } else {
                    elganador = jugadordos;
                }
            }
        }
        if (condicion == 2) {
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
        Ficha azules = new Ficha("azul", generarPosicionesArribaFila(), generarPosicionesArribaColumnas());
        int ganador = 0;
        Jugador jugadorGanador = jugadorRojo;
        boolean ver = false;
        if (opcion == 1) {
            System.out.println("Con cuantos movimientos desea jugar?");
            Scanner in = new Scanner(System.in);
            int maximo = in.nextInt();
            int contador = 0;
            while (contador <= maximo) {
                imprimir(tablero, ver);
                tablero = movimiento(tablero, azules.getPosicionesi(), azules.getPosicionesj(), 0, rojas.getPosicionesi(), rojas.getPosicionesj(), rojas, partida);
                contador++;
                if (contador <= maximo) {
                    imprimir(tablero, ver);
                    tablero = movimiento(tablero, rojas.getPosicionesi(), rojas.getPosicionesj(), 1, azules.getPosicionesi(), azules.getPosicionesj(), azules, partida);
                    contador++;
                }
            }
            jugadorGanador = ganador(jugadorRojo, jugadorAzul, 1, tablero);
        }
        if (opcion == 2) {
            boolean nopaso = false;
            while (!nopaso) {
                imprimir(tablero, ver);
                tablero = movimiento(tablero, rojas.getPosicionesi(), rojas.getPosicionesj(), 1, azules.getPosicionesi(), azules.getPosicionesj(), azules, partida);
                nopaso = verificarPaso(tablero, jugadorRojo, jugadorAzul);
                if (nopaso) {
                    jugadorGanador = jugadorRojo;
                }
                imprimir(tablero, ver);
                tablero = movimiento(tablero, azules.getPosicionesi(), azules.getPosicionesj(), 0, rojas.getPosicionesi(), rojas.getPosicionesj(), rojas, partida);
                nopaso = verificarPaso(tablero, jugadorRojo, jugadorAzul);
                if (nopaso) {
                    jugadorGanador = jugadorAzul;
                }
            }
        }
        if (opcion == 3) {
            boolean paso = false;
            while (!paso) {
                int rojaspasadas = 0;
                int azulespasadas = 0;
                tablero = movimiento(tablero, rojas.getPosicionesi(), rojas.getPosicionesj(), 1, azules.getPosicionesi(), azules.getPosicionesj(), azules, partida);
                imprimir(tablero, ver);
                jugadorGanador = ganador(jugadorRojo, jugadorAzul, 3, tablero);
                tablero = tablero = movimiento(tablero, azules.getPosicionesi(), azules.getPosicionesj(), 0, rojas.getPosicionesi(), rojas.getPosicionesj(), rojas, partida);
                jugadorGanador = ganador(jugadorRojo, jugadorAzul, 3, tablero);
                imprimir(tablero, ver);

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

        System.out.println("La partida termino.");
        System.out.println("El ganador es:" + jugadorGanador);
        int partidasGanadas = jugadorGanador.getPartidasGanadas();
        jugadorGanador.setPartidasGanadas(partidasGanadas++);
        System.out.println("Gracias por jugar");
    }

    public static boolean verificarPaso(int mat[][], Jugador rojo, Jugador azul) {
        boolean paso = false;
        for (int i = 1; i < 9; i++) {
            int[] posicionesrojas = recorrerMatrizValida(mat, azul.getFichasJugador().getPosicionesi(), azul.getFichasJugador().getPosicionesj(), i);
            int[] posicionesazules = recorrerMatrizValida(mat, rojo.getFichasJugador().getPosicionesi(), rojo.getFichasJugador().getPosicionesj(), i);
            if (posicionesrojas[0] <= 3) {
                paso = true;
            }
            if (posicionesazules[0] > 3) {
                paso = true;
            }
        }
        return paso;
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
        instancia = new Posibles(esPosible, fichasPosibles);

        return instancia;
    }

    public static void historial(Sistema sistema) {
        Ficha rojas = sistema.getListaFichas().get(0);
        Ficha azules = sistema.getListaFichas().get(1);
        int[][] tablero = crearTableroPred(8, 9);
        ArrayList<Partida> partidas = sistema.getListaPartidas();
        boolean vacia = partidas.isEmpty();
        if (!vacia) {
            for (int i = 0; i < partidas.size(); i++) {
                System.out.println(partidas.get(i));
            }
            Partida elegida = partidas.get(pedirUnIntValidado(0, partidas.size(), "Elegir una de las partidas", "Partida no valida, ingrese de nuevo"));
            System.out.println("Partida elegida" + elegida);
            imprimir(tablero, false);
            System.out.println("Movimientos de la partida, presione enter para mostrar cada movimiento");
            for (int i = 0; i < elegida.getListaMovimientos().size(); i++) {
                System.out.println("Movimiento numero" + i + 1);
                tablero = movimientoParm(tablero, elegida, rojas, azules);
                enterContinuar();
            }
            System.out.println("Todos los movimientos mostrados");

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
        for (int i = 0; i <= movimientos.size(); i++) {
            String mov = movimientos.get(i);
            int color = colores.get(i);
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

                    if (posicionj != 8 && tablero[posicioniNueva][posicionjNueva] == 0) {
                        tablero[posicioniNueva][posicionjNueva] = tablero[posicioni][posicionj];
                        tablero[posicioni][posicionj] = 0;
                        imprimir(tablero, false);
                    } else {
                        System.out.println("La posicion a donde desea moverse ya esta ocupada");
                    }
                }

                if (mov.contains("A")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    if (posicioni != 0 && tablero[posicioni - 1][posicionj] == 0) {
                        tablero[posicioni - 1][posicionj] = tablero[posicioni][posicionj];
                        tablero[posicioni][posicionj] = 0;
                        imprimir(tablero, false);
                        posiblesMovimientos(posicioni, posicionj, tablero);
                    } else {
                        System.out.println("Este movimiento no es posible, porque ya esta ocupado");
                    }
                }

                if (mov.contains("I")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    if (posicionj != 0 && tablero[posicioni - 1][posicionj - 1] == 0) {
                        tablero[posicioni - 1][posicionj - 1] = tablero[posicioni][posicionj];
                        tablero[posicioni][posicionj] = 0;
                        imprimir(tablero, false);
                    } else {
                        System.out.println("No se puede hacer el movimiento");
                    }

                }
            } else {
                int[] fichasiinvalidas = rojas.getPosicionesi();
                int[] fichasjinvalidas = rojas.getPosicionesj();
                String result = mov.replaceAll("[^0-9]+", "");
                int resultado = Integer.parseInt(result);
                int posicioni = 0;
                int posicionj = 0;
                int posicioniNueva = 0;
                int posicionjNueva = 0;
                String in;
                Scanner intro = new Scanner(System.in);
                if (mov.contains("D")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    posicioniNueva = posicioni + 1;
                    posicionjNueva = posicionj + 1;

                    if (posicionj != 8 && tablero[posicioniNueva][posicionjNueva] == 0) {
                        tablero[posicioniNueva][posicionjNueva] = tablero[posicioni][posicionj];
                        tablero[posicioni][posicionj] = 0;
                        imprimir(tablero, false);
                        posiblesMovimientos(posicioniNueva, posicionjNueva, tablero);
                    } else {
                        System.out.println("El movimiento no es posible");
                    }
                }

                if (mov.contains("A")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    if (posicioni != 7 && tablero[posicioni + 1][posicionj] == 0) {
                        tablero[posicioni + 1][posicionj] = tablero[posicioni][posicionj];
                        tablero[posicioni][posicionj] = 0;
                        imprimir(tablero, false);
                        posiblesMovimientos(posicioni, posicionj, tablero);
                    } else {
                        System.out.println("Este movimiento no es posible, porque ya esta ocupado");
                    }
                }

                if (mov.contains("I")) {
                    int[] posiciones = recorrerMatrizValida(tablero, fichasiinvalidas, fichasjinvalidas, resultado);
                    posicioni = posiciones[0];
                    posicionj = posiciones[1];
                    if (posicionj != 0 && tablero[posicioni + 1][posicionj - 1] == 0) {
                        tablero[posicioni + 1][posicionj - 1] = tablero[posicioni][posicionj];
                        tablero[posicioni][posicionj] = 0;
                        imprimir(tablero, false);
                    } else {
                        System.out.println("No se puede hacer el movimiento");
                    }

                }
            }
        }
        return tablero;
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

    public static void imprimir(int[][] matriz, boolean unVer) {
        if (unVer) {
            imprimirTableroVERN(matriz);
        } else {
            imprimirTableroVERR(matriz);

        }

    }

}
