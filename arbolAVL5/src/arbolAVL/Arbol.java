package arbolAVL;

import java.awt.Graphics;
import javax.swing.JComponent;

public class Arbol {

    private Nodo raiz;
    private int altura = 0;
    public int rotIzq = 0;
    public int rotDer = 0;
    public int rotDobleIzq = 0;
    public int rotDobleDer = 0;
    private int numNodo;

    public void paint(Graphics g, JComponent panel) {
        darUbicacion(panel, raiz);
        if (raiz != null) {
            raiz.paint(g);
        }
    }

    //Cuadra la posicion de los nodos
    //da las coordenadas que utiliza el metodo pintar
    private void darUbicacion(JComponent panel, Nodo nodo) {

        if (nodo != null) {

            //Cuantos niveles tiene el arbol
            int n = darNiveles(raiz, 0, nodo.getDato());
            //Comprueba la altura
            this.actualizarAltura(raiz, 0);
            //Hace una division de posiciones
            int quadrant = panel.getHeight() / altura;

            //Da la posicion y del nodo
            int pos_y = quadrant * n;

            //Da la posicion x del nodo           
            int pos_x;

            if (nodo == raiz) {
                pos_x = getIncrementoX(nodo, panel.getWidth()) - 5;
                nodo.setX(pos_x);
            }
            if (nodo.getHijoIzq() != null) {
                pos_x = nodo.getX() - getIncrementoX(nodo.getHijoIzq(), panel.getWidth());
                nodo.getHijoIzq().setX(pos_x);
            }
            if (nodo.getHijoDer() != null) {
                pos_x = nodo.getX() + getIncrementoX(nodo.getHijoDer(), panel.getWidth());
                nodo.getHijoDer().setX(pos_x);
            }

            //Asigna posicion a los hijos
            if (nodo.getHijoIzq() != null) {
                darUbicacion(panel, nodo.getHijoIzq());
            }
            if (nodo.getHijoDer() != null) {
                darUbicacion(panel, nodo.getHijoDer());
            }

            //Asigna posicion al padre
            nodo.setY(pos_y);
        }
    }

    //Nos da el incremento en x que es para cada uno de los nodos
    private int getIncrementoX(Nodo nodo, int ancho) {
        int n = darNiveles(raiz, 0, nodo.getDato());
        //Nos da el numero de nodos ideal para el nivel actual
        int dos_n = (int) Math.pow(2, n);
        //Calcula la posicion del nodo      
        return (int) ((ancho / dos_n) / 2);
    }

    public void insertaUnNodo(int numero) {
        Nodo nodoNuevo = new Nodo(numero, true);
        Nodo nodoActual = raiz;
        Nodo nodoTemporal;
        numNodo = numero;
        rotDer = 0;
        rotIzq = 0;
        rotDobleDer = 0;
        rotDobleIzq = 0;

        boolean esHijoDer;

        // ve si el arbol esta vacio y crea el nodo como raiz
        if (raiz == null) {
            raiz = new Nodo(numero, false);
        } else if (!contiene(raiz, numero)) {
            while (true) {
                // recorre los nodos que existan para buscar el lugar del nuevo nodo en base a su numero
                if (numero < nodoActual.getDato()) {
                    if (nodoActual.getHijoIzq() != null) {
                        nodoTemporal = nodoActual;
                        nodoActual = nodoActual.getHijoIzq();
                        nodoActual.setPadre(nodoTemporal);
                    } else {
                        nodoActual.setHijoIzquierdo(nodoNuevo);
                        nodoNuevo.setPadre(nodoActual);
                        esHijoDer = false;
                        break;
                    }
                } else {
                    if (nodoActual.getHijoDer() != null) {
                        nodoTemporal = nodoActual;
                        nodoActual = nodoActual.getHijoDer();
                        nodoActual.setPadre(nodoTemporal);
                    } else {
                        nodoActual.setHijoDerecho(nodoNuevo);
                        nodoNuevo.setPadre(nodoActual);
                        esHijoDer = true;
                        break;
                    }
                }
            }

        }
    }

    private int darNiveles(Nodo nodo, int suma, int dat) {
        if (nodo.getDato() == dat) {
            return suma;
        } else if (contiene(nodo.getHijoIzq(), dat)) {
            suma = 1 + darNiveles(nodo.getHijoIzq(), suma, dat);
        } else if (contiene(nodo.getHijoDer(), dat)) {
            suma = 1 + darNiveles(nodo.getHijoDer(), suma, dat);
        }
        return suma;
    }

    private void actualizarAltura(Nodo nodo, int cont) {
        if (nodo != null) {
            cont++;
            if (nodo.esHoja()) {
                if (cont > altura) {
                    altura = cont;
                }
            } else {
                actualizarAltura(nodo.getHijoIzq(), cont);
                actualizarAltura(nodo.getHijoDer(), cont);
            }
        }
    }

    private boolean contiene(Nodo nodo, int dato) {
        if (nodo == null) {
            return false;
        } else {
            return nodo.getDato() == dato || contiene(nodo.getHijoIzq(), dato) || contiene(nodo.getHijoDer(), dato);
        }
    }

    public int darNumeroNodo() {
        System.out.println("numNodo" + numNodo);
        return numNodo;
    }

    public int darRotacionSimpleDerecha() {
        System.out.println("rotDer" + rotDer);
        return rotDer;
    }

    public int darRotacionSimpleIzquierda() {
        System.out.println("rotIzq" + rotIzq);
        return rotIzq;
    }

    public int darRotacionDobleDerecha() {
        System.out.println("rotDobleDer" + rotDobleDer);
        return rotDobleDer;
    }

    public int darRotacionDobleIzquierda() {
        System.out.println("rotDobleIzq" + rotDobleIzq);
        return rotDobleIzq;
    }

}
