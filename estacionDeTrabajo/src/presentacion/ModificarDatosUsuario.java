/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package presentacion;

import java.awt.Component;

import javax.swing.JInternalFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

import logica.IUsuarioController; 
import datatypes.DtProfesorExt;
import datatypes.DtUsuario;
import datatypes.DtSocio;
import datatypes.DtFecha;
import datatypes.DtProfesor;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ItemEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import excepciones.UsuarioNoExisteException;

@SuppressWarnings("serial")
public class ModificarDatosUsuario extends JInternalFrame {

	//Datos del caso de uso
	DtUsuario datosUsuarioActual;
	Set<String> usuarios;
	Set<String> instituciones;
	
	private IUsuarioController controlUsr;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JComboBox<String> comboBoxUsuario;
	private JLabel labelUsuario;
	private JTextArea textAreaBiografia;
	private JTextField textFieldWebsite;
	private JLabel labelWebsite;
	private JLabel labelBiografia;
	private JLabel labelDescripcion;
	private JLabel labelInstitucion;
	private JLabel labelAclaracionProfesor1;
	private JLabel labelAclaracionProfesor2;
	private JLabel labelAclaracionProfesor3;
	private JLabel labelAclaracionProfesor4;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;
	private JScrollPane scrollPane_1;
	private JLabel labelAclaracionFecha;
	private JLabel lblNewLabel;
	private JTextField inicioAnio;
	// Seleccion de Fecha de Inicio:
	private JComboBox<String> boxIDia; // Depende de mes;
	private JComboBox<String> boxIMes;
	private Component verticalStrut;
	private JTextField textFieldInstitucion;
	private JTextField textFieldTipoDeUsuario;
	DefaultComboBoxModel<String> comboModelDia;	
	public ModificarDatosUsuario(IUsuarioController controlUsr) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				clear();
			}
		});
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		this.usuarios = new HashSet<>();
		this.instituciones = new HashSet<>();
		this.datosUsuarioActual = null;
		this.controlUsr = controlUsr;
		
		/* 
		 *  Parametrizacion de dimensiones
		 */
		int columns = 8;
		int rows = 9;
		int iframeWidth = 550;
		int iframeHeight = 640;
		int gridWidth = iframeWidth/columns;
		int gridHeight = iframeHeight/rows;
		setBounds(100+gridWidth-gridWidth+gridHeight-gridHeight,  25,  iframeWidth,  iframeHeight); // w, h
		
		setTitle("Modificar datos de usuario");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {60,  60,  60,  60,  60,  60,  60,  60};
		gridBagLayout.rowHeights = new int[]{25,  25,  25,  25,  25,  25,  25,  25,  25,  25,  25,  75,  25,  75,  25,  25,  25,  25,  25};
		gridBagLayout.columnWeights = new double[]{0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0};
		gridBagLayout.rowWeights = new double[]{0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0};
		getContentPane().setLayout(gridBagLayout);
		
		comboBoxUsuario = new JComboBox<>();
		comboBoxUsuario.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				DefaultComboBoxModel<String> modeloUsuario = new DefaultComboBoxModel<>();
				comboBoxUsuario.removeAllItems();
				modeloUsuario.addElement("-");
				usuarios = controlUsr.obtenerUsuarios();
				for (String us:usuarios) {
					modeloUsuario.addElement(us);
				}
				comboBoxUsuario.setModel(modeloUsuario);
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
				if (comboBoxUsuario.getSelectedIndex() > 0) {
					String nickUsuario = comboBoxUsuario.getItemAt(comboBoxUsuario.getSelectedIndex());
					try {
						datosUsuarioActual = controlUsr.seleccionarUsuario(nickUsuario);
					} catch  (UsuarioNoExisteException ignore) { }

					if ((boxIMes.getSelectedIndex() % 2 == 0) && (boxIMes.getSelectedIndex() < 7) || 
	        				(boxIMes.getSelectedIndex() % 2 == 1) && (boxIMes.getSelectedIndex() > 8)) {
	        			if (boxIMes.getSelectedIndex() == 2)
	        				boxIDia.removeItem("30");
	        			boxIDia.removeItem("31");
	        		} else {
	        			if (comboModelDia.getIndexOf("30") == -1)
	        				comboModelDia.addElement("30");
	        			if (comboModelDia.getIndexOf("31") == -1)
	        				comboModelDia.addElement("31");
	        		}
					
					textFieldNombre.setText(datosUsuarioActual.getNombre());
					textFieldApellido.setText(datosUsuarioActual.getApellido());
					textFieldEmail.setText(datosUsuarioActual.getEmail());
					DtFecha fechaNacimiento = datosUsuarioActual.getFechaNacimiento();
					boxIDia.setSelectedIndex(fechaNacimiento.getDia());
					boxIMes.setSelectedIndex(fechaNacimiento.getMes());
					inicioAnio.setText(String.valueOf(fechaNacimiento.getAnio()));
					
					//El usuario es profesor
					if (datosUsuarioActual instanceof DtProfesorExt) {
						tipoUsuario = "Profesor";
						DtProfesorExt datosProfesorActual = (DtProfesorExt)datosUsuarioActual;
						textFieldInstitucion.setText(datosProfesorActual.getNombreInstitucion());
						textAreaDescripcion.setText(datosProfesorActual.getDescripcion());
						textAreaBiografia.setText(datosProfesorActual.getBiografia());
						textFieldWebsite.setText(datosProfesorActual.getLink());
					}
					else {
						
						tipoUsuario = "Socio";
						
						/*
						 * Borro campos no relevantes para socio
						 */
						textFieldInstitucion.setText("");
						textAreaDescripcion.setText("");
						textAreaBiografia.setText("");
						textFieldWebsite.setText("");
					}
				}
				else {
					//clear();
				}
				textFieldTipoDeUsuario.setText(tipoUsuario);
				boolean esUsuario = tipoUsuario != "-";
				textFieldNombre.setEnabled(esUsuario);
				textFieldApellido.setEnabled(esUsuario);
				
				
				boxIDia.setEnabled(esUsuario);
				boxIMes.setEnabled(esUsuario);
				
				
				inicioAnio.setEnabled(esUsuario);
				boolean esProfesor = tipoUsuario == "Profesor";
				textAreaBiografia.setEnabled(esProfesor);
				textAreaDescripcion.setEnabled(esProfesor);
				textFieldWebsite.setEnabled(esProfesor);
			}
		});
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0,  0,  5,  5);
		gbc_verticalStrut.gridx = 4;
		gbc_verticalStrut.gridy = 0;
		getContentPane().add(verticalStrut,  gbc_verticalStrut);
		
		labelUsuario = new JLabel("Usuario elegido");
		GridBagConstraints gbc_labelUsuario = new GridBagConstraints();
		gbc_labelUsuario.gridwidth = 2;
		gbc_labelUsuario.insets = new Insets(0,  0,  5,  5);
		gbc_labelUsuario.anchor = GridBagConstraints.WEST;
		gbc_labelUsuario.gridx = 1;
		gbc_labelUsuario.gridy = 0;
		getContentPane().add(labelUsuario,  gbc_labelUsuario);
		
		lblNewLabel = new JLabel("Tipo de usuario");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0,  0,  5,  5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel,  gbc_lblNewLabel);
		comboBoxUsuario.setModel(new DefaultComboBoxModel<>(new String[] {"-"}));
		GridBagConstraints gbc_comboBoxUsuario = new GridBagConstraints();
		gbc_comboBoxUsuario.gridwidth = 3;
		gbc_comboBoxUsuario.insets = new Insets(0,  0,  5,  5);
		gbc_comboBoxUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUsuario.gridx = 1;
		gbc_comboBoxUsuario.gridy = 1;
		getContentPane().add(comboBoxUsuario,  gbc_comboBoxUsuario);
		
		textFieldTipoDeUsuario = new JTextField();
		textFieldTipoDeUsuario.setEnabled(false);
		GridBagConstraints gbc_textFieldTipoDeUsuario = new GridBagConstraints();
		gbc_textFieldTipoDeUsuario.gridwidth = 3;
		gbc_textFieldTipoDeUsuario.insets = new Insets(0,  0,  5,  5);
		gbc_textFieldTipoDeUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTipoDeUsuario.gridx = 4;
		gbc_textFieldTipoDeUsuario.gridy = 1;
		getContentPane().add(textFieldTipoDeUsuario,  gbc_textFieldTipoDeUsuario);
		textFieldTipoDeUsuario.setColumns(10);
		
		JLabel labelNombre = new JLabel("Nombre");
		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.gridwidth = 2;
		gbc_labelNombre.anchor = GridBagConstraints.SOUTH;
		gbc_labelNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelNombre.insets = new Insets(0,  0,  5,  5);
		gbc_labelNombre.gridx = 1;
		gbc_labelNombre.gridy = 2;
		getContentPane().add(labelNombre,  gbc_labelNombre);
		
		JLabel labelApellido = new JLabel("Apellido");
		GridBagConstraints gbc_labelApellido = new GridBagConstraints();
		gbc_labelApellido.anchor = GridBagConstraints.SOUTH;
		gbc_labelApellido.insets = new Insets(0,  0,  5,  5);
		gbc_labelApellido.gridx = 4;
		gbc_labelApellido.gridy = 2;
		getContentPane().add(labelApellido,  gbc_labelApellido);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setEnabled(false);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 3;
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.insets = new Insets(0,  0,  5,  5);
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 3;
		getContentPane().add(textFieldNombre,  gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setEnabled(false);
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.gridwidth = 3;
		gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
		gbc_textFieldApellido.insets = new Insets(0,  0,  5,  5);
		gbc_textFieldApellido.gridx = 4;
		gbc_textFieldApellido.gridy = 3;
		getContentPane().add(textFieldApellido,  gbc_textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel labelEmail = new JLabel("Correo electronico");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.gridwidth = 2;
		gbc_labelEmail.anchor = GridBagConstraints.SOUTH;
		gbc_labelEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelEmail.insets = new Insets(0,  0,  5,  5);
		gbc_labelEmail.gridx = 1;
		gbc_labelEmail.gridy = 4;
		getContentPane().add(labelEmail,  gbc_labelEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEnabled(false);
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.gridwidth = 6;
		gbc_textFieldEmail.fill = GridBagConstraints.BOTH;
		gbc_textFieldEmail.insets = new Insets(0,  0,  5,  5);
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 5;
		getContentPane().add(textFieldEmail,  gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel labelFechaNacimiento = new JLabel("Fecha de nacimiento");
		GridBagConstraints gbc_labelFechaNacimiento = new GridBagConstraints();
		gbc_labelFechaNacimiento.gridwidth = 2;
		gbc_labelFechaNacimiento.anchor = GridBagConstraints.SOUTHWEST;
		gbc_labelFechaNacimiento.insets = new Insets(0,  0,  5,  5);
		gbc_labelFechaNacimiento.gridx = 1;
		gbc_labelFechaNacimiento.gridy = 6;
		getContentPane().add(labelFechaNacimiento,  gbc_labelFechaNacimiento);
		
		labelAclaracionFecha = new JLabel("(dd/mm/aaaa)");
		GridBagConstraints gbc_labelAclaracionFecha = new GridBagConstraints();
		gbc_labelAclaracionFecha.insets = new Insets(0,  0,  5,  5);
		gbc_labelAclaracionFecha.gridx = 6;
		gbc_labelAclaracionFecha.gridy = 6;
		getContentPane().add(labelAclaracionFecha,  gbc_labelAclaracionFecha);
		
		// Arrays auxiliares para Fecha y Hora:
        String[] meses = new String[] { "-",  "Enero",  "Febrero",  "Marzo",  "Abril",  "Mayo",  "Junio",  "Julio",  "Agosto", 
        		"Setiembre",  "Octubre",  "Noviembre",  "Diciembre" };
        
        // JComboBox:
        comboModelDia = new DefaultComboBoxModel<>();
        comboModelDia.addElement("-");
        for (int i = 1; i < 32; i++) {
        	comboModelDia.addElement( String.valueOf(i) );
        }
        
        DefaultComboBoxModel<String> comboModelMes = new DefaultComboBoxModel<>(meses);
		
		boxIDia = new JComboBox<>( comboModelDia );        
		boxIDia.setEnabled(false);
		GridBagConstraints gbc_boxIDia = new GridBagConstraints();
		gbc_boxIDia.insets = new Insets(0,  0,  5,  5);
		gbc_boxIDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_boxIDia.gridx = 1;
		gbc_boxIDia.gridy = 7;
		getContentPane().add(boxIDia,  gbc_boxIDia);
		boxIMes = new JComboBox<>(comboModelMes);
		boxIMes.setEnabled(false);
		boxIMes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if ((boxIMes.getSelectedIndex() % 2 == 0) && (boxIMes.getSelectedIndex() < 7) || 
        				(boxIMes.getSelectedIndex() % 2 == 1) && (boxIMes.getSelectedIndex() > 8)) {
        			if (boxIMes.getSelectedIndex() == 2)
        				boxIDia.removeItem("30");
        			boxIDia.removeItem("31");
        		} else {
        			if (comboModelDia.getIndexOf("30") == -1)
        				comboModelDia.addElement("30");
        			if (comboModelDia.getIndexOf("31") == -1)
        				comboModelDia.addElement("31");
        		}
			}
		});
		GridBagConstraints gbc_boxIMes = new GridBagConstraints();
		gbc_boxIMes.gridwidth = 2;
		gbc_boxIMes.insets = new Insets(0,  0,  5,  5);
		gbc_boxIMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_boxIMes.gridx = 2;
		gbc_boxIMes.gridy = 7;
		getContentPane().add(boxIMes,  gbc_boxIMes);
		
        inicioAnio = new JTextField();
        inicioAnio.setEnabled(false);
        inicioAnio.setText("yyyy");
        inicioAnio.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		inicioAnio.setText("");
        	}
        });
        GridBagConstraints gbc_inicioAnio = new GridBagConstraints();
        gbc_inicioAnio.gridwidth = 1;
        gbc_inicioAnio.fill = GridBagConstraints.BOTH;
        gbc_inicioAnio.insets = new Insets(0,  0,  5,  5);
        gbc_inicioAnio.gridx = 4;
        gbc_inicioAnio.gridy = 7;
        getContentPane().add(inicioAnio,  gbc_inicioAnio);
		
		labelInstitucion = new JLabel("Nombre de Institucion");
		GridBagConstraints gbc_labelInstitucion = new GridBagConstraints();
		gbc_labelInstitucion.gridwidth = 2;
		gbc_labelInstitucion.insets = new Insets(0,  0,  5,  5);
		gbc_labelInstitucion.gridx = 1;
		gbc_labelInstitucion.gridy = 8;
		getContentPane().add(labelInstitucion,  gbc_labelInstitucion);
		
		labelAclaracionProfesor1 = new JLabel("(Solo profesor)");
		GridBagConstraints gbc_labelAclaracionProfesor1 = new GridBagConstraints();
		gbc_labelAclaracionProfesor1.insets = new Insets(0,  0,  5,  5);
		gbc_labelAclaracionProfesor1.gridx = 6;
		gbc_labelAclaracionProfesor1.gridy = 8;
		getContentPane().add(labelAclaracionProfesor1,  gbc_labelAclaracionProfesor1);
		
		textFieldInstitucion = new JTextField();
		textFieldInstitucion.setEnabled(false);
		textFieldInstitucion.setColumns(10);
		GridBagConstraints gbc_textFieldInstitucion = new GridBagConstraints();
		gbc_textFieldInstitucion.gridwidth = 6;
		gbc_textFieldInstitucion.insets = new Insets(0,  0,  5,  5);
		gbc_textFieldInstitucion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInstitucion.gridx = 1;
		gbc_textFieldInstitucion.gridy = 9;
		getContentPane().add(textFieldInstitucion,  gbc_textFieldInstitucion);
		
		labelDescripcion = new JLabel("Descripcion");
		GridBagConstraints gbc_labelDescripcion = new GridBagConstraints();
		gbc_labelDescripcion.gridwidth = 2;
		gbc_labelDescripcion.anchor = GridBagConstraints.WEST;
		gbc_labelDescripcion.insets = new Insets(0,  0,  5,  5);
		gbc_labelDescripcion.gridx = 1;
		gbc_labelDescripcion.gridy = 10;
		getContentPane().add(labelDescripcion,  gbc_labelDescripcion);
		
		labelAclaracionProfesor2 = new JLabel("(Solo profesor)");
		GridBagConstraints gbc_labelAclaracionProfesor2 = new GridBagConstraints();
		gbc_labelAclaracionProfesor2.insets = new Insets(0,  0,  5,  5);
		gbc_labelAclaracionProfesor2.gridx = 6;
		gbc_labelAclaracionProfesor2.gridy = 10;
		getContentPane().add(labelAclaracionProfesor2,  gbc_labelAclaracionProfesor2);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0,  0,  5,  5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 11;
		getContentPane().add(scrollPane,  gbc_scrollPane);
		
		textAreaDescripcion = new JTextArea();
		scrollPane.setViewportView(textAreaDescripcion);
		textAreaDescripcion.setEnabled(false);
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		
		labelBiografia = new JLabel("Biografia (opcional)");
		GridBagConstraints gbc_labelBiografia = new GridBagConstraints();
		gbc_labelBiografia.gridwidth = 2;
		gbc_labelBiografia.anchor = GridBagConstraints.WEST;
		gbc_labelBiografia.insets = new Insets(0,  0,  5,  5);
		gbc_labelBiografia.gridx = 1;
		gbc_labelBiografia.gridy = 12;
		getContentPane().add(labelBiografia,  gbc_labelBiografia);
		
		labelAclaracionProfesor3 = new JLabel("(Solo profesor)");
		GridBagConstraints gbc_labelAclaracionProfesor3 = new GridBagConstraints();
		gbc_labelAclaracionProfesor3.insets = new Insets(0,  0,  5,  5);
		gbc_labelAclaracionProfesor3.gridx = 6;
		gbc_labelAclaracionProfesor3.gridy = 12;
		getContentPane().add(labelAclaracionProfesor3,  gbc_labelAclaracionProfesor3);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 6;
		gbc_scrollPane_1.insets = new Insets(0,  0,  5,  5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 13;
		getContentPane().add(scrollPane_1,  gbc_scrollPane_1);
		
		textAreaBiografia = new JTextArea();
		scrollPane_1.setViewportView(textAreaBiografia);
		textAreaBiografia.setEnabled(false);
		textAreaBiografia.setLineWrap(true);
		textAreaBiografia.setWrapStyleWord(true);
		
		labelWebsite = new JLabel("Website (opcional)");
		GridBagConstraints gbc_labelWebsite = new GridBagConstraints();
		gbc_labelWebsite.gridwidth = 2;
		gbc_labelWebsite.anchor = GridBagConstraints.WEST;
		gbc_labelWebsite.insets = new Insets(0,  0,  5,  5);
		gbc_labelWebsite.gridx = 1;
		gbc_labelWebsite.gridy = 14;
		getContentPane().add(labelWebsite,  gbc_labelWebsite);
		
		labelAclaracionProfesor4 = new JLabel("(Solo profesor)");
		GridBagConstraints gbc_labelAclaracionProfesor4 = new GridBagConstraints();
		gbc_labelAclaracionProfesor4.insets = new Insets(0,  0,  5,  5);
		gbc_labelAclaracionProfesor4.gridx = 6;
		gbc_labelAclaracionProfesor4.gridy = 14;
		getContentPane().add(labelAclaracionProfesor4,  gbc_labelAclaracionProfesor4);
		
		textFieldWebsite = new JTextField();
		textFieldWebsite.setEnabled(false);
		GridBagConstraints gbc_textFieldWebsite = new GridBagConstraints();
		gbc_textFieldWebsite.gridwidth = 6;
		gbc_textFieldWebsite.insets = new Insets(0,  0,  5,  5);
		gbc_textFieldWebsite.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWebsite.gridx = 1;
		gbc_textFieldWebsite.gridy = 15;
		getContentPane().add(textFieldWebsite,  gbc_textFieldWebsite);
		textFieldWebsite.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tomarDatos()==0) {
					clear();
				}
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.anchor = GridBagConstraints.NORTH;
		gbc_btnAceptar.insets = new Insets(0,  0,  5,  5);
		gbc_btnAceptar.gridx = 5;
		gbc_btnAceptar.gridy = 17;
		getContentPane().add(btnAceptar,  gbc_btnAceptar);
		
		JButton btnCancelar = new JButton("Limpiar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.NORTH;
		gbc_btnCancelar.insets = new Insets(0,  0,  5,  5);
		gbc_btnCancelar.gridx = 6;
		gbc_btnCancelar.gridy = 17;
		getContentPane().add(btnCancelar,  gbc_btnCancelar);
		

	}
	
	/*
	 * En caso de ser los datos validos,  esta funcion se encarga de ejecutar el caso de uso correspondiente
	 */
	private int tomarDatos() {
		String tipoDeUsuario;
		String nicknameU;
		String nombreU;
        String apellidoU;
        String emailU;
        int diaU;
        int mesU;
        int anioU;
        String institucionU;
        String descripcionU;
        String biografiaU;
        String websiteU;
        if (!checkFormulario())
        	return 1;
		nicknameU = comboBoxUsuario.getItemAt(comboBoxUsuario.getSelectedIndex()).trim();
		nombreU = this.textFieldNombre.getText().trim();
        apellidoU = this.textFieldApellido.getText().trim();
        emailU = this.textFieldEmail.getText().trim();
        diaU = Integer.parseInt(boxIDia.getSelectedItem().toString().trim());
        mesU = boxIMes.getSelectedIndex();
        anioU = Integer.parseInt(inicioAnio.getText().trim());
        tipoDeUsuario = textFieldTipoDeUsuario.getText().trim();

		/*
		 * Crea el tipo de dato segun el tipo de usuario seleccionado
		 */
		DtUsuario datosUser;
		
		if (tipoDeUsuario.contains("Profesor")) {
			descripcionU = textAreaDescripcion.getText().trim();
            biografiaU = textAreaBiografia.getText().trim();
            websiteU = textFieldWebsite.getText().trim();
            institucionU = textFieldInstitucion.getText().trim();
			datosUser = new DtProfesor(nicknameU, nombreU, apellidoU, emailU,  datosUsuarioActual.getContrasenia(), new DtFecha(anioU, mesU, diaU, 0, 0, 0), institucionU,  descripcionU, biografiaU, websiteU, datosUsuarioActual.getImagen());
		}
		else //Se asume que si no es profesor es socio
		{
			datosUser = new DtSocio(nicknameU, nombreU, apellidoU, emailU, datosUsuarioActual.getContrasenia(), new DtFecha(anioU, mesU, diaU, 0, 0, 0), datosUsuarioActual.getImagen());
		}
		
		/*
		 * Fin de caso de uso y MessageDialog final
		 */
		try {
			this.controlUsr.editarDatosBasicos(nicknameU,  datosUser);
			JOptionPane.showMessageDialog(this,  "El usuario ha sido modificado con de forma exitosa",  this.getTitle(),  JOptionPane.INFORMATION_MESSAGE);
			return 0;
		} catch (UsuarioNoExisteException e) {
			JOptionPane.showMessageDialog(this,  e.getMessage(),  this.getTitle(),  JOptionPane.ERROR_MESSAGE);
			return 1;
		}
	}
	
	/*
	 * Valida los datos ingresados por el usuario
	 */
	private boolean checkFormulario() {
		String tipoU  = this.textFieldTipoDeUsuario.getText().trim();
		int nicknameU = this.comboBoxUsuario.getSelectedIndex();
		String nombreU = this.textFieldNombre.getText().trim();
        String apellidoU = this.textFieldApellido.getText().trim();
        String emailU = this.textFieldEmail.getText().trim();
        int diaU = boxIDia.getSelectedIndex();
        int mesU = boxIMes.getSelectedIndex();
        String anioU = inicioAnio.getText().trim();
        //String websiteU = this.textFieldWebsite.getText();
        String descripcionU = this.textAreaDescripcion.getText().trim();

        //Celdas vacias
        if (nicknameU == 0 || nombreU.isEmpty() || apellidoU.isEmpty() || emailU.isEmpty() || diaU < 1 || mesU < 1 || anioU.isEmpty() ||  ((tipoU.contains("Profesor")) &&  descripcionU.isEmpty())) {
            JOptionPane.showMessageDialog(this,  "No puede haber campos vacios",  this.getTitle(),  JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
      //Numeros no son numeros
        try {
            Integer.parseInt(anioU);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,  "El anio ingresado debe ser un numero",  this.getTitle(),  JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //Fechas inexistentes
        int numAnioU = Integer.parseInt(anioU);
        if (numAnioU < 1900 || numAnioU > 2021) {
        	JOptionPane.showMessageDialog(this,  "El anio ingresado debe ser valido",  this.getTitle(),  JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

	public void clear() {
		// TODO Auto-generated method stub
		comboBoxUsuario.setSelectedIndex(0);
        textFieldTipoDeUsuario.setText("");
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldEmail.setText("");
    	boxIDia.setSelectedIndex(0);
    	boxIMes.setSelectedIndex(0);
    	inicioAnio.setText("yyyy");
    	textFieldInstitucion.setText("");
    	textFieldWebsite.setText("");
    	textAreaDescripcion.setText("");
    	textAreaBiografia.setText("");
    	datosUsuarioActual = null;
	}


}
