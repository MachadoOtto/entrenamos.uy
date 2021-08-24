package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class AgregarActividadDeportivaACuponera extends JInternalFrame {

	private JLabel cupo;
	private JLabel inst;
	private JLabel delta;
	
	private JComboBox<String> deltaI;
	private JComboBox<String> insti;
	private JComboBox<String> cup;
	
	private JButton Acept;
    private JButton Cancel;
    private JButton btnNewButton;
	
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
		
		cup = new JComboBox<>();
		cup.setBounds(44, 57, 156, 22);
		panel.add(cup);
		
		inst = new JLabel("Instituciones\r\n");
		inst.setBounds(44, 96, 87, 22);
		panel.add(inst);
		
		insti = new JComboBox<>();
		insti.setBounds(44, 135, 158, 22);
		panel.add(insti);
		
		delta = new JLabel("Delta Instituciones\r\n");
		delta.setBounds(44, 167, 89, 35);
		panel.add(delta);
		
		deltaI = new JComboBox<>();
		deltaI.setBounds(44, 210, 159, 22);
		panel.add(deltaI);
		
		Acept = new JButton("Aceptar\r\n");
		Acept.setBounds(100, 300, 89, 23);
		panel.add(Acept);
        /*Acept.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            agregarActividadACup();
       }

    }*/
		
		Cancel = new JButton("Cancelar");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		Cancel.setBounds(245, 300, 89, 23);
		panel.add(Cancel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 268, 388, 2);
		panel.add(separator);
	}
}
