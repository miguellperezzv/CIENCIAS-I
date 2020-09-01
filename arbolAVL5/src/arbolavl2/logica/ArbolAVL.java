/**
 *
 * @author Juan Camilo Rivera A.
 */
package arbolavl2.logica;

import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Clase que contiene la logica necesaria para manejar un arbol AVL y algunos
 * metodos usados para mostrar la informacion por consola.
 *
 */
public class ArbolAVL {

    private NodoAVL root;
    private int altura = 0;
    private int rotacionSimpleDerecha;
    private int rotacionSimpleIzquierda;
    private int rotacionDobleDerecha;
    private int rotacionDobleIzquierda;
    private int numNodo;

    public void insertar(Comparable x) {
        numNodo = (int) x;
        rotacionSimpleDerecha = 0;
        rotacionSimpleIzquierda = 0;
        rotacionDobleDerecha = 0;
        rotacionDobleIzquierda = 0;
        root = insertar(x, root);
    }

    /*
     * x es una instancia de una clase que implementa Comparable
     */
    private NodoAVL insertar(Comparable x, NodoAVL t) {
        if (t == null) {
            t = new NodoAVL(x, null, null);
        } //si x es menor devuelve -1
        else if (x.compareTo(t.dato) < 0) {
            t.izquierdo = insertar(x, t.izquierdo);
            if (altura(t.izquierdo) - altura(t.derecho) == 2) {
                if (x.compareTo(t.izquierdo.dato) < 0) {
                    rotacionSimpleDerecha = rotacionSimpleDerecha + 1;
                    System.out.println("rotacionSimpleDerecha");
                    t = rotacionSimpleDerecha(t);
                    /* Caso 1:
                     p==2 y q == 1
                     */
                } else {
                    rotacionDobleDerecha = rotacionDobleDerecha + 1;
                    System.out.println("rotacionDobleDerecha");
                    t = rotacionDobleDerecha(t);
                    /* Caso 2 :
                     p==2 y q==-1
                     */
                }
            }
        } else if (x.compareTo(t.dato) > 0) {
            t.derecho = insertar(x, t.derecho);
            if (altura(t.derecho) - altura(t.izquierdo) == 2) {
                if (x.compareTo(t.derecho.dato) > 0) {
                    rotacionSimpleIzquierda = rotacionSimpleIzquierda + 1;
                    System.out.println("rotacionSimpleIzquierda");
                    t = rotacionSimpleIzquierda(t);
                    /* Caso 4:
                     p==-2 y q ==-1
                     */
                } else {
                    rotacionDobleIzquierda = rotacionDobleIzquierda + 1;
                    System.out.println("rotacionDobleIzquierda");
                    t = rotacionDobleIzquierda(t);
                    /* Caso 3 :
                     p==-2 y q == 1
                     */
                }
            }
        } else; // Duplicado; no hago nada
        t.altura = max(altura(t.izquierdo), altura(t.derecho)) + 1;
        return t;
    }

    private static int max(int alturaHijoIzquierdo, int alturaHijoDerecho) {
        return alturaHijoIzquierdo > alturaHijoDerecho ? alturaHijoIzquierdo : alturaHijoDerecho;
    }

    private static NodoAVL rotacionSimpleDerecha(NodoAVL k2) {
        NodoAVL k1 = k2.izquierdo;
        k2.izquierdo = k1.derecho;
        k1.derecho = k2;
        k2.altura = max(altura(k2.izquierdo), altura(k2.derecho)) + 1;
        k1.altura = max(altura(k1.izquierdo), k2.altura) + 1;
        return k1;
    }

    private static NodoAVL rotacionSimpleIzquierda(NodoAVL k1) {
        NodoAVL k2 = k1.derecho;
        k1.derecho = k2.izquierdo;
        k2.izquierdo = k1;
        k1.altura = max(altura(k1.izquierdo), altura(k1.derecho)) + 1;
        k2.altura = max(altura(k2.derecho), k1.altura) + 1;
        return k2;
    }

    private static NodoAVL rotacionDobleDerecha(NodoAVL k3) {
        k3.izquierdo = rotacionSimpleIzquierda(k3.izquierdo);
        return rotacionSimpleDerecha(k3);
    }

    private static NodoAVL rotacionDobleIzquierda(NodoAVL k1) {
        k1.derecho = rotacionSimpleDerecha(k1.derecho);
        return rotacionSimpleIzquierda(k1);
    }

    private static int altura(NodoAVL t) {
        return t == null ? -1 : t.altura;
    }

    /*
     * Imprime el arbol con el recorrido InOrden
     */
    public void imprimir() {
        imprimir(root);
    }

    private void imprimir(NodoAVL nodo) {
        if (nodo != null) {
            imprimir(nodo.derecho);
            System.out.println("[" + nodo.dato + "]");
            imprimir(nodo.izquierdo);
        }
    }

    /*
     * Obtiene la altura del arbol AVL
     */
    public int calcularAltura() {
        return calcularAltura(root);
    }

    private int calcularAltura(NodoAVL actual) {
        if (actual == null) {
            return -1;
        } else {
            return 1 + Math.max(calcularAltura(actual.izquierdo), calcularAltura(actual.derecho));
        }
    }

    // Imprime el arbol por niveles. Comienza por la raiz.
    public void imprimirPorNiveles() {
        imprimirPorNiveles(root);
    }

    // Imprime el arbol por niveles. Comienza por la raiz.
    public String retornarNiveles() {
       return retornarNiveles(root);
    }

    // Imprime el arbol por niveles.
    private void imprimirPorNiveles(NodoAVL nodo) {
        // Mediante la altura calcula el total de nodos posibles del Ã¡rbol
        // Y crea una array cola con ese tamaÃ±o
        int max = 0;
        int nivel = calcularAltura();

        for (; nivel >= 0; nivel--) {
            max += Math.pow(2, nivel);
        }
        max++;      // Suma 1 para no utilizar la posicion 0 del array

        NodoAVL cola[] = new NodoAVL[max];

        // Carga en la pos 1 el nodo raiz
        cola[1] = nodo;
        int x = 1;

        // Carga los demas elementos del arbol,
        // Carga null en izq y der si el nodo es null
        // i aumenta de a 2 por q carga en izq y der los hijos
        // x aumenta 1, que son los nodos raiz - padre
        for (int i = 2; i < max; i += 2, x++) {
            if (cola[x] == null) {
                cola[i] = null;
                cola[i + 1] = null;
            } else {
                cola[i] = cola[x].izquierdo;
                cola[i + 1] = cola[x].derecho;
            }
        }
        nivel = 0;
        int cont = 0;                       // contador para cada nivel
        int cantidad = 1;                   // cantidad de nodos por nivel
        int ultimaPosicion = 1;             // ultimaPosicion del nodo en la cola de cada nivel

        // Cuando i es = a 2^nivel hay cambio de nivel
        // 2 ^ 0 = 1 que es el nodo raiz
        for (int i = 1; i < max; i++) {
            if (i == Math.pow(2, nivel)) {
                // Nodo raiz tiene nivel 1, por eso (nivel + 1)
                System.out.print("\n Nivel " + (nivel) + ": ");
                nivel++;
            }
            if (cola[i] != null) {
                System.out.print("[" + cola[i].dato + "]");
                cont++;
            }
            if (ultimaPosicion == i && cantidad == Math.pow(2, --nivel)) {
                if (cantidad == 1) {
                    System.out.print(" Cantidad de nodos: " + cont + " (raiz)");
                } else {
                    System.out.print(" Cantidad de nodos: " + cont);
                }
                cont = 0;
                cantidad *= 2;
                ultimaPosicion += (int) Math.pow(2, ++nivel);
            }
        }
    }

    // Imprime el arbol por niveles.
    private String retornarNiveles(NodoAVL nodo) {
        String cadenaTexto = "";
        // Mediante la altura calcula el total de nodos posibles del Ã¡rbol
        // Y crea una array cola con ese tamaÃ±o
        int max = 0;
        int nivel = calcularAltura();

        for (; nivel >= 0; nivel--) {
            max += Math.pow(2, nivel);
        }
        max++;      // Suma 1 para no utilizar la posicion 0 del array

        NodoAVL cola[] = new NodoAVL[max];

        // Carga en la pos 1 el nodo raiz
        cola[1] = nodo;
        int x = 1;

        // Carga los demas elementos del arbol,
        // Carga null en izq y der si el nodo es null
        // i aumenta de a 2 por q carga en izq y der los hijos
        // x aumenta 1, que son los nodos raiz - padre
        for (int i = 2; i < max; i += 2, x++) {
            if (cola[x] == null) {
                cola[i] = null;
                cola[i + 1] = null;
            } else {
                cola[i] = cola[x].izquierdo;
                cola[i + 1] = cola[x].derecho;
            }
        }
        nivel = 0;
        int cont = 0;                       // contador para cada nivel
        int cantidad = 1;                   // cantidad de nodos por nivel
        int ultimaPosicion = 1;             // ultimaPosicion del nodo en la cola de cada nivel

        // Cuando i es = a 2^nivel hay cambio de nivel
        // 2 ^ 0 = 1 que es el nodo raiz
        for (int i = 1; i < max; i++) {
            if (i == Math.pow(2, nivel)) {
                // Nodo raiz tiene nivel 1, por eso (nivel + 1)

                cadenaTexto = cadenaTexto.concat("\n Nivel " + (nivel) + ": ");
                nivel++;
            }
            if (cola[i] != null) {

                cadenaTexto = cadenaTexto.concat("[" + cola[i].dato + "]");
                cont++;
            }
            if (ultimaPosicion == i && cantidad == Math.pow(2, --nivel)) {
                if (cantidad == 1) {

                    cadenaTexto = cadenaTexto.concat(" Cantidad de nodos: " + cont + " (raiz)");
                } else {
                    cadenaTexto = cadenaTexto.concat(" Cantidad de nodos: " + cont);

                }
                cont = 0;
                cantidad *= 2;
                ultimaPosicion += (int) Math.pow(2, ++nivel);
            }
        }
        return cadenaTexto;
    }

    // Imprime el arbol por niveles. Comienza por la raiz.
    public int darBalanceoRaiz() {
        return darBalanceo(root);
    }

    public int darBalanceo(NodoAVL actual) {
        if (actual == null) {
            return 0;
        } else {
            return calcularAltura(actual.izquierdo) - calcularAltura(actual.derecho);
        }
    }

    public int darNumeroNodo() {
        return numNodo;
    }

    public int darRotacionSimpleDerecha() {
        return rotacionSimpleDerecha;
    }

    public int darRotacionSimpleIzquierda() {
        return rotacionSimpleIzquierda;
    }

    public int darRotacionDobleDerecha() {
        return rotacionDobleDerecha;
    }

    public int darRotacionDobleIzquierda() {
        return rotacionDobleIzquierda;
    }

    public int darcalcularAltura() {
        return calcularAltura(root);
    }

    public void paint(Graphics g, JComponent panel) {
        darUbicacion(panel, root);
        if (root != null) {
            root.paint(g);
        }
    }

    private boolean contiene(NodoAVL nodo, int dato) {
        if (nodo == null) {
            return false;
        } else {
            return nodo.getDato() == dato || contiene(nodo.getHijoIzq(), dato) || contiene(nodo.getHijoDer(), dato);
        }
    }

    private int darNiveles(NodoAVL nodo, int suma, int dat) {
        if (nodo.getDato() == dat) {
            return suma;
        } else if (contiene(nodo.getHijoIzq(), dat)) {
            suma = 1 + darNiveles(nodo.getHijoIzq(), suma, dat);
        } else if (contiene(nodo.getHijoDer(), dat)) {
            suma = 1 + darNiveles(nodo.getHijoDer(), suma, dat);
        }
        return suma;
    }

    private void actualizarAltura(NodoAVL nodo, int cont) {
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

    //Cuadra la posicion de los nodos
    //da las coordenadas que utiliza el metodo pintar
    private void darUbicacion(JComponent panel, NodoAVL nodo) {

        if (nodo != null) {

            //Cuantos niveles tiene el arbol
            int n = darNiveles(root, 0, nodo.getDato());
            //Comprueba la altura
            this.actualizarAltura(root, 0);
            //Hace una division de posiciones
            int quadrant = panel.getHeight() / altura;

            //Da la posicion y del nodo
            int pos_y = quadrant * n;

            //Da la posicion x del nodo           
            int pos_x;

            if (nodo == root) {
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
    private int getIncrementoX(NodoAVL nodo, int ancho) {
        int n = darNiveles(root, 0, nodo.getDato());
        //Nos da el numero de nodos ideal para el nivel actual
        int dos_n = (int) Math.pow(2, n);
        //Calcula la posicion del nodo      
        return (int) ((ancho / dos_n) / 2);
    }
}
