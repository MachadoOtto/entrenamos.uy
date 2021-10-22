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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import excepciones.CategoriaException;

import logica.IActividadDeportivaController;

import datatypes.DtCategoria;
import java.awt.Component;
import javax.swing.Box;

@SuppressWarnings("serial")
public class AltaCategoria extends JInternalFrame {
	
	private IActividadDeportivaController actControl;
	
	/* Componentes graficas */
	// JLabel:
	private JLabel lblIngreseNombre;
	
	// JTextField:
	private JTextField campoNombre; // Es unico.
	
	// JButton:
	private JButton btnAceptar;
    private JButton btnCancelar;
    private Component verticalStrut;
    
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
		setBounds(10,  40,  380,  286);
		
		// GridLayout:
		GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[] { 30,  60,  60,  60,  60,  30,  10 };
	    gridBagLayout.rowHeights = new int[] { 0,  30,  30,  30,  30,  30,  30,  0,  0 };
	    gridBagLayout.columnWeights = new double[] { 0.0,  1.0,  1.0,  1.0,  0.0,  Double.MIN_VALUE };
	    gridBagLayout.rowWeights = new double[] { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0 };
	    getContentPane().setLayout(gridBagLayout);
        
        verticalStrut = Box.createVerticalStrut(20);
        GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
        gbc_verticalStrut.insets = new Insets(0,  0,  5,  5);
        gbc_verticalStrut.gridx = 1;
        gbc_verticalStrut.gridy = 0;
        getContentPane().add(verticalStrut,  gbc_verticalStrut);
        
        // JLabels:
        lblIngreseNombre = new JLabel("Nombre de Categoria:");
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
        gbc_lblIngreseNombre.gridwidth = 5;
        gbc_lblIngreseNombre.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseNombre.insets = new Insets(0,  0,  5,  5);
        gbc_lblIngreseNombre.gridx = 1;
        gbc_lblIngreseNombre.gridy = 1;
        getContentPane().add(lblIngreseNombre,  gbc_lblIngreseNombre);

        // JTextField:
        campoNombre = new JTextField();
        lblIngreseNombre.setLabelFor(campoNombre);
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.gridwidth = 5;
        gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
        gbc_textFieldNombre.insets = new Insets(0,  0,  5,  5);
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 2;
        getContentPane().add(campoNombre,  gbc_textFieldNombre);
        campoNombre.setColumns(10);
        
        btnCancelar = new JButton("Limpiar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });    
        
        // JButton:
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	darAltaDeClase(arg0);
            }
        });
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.anchor = GridBagConstraints.EAST;
        gbc_btnAceptar.gridwidth = 2;
        gbc_btnAceptar.insets = new Insets(0,  0,  5,  5);
        gbc_btnAceptar.gridx = 3;
        gbc_btnAceptar.gridy = 4;
        getContentPane().add(btnAceptar,  gbc_btnAceptar);
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.insets = new Insets(0,  0,  5,  5);
        gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnCancelar.gridx = 5;
        gbc_btnCancelar.gridy = 4;
        getContentPane().add(btnCancelar,  gbc_btnCancelar);
        
	}
	
	// Metodo de invocacion del Alta de Categoria
    protected void darAltaDeClase(ActionEvent arg0) {
        if (checkDatos()) {
        	// Obtengo datos de los controles Swing:
        	String nombre = campoNombre.getText().trim();
	        try {
	        	actControl.ingresarCatergoria(new DtCategoria(nombre));
				JOptionPane.showMessageDialog(this,  "La Categoria ha sido registrada de forma exitosa.",  
						this.getTitle(),  JOptionPane.INFORMATION_MESSAGE);
				clear();
                setVisible(false);
	        } catch (CategoriaException e) {
	        	JOptionPane.showMessageDialog(this,  e.getMessage(),  getTitle(),  JOptionPane.ERROR_MESSAGE);
            }    
        }
    }
	
	// Realiza el checkeo de la entrada de datos.
    private boolean checkDatos() {
        String nombre = campoNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,  "No puede haber campos vacios.",  this.getTitle(), 
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
	
	// Limpia el JInternalFrame
	public void clear() {
		campoNombre.setText("");
		//campoDescripcion.setText("");
    }
}