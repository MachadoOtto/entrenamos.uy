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
import datatypes.DtClase;
import datatypes.DtFecha;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.TReg;

import excepciones.FechaInvalidaException;
import excepciones.NoExisteCuponeraException;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
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
		altaUsuario.setLocation(462, 25);
		altaUsuario.setVisible(false);
		escritorio.add(altaUsuario);	
		
		//AltaActividadDeportiva
		altaActDep = new AltaActividadDeportiva(IADC);
		altaActDep.setLocation(20, 20);
		altaActDep.setSize(450, 500);
		altaActDep.setVisible(false);
		escritorio.add(altaActDep);

		// AltaDictadoClase:
		altaClase = new AltaDictadoClase(IDCC);
		altaClase.setLocation(10, 11);
		altaClase.setVisible(false);
		escritorio.add(altaClase);
		
		// AltaInstitucionDeporitva:
		altaIns = new AltaInstitucionDeportiva(IADC);
		altaIns.setBounds(212, 37, 354, 344);
		altaIns.setVisible(false);
		escritorio.add(altaIns);
		
		// RegistroUsuarioClase:
		regUsuClass = new RegistroUsuarioClase(IDCC);
		regUsuClass.setVisible(false);
		escritorio.add(regUsuClass);
		
		// ConsultaDictadoClase:
		consultaClass = new ConsultaDictadoClase(IDCC);
		consultaClass.setBounds(10, 40, 382, 545);
		consultaClass.setVisible(false);
		escritorio.add(consultaClass);
		
		// ConsultaCuponeras:
		consultaCup = new ConsultaCuponeras(IDC);
		consultaCup.setBounds(200, 100, 400, 458);
		consultaCup.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		consultaCup.setVisible(false);
		escritorio.add(consultaCup);
		
		//ConsultaUsuario
		consultaUsu = new ConsultaUsuario(IUC);
		consultaUsu.setVisible(false);
		escritorio.add(consultaUsu);

		// ConsultaActividadDeportiva
		consActDep = new ConsultaActividadDeportiva(IADC);
		consActDep.setBounds(143, 20, 457, 719);
		consActDep.setVisible(false);
		escritorio.add(consActDep);
		
		//ModificarDatosUsuario
		modificarUsu = new ModificarDatosUsuario(IUC);
		modificarUsu.setVisible(false);
		escritorio.add(modificarUsu);
		
		altaCup = new CrearCuponera(IDC);
		altaCup.setBounds(100, 100, 500, 483);
		altaCup.setVisible(false);
		escritorio.add(altaCup);
		
		aggCup = new AgregarActividadDeportivaCuponera(IDC,IADC);
		aggCup.setVisible(false);
		escritorio.add(aggCup);
		
		//Se relacionan los Frames de consultas
		consActDep.setRef(consultaClass,consultaCup);
		consultaCup.setRef(consActDep);
		consultaUsu.setRef(consultaClass,consActDep);
	}
	
	private void iniciar() {
        // Se crea el Frame con las dimensiones indicadas:
		menuPrincipal = new JFrame();
		menuPrincipal.setTitle("Entrenamos.uy");
		menuPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menuPrincipal.setBounds(100, 100, 1200, 850);
		menuPrincipal.setResizable(true);
		menuPrincipal.setIconImage(new ImageIcon(getClass().getResource("/img/iconoEntrenamos-uy.png")).getImage());
		
		escritorio = new JDesktopPane();
		escritorio.setBackground(new Color(174, 182, 191));
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
			IADC.altaInstitucion("Instituto Natural", "Clases de gimnasia, aeróbica, spinning y yoga.","https://www.inatural.com");
			// Fuerza Bruta #FB
			IADC.altaInstitucion("Fuerza Bruta", "Gimnasio especializado en el desarrollo de la musculatura.","https://www.musculos.com");
			// Telón #TL
			IADC.altaInstitucion("Telón", "Actividades deportivas para todas las edades.","https://telon.com.uy");
			// Olympic #YT
			IADC.altaInstitucion("Olympic", "Gimnasia y Aparatos.","https://www.olympic21.com");
			
			// ALTA USUARIOS
			// SOCIOS
			// Emi71 #EL
			IUC.ingresarDatosUsuario(new DtSocio("Emi71","Emiliano","Lucas","emi71@gmail.com", new DtFecha(1971,12,31,0,0,0)));
			// caro #CO
			IUC.ingresarDatosUsuario(new DtSocio("caro","Carolina","Omega","caro@gmail.com", new DtFecha(1983,11,15,0,0,0)));
			// euge #EW
			IUC.ingresarDatosUsuario(new DtSocio("euge","Eugenia","Williams","e.will@gmail.com", new DtFecha(1990,4,15,0,0,0)));
			// guille #GH
			IUC.ingresarDatosUsuario(new DtSocio("guille","Guillermo","Hector","ghector@gmail.com", new DtFecha(1959,5,15,0,0,0)));
			// sergiop #SP
			IUC.ingresarDatosUsuario(new DtSocio("sergiop","Sergio","Perez","sergi@gmail.com.uy", new DtFecha(1950,1,28,0,0,0)));
			// andy #AR
			IUC.ingresarDatosUsuario(new DtSocio("andy","Andrés","Roman","chino@gmail.org.uy", new DtFecha(1976,3,17,0,0,0)));
			// tonyp #AP
			IUC.ingresarDatosUsuario(new DtSocio("tonyp","Antonio","Paz","eltony@gmail.org.uy", new DtFecha(1955,2,14,0,0,0)));
			// m1k4 #ML
			IUC.ingresarDatosUsuario(new DtSocio("m1k4","Micaela","Lopez","mika@gmail.com.ar", new DtFecha(1987,2,23,0,0,0)));
			// charly #CB
			IUC.ingresarDatosUsuario(new DtSocio("charly","Carlos","Boston","charly@gmail.com.uy", new DtFecha(1937,5,8,0,0,0)));	
			
			// PROFESORES
			String desc;
			String bio;
			// viktor #VP
			desc = "Victor es un apasionado de los músculos. Sus clases son organizadas en función de distintos "
					+ "aparatos y pesas con el objetivo de desarrollar músculos.";
			bio = "Victor nació en Moscow en 1977. En el año 2005 emigró a Uruguay luego de quedar "
					+ "encantado con el país en un viaje turístico.";
			IUC.ingresarDatosUsuario(new DtProfesor("viktor","Victor","Perez","vperez@fuerza.com", new DtFecha(1997,1,1,0,0,0),
					"Fuerza Bruta", desc, bio ,"www.vikgym.com"));
			// denis #DM
			desc = "A Denis le interesan los deportes con pelota, principalmente el voleibol y el handball.";
			bio = "Denis fue un jugador de voleibol profesional.";
			IUC.ingresarDatosUsuario(new DtProfesor("denis","Denis","Miguel","den80@fuerza.com", new DtFecha(1980,6,14,0,0,0),
					"Telón", desc, bio ,"www.depecho.com"));
			// clazar #CL
			desc = "Carlos es un profesor muy divertido cuyas clases de aeróbica están cargadas de energía.";
			bio = "El interés por la actividad física llevo a Carlos a dejar su trabajo en un estudio "
					+ "contable y abrir su propio gimnasio.";
			IUC.ingresarDatosUsuario(new DtProfesor("clazar","Carlos","Lazaro","claz4r0@hotmail.com", new DtFecha(1953,6,22,0,0,0),
					"Instituto Natural", desc, bio ,"www.enforma.com"));
			// TheBoss #BS
			desc = "Bruno es un ex-boxeardor que busca entrenar a futuros campeones.";
			bio = "Bruno, mejor conocido como Bruce en el ring, compitió como boxeador entre los años 60s y 70s.";
			IUC.ingresarDatosUsuario(new DtProfesor("TheBoss","Bruno","Sosa","bruceTheBoss@gmail.com", new DtFecha(1949,9,23,0,0,0),
					"Fuerza Bruta", desc, bio ,"www.bruce.net"));
			// Nelson #TN
			desc = "Profesor de natación. Especializado en braza y mariposa.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("Nelson","Luis","Nelson","nelson@hotmail.com", new DtFecha(1998,1,1,0,0,0),
					"Telón", desc, bio ,"www.nelson.uy"));
			// lale #LL
			desc = "Luego de una exitosa carrera como jugadora de futbol profesional. Laura dedica sus clases a "
					+ "enseñar tácticas de futbol.";
			bio = "Jugadora profesional de futbol desde 2010 a 2020.";
			IUC.ingresarDatosUsuario(new DtProfesor("lale","Laura","Leyes","la_le@outlook.com", new DtFecha(1987,2,14,0,0,0),
					"Telón", desc, bio ,"www.laley.com"));
			// prisc #PI
			desc = "Laura tiene un gran interés por los deportes olímpicos.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("prisc","Priscila","Pappo","pripa@gmail.com", new DtFecha(1981,8,13,0,0,0),
					"Olympic", desc, bio ,"www.pi314.net"));
			// dagost #DY
			desc = "Profesora dedicada y exigente. No acepta un " + '"' + "no puedo" + '"' + " como respuesta.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("dagost","Daiana","Agostini","d_1940_ago@gmail.com", new DtFecha(1940,3,5,0,0,0),
					"Olympic", desc, bio ,"www.dygym.com"));
			// aldo #AL
			desc = "Dada su gran estatura Aldo siempre jugó al basquetbol, hoy se dedica a enseñarlo.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("aldo","Aldo","Vivaldi","aldo@outlook.com", new DtFecha(1952,7,17,0,0,0),
					"Telón", desc, bio ,"www.sportsaldo.net"));
				
			// ALTA ACTIVIDAD DEPORTIVA
	        // Aparatos y pesas #A1
			IADC.ingresarDatosActividadDep("Fuerza Bruta", new DtActividadDeportiva("Aparatos y pesas",
					"Clases de aparatos, pesas y calistenia.", 90, 550, new DtFecha(2021,3,31,0,0,0)));
			// Voleibol #A2
			IADC.ingresarDatosActividadDep("Telón", new DtActividadDeportiva("Voleibol",
					"Voleibol en todas sus formas.", 120, 750, new DtFecha(2021,4,20,0,0,0)));
			// Aeróbica #A3
			IADC.ingresarDatosActividadDep("Instituto Natural", new DtActividadDeportiva("Aeróbica",
					"Para cuidar el aparato cardiovascular.", 110, 800, new DtFecha(2021,5,30,0,0,0)));
			// Kickboxing #A4
			IADC.ingresarDatosActividadDep("Fuerza Bruta", new DtActividadDeportiva("Kickboxing",
					"En busca del nuevo campeón de boxeo.", 100, 980, new DtFecha(2021,6,7,0,0,0)));
			// Atletismo #A5
			IADC.ingresarDatosActividadDep("Telón", new DtActividadDeportiva("Atletismo",
					"100m , 200m, postas y carreras con obstaculos.", 150, 500, new DtFecha(2021,7,8,0,0,0)));
			// Basquetbol #A6
			IADC.ingresarDatosActividadDep("Telón", new DtActividadDeportiva("Basquetbol",
					"Espectáculo conmemorando los 30 años de Violeta.", 80, 450, new DtFecha(2021,7,31,0,0,0)));
	        
	        // ALTA CLASE
	        // Calistenia #C1
	        IDCC.ingresarDatosClase("Fuerza Bruta", "Aparatos y pesas", new DtClase("Calistenia", "viktor", "viktor", 
	        		1, 5, "https://www.musculos.com/Calistenia", new DtFecha(2021,4,15,15,30,0), new DtFecha(2021,3,31,0,0,0)));
	        // Peso libre #C2
	        IDCC.ingresarDatosClase("Fuerza Bruta", "Aparatos y pesas", new DtClase("Peso libre", "viktor", "viktor", 
	        		1, 5, "https://www.musculos.com/pesolibre", new DtFecha(2021,5,1,17,0,0), new DtFecha(2021,3,31,0,0,0)));
	        // Aparatos #C3
	        IDCC.ingresarDatosClase("Fuerza Bruta", "Aparatos y pesas", new DtClase("Aparatos", "viktor", "viktor", 
	        		1, 7, "https://www.musculos.com/aparatos", new DtFecha(2021,6,1,18,0,0), new DtFecha(2021,3,31,0,0,0)));
	        // Voleibol #C4
	        IDCC.ingresarDatosClase("Telón", "Voleibol", new DtClase("Voleibol", "denis", "denis",
	        		10, 21, "https://telon.com.uy/voley", new DtFecha(2021,6,10,19,0,0), new DtFecha(2021,4,20,0,0,0)));
	        // Braza #C5
	        IDCC.ingresarDatosClase("Telón", "Voleibol", new DtClase("Braza", "Nelson", "Nelson",
	        		2, 6, "https://telon.com.uy/natacionB", new DtFecha(2021,7,10,20,0,0), new DtFecha(2021,4,20,0,0,0)));
	        // Mariposa #C6
	        IDCC.ingresarDatosClase("Telón", "Voleibol", new DtClase("Mariposa", "Nelson", "Nelson",
	        		2, 6, "https://telon.com.uy/natacionM", new DtFecha(2021,8,10,17,45,0), new DtFecha(2021,4,20,0,0,0)));
	        // Aeróbica niños #C7
	        IDCC.ingresarDatosClase("Instituto Natural", "Aeróbica", new DtClase("Aeróbica niños", "clazar", "clazar",
	        		5, 10, "https://www.inatural.com/aeroni", new DtFecha(2021,8,15,16,30,0), new DtFecha(2021,5,30,0,0,0)));
	        // Aeróbico adulto mayor #C8
	        IDCC.ingresarDatosClase("Instituto Natural", "Aeróbica", new DtClase("Aeróbico adulto mayor", "clazar", "clazar",
	        		5, 12, "https://www.inatural.com/aeroam", new DtFecha(2021,8,31,19,30,0), new DtFecha(2021,5,30,0,0,0)));
	        // Aeróbico #C9
	        IDCC.ingresarDatosClase("Instituto Natural", "Aeróbica", new DtClase("Aeróbico", "clazar", "clazar",
	        		5, 20, "https://www.inatural.com/aerogral", new DtFecha(2021,9,30,20,0,0), new DtFecha(2021,5,30,0,0,0)));
	        // Boxeo I #C10
	        IDCC.ingresarDatosClase("Fuerza Bruta", "Kickboxing", new DtClase("Boxeo I", "TheBoss", "TheBoss",
	        		1, 4, "https://www.musculos.com/boxeo1", new DtFecha(2021,9,1,19,30,0), new DtFecha(2021,6,7,0,0,0)));
	        // Boxeo II #C11
	        IDCC.ingresarDatosClase("Fuerza Bruta", "Kickboxing", new DtClase("Boxeo II", "TheBoss", "TheBoss",
	        		2, 2, "https://www.musculos.com/boxeo2", new DtFecha(2021,9,30,17,0,0), new DtFecha(2021,6,7,0,0,0)));
	        // Músculos para boxeo #C12
	        IDCC.ingresarDatosClase("Fuerza Bruta", "Kickboxing", new DtClase("Músculos para boxeo", "viktor", "viktor",
	        		1, 5, "https://www.musculos.com/muscbox", new DtFecha(2021,10,15,20,0,0), new DtFecha(2021,6,7,0,0,0)));
	        // 100 M #C13
	        IDCC.ingresarDatosClase("Telón", "Atletismo", new DtClase("100 M", "lale", "lale",
	        		3, 10, "https://telon.com.uy/100m", new DtFecha(2021,9,25,19,0,0), new DtFecha(2021,7,8,0,0,0)));
	        // 200 M #C14
	        IDCC.ingresarDatosClase("Telón", "Atletismo", new DtClase("200 M", "lale", "lale",
	        		3, 10, "https://telon.com.uy/200m", new DtFecha(2021,10,25,18,30,0), new DtFecha(2021,7,8,0,0,0)));
	        // Posta #C15
	        IDCC.ingresarDatosClase("Telón", "Atletismo", new DtClase("Posta", "lale", "lale",
	        		8, 16, "https://telon.com.uy/posta", new DtFecha(2021,11,25,17,45,0), new DtFecha(2021,7,8,0,0,0)));
	        // Basquet I #C16
	        IDCC.ingresarDatosClase("Telón", "Basquetbol", new DtClase("Basquet I", "aldo", "aldo",
	        		10, 15, "https://telon.com.uy/bball1", new DtFecha(2021,9,1,21,0,0), new DtFecha(2021,7,31,0,0,0)));
	        // Basquet II #C17
	        IDCC.ingresarDatosClase("Telón", "Basquetbol", new DtClase("Basquet II", "aldo", "aldo",
	        		10, 10, "https://telon.com.uy/bball2", new DtFecha(2021,10,1,21,0,0), new DtFecha(2021,7,31,0,0,0)));
	        
	        // CUPONERAS
	        // Pelota #P1
	        IDC.ingresarCuponera("Pelota", "Deportes con pelota.", new DtFecha(2021,5,1,0,0,0), new DtFecha(2021,7,31,23,59,59), 
	        		20, new DtFecha(2021,4,30,0,0,0));
	        IDC.agregarActividadCuponera("Pelota", "Telón", "Voleibol", 7);
	        IDC.agregarActividadCuponera("Pelota", "Telón", "Basquetbol", 18);
	        // Gimnasia #P2
	        IDC.ingresarCuponera("Gimnasia", "Aeróbica y aparatos.", new DtFecha(2021,8,1,0,0,0), new DtFecha(2021,9,30,23,59,59), 
	        		30, new DtFecha(2021,7,15,0,0,0));
	        IDC.agregarActividadCuponera("Gimnasia", "Instituto Natural", "Aeróbica", 2);
	        IDC.agregarActividadCuponera("Gimnasia", "Fuerza Bruta", "Aparatos y pesas", 8);
	        // Músculos #P2
	        IDC.ingresarCuponera("Músculos", "Pesas.", new DtFecha(2021,8,15,0,0,0), new DtFecha(2021,11,15,23,59,59), 
	        		10, new DtFecha(2021,8,1,0,0,0));
	        IDC.agregarActividadCuponera("Músculos", "Fuerza Bruta", "Kickboxing", 11);
	        IDC.agregarActividadCuponera("Músculos", "Fuerza Bruta", "Aparatos y pesas", 12);
	        
	        // REGISTRO A CLASE
        	// #R1
        	IDCC.inscribirSocio("Fuerza Bruta", "Aparatos y pesas", "Calistenia", "caro", TReg.general, 
        			new DtFecha(2021,4,9,0,0,0));
        	// #R2
        	IDCC.inscribirSocio("Fuerza Bruta", "Aparatos y pesas", "Calistenia", "sergiop", TReg.general, 
        			new DtFecha(2021,4,10,0,0,0));
        	// #R3
        	IDCC.inscribirSocio("Fuerza Bruta", "Aparatos y pesas", "Calistenia", "andy", TReg.general, 
        			new DtFecha(2021,4,12,0,0,0));
        	// #R4
        	IDCC.inscribirSocio("Fuerza Bruta", "Aparatos y pesas", "Peso libre", "andy", TReg.general, 
        			new DtFecha(2021,4,15,0,0,0));
        	// #R5
        	IDCC.inscribirSocio("Fuerza Bruta", "Aparatos y pesas", "Peso libre", "tonyp", TReg.general, 
        			new DtFecha(2021,4,20,0,0,0));
        	// #R6
        	IDCC.inscribirSocio("Fuerza Bruta", "Aparatos y pesas", "Peso libre", "caro", TReg.general, 
        			new DtFecha(2021,4,25,0,0,0));
        	// #R7
        	IDCC.inscribirSocio("Fuerza Bruta", "Aparatos y pesas", "Peso libre", "m1k4", TReg.general, 
        			new DtFecha(2021,4,28,0,0,0));
        	// #R8
        	IDCC.inscribirSocio("Fuerza Bruta", "Aparatos y pesas", "Aparatos", "charly", TReg.general, 
        			new DtFecha(2021,4,16,0,0,0));
        	// #R9
        	IDCC.inscribirSocio("Fuerza Bruta", "Aparatos y pesas", "Aparatos", "caro", TReg.general, 
        			new DtFecha(2021,5,14,0,0,0));
        	// #R10
        	IDCC.inscribirSocio("Fuerza Bruta", "Aparatos y pesas", "Aparatos", "m1k4", TReg.general, 
        			new DtFecha(2021,5,20,0,0,0));
        	// #R11
        	IDCC.inscribirSocio("Telón", "Voleibol", "Voleibol", "Emi71", TReg.general, 
        			new DtFecha(2021,5,5,0,0,0));
        	// #R12
        	IDCC.inscribirSocio("Telón", "Voleibol", "Voleibol", "euge", TReg.general, 
        			new DtFecha(2021,5,10,0,0,0));
        	// #R13
        	IDCC.inscribirSocio("Telón", "Voleibol", "Voleibol", "sergiop", TReg.general, 
        			new DtFecha(2021,5,15,0,0,0));
			// #R14
			IDCC.inscribirSocio("Telón", "Voleibol", "Voleibol", "tonyp", TReg.general, 
					new DtFecha(2021,5,20,0,0,0));
			// #R15
			IDCC.inscribirSocio("Telón", "Voleibol", "Braza", "guille", TReg.general, 
					new DtFecha(2021,6,8,0,0,0));
			// #R16
			IDCC.inscribirSocio("Telón", "Voleibol", "Braza", "euge", TReg.general, 
					new DtFecha(2021,6,13,0,0,0));
			// #R17
			IDCC.inscribirSocio("Telón", "Voleibol", "Braza", "m1k4", TReg.general, 
					new DtFecha(2021,6,25,0,0,0));
			// #R18
			IDCC.inscribirSocio("Telón", "Voleibol", "Mariposa", "charly", TReg.general, 
					new DtFecha(2021,7,5,0,0,0));
			// #R19
			IDCC.inscribirSocio("Telón", "Voleibol", "Mariposa", "sergiop", TReg.general, 
					new DtFecha(2021,7,11,0,0,0));
			// #R20
			IDCC.inscribirSocio("Telón", "Voleibol", "Mariposa", "andy", TReg.general, 
					new DtFecha(2021,7,18,0,0,0));
			// #R21
			IDCC.inscribirSocio("Instituto Natural", "Aeróbica", "Aeróbica niños", "m1k4", TReg.general, 
					new DtFecha(2021,7,19,0,0,0));
			// #R22
			IDCC.inscribirSocio("Instituto Natural", "Aeróbica", "Aeróbico adulto mayor", "Emi71", TReg.general, 
					new DtFecha(2021,8,17,0,0,0));
			// #R23
			IDCC.inscribirSocio("Instituto Natural", "Aeróbica", "Aeróbico adulto mayor", "guille", TReg.general, 
					new DtFecha(2021,8,20,0,0,0));
			// #R24
			IDCC.inscribirSocio("Instituto Natural", "Aeróbica", "Aeróbico adulto mayor", "andy", TReg.general, 
					new DtFecha(2021,8,23,0,0,0));
			// #R25
			IDCC.inscribirSocio("Instituto Natural", "Aeróbica", "Aeróbico", "caro", TReg.general, 
					new DtFecha(2021,8,15,0,0,0)); // R25 C9 CO 15/08/21 560
			// #R26
			IDCC.inscribirSocio("Instituto Natural", "Aeróbica", "Aeróbico", "euge", TReg.general, 
					new DtFecha(2021,8,26,0,0,0));
			// #R27
			IDCC.inscribirSocio("Fuerza Bruta", "Kickboxing", "Boxeo I", "andy", TReg.general, 
					new DtFecha(2021,7,19,0,0,0));
			// #R28
			IDCC.inscribirSocio("Fuerza Bruta", "Kickboxing", "Boxeo I", "tonyp", TReg.general, 
					new DtFecha(2021,8,16,0,0,0));
			// #R29
			IDCC.inscribirSocio("Fuerza Bruta", "Kickboxing", "Boxeo I", "m1k4", TReg.general, 
					new DtFecha(2021,8,24,0,0,0));
			// #R30
			IDCC.inscribirSocio("Fuerza Bruta", "Kickboxing", "Boxeo II", "sergiop", TReg.general, 
					new DtFecha(2021,8,1,0,0,0));
			// #R31
			IDCC.inscribirSocio("Fuerza Bruta", "Kickboxing", "Boxeo II", "guille", TReg.general, 
					new DtFecha(2021,8,30,0,0,0));
			// #R32
			IDCC.inscribirSocio("Fuerza Bruta", "Kickboxing", "Músculos para boxeo", "Emi71", TReg.general, 
					new DtFecha(2021,8,16,0,0,0));
			// #R33
			IDCC.inscribirSocio("Fuerza Bruta", "Kickboxing", "Músculos para boxeo", "caro", TReg.general, 
					new DtFecha(2021,8,16,0,0,0));
			// #R34
			IDCC.inscribirSocio("Fuerza Bruta", "Kickboxing", "Músculos para boxeo", "euge", TReg.general, 
					new DtFecha(2021,9,1,0,0,0));
			// #R35
			IDCC.inscribirSocio("Fuerza Bruta", "Kickboxing", "Músculos para boxeo", "sergiop", TReg.general, 
					new DtFecha(2021,9,5,0,0,0));
			// #R36
			IDCC.inscribirSocio("Telón", "Atletismo", "100 M", "guille", TReg.general, 
					new DtFecha(2021,8,16,0,0,0));
			// #R37
			IDCC.inscribirSocio("Telón", "Atletismo", "100 M", "charly", TReg.general, 
					new DtFecha(2021,9,3,0,0,0));
			// #R38
			IDCC.inscribirSocio("Telón", "Atletismo", "200 M", "Emi71", TReg.general, 
					new DtFecha(2021,8,16,0,0,0));
			// #R39
			IDCC.inscribirSocio("Telón", "Atletismo", "200 M", "charly", TReg.general, 
					new DtFecha(2021,9,6,0,0,0));
			// #R40
			IDCC.inscribirSocio("Telón", "Atletismo", "Posta", "caro", TReg.general, 
					new DtFecha(2021,9,1,0,0,0));
			// #R41
			IDCC.inscribirSocio("Telón", "Basquetbol", "Basquet I", "sergiop", TReg.general, 
					new DtFecha(2021,8,16,0,0,0));
			// #R42
			IDCC.inscribirSocio("Telón", "Basquetbol", "Basquet I", "Emi71", TReg.general, 
					new DtFecha(2021,8,20,0,0,0));
			// #R43
			IDCC.inscribirSocio("Telón", "Basquetbol", "Basquet I", "tonyp", TReg.general, 
					new DtFecha(2021,8,31,0,0,0));
			// #R44
			IDCC.inscribirSocio("Telón", "Basquetbol", "Basquet II", "andy", TReg.general, 
					new DtFecha(2021,8,16,0,0,0));
			// #R45
			IDCC.inscribirSocio("Telón", "Basquetbol", "Basquet II", "tonyp", TReg.general, 
					new DtFecha(2021,8,20,0,0,0));
			// #R46
			IDCC.inscribirSocio("Telón", "Basquetbol", "Basquet II", "caro", TReg.general, 
					new DtFecha(2021,9,2,0,0,0));
		    JOptionPane.showMessageDialog(escritorio, "Se han cargado los datos de prueba exitosamente.", 
		    		"Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (FechaInvalidaException e) {
        	JOptionPane.showMessageDialog(escritorio, "Ha ocurrido un error durante la carga de casos de prueba: " +
        			e.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
        } catch (ClaseException e) {
        	JOptionPane.showMessageDialog(escritorio, "Ha ocurrido un error durante la carga de casos de prueba: " +
        			e.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
        } catch (NoExisteCuponeraException e) {
        	JOptionPane.showMessageDialog(escritorio, "Ha ocurrido un error durante la carga de casos de prueba: " +
        			e.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
        } catch (InstitucionException e) {
        	JOptionPane.showMessageDialog(escritorio, "Ha ocurrido un error durante la carga de casos de prueba: " +
        			e.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
        } catch (UsuarioNoExisteException e) {
        	JOptionPane.showMessageDialog(escritorio, "Ha ocurrido un error durante la carga de casos de prueba: " +
        			e.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
        } catch (ActividadDeportivaException e) {
        	JOptionPane.showMessageDialog(escritorio, "Ha ocurrido un error durante la carga de casos de prueba: " +
        			e.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
		}
    }
}
