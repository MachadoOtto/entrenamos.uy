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

package presentacion;

import logica.LaFabrica;
import logica.IUsuarioController;
import logica.IActividadDeportivaController;
import logica.IDictadoClaseController;
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
	//Run program!
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.menuPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create main frame.
	public Menu() {
		iniciar();
		
		LaFabrica fabrica = LaFabrica.getInstance();
		IUC = fabrica.obtenerIUsuarioController();
		IADC = fabrica.obtenerIActDeportivaController();
		IDC = fabrica.obtenerICuponeraController();
		IDCC = fabrica.obtenerIDictadoClaseController();	
		
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
		
		//Alta Categoria
		altaCat = new AltaCategoria(IADC);
		altaCat.setVisible(false);
		escritorio.add(altaCat);
		
		//Se relacionan los Frames de consultas
		consActDep.setRef(consultaClass, consultaCup);
		consultaCup.setRef(consActDep);
		consultaUsu.setRef(consultaClass, consActDep);
	}
	
	private void iniciar() {
        // Se crea el Frame con las dimensiones indicadas:
		menuPrincipal = new JFrame();
		menuPrincipal.setTitle("Entrenamos.uy");
		menuPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menuPrincipal.setBounds(100,  100,  1200,  850);
		menuPrincipal.setResizable(true);
		menuPrincipal.setIconImage(new ImageIcon(getClass().getResource("/img/iconoEntrenamos-uy.png")).getImage());
		
		escritorio = new JDesktopPane();
		escritorio.setBackground(new Color(174,  182,  191));
		menuPrincipal.getContentPane().add(escritorio);
		
		// Crear la Barra del Menu:
		JMenuBar menuBar = new JMenuBar();
		menuPrincipal.setJMenuBar(menuBar);
		
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
				menuPrincipal.dispose();
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
			// ALTA INSTITUCIONES
			// Instituto Natural #IN
			IADC.altaInstitucion("Instituto Natural",  "Clases de gimnasia,  aeróbica,  spinning y yoga.", "https://www.inatural.com");
			// Fuerza Bruta #FB
			IADC.altaInstitucion("Fuerza Bruta",  "Gimnasio especializado en el desarrollo de la musculatura.", "https://www.musculos.com");
			// Telón #TL
			IADC.altaInstitucion("Telón",  "Actividades deportivas para todas las edades.", "https://telon.com.uy");
			// Olympic #YT
			IADC.altaInstitucion("Olympic",  "Gimnasia y Aparatos.", "https://www.olympic21.com");
			
			// ALTA USUARIOS

			// SOCIOS
			// Emi71 #EL
			IUC.ingresarDatosUsuario(new DtSocio("Emi71", "Emiliano", "Lucas", "emi71@gmail.com",  "asdfg456",  new DtFecha(1971, 12, 31, 0, 0, 0),  "Emi71.webp".getBytes()));
			// caro #CO
			IUC.ingresarDatosUsuario(new DtSocio("caro", "Carolina", "Omega", "caro@gmail.com",  "123rtgfdv",  new DtFecha(1983, 11, 15, 0, 0, 0),  "caro.webp".getBytes()));
			// euge #EW
			IUC.ingresarDatosUsuario(new DtSocio("euge", "Eugenia", "Williams", "e.will@gmail.com",  "poiuy086",  new DtFecha(1990, 4, 15, 0, 0, 0),  "euge.jpg".getBytes()));
			// guille #GH
			IUC.ingresarDatosUsuario(new DtSocio("guille", "Guillermo", "Hector", "ghector@gmail.com",  "GTO468",  new DtFecha(1959, 5, 15, 0, 0, 0),  "guille.jpg".getBytes()));
			// sergiop #SP
			IUC.ingresarDatosUsuario(new DtSocio("sergiop", "Sergio", "Perez", "sergi@gmail.com.uy",  "HGF135",  new DtFecha(1950, 1, 28, 0, 0, 0),  "sergiop.jpg".getBytes()));
			// andy #AR
			IUC.ingresarDatosUsuario(new DtSocio("andy", "Andrés", "Roman", "chino@gmail.org.uy",  "lkj65D",  new DtFecha(1976, 3, 17, 0, 0, 0),  "andy.jpg".getBytes()));
			// tonyp #AP
			IUC.ingresarDatosUsuario(new DtSocio("tonyp", "Antonio", "Paz", "eltony@gmail.org.uy",  "jhvf395",  new DtFecha(1955, 2, 14, 0, 0, 0),  "tonyp.jpg".getBytes()));
			// m1k4 #ML
			IUC.ingresarDatosUsuario(new DtSocio("m1k4", "Micaela", "Lopez", "mika@gmail.com.ar",  "ijngr024",  new DtFecha(1987, 2, 23, 0, 0, 0),  "m1k4.webp".getBytes()));
			// charly #CB
			IUC.ingresarDatosUsuario(new DtSocio("charly", "Carlos", "Boston", "charly@gmail.com.uy",  "987mnbgh",  new DtFecha(1937, 5, 8, 0, 0, 0),  "charly.jpg".getBytes()));	
			
			// PROFESORES
			String desc;
			String bio;
			// viktor #VP
			desc = "Victor es un apasionado de los músculos. Sus clases son organizadas en función de distintos "
					+ "aparatos y pesas con el objetivo de desarrollar músculos.";
			bio = "Victor nació en Moscow en 1977. En el año 2005 emigró a Uruguay luego de quedar "
					+ "encantado con el país en un viaje turístico.";
			IUC.ingresarDatosUsuario(new DtProfesor("viktor", "Victor", "Perez", "vperez@fuerza.com",  "lkj34df",  new DtFecha(1997, 1, 1, 0, 0, 0), 
					"Fuerza Bruta",  desc,  bio , "www.vikgym.com",  "viktor.jpg".getBytes()));
			// denis #DM
			desc = "A Denis le interesan los deportes con pelota,  principalmente el voleibol y el handball.";
			bio = "Denis fue un jugador de voleibol profesional.";
			IUC.ingresarDatosUsuario(new DtProfesor("denis", "Denis", "Miguel", "den80@fuerza.com",  "poke579",  new DtFecha(1980, 6, 14, 0, 0, 0), 
					"Telón",  desc,  bio , "www.depecho.com",  "denis.jpg".getBytes()));
			// clazar #CL
			desc = "Carlos es un profesor muy divertido cuyas clases de aeróbica están cargadas de energía.";
			bio = "El interés por la actividad física llevo a Carlos a dejar su trabajo en un estudio "
					+ "contable y abrir su propio gimnasio.";
			IUC.ingresarDatosUsuario(new DtProfesor("clazar", "Carlos", "Lazaro", "claz4r0@hotmail.com",  "mkji648",  new DtFecha(1953, 6, 22, 0, 0, 0), 
					"Instituto Natural",  desc,  bio , "www.enforma.com",  "clazar.jpg".getBytes()));
			// TheBoss #BS
			desc = "Bruno es un ex-boxeardor que busca entrenar a futuros campeones.";
			bio = "Bruno,  mejor conocido como Bruce en el ring,  compitió como boxeador entre los años 60s y 70s.";
			IUC.ingresarDatosUsuario(new DtProfesor("TheBoss", "Bruno", "Sosa", "bruceTheBoss@gmail.com",  "fcku0123",  new DtFecha(1949, 9, 23, 0, 0, 0), 
					"Fuerza Bruta",  desc,  bio , "www.bruce.net",  "TheBoss.jpg".getBytes()));
			// Nelson #TN
			desc = "Profesor de natación. Especializado en braza y mariposa.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("Nelson", "Luis", "Nelson", "nelson@hotmail.com",  "vbmn4r",  new DtFecha(1998, 1, 1, 0, 0, 0), 
					"Telón",  desc,  bio , "www.nelson.uy",  "Nelson.jpg".getBytes()));
			// lale #LL
			desc = "Luego de una exitosa carrera como jugadora de futbol profesional. Laura dedica sus clases a "
					+ "enseñar tácticas de futbol.";
			bio = "Jugadora profesional de futbol desde 2010 a 2020.";
			IUC.ingresarDatosUsuario(new DtProfesor("lale", "Laura", "Leyes", "la_le@outlook.com",  "ncnl123",  new DtFecha(1987, 2, 14, 0, 0, 0), 
					"Telón",  desc,  bio , "www.laley.com",  "lale.jpg".getBytes()));
			// prisc #PI
			desc = "Laura tiene un gran interés por los deportes olímpicos.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("prisc", "Priscila", "Pappo", "pripa@gmail.com",  "mny101",  new DtFecha(1981, 8, 13, 0, 0, 0), 
					"Olympic",  desc,  bio , "www.pi314.net",  null));
			// dagost #DY
			desc = "Profesora dedicada y exigente. No acepta un " + '"' + "no puedo" + '"' + " como respuesta.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("dagost", "Daiana", "Agostini", "d_1940_ago@gmail.com",  "1o1vbm",  new DtFecha(1940, 3, 5, 0, 0, 0), 
					"Olympic",  desc,  bio , "www.dygym.com",  "dagost.webp".getBytes()));
			// aldo #AL
			desc = "Dada su gran estatura Aldo siempre jugó al basquetbol,  hoy se dedica a enseñarlo.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("aldo", "Aldo", "Vivaldi", "aldo@outlook.com",  "ultraton01",  new DtFecha(1952, 7, 17, 0, 0, 0), 
					"Telón",  desc,  bio , "www.sportsaldo.net",  "aldo.jpg".getBytes()));
				
			//LOS SEGUIDOS/SEGUIDORES
			String[] a = {"Emi71", "caro", "euge", "guille", "sergiop", "andy", "tonyp", "m1k4", "charly", "viktor", "denis", "clazar", "TheBoss", "Nelson", "lale", 
			              "prisc", "dagost", "aldo"};
			IUC.seguir(a[0],  a[3]);
			IUC.seguir(a[1],  a[2]);
			IUC.seguir(a[1],  a[3]);
			IUC.seguir(a[2],  a[0]);
			IUC.seguir(a[2],  a[1]);
			IUC.seguir(a[2],  a[7]);
			IUC.seguir(a[3],  a[0]);
			IUC.seguir(a[3],  a[1]);
			IUC.seguir(a[3],  a[2]);
			IUC.seguir(a[3],  a[12]);
			IUC.seguir(a[4],  a[2]);
			IUC.seguir(a[4],  a[5]);
			IUC.seguir(a[4],  a[11]);
			IUC.seguir(a[5],  a[1]);
			IUC.seguir(a[5],  a[6]);
			IUC.seguir(a[5],  a[11]);
			IUC.seguir(a[6],  a[1]);
			IUC.seguir(a[6],  a[7]);
			IUC.seguir(a[6],  a[8]);
			IUC.seguir(a[7],  a[4]);
			IUC.seguir(a[7],  a[6]);
			IUC.seguir(a[8],  a[6]);
			IUC.seguir(a[8],  a[13]);
			IUC.seguir(a[9],  a[6]);
			IUC.seguir(a[9],  a[7]);
			IUC.seguir(a[9],  a[11]);
			IUC.seguir(a[9],  a[14]);
			IUC.seguir(a[9],  a[15]);
			IUC.seguir(a[10],  a[0]);
			IUC.seguir(a[10],  a[1]);
			IUC.seguir(a[10],  a[2]);
			IUC.seguir(a[10],  a[3]);
			IUC.seguir(a[10],  a[4]);
			IUC.seguir(a[10],  a[5]);
			IUC.seguir(a[10],  a[6]);
			IUC.seguir(a[10],  a[7]);
			IUC.seguir(a[10],  a[8]);
			IUC.seguir(a[11],  a[1]);
			IUC.seguir(a[11],  a[2]);
			IUC.seguir(a[11],  a[3]);
			IUC.seguir(a[11],  a[12]);
			IUC.seguir(a[12],  a[3]);
			IUC.seguir(a[12],  a[5]);
			IUC.seguir(a[12],  a[7]);
			IUC.seguir(a[13],  a[0]);
			IUC.seguir(a[13],  a[5]);
			IUC.seguir(a[13],  a[6]);
			IUC.seguir(a[13],  a[14]);
			IUC.seguir(a[13],  a[15]);
			IUC.seguir(a[13],  a[16]);
			IUC.seguir(a[14],  a[8]);
			IUC.seguir(a[14],  a[13]);
			IUC.seguir(a[15],  a[8]);
			IUC.seguir(a[15],  a[13]);
			IUC.seguir(a[16],  a[6]);
			IUC.seguir(a[16],  a[8]);
			IUC.seguir(a[17],  a[5]);
			IUC.seguir(a[17],  a[6]);
			IUC.seguir(a[17],  a[8]);
			IUC.seguir(a[17],  a[14]);
			IUC.seguir(a[17],  a[15]);
			IUC.seguir(a[17],  a[16]);
			
			
			//CATEGORIAS
			IADC.ingresarCatergoria(new DtCategoria("Al aire libre"));
			IADC.ingresarCatergoria(new DtCategoria("Deportes"));
			IADC.ingresarCatergoria(new DtCategoria("Fitness"));
			IADC.ingresarCatergoria(new DtCategoria("Gimnasia"));
			
			//CATEGOIRAS DE LAS ACTDEP
			Set<String> A1cat = new HashSet<>(); A1cat.add("Fitness");
			Set<String> A2cat = new HashSet<>(); A2cat.add("Gimnasia"); A2cat.add("Deportes");
			Set<String> A3cat = new HashSet<>(); A3cat.add("Al aire libre");
			Set<String> A4cat = new HashSet<>(); A4cat.add("Deportes");
			Set<String> A5cat = new HashSet<>(); A5cat.add("Deportes");
			Set<String> A6cat = new HashSet<>(); A6cat.add("Deportes");
			Set<String> A7cat = new HashSet<>(); A7cat.add("Fitness");
			Set<String> A8cat = new HashSet<>(); A8cat.add("Gimnasia");
			Set<String> A9cat = new HashSet<>(); A9cat.add("Deportes"); A9cat.add("Al aire libre");
			Set<String> A10cat = new HashSet<>();A10cat.add("Gimnasia");
			
			// ALTA ACTIVIDAD DEPORTIVA
	        // Aparatos y pesas #A1
			IADC.ingresarDatosActividadDep("Fuerza Bruta",  new DtActividadDeportiva("Aparatos y pesas", 
					"Clases de aparatos,  pesas y calistenia.",  90,  550,  new DtFecha(2021, 3, 31, 0, 0, 0),  A1cat,  TEstado.aceptada,  "viktor", "Aparatos y pesas.jpeg"));
			// Voleibol #A2
			IADC.ingresarDatosActividadDep("Telón",  new DtActividadDeportiva("Voleibol", 
					"Voleibol en todas sus formas.",  120,  750,  new DtFecha(2021, 4, 20, 0, 0, 0),  A2cat,  TEstado.aceptada,  "denis", "Voleibol.jpeg"));
			// Aeróbica #A3
			IADC.ingresarDatosActividadDep("Instituto Natural",  new DtActividadDeportiva("Aeróbica", 
					"Para cuidar el aparato cardiovascular.",  110,  800,  new DtFecha(2021, 5, 30, 0, 0, 0),  A3cat,  TEstado.aceptada,  "Administrador", "Aeróbica.jpg"));
			// Kickboxing #A4
			IADC.ingresarDatosActividadDep("Fuerza Bruta",  new DtActividadDeportiva("Kickboxing", 
					"En busca del nuevo campeón de boxeo.",  100,  980,  new DtFecha(2021, 6, 7, 0, 0, 0),  A4cat,  TEstado.aceptada,  "TheBoss", "Kickboxing.jpg"));
			// Atletismo #A5
			IADC.ingresarDatosActividadDep("Telón",  new DtActividadDeportiva("Atletismo", 
					"100m ,  200m,  postas y carreras con obstaculos.",  150,  500,  new DtFecha(2021, 7, 8, 0, 0, 0),  A5cat,  TEstado.aceptada,  "denis", "Atletismo.webp"));
			// Basquetbol #A6
			IADC.ingresarDatosActividadDep("Telón",  new DtActividadDeportiva("Basquetbol", 
					"Espectáculo conmemorando los 30 años de Violeta.",  80,  450,  new DtFecha(2021, 7, 31, 0, 0, 0),  A6cat,  TEstado.aceptada,  "Nelson", "Basquetbol.jpg"));
	        // AparatosII #A7
			IADC.ingresarDatosActividadDep("Fuerza Bruta",  new DtActividadDeportiva("Aparatos II", 
					"Clases de aparatos avanzadas.",  60,  1500,  new DtFecha(2021, 8, 15, 0, 0, 0),  A7cat,  TEstado.rechazada,  "Administrador"));
			// Pilates #A8
			IADC.ingresarDatosActividadDep("Instituto Natural",  new DtActividadDeportiva("Pilates", 
					"El Método Pilates combina diferentes capacidades físicas.",  45,  600,  new DtFecha(2021, 8, 30, 0, 0, 0),  A8cat,  TEstado.ingresada,  "clazar"));
			// VoleibolII #A9
			IADC.ingresarDatosActividadDep("Telón",  new DtActividadDeportiva("Voleibol II", 
					"Voleibol avanzado.",  120,  1000,  new DtFecha(2021, 9, 1, 0, 0, 0),  A9cat,  TEstado.rechazada,  "denis", "Voleibol II.jpeg"));
			// BasquetbolII #A10
			IADC.ingresarDatosActividadDep("Telón",  new DtActividadDeportiva("Basquetbol II", 
					"Basequetbol avanzado.",  80,  600,  new DtFecha(2021, 9, 7, 0, 0, 0),  A10cat,  TEstado.ingresada,  "denis"));
			
	        // ALTA CLASE
	        // Calistenia #C1
	        IDCC.ingresarDatosClase("Fuerza Bruta",  "Aparatos y pesas",  new DtClase("Calistenia",  "viktor",  "viktor",  
	        		1,  5,  "https://www.musculos.com/Calistenia",  new DtFecha(2021, 4, 15, 15, 30, 0),  new DtFecha(2021, 3, 31, 0, 0, 0),  "Calistenia.jpeg"));
	        // Peso libre #C2
	        IDCC.ingresarDatosClase("Fuerza Bruta",  "Aparatos y pesas",  new DtClase("Peso libre",  "viktor",  "viktor",  
	        		1,  5,  "https://www.musculos.com/pesolibre",  new DtFecha(2021, 5, 1, 17, 0, 0),  new DtFecha(2021, 3, 31, 0, 0, 0),  "PesoLibre.jpg"));
	        // Aparatos #C3
	        IDCC.ingresarDatosClase("Fuerza Bruta",  "Aparatos y pesas",  new DtClase("Aparatos",  "viktor",  "viktor",  
	        		1,  7,  "https://www.musculos.com/aparatos",  new DtFecha(2021, 6, 1, 18, 0, 0),  new DtFecha(2021, 3, 31, 0, 0, 0),  "Aparatos.jpg"));
	        // Voleibol #C4
	        IDCC.ingresarDatosClase("Telón",  "Voleibol",  new DtClase("Voleibol",  "denis",  "denis", 
	        		10,  21,  "https://telon.com.uy/voley",  new DtFecha(2021, 6, 10, 19, 0, 0),  new DtFecha(2021, 4, 20, 0, 0, 0), "Voleibol.jpeg"));
	        // Braza #C5
	        IDCC.ingresarDatosClase("Telón",  "Voleibol",  new DtClase("Braza",  "Nelson",  "Nelson", 
	        		2,  6,  "https://telon.com.uy/natacionB",  new DtFecha(2021, 7, 10, 20, 0, 0),  new DtFecha(2021, 4, 20, 0, 0, 0),  "Braza.jpg"));
	        // Mariposa #C6
	        IDCC.ingresarDatosClase("Telón",  "Voleibol",  new DtClase("Mariposa",  "Nelson",  "Nelson", 
	        		2,  6,  "https://telon.com.uy/natacionM",  new DtFecha(2021, 8, 10, 17, 45, 0),  new DtFecha(2021, 4, 20, 0, 0, 0),  "Mariposa.jpeg"));
	        // Aeróbica niños #C7
	        IDCC.ingresarDatosClase("Instituto Natural",  "Aeróbica",  new DtClase("Aeróbica niños",  "clazar",  "clazar", 
	        		5,  10,  "https://www.inatural.com/aeroni",  new DtFecha(2021, 8, 15, 16, 30, 0),  new DtFecha(2021, 5, 30, 0, 0, 0), "Aeróbica niños.webp"));
	        // Aeróbico adulto mayor #C8
	        IDCC.ingresarDatosClase("Instituto Natural",  "Aeróbica",  new DtClase("Aeróbico adulto mayor",  "clazar",  "clazar", 
	        		5,  12,  "https://www.inatural.com/aeroam",  new DtFecha(2021, 8, 31, 19, 30, 0),  new DtFecha(2021, 5, 30, 0, 0, 0),  "Aeróbico adulto mayor.jpg"));
	        // Aeróbico #C9
	        IDCC.ingresarDatosClase("Instituto Natural",  "Aeróbica",  new DtClase("Aeróbica",  "clazar",  "clazar", 
	        		5,  20,  "https://www.inatural.com/aerogral",  new DtFecha(2021, 9, 30, 20, 0, 0),  new DtFecha(2021, 5, 30, 0, 0, 0), "Aeróbica.gif"));
	        // Boxeo I #C10
	        IDCC.ingresarDatosClase("Fuerza Bruta",  "Kickboxing",  new DtClase("Boxeo I",  "TheBoss",  "TheBoss", 
	        		1,  4,  "https://www.musculos.com/boxeo1",  new DtFecha(2021, 9, 1, 19, 30, 0),  new DtFecha(2021, 6, 7, 0, 0, 0),  "Boxeo I.jpg"));
	        // Boxeo II #C11
	        IDCC.ingresarDatosClase("Fuerza Bruta",  "Kickboxing",  new DtClase("Boxeo II",  "TheBoss",  "TheBoss", 
	        		2,  2,  "https://www.musculos.com/boxeo2",  new DtFecha(2021, 9, 30, 17, 0, 0),  new DtFecha(2021, 6, 7, 0, 0, 0),  "Boxeo II.jpg"));
	        // Músculos para boxeo #C12
	        IDCC.ingresarDatosClase("Fuerza Bruta",  "Kickboxing",  new DtClase("Músculos para boxeo",  "viktor",  "viktor", 
	        		1,  5,  "https://www.musculos.com/muscbox",  new DtFecha(2021, 10, 15, 20, 0, 0),  new DtFecha(2021, 6, 7, 0, 0, 0),  "Músculos para boxeo.jpg"));
	        // 100 M #C13
	        IDCC.ingresarDatosClase("Telón",  "Atletismo",  new DtClase("100 M",  "lale",  "lale", 
	        		3,  10,  "https://telon.com.uy/100m",  new DtFecha(2021, 9, 25, 19, 0, 0),  new DtFecha(2021, 7, 8, 0, 0, 0), "100 M.jpg"));
	        // 200 M #C14
	        IDCC.ingresarDatosClase("Telón",  "Atletismo",  new DtClase("200 M",  "lale",  "lale", 
	        		3,  10,  "https://telon.com.uy/200m",  new DtFecha(2021, 11, 5, 18, 30, 0),  new DtFecha(2021, 7, 8, 0, 0, 0), "200 M.jpg"));
	        // Posta #C15
	        IDCC.ingresarDatosClase("Telón",  "Atletismo",  new DtClase("Posta",  "lale",  "lale", 
	        		8,  16,  "https://telon.com.uy/posta",  new DtFecha(2021, 11, 25, 17, 45, 0),  new DtFecha(2021, 7, 8, 0, 0, 0),  "Posta.jpg"));
	        // Basquet I #C16
	        IDCC.ingresarDatosClase("Telón",  "Basquetbol",  new DtClase("Basquet I",  "aldo",  "aldo", 
	        		10,  15,  "https://telon.com.uy/bball1",  new DtFecha(2021, 11, 3, 21, 0, 0),  new DtFecha(2021, 7, 31, 0, 0, 0),  "Basquet1.jpg"));
	        // Basquet II #C17
	        IDCC.ingresarDatosClase("Telón",  "Basquetbol",  new DtClase("Basquet II",  "aldo",  "aldo", 
	        		10,  10,  "https://telon.com.uy/bball2",  new DtFecha(2021, 11, 21, 21, 0, 0),  new DtFecha(2021, 7, 31, 0, 0, 0),  "Basquet2.gif"));
	        
	        // CUPONERAS
	        // Pelota #P1
	        IDC.ingresarCuponera("Pelota",  "Deportes con pelota.",  new DtFecha(2021, 5, 1, 0, 0, 0),  new DtFecha(2021, 7, 31, 23, 59, 59),  
	        		20,  new DtFecha(2021, 4, 30, 0, 0, 0), "Pelota.webp");
	        IDC.agregarActividadCuponera("Pelota",  "Telón",  "Voleibol",  7);
	        IDC.agregarActividadCuponera("Pelota",  "Telón",  "Basquetbol",  18);
	        // Gimnasia #P2
	        IDC.ingresarCuponera("Gimnasia",  "Aeróbica y aparatos.",  new DtFecha(2021, 8, 1, 0, 0, 0),  new DtFecha(2021, 9, 30, 23, 59, 59),  
	        		30,  new DtFecha(2021, 7, 15, 0, 0, 0), "Gimnasia.jpg");
	        IDC.agregarActividadCuponera("Gimnasia",  "Instituto Natural",  "Aeróbica",  2);
	        IDC.agregarActividadCuponera("Gimnasia",  "Fuerza Bruta",  "Aparatos y pesas",  8);
	        // Músculos #P2
	        IDC.ingresarCuponera("Músculos",  "Pesas.",  new DtFecha(2021, 8, 15, 0, 0, 0),  new DtFecha(2021, 11, 15, 23, 59, 59),  
	        		10,  new DtFecha(2021, 8, 1, 0, 0, 0), "Músculos.jpeg");
	        IDC.agregarActividadCuponera("Músculos",  "Fuerza Bruta",  "Kickboxing",  11);
	        IDC.agregarActividadCuponera("Músculos",  "Fuerza Bruta",  "Aparatos y pesas",  12);
	        
	        // COMPRA CUPONERAS
	        IUC.comprarCuponera("Pelota", "guille", new DtFecha());
	        IUC.comprarCuponera("Gimnasia", "m1k4", new DtFecha());
	        IUC.comprarCuponera("Gimnasia", "caro", new DtFecha());
	        IUC.comprarCuponera("Músculos", "sergiop", new DtFecha());
	        IUC.comprarCuponera("Músculos", "andy", new DtFecha());
	        IUC.comprarCuponera("Pelota", "Emi71", new DtFecha());
	        
	        
	        // REGISTRO A CLASE
        	// #R1
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Calistenia",  "caro",  TReg.general,  
        			new DtFecha(2021, 4, 9, 0, 0, 0),  null);
        	// #R2
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Calistenia",  "sergiop",  TReg.general,  
        			new DtFecha(2021, 4, 10, 0, 0, 0),  null);
        	// #R3
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Calistenia",  "andy",  TReg.general,  
        			new DtFecha(2021, 4, 12, 0, 0, 0),  null);
        	// #R4
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Peso libre",  "andy",  TReg.general,  
        			new DtFecha(2021, 4, 15, 0, 0, 0),  null);
        	// #R5
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Peso libre",  "tonyp",  TReg.general,  
        			new DtFecha(2021, 4, 20, 0, 0, 0),  null);
        	// #R6
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Peso libre",  "caro",  TReg.general,  
        			new DtFecha(2021, 4, 25, 0, 0, 0),  null);
        	// #R7
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Peso libre",  "m1k4",  TReg.general,  
        			new DtFecha(2021, 4, 28, 0, 0, 0),  null);
        	// #R8
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Aparatos",  "charly",  TReg.general,  
        			new DtFecha(2021, 4, 16, 0, 0, 0),  null);
        	// #R9
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Aparatos",  "caro",  TReg.general,  
        			new DtFecha(2021, 5, 14, 0, 0, 0),  null);
        	// #R10
        	IDCC.inscribirSocio("Fuerza Bruta",  "Aparatos y pesas",  "Aparatos",  "m1k4",  TReg.general,  
        			new DtFecha(2021, 5, 20, 0, 0, 0),  null);
        	// #R11
        	IDCC.inscribirSocio("Telón",  "Voleibol",  "Voleibol",  "Emi71",  TReg.general,  
        			new DtFecha(2021, 5, 5, 0, 0, 0),  null);
        	// #R12
        	IDCC.inscribirSocio("Telón",  "Voleibol",  "Voleibol",  "euge",  TReg.general,  
        			new DtFecha(2021, 5, 10, 0, 0, 0),  null);
        	// #R13
        	IDCC.inscribirSocio("Telón",  "Voleibol",  "Voleibol",  "sergiop",  TReg.general,  
        			new DtFecha(2021, 5, 15, 0, 0, 0),  null);
			// #R14
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Voleibol",  "tonyp",  TReg.general,  
					new DtFecha(2021, 5, 20, 0, 0, 0),  null);
			// #R15
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Braza",  "guille",  TReg.general,  
					new DtFecha(2021, 6, 8, 0, 0, 0),  null);
			// #R16
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Braza",  "euge",  TReg.general,  
					new DtFecha(2021, 6, 13, 0, 0, 0),  null);
			// #R17
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Braza",  "m1k4",  TReg.general,  
					new DtFecha(2021, 6, 25, 0, 0, 0),  null);
			// #R18
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Mariposa",  "charly",  TReg.general,  
					new DtFecha(2021, 7, 5, 0, 0, 0),  null);
			// #R19
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Mariposa",  "sergiop",  TReg.general,  
					new DtFecha(2021, 7, 11, 0, 0, 0),  null);
			// #R20
			IDCC.inscribirSocio("Telón",  "Voleibol",  "Mariposa",  "andy",  TReg.general,  
					new DtFecha(2021, 7, 18, 0, 0, 0),  null);
			// #R21
			IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbica niños",  "m1k4",  TReg.cuponera,  
					new DtFecha(2021, 7, 19, 0, 0, 0),  "Gimnasia");
			// #R22
			IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbico adulto mayor",  "Emi71",  TReg.general,  
					new DtFecha(2021, 8, 17, 0, 0, 0),  null);
			// #R23
			IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbico adulto mayor",  "guille",  TReg.general,  
					new DtFecha(2021, 8, 20, 0, 0, 0),  null);
			// #R24
			IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbico adulto mayor",  "andy",  TReg.general,  
					new DtFecha(2021, 8, 23, 0, 0, 0),  null);
			// #R25
			IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbica",  "caro",  TReg.cuponera,  
					new DtFecha(2021, 8, 15, 0, 0, 0),  "Gimnasia"); // R25 C9 CO 15/08/21 560
			// #R26
			IDCC.inscribirSocio("Instituto Natural",  "Aeróbica",  "Aeróbica",  "euge",  TReg.general,  
					new DtFecha(2021, 8, 26, 0, 0, 0),  null);
			// #R27
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo I",  "andy",  TReg.cuponera,  
					new DtFecha(2021, 7, 19, 0, 0, 0),  "Músculos");
			// #R28
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo I",  "tonyp",  TReg.general,  
					new DtFecha(2021, 8, 16, 0, 0, 0),  null);
			// #R29
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo I",  "m1k4",  TReg.general,  
					new DtFecha(2021, 8, 24, 0, 0, 0),  null);
			// #R30
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo II",  "sergiop",  TReg.cuponera,  
					new DtFecha(2021, 8, 1, 0, 0, 0),  "Músculos");
			// #R31
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Boxeo II",  "guille",  TReg.general,  
					new DtFecha(2021, 8, 30, 0, 0, 0),  null);
			// #R32
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Músculos para boxeo",  "Emi71",  TReg.general,  
					new DtFecha(2021, 8, 16, 0, 0, 0),  null);
			// #R33
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Músculos para boxeo",  "caro",  TReg.general,  
					new DtFecha(2021, 8, 16, 0, 0, 0),  null);
			// #R34
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Músculos para boxeo",  "euge",  TReg.general,  
					new DtFecha(2021, 9, 1, 0, 0, 0),  null);
			// #R35
			IDCC.inscribirSocio("Fuerza Bruta",  "Kickboxing",  "Músculos para boxeo",  "sergiop",  TReg.general,  
					new DtFecha(2021, 9, 5, 0, 0, 0),  null);
			// #R36
			IDCC.inscribirSocio("Telón",  "Atletismo",  "100 M",  "guille",  TReg.general,  
					new DtFecha(2021, 8, 16, 0, 0, 0),  null);
			// #R37
			IDCC.inscribirSocio("Telón",  "Atletismo",  "100 M",  "charly",  TReg.general,  
					new DtFecha(2021, 9, 3, 0, 0, 0),  null);
			// #R38
			IDCC.inscribirSocio("Telón",  "Atletismo",  "200 M",  "Emi71",  TReg.general,  
					new DtFecha(2021, 8, 16, 0, 0, 0),  null);
			// #R39
			IDCC.inscribirSocio("Telón",  "Atletismo",  "200 M",  "charly",  TReg.general,  
					new DtFecha(2021, 9, 6, 0, 0, 0),  null);
			// #R40
			IDCC.inscribirSocio("Telón",  "Atletismo",  "Posta",  "caro",  TReg.general,  
					new DtFecha(2021, 9, 1, 0, 0, 0),  null);
			// #R41
			IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet I",  "sergiop",  TReg.general,  
					new DtFecha(2021, 8, 16, 0, 0, 0),  null);
			// #R42
			IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet I",  "Emi71",  TReg.general,  
					new DtFecha(2021, 8, 20, 0, 0, 0),  null);
			// #R43
			IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet I",  "tonyp",  TReg.general,  
					new DtFecha(2021, 8, 31, 0, 0, 0),  null);
			// #R44
			IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet II",  "andy",  TReg.general,  
					new DtFecha(2021, 8, 16, 0, 0, 0),  null);
			// #R45
			IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet II",  "tonyp",  TReg.general,  
					new DtFecha(2021, 8, 20, 0, 0, 0),  null);
			// #R46
			IDCC.inscribirSocio("Telón",  "Basquetbol",  "Basquet II",  "caro",  TReg.general,  
					new DtFecha(2021, 9, 2, 0, 0, 0),  null);
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
		}
    }
}
