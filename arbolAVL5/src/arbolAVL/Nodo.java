package arbolAVL;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Nodo {

    private final int dato;
    private Nodo hijoDer;
    private Nodo hijoIzq;
    private Nodo padre;
    private int x;
    private int y;

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }


    public Nodo(int dato, boolean rojo) {
        this.dato = dato;
          this.x = 100;
        this.y = 100;
    }

        public void paint(Graphics lapiz)
    {
        //DIBUJA EL NODO
        lapiz.fillOval(x, y, 30, 30);
        lapiz.setColor(Color.WHITE);
        lapiz.drawOval(x, y, 30, 30);
        lapiz.setColor(Color.BLACK);
        lapiz.setColor(Color.WHITE);
        lapiz.setFont(new Font("Arial", Font.BOLD, 16)); 
        lapiz.drawString(dato+"", x+6, y+20);
        lapiz.setColor(Color.BLACK);
        //DIBUJA LOS HIJOS
        if(hijoIzq != null){
            lapiz.drawLine(x + 3, y +23, hijoIzq.getX() +23, hijoIzq.getY() +3);
            hijoIzq.paint(lapiz);
        }
        if(hijoDer != null){
            lapiz.drawLine(x + 27, y +23, hijoDer.getX() +3, hijoDer.getY() +3);
            hijoDer.paint(lapiz);
        }
    }

    public int getDato() {
        return dato;
    }

    public Nodo getHijoIzq() {
        return hijoIzq;
    }

    public void setHijoIzquierdo(Nodo hijoIzquierdo) {
        this.hijoIzq = hijoIzquierdo;
    }

    public Nodo getHijoDer() {
        return hijoDer;
    }

    public void setHijoDerecho(Nodo hijoDerecho) {
        this.hijoDer = hijoDerecho;
    }

    public boolean esHoja() {
        return hijoDer == null && hijoIzq == null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
