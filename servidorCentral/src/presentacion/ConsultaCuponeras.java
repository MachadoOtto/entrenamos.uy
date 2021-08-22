package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;



@SuppressWarnings("serial")
public class ConsultaCuponeras extends JInternalFrame{
	/**
	 * @wbp.nonvisual location=468,189
	 */
	private final JInternalFrame visualizadorCuponera = new JInternalFrame("New JInternalFrame");

	public ConsultaCuponeras() {
		visualizadorCuponera.setVisible(true);
		setTitle("Consulta de Cuponeras de actividades deportivas");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Escoja una cuponera:");
		getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		getContentPane().add(comboBox);
		
	}

}
