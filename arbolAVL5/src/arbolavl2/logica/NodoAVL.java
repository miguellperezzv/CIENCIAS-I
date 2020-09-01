/**
 *
 * @author Juan Camilo Rivera A.
 */
package arbolavl2.logica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Nodo basico para arboles AVL
 */
public class NodoAVL {

    public Comparable dato;      	 // el dato del nodo
    public NodoAVL izquierdo;            // hijo izquierdo
    public NodoAVL derecho;              // hijo derecho
    public int altura;                   // altura
    private int x;
    private int y;

    // Constructores
    public NodoAVL(Comparable dato) {
        this(dato, null, null);
    }

    public void paint(Graphics lapiz) {
        //INDICA EL COLOR DEL NODO
        lapiz.setColor(Color.BLACK);

        //DIBUJA EL NODO
        lapiz.fillOval(x, y, 30, 30);
        lapiz.setColor(Color.WHITE);
        lapiz.drawOval(x, y, 30, 30);
        lapiz.setColor(Color.BLACK);
        lapiz.setColor(Color.GREEN);
        lapiz.setFont(new Font("Arial", Font.BOLD, 16));
        lapiz.drawString(dato + "", x + 6, y + 20);
        lapiz.setColor(Color.BLACK);
        //DIBUJA LOS HIJOS
        if (izquierdo != null) {
            lapiz.drawLine(x + 3, y + 23, izquierdo.getX() + 23, izquierdo.getY() + 3);
            izquierdo.paint(lapiz);
        }
        if (derecho != null) {
            lapiz.drawLine(x + 27, y + 23, derecho.getX() + 3, derecho.getY() + 3);
            derecho.paint(lapiz);
        }
    }

    public NodoAVL(Comparable dato, NodoAVL izq, NodoAVL der) {
        this.dato = dato;
        this.izquierdo = izq;
        this.derecho = der;
        altura = 0;               // altura predeterminada
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

    public int getDato() {
        return (int) dato;
    }

    public boolean esHoja() {
        return derecho == null && izquierdo == null;
    }

    public NodoAVL getHijoIzq() {
        return izquierdo;
    }

    public void setHijoIzquierdo(NodoAVL hijoIzquierdo) {
        this.izquierdo = hijoIzquierdo;
    }

    public NodoAVL getHijoDer() {
        return derecho;
    }

    public void setHijoDerecho(NodoAVL hijoDerecho) {
        this.derecho = hijoDerecho;
    }
}
