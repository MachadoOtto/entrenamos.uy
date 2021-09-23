package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.util.Set;

import excepciones.ActividadDeportivaException;
import excepciones.CuponeraInmutableException;
import excepciones.InstitucionException;

import logica.IActividadDeportivaController;
import logica.ICuponeraController;

@SuppressWarnings("serial")
public class AgregarActividadDeportivaCuponera extends JInternalFrame {
//	private ICuponeraController controlDep;
	private JComboBox<String> comboBoxCup;
	private JComboBox<String> comboBoxInstitucion;
	private JComboBox<String> deltaI;
	private JTextField CantClases;
	private JButton Acept;
	private JButton Cancel;
	
	public AgregarActividadDeportivaCuponera(ICuponeraController controlDep, IActividadDeportivaController IADC) {

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setBounds(100, 100, 435, 254);
		setTitle("Agrear actividad deportiva a cuponera");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel cupo = new JLabel("Cuponeras");
		GridBagConstraints gbc_cupo = new GridBagConstraints();
		gbc_cupo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cupo.gridwidth = 11;
		gbc_cupo.insets = new Insets(0, 0, 5, 5);
		gbc_cupo.gridx = 1;
		gbc_cupo.gridy = 1;
		getContentPane().add(cupo, gbc_cupo);
//---------------------------------------------------------------------------
		
		comboBoxCup = new JComboBox<String>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		Set<String> ss= controlDep.getNombreCuponerasSinRecibos();
		model.addElement("-");
		for(String x: ss ) {
			model.addElement(x);
		}
		comboBoxCup.setModel(model);
		comboBoxCup.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String xx = (String) comboBoxCup.getSelectedItem();
					String yy = (String) comboBoxInstitucion.getSelectedItem();
					if(xx==null||yy==null)
						return;
					deltaI.removeAllItems();
					deltaI.addItem("-");
					if(!(xx.equals("-")) && !(yy.equals("-"))) {
						for(String x: IADC.obtenerDeltaInstituciones((String)comboBoxCup.getSelectedItem(), (String)comboBoxInstitucion.getSelectedItem())) {
							deltaI.addItem(x);
						}
					}
					deltaI.setSelectedItem("-");
				} catch (InstitucionException ignore) { }
			}
		});

		comboBoxCup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Set<String> tt = controlDep.getNombreCuponeras();
				if(comboBoxCup.getItemCount()!=tt.size()+1) {
					String t = (String) comboBoxCup.getSelectedItem();
					comboBoxCup.removeAllItems();
					model.addElement("-");
					for(String x: controlDep.getNombreCuponeras()) {
						comboBoxCup.addItem(x);
					}
					comboBoxCup.setSelectedItem(t);
				}
			}
		});

		GridBagConstraints gbc_cup = new GridBagConstraints();
		gbc_cup.gridwidth = 11;
		gbc_cup.insets = new Insets(0, 0, 5, 5);
		gbc_cup.fill = GridBagConstraints.HORIZONTAL;
		gbc_cup.gridx = 1;
		gbc_cup.gridy = 2;
		getContentPane().add(comboBoxCup, gbc_cup);
//-------------------------------------------------------------------------		
		JLabel inst = new JLabel("Instituciones\r\n");
		GridBagConstraints gbc_inst = new GridBagConstraints();
		gbc_inst.fill = GridBagConstraints.HORIZONTAL;
		gbc_inst.gridwidth = 11;
		gbc_inst.insets = new Insets(0, 0, 5, 5);
		gbc_inst.gridx = 1;
		gbc_inst.gridy = 3;
		getContentPane().add(inst, gbc_inst);
		
//-----------------------------------------------------------------------------------

		DefaultComboBoxModel<String> comboModelInstitucion = new DefaultComboBoxModel<>();
		comboModelInstitucion.addElement("-");
		for(String q: IADC.obtenerInstituciones()){
			comboModelInstitucion.addElement(q);
		}
		
		comboBoxInstitucion = new JComboBox<>();
		comboBoxInstitucion.setModel(comboModelInstitucion);
		comboBoxInstitucion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String xx = (String) comboBoxCup.getSelectedItem();
					String yy = (String) comboBoxInstitucion.getSelectedItem();
					if(xx==null||yy==null)
						return;
					deltaI.removeAllItems();
					deltaI.addItem("-");
					if(!(xx.equals("-")) && !(yy.equals("-"))) {
						for(String x: IADC.obtenerDeltaInstituciones((String)comboBoxCup.getSelectedItem(), (String)comboBoxInstitucion.getSelectedItem())) {
							deltaI.addItem(x);
						}
					}
					deltaI.setSelectedItem("-");
				} catch (InstitucionException ignore) { }
			}
		});
		comboBoxInstitucion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Set<String> tt = IADC.obtenerInstituciones();
				if(comboBoxInstitucion.getItemCount()!=tt.size()+1) {
					String t = (String) comboBoxInstitucion.getSelectedItem();
					comboBoxInstitucion.removeAllItems();
					comboModelInstitucion.addElement("-");
					for(String x: IADC.obtenerInstituciones()) {
						comboBoxInstitucion.addItem(x);
					}
					comboBoxInstitucion.setSelectedItem(t);
				}
			}
		});
		
		GridBagConstraints gbc_insti = new GridBagConstraints();
		gbc_insti.gridwidth = 11;
		gbc_insti.insets = new Insets(0, 0, 5, 5);
		gbc_insti.fill = GridBagConstraints.HORIZONTAL;
		gbc_insti.gridx = 1;
		gbc_insti.gridy = 4;
		getContentPane().add(comboBoxInstitucion, gbc_insti);

//--------------------------------------------------------------------------------		
		JLabel lblActividadDeportiva = new JLabel("Actividad Deportiva");
		GridBagConstraints gbc_lblActividadDeportiva = new GridBagConstraints();
		gbc_lblActividadDeportiva.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblActividadDeportiva.gridwidth = 11;
		gbc_lblActividadDeportiva.insets = new Insets(0, 0, 5, 5);
		gbc_lblActividadDeportiva.gridx = 1;
		gbc_lblActividadDeportiva.gridy = 5;
		getContentPane().add(lblActividadDeportiva, gbc_lblActividadDeportiva);

//------------------------------------------------------------------------------		


		DefaultComboBoxModel<String> comboModelDeltaI = new DefaultComboBoxModel<>();
		comboModelDeltaI.addElement("-");
		deltaI = new JComboBox<>(comboModelDeltaI);

		GridBagConstraints gbc_deltaI = new GridBagConstraints();
		gbc_deltaI.gridwidth = 11;
		gbc_deltaI.insets = new Insets(0, 0, 5, 5);
		gbc_deltaI.fill = GridBagConstraints.HORIZONTAL;
		gbc_deltaI.gridx = 1;
		gbc_deltaI.gridy = 6;
		getContentPane().add(deltaI, gbc_deltaI);

//_-----------------------------------------------------------------------------------		
		Acept = new JButton("Aceptar\r\n");
		Acept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tomarDatos(controlDep);
			}
		});
		GridBagConstraints gbc_Acept = new GridBagConstraints();
		gbc_Acept.insets = new Insets(0, 0, 5, 5);
		gbc_Acept.gridx = 10;
		gbc_Acept.gridy = 8;
		getContentPane().add(Acept, gbc_Acept);
		
		JLabel lblNewLabel = new JLabel("Cantidad de clases");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 7;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		CantClases = new JTextField();
		GridBagConstraints gbc_CantClases = new GridBagConstraints();
		gbc_CantClases.gridwidth = 3;
		gbc_CantClases.insets = new Insets(0, 0, 5, 5);
		gbc_CantClases.fill = GridBagConstraints.HORIZONTAL;
		gbc_CantClases.gridx = 2;
		gbc_CantClases.gridy = 8;
		getContentPane().add(CantClases, gbc_CantClases);
		CantClases.setColumns(10);

		
		Cancel = new JButton("Limpiar");
		Cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				}
			});
		GridBagConstraints gbc_Cancel = new GridBagConstraints();
		gbc_Cancel.insets = new Insets(0, 0, 5, 5);
		gbc_Cancel.gridx = 11;
		gbc_Cancel.gridy = 8;
		getContentPane().add(Cancel, gbc_Cancel);
	}

	public void clear() {
		deltaI.setSelectedItem("-");
		comboBoxCup.setSelectedItem("-");
		comboBoxInstitucion.setSelectedItem("-");
		CantClases.setText("");
		
	}
	private void tomarDatos(ICuponeraController controlDep) {
		try {
			if (!checkFormulario())
	        	return;
	        String institucion;
	        String cuponera;
	        String deltains;	
	        int cant;
			institucion = this.comboBoxInstitucion.getSelectedItem().toString().trim();
	    	cuponera = this.comboBoxCup.getSelectedItem().toString().trim();
	    	deltains = this.deltaI.getSelectedItem().toString().trim();
	    	cant = Integer.valueOf(this.CantClases.getText().trim());
	    	controlDep.agregarActividadCuponera(cuponera, institucion, deltains, cant);
	    	JOptionPane.showMessageDialog(this, "Actividad deportiva agregada con exito.", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);   
	    	clear();
			setVisible(false);
		} catch (InstitucionException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), this.getTitle(), JOptionPane.ERROR_MESSAGE); 
		} catch (ActividadDeportivaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), this.getTitle(), JOptionPane.ERROR_MESSAGE); 
		} catch (CuponeraInmutableException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), this.getTitle(), JOptionPane.ERROR_MESSAGE); 
		}
	}

	private boolean checkFormulario() {
		
		String institucion = this.comboBoxInstitucion.getSelectedItem().toString().trim();
    	String cuponera = this.comboBoxCup.getSelectedItem().toString().trim();
    	String deltains = this.deltaI.getSelectedItem().toString().trim();
    	
    	if((cuponera.trim().equals("-") || institucion.trim().equals("-") || deltains.trim().equals("-"))) {
    		JOptionPane.showMessageDialog(this, "No puede haber campos vacios.", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
    	}
    	try {
    		Integer.valueOf(this.CantClases.getText());
    	} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(this, "La cantidad de clases debe ser un numero.", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
    	}
    	int cant = Integer.valueOf(this.CantClases.getText());
    	if(cant > 10000) {
    		JOptionPane.showMessageDialog(this, "No es posible crear una cuponera con tantas clases.", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
    	}
    	if(cant <= 0 ) {
    		JOptionPane.showMessageDialog(this, "La cantidad de clases debe ser mayor a 0.", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
    	}
    	return true;
	}
}
