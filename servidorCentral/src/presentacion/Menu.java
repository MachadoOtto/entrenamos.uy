import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class Menu extends JFrame {

	private JPanel contentPane;
	public static JDesktopPane desktopPane;
	private JMenu mnNewMenu_1;
	private JMenu mnNewMenu_2;
	private JMenu mnNewMenu_3;
	private JMenu mnNewMenu_4;
	private JMenu mnNewMenu_5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setTitle("ENTRENAMOS.UY");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 100, 1000, 800);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuInicio = new JMenu("Inicio\r\n");
		menuBar.add(menuInicio);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Ir a Inicio");
		menuInicio.add(mntmNewMenuItem_11);

		JMenuItem mntmNewMenuItem_salir = new JMenuItem("Salir");
		menuInicio.add(mntmNewMenuItem_salir);
		
		JMenu mnNewMenu = new JMenu("Registros");
		menuBar.add(mnNewMenu);
		
		mnNewMenu_1 = new JMenu("Usuario");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem registrarUsuario = new JMenuItem("Registrar Usuario");
		mnNewMenu_1.add(registrarUsuario);
		registrarUsuario.setIcon(null);
		
		mnNewMenu_3 = new JMenu("Institucion");
		mnNewMenu.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Alta Institucion Deportiva");
		mnNewMenu_3.add(mntmNewMenuItem_10);
		
		mnNewMenu_2 = new JMenu("Actividad Deportiva");
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Alta Actividad Deportiva");
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		mnNewMenu_4 = new JMenu("Dictado Clase");
		mnNewMenu.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Alta Dictado Clase");
		mnNewMenu_4.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Registro a Dictado Clase");
		mnNewMenu_4.add(mntmNewMenuItem_6);
		
		mnNewMenu_5 = new JMenu("Cuponera");
		mnNewMenu.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Crear Cuponera");
		mnNewMenu_5.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Agregar Actividad Deportiva");
		mnNewMenu_5.add(mntmNewMenuItem_8);
		
		JMenu menuRegistros = new JMenu("Consultas");
		menuBar.add(menuRegistros);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Consulta Usuario");
		menuRegistros.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Consulta Actividad Deportiva");
		menuRegistros.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Consulta Dictado Clase");
		menuRegistros.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Consulta de Cuponeras");
		menuRegistros.add(mntmNewMenuItem_9);
		
		JMenu menuConsultas = new JMenu("Modificaciones");
		menuBar.add(menuConsultas);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Modificar Datos Usuario");
		menuConsultas.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBorder(null);
		desktopPane_1.setBackground(new Color(102, 153, 255));
		contentPane.add(desktopPane_1, BorderLayout.CENTER);
		GridBagLayout gbl_desktopPane_1 = new GridBagLayout();
		gbl_desktopPane_1.columnWidths = new int[]{248, 451, 0};
		gbl_desktopPane_1.rowHeights = new int[]{271, 93, 0};
		gbl_desktopPane_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_desktopPane_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		desktopPane_1.setLayout(gbl_desktopPane_1);
		
		JLabel lblNewLabel = new JLabel("ENTRENAMOS.UY");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Lucida Sans", Font.BOLD, 50));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		desktopPane_1.add(lblNewLabel, gbc_lblNewLabel);
	}
}