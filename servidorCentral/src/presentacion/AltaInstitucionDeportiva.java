package presentacion;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AltaInstitucionDeportiva extends JInternalFrame{

	public AltaInstitucionDeportiva() {
		setTitle("Alta de Institucion Deportiva");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelAccion = new JPanel();
		getContentPane().add(panelAccion,BorderLayout.PAGE_END);
		panelAccion.setLayout(new BoxLayout(panelAccion, BoxLayout.X_AXIS));
		
		JPanel panelDatos = new JPanel();
		getContentPane().add(panelDatos, BorderLayout.CENTER);
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
		
		JPanel panelNombre = new JPanel();
		panelDatos.add(panelNombre);
		panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.X_AXIS));
		
		panelDatos.add(Box.createVerticalGlue());
		
		JPanel panelDesc = new JPanel();
		panelDatos.add(panelDesc);
		panelDesc.setLayout(new BoxLayout(panelDesc, BoxLayout.X_AXIS));
		
		JPanel panelURL = new JPanel();
		panelDatos.add(panelURL);
		panelURL.setLayout(new BoxLayout(panelURL, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Ingrese los siguientes datos:");
		getContentPane().add(lblNewLabel, BorderLayout.PAGE_START);
		
		JLabel nl = new JLabel("Nombre:");
		panelNombre.add(nl, BorderLayout.PAGE_START);
		
		JTextField nt = new JTextField();
		panelNombre.add(nt,BorderLayout.PAGE_START);
		nt.setColumns(10);

		JLabel dl = new JLabel("Descripcion:");
		panelDesc.add(dl, BorderLayout.PAGE_START);
		
		JTextField dt = new JTextField();
		panelDesc.add(dt,BorderLayout.PAGE_START);
		dt.setColumns(10);
		
		JLabel ul = new JLabel("URL:");
		panelURL.add(ul, BorderLayout.PAGE_START);
		
		JTextField ut = new JTextField();
		panelURL.add(ut,BorderLayout.PAGE_START);
		ut.setColumns(10);
		
		
		JButton ok = new JButton("Ingresar");
		panelAccion.add(ok);

		JButton cancel = new JButton("Cancelar");
		panelAccion.add(cancel);
	}

}
