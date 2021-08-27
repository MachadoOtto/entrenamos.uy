package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import datatypes.DtFecha;
import logica.IDeportivaController;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

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
	private Component verticalStrut_2;
	private JTextArea textArea;
	private JComboBox<String> DiaAlta;
	private JComboBox<String> MesAlta;
	private JLabel lblNewLabel_3;
	private JTextField AnioAlta;
	private JLabel lblNewLabel_5;
	private JSlider slider;
	
	public CrearCuponera(IDeportivaController dep) {
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setBounds(100, 100, 427, 446);
		setTitle("Crear cuponera de actividades deportivas");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
        
        DefaultComboBoxModel<String> comboModelDia3 = new DefaultComboBoxModel<>();
        comboModelDia3.addElement("-");
        for(int i = 1; i < 32; i++) {
        	comboModelDia3.addElement( String.valueOf(i) );
        }
        // Arrays auxiliares para Fecha y Hora:
        String[] meses = new String[] { "-", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
        		"Setiembre", "Octubre", "Noviembre", "Diciembre" };
        
        DefaultComboBoxModel<String> comboModelMes = new DefaultComboBoxModel<>(meses);		
        DefaultComboBoxModel<String> comboModelMes2 = new DefaultComboBoxModel<>(meses);		
        DefaultComboBoxModel<String> comboModelMes3 = new DefaultComboBoxModel<>(meses);
		
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
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 2;
		gbc_textArea.gridwidth = 3;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 4;
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		textArea.setBorder(BorderFactory.createCompoundBorder(border, 
		      BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		getContentPane().add(textArea, gbc_textArea);
        
        JLabel lblNewLabel_2 = new JLabel("Fecha de inicio");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_2.gridwidth = 2;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 6;
        getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        labelAclaracionFecha2 = new JLabel("(dd/mm/aaaa)");
        GridBagConstraints gbc_labelAclaracionFecha2 = new GridBagConstraints();
        gbc_labelAclaracionFecha2.insets = new Insets(0, 0, 5, 5);
        gbc_labelAclaracionFecha2.gridx = 3;
        gbc_labelAclaracionFecha2.gridy = 6;
        getContentPane().add(labelAclaracionFecha2, gbc_labelAclaracionFecha2);
        
        boxIDia = new JComboBox<>(comboModelDia);        
        GridBagConstraints gbc_boxIDia = new GridBagConstraints();
        gbc_boxIDia.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxIDia.insets = new Insets(0, 0, 5, 5);
        gbc_boxIDia.gridx = 1;
        gbc_boxIDia.gridy = 7;
        getContentPane().add(boxIDia, gbc_boxIDia);
        
        DiaAlta = new JComboBox<>(comboModelDia3);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 11;
		getContentPane().add(DiaAlta, gbc_comboBox);
        
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
        gbc_boxIMes.insets = new Insets(0, 0, 5, 5);
        gbc_boxIMes.fill = GridBagConstraints.BOTH;
        gbc_boxIMes.gridx = 2;
        gbc_boxIMes.gridy = 7;
        getContentPane().add(boxIMes, gbc_boxIMes);
		
		MesAlta = new JComboBox<>(comboModelMes3);
        MesAlta.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if ((MesAlta.getSelectedIndex() % 2 == 0) && (MesAlta.getSelectedIndex() < 7) || 
        				(MesAlta.getSelectedIndex() % 2 == 1) && (MesAlta.getSelectedIndex() > 8)) {
        			if (MesAlta.getSelectedIndex() == 2)
        				DiaAlta.removeItem("30");
        			DiaAlta.removeItem("31");
        		} else {
        			if (comboModelDia3.getIndexOf("30") == -1)
        				comboModelDia3.addElement("30");
        			if (comboModelDia3.getIndexOf("31") == -1)
        				comboModelDia3.addElement("31");
        		}
        	}
        });
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.BOTH;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 11;
		getContentPane().add(MesAlta, gbc_comboBox_1);

		inicioAnio = new JTextField();
		inicioAnio.setText("yyyy");
		inicioAnio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(inicioAnio.getText().equals("yyyy"))
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
        gbc_boxIMes2.fill = GridBagConstraints.BOTH;
        gbc_boxIMes2.gridx = 2;
        gbc_boxIMes2.gridy = 9;
        boxIMes2.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if ((boxIMes2.getSelectedIndex() % 2 == 0) && (boxIMes2.getSelectedIndex() < 7) || 
        				(boxIMes2.getSelectedIndex() % 2 == 1) && (boxIMes2.getSelectedIndex() > 8)) {
        			if (boxIMes2.getSelectedIndex() == 2)
        				boxIDia2.removeItem("30");
        			boxIDia2.removeItem("31");
        		} else {
        			if (comboModelDia2.getIndexOf("30") == -1)
        				comboModelDia2.addElement("30");
        			if (comboModelDia2.getIndexOf("31") == -1)
        				comboModelDia2.addElement("31");
        		}
        	}
        });
        getContentPane().add(boxIMes2, gbc_boxIMes2);
		
        
        inicioAnio2 = new JTextField();
        inicioAnio2.setText("yyyy");
		inicioAnio2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(inicioAnio2.getText().equals("yyyy"))
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
		
		lblNewLabel_3 = new JLabel("Fecha de alta\t\t");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 10;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		lblNewLabel_5 = new JLabel("(dd/mm/aaaa)");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 3;
		gbc_lblNewLabel_5.gridy = 10;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		AnioAlta = new JTextField();
		AnioAlta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(AnioAlta.getText().equals("yyyy"))
					AnioAlta.setText("");
			}
		});
		AnioAlta.setText("yyyy");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 11;
		getContentPane().add(AnioAlta, gbc_textField_1);
		AnioAlta.setColumns(10);
		
		JLabel desculab = new JLabel("Descuento: 0%");
		GridBagConstraints gbc_desculab = new GridBagConstraints();
		gbc_desculab.gridheight = 2;
		gbc_desculab.anchor = GridBagConstraints.WEST;
		gbc_desculab.gridwidth = 2;
		gbc_desculab.insets = new Insets(0, 0, 5, 5);
		gbc_desculab.gridx = 1;
		gbc_desculab.gridy = 12;
		getContentPane().add(desculab, gbc_desculab);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 13;
		getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		
		slider = new JSlider();
		slider.setValue(0);
		slider.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				desculab.setText("Descuento: "+slider.getValue()+"%");
			}
		});

		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.gridwidth = 3;
		gbc_slider.fill = GridBagConstraints.BOTH;
		gbc_slider.gridheight = 2;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 14;
		getContentPane().add(slider, gbc_slider);
		
		btnNewButton_1 = new JButton("Aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					tomarDatos(dep);	
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 16;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton = new JButton("Limpiar");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 16;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 2;
		gbc_verticalStrut_2.gridy = 17;
		getContentPane().add(verticalStrut_2, gbc_verticalStrut_2);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_1.gridx = 3;
		gbc_verticalStrut_1.gridy = 19;
		getContentPane().add(verticalStrut_1, gbc_verticalStrut_1);
		
	}
	public void clear() {
		boxIMes.setSelectedIndex(0);
		boxIMes2.setSelectedIndex(0);
		boxIDia.setSelectedIndex(0);
		boxIDia2.setSelectedIndex(0);
		DiaAlta.setSelectedIndex(0);
		MesAlta.setSelectedIndex(0);
		
		AnioAlta.setText("yyyy");
		inicioAnio.setText("yyyy");
		inicioAnio2.setText("yyyy");
        textField.setText("");
        textArea.setText("");
        slider.setValue(0);
    }
	
	private void tomarDatos(IDeportivaController dep) {
		if(!checkFormulario())
			return;
		
		String nombreU;
        String descripcion;
        int dia;
        int mes;
        int anio;
        int dia2;
        int mes2;
        int anio2;
        int desc;
        
		nombreU = this.textField.getText();
        descripcion = this.textArea.getSelectedText();
        
        dia = this.boxIDia.getSelectedIndex();
        mes = this.boxIMes.getSelectedIndex();
        anio = Integer.parseInt(inicioAnio.getText());
        dia2 = this.boxIDia2.getSelectedIndex();
        mes2 = this.boxIMes2.getSelectedIndex();
        anio2 = Integer.parseInt(inicioAnio2.getText());
        desc = slider.getValue();
        int diaA = this.DiaAlta.getSelectedIndex();
        int mesA = this.MesAlta.getSelectedIndex();
        int anioA = Integer.parseInt(AnioAlta.getText());
   
        DtFecha FInicio = new DtFecha(anio, mes, dia, 0, 0, 0);
        DtFecha  FFinal = new DtFecha(anio2, mes2, dia2, 0, 0, 0);
        DtFecha alta = new DtFecha(diaA, mesA, anioA, 0, 0, 0);

        dep.ingresarCuponera(nombreU, descripcion, FInicio, FFinal, desc, alta);
		JOptionPane.showMessageDialog(this, "La cuponera ha sido creada con exito.", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
	}
	
	private boolean checkFormulario() {

       int dia = this.boxIDia.getSelectedIndex();
       int mes = this.boxIMes.getSelectedIndex();
       String anio = inicioAnio.getText();
       int dia2 = this.boxIDia2.getSelectedIndex();
       int mes2 = this.boxIMes2.getSelectedIndex();
       String anio2 = inicioAnio2.getText();
       
       int dia3 = this.DiaAlta.getSelectedIndex();
       int mes3 = this.MesAlta.getSelectedIndex();
       String anio3 = AnioAlta.getText();
       
	   String nombreU = this.textField.getText();
       String descripcion = this.textArea.getText();
       if (nombreU.isEmpty() || anio3.equals("yyyy")||anio.equals("yyyy") || anio2.equals("yyyy")  ||
    		   dia == 0 || mes == 0 || dia2 == 0 || mes2 == 0 || dia3==0 ||mes3==0 || descripcion.isEmpty()) {
           JOptionPane.showMessageDialog(this, "No puede haber campos vacios", this.getTitle(), JOptionPane.ERROR_MESSAGE);
           return false;
       }  
       
       //Numeros no son numeros
       try {
           Integer.parseInt(anio2);
           Integer.parseInt(anio3);
           Integer.parseInt(anio);
       } catch (NumberFormatException e) {
           JOptionPane.showMessageDialog(this, "La fecha ingresada debe ser un numero.", this.getTitle(), JOptionPane.ERROR_MESSAGE);
           return false;
       }       

       
       //Fechas inexistentes
       int Nanio = Integer.parseInt(anio);
       int Nanio2 = Integer.parseInt(anio2);
       if (Nanio < 1900 || Nanio2 < 1900 ) {
    	   JOptionPane.showMessageDialog(this, "La fecha ingresada debe ser valida", this.getTitle(), JOptionPane.ERROR_MESSAGE);
           return false;
       }
       
       //
       if(Nanio*365+mes*30+dia > Nanio2*365+mes*30+dia) {
    	   //No es una verificacion perfecta :)
           JOptionPane.showMessageDialog(this, "La fecha de vencimiento de la cuponera no puede ser antes que la de su creacion.", this.getTitle(), JOptionPane.ERROR_MESSAGE);
           return false;
       }
       return true;       
	}
	
}