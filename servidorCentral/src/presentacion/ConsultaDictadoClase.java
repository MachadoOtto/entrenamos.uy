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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Set;

import logica.IDictadoClaseController;

import datatypes.DtClaseExt;

@SuppressWarnings("serial")
public class ConsultaDictadoClase extends JInternalFrame {
	
	/* Controlador de Dictado de Clase para las acciones del JInternalFrame */
	private IDictadoClaseController controlClase;
	
	/* Componentes graficas */
	// JLabel:
	private JLabel lblSeleccionInstitucion;
	private JLabel lblSeleccionActividad;
	private JLabel lblSeleccionClase;
	
	// JScrollPane:
    private JScrollPane scrollPane;
	
	// JTextArea:
    private JTextArea textArea;
	
	// JComboBox:
	private JComboBox<String> boxInstitucion;
	private JComboBox<String> boxActividad;
	private JComboBox<String> boxClase;
	
	
	// JButton:
	private JButton btnAceptar;
    private JButton btnCancelar;
    
    /* Crear frame */
	public ConsultaDictadoClase(IDictadoClaseController idcc) {
		// Inicializa controlador Dictado de Clase:
		controlClase = idcc;
		
		// Propiedades del JInternalFrame:
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consulta de Dictado de Clase");
		setBounds(10, 40, 410, 430);
		
		// GridLayout:
		GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[] { 30, 60, 60, 30, 30, 10 };
	    gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 };
	    gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
	    gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
	    getContentPane().setLayout(gridBagLayout);
        
        // JLabels:
        lblSeleccionInstitucion = new JLabel("Seleccione Institucion:");
        lblSeleccionInstitucion.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionInstitucion = new GridBagConstraints();
        gbc_lblSeleccionInstitucion.gridwidth = 2;
        gbc_lblSeleccionInstitucion.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionInstitucion.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionInstitucion.gridx = 1;
        gbc_lblSeleccionInstitucion.gridy = 0;
        getContentPane().add(lblSeleccionInstitucion, gbc_lblSeleccionInstitucion);
        
        lblSeleccionActividad = new JLabel("Seleccione Actividad Deportiva:");
        lblSeleccionActividad.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionActividad = new GridBagConstraints();
        gbc_lblSeleccionActividad.gridwidth = 2;
        gbc_lblSeleccionActividad.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionActividad.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionActividad.gridx = 1;
        gbc_lblSeleccionActividad.gridy = 2;
        getContentPane().add(lblSeleccionActividad, gbc_lblSeleccionActividad);
        
        lblSeleccionClase = new JLabel("Seleccione Clase:");
        lblSeleccionClase.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionClase = new GridBagConstraints();
        gbc_lblSeleccionClase.gridwidth = 2;
        gbc_lblSeleccionClase.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionClase.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionClase.gridx = 1;
        gbc_lblSeleccionClase.gridy = 4;
        getContentPane().add(lblSeleccionClase, gbc_lblSeleccionClase);
        
        // JScrollPane:
        scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridheight = 4;
        gbc_scrollPane.gridwidth = 3;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 1;
        gbc_scrollPane.gridy = 7;
        getContentPane().add(scrollPane, gbc_scrollPane);
        
        // JTextArea:
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("Aqui se mostrara la informacion de la Clase seleccionada.");
        scrollPane.setViewportView(textArea);
        
        // JComboBox:
        boxInstitucion = new JComboBox<>();
        boxInstitucion.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		int selectIndex = boxInstitucion.getSelectedIndex();
    			boxActividad.removeAllItems();
    			DefaultComboBoxModel<String> modelActividad = new DefaultComboBoxModel<>();
    			modelActividad.addElement("-");
    			if (selectIndex > 0) {
        			Set<String> actividades = controlClase.obtenerActividades(boxInstitucion.getItemAt(selectIndex));
                    for (String x: actividades) {
                    	modelActividad.addElement(x);
                    }
                    boxActividad.setEnabled(true);
        		} else {
        			boxActividad.setEnabled(false);
        		}
            	boxActividad.setModel(modelActividad);
        	}
        });
        GridBagConstraints gbc_boxInstitucion = new GridBagConstraints();
        gbc_boxInstitucion.gridwidth = 3;
        gbc_boxInstitucion.insets = new Insets(0, 0, 5, 5);
        gbc_boxInstitucion.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxInstitucion.gridx = 1;
        gbc_boxInstitucion.gridy = 1;
        getContentPane().add(boxInstitucion, gbc_boxInstitucion);
        
        boxActividad = new JComboBox<>();
        boxActividad.setEnabled(false);
        boxActividad.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		int selectIndex = boxActividad.getSelectedIndex();
    			boxClase.removeAllItems();
    			DefaultComboBoxModel<String> modelClase = new DefaultComboBoxModel<>();
    			modelClase.addElement("-");
    			if (selectIndex > 0) {
        			Set<String> clases = controlClase.obtenerClases(boxInstitucion.getItemAt(boxInstitucion.getSelectedIndex()), 
        					boxActividad.getItemAt(selectIndex));
                    for (String x: clases) {
                    	modelClase.addElement(x);
                    }
                    boxClase.setEnabled(true);
        		} else {
        			boxClase.setEnabled(false);
        		}
    			boxClase.setModel(modelClase);
        	}
        });
        GridBagConstraints gbc_boxActividad = new GridBagConstraints();
        gbc_boxActividad.gridwidth = 3;
        gbc_boxActividad.insets = new Insets(0, 0, 5, 5);
        gbc_boxActividad.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxActividad.gridx = 1;
        gbc_boxActividad.gridy = 3;
        getContentPane().add(boxActividad, gbc_boxActividad);
        
        boxClase = new JComboBox<>();
        GridBagConstraints gbc_boxClase = new GridBagConstraints();
        gbc_boxClase.gridwidth = 3;
        gbc_boxClase.insets = new Insets(0, 0, 5, 5);
        gbc_boxClase.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxClase.gridx = 1;
        gbc_boxClase.gridy = 5;
        getContentPane().add(boxClase, gbc_boxClase);
        boxClase.setEnabled(false);
        
        // JButton:
        btnAceptar = new JButton("Consultar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                consultarClase(arg0);
            }
        });
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptar.gridx = 2;
        gbc_btnAceptar.gridy = 12;
        getContentPane().add(btnAceptar, gbc_btnAceptar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
                setVisible(false);
            }
        });    
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.gridwidth = 2;
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.gridx = 3;
        gbc_btnCancelar.gridy = 12;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
	}
	
	// Metodo de invocacion de la Consulta de Clase
    protected void consultarClase(ActionEvent arg0) {
        if (checkDatos()) {
        	// Obtengo datos de los controles Swing:
            String nombreInstitucion = boxInstitucion.getItemAt(boxInstitucion.getSelectedIndex());
            String nombreActividad = boxActividad.getItemAt(boxActividad.getSelectedIndex());
            String nombreClase = boxClase.getItemAt(boxClase.getSelectedIndex());
            DtClaseExt datosClase = controlClase.seleccionarClase(nombreInstitucion, nombreActividad, nombreClase);
            textArea.setText(datosClase.toString());
        }
    }
	
	
	// Realiza el checkeo de la entrada de datos.
    private boolean checkDatos() {
        int indexInstitucion = boxInstitucion.getSelectedIndex();
        int indexActividad = boxActividad.getSelectedIndex();
        int indexClase = boxClase.getSelectedIndex();
        if (indexInstitucion < 1 || indexActividad < 1 || indexClase < 1) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Consulta de Dictado de Clase",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
	
	// Cargar Datos al JComboBox
    // Se invoca el método antes de hacer visible el JInternalFrame
    public void cargarInstitucion() {
        DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<>();
        model.addElement("-");
        for(String x: controlClase.obtenerInstituciones()) {
            model.addElement(x);
        }
        boxInstitucion.setModel(model);
    }
    
    // Limpia el JInternalFrame
 	public void clear() {
 		textArea.setText("Aqui se mostrara la informacion de la Clase seleccionada.");
        boxInstitucion.removeAllItems();
        boxActividad.removeAllItems();
        boxActividad.setEnabled(false);
        boxClase.removeAllItems();
        boxClase.setEnabled(false);
    }
}