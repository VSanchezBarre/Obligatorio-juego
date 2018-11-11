//author Valentin Sanchez Ignacio Pigatto
package PackageJuego;
import java.util.ArrayList;

public class Posibles {
    boolean esPosible;
    ArrayList <Integer> posibles;
    int[][] matriz;
    int contador;
    boolean ver;

    public void setVer(boolean ver) {
        this.ver = ver;
    }

    public boolean getVer() {
        return ver;
    }

    public boolean getEsPosible() {
        return esPosible;
    }

    public void setEsPosible(boolean esPosible) {
        this.esPosible = esPosible;
    }

    public void setPosibles(ArrayList<Integer> posibles) {
        this.posibles = posibles;
    }

    public ArrayList<Integer> getPosibles() {
        return posibles;
    }

    public boolean isEsPosible() {
        return esPosible;
    }

    public void setPosible(int[][] posible) {
        this.matriz = posible;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public int getContador() {
        return contador;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }


    
    public Posibles(boolean esPosible, ArrayList<Integer> posibles,int[][] matriz,int contador) {
        this.esPosible = esPosible;
        this.posibles = posibles;
        this.matriz = matriz;
        this.contador=contador;
    }

 

    
    
}
