/* Taller de Programacion - INCO/FING/UDELAR
 * Integrantes:
 *      Alexis Baladon (5.574.612-4) - alexis.baladon@fing.edu.uy
 *      Guillermo Toyos (5.139.879-9) - guillermo.toyos@fing.edu.uy
 *      Jorge Machado (4.876.616-9) - jorge.machado.ottonelli@fing.edu.uy
 *      Juan Jose Mangado (5.535.227-0) - juan.mangado@fing.edu.uy
 *      Mathias Ramilo (5.665.788-5) - mathias.ramilo@fing.edu.uy
 *      
 *      "Nihil novum sub sole"
 */

package workstation;

import logica.LaFabrica;
import main.Main;
import logica.IUsuarioController;
import logica.IActividadDeportivaController;
import logica.IDictadoClaseController;
import logica.ILogger;
import logica.ICuponeraController;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import datatypes.DtActividadDeportiva;
import datatypes.DtCategoria;
import datatypes.DtClase;
import datatypes.DtFecha;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.TEstado;
import datatypes.TReg;

import excepciones.FechaInvalidaException;
import excepciones.NoExisteCuponeraException;
import excepciones.ActividadDeportivaException;
import excepciones.CategoriaException;
import excepciones.ClaseException;
import excepciones.CuponeraInmutableException;
import excepciones.CuponeraNoExisteException;
import excepciones.CuponeraRepetidaException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;

public class Menu {
	private JFrame menuPrincipal;
	private JDesktopPane escritorio;
	private IUsuarioController IUC;
	private IActividadDeportivaController IADC;
	private ICuponeraController IDC;
	private IDictadoClaseController IDCC;
	
	// Declaracion de los JInternalFrames:
	private AltaUsuario altaUsuario;
	private AltaActividadDeportiva altaActDep;
	private AltaDictadoClase altaClase;
	private AltaInstitucionDeportiva altaIns;
	private CrearCuponera altaCup;
	private RegistroUsuarioClase regUsuClass;
	private ConsultaDictadoClase consultaClass;
	private ConsultaActividadDeportiva consActDep;
	private ConsultaCuponeras consultaCup;
	private ConsultaUsuario consultaUsu;
	private ModificarDatosUsuario modificarUsu;
	private AgregarActividadDeportivaCuponera aggCup;
	private AceptarRechazarActividadDeportiva acceptActDep;
	private AltaCategoria altaCat;
	private ILogger IL;
	private LogViewer logviewer;
	/* DEPRECTED MAIN.
	//Run program!
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.getMenuPrincipal().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	//Create main frame.
	public Menu() {
		iniciar();
		
		LaFabrica fabrica = LaFabrica.getInstance();
		IUC = fabrica.obtenerIUsuarioController();
		IADC = fabrica.obtenerIActDeportivaController();
		IDC = fabrica.obtenerICuponeraController();
		IDCC = fabrica.obtenerIDictadoClaseController();	
		IL = fabrica.getILogger();
		//Preinicializacion de JInternalFrames con visibilidad=false
		
		//AltaUsuario:
		altaUsuario = new AltaUsuario(IUC);
		altaUsuario.setLocation(462,  25);
		altaUsuario.setVisible(false);
		escritorio.add(altaUsuario);	
		
		//AltaActividadDeportiva
		altaActDep = new AltaActividadDeportiva(IADC);
		altaActDep.setLocation(20,  20);
		altaActDep.setSize(450,  700);
		altaActDep.setVisible(false);
		escritorio.add(altaActDep);

		// AltaDictadoClase:
		altaClase = new AltaDictadoClase(IDCC);
		altaClase.setLocation(10,  11);
		altaClase.setVisible(false);
		escritorio.add(altaClase);
		
		// AltaInstitucionDeporitva:
		altaIns = new AltaInstitucionDeportiva(IADC);
		altaIns.setBounds(212,  37,  354,  344);
		altaIns.setVisible(false);
		escritorio.add(altaIns);
		
		// RegistroUsuarioClase:
		regUsuClass = new RegistroUsuarioClase(IDCC);
		regUsuClass.setVisible(false);
		escritorio.add(regUsuClass);
		
		// ConsultaDictadoClase:
		consultaClass = new ConsultaDictadoClase(IDCC);
		consultaClass.setBounds(10,  40,  382,  545);
		consultaClass.setVisible(false);
		escritorio.add(consultaClass);
		
		// ConsultaCuponeras:
		consultaCup = new ConsultaCuponeras(IDC);
		consultaCup.setBounds(200,  100,  400,  458);
		consultaCup.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		consultaCup.setVisible(false);
		escritorio.add(consultaCup);
		
		//ConsultaUsuario
		consultaUsu = new ConsultaUsuario(IUC);
		consultaUsu.setVisible(false);
		escritorio.add(consultaUsu);

		// ConsultaActividadDeportiva
		consActDep = new ConsultaActividadDeportiva(IADC);
		consActDep.setBounds(143,  20,  457,  719);
		consActDep.setVisible(false);
		escritorio.add(consActDep);
		
		//ModificarDatosUsuario
		modificarUsu = new ModificarDatosUsuario(IUC);
		modificarUsu.setVisible(false);
		escritorio.add(modificarUsu);
		
		//AltaCuponera
		altaCup = new CrearCuponera(IDC);
		altaCup.setBounds(100,  100,  500,  483);
		altaCup.setVisible(false);
		escritorio.add(altaCup);
		
		//Aggreagar actividad deportiva a cuponera
		aggCup = new AgregarActividadDeportivaCuponera(IDC, IADC);
		aggCup.setVisible(false);
		escritorio.add(aggCup);
		
		//Aceptar rechazar actividad deportiva
		acceptActDep = new AceptarRechazarActividadDeportiva(IADC);
		acceptActDep.setBounds(437,  290,  500,  400);
		acceptActDep.setVisible(false);
		escritorio.add(acceptActDep);
		
		//Logger
		logviewer = new LogViewer(IL);
		logviewer.setBounds(60,  290,  877,  400);
		logviewer.setVisible(false);
		escritorio.add(logviewer);
		
		//Alta Categoria
		altaCat = new AltaCategoria(IADC);
		altaCat.setVisible(false);
		escritorio.add(altaCat);
		
		//Se relacionan los Frames de consultas
		consActDep.setRef(consultaClass, consultaCup, consultaUsu);
		consultaCup.setRef(consActDep);
		consultaUsu.setRef(consultaClass, consActDep);
	}
	
	private void iniciar() {
        // Se crea el Frame con las dimensiones indicadas:
		setMenuPrincipal(new JFrame());
		getMenuPrincipal().setTitle("Entrenamos.uy");
		getMenuPrincipal().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getMenuPrincipal().setBounds(100,  100,  1200,  850);
		getMenuPrincipal().setResizable(true);
		getMenuPrincipal().setIconImage(new ImageIcon(getClass().getResource("/workstation/iconoEntrenamos-uy.png")).getImage());
		
		escritorio = new JDesktopPane();
		escritorio.setBackground(new Color(174,  182,  191));
		getMenuPrincipal().getContentPane().add(escritorio);
		
		// Crear la Barra del Menu:
		JMenuBar menuBar = new JMenuBar();
		getMenuPrincipal().setJMenuBar(menuBar);
		
		JMenu menuInicio = new JMenu("Inicio\r\n");
		menuBar.add(menuInicio);
		
		JMenuItem itemIrInicio = new JMenuItem("Limpiar Escritorio");
		menuInicio.add(itemIrInicio);
		itemIrInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capaDeInvisibilidad();
			}
		});
		JMenuItem itemPueba = new JMenuItem("Cargar Datos Prueba");
		menuInicio.add(itemPueba);
		itemPueba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatosPrueba();
			}
		});

		JMenuItem itemSalir = new JMenuItem("Salir");
		menuInicio.add(itemSalir);
		itemSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMenuPrincipal().dispose();
			}
		});
		JMenu menuRegistro = new JMenu("Registros");
		menuBar.add(menuRegistro);
		
		JMenu subMenuUsuario = new JMenu("Usuario");
		menuRegistro.add(subMenuUsuario);
		
		JMenuItem itemRegistrarUsuario = new JMenuItem("Alta Usuario");
		subMenuUsuario.add(itemRegistrarUsuario);
		itemRegistrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (altaUsuario.isVisible()) 
					altaUsuario.toFront();
				else {
					altaUsuario.clear();
					altaUsuario.setVisible(true);
				}
			}
		});		
		
		JMenu subMenuInstitucion = new JMenu("Institucion");
		menuRegistro.add(subMenuInstitucion);
		
		JMenuItem itemAltaInstitucion = new JMenuItem("Alta Institucion Deportiva");
		subMenuInstitucion.add(itemAltaInstitucion);
		itemAltaInstitucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (altaIns.isVisible()) 
					altaIns.toFront();
				else {
					altaIns.clear();
					altaIns.setVisible(true);
				}
			}
		});
		
		JMenu subMenuActDep = new JMenu("Actividad Deportiva");
		menuRegistro.add(subMenuActDep);
		
		JMenuItem itemAltaActividad = new JMenuItem("Alta Actividad Deportiva");
		subMenuActDep.add(itemAltaActividad);
		itemAltaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (altaActDep.isVisible()) 
					altaActDep.toFront();
				else {
					altaActDep.clear();
					//altaActDep.cargarInstituciones(); NO!
					altaActDep.setVisible(true);
				}
			}
		});		
		
		JMenu subMenuDictado = new JMenu("Dictado Clase");
		menuRegistro.add(subMenuDictado);
		
		JMenuItem itemAltaDictado = new JMenuItem("Alta de Dictado de Clase");
		itemAltaDictado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (altaClase.isVisible())
					altaClase.toFront();
				else {
					altaClase.clear();
					altaClase.setVisible(true);
				}
			}
		});
		subMenuDictado.add(itemAltaDictado);
		
		JMenuItem itemRegistroAClase = new JMenuItem("Registro de Usuario a Dictado de Clase");
		itemRegistroAClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (regUsuClass.isVisible())
					regUsuClass.toFront();
				else {
					regUsuClass.clear();
					regUsuClass.setVisible(true);
				}
			}
		});
		subMenuDictado.add(itemRegistroAClase);
		
		JMenu subMenuCuponera = new JMenu("Cuponera");
		menuRegistro.add(subMenuCuponera);

		JMenuItem itemCrearCuponera = new JMenuItem("Crear Cuponera");
		subMenuCuponera.add(itemCrearCuponera);
		itemCrearCuponera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (altaCup.isVisible()) 
					altaCup.toFront();
				else {
					altaCup.clear();
					altaCup.setVisible(true);
				}
			}
		});
		
		JMenuItem itemAgregarActividad = new JMenuItem("Agregar Actividad Deportiva");
		subMenuCuponera.add(itemAgregarActividad);
		itemAgregarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (aggCup.isVisible()) 
					aggCup.toFront();
				else {
					aggCup.clear();
					aggCup.setVisible(true);
				}
			}
		});
		
		JMenu subMenuCategoria = new JMenu("Categoria");
		menuRegistro.add(subMenuCategoria);

		JMenuItem itemCrearCategoria = new JMenuItem("Crear Categoria");
		subMenuCategoria.add(itemCrearCategoria);
		itemCrearCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (altaCat.isVisible()) 
					altaCat.toFront();
				else {
					altaCat.clear();
					altaCat.setVisible(true);
				}
			}
		});
		
		JMenu menuConsultas = new JMenu("Consultas");
		menuBar.add(menuConsultas);
		
		JMenuItem itemConsultaUsuario = new JMenuItem("Consulta Usuario");
		menuConsultas.add(itemConsultaUsuario);
		itemConsultaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (consultaUsu.isVisible()) 
					consultaUsu.toFront();
				else {
					consultaUsu.clear();
					consultaUsu.setVisible(true);
				}
			}
		});
		JMenuItem itemConsultaActividad = new JMenuItem("Consulta Actividad Deportiva");
		menuConsultas.add(itemConsultaActividad);
		itemConsultaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (consActDep.isVisible()) 
					consActDep.toFront();
				else {
					consActDep.clear();
					consActDep.setVisible(true);
				}
			}
		});
		
		JMenuItem itemConsultaClase = new JMenuItem("Consulta de Dictado de Clase");
		menuConsultas.add(itemConsultaClase);
		itemConsultaClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (consultaClass.isVisible()) 
					consultaClass.toFront();
				else {
					consultaClass.clear();
					consultaClass.setVisible(true);
				}
			}
		});
		
		JMenuItem itemConsultaCuponera = new JMenuItem("Consulta de Cuponeras");
		menuConsultas.add(itemConsultaCuponera);
		itemConsultaCuponera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (consultaCup.isVisible())
					consultaCup.toFront();
				else {
					// altaClase.limpiar() //no IMplementado aun.
					consultaCup.setVisible(true);
				}
			}
		});
		
		JMenu menuModificaciones = new JMenu("Modificaciones");
		menuBar.add(menuModificaciones);
		
		JMenu registrosMenu = new JMenu("Registros");
		menuBar.add(registrosMenu);
		JMenuItem registrosSitioItem = new JMenuItem("Modificar Datos Usuario");
		registrosMenu.add(registrosSitioItem);
		registrosSitioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (logviewer.isVisible()) 
					logviewer.toFront();
				else {
					logviewer.setVisible(true);
				}
			}
		});

		JMenuItem itemModUsuario = new JMenuItem("Modificar Datos Usuario");
		menuModificaciones.add(itemModUsuario);
		itemModUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (modificarUsu.isVisible()) 
					modificarUsu.toFront();
				else {
					modificarUsu.clear();
					modificarUsu.setVisible(true);
				}
			}
		});
		JMenuItem itemAcceptRejectActD = new JMenuItem("Aceptar/Rechazar Actividad Deportiva");
		menuModificaciones.add(itemAcceptRejectActD);
		itemAcceptRejectActD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (acceptActDep.isVisible()) 
					acceptActDep.toFront();
				else {
					acceptActDep.clear();
					acceptActDep.setVisible(true);
				}
			}
		});
	}
	
	private void capaDeInvisibilidad() {
		altaUsuario.setVisible(false);
		altaActDep.setVisible(false);
		altaClase.setVisible(false);
		altaIns.setVisible(false);
	    altaCup.setVisible(false);
		regUsuClass.setVisible(false);
		consultaClass.setVisible(false);
		consActDep.setVisible(false);
		consultaCup.setVisible(false);
		consultaUsu.setVisible(false);
		modificarUsu.setVisible(false);
		aggCup.setVisible(false);
	}
	
	private void cargarDatosPrueba() {
		try {
			if(IADC.obtenerInstituciones().size()>0) {
	        	JOptionPane.showMessageDialog(escritorio, "Los datos de prueba solo pueden cargarse con el sistema vacío.",  "Info",  JOptionPane.ERROR_MESSAGE);
				return;
			}
			Main.cargaDeCasos();
		    JOptionPane.showMessageDialog(escritorio,  "Se han cargado los datos de prueba exitosamente.",  
		    		"Info",  JOptionPane.INFORMATION_MESSAGE);
        } catch (FechaInvalidaException e) {
        	JOptionPane.showMessageDialog(escritorio,  "Ha ocurrido un error durante la carga de casos de prueba: " +
        			e.getMessage(),  "Info",  JOptionPane.ERROR_MESSAGE);
        } catch (ClaseException e) {
        	JOptionPane.showMessageDialog(escritorio,  "Ha ocurrido un error durante la carga de casos de prueba: " +
        			e.getMessage(),  "Info",  JOptionPane.ERROR_MESSAGE);
        } catch (NoExisteCuponeraException e) {
        	JOptionPane.showMessageDialog(escritorio,  "Ha ocurrido un error durante la carga de casos de prueba: " +
        			e.getMessage(),  "Info",  JOptionPane.ERROR_MESSAGE);
        } catch (InstitucionException e) {
        	JOptionPane.showMessageDialog(escritorio,  "Ha ocurrido un error durante la carga de casos de prueba: " +
        			e.getMessage(),  "Info",  JOptionPane.ERROR_MESSAGE);
        } catch (UsuarioNoExisteException e) {
        	JOptionPane.showMessageDialog(escritorio,  "Ha ocurrido un error durante la carga de casos de prueba: " +
        			e.getMessage(),  "Info",  JOptionPane.ERROR_MESSAGE);
        } catch (ActividadDeportivaException e) {
        	JOptionPane.showMessageDialog(escritorio,  "Ha ocurrido un error durante la carga de casos de prueba: " +
        			e.getMessage(),  "Info",  JOptionPane.ERROR_MESSAGE);
		} catch (CuponeraRepetidaException e) {
			JOptionPane.showMessageDialog(escritorio,  "Ha ocurrido un error durante la carga de casos de prueba: " +
        			e.getMessage(),  "Info",  JOptionPane.ERROR_MESSAGE);
		} catch (CuponeraInmutableException e) {
			JOptionPane.showMessageDialog(escritorio,  "Ha ocurrido un error durante la carga de casos de prueba: " +
	    			e.getMessage(),  "Info",  JOptionPane.ERROR_MESSAGE);
		} catch (CategoriaException e) {
			JOptionPane.showMessageDialog(escritorio,  "Ha ocurrido un error durante la carga de casos de prueba: " +
	    			e.getMessage(),  "Info",  JOptionPane.ERROR_MESSAGE);
		} catch (CuponeraNoExisteException e) {
			JOptionPane.showMessageDialog(escritorio,  "Ha ocurrido un error durante la carga de casos de prueba: " +
	    			e.getMessage(),  "Info",  JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(escritorio,  "Ha ocurrido un error excepcional durante la carga de casos de prueba: " +
	    			e.getMessage(),  "Info",  JOptionPane.ERROR_MESSAGE);
		}
    }

	public JFrame getMenuPrincipal() {
		return menuPrincipal;
	}

	public void setMenuPrincipal(JFrame menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
	}
}
