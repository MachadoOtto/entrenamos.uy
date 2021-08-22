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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logica.IDictadoClaseController;

public class AltaDictadoClase extends JInternalFrame {
	
	/* Controlador de Dictado de Clase para las acciones del JInternalFrame */
	private IDictadoClaseController controlClase;
	
	/* Componentes graficas */
	// JLabel:
	private JLabel lblIngreseNombre;
	private JLabel lblIngreseSocios;
	private JLabel lblIngreseFechaI;
	private JLabel lblIngreseHoraI;
	private JLabel lblSeleccionInstitucion;
	private JLabel lblSeleccionActividad;
	private JLabel lblSeleccionProfesor;
	private JLabel lblIngreseUrl;
	private JLabel lblIngreseFechaR;
	private JLabel lblIngreseHoraR;
	
	// JTextField:
	private JTextField nombreClase; // Es unico.
	private JTextField sociosMin;
	private JTextField sociosMax;
	private JTextField inicioAnio;
	private JTextField registroAnio;
	private JTextField url;
	
	// JComboBox:
	private JComboBox<String> boxInstitucion;
	private JComboBox<String> boxActividad;
	private JComboBox<String> boxProfesor;
	// Seleccion de Fecha de Inicio:
	private JComboBox<Integer> boxIDia; // Depende de mes;
	private JComboBox<Integer> boxIMes;
	private JComboBox<Integer> boxIHora;
	private JComboBox<Integer> boxIMinuto;
	// Seleccion de Fecha de Registro (de alta):
	private JComboBox<Integer> boxRDia; // Depende de mes;
	private JComboBox<Integer> boxRMes;
	private JComboBox<Integer> boxRHora;
	private JComboBox<Integer> boxRMinuto;
	
	// JButton:
	private JButton btnAceptar;
    private JButton btnCancelar;
    
    /* Crear frame */
	public AltaDictadoClase(IDictadoClaseController idcc) {
		// Inicializa controlador Dictado de Clase:
		controlClase = idcc;
		
		// Propiedades del JInternalFrame:
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de Dictado de Clase");
		setBounds(10, 40, 500, 540);
		
		// GridLayout:
		GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[] { 30, 60, 60, 60, 60, 30, 60, 60, 15, 15, 0 };
	    gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30,
	    		0, 0 };
	    gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
	    gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
	    		0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	    getContentPane().setLayout(gridBagLayout);
        
        // JLabels:
        lblIngreseNombre = new JLabel("Nombre de Clase:");
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
        gbc_lblIngreseNombre.gridwidth = 4;
        gbc_lblIngreseNombre.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseNombre.gridx = 1;
        gbc_lblIngreseNombre.gridy = 0;
        getContentPane().add(lblIngreseNombre, gbc_lblIngreseNombre);
        
        lblIngreseSocios = new JLabel("Cantidad Socios:");
        lblIngreseSocios.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblIngreseSocios = new GridBagConstraints();
        gbc_lblIngreseSocios.gridwidth = 2;
        gbc_lblIngreseSocios.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseSocios.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseSocios.gridx = 6;
        gbc_lblIngreseSocios.gridy = 0;
        getContentPane().add(lblIngreseSocios, gbc_lblIngreseSocios);
        
        lblIngreseFechaI = new JLabel("Fecha de Inicio de Clase:");
        lblIngreseFechaI.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblIngreseFechaI = new GridBagConstraints();
        gbc_lblIngreseFechaI.gridwidth = 4;
        gbc_lblIngreseFechaI.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseFechaI.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseFechaI.gridx = 1;
        gbc_lblIngreseFechaI.gridy = 2;
        getContentPane().add(lblIngreseFechaI, gbc_lblIngreseFechaI);
        
        lblIngreseHoraI = new JLabel("Hora de Inicio:");
        lblIngreseHoraI.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblIngreseHoraI = new GridBagConstraints();
        gbc_lblIngreseHoraI.gridwidth = 2;
        gbc_lblIngreseHoraI.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseHoraI.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseHoraI.gridx = 6;
        gbc_lblIngreseHoraI.gridy = 2;
        getContentPane().add(lblIngreseHoraI, gbc_lblIngreseHoraI);
        
        lblSeleccionInstitucion = new JLabel("Seleccione Institucion:");
        lblSeleccionInstitucion.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionInstitucion = new GridBagConstraints();
        gbc_lblSeleccionInstitucion.gridwidth = 4;
        gbc_lblSeleccionInstitucion.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionInstitucion.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionInstitucion.gridx = 1;
        gbc_lblSeleccionInstitucion.gridy = 4;
        getContentPane().add(lblSeleccionInstitucion, gbc_lblSeleccionInstitucion);
        
        lblSeleccionActividad = new JLabel("Seleccione Actividad Deportiva:");
        lblSeleccionActividad.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionActividad = new GridBagConstraints();
        gbc_lblSeleccionActividad.gridwidth = 4;
        gbc_lblSeleccionActividad.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionActividad.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionActividad.gridx = 1;
        gbc_lblSeleccionActividad.gridy = 6;
        getContentPane().add(lblSeleccionActividad, gbc_lblSeleccionActividad);
        
        lblSeleccionProfesor = new JLabel("Seleccione Profesor:");
        lblSeleccionProfesor.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionProfesor = new GridBagConstraints();
        gbc_lblSeleccionProfesor.gridwidth = 4;
        gbc_lblSeleccionProfesor.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionProfesor.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionProfesor.gridx = 1;
        gbc_lblSeleccionProfesor.gridy = 8;
        getContentPane().add(lblSeleccionProfesor, gbc_lblSeleccionProfesor);
        
        lblIngreseUrl = new JLabel("URL de la Clase:");
        lblIngreseUrl.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblIngreseUrl = new GridBagConstraints();
        gbc_lblIngreseUrl.gridwidth = 4;
        gbc_lblIngreseUrl.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseUrl.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseUrl.gridx = 1;
        gbc_lblIngreseUrl.gridy = 10;
        getContentPane().add(lblIngreseUrl, gbc_lblIngreseUrl);
        
        lblIngreseFechaR = new JLabel("Fecha de Registro de Clase:");
        lblIngreseFechaR.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblIngreseFechaR = new GridBagConstraints();
        gbc_lblIngreseFechaR.gridwidth = 4;
        gbc_lblIngreseFechaR.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseFechaR.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseFechaR.gridx = 1;
        gbc_lblIngreseFechaR.gridy = 12;
        getContentPane().add(lblIngreseFechaR, gbc_lblIngreseFechaR);
        
        lblIngreseHoraR = new JLabel("Hora de Registro:");
        lblIngreseHoraR.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblIngreseHoraR = new GridBagConstraints();
        gbc_lblIngreseHoraR.gridwidth = 2;
        gbc_lblIngreseHoraR.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseHoraR.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseHoraR.gridx = 6;
        gbc_lblIngreseHoraR.gridy = 12;
        getContentPane().add(lblIngreseHoraR, gbc_lblIngreseHoraR);
        
        // JTextField:
        nombreClase = new JTextField();
        lblIngreseNombre.setLabelFor(nombreClase);
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.gridwidth = 4;
        gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 1;
        getContentPane().add(nombreClase, gbc_textFieldNombre);
        nombreClase.setColumns(10);
        
        sociosMin = new JTextField();
        GridBagConstraints gbc_sociosMin = new GridBagConstraints();
        gbc_sociosMin.gridwidth = 1;
        gbc_sociosMin.fill = GridBagConstraints.BOTH;
        gbc_sociosMin.insets = new Insets(0, 0, 5, 5);
        gbc_sociosMin.gridx = 6;
        gbc_sociosMin.gridy = 1;
        getContentPane().add(sociosMin, gbc_sociosMin);
        
        sociosMax = new JTextField();
        GridBagConstraints gbc_sociosMax = new GridBagConstraints();
        gbc_sociosMax.gridwidth = 1;
        gbc_sociosMax.fill = GridBagConstraints.BOTH;
        gbc_sociosMax.insets = new Insets(0, 0, 5, 5);
        gbc_sociosMax.gridx = 7;
        gbc_sociosMax.gridy = 1;
        getContentPane().add(sociosMax, gbc_sociosMax);
        
        inicioAnio = new JTextField();
        GridBagConstraints gbc_inicioAnio = new GridBagConstraints();
        gbc_inicioAnio.gridwidth = 1;
        gbc_inicioAnio.fill = GridBagConstraints.BOTH;
        gbc_inicioAnio.insets = new Insets(0, 0, 5, 5);
        gbc_inicioAnio.gridx = 4;
        gbc_inicioAnio.gridy = 3;
        getContentPane().add(inicioAnio, gbc_inicioAnio);
        
        url = new JTextField();
        lblIngreseUrl.setLabelFor(url);
        GridBagConstraints gbc_textFieldUrl = new GridBagConstraints();
        gbc_textFieldUrl.gridwidth = 4;
        gbc_textFieldUrl.fill = GridBagConstraints.BOTH;
        gbc_textFieldUrl.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldUrl.gridx = 1;
        gbc_textFieldUrl.gridy = 11;
        getContentPane().add(url, gbc_textFieldUrl);
        url.setColumns(10);
        
        registroAnio = new JTextField();
        GridBagConstraints gbc_registroAnio = new GridBagConstraints();
        gbc_registroAnio.gridwidth = 1;
        gbc_registroAnio.fill = GridBagConstraints.BOTH;
        gbc_registroAnio.insets = new Insets(0, 0, 5, 5);
        gbc_registroAnio.gridx = 4;
        gbc_registroAnio.gridy = 13;
        getContentPane().add(registroAnio, gbc_registroAnio);
        
        // JComboBox:
        boxIDia = new JComboBox<>();
        GridBagConstraints gbc_boxIDia = new GridBagConstraints();
        gbc_boxIDia.insets = new Insets(0, 0, 5, 5);
        gbc_boxIDia.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxIDia.gridx = 1;
        gbc_boxIDia.gridy = 3;
        getContentPane().add(boxIDia, gbc_boxIDia);
        
        boxIMes = new JComboBox<>();
        GridBagConstraints gbc_boxIMes = new GridBagConstraints();
        gbc_boxIMes.gridwidth = 2;
        gbc_boxIMes.insets = new Insets(0, 0, 5, 5);
        gbc_boxIMes.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxIMes.gridx = 2;
        gbc_boxIMes.gridy = 3;
        getContentPane().add(boxIMes, gbc_boxIMes);
        
        boxIHora = new JComboBox<>();
        GridBagConstraints gbc_boxIHora = new GridBagConstraints();
        gbc_boxIHora.insets = new Insets(0, 0, 5, 5);
        gbc_boxIHora.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxIHora.gridx = 6;
        gbc_boxIHora.gridy = 3;
        getContentPane().add(boxIHora, gbc_boxIHora);
        
        boxIMinuto = new JComboBox<>();
        GridBagConstraints gbc_boxIMinuto = new GridBagConstraints();
        gbc_boxIMinuto.insets = new Insets(0, 0, 5, 5);
        gbc_boxIMinuto.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxIMinuto.gridx = 7;
        gbc_boxIMinuto.gridy = 3;
        getContentPane().add(boxIMinuto, gbc_boxIMinuto);
        
        boxInstitucion = new JComboBox<>();
        GridBagConstraints gbc_boxInstitucion = new GridBagConstraints();
        gbc_boxInstitucion.gridwidth = 4;
        gbc_boxInstitucion.insets = new Insets(0, 0, 5, 5);
        gbc_boxInstitucion.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxInstitucion.gridx = 1;
        gbc_boxInstitucion.gridy = 5;
        getContentPane().add(boxInstitucion, gbc_boxInstitucion);
        
        boxActividad = new JComboBox<>();
        boxActividad.setEnabled(false);
        GridBagConstraints gbc_boxActividad = new GridBagConstraints();
        gbc_boxActividad.gridwidth = 4;
        gbc_boxActividad.insets = new Insets(0, 0, 5, 5);
        gbc_boxActividad.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxActividad.gridx = 1;
        gbc_boxActividad.gridy = 7;
        getContentPane().add(boxActividad, gbc_boxActividad);
        
        boxProfesor = new JComboBox<>();
        GridBagConstraints gbc_boxProfesor = new GridBagConstraints();
        gbc_boxProfesor.gridwidth = 4;
        gbc_boxProfesor.insets = new Insets(0, 0, 5, 5);
        gbc_boxProfesor.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxProfesor.gridx = 1;
        gbc_boxProfesor.gridy = 9;
        getContentPane().add(boxProfesor, gbc_boxProfesor);
        boxProfesor.setEnabled(false);
        
        boxRDia = new JComboBox<>();
        GridBagConstraints gbc_boxRDia = new GridBagConstraints();
        gbc_boxRDia.insets = new Insets(0, 0, 5, 5);
        gbc_boxRDia.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxRDia.gridx = 1;
        gbc_boxRDia.gridy = 13;
        getContentPane().add(boxRDia, gbc_boxRDia);
        
        boxRMes = new JComboBox<>();
        GridBagConstraints gbc_boxRMes = new GridBagConstraints();
        gbc_boxRMes.gridwidth = 2;
        gbc_boxRMes.insets = new Insets(0, 0, 5, 5);
        gbc_boxRMes.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxRMes.gridx = 2;
        gbc_boxRMes.gridy = 13;
        getContentPane().add(boxRMes, gbc_boxRMes);
                
        boxRHora = new JComboBox<>();
        GridBagConstraints gbc_boxRHora = new GridBagConstraints();
        gbc_boxRHora.insets = new Insets(0, 0, 5, 5);
        gbc_boxRHora.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxRHora.gridx = 6;
        gbc_boxRHora.gridy = 13;
        getContentPane().add(boxRHora, gbc_boxRHora);
        
        boxRMinuto = new JComboBox<>();
        GridBagConstraints gbc_boxRMinuto = new GridBagConstraints();
        gbc_boxRMinuto.insets = new Insets(0, 0, 5, 5);
        gbc_boxRMinuto.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxRMinuto.gridx = 7;
        gbc_boxRMinuto.gridy = 13;
        getContentPane().add(boxRMinuto, gbc_boxRMinuto);  
        
        // JButton:
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // cmdRegistroUsuarioActionPerformed(arg0);
            }
        });
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.gridwidth = 2;
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
        gbc_btnAceptar.gridx = 5;
        gbc_btnAceptar.gridy = 15;
        getContentPane().add(btnAceptar, gbc_btnAceptar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // limpiarFormulario();
                // setVisible(false);
            }
        });    
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.gridwidth = 2;
        gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.gridx = 7;
        gbc_btnCancelar.gridy = 15;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
        
	}
}

/*	// Este método es invocado al querer registrar un usuario, funcionalidad
    // provista por la operación del sistem registrarUsuario().
    // Previamente se hace una verificación de los campos, particularmente que no sean vacíos
    // y que la cédula sea un número. 
    // Tanto en caso de que haya un error (de verificación o de registro) o no, se despliega
    // un mensaje utilizando un panel de mensaje (JOptionPane).
    protected void cmdRegistroUsuarioActionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

        // Obtengo datos de los controles Swing
        String nombreU = this.textFieldNombre.getText();
        String apellidoU = this.textFieldApellido.getText();
        String ciU = this.textFieldCI.getText();

        if (checkFormulario()) {
            try {
                controlUsr.registrarUsuario(nombreU, apellidoU, ciU);

                // Muestro éxito de la operación
                JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito", "Registrar Usuario",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (UsuarioRepetidoException e) {
                // Muestro error de registro
                JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
            }

            // Limpio el internal frame antes de cerrar la ventana
            limpiarFormulario();
            setVisible(false);
        }
    }

    // Permite validar la información introducida en los campos e indicar
    // a través de un mensaje de error (JOptionPane) cuando algo sucede.
    // Este tipo de chequeos se puede realizar de otras formas y con otras librerías de Java, 
    // por ejemplo impidiendo que se escriban caracteres no numéricos al momento de escribir en
    // en el campo de la cédula, o mostrando un mensaje de error apenas el foco pasa a otro campo.
    private boolean checkFormulario() {
        String nombreU = this.textFieldNombre.getText();
        String apellidoU = this.textFieldApellido.getText();
        String ciU = this.textFieldCI.getText();

        if (nombreU.isEmpty() || apellidoU.isEmpty() || ciU.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Usuario",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Integer.parseInt(ciU);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La CI debe ser un numero", "Registrar Usuario",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    // Permite borrar el contenido de un formulario antes de cerrarlo.
    // Recordar que las ventanas no se destruyen, sino que simplemente 
    // se ocultan, por lo que conviene borrar la información para que 
    // no aparezca al mostrarlas nuevamente.
    private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldCI.setText("");
    } */