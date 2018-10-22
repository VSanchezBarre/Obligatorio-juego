
package PackageJuego;
import java.util.ArrayList;

public class Posibles {
    boolean esPosible;
    ArrayList <Integer> posibles;

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

    public Posibles(boolean esPosible, ArrayList<Integer> posibles) {
        this.esPosible = esPosible;
        this.posibles = posibles;
    }

 

    
    
}
