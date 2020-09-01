package arbolAVL;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class NodoArbol {

    private Object dato;
    private NodoArbol hijoDerecho;
    private NodoArbol hijoIzquierdo;
    private NodoArbol padre;
    private int longitud, altIzq, altDer;
    private Punto ubicacion;
    private boolean rojo;

    public NodoArbol getPadre() {
        return padre;
    }

    public void setPadre(NodoArbol padre) {
        this.padre = padre;
    }

  
    public int getAltDer() {
        return altDer;
    }

    public void setAltDer(int altDer) {
        this.altDer = altDer;
    }

    public int getAltIzq() {
        return altIzq;
    }

    public void setAltIzq(int altIzq) {
        this.altIzq = altIzq;
    }

    public NodoArbol() {
    }

    public NodoArbol(Object dato, boolean rojo) {
        this.longitud = 10;
        this.ubicacion = new Punto();
        this.dato = dato;
        this.rojo = rojo;
    }

    public NodoArbol(Object dato, NodoArbol hijoDerecho, NodoArbol hijoIzquierdo, boolean rojo) {
        this(dato, rojo);
        this.hijoDerecho = hijoDerecho;
        this.hijoIzquierdo = hijoIzquierdo;
        this.ubicacion = new Punto();
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (this.rojo) {
            g2.setPaint(Color.RED);
        } else {
            g2.setPaint(Color.BLACK);
        }
        g.fillOval(ubicacion.getX(), ubicacion.getY(), longitud + 10, longitud + 10);
        g2.setPaint(Color.WHITE);
        g.drawOval(ubicacion.getX(), ubicacion.getY(), longitud + 10, longitud + 10);
        g2.setPaint(Color.BLACK);

        g2.setPaint(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString(this.getDato() + "", ubicacion.getX() + 6, ubicacion.getY() + 20);
        g2.setPaint(Color.BLACK);
    }

    public boolean estaEnMi(int n) {
        boolean res_izq = false;
        boolean res_der = false;
        int dato = Integer.parseInt(this.getDato().toString());
        if (n == dato) {
            return true;
        }
        if (tieneHijoIzquierdo()) {
            res_izq = this.getHijoIzquierdo().estaEnMi(n);
        }
        if (tieneHijoDerecho()) {
            res_der = this.getHijoDerecho().estaEnMi(n);
        }
        return res_izq || res_der || false;
    }

    public boolean estaEnMi(String n) {
        boolean res_izq = false;
        boolean res_der = false;
        int dato = Integer.parseInt(this.getDato().toString());
        if (n.equals(dato)) {
            return true;
        }
        if (tieneHijoIzquierdo()) {
            res_izq = this.getHijoIzquierdo().estaEnMi(n);
        }
        if (tieneHijoDerecho()) {
            res_der = this.getHijoDerecho().estaEnMi(n);
        }
        return res_izq || res_der || false;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public NodoArbol getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoArbol hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoArbol getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoArbol hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public boolean tieneHijoIzquierdo() {
        return hijoIzquierdo != null;
    }

    public boolean tieneHijoDerecho() {
        return hijoDerecho != null;
    }

    public boolean esHoja() {
        return !tieneHijoIzquierdo() && !tieneHijoDerecho();
    }

    public Punto getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Punto ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

//    public int alturaIzq(NodoArbol hoja)
//    {            
//        while(hoja.tieneHijoIzquierdo())
//        {
//            this.setAltIzq(this.getAltIzq()+1);
//            hoja=hoja.getHijoIzquierdo();
//        }
//        return altIzq;
//    }
//    
//    public int alturaDer(NodoArbol hoja)
//    {            
//        while(hoja.tieneHijoDerecho())
//        {
//            this.setAltDer(this.getAltDer()+1);
//            hoja=hoja.getHijoDerecho();
//        }
//        return altDer;
//    }
    public int diferencia() {
//        System.out.println("izq"+getAltIzq());
//        System.out.println("der"+ getAltDer());
        return this.getAltIzq() - this.getAltDer();
    }
}
