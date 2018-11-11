//author Valentin Sanchez Ignacio Pigatto
package PackageJuego;

import java.util.ArrayList;
import java.io.*;

public class Sistema implements Serializable {

    private ArrayList<Jugador> listaJugadores;
    private ArrayList<Partida> listaPartidas;
    private ArrayList<Ficha> listaFichas;

    public Sistema() {

        this.listaJugadores = new ArrayList<>();
        this.listaPartidas = new ArrayList<>();
        this.listaFichas = new ArrayList<>();

    }

    public void setListaFichas(ArrayList<Ficha> listaFichas) {
        this.listaFichas = listaFichas;
    }

    public ArrayList<Ficha> getListaFichas() {
        return listaFichas;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public ArrayList<Partida> getListaPartidas() {
        return listaPartidas;
    }

    public void setListaJugadores(ArrayList<Jugador> unalistaJugadores) {
        this.listaJugadores = unalistaJugadores;
    }

    public void setListaPartidas(ArrayList<Partida> unalistaPartidas) {
        this.listaPartidas = listaPartidas;
    }

    public void agregarJugador(String nombre, String alias, int edad, int partidasGanadas, Ficha fichas) {
        Jugador jugador = new Jugador(nombre, alias, edad, partidasGanadas, fichas);
        if (!alias.equals("Empate")) {
            listaJugadores.add(jugador);
        }
    }

    public boolean existeAlias(String alias) {
        Jugador auxPlayer = new Jugador();
        auxPlayer.setAlias(alias);
        return this.getListaJugadores().contains(auxPlayer);
    }

}
