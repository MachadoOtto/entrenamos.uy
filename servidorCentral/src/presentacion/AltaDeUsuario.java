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

public class AltaDeUsuario extends JInternalFrame {

	Set<String> instituciones;
	private IUsuarioController controlUsr;
	private JTextField textFieldNickname;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JTextField textFieldDia;
	private JComboBox comboBoxMes;
	private JTextField textFieldAnio;
	private JComboBox comboBoxTipoDeUsuario;
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
	private JComboBox comboBoxInstitucion;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;
	private JScrollPane scrollPane_1;
	private JLabel labelAclaracionFecha;
	
	public AltaDeUsuario(IUsuarioController controlUsr) {
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
		int iframeWidth = 480;
		int iframeHeight = 625;
		int gridWidth = iframeWidth/columns;
		int gridHeight = iframeHeight/rows;
		setBounds(100, 25, iframeWidth, iframeHeight); // w,h
		
		setTitle("Alta de usuario");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {60, 60, 60, 60, 60, 60, 60, 60};
		gridBagLayout.rowHeights = new int[]{25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 75, 25, 75, 25, 25, 25, 25};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		labelTipoDeUsuario = new JLabel("Tipo de usuario");
		GridBagConstraints gbc_labelTipoDeUsuario = new GridBagConstraints();
		gbc_labelTipoDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_labelTipoDeUsuario.anchor = GridBagConstraints.EAST;
		gbc_labelTipoDeUsuario.gridx = 5;
		gbc_labelTipoDeUsuario.gridy = 0;
		getContentPane().add(labelTipoDeUsuario, gbc_labelTipoDeUsuario);
		
		comboBoxTipoDeUsuario = new JComboBox();
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
				
				//Carga instituciones al comboBox en caso de no haber sido cargadas antes
				if(instituciones.isEmpty()) {
					instituciones = controlUsr.obtenerInstituciones();
					for(String ins:instituciones) {
						comboBoxInstitucion.addItem(ins);
					}
				}
			}
		});
		comboBoxTipoDeUsuario.setModel(new DefaultComboBoxModel(new String[] {"-", "Socio", "Profesor"}));
		GridBagConstraints gbc_comboBoxTipoDeUsuario = new GridBagConstraints();
		gbc_comboBoxTipoDeUsuario.anchor = GridBagConstraints.SOUTH;
		gbc_comboBoxTipoDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTipoDeUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipoDeUsuario.gridx = 6;
		gbc_comboBoxTipoDeUsuario.gridy = 0;
		getContentPane().add(comboBoxTipoDeUsuario, gbc_comboBoxTipoDeUsuario);
		
		JLabel labelNickname = new JLabel("Nickname");
		GridBagConstraints gbc_labelNickname = new GridBagConstraints();
		gbc_labelNickname.gridwidth = 2;
		gbc_labelNickname.anchor = GridBagConstraints.SOUTH;
		gbc_labelNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelNickname.insets = new Insets(0, 0, 5, 5);
		gbc_labelNickname.gridx = 1;
		gbc_labelNickname.gridy = 1;
		getContentPane().add(labelNickname, gbc_labelNickname);
		
		textFieldNickname = new JTextField();
		GridBagConstraints gbc_textFieldNickname = new GridBagConstraints();
		gbc_textFieldNickname.gridwidth = 6;
		gbc_textFieldNickname.fill = GridBagConstraints.BOTH;
		gbc_textFieldNickname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNickname.gridx = 1;
		gbc_textFieldNickname.gridy = 2;
		getContentPane().add(textFieldNickname, gbc_textFieldNickname);
		textFieldNickname.setColumns(10);
		
		JLabel labelNombre = new JLabel("Nombre");
		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.gridwidth = 2;
		gbc_labelNombre.anchor = GridBagConstraints.SOUTH;
		gbc_labelNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombre.gridx = 1;
		gbc_labelNombre.gridy = 3;
		getContentPane().add(labelNombre, gbc_labelNombre);
		
		JLabel labelApellido = new JLabel("Apellido");
		GridBagConstraints gbc_labelApellido = new GridBagConstraints();
		gbc_labelApellido.anchor = GridBagConstraints.SOUTH;
		gbc_labelApellido.insets = new Insets(0, 0, 5, 5);
		gbc_labelApellido.gridx = 4;
		gbc_labelApellido.gridy = 3;
		getContentPane().add(labelApellido, gbc_labelApellido);
		
		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 3;
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 4;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.gridwidth = 3;
		gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellido.gridx = 4;
		gbc_textFieldApellido.gridy = 4;
		getContentPane().add(textFieldApellido, gbc_textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel labelEmail = new JLabel("Correo electronico");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.gridwidth = 2;
		gbc_labelEmail.anchor = GridBagConstraints.SOUTHWEST;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 1;
		gbc_labelEmail.gridy = 5;
		getContentPane().add(labelEmail, gbc_labelEmail);
		
		textFieldEmail = new JTextField();
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.gridwidth = 6;
		gbc_textFieldEmail.fill = GridBagConstraints.BOTH;
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 6;
		getContentPane().add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel labelFechaNacimiento = new JLabel("Fecha de nacimiento");
		GridBagConstraints gbc_labelFechaNacimiento = new GridBagConstraints();
		gbc_labelFechaNacimiento.gridwidth = 2;
		gbc_labelFechaNacimiento.anchor = GridBagConstraints.SOUTHWEST;
		gbc_labelFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_labelFechaNacimiento.gridx = 1;
		gbc_labelFechaNacimiento.gridy = 7;
		getContentPane().add(labelFechaNacimiento, gbc_labelFechaNacimiento);
		
		labelAclaracionFecha = new JLabel("(dd/mm/aaaa)");
		GridBagConstraints gbc_labelAclaracionFecha = new GridBagConstraints();
		gbc_labelAclaracionFecha.insets = new Insets(0, 0, 5, 5);
		gbc_labelAclaracionFecha.gridx = 6;
		gbc_labelAclaracionFecha.gridy = 7;
		getContentPane().add(labelAclaracionFecha, gbc_labelAclaracionFecha);
		
		textFieldDia = new JTextField();
		GridBagConstraints gbc_textFieldDia = new GridBagConstraints();
		gbc_textFieldDia.gridwidth = 2;
		gbc_textFieldDia.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDia.gridx = 1;
		gbc_textFieldDia.gridy = 8;
		getContentPane().add(textFieldDia, gbc_textFieldDia);
		textFieldDia.setColumns(10);
		
		comboBoxMes = new JComboBox();
		comboBoxMes.setModel(new DefaultComboBoxModel(new String[] {"-","Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		GridBagConstraints gbc_comboBoxMes = new GridBagConstraints();
		gbc_comboBoxMes.gridwidth = 2;
		gbc_comboBoxMes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxMes.gridx = 3;
		gbc_comboBoxMes.gridy = 8;
		getContentPane().add(comboBoxMes, gbc_comboBoxMes);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tomarDatos();
				limpiarFormulario();
				setVisible(false);
			}
		});
		
		textFieldAnio = new JTextField();
		GridBagConstraints gbc_textFieldAnio = new GridBagConstraints();
		gbc_textFieldAnio.gridwidth = 2;
		gbc_textFieldAnio.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAnio.gridx = 5;
		gbc_textFieldAnio.gridy = 8;
		getContentPane().add(textFieldAnio, gbc_textFieldAnio);
		textFieldAnio.setColumns(10);
		
		labelInstitucion = new JLabel("Nombre de Institucion");
		GridBagConstraints gbc_labelInstitucion = new GridBagConstraints();
		gbc_labelInstitucion.anchor = GridBagConstraints.WEST;
		gbc_labelInstitucion.gridwidth = 2;
		gbc_labelInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_labelInstitucion.gridx = 1;
		gbc_labelInstitucion.gridy = 9;
		getContentPane().add(labelInstitucion, gbc_labelInstitucion);
		
		labelAclaracionProfesor1 = new JLabel("(Solo profesor)");
		GridBagConstraints gbc_labelAclaracionProfesor1 = new GridBagConstraints();
		gbc_labelAclaracionProfesor1.insets = new Insets(0, 0, 5, 5);
		gbc_labelAclaracionProfesor1.gridx = 6;
		gbc_labelAclaracionProfesor1.gridy = 9;
		getContentPane().add(labelAclaracionProfesor1, gbc_labelAclaracionProfesor1);
		
		comboBoxInstitucion = new JComboBox();
		comboBoxInstitucion.setModel(new DefaultComboBoxModel(new String[] {"-"}));
		comboBoxInstitucion.setEnabled(false);
		
		GridBagConstraints gbc_comboBoxInstitucion = new GridBagConstraints();
		gbc_comboBoxInstitucion.gridwidth = 6;
		gbc_comboBoxInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxInstitucion.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInstitucion.gridx = 1;
		gbc_comboBoxInstitucion.gridy = 10;
		getContentPane().add(comboBoxInstitucion, gbc_comboBoxInstitucion);
		
		labelDescripcion = new JLabel("Descripcion");
		GridBagConstraints gbc_labelDescripcion = new GridBagConstraints();
		gbc_labelDescripcion.gridwidth = 2;
		gbc_labelDescripcion.anchor = GridBagConstraints.WEST;
		gbc_labelDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_labelDescripcion.gridx = 1;
		gbc_labelDescripcion.gridy = 11;
		getContentPane().add(labelDescripcion, gbc_labelDescripcion);
		
		labelAclaracionProfesor2 = new JLabel("(Solo profesor)");
		GridBagConstraints gbc_labelAclaracionProfesor2 = new GridBagConstraints();
		gbc_labelAclaracionProfesor2.insets = new Insets(0, 0, 5, 5);
		gbc_labelAclaracionProfesor2.gridx = 6;
		gbc_labelAclaracionProfesor2.gridy = 11;
		getContentPane().add(labelAclaracionProfesor2, gbc_labelAclaracionProfesor2);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 12;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setEnabled(false);
		scrollPane.setViewportView(textAreaDescripcion);
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		
		labelBiografia = new JLabel("Biografia (opcional)");
		GridBagConstraints gbc_labelBiografia = new GridBagConstraints();
		gbc_labelBiografia.gridwidth = 2;
		gbc_labelBiografia.anchor = GridBagConstraints.WEST;
		gbc_labelBiografia.insets = new Insets(0, 0, 5, 5);
		gbc_labelBiografia.gridx = 1;
		gbc_labelBiografia.gridy = 13;
		getContentPane().add(labelBiografia, gbc_labelBiografia);
		
		labelAclaracionProfesor3 = new JLabel("(Solo profesor)");
		GridBagConstraints gbc_labelAclaracionProfesor3 = new GridBagConstraints();
		gbc_labelAclaracionProfesor3.insets = new Insets(0, 0, 5, 5);
		gbc_labelAclaracionProfesor3.gridx = 6;
		gbc_labelAclaracionProfesor3.gridy = 13;
		getContentPane().add(labelAclaracionProfesor3, gbc_labelAclaracionProfesor3);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 6;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 14;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		textAreaBiografia = new JTextArea();
		textAreaBiografia.setEnabled(false);
		scrollPane_1.setViewportView(textAreaBiografia);
		textAreaBiografia.setLineWrap(true);
		textAreaBiografia.setWrapStyleWord(true);
		
		labelWebsite = new JLabel("Website (opcional)");
		GridBagConstraints gbc_labelWebsite = new GridBagConstraints();
		gbc_labelWebsite.gridwidth = 2;
		gbc_labelWebsite.anchor = GridBagConstraints.WEST;
		gbc_labelWebsite.insets = new Insets(0, 0, 5, 5);
		gbc_labelWebsite.gridx = 1;
		gbc_labelWebsite.gridy = 15;
		getContentPane().add(labelWebsite, gbc_labelWebsite);
		
		labelAclaracionProfesor4 = new JLabel("(Solo profesor)");
		GridBagConstraints gbc_labelAclaracionProfesor4 = new GridBagConstraints();
		gbc_labelAclaracionProfesor4.insets = new Insets(0, 0, 5, 5);
		gbc_labelAclaracionProfesor4.gridx = 6;
		gbc_labelAclaracionProfesor4.gridy = 15;
		getContentPane().add(labelAclaracionProfesor4, gbc_labelAclaracionProfesor4);
		
		textFieldWebsite = new JTextField();
		textFieldWebsite.setEnabled(false);
		GridBagConstraints gbc_textFieldWebsite = new GridBagConstraints();
		gbc_textFieldWebsite.gridwidth = 6;
		gbc_textFieldWebsite.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldWebsite.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWebsite.gridx = 1;
		gbc_textFieldWebsite.gridy = 16;
		getContentPane().add(textFieldWebsite, gbc_textFieldWebsite);
		textFieldWebsite.setColumns(10);
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.anchor = GridBagConstraints.NORTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 5;
		gbc_btnAceptar.gridy = 18;
		getContentPane().add(btnAceptar, gbc_btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.NORTH;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 6;
		gbc_btnCancelar.gridy = 18;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		

	}
	
	/*
	 * Se encarga de limpiar datos ingresados por el usuario
	 */
	private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldNickname.setText("");
        textFieldEmail.setText("");
    	textFieldDia.setText("");
    	textFieldAnio.setText("");
    	comboBoxMes.setSelectedIndex(0);
    	instituciones.clear();
    }
	
	/*
	 * En caso de ser los datos validos, esta funcion se encarga de ejecutar el caso de uso correspondiente
	 */
	private void tomarDatos() {
		
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
        
        if (checkFormulario())
        {
        	while(this.isVisible()) 
        	{
        		nicknameU = this.textFieldNickname.getText();
        		nombreU = this.textFieldNombre.getText();
                apellidoU = this.textFieldApellido.getText();
                emailU = this.textFieldEmail.getText();
                diaU = Integer.parseInt(textFieldDia.getText());
                mesU = this.comboBoxMes.getSelectedIndex();
                anioU = Integer.parseInt(textFieldAnio.getText());
                institutoU = this.comboBoxInstitucion.getSelectedItem().toString();
                descripcionU = this.textAreaDescripcion.getSelectedText();
                biografiaU = this.textAreaBiografia.getSelectedText();
                websiteU = this.textFieldWebsite.getSelectedText();
                
        		/*
        		 * Crea el tipo de dato segun el tipo de usuario seleccionado
        		 */
        		DtUsuario datosUser;
        		if(this.comboBoxTipoDeUsuario.getSelectedItem().toString() == "Profesor") {
        			datosUser = new DtProfesor(nicknameU,nombreU,apellidoU,emailU, new DtFecha(diaU,mesU,anioU,0,0,0),institutoU, descripcionU,biografiaU,websiteU);
        		}
        		else //Se asume que si no es profesor es socio
        		{
        			datosUser = new DtSocio(nicknameU,nombreU,apellidoU,emailU, new DtFecha(diaU,mesU,anioU,0,0,0));
        		}
        		
        		int variableControl = this.controlUsr.ingresarDatosUsuario(datosUser);
        		String mensajeResultado = "";
        		if(variableControl == 0)
        		{
        			mensajeResultado = "El usuario " + nicknameU + " ha sido registrado con exito";
        			break;
        		}
        		else if(variableControl == 1)
        		{
        			mensajeResultado = "El nickname del usuario ingresado ya existe";
        		}
        		JOptionPane.showMessageDialog(this, mensajeResultado, this.getTitle(), JOptionPane.ERROR_MESSAGE);
        	}
        }
        
	}
	
	/*
	 * Valida los datos ingresados por el usuario
	 */
	private boolean checkFormulario() {
		int tipoDeUsuarioU = this.comboBoxTipoDeUsuario.getSelectedIndex();
		String nicknameU = this.textFieldNickname.getText();
		String nombreU = this.textFieldNombre.getText();
        String apellidoU = this.textFieldApellido.getText();
        String emailU = this.textFieldEmail.getText();
        String diaU = textFieldDia.getText();
        int mesU = this.comboBoxMes.getSelectedIndex();
        String anioU = textFieldAnio.getText();
        String institutoU = this.comboBoxInstitucion.getSelectedItem().toString();
        String websiteU = this.textFieldWebsite.getSelectedText();

        //Celdas vacias
        if (tipoDeUsuarioU == 0 || nicknameU.isEmpty() || nombreU.isEmpty() || apellidoU.isEmpty() || emailU.isEmpty() || diaU.isEmpty() || mesU == 0 || anioU.isEmpty() || institutoU == "-" || websiteU.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacios", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
      //Numeros no son numeros
        try {
            int numDiaUInteger = Integer.parseInt(diaU);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El dia ingresado debe ser un numero", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int numAnioU = Integer.parseInt(anioU);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El anio ingresado debe ser un numero", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //Fechas inexistentes
        int numAnioU = Integer.parseInt(anioU);
        if (numAnioU < 1900 || numAnioU > 2021) {
        	JOptionPane.showMessageDialog(this, "El anio ingresado debe ser valido", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int numDiaU = Integer.parseInt(diaU);
        if (numDiaU < 1 || numDiaU > 31) {
        	JOptionPane.showMessageDialog(this, "El dia ingresado debe ser valido", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}
