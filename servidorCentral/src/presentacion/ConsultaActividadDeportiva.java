package presentacion;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import logica.ActividadDeportiva;
import logica.HandlerCuponera;
import logica.HandlerInstitucion;
import logica.IActividadDeportivaController;
import logica.Institucion;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import datatypes.DtClaseExt;
import datatypes.DtCuponera;
import datatypes.DtFecha;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ConsultaActividadDeportiva extends JInternalFrame {
	
	//Controller
	private IActividadDeportivaController IADC;
	private JLabel lblCabecera;
	private JPanel panelInsActDep;
	private JLabel lblIns;
	private JLabel lblActDep;
	private JComboBox comboBoxIns;
	private JComboBox comboBoxActDep;
	private JPanel panelDatosAD;
	private JLabel lblNombre;
	private JLabel lblDesc;
	private JTextField textFieldNombre;
	private JTextField textFieldDesc;
	private JLabel lblDuracion;
	private JLabel lblFecha;
	private JPanel panelDurCost;
	private JPanel panelFecha;
	private JTextField textFieldDur;
	private JLabel lblMins;
	private JLabel lblCosto;
	private JTextField textFieldCosto;
	private JLabel lblPesos;
	private JTextField textFieldDia;
	private JTextField textFieldMes;
	private JTextField textFieldAnio;
	private JPanel panelClasesCuponeras;
	private JLabel lblClases;
	private JLabel lblCuponeras;
	private JComboBox comboBoxClases;
	private JComboBox comboBoxCuponeras;
	private JLabel lblEnd;
	private JTextField textFieldClase;
	private JTextField textFieldCuponeras;
	
	public ConsultaActividadDeportiva(IActividadDeportivaController IADC) {
		
		this.IADC = IADC;
		
		//Configuracion del frame
		setTitle("Consulta Actividad Deportiva");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 478, 640);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 421, 0, 0};
		gridBagLayout.rowHeights = new int[]{47, 89, 255, 193, 22, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		lblCabecera = new JLabel("Seleccione la actividad deportiva de la cual desea obtener la informacion:");
		GridBagConstraints gbc_lblCabecera = new GridBagConstraints();
		gbc_lblCabecera.anchor = GridBagConstraints.WEST;
		gbc_lblCabecera.insets = new Insets(0, 0, 5, 5);
		gbc_lblCabecera.gridx = 1;
		gbc_lblCabecera.gridy = 0;
		getContentPane().add(lblCabecera, gbc_lblCabecera);
		
		panelInsActDep = new JPanel();
		GridBagConstraints gbc_panelInsActDep = new GridBagConstraints();
		gbc_panelInsActDep.insets = new Insets(0, 0, 5, 5);
		gbc_panelInsActDep.fill = GridBagConstraints.BOTH;
		gbc_panelInsActDep.gridx = 1;
		gbc_panelInsActDep.gridy = 1;
		getContentPane().add(panelInsActDep, gbc_panelInsActDep);
		GridBagLayout gbl_panelInsActDep = new GridBagLayout();
		gbl_panelInsActDep.columnWidths = new int[]{0, 0, 0};
		gbl_panelInsActDep.rowHeights = new int[]{42, 34, 0};
		gbl_panelInsActDep.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelInsActDep.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelInsActDep.setLayout(gbl_panelInsActDep);
		
		lblIns = new JLabel("Institucion:");
		GridBagConstraints gbc_lblIns = new GridBagConstraints();
		gbc_lblIns.anchor = GridBagConstraints.WEST;
		gbc_lblIns.insets = new Insets(0, 0, 5, 5);
		gbc_lblIns.gridx = 0;
		gbc_lblIns.gridy = 0;
		panelInsActDep.add(lblIns, gbc_lblIns);
		
		comboBoxIns = new JComboBox<>();
		GridBagConstraints gbc_comboBoxIns = new GridBagConstraints();
		gbc_comboBoxIns.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxIns.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxIns.gridx = 1;
		gbc_comboBoxIns.gridy = 0;
		panelInsActDep.add(comboBoxIns, gbc_comboBoxIns);
		comboBoxIns.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if (comboBoxIns.getSelectedIndex() != 0) {
        			cargarActividadesDeportivas();
        		}
        	}
        });
		
		lblActDep = new JLabel("Actividad Deportiva:");
		GridBagConstraints gbc_lblActDep = new GridBagConstraints();
		gbc_lblActDep.anchor = GridBagConstraints.WEST;
		gbc_lblActDep.insets = new Insets(0, 0, 0, 5);
		gbc_lblActDep.gridx = 0;
		gbc_lblActDep.gridy = 1;
		panelInsActDep.add(lblActDep, gbc_lblActDep);
		
		comboBoxActDep = new JComboBox<>();
		comboBoxActDep.setEnabled(false);
		GridBagConstraints gbc_comboBoxActDep = new GridBagConstraints();
		gbc_comboBoxActDep.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxActDep.gridx = 1;
		gbc_comboBoxActDep.gridy = 1;
		panelInsActDep.add(comboBoxActDep, gbc_comboBoxActDep);
		comboBoxActDep.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if (comboBoxActDep.getSelectedIndex() != 0) {
        			cargarDatosActDep();
        			cargarClases();
        			cargarCuponeras();
        		}
        	}
        });
		
		panelDatosAD = new JPanel();
		panelDatosAD.setBorder(new TitledBorder(null, "Datos actividad deportiva", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelDatosAD = new GridBagConstraints();
		gbc_panelDatosAD.insets = new Insets(0, 0, 5, 5);
		gbc_panelDatosAD.fill = GridBagConstraints.BOTH;
		gbc_panelDatosAD.gridx = 1;
		gbc_panelDatosAD.gridy = 2;
		getContentPane().add(panelDatosAD, gbc_panelDatosAD);
		GridBagLayout gbl_panelDatosAD = new GridBagLayout();
		gbl_panelDatosAD.columnWidths = new int[]{0, 61, 257, 0, 0};
		gbl_panelDatosAD.rowHeights = new int[]{54, 98, 38, 31, 0};
		gbl_panelDatosAD.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDatosAD.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panelDatosAD.setLayout(gbl_panelDatosAD);
		
		lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 0;
		panelDatosAD.add(lblNombre, gbc_lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 0;
		panelDatosAD.add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblDesc = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblDesc = new GridBagConstraints();
		gbc_lblDesc.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblDesc.gridx = 1;
		gbc_lblDesc.gridy = 1;
		panelDatosAD.add(lblDesc, gbc_lblDesc);
		
		textFieldDesc = new JTextField();
		textFieldDesc.setEditable(false);
		GridBagConstraints gbc_textFieldDesc = new GridBagConstraints();
		gbc_textFieldDesc.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDesc.fill = GridBagConstraints.BOTH;
		gbc_textFieldDesc.gridx = 2;
		gbc_textFieldDesc.gridy = 1;
		panelDatosAD.add(textFieldDesc, gbc_textFieldDesc);
		textFieldDesc.setColumns(10);
		
		lblDuracion = new JLabel("Duracion:");
		GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
		gbc_lblDuracion.anchor = GridBagConstraints.WEST;
		gbc_lblDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracion.gridx = 1;
		gbc_lblDuracion.gridy = 2;
		panelDatosAD.add(lblDuracion, gbc_lblDuracion);
		
		panelDurCost = new JPanel();
		GridBagConstraints gbc_panelDurCost = new GridBagConstraints();
		gbc_panelDurCost.insets = new Insets(0, 0, 5, 5);
		gbc_panelDurCost.fill = GridBagConstraints.BOTH;
		gbc_panelDurCost.gridx = 2;
		gbc_panelDurCost.gridy = 2;
		panelDatosAD.add(panelDurCost, gbc_panelDurCost);
		GridBagLayout gbl_panelDurCost = new GridBagLayout();
		gbl_panelDurCost.columnWidths = new int[]{47, 31, 0, 0, 43, 72, 0};
		gbl_panelDurCost.rowHeights = new int[]{31, 0};
		gbl_panelDurCost.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDurCost.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelDurCost.setLayout(gbl_panelDurCost);
		
		textFieldDur = new JTextField();
		textFieldDur.setEditable(false);
		GridBagConstraints gbc_textFieldDur = new GridBagConstraints();
		gbc_textFieldDur.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldDur.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDur.gridx = 0;
		gbc_textFieldDur.gridy = 0;
		panelDurCost.add(textFieldDur, gbc_textFieldDur);
		textFieldDur.setColumns(10);
		
		lblMins = new JLabel("Mins");
		GridBagConstraints gbc_lblMins = new GridBagConstraints();
		gbc_lblMins.anchor = GridBagConstraints.WEST;
		gbc_lblMins.insets = new Insets(0, 0, 0, 5);
		gbc_lblMins.gridx = 1;
		gbc_lblMins.gridy = 0;
		panelDurCost.add(lblMins, gbc_lblMins);
		
		lblCosto = new JLabel("Costo:");
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.insets = new Insets(0, 0, 0, 5);
		gbc_lblCosto.anchor = GridBagConstraints.EAST;
		gbc_lblCosto.gridx = 3;
		gbc_lblCosto.gridy = 0;
		panelDurCost.add(lblCosto, gbc_lblCosto);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setEditable(false);
		GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
		gbc_textFieldCosto.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCosto.gridx = 4;
		gbc_textFieldCosto.gridy = 0;
		panelDurCost.add(textFieldCosto, gbc_textFieldCosto);
		textFieldCosto.setColumns(10);
		
		lblPesos = new JLabel("Pesos");
		GridBagConstraints gbc_lblPesos = new GridBagConstraints();
		gbc_lblPesos.anchor = GridBagConstraints.WEST;
		gbc_lblPesos.gridx = 5;
		gbc_lblPesos.gridy = 0;
		panelDurCost.add(lblPesos, gbc_lblPesos);
		
		lblFecha = new JLabel("Fecha Registro:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.WEST;
		gbc_lblFecha.insets = new Insets(0, 0, 0, 5);
		gbc_lblFecha.gridx = 1;
		gbc_lblFecha.gridy = 3;
		panelDatosAD.add(lblFecha, gbc_lblFecha);
		
		panelFecha = new JPanel();
		GridBagConstraints gbc_panelFecha = new GridBagConstraints();
		gbc_panelFecha.insets = new Insets(0, 0, 0, 5);
		gbc_panelFecha.fill = GridBagConstraints.BOTH;
		gbc_panelFecha.gridx = 2;
		gbc_panelFecha.gridy = 3;
		panelDatosAD.add(panelFecha, gbc_panelFecha);
		GridBagLayout gbl_panelFecha = new GridBagLayout();
		gbl_panelFecha.columnWidths = new int[]{46, 123, 65, 38, 0};
		gbl_panelFecha.rowHeights = new int[]{36, 0};
		gbl_panelFecha.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelFecha.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelFecha.setLayout(gbl_panelFecha);
		
		textFieldDia = new JTextField();
		textFieldDia.setEditable(false);
		GridBagConstraints gbc_textFieldDia = new GridBagConstraints();
		gbc_textFieldDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDia.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldDia.gridx = 0;
		gbc_textFieldDia.gridy = 0;
		panelFecha.add(textFieldDia, gbc_textFieldDia);
		textFieldDia.setColumns(10);
		
		textFieldMes = new JTextField();
		textFieldMes.setEditable(false);
		GridBagConstraints gbc_textFieldMes = new GridBagConstraints();
		gbc_textFieldMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMes.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldMes.gridx = 1;
		gbc_textFieldMes.gridy = 0;
		panelFecha.add(textFieldMes, gbc_textFieldMes);
		textFieldMes.setColumns(10);
		
		textFieldAnio = new JTextField();
		textFieldAnio.setEditable(false);
		GridBagConstraints gbc_textFieldAnio = new GridBagConstraints();
		gbc_textFieldAnio.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAnio.gridx = 2;
		gbc_textFieldAnio.gridy = 0;
		panelFecha.add(textFieldAnio, gbc_textFieldAnio);
		textFieldAnio.setColumns(10);
		
		panelClasesCuponeras = new JPanel();
		GridBagConstraints gbc_panelClasesCuponeras = new GridBagConstraints();
		gbc_panelClasesCuponeras.insets = new Insets(0, 0, 5, 5);
		gbc_panelClasesCuponeras.fill = GridBagConstraints.BOTH;
		gbc_panelClasesCuponeras.gridx = 1;
		gbc_panelClasesCuponeras.gridy = 3;
		getContentPane().add(panelClasesCuponeras, gbc_panelClasesCuponeras);
		GridBagLayout gbl_panelClasesCuponeras = new GridBagLayout();
		gbl_panelClasesCuponeras.columnWidths = new int[]{77, 201, 22, 78, 0};
		gbl_panelClasesCuponeras.rowHeights = new int[]{96, 89, 0};
		gbl_panelClasesCuponeras.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelClasesCuponeras.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelClasesCuponeras.setLayout(gbl_panelClasesCuponeras);
		
		lblClases = new JLabel("Clases:");
		GridBagConstraints gbc_lblClases = new GridBagConstraints();
		gbc_lblClases.anchor = GridBagConstraints.WEST;
		gbc_lblClases.insets = new Insets(0, 0, 5, 5);
		gbc_lblClases.gridx = 0;
		gbc_lblClases.gridy = 0;
		panelClasesCuponeras.add(lblClases, gbc_lblClases);
		
		comboBoxClases = new JComboBox<>();
		comboBoxClases.setEnabled(false);
		GridBagConstraints gbc_comboBoxClases = new GridBagConstraints();
		gbc_comboBoxClases.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxClases.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxClases.gridx = 1;
		gbc_comboBoxClases.gridy = 0;
		panelClasesCuponeras.add(comboBoxClases, gbc_comboBoxClases);
		comboBoxClases.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if (comboBoxClases.getSelectedIndex() != 0) {
        			verInfoClase();
        		}
        	}
        });
		
		textFieldClase = new JTextField();
		textFieldClase.setEditable(false);
		GridBagConstraints gbc_textFieldClase = new GridBagConstraints();
		gbc_textFieldClase.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldClase.fill = GridBagConstraints.BOTH;
		gbc_textFieldClase.gridx = 3;
		gbc_textFieldClase.gridy = 0;
		panelClasesCuponeras.add(textFieldClase, gbc_textFieldClase);
		textFieldClase.setColumns(10);
		
		lblCuponeras = new JLabel("Cuponeras:");
		GridBagConstraints gbc_lblCuponeras = new GridBagConstraints();
		gbc_lblCuponeras.anchor = GridBagConstraints.WEST;
		gbc_lblCuponeras.insets = new Insets(0, 0, 0, 5);
		gbc_lblCuponeras.gridx = 0;
		gbc_lblCuponeras.gridy = 1;
		panelClasesCuponeras.add(lblCuponeras, gbc_lblCuponeras);
		
		comboBoxCuponeras = new JComboBox<>();
		comboBoxCuponeras.setEnabled(false);
		GridBagConstraints gbc_comboBoxCuponeras = new GridBagConstraints();
		gbc_comboBoxCuponeras.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCuponeras.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxCuponeras.gridx = 1;
		gbc_comboBoxCuponeras.gridy = 1;
		panelClasesCuponeras.add(comboBoxCuponeras, gbc_comboBoxCuponeras);
		comboBoxCuponeras.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if (comboBoxCuponeras.getSelectedIndex() != 0) {
        			verInfoCuponera();
        		}
        	}
        });
		
		textFieldCuponeras = new JTextField();
		textFieldCuponeras.setEditable(false);
		GridBagConstraints gbc_textFieldCuponeras = new GridBagConstraints();
		gbc_textFieldCuponeras.fill = GridBagConstraints.BOTH;
		gbc_textFieldCuponeras.gridx = 3;
		gbc_textFieldCuponeras.gridy = 1;
		panelClasesCuponeras.add(textFieldCuponeras, gbc_textFieldCuponeras);
		textFieldCuponeras.setColumns(10);
		
		lblEnd = new JLabel("Seleccione una clase o cuponera para obtener su informacion");
		GridBagConstraints gbc_lblEnd = new GridBagConstraints();
		gbc_lblEnd.insets = new Insets(0, 0, 0, 5);
		gbc_lblEnd.gridx = 1;
		gbc_lblEnd.gridy = 4;
		getContentPane().add(lblEnd, gbc_lblEnd);

	}
	
	public void cargarInstituciones() {
        DefaultComboBoxModel<String> modelInstituciones;
        modelInstituciones = new DefaultComboBoxModel<>();
        modelInstituciones.addElement("---Seleccione una institucion---");
        for (String ins: IADC.obtenerInstituciones()) {
        	modelInstituciones.addElement(ins);
        }
        comboBoxIns.setModel(modelInstituciones);
    }
	
	public void cargarActividadesDeportivas() {
		DefaultComboBoxModel<String> modelActDep;
		modelActDep = new DefaultComboBoxModel<>();
		modelActDep.addElement("---Seleccione una actividad deportiva---");
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion ins = hi.findInstitucion(comboBoxIns.getName());
//		for (String actDep: ins.obtenerActDep()) {
//			modelActDep.addElement(actDep);
//		}
//		comboBoxActDep.setModel(modelActDep);
	}
	
	public void cargarDatosActDep() {
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion ins = hi.findInstitucion(comboBoxIns.getName());
		ActividadDeportiva actDep = ins.getActDep(comboBoxActDep.getName());
		textFieldNombre.setText(actDep.getNombre());
		textFieldDesc.setText(actDep.getDescripcion());
		textFieldDur.setText(Integer.toString(actDep.getDuracionMinutos()));
		textFieldCosto.setText(Float.toString(actDep.getCosto()));
		DtFecha fecha = actDep.getFechaRegistro();
		textFieldDia.setText(Integer.toString(fecha.getDia()));
		textFieldMes.setText(Integer.toString(fecha.getMes()));
		textFieldAnio.setText(Integer.toString(fecha.getAnio()));
	}
	
	public void cargarClases() {
		DefaultComboBoxModel<String> modelClases;
		modelClases = new DefaultComboBoxModel<>();
		modelClases.addElement("---Seleccione una clase---");
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion ins = hi.findInstitucion(comboBoxIns.getName());
//		for (String clase: ins.obtenerNombreClasesActDep(comboBoxActDep.getName())) {
//			modelClases.addElement(clase);
//		}
//		comboBoxClases.setModel(modelClases);
	}
	
	public void cargarCuponeras() {
		DefaultComboBoxModel<String> modelCuponeras;
		modelCuponeras = new DefaultComboBoxModel<>();
		modelCuponeras.addElement("---Seleccione una cuponera---");
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion ins = hi.findInstitucion(comboBoxIns.getName());
//		for (String clase: ins.obtenerNombreClasesActDep(comboBoxActDep.getName())) {
//			modelCuponeras.addElement(clase);
//		}
		comboBoxCuponeras.setModel(modelCuponeras);
	}
	
	public void verInfoClase() {
		HandlerInstitucion hi = HandlerInstitucion.getInstance();
		Institucion ins = hi.findInstitucion(comboBoxIns.getName());
//		DtClaseExt datosClase = ins.obtenerDtClase(comboBoxActDep.getName(),comboBoxClases.getName());
//		textFieldClase.setText(datosClase.toString());
	}
	
	public void verInfoCuponera() {
		HandlerCuponera hc = HandlerCuponera.getInstance();
		DtCuponera datosCuponera = hc.getDtCuponera(comboBoxCuponeras.getName());
		textFieldCuponeras.setText(datosCuponera.toString());
	}
	
	public void clear() {
		comboBoxIns.setSelectedIndex(0);
		comboBoxActDep.setEnabled(false);
		textFieldNombre.setText("");
		textFieldDesc.setText("");
		textFieldDur.setText("");
		textFieldCosto.setText("");
		textFieldDia.setText("");
		textFieldMes.setText("");
		textFieldAnio.setText("");
		comboBoxClases.setEnabled(false);
		comboBoxCuponeras.setEnabled(false);
	}
}