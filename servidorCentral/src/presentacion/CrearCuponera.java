package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import java.awt.Component;
import javax.swing.Box;

public class CrearCuponera extends JInternalFrame {
	private JTextField textField;
	private JComboBox<String> boxIDia; // Depende de mes;
	private JComboBox<String> boxIMes;
	private JLabel labelAclaracionFecha;
	private JTextField inicioAnio;
	private JLabel labelAclaracionFecha2;
	private JComboBox<String> boxIDia2; // Depende de mes;
	private JComboBox<String> boxIMes2;
	private JTextField inicioAnio2;
	private Component horizontalStrut;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private JSlider slider;
	private Component verticalStrut_2;
	
	public CrearCuponera() {
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setBounds(100, 100, 552, 393);
		setTitle("Crear cuponera de actividades deportivas");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		//Modelos
        DefaultComboBoxModel<String> comboModelDia = new DefaultComboBoxModel<>();
        comboModelDia.addElement("-");
        for(int i = 1; i < 32; i++) {
        	comboModelDia.addElement( String.valueOf(i) );
        }
        DefaultComboBoxModel<String> comboModelDia2 = new DefaultComboBoxModel<>();
        comboModelDia2.addElement("-");
        for(int i = 1; i < 32; i++) {
        	comboModelDia2.addElement( String.valueOf(i) );
        }
        // Arrays auxiliares para Fecha y Hora:
        String[] meses = new String[] { "-", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
        		"Setiembre", "Octubre", "Noviembre", "Diciembre" };
        
        DefaultComboBoxModel<String> comboModelMes = new DefaultComboBoxModel<>(meses);		
        DefaultComboBoxModel<String> comboModelMes2 = new DefaultComboBoxModel<>(meses);		
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 0;
		getContentPane().add(verticalStrut, gbc_verticalStrut);
        
		JLabel lblNewLabel = new JLabel("Nombre\r\n\t");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		getContentPane().add(textField, gbc_textField);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridwidth = 4;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 3;
		gbc_textArea.gridheight = 2;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 4;
		getContentPane().add(textArea, gbc_textArea);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha de inicio");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 6;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		
        boxIMes = new JComboBox<>(comboModelMes);
        boxIMes.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if (boxIMes.getSelectedIndex() % 2 == 0) {
        			boxIDia.removeItem("31");
        		} else {
        			if (comboModelDia.getIndexOf("31") == -1)
        				comboModelDia.addElement("31");
        		}
        	}
        });
        
        labelAclaracionFecha2 = new JLabel("(dd/mm/aaaa)");
        GridBagConstraints gbc_labelAclaracionFecha2 = new GridBagConstraints();
        gbc_labelAclaracionFecha2.insets = new Insets(0, 0, 5, 5);
        gbc_labelAclaracionFecha2.gridx = 3;
        gbc_labelAclaracionFecha2.gridy = 6;
        getContentPane().add(labelAclaracionFecha2, gbc_labelAclaracionFecha2);
        
        boxIDia = new JComboBox<>(comboModelDia);        
        GridBagConstraints gbc_boxIDia = new GridBagConstraints();
        gbc_boxIDia.insets = new Insets(0, 0, 5, 5);
        gbc_boxIDia.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxIDia.gridx = 1;
        gbc_boxIDia.gridy = 7;
        getContentPane().add(boxIDia, gbc_boxIDia);
        GridBagConstraints gbc_boxIMes = new GridBagConstraints();
        gbc_boxIMes.insets = new Insets(0, 0, 5, 5);
        gbc_boxIMes.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxIMes.gridx = 2;
        gbc_boxIMes.gridy = 7;
        getContentPane().add(boxIMes, gbc_boxIMes);
        boxIMes.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if (boxIMes2.getSelectedIndex() % 2 == 0) {
        			boxIDia2.removeItem("31");
        		} else {
        			if (comboModelDia2.getIndexOf("31") == -1)
        				comboModelDia2.addElement("31");
        		}
        	}
        });
		
		
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
		gbc_inicioAnio.gridx = 3;
		gbc_inicioAnio.gridy = 7;
		getContentPane().add(inicioAnio, gbc_inicioAnio);
		
		JLabel lblNewLabel_2_1 = new JLabel("Fecha de fin\r\n");
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2_1.gridwidth = 2;
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1.gridx = 1;
		gbc_lblNewLabel_2_1.gridy = 8;
		getContentPane().add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);
		
        // JComboBox:
        
		labelAclaracionFecha = new JLabel("(dd/mm/aaaa)");
		GridBagConstraints gbc_labelAclaracionFecha = new GridBagConstraints();
		gbc_labelAclaracionFecha.insets = new Insets(0, 0, 5, 5);
		gbc_labelAclaracionFecha.gridx = 3;
		gbc_labelAclaracionFecha.gridy = 8;
		getContentPane().add(labelAclaracionFecha, gbc_labelAclaracionFecha);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 9;
		getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		
		boxIDia2 = new JComboBox<>(comboModelDia2);        
		GridBagConstraints gbc_boxIDia2 = new GridBagConstraints();
		gbc_boxIDia2.insets = new Insets(0, 0, 5, 5);
		gbc_boxIDia2.fill = GridBagConstraints.HORIZONTAL;
		gbc_boxIDia2.gridx = 1;
		gbc_boxIDia2.gridy = 9;
		getContentPane().add(boxIDia2, gbc_boxIDia2);
		
        boxIMes2 = new JComboBox<>(comboModelMes2);
        GridBagConstraints gbc_boxIMes2 = new GridBagConstraints();
        gbc_boxIMes2.insets = new Insets(0, 0, 5, 5);
        gbc_boxIMes2.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxIMes2.gridx = 2;
        gbc_boxIMes2.gridy = 9;
        getContentPane().add(boxIMes2, gbc_boxIMes2);
		
        
        inicioAnio2 = new JTextField();
        inicioAnio2.setText("yyyy");
        inicioAnio2.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		inicioAnio2.setText("");
        	}
        });
        GridBagConstraints gbc_inicioAnio2 = new GridBagConstraints();
        gbc_inicioAnio2.gridwidth = 1;
        gbc_inicioAnio2.fill = GridBagConstraints.BOTH;
        gbc_inicioAnio2.insets = new Insets(0, 0, 5, 5);
        gbc_inicioAnio2.gridx = 3;
        gbc_inicioAnio2.gridy = 9;
        getContentPane().add(inicioAnio2, gbc_inicioAnio2);
        
		JLabel lblNewLabel_4 = new JLabel("Descuento (%)");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.gridwidth = 2;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 10;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		slider = new JSlider();
		slider.setMinorTickSpacing(5);
		slider.setMajorTickSpacing(10);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.gridwidth = 2;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 11;
		getContentPane().add(slider, gbc_slider);
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 2;
		gbc_verticalStrut_2.gridy = 12;
		getContentPane().add(verticalStrut_2, gbc_verticalStrut_2);
		
		btnNewButton_1 = new JButton("Aceptar");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 13;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton = new JButton("Limpiar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 13;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_1.gridx = 3;
		gbc_verticalStrut_1.gridy = 14;
		getContentPane().add(verticalStrut_1, gbc_verticalStrut_1);
		
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
