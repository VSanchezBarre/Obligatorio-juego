//author Valentin Sanchez Ignacio Pigatto
package PackageJuego;

public class Ficha {
   String color; int [] posicionesi;int[]posicionesj;

    public String getColor() {
        return color;
    }

    public int[] getPosicionesi() {
        return posicionesi;
    }

    public int[] getPosicionesj() {
        return posicionesj;
    }

    public Ficha(String color, int[] posicionesi, int[] posicionesj) {
        this.color = color;
        this.posicionesi = posicionesi;
        this.posicionesj = posicionesj;
       
    }
    public Ficha(){
        this.color="";
        this.posicionesi=new int[8];
        this.posicionesj=new int[8];
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPosicionesi(int[] posicionesi) {
        this.posicionesi = posicionesi;
    }

    public void setPosicionesj(int[] posicionesj) {
        this.posicionesj = posicionesj;
    }


}
