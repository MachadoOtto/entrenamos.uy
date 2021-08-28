/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 */

package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTree;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Set;

import logica.IDictadoClaseController;

import datatypes.DtClaseExt;

@SuppressWarnings("serial")
public class ConsultaDictadoClase extends JInternalFrame {
	
	/* Controlador de Dictado de Clase para las acciones del JInternalFrame */
	private IDictadoClaseController controlClase;
	
	/* Componentes graficas */
	// JLabel:
	private JLabel lblSeleccionInstitucion;
	private JLabel lblSeleccionActividad;
	private JLabel lblSeleccionClase;
	
	// JComboBox:
	private JComboBox<String> boxInstitucion;
	private JComboBox<String> boxActividad;
	private JComboBox<String> boxClase;
	private JTree treeCuponera;
	private JLabel lblInformacin;
    
    /* Crear frame */
	public ConsultaDictadoClase(IDictadoClaseController idcc) {
		// Inicializa controlador Dictado de Clase:
		controlClase = idcc;
		
		// Propiedades del JInternalFrame:
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consulta de Dictado de Clase");
		setBounds(10, 40, 392, 418);
		
		// GridLayout:
		GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[] { 30, 60, 60, 30, 30, 10 };
	    gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 };
	    gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
	    gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0 };
	    getContentPane().setLayout(gridBagLayout);
        
        // JLabels:
        lblSeleccionInstitucion = new JLabel("Seleccione Institucion:");
        lblSeleccionInstitucion.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionInstitucion = new GridBagConstraints();
        gbc_lblSeleccionInstitucion.gridwidth = 2;
        gbc_lblSeleccionInstitucion.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionInstitucion.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionInstitucion.gridx = 1;
        gbc_lblSeleccionInstitucion.gridy = 0;
        getContentPane().add(lblSeleccionInstitucion, gbc_lblSeleccionInstitucion);
        
        lblSeleccionActividad = new JLabel("Seleccione Actividad Deportiva:");
        lblSeleccionActividad.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionActividad = new GridBagConstraints();
        gbc_lblSeleccionActividad.gridwidth = 2;
        gbc_lblSeleccionActividad.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionActividad.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionActividad.gridx = 1;
        gbc_lblSeleccionActividad.gridy = 2;
        getContentPane().add(lblSeleccionActividad, gbc_lblSeleccionActividad);
        
        lblSeleccionClase = new JLabel("Seleccione Clase:");
        lblSeleccionClase.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblSeleccionClase = new GridBagConstraints();
        gbc_lblSeleccionClase.gridwidth = 2;
        gbc_lblSeleccionClase.fill = GridBagConstraints.BOTH;
        gbc_lblSeleccionClase.insets = new Insets(0, 0, 5, 5);
        gbc_lblSeleccionClase.gridx = 1;
        gbc_lblSeleccionClase.gridy = 4;
        getContentPane().add(lblSeleccionClase, gbc_lblSeleccionClase);
        
        // JComboBox:
        boxInstitucion = new JComboBox<>();
        cargarInstitucion();
        boxInstitucion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Set<String> tt = controlClase.obtenerInstituciones();
				if(boxInstitucion.getItemCount()!=tt.size()+1) {
					String t = (String) boxInstitucion.getSelectedItem();
					boxInstitucion.removeAllItems();
					boxInstitucion.addItem("-");
					for(String x: controlClase.obtenerInstituciones()) {
						boxInstitucion.addItem(x);
					}
					boxInstitucion.setSelectedItem(t);
				}
			}
		});
        boxInstitucion.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		int selectIndex = boxInstitucion.getSelectedIndex();
    			boxActividad.removeAllItems();
    			DefaultComboBoxModel<String> modelActividad = new DefaultComboBoxModel<>();
    			modelActividad.addElement("-");
    			if (selectIndex > 0) {
        			Set<String> actividades = controlClase.obtenerActividades(boxInstitucion.getItemAt(selectIndex));
                    for (String x: actividades) {
                    	modelActividad.addElement(x);
                    }
                    boxActividad.setEnabled(true);
        		} else {
        			boxActividad.setEnabled(false);
        			treeCuponera.setModel(new DefaultTreeModel(
        					new DefaultMutableTreeNode("root") {
        						{
        							//WindowBuilder BUG: se cambia a getContentPane.add() por algun motivo. Dejarlo solo como add();
        							// Cada vez que se abre la ventan design hay que corregirlo xddd;
        							add(new DefaultMutableTreeNode("Seleccione una clase para desplegar su info."));
        						}
        					}
        				));
        		}
            	boxActividad.setModel(modelActividad);
        	}
        });
        GridBagConstraints gbc_boxInstitucion = new GridBagConstraints();
        gbc_boxInstitucion.gridwidth = 3;
        gbc_boxInstitucion.insets = new Insets(0, 0, 5, 0);
        gbc_boxInstitucion.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxInstitucion.gridx = 1;
        gbc_boxInstitucion.gridy = 1;
        getContentPane().add(boxInstitucion, gbc_boxInstitucion);
        
        boxActividad = new JComboBox<>();
        boxActividad.setEnabled(false);
        boxActividad.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		int selectIndex = boxActividad.getSelectedIndex();
    			boxClase.removeAllItems();
    			DefaultComboBoxModel<String> modelClase = new DefaultComboBoxModel<>();
    			modelClase.addElement("-");
    			if (selectIndex > 0) {
        			Set<String> clases = controlClase.obtenerClases(boxInstitucion.getItemAt(boxInstitucion.getSelectedIndex()), 
        					boxActividad.getItemAt(selectIndex));
                    for (String x: clases) {
                    	modelClase.addElement(x);
                    }
                    boxClase.setEnabled(true);
        		} else {
        			boxClase.setEnabled(false);
        			treeCuponera.setModel(new DefaultTreeModel(
        					new DefaultMutableTreeNode("root") {
        						{
        							//WindowBuilder BUG: se cambia a getContentPane.add() por algun motivo. Dejarlo solo como add();
        							// Cada vez que se abre la ventan design hay que corregirlo xddd;
        							add(new DefaultMutableTreeNode("Seleccione una clase para desplegar su info."));
        						}
        					}
        				));
        		}
    			boxClase.setModel(modelClase);
        	}
        });
        GridBagConstraints gbc_boxActividad = new GridBagConstraints();
        gbc_boxActividad.gridwidth = 3;
        gbc_boxActividad.insets = new Insets(0, 0, 5, 0);
        gbc_boxActividad.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxActividad.gridx = 1;
        gbc_boxActividad.gridy = 3;
        getContentPane().add(boxActividad, gbc_boxActividad);
        
        boxClase = new JComboBox<>();
        GridBagConstraints gbc_boxClase = new GridBagConstraints();
        gbc_boxClase.gridwidth = 3;
        gbc_boxClase.insets = new Insets(0, 0, 5, 0);
        gbc_boxClase.fill = GridBagConstraints.HORIZONTAL;
        gbc_boxClase.gridx = 1;
        gbc_boxClase.gridy = 5;
        getContentPane().add(boxClase, gbc_boxClase);
        boxClase.setEnabled(false);
        boxClase.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if(boxClase.getSelectedIndex() > 0 && boxInstitucion.getSelectedIndex()>0 && boxActividad.getSelectedIndex()>0) {
        			desplegarClase();
        		}
        		else {
        			treeCuponera.setModel(new DefaultTreeModel(
        					new DefaultMutableTreeNode("root") {
        						{
        							//WindowBuilder BUG: se cambia a getContentPane.add() por algun motivo. Dejarlo solo como add();
        							// Cada vez que se abre la ventan design hay que corregirlo xddd;
        							add(new DefaultMutableTreeNode("Seleccione una clase para desplegar su info."));
        						}
        					}
        				));
        		}
        	}
        });
        lblInformacin = new JLabel("Informaci\u00F3n:");
        lblInformacin.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblInformacin = new GridBagConstraints();
        gbc_lblInformacin.anchor = GridBagConstraints.WEST;
        gbc_lblInformacin.insets = new Insets(0, 0, 5, 5);
        gbc_lblInformacin.gridx = 1;
        gbc_lblInformacin.gridy = 6;
        getContentPane().add(lblInformacin, gbc_lblInformacin);
        
        treeCuponera = new JTree();
        treeCuponera.setRootVisible(false);
		treeCuponera.setRootVisible(false);
		treeCuponera.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("root") {
					{
						//WindowBuilder BUG: se cambia a getContentPane.add() por algun motivo. Dejarlo solo como add();
						// Cada vez que se abre la ventan design hay que corregirlo xddd;
						add(new DefaultMutableTreeNode("Seleccione una clase para desplegar su info."));
					}
				}
			));
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		treeCuponera.setBorder(BorderFactory.createCompoundBorder(border, 
			      BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints gbc_treeCuponera = new GridBagConstraints();
        gbc_treeCuponera.gridwidth = 3;
        gbc_treeCuponera.gridheight = 4;
        gbc_treeCuponera.fill = GridBagConstraints.BOTH;
        gbc_treeCuponera.gridx = 1;
        gbc_treeCuponera.gridy = 7;
        getContentPane().add(treeCuponera, gbc_treeCuponera);
	}

	// Cargar Datos al JComboBox
    // Se invoca el método antes de hacer visible el JInternalFrame
    public void cargarInstitucion() {
        DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<>();
        model.addElement("-");
        for(String x: controlClase.obtenerInstituciones()) {
            model.addElement(x);
        }
        boxInstitucion.setModel(model);
    }
    
    private void desplegarClase() {
        String nombreInstitucion = boxInstitucion.getItemAt(boxInstitucion.getSelectedIndex());
        String nombreActividad = boxActividad.getItemAt(boxActividad.getSelectedIndex());
        String nombreClase = boxClase.getItemAt(boxClase.getSelectedIndex());
        DtClaseExt x = controlClase.seleccionarClase(nombreInstitucion, nombreActividad, nombreClase);
        
		treeCuponera.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Cuponera \""+x.getNombre()+"\"") {
					{
						add(new DefaultMutableTreeNode("Nombre: "+x.getNombre()));
						add(new DefaultMutableTreeNode("Fecha de Inicio: "+x.getFechaClase().toFechaHora()));
						add(new DefaultMutableTreeNode("Profesor que la Dicta: "+ x.getNicknameProfesor()));
						DefaultMutableTreeNode nodoF = new DefaultMutableTreeNode("Cantidad de Cupos");
						nodoF.add(new DefaultMutableTreeNode("Min: "+x.getMinSocios()+" socios."));
						nodoF.add(new DefaultMutableTreeNode("Max: "+x.getMaxSocios()+" socios."));
						add(nodoF);
						add(new DefaultMutableTreeNode("URL: "+x.getURL()));
						add(new DefaultMutableTreeNode("Fecha de registro: "+x.getFechaRegistro().toFecha()));
						
						DefaultMutableTreeNode nodoA = new DefaultMutableTreeNode("Socios inscriptos");
						for(String v: x.getAlumnos()) {
							nodoA.add(new DefaultMutableTreeNode(v));
						}
						if(nodoA.getChildCount()==0) {
							nodoA.add(new DefaultMutableTreeNode("No hay socios inscriptos a esta clase."));
						}
						add(nodoA);
					}
				}
			));
    }
    
    // Limpia el JInternalFrame
 	public void clear() {
 		//textArea.setText("Aqui se mostrara la informacion de la Clase seleccionada.");
        boxInstitucion.removeAllItems();
        boxActividad.removeAllItems();
        boxActividad.setEnabled(false);
        boxClase.removeAllItems();
        boxClase.setEnabled(false);
    }

	public void refEntry(String act, String cla) {
        DefaultComboBoxModel<String> model;
        String institf=null;
        model = new DefaultComboBoxModel<>();
        model.addElement("-");
        for(String x: controlClase.obtenerInstituciones()) {
            model.addElement(x);
            for(String y: controlClase.obtenerActividades(x)) {
            	if(y.equals(act)) {
            		institf = x;
            	}
            }
            if(institf != null)
            	break;
        }
        boxInstitucion.setModel(model);
        boxInstitucion.setSelectedItem(institf);
		Set<String> actividades = controlClase.obtenerActividades(institf);
		DefaultComboBoxModel<String> modelActividad = new DefaultComboBoxModel<>();
        for (String x: actividades) {
        	modelActividad.addElement(x);
        }
        boxActividad.setEnabled(true);
        boxActividad.setModel(modelActividad);
        boxActividad.setSelectedItem(act);
		Set<String> clases = controlClase.obtenerClases(institf, act);
		DefaultComboBoxModel<String> modelClases = new DefaultComboBoxModel<>();
        for (String x: clases) {
        	modelClases.addElement(x);
        }
        boxClase.setEnabled(true);
        boxClase.setModel(modelClases);
        boxClase.setSelectedItem(cla);
        desplegarClase();
		if (this.isVisible()) 
			this.toFront();
		else {
			this.setVisible(true);
		}
	}
}