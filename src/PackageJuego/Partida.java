package PackageJuego;

import java.util.ArrayList;

import java.util.Date;

public class Partida implements Comparable<Partida> {

    Jugador jugadorUno;
    Jugador jugadorDos;
    ArrayList<String> listaMovimientos;
    ArrayList<Integer> listaColores;
    Date hora;

    public Partida() {
        Date date = new Date();
        this.jugadorUno = new Jugador();
        this.jugadorDos = new Jugador();
        this.listaMovimientos = new ArrayList<String>();
        this.listaColores = new ArrayList<>();
        this.hora = date;
    }

    public Partida(Jugador jugadoruno, Jugador jugadordos, ArrayList<String> listaMovimientos) {
        this.jugadorUno = jugadoruno;
        this.jugadorDos = jugadordos;
        this.listaMovimientos = listaMovimientos;
        this.hora = new Date();
        this.listaColores = new ArrayList <>();
    }

    public Partida(Jugador jugadoruno,Jugador jugadordos){
        this.jugadorUno =jugadoruno;
        this.jugadorDos = jugadordos;
        this.hora = new Date();
        this.listaMovimientos= new ArrayList<>();
        this.listaColores=new ArrayList<>();
    }
    public Jugador getJugadorUno() {
        return jugadorUno;
    }

    public Jugador getJugadorDos() {
        return jugadorDos;
    }

    public ArrayList<String> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setJugadorUno(Jugador jugadorUno) {
        this.jugadorUno = jugadorUno;
    }

    public void setJugadorDos(Jugador jugadorDos) {
        this.jugadorDos = jugadorDos;
    }

    public void setListaMovimientos(ArrayList<String> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    public Date getHora() {
        return hora;
    }

    public ArrayList<Integer> getColores() {
        return listaColores;
    }

    public void setColores(ArrayList<Integer> colores) {
        this.listaColores = colores;
    }


    public void setHora(Date hora) {
        this.hora = hora;
    }

    @Override
    public int compareTo(Partida unaPartida) {
        return this.getHora().compareTo(unaPartida.getHora());
    }

    @Override
    public String toString() {
        return "Partida{" + "Jugador Uno = " + jugadorUno + ", Jugador Dos =" + jugadorDos + " Hora = "  + hora + '}';
    }
    
  

}
