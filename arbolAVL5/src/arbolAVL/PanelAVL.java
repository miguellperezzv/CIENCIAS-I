package arbolAVL;

import arbolavl2.logica.ArbolAVL;
import java.awt.Graphics;
import javax.swing.JComponent;

public class PanelAVL extends JComponent {

    private final ArbolAVL arbol;

    public PanelAVL(ArbolAVL arbolAVL) {
        super();
        arbol = arbolAVL;
    }

    public void añadirNodo(int num) {
        arbol.insertar(num);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        arbol.paint(g, this);
    }
}
