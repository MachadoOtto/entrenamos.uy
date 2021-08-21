package presentacion;

import javax.swing.JInternalFrame;

import excepciones.UsuarioRepetidoException;
import logica.IControladorUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * JInternalFrame que permite registrar un nuevo usuario al sistema.
 * 
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class CrearUsuario extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorUsuario controlUsr;
    
    // Los componentes gráficos se agregan como atributos de la clase
    // para facilitar su acceso desde diferentes métodos de la misma.
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldCI;
    private JLabel lblIngreseNombre;
    private JLabel lblIngreseApellido;
    private JLabel lblIngreseCi;
    private JButton btnAceptar;
    private JButton btnCancelar;

    /**
     * Create the frame.
     */
    public CrearUsuario(IControladorUsuario icu) {
        // Se inicializa con el controlador de usuarios
        controlUsr = icu;

        // Propiedades del JInternalFrame como dimensión, posición dentro del frame,
        // etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Registrar un Usuario");
        setBounds(10, 40, 360, 150);

        // En este caso utilizaremos el GridBagLayout que permite armar una grilla
        // en donde las filas y columnas no son uniformes.
        // Conviene trabajar este componente desde la vista de diseño gráfico y sólo
        // manipular los valores para ajustar alguna cosa.
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 100, 120, 120, 0 };
        gridBagLayout.rowHeights = new int[] { 30, 30, 30, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        getContentPane().setLayout(gridBagLayout);

        // Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse 
        // el nombre del usuario. El texto está alineado horizontalmente a la derecha para
        // que quede casi pegado al campo de texto.
        lblIngreseNombre = new JLabel("Nombre:");
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
        gbc_lblIngreseNombre.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseNombre.gridx = 0;
        gbc_lblIngreseNombre.gridy = 0;
        getContentPane().add(lblIngreseNombre, gbc_lblIngreseNombre);

        // Una campo de texto (JTextField) para ingresar el nombre del usuario. 
        // Por defecto es posible ingresar cualquier string.
        textFieldNombre = new JTextField();
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.gridwidth = 2;
        gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 0;
        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
        textFieldNombre.setColumns(10);

        // Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse 
        // el apellido del usuario. El texto está alineado horizontalmente a la derecha para
        // que quede casi pegado al campo de texto.
        lblIngreseApellido = new JLabel("Apellido:");
        lblIngreseApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseApellido = new GridBagConstraints();
        gbc_lblIngreseApellido.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseApellido.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseApellido.gridx = 0;
        gbc_lblIngreseApellido.gridy = 1;
        getContentPane().add(lblIngreseApellido, gbc_lblIngreseApellido);

        // Una campo de texto (JTextField) para ingresar el apellido del usuario. 
        // Por defecto es posible ingresar cualquier string.
        textFieldApellido = new JTextField();
        GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
        gbc_textFieldApellido.gridwidth = 2;
        gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
        gbc_textFieldApellido.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldApellido.gridx = 1;
        gbc_textFieldApellido.gridy = 1;
        getContentPane().add(textFieldApellido, gbc_textFieldApellido);
        textFieldApellido.setColumns(10);

        // Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse 
        // la cédula del usuario. El texto está alineado horizontalmente a la derecha para
        // que quede casi pegado al campo de texto.
        lblIngreseCi = new JLabel("C.I.:");
        lblIngreseCi.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseCi = new GridBagConstraints();
        gbc_lblIngreseCi.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseCi.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseCi.gridx = 0;
        gbc_lblIngreseCi.gridy = 2;
        getContentPane().add(lblIngreseCi, gbc_lblIngreseCi);

        // Una campo de texto (JTextField) para ingresar la cédula del usuario. 
        // Por defecto es posible ingresar cualquier string.
        // Al campo se le incluye un Tooltip que, al pasar el mouse por encima, despliega un mensaje.
        textFieldCI = new JTextField();
        textFieldCI.setToolTipText("Ingrese un número sin puntos ni guiones");
        textFieldCI.setColumns(10);
        GridBagConstraints gbc_textFieldCI = new GridBagConstraints();
        gbc_textFieldCI.gridwidth = 2;
        gbc_textFieldCI.fill = GridBagConstraints.BOTH;
        gbc_textFieldCI.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldCI.gridx = 1;
        gbc_textFieldCI.gridy = 2;
        getContentPane().add(textFieldCI, gbc_textFieldCI);

        // Un botón (JButton) con un evento asociado que permite registrar el usuario.
        // Dado que el código de registro tiene cierta complejidad, conviene delegarlo
        // a otro método en lugar de incluirlo directamente de el método actionPerformed 
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cmdRegistroUsuarioActionPerformed(arg0);
            }
        });

        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptar.gridx = 1;
        gbc_btnAceptar.gridy = 3;
        getContentPane().add(btnAceptar, gbc_btnAceptar);

        // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
        // Dado que antes de cerrar se limpia el formulario, se invoca un método reutilizable para ello. 
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 3;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
    }

    // Este método es invocado al querer registrar un usuario, funcionalidad
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
    }
}
