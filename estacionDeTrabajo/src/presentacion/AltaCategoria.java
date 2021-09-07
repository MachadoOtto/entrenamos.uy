/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import excepciones.CategoriaException;

import logica.IActividadDeportivaController;

import datatypes.DtCategoria;

@SuppressWarnings("serial")
public class AltaCategoria extends JInternalFrame {
	
	private IActividadDeportivaController actControl;
	
	/* Componentes graficas */
	// JLabel:
	private JLabel lblIngreseNombre;
	private JLabel lblIngreseDesc;
	
	// JTextField:
	private JTextField campoNombre; // Es unico.
    private JTextArea campoDescripcion;
	
	// JButton:
	private JButton btnAceptar;
    private JButton btnCancelar;
    
    private JScrollPane scrollPane;
    
    /* Crear frame */
	public AltaCategoria(IActividadDeportivaController iacc) {
		// Inicializa controlador:
		actControl = iacc;
		
		// Propiedades del JInternalFrame:
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de Categoria");
		setBounds(10, 40, 380, 320);
		
		// GridLayout:
		GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[] { 30, 60, 60, 60, 60, 30, 10 };
	    gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 0, 0 };
	    gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
	    gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE, 0.0, 0.0 };
	    getContentPane().setLayout(gridBagLayout);
        
        // JLabels:
        lblIngreseNombre = new JLabel("Nombre de Categoria:");
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
        gbc_lblIngreseNombre.gridwidth = 4;
        gbc_lblIngreseNombre.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseNombre.gridx = 1;
        gbc_lblIngreseNombre.gridy = 0;
        getContentPane().add(lblIngreseNombre, gbc_lblIngreseNombre);

        // JTextField:
        campoNombre = new JTextField();
        lblIngreseNombre.setLabelFor(campoNombre);
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.gridwidth = 4;
        gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 1;
        getContentPane().add(campoNombre, gbc_textFieldNombre);
        campoNombre.setColumns(10);
        
        lblIngreseDesc = new JLabel("Descripcion:");
        lblIngreseDesc.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblIngreseDesc = new GridBagConstraints();
        gbc_lblIngreseDesc.gridwidth = 2;
        gbc_lblIngreseDesc.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseDesc.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseDesc.gridx = 1;
        gbc_lblIngreseDesc.gridy = 2;
        getContentPane().add(lblIngreseDesc, gbc_lblIngreseDesc);
        
        scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridheight = 3;
        gbc_scrollPane.gridwidth = 4;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 1;
        gbc_scrollPane.gridy = 3;
        getContentPane().add(scrollPane, gbc_scrollPane);
        
        campoDescripcion = new JTextArea();
        scrollPane.setViewportView(campoDescripcion);
        
        // JButton:
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	darAltaDeClase(arg0);
            }
        });
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
        gbc_btnAceptar.gridx = 3;
        gbc_btnAceptar.gridy = 7;
        getContentPane().add(btnAceptar, gbc_btnAceptar);
        
        btnCancelar = new JButton("Limpiar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });    
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
        gbc_btnCancelar.gridwidth = 2;
        gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnCancelar.gridx = 4;
        gbc_btnCancelar.gridy = 7;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
	}
	
	// Metodo de invocacion del Alta de Categoria
    protected void darAltaDeClase(ActionEvent arg0) {
        if (checkDatos()) {
        	// Obtengo datos de los controles Swing:
        	String nombre = campoNombre.getText().trim();
            String desc = campoDescripcion.getText().trim();
	        try {
	        	actControl.ingresarCatergoria(new DtCategoria(nombre, desc));
				JOptionPane.showMessageDialog(this, "La Categoria ha sido registrada de forma exitosa.", 
						this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				clear();
	        } catch (CategoriaException e) {
	        	JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
            }    
        }
    }
	
	// Realiza el checkeo de la entrada de datos.
    private boolean checkDatos() {
        String nombre = campoNombre.getText().trim();
        String desc = campoDescripcion.getText().trim();
        if (nombre.isEmpty() || desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacios.", this.getTitle(),
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
	
	// Limpia el JInternalFrame
	public void clear() {
		campoNombre.setText("");
		campoDescripcion.setText("");
    }
}