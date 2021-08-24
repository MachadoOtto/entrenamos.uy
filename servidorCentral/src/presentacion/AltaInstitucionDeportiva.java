package presentacion;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.border.Border;

import logica.IDeportivaController;

import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class AltaInstitucionDeportiva extends JInternalFrame{
	
	//Controller
	private IDeportivaController IDC;
	//Inputs
	private JTextField inputNombre;
	private JTextField inputURL;
	private JTextArea inputDescripcion;
	
	public AltaInstitucionDeportiva(IDeportivaController IDC) {
		this.IDC = IDC;
		//Configuracion del JFRAME
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de Institucion Deportiva");
		
		//Layout Config
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 0;
		getContentPane().add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		getContentPane().add(lblNombre, gbc_lblNombre);
		
		inputNombre = new JTextField();
		GridBagConstraints gbc_inputNombre = new GridBagConstraints();
		gbc_inputNombre.gridwidth = 3;
		gbc_inputNombre.insets = new Insets(0, 0, 5, 5);
		gbc_inputNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputNombre.gridx = 1;
		gbc_inputNombre.gridy = 2;
		getContentPane().add(inputNombre, gbc_inputNombre);
		inputNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.WEST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 3;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);
		
		inputDescripcion = new JTextArea();
		inputDescripcion.setLineWrap(true);
		inputDescripcion.setWrapStyleWord(true);
		inputDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_inputDescripcion = new GridBagConstraints();
		gbc_inputDescripcion.gridwidth = 3;
		gbc_inputDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_inputDescripcion.fill = GridBagConstraints.BOTH;
		gbc_inputDescripcion.gridx = 1;
		gbc_inputDescripcion.gridy = 4;
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		inputDescripcion.setBorder(BorderFactory.createCompoundBorder(border, 
		      BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		getContentPane().add(inputDescripcion, gbc_inputDescripcion);
		
		JLabel lblURL = new JLabel("URL:");
		GridBagConstraints gbc_lblURL = new GridBagConstraints();
		gbc_lblURL.anchor = GridBagConstraints.WEST;
		gbc_lblURL.insets = new Insets(0, 0, 5, 5);
		gbc_lblURL.gridx = 1;
		gbc_lblURL.gridy = 5;
		getContentPane().add(lblURL, gbc_lblURL);
		
		inputURL = new JTextField();
		GridBagConstraints gbc_inputURL = new GridBagConstraints();
		gbc_inputURL.gridwidth = 3;
		gbc_inputURL.insets = new Insets(0, 0, 5, 5);
		gbc_inputURL.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputURL.gridx = 1;
		gbc_inputURL.gridy = 6;
		getContentPane().add(inputURL, gbc_inputURL);
		inputURL.setColumns(10);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.anchor = GridBagConstraints.NORTHEAST;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 8;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				altaInsSTART();
			}
		});
		panel.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Limpiar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clear();
			}
		});
		panel.add(btnCancelar);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 9;
		getContentPane().add(horizontalStrut, gbc_horizontalStrut);

	}

	private void altaInsSTART() {
		if(inputNombre.getText().length()==0|| inputDescripcion.getText().length()==0|| inputURL.getText().length()==0) {
			JOptionPane.showMessageDialog(this, "No se permite el ingreso de campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(IDC.altaInstitucion(inputNombre.getText(), inputDescripcion.getText(), inputURL.getText())!=0)
			JOptionPane.showMessageDialog(this, "Ya existe una institucion con el nombre ingresado", "Error", JOptionPane.ERROR_MESSAGE);
		else {
			JOptionPane.showMessageDialog(this, "Se registro la institucion de forma existosa.", "Informacion",JOptionPane.INFORMATION_MESSAGE);
			clear();
		}
	}
	
	public void clear() {
		inputNombre.setText("");
		inputDescripcion.setText("");
		inputURL.setText("");
	}
}