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
import javax.swing.DefaultComboBoxModel;

import logica.IUsuarioController; 
import datatypes.DtUsuario;
import datatypes.DtSocioExt;
import datatypes.DtFecha;
import datatypes.DtProfesorExt;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ItemEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.JTextPane;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

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
	private JComboBox comboBoxUsuario;
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
	private JLabel lblActividadesDeportivas;
	private JLabel labelWebsite_2;
	private JTextField textFieldMes;
	private JTextField textFieldInstitucion;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JTextArea textAreaActividades;
	private JTextArea textAreaClases;
	
	public ConsultaUsuario(IUsuarioController controlUsr) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				setVisible(false);
				limpiarFormulario();
			}
		});
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
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
		setBounds(100, 25, iframeWidth, iframeHeight); // w,h
		
		setTitle("Consulta de usuario");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {60, 60, 60, 60, 60, 60, 60, 60};
		gridBagLayout.rowHeights = new int[]{25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 50, 25, 25, 25, 75, 25, 25};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		comboBoxUsuario = new JComboBox();
		comboBoxUsuario.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				if(usuarios.isEmpty()) {
					usuarios = controlUsr.obtenerUsuarios();
					for(String us:usuarios) {
						comboBoxUsuario.addItem(us);
					}
				}
			}
		});
		comboBoxUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				/*
				 * Habilita determinados campos segun el tipo de usuario.
				 * Ademas rellena cada campo con sus datos actuales
				 * Si no se seleccionan usuarios se limpian los campos
				 */
				String tipoUsuario = "-";
				if(comboBoxUsuario.getSelectedIndex() != 0) {
					datosUsuarioActual = controlUsr.seleccionarUsuario(comboBoxUsuario.getSelectedItem().toString());
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
						Set<String> clasesDictadas = datosProfesorActual.getClasesDictadas();
						String textoClases = "";
						for(String cd:clasesDictadas) {
							textoClases += cd + "\n";
						}
						textAreaClases.setText(textoClases);
						Set<String> actividadesDictadas = datosProfesorActual.getActividadesDepAsociadas();
						String textoActividades = "";
						for(String ad:actividadesDictadas) {
							textoActividades += ad + "\n";
						}
						textAreaActividades.setText(textoClases);
					}
					else {
						DtSocioExt datosSocioActual = (DtSocioExt)datosUsuarioActual;
						Set<String> clasesSocio = datosSocioActual.getClases();
						String textoClases = "";
						for(String cd:clasesSocio) {
							textoClases += cd + "\n";
						}
						tipoUsuario = "Socio";
					}
				}
				else {
					limpiarFormulario();
				}
				textPaneTipoDeUsuario.setText(tipoUsuario);
				boolean esUsuario = tipoUsuario != "-";
				textFieldNombre.setEnabled(esUsuario);
				textFieldApellido.setEnabled(esUsuario);
				textFieldDia.setEnabled(esUsuario);
				textFieldMes.setEnabled(esUsuario);
				textFieldAnio.setEnabled(esUsuario);
				boolean esProfesor = tipoUsuario == "Profesor";
				textAreaBiografia.setEnabled(esProfesor);
				textAreaDescripcion.setEnabled(esProfesor);
				textFieldWebsite.setEnabled(esProfesor);
				textFieldInstitucion.setEnabled(esProfesor);
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
		gbc_labelInstitucion.gridwidth = 2;
		gbc_labelInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_labelInstitucion.gridx = 1;
		gbc_labelInstitucion.gridy = 8;
		getContentPane().add(labelInstitucion, gbc_labelInstitucion);
		
		textFieldInstitucion = new JTextField();
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
		
		labelBiografia = new JLabel("Biografia");
		GridBagConstraints gbc_labelBiografia = new GridBagConstraints();
		gbc_labelBiografia.gridwidth = 2;
		gbc_labelBiografia.anchor = GridBagConstraints.WEST;
		gbc_labelBiografia.insets = new Insets(0, 0, 5, 5);
		gbc_labelBiografia.gridx = 4;
		gbc_labelBiografia.gridy = 10;
		getContentPane().add(labelBiografia, gbc_labelBiografia);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 11;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		textAreaDescripcion = new JTextArea();
		scrollPane.setViewportView(textAreaDescripcion);
		textAreaDescripcion.setEnabled(false);
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 4;
		gbc_scrollPane_1.gridy = 11;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		textAreaBiografia = new JTextArea();
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
		gbc_labelWebsite.gridy = 12;
		getContentPane().add(labelWebsite, gbc_labelWebsite);
		
		textFieldWebsite = new JTextField();
		textFieldWebsite.setEnabled(false);
		GridBagConstraints gbc_textFieldWebsite = new GridBagConstraints();
		gbc_textFieldWebsite.gridwidth = 6;
		gbc_textFieldWebsite.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldWebsite.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWebsite.gridx = 1;
		gbc_textFieldWebsite.gridy = 13;
		getContentPane().add(textFieldWebsite, gbc_textFieldWebsite);
		textFieldWebsite.setColumns(10);
		
		lblActividadesDeportivas = new JLabel("Actividades deportivas");
		GridBagConstraints gbc_lblActividadesDeportivas = new GridBagConstraints();
		gbc_lblActividadesDeportivas.gridwidth = 2;
		gbc_lblActividadesDeportivas.insets = new Insets(0, 0, 5, 5);
		gbc_lblActividadesDeportivas.gridx = 1;
		gbc_lblActividadesDeportivas.gridy = 14;
		getContentPane().add(lblActividadesDeportivas, gbc_lblActividadesDeportivas);
		
		labelWebsite_2 = new JLabel("Clases deportivas");
		GridBagConstraints gbc_labelWebsite_2 = new GridBagConstraints();
		gbc_labelWebsite_2.anchor = GridBagConstraints.WEST;
		gbc_labelWebsite_2.gridwidth = 2;
		gbc_labelWebsite_2.insets = new Insets(0, 0, 5, 5);
		gbc_labelWebsite_2.gridx = 4;
		gbc_labelWebsite_2.gridy = 14;
		getContentPane().add(labelWebsite_2, gbc_labelWebsite_2);
		
		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 3;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 15;
		getContentPane().add(scrollPane_2, gbc_scrollPane_2);
		
		textAreaActividades = new JTextArea();
		textAreaActividades.setEditable(false);
		textAreaActividades.setWrapStyleWord(true);
		textAreaActividades.setLineWrap(true);
		textAreaActividades.setEnabled(false);
		scrollPane_2.setViewportView(textAreaActividades);
		
		scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridwidth = 3;
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 4;
		gbc_scrollPane_3.gridy = 15;
		getContentPane().add(scrollPane_3, gbc_scrollPane_3);
		
		textAreaClases = new JTextArea();
		textAreaClases.setEditable(false);
		textAreaClases.setWrapStyleWord(true);
		textAreaClases.setLineWrap(true);
		textAreaClases.setEnabled(false);
		scrollPane_3.setViewportView(textAreaClases);
		

	}
	
	/*
	 * Se encarga de limpiar datos ingresados por el usuario
	 */
	private void limpiarFormulario() {
		comboBoxUsuario.setSelectedIndex(0);
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
    	textAreaActividades.setText("");
    	textAreaClases.setText("");
    	usuarios.clear();
    	datosUsuarioActual = null;
    }

	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
