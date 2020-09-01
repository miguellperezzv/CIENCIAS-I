package arbolavl2.interfaz;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Es el panel central donde se encuentran los paneles de agregar y la tabla.
 */
public class PanelCentral extends JPanel {
    // --------------------------------------------------------
    // Atributos
    // --------------------------------------------------------

    private InterfazArbolAVL principal;

    // --------------------------------------------------------
    // Atributos de la interfaz
    // --------------------------------------------------------
    /**
     * Panel tabla avl
     */
    private PanelTabla panelTablaAVL;
  
    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------
    /**
     * Construye un nuevo panel central
     *
     * @param principalP es la interfaz padre de este panel
     */
    public PanelCentral(InterfazArbolAVL principalP) {
        principal = principalP;

        // Construye la forma
        setLayout(new BorderLayout());
        setSize(1400, 300);

        panelTablaAVL = new PanelTabla(this);
        panelTablaAVL.setBorder(BorderFactory.createTitledBorder("Tabla AVL"));
        add(panelTablaAVL, BorderLayout.NORTH);

    }

    // --------------------------------------------------------
    // Metodos
    // --------------------------------------------------------

    void actualizarTabla() {
        principal.actualizarTabla();
    }

    void actualizarTablaAVL(String[] tabla) {
        panelTablaAVL.actualizarTabla(tabla);
    }



}
