package presentacion;


import javax.swing.JInternalFrame;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import logica.IActividadDeportivaController;
import logica.IDeportivaController;
import javax.swing.JTextField;


public class AgregarActividadDeportivaCuponera extends JInternalFrame {
//	private IDeportivaController controlDep;
	private JComboBox<String> comboBoxCup;
	private JComboBox<String> comboBoxInstitucion;
	private JComboBox<String> deltaI;
	private JTextField CantClases;
	private JButton Acept;
	private JButton Cancel;
	
	public AgregarActividadDeportivaCuponera(IDeportivaController controlDep, IActividadDeportivaController IADC) {

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
		
		DefaultComboBoxModel<String> comboModelCuponera = new DefaultComboBoxModel<>();
		comboModelCuponera.addElement("-");
		Set<String> cuponeras = controlDep.getNombreCuponeras();
		Collection<String> strColection = cuponeras;
		Iterator<String> itStr1 = strColection.iterator();
		while (itStr1.hasNext()) {
			String strAux1 = itStr1.next();
			comboModelCuponera.addElement(strAux1);
		}
		
		comboBoxCup = new JComboBox<String>();
		
		comboBoxCup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				String t = (String) comboBoxCup.getSelectedItem();
				comboBoxCup.removeAllItems();
				comboBoxCup.addItem("-");
				for(String x: controlDep.getNombreCuponeras()) {
					comboBoxCup.addItem(x);
				}
				comboBoxCup.setSelectedItem(t);
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
		Set<String> instituciones = IADC.obtenerInstituciones();
		Collection<String> strCollection = instituciones;
		Iterator<String> itStr = strCollection.iterator();
		while (itStr.hasNext()) {
			String strAux = itStr.next();
			comboModelInstitucion.addElement(strAux);
		}
		
		comboBoxInstitucion = new JComboBox<>(comboModelInstitucion);
		comboBoxInstitucion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				String t = (String) comboBoxInstitucion.getSelectedItem();
				comboBoxInstitucion.removeAllItems();
				comboBoxInstitucion.addItem("-");
				for(String x: IADC.obtenerInstituciones()) {
					comboBoxInstitucion.addItem(x);
				}
				comboBoxInstitucion.setSelectedItem(t);
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

		String a, b;
		a = comboBoxInstitucion.getSelectedItem().toString();
		//b = comboBoxCup.getSelectedItem().toString();
		
		DefaultComboBoxModel<String> comboModelDeltaI = new DefaultComboBoxModel<>();
		comboModelDeltaI.addElement("-");
		Set<String> delta = IADC.obtenerDeltaInstituciones(a,a);
		Collection<String> strColectio = delta;
		Iterator<String> itStr2 = strColectio.iterator();
		while (itStr2.hasNext()) {
			String strAux2 = itStr2.next();
			comboModelDeltaI.addElement(strAux2);
		}
		
		deltaI = new JComboBox<>(comboModelDeltaI);
		
		deltaI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				String t = (String) deltaI.getSelectedItem();
				deltaI.removeAllItems();
				deltaI.addItem("-");
				for(String x: IADC.obtenerDeltaInstituciones(a,a)) {
					deltaI.addItem(x);
				}
				deltaI.setSelectedItem(t);
			}
		});

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
		gbc_CantClases.gridwidth = 5;
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
		//deltaI.setSelectedIndex(0);
	//	comboBoxCup.setSelectedIndex(0);
		//comboBoxInstitucion.setSelectedIndex(0);
		CantClases.setText("");
		
	}
	private void tomarDatos(IDeportivaController controlDep) {
		
       /* if (!checkFormulario())
        	return;
		
        String institucion;
        String cuponera;
        String deltains;	
        int cant;
        		institucion = this.insti.getSelectedItem().toString();
            	cuponera = this.comboBoxCup.getSelectedItem().toString();
            	deltains = this.deltaI.getSelectedItem().toString();
            	cant = Integer.valueOf(this.CantClases.getText());
        	
        	controlDep.agregarActividadCuponera(cuponera, institucion, deltains, cant);
        	*/JOptionPane.showMessageDialog(this, "Actividad deportiva agregada con exito", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);   
	}

	private boolean checkFormulario() {
		
		String institucion = this.comboBoxInstitucion.getSelectedItem().toString();
    	String cuponera = this.comboBoxCup.getSelectedItem().toString();
    	String deltains = this.deltaI.getSelectedItem().toString();
    	int cant = Integer.valueOf(this.CantClases.getText());
    	
    	if((cant <= 0 || cuponera.isEmpty() || institucion.isEmpty() ||deltains.isEmpty())) {
    		JOptionPane.showMessageDialog(this, "No puede haber campos vacios", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
    	}
    	else {
    		return true;
    	}
	}
}
