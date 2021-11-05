package workstation;

import javax.swing.JInternalFrame;

import logica.IActividadDeportivaController;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.ScrollPaneConstants;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;

import javax.swing.border.LineBorder;

import datatypes.TEstado;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.JFrame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class AceptarRechazarActividadDeportiva extends JInternalFrame{
	private JPanel panel;
	private JScrollPane scrollPane;
	private class ARPanel{
		private Component struct = null;
		private JPanel p;
		private JLabel n;
		private JButton accept;
		private JButton reject;
		private String ad;
		ARPanel(String a, IActividadDeportivaController IADC){
			ad = a;
			p = new JPanel();
			p.setBorder(new LineBorder(new Color(0,  0,  0),  1,  true));
			GridBagLayout pg = new GridBagLayout();
			pg.columnWidths = new int[]{0,  0,  0,  0,  0,  0,  0,  0,  0,  0};
			pg.rowHeights = new int[]{0,  0,  0};
			pg.columnWeights = new double[]{0.0,  1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  Double.MIN_VALUE};
			pg.rowWeights = new double[]{0.0,  0.0,  Double.MIN_VALUE};
			p.setLayout(pg);
			
			Component horizontalStrut = Box.createHorizontalStrut(20);
			GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
			gbc_horizontalStrut.insets = new Insets(0,  0,  0,  5);
			gbc_horizontalStrut.gridx = 0;
			gbc_horizontalStrut.gridy = 1;
			p.add(horizontalStrut,  gbc_horizontalStrut);
			
			n = new JLabel(ad);
			GridBagConstraints ng = new GridBagConstraints();
			ng.anchor = GridBagConstraints.WEST;
			ng.insets = new Insets(0,  0,  0,  5);
			ng.gridx = 1;
			ng.gridy = 1;
			p.add(n,  ng);
			
			accept = new JButton("Aceptar");
			GridBagConstraints acceptg = new GridBagConstraints();
			acceptg.insets = new Insets(0,  0,  0,  5);
			acceptg.gridx = 6;
			acceptg.gridy = 1;
			p.add(accept,  acceptg);
			
			reject = new JButton("Rechazar");
			GridBagConstraints rejectg = new GridBagConstraints();
			rejectg.insets = new Insets(0,  0,  0,  5);
			rejectg.gridx = 7;
			rejectg.gridy = 1;
			p.add(reject,  rejectg);
			
			accept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IADC.aprobarActividad(ad,  TEstado.aceptada);
					destruir();			
				}
			});
			reject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IADC.aprobarActividad(ad,  TEstado.rechazada);
					destruir();
				}
			});
			
		}
		public JPanel getPanel() {
			return p;
		}
		private void destruir() {
			AceptarRechazarActividadDeportiva.this.suicidarPanel(this);
		}
		public Component getStruct() {
			return struct;
		}
		public void setStruct(Component struct) {
			this.struct = struct;
		}
	}
	AceptarRechazarActividadDeportiva(IActividadDeportivaController IADC){
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				panel.removeAll();
				Set<String> ADI = IADC.obtenerActDepIngresadas();
				if (ADI.size()!=0) {
					JLabel lblNewLabel = new JLabel("Actividades pendientes de aprobar:");
					scrollPane.setColumnHeaderView(lblNewLabel);
					
					for (String x: ADI) {
						ARPanel p1 = new ARPanel(x, IADC);
						panel.add(p1.getPanel());
						Component hh = Box.createVerticalStrut(5);
						panel.add(hh);
						p1.setStruct(hh);
					}
				} else {
					JLabel lblNewLabel = new JLabel("Actualmente no hay actividades deportivas pendientes de aceptar/rechazar.");
					scrollPane.setColumnHeaderView(lblNewLabel);
				}
			}
		});
        addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
				panel.removeAll();
				Set<String> ADI = IADC.obtenerActDepIngresadas();
				if (ADI.size()!=0) {
					JLabel lblNewLabel = new JLabel("Actividades pendientes de aprobar:");
					scrollPane.setColumnHeaderView(lblNewLabel);
					
					for (String x: ADI) {
						ARPanel p1 = new ARPanel(x, IADC);
						panel.add(p1.getPanel());
						Component hh = Box.createVerticalStrut(5);
						panel.add(hh);
						p1.setStruct(hh);
					}
				} else {
					JLabel lblNewLabel = new JLabel("Actualmente no hay actividades deportivas pendientes de aceptar/rechazar.");
					scrollPane.setColumnHeaderView(lblNewLabel);
				}
        	}
        });
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		setTitle("Aceptar/Rechazar Actividad Deportiva");
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(),  BoxLayout.X_AXIS));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		panel.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				scrollPane.revalidate();
				scrollPane.repaint();
			}
		});
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel,  BoxLayout.Y_AXIS));
		
		Set<String> ADI = IADC.obtenerActDepIngresadas();
		if (ADI.size()!=0) {
			JLabel lblNewLabel = new JLabel("Actividades pendientes de aprobar:");
			scrollPane.setColumnHeaderView(lblNewLabel);
			
			for (String x: ADI) {
				ARPanel p1 = new ARPanel(x, IADC);
				panel.add(p1.getPanel());
				Component hh = Box.createVerticalStrut(5);
				panel.add(hh);
				p1.setStruct(hh);
			}
		} else {
			JLabel lblNewLabel = new JLabel("Actualmente no hay actividades deportivas pendientes de aceptar/rechazar.");
			scrollPane.setColumnHeaderView(lblNewLabel);
		}
		
	}
	
	
	protected void suicidarPanel(ARPanel ar) {
		panel.remove(ar.getPanel());
		panel.remove(ar.getStruct());
	}
	public void clear() {
		// TODO Auto-generated method stub
	}
}
