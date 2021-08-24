package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearCuponeraDeActividadesDeportivas extends JInternalFrame {
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;

	public CrearCuponeraDeActividadesDeportivas() {
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setBounds(100, 100, 450, 330);
		getContentPane().setLayout(null);
		setTitle("Crear cuponera de actividades deportivas");
		
		JLabel lblNewLabel = new JLabel("Nombre\r\n\t");
		lblNewLabel.setBounds(32, 32, 46, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(32, 57, 117, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(243, 32, 76, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(243, 57, 117, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha de inicio");
		lblNewLabel_2.setBounds(32, 105, 76, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Fecha de fin\r\n");
		lblNewLabel_2_1.setBounds(243, 105, 76, 14);
		getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha de alta");
		lblNewLabel_3.setBounds(32, 175, 76, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Descuento");
		lblNewLabel_4.setBounds(243, 175, 76, 14);
		getContentPane().add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(243, 200, 117, 20);
		getContentPane().add(textField_4);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(250, 255, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(90, 255, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(21, 235, 392, 2);
		getContentPane().add(separator);

	}
}
