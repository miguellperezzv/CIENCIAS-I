/**
 *
 * @author Juan Camilo Rivera A.
 */
package arbolavl2.interfaz;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Es el panel que muestra la tabla con los datos de las rotaciones realizadas
 * al insertar un nodo , el dato del nodo insertado y el balanceo de la raiz.
 */
public class PanelTabla extends JPanel {

    // --------------------------------------------------------
    // Atributos
    // --------------------------------------------------------
    /**
     * Panel principal de este panel
     */
    private PanelCentral principal;

    // --------------------------------------------------------
    // Atributos de la interfaz
    // --------------------------------------------------------
    /**
     * Modelo de la tabla
     */
    private DefaultTableModel modeloTabla;

    /**
     * Tabla con los datos de las rotaciones
     */
    private JTable tabla;

    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------
    /**
     * Construye un nuevo panel tabla
     *
     * @param principalP es el panel padre de este panel
     */
    public PanelTabla(PanelCentral principalP) {

        principal = principalP;
        setSize(200, 200);

        modeloTabla = new DefaultTableModel();
        tabla = new JTable(modeloTabla) {
            public boolean isCellEditable(int col, int row) {
                return false;
            }
        };
        setLayout(new BorderLayout());

        modeloTabla.addColumn("Nodo insertado");
        modeloTabla.addColumn("Rotacion simple derecha");
        modeloTabla.addColumn("Rotacion simple izquierda");
        modeloTabla.addColumn("Rotacion doble derecha");
        modeloTabla.addColumn("Rotacion doble izquierda");
        modeloTabla.addColumn("Valor balanceo en la raiz");

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(getWidth() * 1 / 14);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(getWidth() * 3 / 14);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(getWidth() * 3 / 14);

        JScrollPane panelScroll = new JScrollPane(tabla);
        add(panelScroll, BorderLayout.CENTER);
    }

    void actualizarTabla(String[] tabla) {
        modeloTabla.addRow(tabla);

    }
}
