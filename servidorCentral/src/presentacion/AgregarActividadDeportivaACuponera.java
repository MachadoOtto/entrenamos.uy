package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;

import logica.IDeportivaController; 
import logica.IDictadoClaseController;
import javax.swing.JTextField;

public class AgregarActividadDeportivaACuponera extends JInternalFrame {

	private IDictadoClaseController dicc;
	private IDeportivaController controlDep;
	private JLabel cupo;
	private JLabel inst;
	private JLabel delta;
	
	private JComboBox<String> deltaI;
	private JComboBox<String> insti;
	private JComboBox<String> comboBoxCant;
	
	private JButton Acept;
    private JButton Cancel;
    private JLabel lblNewLabel;
    private JButton btnNewButton;
    private JComboBox<String> comboBoxCup;
	
	public AgregarActividadDeportivaACuponera() {

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setBounds(100, 100, 450, 388);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		setTitle("Agrear actividad deportiva a cuponera");
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		cupo = new JLabel("Cuponeras");
		cupo.setBounds(44, 32, 52, 14);
		panel.add(cupo);
		
		comboBoxCup = new JComboBox();
		comboBoxCup.setBounds(44, 57, 158, 22);
		panel.add(comboBoxCup);
		
		//Agregar cuponeras
		
		
		inst = new JLabel("Instituciones\r\n");
		inst.setBounds(44, 96, 87, 22);
		panel.add(inst);
		
		insti = new JComboBox<>();
		insti.setBounds(44, 135, 158, 22);
		panel.add(insti);
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		Set<String> ss= dicc.obtenerInstituciones();
		for(String x: ss ) {
			model.addElement(x);
		}

   /* 	cup.setModel(model);
		cup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				String t = (String) cup.getSelectedItem();
				cup.removeAllItems();
				for(String x: dicc.obtenerInstituciones()) {
					cup.addItem(x);
				}
				cup.setSelectedItem(t);*/
		
		
		delta = new JLabel("Delta Instituciones\r\n");
		delta.setBounds(44, 167, 89, 35);
		panel.add(delta);
		
		deltaI = new JComboBox<>();
		deltaI.setBounds(44, 210, 159, 22);
		panel.add(deltaI);		
		
		//AGregar deltainst
		
		lblNewLabel = new JLabel("Cantidad de clases\r\n");
		lblNewLabel.setBounds(44, 253, 116, 14);
		panel.add(lblNewLabel);
		
		comboBoxCant = new JComboBox<>();
		comboBoxCant.setBounds(44, 278, 158, 22);
		panel.add(comboBoxCant);

		
		
		btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tomarDatos();
				limpiarFormulario();
				setVisible(false);
			}
		});
		btnNewButton.setBounds(104, 313, 89, 23);
		panel.add(btnNewButton);
	
		Cancel = new JButton("Cancelar");
		Cancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			limpiarFormulario();
			setVisible(false);
			}
		});
		
		Cancel.setBounds(244, 313, 89, 23);
		panel.add(Cancel);
	}

		private void limpiarFormulario() {
		deltaI.setSelectedIndex(0);
		comboBoxCup.setSelectedIndex(0);
		insti.setSelectedIndex(0);

	}
		
	private void tomarDatos() {
		
        String institucion;
        String cuponera;
        String deltains;	
        int cant;
        
        if (checkFormulario())
        {
        	while(this.isVisible()) 
        	{
        		institucion = this.insti.getSelectedItem().toString();
            	cuponera = this.comboBoxCup.getSelectedItem().toString();
            	deltains = this.deltaI.getSelectedItem().toString();
            	cant = Integer.valueOf(this.comboBoxCant.getSelectedItem().toString());
        	
        	controlDep.agregarActividadCuponera(cuponera, institucion, deltains, cant);
        	}
        }
        
	}
	
	private boolean checkFormulario() {
		
		String institucion = this.insti.getSelectedItem().toString();
    	String cuponera = this.comboBoxCup.getSelectedItem().toString();
    	String deltains = this.deltaI.getSelectedItem().toString();
    	int cant = Integer.valueOf(this.comboBoxCant.getSelectedItem().toString());
    	
    	if((cant == 0 || cuponera.isEmpty() || institucion.isEmpty() ||deltains.isEmpty()) {
    		JOptionPane.showMessageDialog(this, "No puede haber campos vacios", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            return false;
    	}
 	
    	if(cant<0) {
    		return false;
    	}
    	return true;
		
	}

}
