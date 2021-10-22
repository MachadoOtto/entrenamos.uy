package presentacion;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTree;
import javax.swing.JTextArea;

import java.util.Set;

import logica.IActividadDeportivaController;
import excepciones.ActividadDeportivaException;
import excepciones.InstitucionException;
import datatypes.TEstado;
import datatypes.DtActividadDeportivaExt;
import datatypes.DtFecha;

@SuppressWarnings("serial")
public class ConsultaActividadDeportiva extends JInternalFrame {
	
	//Controller
	private IActividadDeportivaController IADC;
	private JLabel lblCabecera;
	private JPanel panelInsActDep;
	private JLabel lblIns;
	private JLabel lblActDep;
	private JComboBox<String> comboBoxIns;
	private JComboBox<String> comboBoxActDep;
	private JPanel panelDatosAD;
	private JLabel lblNombre;
	private JLabel lblDesc;
	private JTextField textFieldNombre;
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
	private JLabel lblEnd, estadoLbl;
	private JScrollPane scrollPane;
	private JTree tree;
	private JScrollPane scrollPaneDesc;
	private JTextArea textFieldDesc;
	private JLabel lblNewLabel;
	private ConsultaDictadoClase refClase;
	private ConsultaCuponeras refCup;
	private JLabel estado;
	
	public ConsultaActividadDeportiva(IActividadDeportivaController IADC) {
		
		this.IADC = IADC;
		
		//Configuracion del frame
		setTitle("Consulta Actividad Deportiva");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100,  100,  435,  681);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20,  421,  0,  0};
		gridBagLayout.rowHeights = new int[]{47,  89,  255,  0,  193,  22,  0};
		gridBagLayout.columnWeights = new double[]{0.0,  1.0,  0.0,  Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0,  0.0,  0.0,  0.0,  1.0,  0.0,  Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		lblCabecera = new JLabel("Seleccione la actividad deportiva de la cual desea obtener la informacion:");
		GridBagConstraints gbc_lblCabecera = new GridBagConstraints();
		gbc_lblCabecera.anchor = GridBagConstraints.WEST;
		gbc_lblCabecera.insets = new Insets(0,  0,  5,  5);
		gbc_lblCabecera.gridx = 1;
		gbc_lblCabecera.gridy = 0;
		getContentPane().add(lblCabecera,  gbc_lblCabecera);
		
		panelInsActDep = new JPanel();
		GridBagConstraints gbc_panelInsActDep = new GridBagConstraints();
		gbc_panelInsActDep.insets = new Insets(0,  0,  5,  5);
		gbc_panelInsActDep.fill = GridBagConstraints.BOTH;
		gbc_panelInsActDep.gridx = 1;
		gbc_panelInsActDep.gridy = 1;
		getContentPane().add(panelInsActDep,  gbc_panelInsActDep);
		GridBagLayout gbl_panelInsActDep = new GridBagLayout();
		gbl_panelInsActDep.columnWidths = new int[]{0,  0,  0};
		gbl_panelInsActDep.rowHeights = new int[]{42,  34,  0};
		gbl_panelInsActDep.columnWeights = new double[]{0.0,  1.0,  Double.MIN_VALUE};
		gbl_panelInsActDep.rowWeights = new double[]{0.0,  0.0,  Double.MIN_VALUE};
		panelInsActDep.setLayout(gbl_panelInsActDep);
		
		lblIns = new JLabel("Institucion:");
		GridBagConstraints gbc_lblIns = new GridBagConstraints();
		gbc_lblIns.anchor = GridBagConstraints.WEST;
		gbc_lblIns.insets = new Insets(0,  0,  5,  5);
		gbc_lblIns.gridx = 0;
		gbc_lblIns.gridy = 0;
		panelInsActDep.add(lblIns,  gbc_lblIns);
		
		comboBoxIns = new JComboBox<>();
		comboBoxIns.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        		String z = null, t=(String) comboBoxIns.getSelectedItem();
        		if (comboBoxActDep.isEnabled()) 
        			z=(String) comboBoxActDep.getSelectedItem();
        		cargarInstitucion();
        		comboBoxIns.setSelectedItem(t);
        		if (comboBoxActDep.isEnabled()) 
        			comboBoxActDep.setSelectedItem(z);
        	}
        });
		comboBoxIns.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		try {
	        		int selectIndex = comboBoxIns.getSelectedIndex();
	        		comboBoxActDep.removeAllItems();
	    			DefaultComboBoxModel<String> modelActividad = new DefaultComboBoxModel<>();
	    			modelActividad.addElement("-");     		
	    			if (selectIndex > 0) {
	        			Set<String> actividades = IADC.obtenerActividades((String) comboBoxIns.getItemAt(selectIndex));
	                    for (String x: actividades) {
	                    	modelActividad.addElement(x);
	                    }
	                    comboBoxActDep.setEnabled(true);
	        		} else {
	        			comboBoxActDep.setEnabled(false);
	        		}
	    			comboBoxActDep.setModel(modelActividad);
	    			tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("z") {
	    				{
	    				DefaultMutableTreeNode nodoAct;
	    				nodoAct = new DefaultMutableTreeNode("No hay actividad deportiva seleccionada.");
	    				add(nodoAct);
	    				}}));
        		} catch (InstitucionException ignore) { }
        	}
        });
		cargarInstitucion();
		GridBagConstraints gbc_comboBoxIns = new GridBagConstraints();
		gbc_comboBoxIns.insets = new Insets(0,  0,  5,  0);
		gbc_comboBoxIns.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxIns.gridx = 1;
		gbc_comboBoxIns.gridy = 0;
		panelInsActDep.add(comboBoxIns,  gbc_comboBoxIns);
		
		lblActDep = new JLabel("Actividad Deportiva:");
		GridBagConstraints gbc_lblActDep = new GridBagConstraints();
		gbc_lblActDep.anchor = GridBagConstraints.WEST;
		gbc_lblActDep.insets = new Insets(0,  0,  0,  5);
		gbc_lblActDep.gridx = 0;
		gbc_lblActDep.gridy = 1;
		panelInsActDep.add(lblActDep,  gbc_lblActDep);

		comboBoxActDep = new JComboBox<>();
		comboBoxActDep.setEnabled(false);
		DefaultComboBoxModel<String> modelActividad = new DefaultComboBoxModel<>();
		modelActividad.addElement("-");    
		comboBoxActDep.setModel(modelActividad);
		GridBagConstraints gbc_comboBoxActDep = new GridBagConstraints();
		gbc_comboBoxActDep.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxActDep.gridx = 1;
		gbc_comboBoxActDep.gridy = 1;
		panelInsActDep.add(comboBoxActDep,  gbc_comboBoxActDep);
		comboBoxActDep.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if (comboBoxIns.getSelectedIndex() >0 && comboBoxActDep.getSelectedIndex() > 0) {
        			loadData();
        		} else {
        			textFieldNombre.setText("");
        			textFieldDesc.setText("");
        			textFieldDur.setText("");
        			textFieldCosto.setText("");
        			textFieldDia.setText("");
        			textFieldMes.setText("");
        		}
        	}
        });
		
		
		panelDatosAD = new JPanel();
		panelDatosAD.setBorder(new TitledBorder(null,  "Datos actividad deportiva",  TitledBorder.LEADING,  TitledBorder.TOP,  null,  null));
		GridBagConstraints gbc_panelDatosAD = new GridBagConstraints();
		gbc_panelDatosAD.insets = new Insets(0,  0,  5,  5);
		gbc_panelDatosAD.fill = GridBagConstraints.BOTH;
		gbc_panelDatosAD.gridx = 1;
		gbc_panelDatosAD.gridy = 2;
		getContentPane().add(panelDatosAD,  gbc_panelDatosAD);
		GridBagLayout gbl_panelDatosAD = new GridBagLayout();
		gbl_panelDatosAD.columnWidths = new int[]{0,  61,  257,  0,  0};
		gbl_panelDatosAD.rowHeights = new int[]{54,  0,  98,  38,  31,  0};
		gbl_panelDatosAD.columnWeights = new double[]{0.0,  0.0,  1.0,  0.0,  Double.MIN_VALUE};
		gbl_panelDatosAD.rowWeights = new double[]{0.0,  0.0,  1.0,  1.0,  1.0,  Double.MIN_VALUE};
		panelDatosAD.setLayout(gbl_panelDatosAD);
		
		lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0,  0,  5,  5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		panelDatosAD.add(lblNombre,  gbc_lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBackground(Color.WHITE);
		textFieldNombre.setEditable(false);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 3;
		gbc_textFieldNombre.insets = new Insets(0,  0,  5,  0);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 0;
		panelDatosAD.add(textFieldNombre,  gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
				estadoLbl = new JLabel("Estado:");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.insets = new Insets(0,  0,  5,  5);
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 1;
				panelDatosAD.add(estadoLbl,  gbc_lblNewLabel_1);
		
		estado = new JLabel("-");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0,  0,  5,  5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		panelDatosAD.add(estado,  gbc_label);
		
		scrollPaneDesc = new JScrollPane();
		GridBagConstraints gbc_scrollPaneDesc = new GridBagConstraints();
		gbc_scrollPaneDesc.gridheight = 1;
		gbc_scrollPaneDesc.gridwidth = 3;
		gbc_scrollPaneDesc.insets = new Insets(0,  0,  5,  0);
		gbc_scrollPaneDesc.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneDesc.gridx = 1;
		gbc_scrollPaneDesc.gridy = 2;
		panelDatosAD.add(scrollPaneDesc,  gbc_scrollPaneDesc);
		
		textFieldDesc = new JTextArea();
		scrollPaneDesc.setViewportView(textFieldDesc);
		textFieldDesc.setEditable(false);
		textFieldDesc.setLineWrap(true);
		textFieldDesc.setWrapStyleWord(true);
		GridBagConstraints gbc_textFieldDesc = new GridBagConstraints();
		gbc_textFieldDesc.gridwidth = 3;
		gbc_textFieldDesc.insets = new Insets(0,  0,  5,  5);
		gbc_textFieldDesc.fill = GridBagConstraints.BOTH;
		gbc_textFieldDesc.gridx = 1;
		gbc_textFieldDesc.gridy = 1;
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		
		lblDesc = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblDesc = new GridBagConstraints();
		gbc_lblDesc.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblDesc.insets = new Insets(0,  0,  5,  5);
		gbc_lblDesc.gridx = 0;
		gbc_lblDesc.gridy = 2;
		panelDatosAD.add(lblDesc,  gbc_lblDesc);
		textFieldDesc.setBorder(BorderFactory.createCompoundBorder(border,  
			      BorderFactory.createEmptyBorder(10,  10,  10,  10)));
		//panelDatosAD.add(textFieldDesc,  gbc_textFieldDesc);
		
		lblDuracion = new JLabel("Duracion:");
		GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
		gbc_lblDuracion.anchor = GridBagConstraints.WEST;
		gbc_lblDuracion.insets = new Insets(0,  0,  5,  5);
		gbc_lblDuracion.gridx = 0;
		gbc_lblDuracion.gridy = 3;
		panelDatosAD.add(lblDuracion,  gbc_lblDuracion);
		
		panelDurCost = new JPanel();
		GridBagConstraints gbc_panelDurCost = new GridBagConstraints();
		gbc_panelDurCost.gridwidth = 3;
		gbc_panelDurCost.insets = new Insets(0,  0,  5,  0);
		gbc_panelDurCost.fill = GridBagConstraints.BOTH;
		gbc_panelDurCost.gridx = 1;
		gbc_panelDurCost.gridy = 3;
		panelDatosAD.add(panelDurCost,  gbc_panelDurCost);
		GridBagLayout gbl_panelDurCost = new GridBagLayout();
		gbl_panelDurCost.columnWidths = new int[]{47,  31,  0,  0,  43,  72,  0};
		gbl_panelDurCost.rowHeights = new int[]{31,  0};
		gbl_panelDurCost.columnWeights = new double[]{0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  Double.MIN_VALUE};
		gbl_panelDurCost.rowWeights = new double[]{0.0,  Double.MIN_VALUE};
		panelDurCost.setLayout(gbl_panelDurCost);
		
		textFieldDur = new JTextField();
		textFieldDur.setBackground(Color.WHITE);
		textFieldDur.setEditable(false);
		GridBagConstraints gbc_textFieldDur = new GridBagConstraints();
		gbc_textFieldDur.insets = new Insets(0,  0,  0,  5);
		gbc_textFieldDur.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDur.gridx = 0;
		gbc_textFieldDur.gridy = 0;
		panelDurCost.add(textFieldDur,  gbc_textFieldDur);
		textFieldDur.setColumns(10);
		
		lblMins = new JLabel("Mins");
		GridBagConstraints gbc_lblMins = new GridBagConstraints();
		gbc_lblMins.anchor = GridBagConstraints.WEST;
		gbc_lblMins.insets = new Insets(0,  0,  0,  5);
		gbc_lblMins.gridx = 1;
		gbc_lblMins.gridy = 0;
		panelDurCost.add(lblMins,  gbc_lblMins);
		
		lblCosto = new JLabel("Costo:");
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.insets = new Insets(0,  0,  0,  5);
		gbc_lblCosto.anchor = GridBagConstraints.EAST;
		gbc_lblCosto.gridx = 3;
		gbc_lblCosto.gridy = 0;
		panelDurCost.add(lblCosto,  gbc_lblCosto);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setBackground(Color.WHITE);
		textFieldCosto.setEditable(false);
		GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
		gbc_textFieldCosto.insets = new Insets(0,  0,  0,  5);
		gbc_textFieldCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCosto.gridx = 4;
		gbc_textFieldCosto.gridy = 0;
		panelDurCost.add(textFieldCosto,  gbc_textFieldCosto);
		textFieldCosto.setColumns(10);
		
		lblPesos = new JLabel("Pesos");
		GridBagConstraints gbc_lblPesos = new GridBagConstraints();
		gbc_lblPesos.anchor = GridBagConstraints.WEST;
		gbc_lblPesos.gridx = 5;
		gbc_lblPesos.gridy = 0;
		panelDurCost.add(lblPesos,  gbc_lblPesos);
		
		lblFecha = new JLabel("Fecha Registro:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.WEST;
		gbc_lblFecha.insets = new Insets(0,  0,  0,  5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 4;
		panelDatosAD.add(lblFecha,  gbc_lblFecha);
		
		panelFecha = new JPanel();
		GridBagConstraints gbc_panelFecha = new GridBagConstraints();
		gbc_panelFecha.gridwidth = 3;
		gbc_panelFecha.fill = GridBagConstraints.BOTH;
		gbc_panelFecha.gridx = 1;
		gbc_panelFecha.gridy = 4;
		panelDatosAD.add(panelFecha,  gbc_panelFecha);
		GridBagLayout gbl_panelFecha = new GridBagLayout();
		gbl_panelFecha.columnWidths = new int[]{46,  123,  65,  38,  0};
		gbl_panelFecha.rowHeights = new int[]{36,  0};
		gbl_panelFecha.columnWeights = new double[]{0.0,  0.0,  0.0,  0.0,  Double.MIN_VALUE};
		gbl_panelFecha.rowWeights = new double[]{0.0,  Double.MIN_VALUE};
		panelFecha.setLayout(gbl_panelFecha);
		
		textFieldDia = new JTextField();
		textFieldDia.setBackground(Color.WHITE);
		textFieldDia.setEditable(false);
		GridBagConstraints gbc_textFieldDia = new GridBagConstraints();
		gbc_textFieldDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDia.insets = new Insets(0,  0,  0,  5);
		gbc_textFieldDia.gridx = 0;
		gbc_textFieldDia.gridy = 0;
		panelFecha.add(textFieldDia,  gbc_textFieldDia);
		textFieldDia.setColumns(10);
		
		textFieldMes = new JTextField();
		textFieldMes.setBackground(Color.WHITE);
		textFieldMes.setEditable(false);
		GridBagConstraints gbc_textFieldMes = new GridBagConstraints();
		gbc_textFieldMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMes.insets = new Insets(0,  0,  0,  5);
		gbc_textFieldMes.gridx = 1;
		gbc_textFieldMes.gridy = 0;
		panelFecha.add(textFieldMes,  gbc_textFieldMes);
		textFieldMes.setColumns(10);
		
		textFieldAnio = new JTextField();
		textFieldAnio.setBackground(Color.WHITE);
		textFieldAnio.setEditable(false);
		GridBagConstraints gbc_textFieldAnio = new GridBagConstraints();
		gbc_textFieldAnio.insets = new Insets(0,  0,  0,  5);
		gbc_textFieldAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAnio.gridx = 2;
		gbc_textFieldAnio.gridy = 0;
		panelFecha.add(textFieldAnio,  gbc_textFieldAnio);
		textFieldAnio.setColumns(10);
		
		lblNewLabel = new JLabel("Clases,  cuponeras y categorias asociadas:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0,  0,  5,  5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		getContentPane().add(lblNewLabel,  gbc_lblNewLabel);
		
		panelClasesCuponeras = new JPanel();
		GridBagConstraints gbc_panelClasesCuponeras = new GridBagConstraints();
		gbc_panelClasesCuponeras.insets = new Insets(0,  0,  5,  5);
		gbc_panelClasesCuponeras.fill = GridBagConstraints.BOTH;
		gbc_panelClasesCuponeras.gridx = 1;
		gbc_panelClasesCuponeras.gridy = 4;
		getContentPane().add(panelClasesCuponeras,  gbc_panelClasesCuponeras);
		GridBagLayout gbl_panelClasesCuponeras = new GridBagLayout();
		gbl_panelClasesCuponeras.columnWidths = new int[]{77,  201,  22,  78,  0};
		gbl_panelClasesCuponeras.rowHeights = new int[]{96,  89,  0};
		gbl_panelClasesCuponeras.columnWeights = new double[]{1.0,  0.0,  0.0,  1.0,  Double.MIN_VALUE};
		gbl_panelClasesCuponeras.rowWeights = new double[]{1.0,  0.0,  Double.MIN_VALUE};
		panelClasesCuponeras.setLayout(gbl_panelClasesCuponeras);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0,  0,  5,  5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelClasesCuponeras.add(scrollPane,  gbc_scrollPane);
		
		tree = new JTree();
		scrollPane.setViewportView(tree);
		tree.setRootVisible(false);
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("z") {
			{
			DefaultMutableTreeNode nodoAct;
			nodoAct = new DefaultMutableTreeNode("No hay actividad deportiva seleccionada.");
			add(nodoAct);
			}
		}));
		Border border2 = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		tree.setBorder(BorderFactory.createCompoundBorder(border2,  
			      BorderFactory.createEmptyBorder(10,  10,  10,  10)));
		GridBagConstraints gbc_tree = new GridBagConstraints();
		gbc_tree.gridheight = 2;
		gbc_tree.gridwidth = 4;
		gbc_tree.insets = new Insets(0,  0,  5,  5);
		gbc_tree.fill = GridBagConstraints.BOTH;
		gbc_tree.gridx = 0;
		gbc_tree.gridy = 0;
		//panelClasesCuponeras.add(tree,  gbc_tree);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		TreeSelectionListener lst = new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				 DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				 if (node == null) 
					 return;
				 DefaultMutableTreeNode dad = (DefaultMutableTreeNode) node.getParent();
				 if (dad != null && dad.getUserObject().equals("Cuponeras")) {
					 //Ref CU. Consulta Cuponeras
					 refCup.refEntry((String) node.getUserObject());
				 }
				 if (dad != null && dad.getUserObject().equals("Clases")) {
					 //Ref CU. Consulta Clases
					 refClase.refEntry((String)comboBoxActDep.getSelectedItem(),  (String) node.getUserObject());
				 }
			}
		};
		tree.addTreeSelectionListener(lst);
		lblEnd = new JLabel("Seleccione una clase o cuponera para obtener su informacion");
		GridBagConstraints gbc_lblEnd = new GridBagConstraints();
		gbc_lblEnd.insets = new Insets(0,  0,  0,  5);
		gbc_lblEnd.gridx = 1;
		gbc_lblEnd.gridy = 5;
		getContentPane().add(lblEnd,  gbc_lblEnd);

	}
	
	private void loadData() {
		try {
			DtActividadDeportivaExt actDep = IADC.getActDepExt((String)comboBoxIns.getSelectedItem(), (String)comboBoxActDep.getSelectedItem());
			textFieldNombre.setText(actDep.getNombre());
			textFieldDesc.setText(actDep.getDescripcion());
			textFieldDur.setText(Integer.toString(actDep.getDuracionMinutos()));
			textFieldCosto.setText(Float.toString(actDep.getCosto()));
			DtFecha fecha = actDep.getFechaRegistro();
			textFieldDia.setText(Integer.toString(fecha.getDia()));
			textFieldMes.setText(Integer.toString(fecha.getMes()));
			textFieldAnio.setText(Integer.toString(fecha.getAnio()));
			estado.setText(actDep.getEstado().toString().toUpperCase());
			if (actDep.getEstado()==TEstado.ingresada)
				estado.setForeground(Color.ORANGE);
			else if (actDep.getEstado()==TEstado.aceptada)
				estado.setForeground(Color.green);
			else
				estado.setForeground(Color.red);
			
			tree.setModel(new DefaultTreeModel(
					new DefaultMutableTreeNode("lol") {
						{
							DefaultMutableTreeNode nodoCl = new DefaultMutableTreeNode("Clases");
							DefaultMutableTreeNode nodoCup = new DefaultMutableTreeNode("Cuponeras");
							DefaultMutableTreeNode nodoCats = new DefaultMutableTreeNode("Categorias");
							for (String x: actDep.getClases()) {
								nodoCl.add(new DefaultMutableTreeNode(x));
							}
							for (String x: actDep.getCuponeras()) {
								nodoCup.add(new DefaultMutableTreeNode(x));
							}
							for (String x: actDep.getCategorias()) {
								nodoCats.add(new DefaultMutableTreeNode(x));
							}
							add(nodoCl);
							add(nodoCup);
							add(nodoCats);
						}
					}
				));
		} catch (InstitucionException e) {
			JOptionPane.showMessageDialog(this,  e.getMessage(),  getTitle(),  JOptionPane.ERROR_MESSAGE);
		} catch (ActividadDeportivaException e) {
			JOptionPane.showMessageDialog(this,  e.getMessage(),  getTitle(),  JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cargarInstitucion() {
        DefaultComboBoxModel<String> modelInstituciones;
        modelInstituciones = new DefaultComboBoxModel<>();
        //modelInstituciones.addElement("---Seleccione una institucion---");
		modelInstituciones.addElement("-");
        for (String ins: IADC.obtenerInstituciones()) {
        	modelInstituciones.addElement(ins);
        }
        comboBoxIns.setModel(modelInstituciones);
    }
	
    public void setRef(ConsultaDictadoClase Refcdc, ConsultaCuponeras Refcc) {
    	refClase = Refcdc;
    	refCup = Refcc;
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
	}

	public void refEntry(String actDep) {
		
		try {
			if (actDep.contains("/")) {
				actDep = actDep.split("/")[0];
				actDep = actDep.trim();
			}
	        DefaultComboBoxModel<String> model;
	        String institf=null;
	        model = new DefaultComboBoxModel<>();
	        model.addElement("-");
	        for (String x: IADC.obtenerInstituciones()) {
	            model.addElement(x);
	            for (String y: IADC.obtenerActividades(x)) {
	            	if (y.equals(actDep)) {
	            		institf = x;
	            	}
	            }
	            if (institf != null)
	            	break;
	        }
	        comboBoxIns.setModel(model);
	        comboBoxIns.getModel().setSelectedItem(institf);
			Set<String> actividades = IADC.obtenerActividades(institf);
			DefaultComboBoxModel<String> modelActividad = new DefaultComboBoxModel<>();
			modelActividad.addElement("-");
	        for (String x: actividades) {
	        	modelActividad.addElement(x);
	        }
	        comboBoxActDep.setEnabled(true);
	        comboBoxActDep.setModel(modelActividad);
	        comboBoxActDep.getModel().setSelectedItem(actDep);
			loadData();
			if (this.isVisible()) 
				this.toFront();
			else {
				this.setVisible(true);
			}
		} catch (InstitucionException e) {
			JOptionPane.showMessageDialog(this,  e.getMessage(),  
					"Alta actividad deportiva",  JOptionPane.ERROR_MESSAGE);
		}
	}
}
