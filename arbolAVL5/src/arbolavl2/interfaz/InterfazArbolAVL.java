
package arbolavl2.interfaz;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import arbolavl2.logica.ArbolAVL;
import java.util.Random;
import arbolAVL.Arbol;
import arbolAVL.PanelAVL;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * Esta es la ventana principal.
 */
public class InterfazArbolAVL extends JFrame {

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Clase principal de la logica
     */
    private ArbolAVL arbolAVL;
  

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------
    /**
     * PanelRN central AVL
     */
    private PanelCentral panelCentral;
    /**
     * Boton añadir RN
     */
    private JButton btnAñadir;
    /**
     * Boton borrar RN
     */
    private JButton btnBorrar;
    /**
     * txAreaNiveles
     */
    public static JTextArea txAreaNiveles;

    /**
     * txAreaNiveles
     */
    private JScrollPane panelBarraAreaNiveles;

    /**
     * Label digite nodo
     */
    private JLabel jLabel1;
    /**
     * PanelRN botones RN
     */
    private JPanel pnlComandos;
    /**
     * PanelRN pintar arbol RN
     */
    private JPanel pnlVisorNodoRN;
    /**
     * PanelAVL pintar arbol AVL
     */
    private JPanel pnlVisorNodoAVL;
    /**
     * campo para añadir nodo RN
     */
    private JTextField txtIngresoNodo;
    /**
     * panel comunicacionAVL
     */
    private PanelAVL comunicacionAVL;
    /**
     * panel comunicacionRN
     */


    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Crea la ventana principal
     *
     */
    public InterfazArbolAVL() {
        // Crea la clase principal
        arbolAVL = new ArbolAVL();
       
        //crea un arbol inicial e intenta insertar 50 numeros entre 0 y 100
        Random rnd = new Random();

        setTitle("Arbol AVL y Arbol RojiNegro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);

        // Construye la forma
        setLayout(new BorderLayout());
        setSize(1440, 850);
        panelCentral = new PanelCentral(this);
        add(panelCentral, BorderLayout.EAST);

        pnlVisorNodoRN = new JPanel();
        pnlVisorNodoAVL = new JPanel();
        pnlComandos = new JPanel();
        txtIngresoNodo = new JTextField();
        jLabel1 = new JLabel();
        btnAñadir = new JButton();
        btnBorrar = new JButton();
        txAreaNiveles = new JTextArea();
        panelBarraAreaNiveles = new JScrollPane();

        pnlVisorNodoRN.setBackground(new Color(255, 255, 255));
        pnlVisorNodoRN.setLayout(null);
        add(pnlVisorNodoRN, BorderLayout.CENTER);
        pnlVisorNodoRN.setBounds(0, 50, 475, 800);

        pnlVisorNodoAVL.setBackground(new Color(255, 255, 255));
        pnlVisorNodoAVL.setLayout(null);
        add(pnlVisorNodoAVL, BorderLayout.CENTER);
        pnlVisorNodoAVL.setBounds(475, 50, 475, 800);

        pnlComandos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlComandos.setLayout(null);
        pnlComandos.add(txtIngresoNodo);
        txtIngresoNodo.setBounds(100, 10, 89, 30);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel1.setText("Digite Nodo:");
        pnlComandos.add(jLabel1);
        jLabel1.setBounds(10, 10, 74, 30);

        btnAñadir.setText("Añadir");
        btnAñadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        pnlComandos.add(btnAñadir);
        btnAñadir.setBounds(220, 10, 100, 30);

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        pnlComandos.add(btnBorrar);
        btnBorrar.setBounds(340, 10, 100, 30);
        txAreaNiveles.setEditable(false);
        txAreaNiveles.setFont(new Font("Monospaced", 0, 12));
        txAreaNiveles.setLineWrap(true);        

        panelBarraAreaNiveles = new JScrollPane(txAreaNiveles);
        panelBarraAreaNiveles.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelBarraAreaNiveles.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);       
        this.add(panelBarraAreaNiveles);
        panelBarraAreaNiveles.setBounds(460, 10, 490, 30);
        
        add(pnlComandos);
        pnlComandos.setBounds(10, 11, 480, 47);
        
    
        comunicacionAVL = new PanelAVL(arbolAVL);
        comunicacionAVL.setBounds(0, 0, pnlVisorNodoAVL.getWidth(), pnlVisorNodoAVL.getHeight());
        pnlVisorNodoAVL.add(comunicacionAVL);

        for (int i = 0; i < 10; i++) {

            Integer elemento = (int) (rnd.nextDouble() * 100);
            int numero = (int) elemento;
                       comunicacionAVL.añadirNodo(numero);
        }
        arbolAVL.imprimirPorNiveles();
        
        pnlVisorNodoRN.repaint();
        pnlVisorNodoAVL.repaint();        
    }

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------
    String[] darDatosTablaAVL() {
        String[] datos = {Integer.toString(arbolAVL.darNumeroNodo()),
            Integer.toString(arbolAVL.darRotacionSimpleDerecha()),
            Integer.toString(arbolAVL.darRotacionSimpleIzquierda()),
            Integer.toString(arbolAVL.darRotacionDobleDerecha()),
            Integer.toString(arbolAVL.darRotacionDobleIzquierda()),
            Integer.toString(arbolAVL.darBalanceoRaiz())
        };
        return datos;
    }

    void actualizarTabla() {
        String[] tablaAVL = darDatosTablaAVL();
        

        panelCentral.actualizarTablaAVL(tablaAVL);

    }

    // -----------------------------------------------------------------
    // Metodos 
    // -----------------------------------------------------------------
    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int nodo = Integer.parseInt(txtIngresoNodo.getText());
                        comunicacionAVL.añadirNodo(nodo);
            pnlVisorNodoRN.repaint();
            comunicacionAVL.repaint();
            txtIngresoNodo.setText("");
            System.out.println("\n");
            arbolAVL.imprimirPorNiveles();
            actualizarTabla();
            txAreaNiveles.setText(arbolAVL.retornarNiveles());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        new InterfazArbolAVL().setVisible(true);
    }

  

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------
    /**
     * Este metodo ejecuta la aplicacion, creando una nueva interfaz
     *
     * @param args
     */
    public static void main(String[] args) {

        InterfazArbolAVL interfaz = new InterfazArbolAVL();
        interfaz.setVisible(true);
    }
}
