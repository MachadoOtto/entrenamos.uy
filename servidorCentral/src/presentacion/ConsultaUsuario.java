package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

import logica.IUsuarioController; 
import datatypes.DtUsuario;
import datatypes.DtSocioExt;
import datatypes.DtFecha;
import datatypes.DtProfesorExt;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.event.ItemEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.JTextPane;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.Color;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;

public class ConsultaUsuario extends JInternalFrame {

	//Datos del caso de uso
	DtUsuario datosUsuarioActual;
	Set<String> usuarios;
	
	private IUsuarioController controlUsr;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JTextField textFieldDia;
	private JTextField textFieldAnio;
	private JComboBox<String> comboBoxUsuario;
	private JLabel labelUsuario;
	private JTextArea textAreaBiografia;
	private JTextField textFieldWebsite;
	private JLabel labelWebsite;
	private JLabel labelBiografia;
	private JLabel labelDescripcion;
	private JLabel labelInstitucion;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;
	private JScrollPane scrollPane_1;
	private JLabel labelAclaracionFecha;
	private JTextPane textPaneTipoDeUsuario;
	private JLabel lblNewLabel;
	private JTextField textFieldMes;
	private JTextField textFieldInstitucion;
	private JTree tree;
	private JLabel labelWebsite_1;
	
/*
 * AVISO IMPORTANTE: EL WINDOW BUILDER ESTA BUGEADO PARA ESTA CLASE
 * NO ABRIR!!!
 * SI SE ABRE EL WINDOW BUILDER, LEER LOS COMENTARIOS SOBRE COMO CORREGIR
 * LOS ERRORES QUE GENERA.
 * 
 */
	@SuppressWarnings("serial")
	public ConsultaUsuario(IUsuarioController controlUsr) {
//		addInternalFrameListener(new InternalFrameAdapter() {
//			@Override
//			public void internalFrameClosed(InternalFrameEvent e) {
//				setVisible(false);
//				clear();
//			}
//		});
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(true);
		this.usuarios = new HashSet<>();
		this.datosUsuarioActual = null;
		this.controlUsr = controlUsr;
		
		/* 
		 *  Parametrizacion de dimensiones
		 */
		int columns = 8;
		int rows = 9;
		int iframeWidth = 480;
		int iframeHeight = 575;
		int gridWidth = iframeWidth/columns;
		int gridHeight = iframeHeight/rows;
		setBounds(100, 25, 487, 682); // w,h
		
		setTitle("Consulta de usuario");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 60, 60, 60, 60, 60, 60, 30};
		gridBagLayout.rowHeights = new int[]{25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 70, 25, 50, 25, 30, 25, 25, 100, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		
		comboBoxUsuario = new JComboBox<>();
		comboBoxUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		comboBoxUsuario.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				DefaultComboBoxModel<String> modeloUsuarios = new DefaultComboBoxModel<>();
				comboBoxUsuario.removeAllItems();
				modeloUsuarios.addElement("-");
				
				
				usuarios = controlUsr.obtenerUsuarios();
				for(String us:usuarios) {
					modeloUsuarios.addElement(us);
				}
				comboBoxUsuario.setModel(modeloUsuarios);
				
			}
		});
		comboBoxUsuario.addItemListener(new ItemListener() {
			@SuppressWarnings("serial")
			public void itemStateChanged(ItemEvent e) {
				
				/*
				 * Habilita determinados campos segun el tipo de usuario.
				 * Ademas rellena cada campo con sus datos actuales
				 * Si no se seleccionan usuarios se limpian los campos
				 */	
				
				String tipoUsuario = "-";
				if(comboBoxUsuario.getSelectedIndex() > 0) {
					String nickUsuario = comboBoxUsuario.getItemAt(comboBoxUsuario.getSelectedIndex());
					datosUsuarioActual = controlUsr.seleccionarUsuario(nickUsuario);
					textFieldNombre.setText(datosUsuarioActual.getNombre());
					textFieldApellido.setText(datosUsuarioActual.getApellido());
					textFieldEmail.setText(datosUsuarioActual.getEmail());
					DtFecha fechaNacimiento = datosUsuarioActual.getFechaNacimiento();
					textFieldDia.setText(String.valueOf(fechaNacimiento.getDia()));
					textFieldMes.setText(String.valueOf(fechaNacimiento.getMes()));
					textFieldAnio.setText(String.valueOf(fechaNacimiento.getAnio()));
					
					//El usuario es profesor
					if(datosUsuarioActual instanceof DtProfesorExt) {
						tipoUsuario = "Profesor";
						DtProfesorExt datosProfesorActual = (DtProfesorExt)datosUsuarioActual;
						textFieldInstitucion.setText(datosProfesorActual.getNombreInstitucion());
						textAreaDescripcion.setText(datosProfesorActual.getDescripcion());
						textAreaBiografia.setText(datosProfesorActual.getBiografia());
						textFieldWebsite.setText(datosProfesorActual.getLink());
						labelWebsite_1.setText("Clases dictadas (ordenadas por actividad deportiva)");
						Set<Entry<String, Set<String>>> m = datosProfesorActual.getClasesxActividades().entrySet();
						if(m.size()==0) {
							tree.setModel(new DefaultTreeModel(
									new DefaultMutableTreeNode("root") {
										{
											//WindowBuilder BUG: se cambia a getContentPane.add() por algun motivo. Dejarlo solo como add();
											add(new DefaultMutableTreeNode("El profesor no dicta ninguna clase."));
										}
									}
								));
						} else {
							tree.setModel(new DefaultTreeModel(
								new DefaultMutableTreeNode("root") {
									{
									
									for(Entry<String, Set<String>> ad: m) {
										DefaultMutableTreeNode nodoAct = new DefaultMutableTreeNode(ad.getKey());
										for(String c: ad.getValue()) {
											nodoAct.add(new DefaultMutableTreeNode(c));
										}
										add(nodoAct);
									}
									
									}
								}
							));
						}
					}
					else {
						labelWebsite_1.setText("Clases a las que se inscribió (ordenadas por actividad deportiva)");
						DtSocioExt datosSocioActual = (DtSocioExt)datosUsuarioActual;
						Set<Entry<String, Set<String>>> m = datosSocioActual.getAguadeUwu().entrySet();
						if(m.size()==0) {
							tree.setModel(new DefaultTreeModel(
									new DefaultMutableTreeNode("root") {
										{
											//WindowBuilder BUG: se cambia a getContentPane.add() por algun motivo. Dejarlo solo como add();
											add(new DefaultMutableTreeNode("El socio no está inscripto a ninguna clase."));
										}
									}
								));
						} else {
							tree.setModel(new DefaultTreeModel(
								new DefaultMutableTreeNode("root") {
									{
									
									for(Entry<String, Set<String>> ad: m) {
										DefaultMutableTreeNode nodoAct = new DefaultMutableTreeNode(ad.getKey());
										for(String c: ad.getValue()) {
											nodoAct.add(new DefaultMutableTreeNode(c));
										}
										add(nodoAct);
									}
									
									}
								}
							));
						}
						
						/*
						 * Borro campos no relevantes para socio
						 */
						
						textFieldInstitucion.setText("");
						textAreaDescripcion.setText("");
						textAreaBiografia.setText("");
						textFieldWebsite.setText("");
//						textAreaActividades.setText("");
					}
					textPaneTipoDeUsuario.setText(tipoUsuario);
				}
				else {
					//clear();
				}
			}
		});
		
		labelUsuario = new JLabel("Usuario elegido");
		GridBagConstraints gbc_labelUsuario = new GridBagConstraints();
		gbc_labelUsuario.gridwidth = 2;
		gbc_labelUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_labelUsuario.anchor = GridBagConstraints.WEST;
		gbc_labelUsuario.gridx = 1;
		gbc_labelUsuario.gridy = 0;
		getContentPane().add(labelUsuario, gbc_labelUsuario);
		
		lblNewLabel = new JLabel("Tipo de usuario");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		comboBoxUsuario.setModel(new DefaultComboBoxModel(new String[] {"-"}));
		GridBagConstraints gbc_comboBoxUsuario = new GridBagConstraints();
		gbc_comboBoxUsuario.gridwidth = 3;
		gbc_comboBoxUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUsuario.gridx = 1;
		gbc_comboBoxUsuario.gridy = 1;
		getContentPane().add(comboBoxUsuario, gbc_comboBoxUsuario);
		
		textPaneTipoDeUsuario = new JTextPane();
		textPaneTipoDeUsuario.setEditable(false);
		GridBagConstraints gbc_textPaneTipoDeUsuario = new GridBagConstraints();
		gbc_textPaneTipoDeUsuario.gridwidth = 3;
		gbc_textPaneTipoDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textPaneTipoDeUsuario.fill = GridBagConstraints.BOTH;
		gbc_textPaneTipoDeUsuario.gridx = 4;
		gbc_textPaneTipoDeUsuario.gridy = 1;
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		textPaneTipoDeUsuario.setBorder(BorderFactory.createCompoundBorder(border, 
			      BorderFactory.createEmptyBorder(3, 4, 1, 0)));
		getContentPane().add(textPaneTipoDeUsuario, gbc_textPaneTipoDeUsuario);
		
		JLabel labelNombre = new JLabel("Nombre");
		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.gridwidth = 2;
		gbc_labelNombre.anchor = GridBagConstraints.SOUTH;
		gbc_labelNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombre.gridx = 1;
		gbc_labelNombre.gridy = 2;
		getContentPane().add(labelNombre, gbc_labelNombre);
		
		JLabel labelApellido = new JLabel("Apellido");
		GridBagConstraints gbc_labelApellido = new GridBagConstraints();
		gbc_labelApellido.gridwidth = 2;
		gbc_labelApellido.anchor = GridBagConstraints.SOUTHWEST;
		gbc_labelApellido.insets = new Insets(0, 0, 5, 5);
		gbc_labelApellido.gridx = 4;
		gbc_labelApellido.gridy = 2;
		getContentPane().add(labelApellido, gbc_labelApellido);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setDisabledTextColor(Color.BLACK);
		textFieldNombre.setEnabled(false);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 3;
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 3;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setDisabledTextColor(Color.BLACK);
		textFieldApellido.setEnabled(false);
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.gridwidth = 3;
		gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellido.gridx = 4;
		gbc_textFieldApellido.gridy = 3;
		getContentPane().add(textFieldApellido, gbc_textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel labelEmail = new JLabel("Correo electronico");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.gridwidth = 2;
		gbc_labelEmail.anchor = GridBagConstraints.SOUTH;
		gbc_labelEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 1;
		gbc_labelEmail.gridy = 4;
		getContentPane().add(labelEmail, gbc_labelEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setDisabledTextColor(Color.BLACK);
		textFieldEmail.setEnabled(false);
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.gridwidth = 6;
		gbc_textFieldEmail.fill = GridBagConstraints.BOTH;
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 5;
		getContentPane().add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel labelFechaNacimiento = new JLabel("Fecha de nacimiento");
		GridBagConstraints gbc_labelFechaNacimiento = new GridBagConstraints();
		gbc_labelFechaNacimiento.gridwidth = 2;
		gbc_labelFechaNacimiento.anchor = GridBagConstraints.SOUTHWEST;
		gbc_labelFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_labelFechaNacimiento.gridx = 1;
		gbc_labelFechaNacimiento.gridy = 6;
		getContentPane().add(labelFechaNacimiento, gbc_labelFechaNacimiento);
		
		labelAclaracionFecha = new JLabel("(dd/mm/aaaa)");
		GridBagConstraints gbc_labelAclaracionFecha = new GridBagConstraints();
		gbc_labelAclaracionFecha.anchor = GridBagConstraints.EAST;
		gbc_labelAclaracionFecha.gridwidth = 2;
		gbc_labelAclaracionFecha.insets = new Insets(0, 0, 5, 5);
		gbc_labelAclaracionFecha.gridx = 5;
		gbc_labelAclaracionFecha.gridy = 6;
		getContentPane().add(labelAclaracionFecha, gbc_labelAclaracionFecha);
		
		textFieldDia = new JTextField();
		textFieldDia.setDisabledTextColor(Color.BLACK);
		textFieldDia.setEnabled(false);
		GridBagConstraints gbc_textFieldDia = new GridBagConstraints();
		gbc_textFieldDia.gridwidth = 2;
		gbc_textFieldDia.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDia.gridx = 1;
		gbc_textFieldDia.gridy = 7;
		getContentPane().add(textFieldDia, gbc_textFieldDia);
		textFieldDia.setColumns(10);
		
		textFieldMes = new JTextField();
		textFieldMes.setDisabledTextColor(Color.BLACK);
		textFieldMes.setEnabled(false);
		textFieldMes.setColumns(10);
		GridBagConstraints gbc_textFieldMes = new GridBagConstraints();
		gbc_textFieldMes.gridwidth = 2;
		gbc_textFieldMes.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMes.gridx = 3;
		gbc_textFieldMes.gridy = 7;
		getContentPane().add(textFieldMes, gbc_textFieldMes);
		
		textFieldAnio = new JTextField();
		textFieldAnio.setDisabledTextColor(Color.BLACK);
		textFieldAnio.setEnabled(false);
		GridBagConstraints gbc_textFieldAnio = new GridBagConstraints();
		gbc_textFieldAnio.gridwidth = 2;
		gbc_textFieldAnio.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAnio.gridx = 5;
		gbc_textFieldAnio.gridy = 7;
		getContentPane().add(textFieldAnio, gbc_textFieldAnio);
		textFieldAnio.setColumns(10);
		
		labelInstitucion = new JLabel("Nombre de Institucion");
		GridBagConstraints gbc_labelInstitucion = new GridBagConstraints();
		gbc_labelInstitucion.anchor = GridBagConstraints.WEST;
		gbc_labelInstitucion.gridwidth = 2;
		gbc_labelInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_labelInstitucion.gridx = 1;
		gbc_labelInstitucion.gridy = 8;
		getContentPane().add(labelInstitucion, gbc_labelInstitucion);
		
		textFieldInstitucion = new JTextField();
		textFieldInstitucion.setDisabledTextColor(Color.BLACK);
		textFieldInstitucion.setEnabled(false);
		textFieldInstitucion.setColumns(10);
		GridBagConstraints gbc_textFieldInstitucion = new GridBagConstraints();
		gbc_textFieldInstitucion.gridwidth = 6;
		gbc_textFieldInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldInstitucion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInstitucion.gridx = 1;
		gbc_textFieldInstitucion.gridy = 9;
		getContentPane().add(textFieldInstitucion, gbc_textFieldInstitucion);
		
		labelDescripcion = new JLabel("Descripcion");
		GridBagConstraints gbc_labelDescripcion = new GridBagConstraints();
		gbc_labelDescripcion.gridwidth = 2;
		gbc_labelDescripcion.anchor = GridBagConstraints.WEST;
		gbc_labelDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_labelDescripcion.gridx = 1;
		gbc_labelDescripcion.gridy = 10;
		getContentPane().add(labelDescripcion, gbc_labelDescripcion);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 11;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setDisabledTextColor(Color.BLACK);
		scrollPane.setViewportView(textAreaDescripcion);
		textAreaDescripcion.setEnabled(false);
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		
		labelBiografia = new JLabel("Biograf\u00EDa");
		GridBagConstraints gbc_labelBiografia = new GridBagConstraints();
		gbc_labelBiografia.gridwidth = 2;
		gbc_labelBiografia.anchor = GridBagConstraints.WEST;
		gbc_labelBiografia.insets = new Insets(0, 0, 5, 5);
		gbc_labelBiografia.gridx = 1;
		gbc_labelBiografia.gridy = 12;
		getContentPane().add(labelBiografia, gbc_labelBiografia);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.gridwidth = 6;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 13;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		textAreaBiografia = new JTextArea();
		textAreaBiografia.setDisabledTextColor(Color.BLACK);
		scrollPane_1.setViewportView(textAreaBiografia);
		textAreaBiografia.setEnabled(false);
		textAreaBiografia.setLineWrap(true);
		textAreaBiografia.setWrapStyleWord(true);
		
		labelWebsite = new JLabel("Website");
		GridBagConstraints gbc_labelWebsite = new GridBagConstraints();
		gbc_labelWebsite.gridwidth = 2;
		gbc_labelWebsite.anchor = GridBagConstraints.WEST;
		gbc_labelWebsite.insets = new Insets(0, 0, 5, 5);
		gbc_labelWebsite.gridx = 1;
		gbc_labelWebsite.gridy = 15;
		getContentPane().add(labelWebsite, gbc_labelWebsite);
		
		textFieldWebsite = new JTextField();
		textFieldWebsite.setDisabledTextColor(Color.BLACK);
		textFieldWebsite.setEnabled(false);
		GridBagConstraints gbc_textFieldWebsite = new GridBagConstraints();
		gbc_textFieldWebsite.gridwidth = 6;
		gbc_textFieldWebsite.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldWebsite.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWebsite.gridx = 1;
		gbc_textFieldWebsite.gridy = 16;
		getContentPane().add(textFieldWebsite, gbc_textFieldWebsite);
		textFieldWebsite.setColumns(10);
		
		labelWebsite_1 = new JLabel("Clases");
		GridBagConstraints gbc_labelWebsite_1 = new GridBagConstraints();
		gbc_labelWebsite_1.anchor = GridBagConstraints.WEST;
		gbc_labelWebsite_1.gridwidth = 6;
		gbc_labelWebsite_1.insets = new Insets(0, 0, 5, 5);
		gbc_labelWebsite_1.gridx = 1;
		gbc_labelWebsite_1.gridy = 17;
		getContentPane().add(labelWebsite_1, gbc_labelWebsite_1);
		
		tree = new JTree();
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    if (SwingUtilities.isRightMouseButton(e)) {
			        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
			        if(selPath==null) return;
			        if(selPath.getPathCount()==2 && (((DefaultMutableTreeNode) selPath.getPathComponent(1)).getChildCount())>0) {
			        	String q = (String) ((DefaultMutableTreeNode) selPath.getPathComponent(1)).getUserObject();
			        	//AQUI VA LA REFERENCIA A LA ACTIVIDAD DEPORTIVA
			        }
			        if(selPath.getPathCount()==3 && (((DefaultMutableTreeNode) selPath.getPathComponent(2)).getChildCount())==0) {
			        	String q = (String) ((DefaultMutableTreeNode) selPath.getPathComponent(1)).getUserObject();
			        	//AQUI VA LA REFERENCIA A LA CLASE SELECCIONADA
			        }
			    }
			}
		});
		tree.setRootVisible(false);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("root") {
				{
					//WindowBuilder BUG: se cambia a getContentPane.add() por algun motivo. Dejarlo solo como add();
					// Cada vez que se abre la ventan design hay que corregirlo xddd;
					add(new DefaultMutableTreeNode("Aquí se listan las clases."));
					add(new DefaultMutableTreeNode("Las clases est\u00E1n organizadas por actividad deportiva."));
				}
			}
		));
		GridBagConstraints gbc_tree = new GridBagConstraints();
		gbc_tree.gridwidth = 6;
		gbc_tree.insets = new Insets(0, 0, 5, 5);
		gbc_tree.fill = GridBagConstraints.BOTH;
		gbc_tree.gridx = 1;
		gbc_tree.gridy = 18;
		getContentPane().add(tree, gbc_tree);
		

	}
	
	/*
	 * Se encarga de limpiar datos ingresados por el usuario
	 */

	@SuppressWarnings("serial")
	public void clear() {
        textPaneTipoDeUsuario.setText("");
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldEmail.setText("");
    	textFieldDia.setText("");
    	textFieldMes.setText("");
    	textFieldAnio.setText("");
    	textFieldInstitucion.setText("");
    	textFieldWebsite.setText("");
    	textAreaDescripcion.setText("");
    	textAreaBiografia.setText("");
    	labelWebsite_1.setText("Clases");
		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("root") {
					{
						//WindowBuilder BUG: se cambia a getContentPane.add() por algun motivo. Dejarlo solo como add();
						// Cada vez que se abre la ventan design hay que corregirlo xddd;
						add(new DefaultMutableTreeNode("Aquí se listan las clases."));
						add(new DefaultMutableTreeNode("Las clases est\u00E1n organizadas por actividad deportiva."));
					}
				}
			));
    	datosUsuarioActual = null;
    	comboBoxUsuario.setSelectedIndex(0);
	}

}

//tree.setModel(new DefaultTreeModel(
//		new DefaultMutableTreeNode("root") {
//			{
//				add(new DefaultMutableTreeNode("Aqui se listan las clases que el profesor seleccionado dicta."));
//				add(new DefaultMutableTreeNode("Las clases est\u00E1n organizadas por actividad deportiva."));
//			}
//		}
//	));
