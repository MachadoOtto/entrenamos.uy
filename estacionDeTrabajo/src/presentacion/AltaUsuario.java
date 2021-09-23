package presentacion;

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
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import excepciones.InstitucionException;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class AltaUsuario extends JInternalFrame {

	Set<String> instituciones;
	private IUsuarioController controlUsr;
	private JTextField textFieldNickname;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JComboBox<String> comboBoxTipoDeUsuario;
	private JLabel labelTipoDeUsuario;
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
	private JComboBox<String> comboBoxInstitucion;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;
	private JScrollPane scrollPane_1;
	private JTextField inicioAnio;
	// Seleccion de Fecha de Inicio:
	private JComboBox<String> boxIDia; // Depende de mes;
	private JComboBox<String> boxIMes;
	private Component verticalStrut;
	private JLabel labelContrasenia1;
	private JLabel lblReescribirContrasea;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	
	public AltaUsuario(IUsuarioController controlUsr) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		this.instituciones = new HashSet<>();
		this.controlUsr = controlUsr;
		
		/* 
		 *  Parametrizacion de dimensiones
		 */
		int columns = 8;
		int rows = 9;
		int iframeWidth = 550;
		int iframeHeight = 700;
		int gridWidth = iframeWidth/columns;
		int gridHeight = iframeHeight/rows;
		int x = gridWidth+gridHeight;
		setBounds(100, 25, iframeWidth, iframeHeight); // w,h
		
		setTitle("Alta de usuario");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {25, 60, 60, 60, 60, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{5, 0, 0, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 75, 25, 75, 25, 25, 25, 0, 25};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		comboBoxTipoDeUsuario = new JComboBox<String>();
		comboBoxTipoDeUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				/*
				 * Si se eligio profesor en el comboBox
				 * se hacen visibles los campos que solo le corresponden a este
				 */
				boolean esProfesor = false;
				esProfesor = comboBoxTipoDeUsuario.getSelectedItem().toString() == "Profesor";
				textAreaBiografia.setEnabled(esProfesor);
				textAreaDescripcion.setEnabled(esProfesor);
				textFieldWebsite.setEnabled(esProfesor);
				comboBoxInstitucion.setEnabled(esProfesor);
				if(esProfesor) {
					textAreaBiografia.setText("");
					textAreaDescripcion.setText("");
					textFieldWebsite.setText("");
					comboBoxInstitucion.setSelectedIndex(0);
				}
	
			}
		});
		
		labelTipoDeUsuario = new JLabel("Tipo de usuario");
		GridBagConstraints gbc_labelTipoDeUsuario = new GridBagConstraints();
		gbc_labelTipoDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_labelTipoDeUsuario.anchor = GridBagConstraints.EAST;
		gbc_labelTipoDeUsuario.gridx = 5;
		gbc_labelTipoDeUsuario.gridy = 1;
		getContentPane().add(labelTipoDeUsuario, gbc_labelTipoDeUsuario);
		comboBoxTipoDeUsuario.setModel(new DefaultComboBoxModel<>(new String[] {"-", "Socio", "Profesor"}));
		GridBagConstraints gbc_comboBoxTipoDeUsuario = new GridBagConstraints();
		gbc_comboBoxTipoDeUsuario.anchor = GridBagConstraints.SOUTH;
		gbc_comboBoxTipoDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTipoDeUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipoDeUsuario.gridx = 6;
		gbc_comboBoxTipoDeUsuario.gridy = 1;
		getContentPane().add(comboBoxTipoDeUsuario, gbc_comboBoxTipoDeUsuario);
		
		JLabel labelNickname = new JLabel("Nickname");
		GridBagConstraints gbc_labelNickname = new GridBagConstraints();
		gbc_labelNickname.gridwidth = 2;
		gbc_labelNickname.anchor = GridBagConstraints.SOUTH;
		gbc_labelNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelNickname.insets = new Insets(0, 0, 5, 5);
		gbc_labelNickname.gridx = 1;
		gbc_labelNickname.gridy = 2;
		getContentPane().add(labelNickname, gbc_labelNickname);
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 4+x-x;
		gbc_verticalStrut.gridy = 2;
		getContentPane().add(verticalStrut, gbc_verticalStrut);
		
		textFieldNickname = new JTextField();
		GridBagConstraints gbc_textFieldNickname = new GridBagConstraints();
		gbc_textFieldNickname.gridwidth = 6;
		gbc_textFieldNickname.fill = GridBagConstraints.BOTH;
		gbc_textFieldNickname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNickname.gridx = 1;
		gbc_textFieldNickname.gridy = 3;
		getContentPane().add(textFieldNickname, gbc_textFieldNickname);
		textFieldNickname.setColumns(10);
		
		JLabel labelNombre = new JLabel("Nombre");
		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.gridwidth = 2;
		gbc_labelNombre.anchor = GridBagConstraints.SOUTH;
		gbc_labelNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombre.gridx = 1;
		gbc_labelNombre.gridy = 4;
		getContentPane().add(labelNombre, gbc_labelNombre);
		
		JLabel labelApellido = new JLabel("Apellido");
		GridBagConstraints gbc_labelApellido = new GridBagConstraints();
		gbc_labelApellido.gridwidth = 2;
		gbc_labelApellido.anchor = GridBagConstraints.SOUTHWEST;
		gbc_labelApellido.insets = new Insets(0, 0, 5, 5);
		gbc_labelApellido.gridx = 4;
		gbc_labelApellido.gridy = 4;
		getContentPane().add(labelApellido, gbc_labelApellido);
		
		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 3;
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 5;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.gridwidth = 3;
		gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellido.gridx = 4;
		gbc_textFieldApellido.gridy = 5;
		getContentPane().add(textFieldApellido, gbc_textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel labelEmail = new JLabel("Correo electronico");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.gridwidth = 2;
		gbc_labelEmail.anchor = GridBagConstraints.SOUTHWEST;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 1;
		gbc_labelEmail.gridy = 6;
		getContentPane().add(labelEmail, gbc_labelEmail);
        
        
        // Arrays auxiliares para Fecha y Hora:
        String[] meses = new String[] { "-", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
        		"Setiembre", "Octubre", "Noviembre", "Diciembre" };
        
        // JComboBox:
        DefaultComboBoxModel<String> comboModelDia = new DefaultComboBoxModel<>();
        comboModelDia.addElement("-");
        for(int i = 1; i < 32; i++) {
        	comboModelDia.addElement( String.valueOf(i) );
        }
        
        DefaultComboBoxModel<String> comboModelMes = new DefaultComboBoxModel<>(meses);
		
		textFieldEmail = new JTextField();
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.gridwidth = 6;
		gbc_textFieldEmail.fill = GridBagConstraints.BOTH;
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 7;
		getContentPane().add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		labelContrasenia1 = new JLabel("Contraseña");
		GridBagConstraints gbc_labelContrasenia1 = new GridBagConstraints();
		gbc_labelContrasenia1.gridwidth = 2;
		gbc_labelContrasenia1.anchor = GridBagConstraints.WEST;
		gbc_labelContrasenia1.insets = new Insets(0, 0, 5, 5);
		gbc_labelContrasenia1.gridx = 1;
		gbc_labelContrasenia1.gridy = 8;
		getContentPane().add(labelContrasenia1, gbc_labelContrasenia1);
		
		lblReescribirContrasea = new JLabel("Reescribir contraseña");
		GridBagConstraints gbc_lblReescribirContrasea = new GridBagConstraints();
		gbc_lblReescribirContrasea.anchor = GridBagConstraints.WEST;
		gbc_lblReescribirContrasea.gridwidth = 2;
		gbc_lblReescribirContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblReescribirContrasea.gridx = 4;
		gbc_lblReescribirContrasea.gridy = 8;
		getContentPane().add(lblReescribirContrasea, gbc_lblReescribirContrasea);
		
		passwordField1 = new JPasswordField();
		GridBagConstraints gbc_passwordField1 = new GridBagConstraints();
		gbc_passwordField1.gridwidth = 3;
		gbc_passwordField1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField1.gridx = 1;
		gbc_passwordField1.gridy = 9;
		getContentPane().add(passwordField1, gbc_passwordField1);
		
		passwordField2 = new JPasswordField();
		GridBagConstraints gbc_passwordField2 = new GridBagConstraints();
		gbc_passwordField2.gridwidth = 3;
		gbc_passwordField2.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField2.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField2.gridx = 4;
		gbc_passwordField2.gridy = 9;
		getContentPane().add(passwordField2, gbc_passwordField2);
		JLabel labelFechaNacimiento = new JLabel("Fecha de nacimiento (dd/mm/aaaa)");
		GridBagConstraints gbc_labelFechaNacimiento = new GridBagConstraints();
		gbc_labelFechaNacimiento.gridwidth = 4;
		gbc_labelFechaNacimiento.anchor = GridBagConstraints.SOUTHWEST;
		gbc_labelFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_labelFechaNacimiento.gridx = 1;
		gbc_labelFechaNacimiento.gridy = 10;
		getContentPane().add(labelFechaNacimiento, gbc_labelFechaNacimiento);
        
        boxIDia = new JComboBox<>( comboModelDia );
        
        GridBagConstraints gbc_boxIDia = new GridBagConstraints();
        gbc_boxIDia.insets = new Insets(0, 0, 5, 5);
        gbc_boxIDia.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxIDia.gridx = 1;
        gbc_boxIDia.gridy = 11;
        getContentPane().add(boxIDia, gbc_boxIDia);
        boxIMes = new JComboBox<>(comboModelMes);
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
        gbc_boxIMes.insets = new Insets(0, 0, 5, 5);
        gbc_boxIMes.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxIMes.gridx = 2;
        gbc_boxIMes.gridy = 11;
        getContentPane().add(boxIMes, gbc_boxIMes);
		
        inicioAnio = new JTextField();
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
        gbc_inicioAnio.insets = new Insets(0, 0, 5, 5);
        gbc_inicioAnio.gridx = 4;
        gbc_inicioAnio.gridy = 11;
        getContentPane().add(inicioAnio, gbc_inicioAnio);
		
		labelInstitucion = new JLabel("Nombre de Institucion");
		GridBagConstraints gbc_labelInstitucion = new GridBagConstraints();
		gbc_labelInstitucion.anchor = GridBagConstraints.WEST;
		gbc_labelInstitucion.gridwidth = 3;
		gbc_labelInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_labelInstitucion.gridx = 1;
		gbc_labelInstitucion.gridy = 12;
		getContentPane().add(labelInstitucion, gbc_labelInstitucion);
		
		comboBoxInstitucion = new JComboBox<String>();
		comboBoxInstitucion.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				comboBoxInstitucion.removeAllItems();
				comboBoxInstitucion.addItem("-");
				for(String ins:controlUsr.obtenerInstituciones())
					comboBoxInstitucion.addItem(ins);
			}
		});
		comboBoxInstitucion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
		});
		
		labelAclaracionProfesor1 = new JLabel("(Solo profesor)");
		GridBagConstraints gbc_labelAclaracionProfesor1 = new GridBagConstraints();
		gbc_labelAclaracionProfesor1.insets = new Insets(0, 0, 5, 5);
		gbc_labelAclaracionProfesor1.gridx = 6;
		gbc_labelAclaracionProfesor1.gridy = 12;
		getContentPane().add(labelAclaracionProfesor1, gbc_labelAclaracionProfesor1);
		comboBoxInstitucion.setModel(new DefaultComboBoxModel<String>(new String[] {"-"}));
		comboBoxInstitucion.setEnabled(false);
		
		GridBagConstraints gbc_comboBoxInstitucion = new GridBagConstraints();
		gbc_comboBoxInstitucion.gridwidth = 6;
		gbc_comboBoxInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxInstitucion.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInstitucion.gridx = 1;
		gbc_comboBoxInstitucion.gridy = 13;
		getContentPane().add(comboBoxInstitucion, gbc_comboBoxInstitucion);
		
		labelDescripcion = new JLabel("Descripcion");
		GridBagConstraints gbc_labelDescripcion = new GridBagConstraints();
		gbc_labelDescripcion.gridwidth = 2;
		gbc_labelDescripcion.anchor = GridBagConstraints.WEST;
		gbc_labelDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_labelDescripcion.gridx = 1;
		gbc_labelDescripcion.gridy = 14;
		getContentPane().add(labelDescripcion, gbc_labelDescripcion);
		
		labelAclaracionProfesor2 = new JLabel("(Solo profesor)");
		GridBagConstraints gbc_labelAclaracionProfesor2 = new GridBagConstraints();
		gbc_labelAclaracionProfesor2.insets = new Insets(0, 0, 5, 5);
		gbc_labelAclaracionProfesor2.gridx = 6;
		gbc_labelAclaracionProfesor2.gridy = 14;
		getContentPane().add(labelAclaracionProfesor2, gbc_labelAclaracionProfesor2);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 15;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		textAreaDescripcion = new JTextArea();
		scrollPane.setViewportView(textAreaDescripcion);
		textAreaDescripcion.setEnabled(false);
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		
		labelBiografia = new JLabel("Biografia (opcional)");
		GridBagConstraints gbc_labelBiografia = new GridBagConstraints();
		gbc_labelBiografia.gridwidth = 2;
		gbc_labelBiografia.anchor = GridBagConstraints.WEST;
		gbc_labelBiografia.insets = new Insets(0, 0, 5, 5);
		gbc_labelBiografia.gridx = 1;
		gbc_labelBiografia.gridy = 16;
		getContentPane().add(labelBiografia, gbc_labelBiografia);
		
		labelAclaracionProfesor3 = new JLabel("(Solo profesor)");
		GridBagConstraints gbc_labelAclaracionProfesor3 = new GridBagConstraints();
		gbc_labelAclaracionProfesor3.insets = new Insets(0, 0, 5, 5);
		gbc_labelAclaracionProfesor3.gridx = 6;
		gbc_labelAclaracionProfesor3.gridy = 16;
		getContentPane().add(labelAclaracionProfesor3, gbc_labelAclaracionProfesor3);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 6;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 17;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		textAreaBiografia = new JTextArea();
		scrollPane_1.setViewportView(textAreaBiografia);
		textAreaBiografia.setEnabled(false);
		textAreaBiografia.setLineWrap(true);
		textAreaBiografia.setWrapStyleWord(true);
		
		labelWebsite = new JLabel("Website (opcional)");
		GridBagConstraints gbc_labelWebsite = new GridBagConstraints();
		gbc_labelWebsite.gridwidth = 2;
		gbc_labelWebsite.anchor = GridBagConstraints.WEST;
		gbc_labelWebsite.insets = new Insets(0, 0, 5, 5);
		gbc_labelWebsite.gridx = 1;
		gbc_labelWebsite.gridy = 18;
		getContentPane().add(labelWebsite, gbc_labelWebsite);
		
		labelAclaracionProfesor4 = new JLabel("(Solo profesor)");
		GridBagConstraints gbc_labelAclaracionProfesor4 = new GridBagConstraints();
		gbc_labelAclaracionProfesor4.insets = new Insets(0, 0, 5, 5);
		gbc_labelAclaracionProfesor4.gridx = 6;
		gbc_labelAclaracionProfesor4.gridy = 18;
		getContentPane().add(labelAclaracionProfesor4, gbc_labelAclaracionProfesor4);
		
		textFieldWebsite = new JTextField();
		textFieldWebsite.setBackground(Color.WHITE);
		textFieldWebsite.setEnabled(false);
		GridBagConstraints gbc_textFieldWebsite = new GridBagConstraints();
		gbc_textFieldWebsite.gridwidth = 6;
		gbc_textFieldWebsite.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldWebsite.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWebsite.gridx = 1;
		gbc_textFieldWebsite.gridy = 19;
		getContentPane().add(textFieldWebsite, gbc_textFieldWebsite);
		textFieldWebsite.setColumns(10);
		
		JButton btnCancelar = new JButton("Limpiar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tomarDatos()==0)
					clear();
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.anchor = GridBagConstraints.NORTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 5;
		gbc_btnAceptar.gridy = 21;
		getContentPane().add(btnAceptar, gbc_btnAceptar);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.anchor = GridBagConstraints.NORTH;
		gbc_btnCancelar.gridx = 6;
		gbc_btnCancelar.gridy = 21;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		

	}
	
	/*
	 * Se encarga de limpiar datos ingresados por el usuario
	 */
	public void clear() {
		comboBoxTipoDeUsuario.setSelectedIndex(0);
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldNickname.setText("");
        textFieldEmail.setText("");
    	boxIDia.setSelectedIndex(0);
    	inicioAnio.setText("");
    	passwordField1.setText("");
    	passwordField2.setText("");
    	boxIMes.setSelectedIndex(0);
    	textAreaDescripcion.setText("");
    	textAreaBiografia.setText("");
    	textFieldWebsite.setText("");
    	comboBoxInstitucion.setSelectedIndex(0);
    }
	
	/*
	 * En caso de ser los datos validos, esta funcion se encarga de ejecutar el caso de uso correspondiente
	 */
	private int tomarDatos() {
		try {
			String tipoU;
			String nicknameU;
			String nombreU;
	        String apellidoU;
	        String emailU;
	        int diaU;
	        int mesU;
	        int anioU;
	        String institutoU;
	        String descripcionU;
	        String biografiaU;
	        String websiteU;
	        
	        if(!checkFormulario())
	        	return 1;
			tipoU = this.comboBoxTipoDeUsuario.getSelectedItem().toString().trim();
			nicknameU = this.textFieldNickname.getText().trim();
			nombreU = this.textFieldNombre.getText().trim();
	        apellidoU = this.textFieldApellido.getText().trim();
	        emailU = this.textFieldEmail.getText().trim();
	        diaU = boxIDia.getSelectedIndex();
	        mesU = boxIMes.getSelectedIndex();
	        anioU = Integer.parseInt(inicioAnio.getText().trim());
	        institutoU = this.comboBoxInstitucion.getSelectedItem().toString().trim();
	        descripcionU = this.textAreaDescripcion.getText().trim();
	        biografiaU = this.textAreaBiografia.getText().trim();
	        websiteU = this.textFieldWebsite.getText().trim();
	        String contrasenia = passwordField1.getPassword().toString().trim();
	        
			/*
			 * Crea el tipo de dato segun el tipo de usuario seleccionado
			 */
			DtUsuario datosUser;
			if(tipoU == "Profesor")
				datosUser = new DtProfesor(nicknameU,nombreU,apellidoU,emailU, contrasenia, new DtFecha(anioU,mesU,diaU,0,0,0),institutoU, descripcionU,biografiaU,websiteU,null);
			else //Se asume que si no es profesor es socio
				datosUser = new DtSocio(nicknameU,nombreU,apellidoU,emailU, contrasenia, new DtFecha(anioU,mesU,diaU,0,0,0),null);
			if(controlUsr.ingresarDatosUsuario(datosUser) != 0) {
				JOptionPane.showMessageDialog(this, "Ya existe un usuario con los datos ingresados.", this.getTitle(), JOptionPane.ERROR_MESSAGE);
				return 1;
			} else {
				JOptionPane.showMessageDialog(this, "El usuario ha sido registrado de forma exitosa.", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				return 0;
			}
		} catch (InstitucionException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
	        return 0;
		}
	}
	
	/*
	 * Valida los datos ingresados por el usuario
	 */
	private boolean checkFormulario() {
		String tipoU  = this.comboBoxTipoDeUsuario.getSelectedItem().toString().trim();
		String nicknameU = this.textFieldNickname.getText().trim();
		String nombreU = this.textFieldNombre.getText().trim();
        String apellidoU = this.textFieldApellido.getText().trim();
        String emailU = this.textFieldEmail.getText().trim();
        int diaU = boxIDia.getSelectedIndex();
        int mesU = this.boxIMes.getSelectedIndex();
        String anioU = inicioAnio.getText().trim();
        String institutoU = this.comboBoxInstitucion.getSelectedItem().toString().trim();
        String descripcionU = this.textAreaDescripcion.getText().trim();
        String contrasenia1 = String.valueOf(passwordField1.getPassword());
        String contrasenia2 = String.valueOf(passwordField2.getPassword());

        //Celdas vacias
        if (tipoU == "-" || nicknameU.isEmpty() || nombreU.isEmpty() || apellidoU.isEmpty() || emailU.isEmpty() || diaU < 1 || mesU < 1 || anioU.isEmpty() || ((tipoU == "Profesor") && (institutoU == "-" || descripcionU.isEmpty()))) {
            JOptionPane.showMessageDialog(this, "Exisisten campos obligatorios vacios/sin seleccionar.", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
      //Numeros no son numeros
        try {
            Integer.parseInt(anioU);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La fecha de ingresada no es valida", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //Contraseñas no coinciden
        if (!contrasenia1.equals(contrasenia2)) {
        	JOptionPane.showMessageDialog(this, "Las contraseñas ingresadas son distintas", this.getTitle(), JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        //Contraseña no segura
        if (contrasenia1.equals("")) {
        	JOptionPane.showMessageDialog(this, "La contraseña debe tener por lo menos 1 bit de entropía.", this.getTitle(), JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        return true;
	}
}
