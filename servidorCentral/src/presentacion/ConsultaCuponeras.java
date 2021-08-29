package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import logica.ICuponeraController;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import datatypes.DtClasesCuponera;
import datatypes.DtCuponera;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;



@SuppressWarnings("serial")
public class ConsultaCuponeras extends JInternalFrame{
	/**
	 * @wbp.nonvisual location=318,69
	 */
	
	//Controller
	private ICuponeraController IDC;
	//Components
	private JComboBox<String> cbCuponera;
	private JTree treeCuponera;
	private ConsultaActividadDeportiva refAd;
	JScrollPane scrollPane;
	
	public ConsultaCuponeras(ICuponeraController IDC) {
		this.IDC = IDC;
		//Configuracion del JFRAME
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consulta de Cuponeras de actividades deportivas");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 0;
		getContentPane().add(verticalStrut, gbc_verticalStrut);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 0;
		gbc_horizontalStrut_1.gridy = 1;
		getContentPane().add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		JLabel lblCuponera = new JLabel("Cuponera:");
		GridBagConstraints gbc_lblCuponera = new GridBagConstraints();
		gbc_lblCuponera.anchor = GridBagConstraints.WEST;
		gbc_lblCuponera.insets = new Insets(0, 0, 5, 5);
		gbc_lblCuponera.gridx = 1;
		gbc_lblCuponera.gridy = 1;
		getContentPane().add(lblCuponera, gbc_lblCuponera);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut.gridx = 2;
		gbc_horizontalStrut.gridy = 1;
		getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		
		cbCuponera = new JComboBox<>();
		cbCuponera.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cbCuponera.getSelectedItem() == "-" ||cbCuponera.getSelectedItem() == null) {
					treeCuponera.setModel(new DefaultTreeModel(
							new DefaultMutableTreeNode("No hay cuponera seleccionada.")));
				}
				else {
					DtCuponera x = IDC.seleccionarCuponera((String) cbCuponera.getSelectedItem());
					treeCuponera.setModel(new DefaultTreeModel(
							new DefaultMutableTreeNode("Cuponera \""+x.getNombre()+"\"") {
								{
									DefaultMutableTreeNode nodoAct;
									add(new DefaultMutableTreeNode("Nombre: "+x.getNombre()));
									add(new DefaultMutableTreeNode("Descripcion: "+x.getDescripcion()));
									add(new DefaultMutableTreeNode("Valida a partir del: "+x.getFechaInicio().toFecha()));
									add(new DefaultMutableTreeNode("Valida hasta el: "+x.getFechaFin().toFecha()));
									add(new DefaultMutableTreeNode("Costo: "+Math.round(x.getCosto())));
									add(new DefaultMutableTreeNode("Descuento que aplica: "+Math.round(100*(1-x.getDescuento()))+"%"));
									nodoAct = new DefaultMutableTreeNode("Actividades deportivas");
									for(DtClasesCuponera v: x.getContenido()) {
										nodoAct.add(new DefaultMutableTreeNode(v.getNombreActividad()+" / "+v.getCantidadClases()+" clases."));
									}
									if(nodoAct.getChildCount()==0) {
										nodoAct.add(new DefaultMutableTreeNode("No hay actividades asociadas a esta cuponera."));
									}
									add(nodoAct);
								}
							}
						));
				}
			}
		});
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		Set<String> ss= IDC.getNombreCuponeras();
		model.addElement("-");
		for(String x: ss ) {
			model.addElement(x);
		}
		cbCuponera.setModel(model);
		cbCuponera.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				Set<String> tt = IDC.getNombreCuponeras();
				if(cbCuponera.getItemCount()!=tt.size()+1) {
					String t = (String) cbCuponera.getSelectedItem();
					cbCuponera.removeAllItems();
					model.addElement("-");
					for(String x: IDC.getNombreCuponeras()) {
						cbCuponera.addItem(x);
					}
					cbCuponera.setSelectedItem(t);
				}
			}
		});

		GridBagConstraints gbc_cbCuponera = new GridBagConstraints();
		gbc_cbCuponera.insets = new Insets(0, 0, 5, 5);
		gbc_cbCuponera.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCuponera.gridx = 1;
		gbc_cbCuponera.gridy = 2;
		getContentPane().add(cbCuponera, gbc_cbCuponera);
		
		JSeparator separator = new JSeparator();
		separator.setToolTipText("");
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 3;
		getContentPane().add(separator, gbc_separator);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 1;
		gbc_scrollPane.gridwidth = 1;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);
		
		treeCuponera = new JTree();
		scrollPane.setViewportView(treeCuponera);
		treeCuponera.setRootVisible(false);
		if(((String) cbCuponera.getSelectedItem())=="-") {
			treeCuponera.setModel(new DefaultTreeModel(
					new DefaultMutableTreeNode("No hay cuponera seleccionada.")));
		}
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		treeCuponera.setBorder(BorderFactory.createCompoundBorder(border, 
			      BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		treeCuponera.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		TreeSelectionListener lst = new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				 DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeCuponera.getLastSelectedPathComponent();
				 if(node == null) 
					 return;
				 DefaultMutableTreeNode dad = (DefaultMutableTreeNode) node.getParent();
				 if(dad != null && dad.getUserObject().equals("Actividades deportivas")) {
					 //Ref actdep.
					 refAd.refEntry((String) node.getUserObject());
				 }
			}
		};
		treeCuponera.addTreeSelectionListener(lst);
		GridBagConstraints gbc_treeCuponera = new GridBagConstraints();
		gbc_treeCuponera.insets = new Insets(0, 0, 5, 5);
		gbc_treeCuponera.fill = GridBagConstraints.BOTH;
		gbc_treeCuponera.gridx = 1;
		gbc_treeCuponera.gridy = 4;
		//getContentPane().add(treeCuponera, gbc_treeCuponera);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_1.gridx = 1;
		gbc_verticalStrut_1.gridy = 5;
		getContentPane().add(verticalStrut_1, gbc_verticalStrut_1);
		
	}
	
    public void cbCuponeraLoad() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		Set<String> ss= IDC.getNombreCuponeras();
		model.addElement("-");
		for(String x: ss ) {
			model.addElement(x);
		}
    }
    public void setRef(ConsultaActividadDeportiva a) {
    	refAd = a;
    }
    public void refEntry(String c) {
    	
			
		cbCuponeraLoad();
		cbCuponera.getModel().setSelectedItem(c);
		if (this.isVisible()) 
			this.toFront();
		else {
			this.setVisible(true);
		}
    }
}
