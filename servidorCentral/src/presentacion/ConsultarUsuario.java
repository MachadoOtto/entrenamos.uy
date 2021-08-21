package presentacion;

import javax.swing.JInternalFrame;

import excepciones.UsuarioNoExisteException;
import logica.DataUsuario;
import logica.IControladorUsuario;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * JInternalFrame que permite consultar la información de un usuario del sistema.
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class ConsultarUsuario extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorUsuario controlUsr;
    
    // Los componentes gráficos se agregan como atributos de la clase
    // para facilitar su acceso desde diferentes métodos de la misma.
    private JTextField textFieldCI;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JLabel lblIngresoCI;
    private JButton btnBuscar;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JButton btnCerrar;
    private JLabel lblInfoUsuario;

    /**
     * Create the frame.
     */
    public ConsultarUsuario(IControladorUsuario icu) {
        // Se inicializa con el controlador de usuarios
        controlUsr = icu;
        
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consultar un Usuario");
        setBounds(30, 30, 400, 280);

        // En este caso usaremos el Absolute Layout y deberemos indicar
        // la posición absoluta de todos los componentes
        getContentPane().setLayout(null);

        // Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse 
        // la cédula del usuario.
        lblIngresoCI = new JLabel("Ingrese CI:");
        lblIngresoCI.setBounds(10, 24, 170, 14);
        getContentPane().add(lblIngresoCI);

        // Una campo de texto (JTextField) para ingresar la cédula de un usuario. 
        textFieldCI = new JTextField();
        textFieldCI.setBounds(104, 17, 140, 30);
        getContentPane().add(textFieldCI);

        // Un botón (JButton) con un evento asociado que permite buscar un usuario.
        // Dado que el código de registro tiene cierta complejidad, conviene delegarlo
        // a otro método en lugar de incluirlo directamente de el método actionPerformed 
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cmdBuscarUsuarioActionPerformed(e);
            }
        });
        btnBuscar.setBounds(256, 16, 95, 30);
        getContentPane().add(btnBuscar);
        
        // Una etiqueta (JLabel) indicando que a continuación se verá la 
        // información del usuarios buscado.
        lblInfoUsuario = new JLabel("Información de Usuario");
        lblInfoUsuario.setBounds(104, 70, 180, 14);
        getContentPane().add(lblInfoUsuario);

        // Una etiqueta (JLabel) indicando que en el siguiente campo se verá 
        // el nombre del usuario encontrado.
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 114, 65, 14);
        getContentPane().add(lblNombre);

        // Una campo de texto (JTextField) para mostrar el nombre del usuario. 
        // El campo se hace no editable para impedir que se modifique.
        textFieldNombre = new JTextField();
        textFieldNombre.setEditable(false);
        textFieldNombre.setBounds(80, 107, 271, 30);
        getContentPane().add(textFieldNombre);

        // Una etiqueta (JLabel) indicando que en el siguiente campo se verá 
        // el apellido del usuario encontrado.
        lblApellido = new JLabel("Apellido");
        lblApellido.setBounds(10, 156, 65, 14);
        getContentPane().add(lblApellido);
        
        // Una campo de texto (JTextField) para mostrar el apellido del usuario. 
        // El campo se hace no editable para impedir que se modifique.
        textFieldApellido = new JTextField();
        textFieldApellido.setEditable(false);
        textFieldApellido.setBounds(80, 149, 271, 30);
        getContentPane().add(textFieldApellido);

        // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
        // Dado que antes de cerrar se limpia el formulario, se invoca un método reutilizable para ello. 
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        btnCerrar.setBounds(262, 191, 89, 23);
        getContentPane().add(btnCerrar);
    }

    // Este método es invocado al querer buscar un usuario, funcionalidad
    // provista por la operación del sistem verInfoUsuario().
    // En caso de que haya un error de búsqueda se despliega
    // un mensaje utilizando un panel de mensaje (JOptionPane).
    // No es necesario verificar que el campo con la cédula sea un número ya
    // que internamente el sistema almacena la cédula como un string.
    protected void cmdBuscarUsuarioActionPerformed(ActionEvent e) {
        DataUsuario du;
        try {
            du = controlUsr.verInfoUsuario(textFieldCI.getText());
            textFieldNombre.setText(du.getNombre());
            textFieldApellido.setText(du.getApellido());
        } catch (UsuarioNoExisteException e1) {
            // Si el usuario no existe, se muestra mensaje de error y se limpia el
            // formulario.
            JOptionPane.showMessageDialog(this, e1.getMessage(), "Buscar Usuario", JOptionPane.ERROR_MESSAGE);
            limpiarFormulario();
        }

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
