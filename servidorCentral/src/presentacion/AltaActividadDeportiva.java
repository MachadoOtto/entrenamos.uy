package presentacion;

import datatypes.DtActividadDeportiva;
import datatypes.DtFecha;

import java.awt.EventQueue;

import logica.HandlerInstitucion;
import logica.IActividadDeportivaController;
import logica.Institucion;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class AltaActividadDeportiva extends JInternalFrame{
	
	//Controller
	private IActividadDeportivaController IADC;
	
	private JPanel panelInstitucion;
	private JLabel lblInstitucion;
	private JComboBox comboBoxInstitucion;
	private JPanel panelCabecera;
	private JLabel lblCabecera;
	private JPanel panelDatosActDep;
	private JLabel lblNombre;
	private JTextField textFieldNombre;
	private JLabel lblDescripcion;
	private JTextField textFieldDescripcion;
	private JLabel lblDuracion;
	private JPanel panelDuracionCosto;
	private JTextField textFieldDuracion;
	private JLabel lblMins;
	private JLabel lblCosto;
	private JTextField textFieldCosto;
	private JLabel lblPesos;
	private JLabel lblFecha;
	private JPanel panelFecha;
	private JComboBox comboBoxDia;
	private JComboBox comboBoxMes;
	private JComboBox comboBoxanio;
	private JPanel panelAcepCancel;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	public AltaActividadDeportiva(IActividadDeportivaController IADC) {
		this.IADC = IADC;
		//Configuracion del internal frame
		setTitle("Alta Actividad Deportiva");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 478, 551);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 433, 0, 0};
		gridBagLayout.rowHeights = new int[]{19, 33, 48, 310, 14, 69, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		panelCabecera = new JPanel();
		GridBagConstraints gbc_panelCabecera = new GridBagConstraints();
		gbc_panelCabecera.anchor = GridBagConstraints.NORTH;
		gbc_panelCabecera.insets = new Insets(0, 0, 5, 5);
		gbc_panelCabecera.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelCabecera.gridx = 1;
		gbc_panelCabecera.gridy = 1;
		getContentPane().add(panelCabecera, gbc_panelCabecera);
		GridBagLayout gbl_panelCabecera = new GridBagLayout();
		gbl_panelCabecera.columnWidths = new int[]{165, 14, 0};
		gbl_panelCabecera.rowHeights = new int[]{19, 0};
		gbl_panelCabecera.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelCabecera.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelCabecera.setLayout(gbl_panelCabecera);
		
		lblCabecera = new JLabel("Ingrese los siguientes datos:");
		lblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblCabecera = new GridBagConstraints();
		gbc_lblCabecera.insets = new Insets(0, 0, 0, 5);
		gbc_lblCabecera.anchor = GridBagConstraints.NORTH;
		gbc_lblCabecera.gridx = 0;
		gbc_lblCabecera.gridy = 0;
		panelCabecera.add(lblCabecera, gbc_lblCabecera);
		
		panelInstitucion = new JPanel();
		GridBagConstraints gbc_panelInstitucion = new GridBagConstraints();
		gbc_panelInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_panelInstitucion.fill = GridBagConstraints.BOTH;
		gbc_panelInstitucion.gridx = 1;
		gbc_panelInstitucion.gridy = 2;
		getContentPane().add(panelInstitucion, gbc_panelInstitucion);
		GridBagLayout gbl_panelInstitucion = new GridBagLayout();
		gbl_panelInstitucion.columnWidths = new int[]{80, 322, 0};
		gbl_panelInstitucion.rowHeights = new int[]{37, 0};
		gbl_panelInstitucion.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelInstitucion.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelInstitucion.setLayout(gbl_panelInstitucion);
		
		lblInstitucion = new JLabel("Institucion:");
		lblInstitucion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblInstitucion = new GridBagConstraints();
		gbc_lblInstitucion.insets = new Insets(0, 0, 0, 5);
		gbc_lblInstitucion.gridx = 0;
		gbc_lblInstitucion.gridy = 0;
		panelInstitucion.add(lblInstitucion, gbc_lblInstitucion);
		
		DefaultComboBoxModel<String> comboModelInstitucion = new DefaultComboBoxModel<>();
		comboModelInstitucion.addElement("---Seleccione una institucion---");
		Set<String> instituciones = IADC.obtenerInstituciones();
		Collection<String> strCollection = instituciones;
		Iterator<String> itStr = strCollection.iterator();
		while (itStr.hasNext()) {
			String strAux = itStr.next();
			comboModelInstitucion.addElement(strAux);
		}
		
		comboBoxInstitucion = new JComboBox<>(comboModelInstitucion);
		GridBagConstraints gbc_comboBoxInstitucion = new GridBagConstraints();
		gbc_comboBoxInstitucion.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInstitucion.gridx = 1;
		gbc_comboBoxInstitucion.gridy = 0;
		panelInstitucion.add(comboBoxInstitucion, gbc_comboBoxInstitucion);
		
		panelDatosActDep = new JPanel();
		panelDatosActDep.setToolTipText("");
		panelDatosActDep.setBorder(new TitledBorder(null, "Ingresar datos actividad deportiva: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelDatosActDep = new GridBagConstraints();
		gbc_panelDatosActDep.insets = new Insets(0, 0, 5, 5);
		gbc_panelDatosActDep.fill = GridBagConstraints.BOTH;
		gbc_panelDatosActDep.gridx = 1;
		gbc_panelDatosActDep.gridy = 3;
		getContentPane().add(panelDatosActDep, gbc_panelDatosActDep);
		GridBagLayout gbl_panelDatosActDep = new GridBagLayout();
		gbl_panelDatosActDep.columnWidths = new int[]{19, 75, 289, 0};
		gbl_panelDatosActDep.rowHeights = new int[]{37, 167, 37, 36, 0};
		gbl_panelDatosActDep.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDatosActDep.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDatosActDep.setLayout(gbl_panelDatosActDep);
		
		lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 0;
		panelDatosActDep.add(lblNombre, gbc_lblNombre);
		
		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 0;
		panelDatosActDep.add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 1;
		panelDatosActDep.add(lblDescripcion, gbc_lblDescripcion);
		
		textFieldDescripcion = new JTextField();
		GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
		gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textFieldDescripcion.gridx = 2;
		gbc_textFieldDescripcion.gridy = 1;
		panelDatosActDep.add(textFieldDescripcion, gbc_textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		lblDuracion = new JLabel("Duracion:");
		GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
		gbc_lblDuracion.anchor = GridBagConstraints.WEST;
		gbc_lblDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracion.gridx = 1;
		gbc_lblDuracion.gridy = 2;
		panelDatosActDep.add(lblDuracion, gbc_lblDuracion);
		
		panelDuracionCosto = new JPanel();
		GridBagConstraints gbc_panelDuracionCosto = new GridBagConstraints();
		gbc_panelDuracionCosto.insets = new Insets(0, 0, 5, 0);
		gbc_panelDuracionCosto.fill = GridBagConstraints.BOTH;
		gbc_panelDuracionCosto.gridx = 2;
		gbc_panelDuracionCosto.gridy = 2;
		panelDatosActDep.add(panelDuracionCosto, gbc_panelDuracionCosto);
		GridBagLayout gbl_panelDuracionCosto = new GridBagLayout();
		gbl_panelDuracionCosto.columnWidths = new int[]{48, 43, 78, 60, 44, 0};
		gbl_panelDuracionCosto.rowHeights = new int[]{32, 0};
		gbl_panelDuracionCosto.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDuracionCosto.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelDuracionCosto.setLayout(gbl_panelDuracionCosto);
		
		textFieldDuracion = new JTextField();
		GridBagConstraints gbc_textFieldDuracion = new GridBagConstraints();
		gbc_textFieldDuracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDuracion.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldDuracion.gridx = 0;
		gbc_textFieldDuracion.gridy = 0;
		panelDuracionCosto.add(textFieldDuracion, gbc_textFieldDuracion);
		textFieldDuracion.setColumns(10);
		
		lblMins = new JLabel("Mins");
		lblMins.setForeground(SystemColor.textInactiveText);
		GridBagConstraints gbc_lblMins = new GridBagConstraints();
		gbc_lblMins.anchor = GridBagConstraints.WEST;
		gbc_lblMins.insets = new Insets(0, 0, 0, 5);
		gbc_lblMins.gridx = 1;
		gbc_lblMins.gridy = 0;
		panelDuracionCosto.add(lblMins, gbc_lblMins);
		
		lblCosto = new JLabel("Costo:");
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.insets = new Insets(0, 0, 0, 5);
		gbc_lblCosto.anchor = GridBagConstraints.EAST;
		gbc_lblCosto.gridx = 2;
		gbc_lblCosto.gridy = 0;
		panelDuracionCosto.add(lblCosto, gbc_lblCosto);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setText("");
		GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
		gbc_textFieldCosto.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCosto.gridx = 3;
		gbc_textFieldCosto.gridy = 0;
		panelDuracionCosto.add(textFieldCosto, gbc_textFieldCosto);
		textFieldCosto.setColumns(10);
		
		lblPesos = new JLabel("Pesos");
		lblPesos.setForeground(SystemColor.textInactiveText);
		GridBagConstraints gbc_lblPesos = new GridBagConstraints();
		gbc_lblPesos.anchor = GridBagConstraints.WEST;
		gbc_lblPesos.gridx = 4;
		gbc_lblPesos.gridy = 0;
		panelDuracionCosto.add(lblPesos, gbc_lblPesos);
		
		lblFecha = new JLabel("Fecha Alta:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.WEST;
		gbc_lblFecha.insets = new Insets(0, 0, 0, 5);
		gbc_lblFecha.gridx = 1;
		gbc_lblFecha.gridy = 3;
		panelDatosActDep.add(lblFecha, gbc_lblFecha);
		
		panelFecha = new JPanel();
		GridBagConstraints gbc_panelFecha = new GridBagConstraints();
		gbc_panelFecha.fill = GridBagConstraints.BOTH;
		gbc_panelFecha.gridx = 2;
		gbc_panelFecha.gridy = 3;
		panelDatosActDep.add(panelFecha, gbc_panelFecha);
		GridBagLayout gbl_panelFecha = new GridBagLayout();
		gbl_panelFecha.columnWidths = new int[]{51, 133, 77, 0};
		gbl_panelFecha.rowHeights = new int[]{36, 0};
		gbl_panelFecha.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelFecha.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelFecha.setLayout(gbl_panelFecha);
		
		DefaultComboBoxModel<String> comboModelDia = new DefaultComboBoxModel<>();
		comboModelDia.addElement("-");
		for (int i = 1; i <= 31; i++) {
			comboModelDia.addElement(String.valueOf(i));
		}
		
		comboBoxDia = new JComboBox<>(comboModelDia);
		GridBagConstraints gbc_comboBoxDia = new GridBagConstraints();
		gbc_comboBoxDia.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDia.gridx = 0;
		gbc_comboBoxDia.gridy = 0;
		panelFecha.add(comboBoxDia, gbc_comboBoxDia);
		
		String[] meses = new String[] {"-", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
        		"Setiembre", "Octubre", "Noviembre", "Diciembre"};
		
		DefaultComboBoxModel<String> comboModelMes = new DefaultComboBoxModel<>(meses);
		
		comboBoxMes = new JComboBox<>(comboModelMes);
		comboBoxMes.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if (comboBoxMes.getSelectedIndex() % 2 == 0) {
        			comboBoxDia.removeItem("31");
        		} else {
        			if (comboModelDia.getIndexOf("31") == -1)
        				comboModelDia.addElement("31");
        		}
        	}
        });
		GridBagConstraints gbc_comboBoxMes = new GridBagConstraints();
		gbc_comboBoxMes.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxMes.gridx = 1;
		gbc_comboBoxMes.gridy = 0;
		panelFecha.add(comboBoxMes, gbc_comboBoxMes);
		
		DefaultComboBoxModel<String> comboModelAnio = new DefaultComboBoxModel<>();
		comboModelAnio.addElement("-");
		for (int j = 2025; j >= 2018; j--) {
			comboModelAnio.addElement(String.valueOf(j));
		}
		comboBoxanio = new JComboBox<>(comboModelAnio);
		GridBagConstraints gbc_comboBoxanio = new GridBagConstraints();
		gbc_comboBoxanio.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxanio.gridx = 2;
		gbc_comboBoxanio.gridy = 0;
		panelFecha.add(comboBoxanio, gbc_comboBoxanio);
		
		panelAcepCancel = new JPanel();
		GridBagConstraints gbc_panelAcepCancel = new GridBagConstraints();
		gbc_panelAcepCancel.insets = new Insets(0, 0, 0, 5);
		gbc_panelAcepCancel.fill = GridBagConstraints.BOTH;
		gbc_panelAcepCancel.gridx = 1;
		gbc_panelAcepCancel.gridy = 5;
		getContentPane().add(panelAcepCancel, gbc_panelAcepCancel);
		GridBagLayout gbl_panelAcepCancel = new GridBagLayout();
		gbl_panelAcepCancel.columnWidths = new int[]{69, 138, 0, 142, 25, 0};
		gbl_panelAcepCancel.rowHeights = new int[]{49, 0};
		gbl_panelAcepCancel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelAcepCancel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelAcepCancel.setLayout(gbl_panelAcepCancel);
		
		btnAceptar = new JButton("Aceptar");
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 0;
		panelAcepCancel.add(btnAceptar, gbc_btnAceptar); 
		btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (checkFormulario()) {
            		altaActDepACEPTAR();
            	}
            }
        });
		
		btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 0;
		panelAcepCancel.add(btnCancelar, gbc_btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
                setVisible(false);
            }
        }); 
	}
	
	private boolean checkFormulario() {
		String nombreInsti = comboBoxInstitucion.getName();
		String nombre = textFieldNombre.getText();
        String descripcion = textFieldDescripcion.getText();
        int duracion = Integer.parseInt(textFieldDuracion.getText());
    	float costo = Float.parseFloat(textFieldCosto.getText());
    	int anio = Integer.parseInt(comboBoxanio.getName());
    	int mes = comboBoxMes.getSelectedIndex();
    	int dia = comboBoxDia.getSelectedIndex();

        if (nombreInsti.equals("---Seleccione una institucion---") || nombre.isEmpty() || descripcion.isEmpty()
        		|| textFieldDuracion.getText().isEmpty() || textFieldCosto.getText().isEmpty() || comboBoxanio.getName().equals("-")
        		    || comboBoxMes.getName().equals("-") || comboBoxDia.getName().equals("-")) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Alta actividad deportiva", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Integer.parseInt(textFieldDuracion.getText());
            Float.parseFloat(textFieldCosto.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Duracion y costo deben ser numeros", "Alta actividad deportiva",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
	}
	
	private void altaActDepACEPTAR() {
		String nombreInsti = comboBoxInstitucion.getName();
    	String nombre = textFieldNombre.getText();
    	String descripcion = textFieldDescripcion.getText();
    	int duracion = Integer.parseInt(textFieldDuracion.getText());
    	float costo = Float.parseFloat(textFieldCosto.getText());
    	int anio = comboBoxanio.getSelectedIndex();
    	int mes = comboBoxMes.getSelectedIndex();
    	int dia = comboBoxDia.getSelectedIndex();
    	//boolean nombreRepetido = false;
    	//HandlerInstitucion hi = HandlerInstitucion.getInstance();
    	//for(String ins: IADC.obtenerInstituciones()) {
        	//if (hi.findInstitucion(ins).existeActDep(nombre)) {
        		//nombreRepetido = true;
        		//break;
        	//}
        //}
        DtFecha fechaAlta = new DtFecha(anio,mes,dia,0,0,0);
        DtActividadDeportiva datosAD = new DtActividadDeportiva(nombre,descripcion,duracion,costo,fechaAlta);
        boolean datosIngConExito = IADC.ingresarDatosActividadDep(nombreInsti, datosAD);
        if (datosIngConExito) {
        	JOptionPane.showMessageDialog(this, "Actividad deportiva dada de alta con exito", "Alta actividad deportiva", JOptionPane.INFORMATION_MESSAGE);
        } else {
			JOptionPane.showMessageDialog(this, "Ya existe una actividad deportiva con el nombre ingresado", "Alta actividad deportiva", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void cargarInstituciones() {
        DefaultComboBoxModel<String> modelInstituciones;
        modelInstituciones = new DefaultComboBoxModel<>();
        modelInstituciones.addElement("---Seleccione una institucion---");
        for(String ins: IADC.obtenerInstituciones()) {
        	modelInstituciones.addElement(ins);
        }
        comboBoxInstitucion.setModel(modelInstituciones);
    }
	
	public void clear() {
		comboBoxInstitucion.setSelectedIndex(0);
		textFieldNombre.setText("");
		textFieldDescripcion.setText("");
		textFieldDuracion.setText("");
		textFieldCosto.setText("");
		comboBoxDia.setSelectedIndex(0);
		comboBoxMes.setSelectedIndex(0);
		comboBoxanio.setSelectedIndex(0);
	}
}
