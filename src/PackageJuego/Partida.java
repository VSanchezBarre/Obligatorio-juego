package PackageJuego;

import java.util.ArrayList;

import java.util.Date;

public class Partida implements Comparable<Partida> {

    Jugador jugadoruno;
    Jugador jugadordos;
    ArrayList<String> listaMovimientos;
    ArrayList<Integer> listaColores;
    Date hora;

    public Partida() {
        Date date = new Date();
        this.jugadoruno = new Jugador();
        this.jugadordos = new Jugador();
        this.listaMovimientos = new ArrayList<String>();
        this.listaColores = new ArrayList<>();
        this.hora = date;
    }

    public Partida(Jugador jugadoruno, Jugador jugadordos, ArrayList<String> listaMovimientos) {
        this.jugadoruno = jugadoruno;
        this.jugadordos = jugadordos;
        this.listaMovimientos = listaMovimientos;
        this.hora = new Date();
        this.listaColores = new ArrayList <>();
    }

    public Partida(Jugador jugadoruno,Jugador jugadordos){
        this.jugadoruno =jugadoruno;
        this.jugadordos = jugadordos;
        this.hora = new Date();
        this.listaMovimientos= new ArrayList<>();
        this.listaColores=new ArrayList<>();
    }
    public Jugador getJugadoruno() {
        return jugadoruno;
    }

    public Jugador getJugadordos() {
        return jugadordos;
    }

    public ArrayList<String> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setJugadoruno(Jugador jugadoruno) {
        this.jugadoruno = jugadoruno;
    }

    public void setJugadordos(Jugador jugadordos) {
        this.jugadordos = jugadordos;
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
        return "Partida{" + "Jugador Uno = " + jugadoruno + ", Jugador Dos =" + jugadordos + " Hora = "  + hora + '}';
    }
    
  

}
