package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.DtActividadDeportiva;
import datatypes.DtClase;
import datatypes.DtClaseExt;
import datatypes.DtClasesCuponera;
import datatypes.DtCuponera;
import datatypes.DtFecha;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import datatypes.TReg;
import datatypes.DtInstitucion;
import datatypes.DtProfesor;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.CuponeraRepetidaException;
import excepciones.FechaInvalidaException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;
import excepciones.UsuarioNoExisteException;
import logica.IActividadDeportivaController;
import logica.ICuponeraController;
import logica.IDictadoClaseController;
import logica.IUsuarioController;
import logica.LaFabrica;

class TestCasos {
	
	private static IActividadDeportivaController IADC;
	private static IUsuarioController IUC;
	private static ICuponeraController ICC;
	private static IDictadoClaseController IDCC;
	
	@BeforeAll
	public static void iniciar() {
		LaFabrica fabrica = LaFabrica.getInstance();
		IADC = fabrica.obtenerIActDeportivaController();
		IUC = fabrica.obtenerIUsuarioController();
		ICC = fabrica.obtenerICuponeraController();
		IDCC = fabrica.obtenerIDictadoClaseController();
		cargaDeCasos();
	}
	
	public static void cargaDeCasos() {
		try {			
			// ALTA INSTITUCIONES
			// Instituto Natural #IN
			IADC.altaInstitucion("Instituto Natural","https://www.inatural.com", "Clases de gimnasia, aeróbica, spinning y yoga.");
			// Fuerza Bruta #FB
			IADC.altaInstitucion("Fuerza Bruta", "https://www.musculos.com", "Gimnasio especializado en el desarrollo de la musculatura.");
			// Telón #TL
			IADC.altaInstitucion("Telón", "https://telon.com.uy", "Actividades deportivas para todas las edades.");
			// Olympic #YT
			IADC.altaInstitucion("Olympic", "https://www.olympic21.com", "Gimnasia y Aparatos.");
			
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
	        IDCC.ingresarDatosClase("Instituto Natural", "Aeróbica", new DtClase("Aeróbica", "clazar", "clazar",
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
	        ICC.ingresarCuponera("Pelota", "Deportes con pelota.", new DtFecha(2021,5,1,0,0,0), new DtFecha(2021,7,31,23,59,59), 
	        		20, new DtFecha(2021,4,30,0,0,0));
	        ICC.agregarActividadCuponera("Pelota", "Telón", "Voleibol", 7);
	        ICC.agregarActividadCuponera("Pelota", "Telón", "Basquetbol", 18);
	        // Gimnasia #P2
	        ICC.ingresarCuponera("Gimnasia", "Aeróbica y aparatos.", new DtFecha(2021,8,1,0,0,0), new DtFecha(2021,9,30,23,59,59), 
	        		30, new DtFecha(2021,7,15,0,0,0));
	        ICC.agregarActividadCuponera("Gimnasia", "Instituto Natural", "Aeróbica", 2);
	        ICC.agregarActividadCuponera("Gimnasia", "Fuerza Bruta", "Aparatos y pesas", 8);
	        // Músculos #P2
	        ICC.ingresarCuponera("Músculos", "Pesas.", new DtFecha(2021,8,15,0,0,0), new DtFecha(2021,11,15,23,59,59), 
	        		10, new DtFecha(2021,8,1,0,0,0));
	        ICC.agregarActividadCuponera("Músculos", "Fuerza Bruta", "Kickboxing", 11);
	        ICC.agregarActividadCuponera("Músculos", "Fuerza Bruta", "Aparatos y pesas", 12);
	        
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
			IDCC.inscribirSocio("Instituto Natural", "Aeróbica", "Aeróbica", "caro", TReg.general, 
					new DtFecha(2021,8,15,0,0,0)); // R25 C9 CO 15/08/21 560
			// #R26
			IDCC.inscribirSocio("Instituto Natural", "Aeróbica", "Aeróbica", "euge", TReg.general, 
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
        } catch (FechaInvalidaException e) {
        	fail(e.getMessage());
			e.printStackTrace();
        } catch (ClaseException e) {
        	fail(e.getMessage());
			e.printStackTrace();
        } catch (NoExisteCuponeraException e) {
        	fail(e.getMessage());
			e.printStackTrace();
        } catch (InstitucionException e) {
        	fail(e.getMessage());
			e.printStackTrace();
        } catch (UsuarioNoExisteException e) {
        	fail(e.getMessage());
        	e.printStackTrace();
        } catch (ActividadDeportivaException e) {
        	fail(e.getMessage());
        	e.printStackTrace();
		} catch (CuponeraRepetidaException e) {
			fail(e.getMessage());
        	e.printStackTrace();
		}
	}
	
	@Test
	void testCargaDeInstitucionesOk() {
		try {
			// Instituto Natural #IN
			DtInstitucion data = IADC.obtenerDatosInstitucion("Instituto Natural");
			assertEquals(data.getNombre(), "Instituto Natural");
			assertEquals(data.getDescripcion(), "Clases de gimnasia, aeróbica, spinning y yoga.");
			assertEquals(data.getURL(), "https://www.inatural.com");
			// Fuerza Bruta #FB
			data = IADC.obtenerDatosInstitucion("Fuerza Bruta");
			assertEquals(data.getNombre(), "Fuerza Bruta");
			assertEquals(data.getDescripcion(), "Gimnasio especializado en el desarrollo de la musculatura.");
			assertEquals(data.getURL(), "https://www.musculos.com");
			// Telón #TL
			data = IADC.obtenerDatosInstitucion("Telón");
			assertEquals(data.getNombre(), "Telón");
			assertEquals(data.getDescripcion(), "Actividades deportivas para todas las edades.");
			assertEquals(data.getURL(), "https://telon.com.uy");
			// Olympic #YT
			data = IADC.obtenerDatosInstitucion("Olympic");
			assertEquals(data.getNombre(), "Olympic");
			assertEquals(data.getDescripcion(), "Gimnasia y Aparatos.");
			assertEquals(data.getURL(), "https://www.olympic21.com");
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testAltaInstitucionesOk() {
		try {
			// Generamos una Institucion Nueva			
			// Retorna 0 si se da de alta de manera exitosa; 1 en otro caso;
			int exitoEnAlta = IADC.altaInstitucion("Instituto Nuevo", "https://www.nuevo.com", "Instituto que es muy nuevo.");
			assertEquals(exitoEnAlta, 0);
			DtInstitucion data = IADC.obtenerDatosInstitucion("Instituto Nuevo");
			assertEquals(data.getNombre(), "Instituto Nuevo");
			assertEquals(data.getDescripcion(), "Instituto que es muy nuevo.");
			assertEquals(data.getURL(), "https://www.nuevo.com");
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testAltaInstitucionRepetida() {
		// Generamos una Institucion Nueva
		IADC.altaInstitucion("Instituto Repetido", "https://www.repetido.com", "Instituto que se repite en el sistema.");
		// 0 == operacionExitosa; 1 si no
		int operacionExito = IADC.altaInstitucion("Instituto Repetido","https://www.nuevarepetida.com", "Es repetida.");
		assertEquals(operacionExito, 1);
	}
	
	@Test
	void testCargaSociosOk() {
		try {
			// Emi71 #EL
			DtUsuario socio = IUC.seleccionarUsuario("Emi71");
			assertEquals(socio.getNickname(), "Emi71");
			assertEquals(socio.getNombre(), "Emiliano");
			assertEquals(socio.getApellido(), "Lucas");
			assertEquals(socio.getEmail(), "emi71@gmail.com");
			DtFecha fecha = new DtFecha(1971,12,31,0,0,0);
			assertEquals(socio.getFechaNacimiento().toFecha(), fecha.toFecha());
			// caro #CO
			socio = IUC.seleccionarUsuario("caro");
			assertEquals(socio.getNickname(), "caro");
			assertEquals(socio.getNombre(), "Carolina");
			assertEquals(socio.getApellido(), "Omega");
			assertEquals(socio.getEmail(), "caro@gmail.com");
			fecha = new DtFecha(1983,11,15,0,0,0);
			assertEquals(socio.getFechaNacimiento().toFecha(), fecha.toFecha());
			// euge #EW
			socio = IUC.seleccionarUsuario("euge");
			assertEquals(socio.getNickname(), "euge");
			assertEquals(socio.getNombre(), "Eugenia");
			assertEquals(socio.getApellido(), "Williams");
			assertEquals(socio.getEmail(), "e.will@gmail.com");
			fecha = new DtFecha(1990,4,15,0,0,0);
			assertEquals(socio.getFechaNacimiento().toFecha(), fecha.toFecha());
			// guille #GH
			socio = IUC.seleccionarUsuario("guille");
			assertEquals(socio.getNickname(), "guille");
			assertEquals(socio.getNombre(), "Guillermo");
			assertEquals(socio.getApellido(), "Hector");
			assertEquals(socio.getEmail(), "ghector@gmail.com");
			fecha = new DtFecha(1959,5,15,0,0,0);
			assertEquals(socio.getFechaNacimiento().toFecha(), fecha.toFecha());
			// sergiop #SP
			socio = IUC.seleccionarUsuario("sergiop");
			assertEquals(socio.getNickname(), "sergiop");
			assertEquals(socio.getNombre(), "Sergio");
			assertEquals(socio.getApellido(), "Perez");
			assertEquals(socio.getEmail(), "sergi@gmail.com.uy");
			fecha = new DtFecha(1950,1,28,0,0,0);
			assertEquals(socio.getFechaNacimiento().toFecha(), fecha.toFecha());
			// andy #AR
			socio = IUC.seleccionarUsuario("andy");
			assertEquals(socio.getNickname(), "andy");
			assertEquals(socio.getNombre(), "Andrés");
			assertEquals(socio.getApellido(), "Roman");
			assertEquals(socio.getEmail(), "chino@gmail.org.uy");
			fecha = new DtFecha(1976,3,17,0,0,0);
			assertEquals(socio.getFechaNacimiento().toFecha(), fecha.toFecha());
			// tonyp #AP
			socio = IUC.seleccionarUsuario("tonyp");
			assertEquals(socio.getNickname(), "tonyp");
			assertEquals(socio.getNombre(), "Antonio");
			assertEquals(socio.getApellido(), "Paz");
			assertEquals(socio.getEmail(), "eltony@gmail.org.uy");
			fecha = new DtFecha(1955,2,14,0,0,0);
			assertEquals(socio.getFechaNacimiento().toFecha(), fecha.toFecha());
			// m1k4 #ML
			socio = IUC.seleccionarUsuario("m1k4");
			assertEquals(socio.getNickname(), "m1k4");
			assertEquals(socio.getNombre(), "Micaela");
			assertEquals(socio.getApellido(), "Lopez");
			assertEquals(socio.getEmail(), "mika@gmail.com.ar");
			fecha = new DtFecha(1987,2,23,0,0,0);
			assertEquals(socio.getFechaNacimiento().toFecha(), fecha.toFecha());
			// charly #CB
			socio = IUC.seleccionarUsuario("charly");
			assertEquals(socio.getNickname(), "charly");
			assertEquals(socio.getNombre(), "Carlos");
			assertEquals(socio.getApellido(), "Boston");
			assertEquals(socio.getEmail(), "charly@gmail.com.uy");
			fecha = new DtFecha(1937,5,8,0,0,0);
			assertEquals(socio.getFechaNacimiento().toFecha(), fecha.toFecha());
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testAltaSocioOk() {
		try {
			// Generamos un Socio Nuevo			
			// Retorna 0 si se da de alta de manera exitosa; 1 en otro caso;
			int exitoEnAlta = IUC.ingresarDatosUsuario(new DtSocio("Newuser","Nuevo","Usuario","new@user.com", 
					new DtFecha(2020,1,1,0,0,0)));
			assertEquals(exitoEnAlta, 0);
			DtUsuario socio = IUC.seleccionarUsuario("Newuser");
			assertEquals(socio.getNickname(), "Newuser");
			assertEquals(socio.getNombre(), "Nuevo");
			assertEquals(socio.getApellido(), "Usuario");
			assertEquals(socio.getEmail(), "new@user.com");
			DtFecha fecha = new DtFecha(2020,1,1,0,0,0);
			assertEquals(socio.getFechaNacimiento().toFecha(), fecha.toFecha());
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testSocioNickRepetido() {
		try {
			// Generamos un Socio Nuevo
			IUC.ingresarDatosUsuario(new DtSocio("userRepetido","Repe","Tipo","repe@tido.com", new DtFecha(2020,1,1,0,0,0)));
			// 0 == operacionExitosa; 1 si no
			int operacionExito = IUC.ingresarDatosUsuario(new DtSocio("userRepetido","Guido","Kazka","mostrame@laRepe.com", 
					new DtFecha(1980,1,1,0,0,0)));
			assertEquals(operacionExito, 1);
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testSocioCorreoRepetido() {
		try {
			// Generamos un Socio Nuevo
			IUC.ingresarDatosUsuario(new DtSocio("userRepetido","Repe","Tipo","mostrame@laRepe.com", new DtFecha(2020,1,1,0,0,0)));
			// 0 == operacionExitosa; 1 si no
			int operacionExito = IUC.ingresarDatosUsuario(new DtSocio("guido","Guido","Kazka","mostrame@laRepe.com", 
					new DtFecha(1980,1,1,0,0,0)));
			assertEquals(operacionExito, 1);
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testCargaProfesorOk() {
		try {
			String desc;
			String bio;
			// viktor #VP
			desc = "Victor es un apasionado de los músculos. Sus clases son organizadas en función de distintos "
					+ "aparatos y pesas con el objetivo de desarrollar músculos.";
			bio = "Victor nació en Moscow en 1977. En el año 2005 emigró a Uruguay luego de quedar "
					+ "encantado con el país en un viaje turístico.";
			DtProfesor profe = (DtProfesor)IUC.seleccionarUsuario("viktor");
			DtFecha fecha = new DtFecha(1997,1,1,0,0,0);
			assertEquals(profe.getNickname(), "viktor");
			assertEquals(profe.getNombre(), "Victor");
			assertEquals(profe.getApellido(), "Perez");
			assertEquals(profe.getEmail(), "vperez@fuerza.com");
			assertEquals(profe.getFechaNacimiento().toFecha(), fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(), "Fuerza Bruta");
			assertEquals(profe.getDescripcion(), desc);
			assertEquals(profe.getBiografia(), bio);
			assertEquals(profe.getLink(), "www.vikgym.com");
			// denis #DM
			desc = "A Denis le interesan los deportes con pelota, principalmente el voleibol y el handball.";
			bio = "Denis fue un jugador de voleibol profesional.";
			profe = (DtProfesor)IUC.seleccionarUsuario("denis");
			fecha = new DtFecha(1980,6,14,0,0,0);
			assertEquals(profe.getNickname(), "denis");
			assertEquals(profe.getNombre(), "Denis");
			assertEquals(profe.getApellido(), "Miguel");
			assertEquals(profe.getEmail(), "den80@fuerza.com");
			assertEquals(profe.getFechaNacimiento().toFecha(), fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(), "Telón");
			assertEquals(profe.getDescripcion(), desc);
			assertEquals(profe.getBiografia(), bio);
			assertEquals(profe.getLink(), "www.depecho.com");
			// clazar #CL
			desc = "Carlos es un profesor muy divertido cuyas clases de aeróbica están cargadas de energía.";
			bio = "El interés por la actividad física llevo a Carlos a dejar su trabajo en un estudio "
					+ "contable y abrir su propio gimnasio.";
			profe = (DtProfesor)IUC.seleccionarUsuario("clazar");
			fecha = new DtFecha(1953,6,22,0,0,0);
			assertEquals(profe.getNickname(), "clazar");
			assertEquals(profe.getNombre(), "Carlos");
			assertEquals(profe.getApellido(), "Lazaro");
			assertEquals(profe.getEmail(), "claz4r0@hotmail.com");
			assertEquals(profe.getFechaNacimiento().toFecha(), fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(), "Instituto Natural");
			assertEquals(profe.getDescripcion(), desc);
			assertEquals(profe.getBiografia(), bio);
			assertEquals(profe.getLink(), "www.enforma.com");
			// TheBoss #BS
			desc = "Bruno es un ex-boxeardor que busca entrenar a futuros campeones.";
			bio = "Bruno, mejor conocido como Bruce en el ring, compitió como boxeador entre los años 60s y 70s.";
			profe = (DtProfesor)IUC.seleccionarUsuario("TheBoss");
			fecha = new DtFecha(1949,9,23,0,0,0);
			assertEquals(profe.getNickname(), "TheBoss");
			assertEquals(profe.getNombre(), "Bruno");
			assertEquals(profe.getApellido(), "Sosa");
			assertEquals(profe.getEmail(), "bruceTheBoss@gmail.com");
			assertEquals(profe.getFechaNacimiento().toFecha(), fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(), "Fuerza Bruta");
			assertEquals(profe.getDescripcion(), desc);
			assertEquals(profe.getBiografia(), bio);
			assertEquals(profe.getLink(), "www.bruce.net");
			// Nelson #TN
			desc = "Profesor de natación. Especializado en braza y mariposa.";
			bio = "";
			profe = (DtProfesor)IUC.seleccionarUsuario("Nelson");
			fecha = new DtFecha(1998,1,1,0,0,0);
			assertEquals(profe.getNickname(), "Nelson");
			assertEquals(profe.getNombre(), "Luis");
			assertEquals(profe.getApellido(), "Nelson");
			assertEquals(profe.getEmail(), "nelson@hotmail.com");
			assertEquals(profe.getFechaNacimiento().toFecha(), fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(), "Telón");
			assertEquals(profe.getDescripcion(), desc);
			assertEquals(profe.getBiografia(), bio);
			assertEquals(profe.getLink(), "www.nelson.uy");
			// lale #LL
			desc = "Luego de una exitosa carrera como jugadora de futbol profesional. Laura dedica sus clases a "
					+ "enseñar tácticas de futbol.";
			bio = "Jugadora profesional de futbol desde 2010 a 2020.";
			profe = (DtProfesor)IUC.seleccionarUsuario("lale");
			fecha = new DtFecha(1987,2,14,0,0,0);
			assertEquals(profe.getNickname(), "lale");
			assertEquals(profe.getNombre(), "Laura");
			assertEquals(profe.getApellido(), "Leyes");
			assertEquals(profe.getEmail(), "la_le@outlook.com");
			assertEquals(profe.getFechaNacimiento().toFecha(), fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(), "Telón");
			assertEquals(profe.getDescripcion(), desc);
			assertEquals(profe.getBiografia(), bio);
			assertEquals(profe.getLink(), "www.laley.com");
			// prisc #PI
			desc = "Laura tiene un gran interés por los deportes olímpicos.";
			bio = "";
			profe = (DtProfesor)IUC.seleccionarUsuario("prisc");
			fecha =  new DtFecha(1981,8,13,0,0,0);
			assertEquals(profe.getNickname(), "prisc");
			assertEquals(profe.getNombre(), "Priscila");
			assertEquals(profe.getApellido(), "Pappo");
			assertEquals(profe.getEmail(), "pripa@gmail.com");
			assertEquals(profe.getFechaNacimiento().toFecha(), fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(), "Olympic");
			assertEquals(profe.getDescripcion(), desc);
			assertEquals(profe.getBiografia(), bio);
			assertEquals(profe.getLink(), "www.pi314.net");
			// dagost #DY
			desc = "Profesora dedicada y exigente. No acepta un " + '"' + "no puedo" + '"' + " como respuesta.";
			bio = "";
			profe = (DtProfesor)IUC.seleccionarUsuario("dagost");
			fecha =  new DtFecha(1940,3,5,0,0,0);
			assertEquals(profe.getNickname(), "dagost");
			assertEquals(profe.getNombre(), "Daiana");
			assertEquals(profe.getApellido(), "Agostini");
			assertEquals(profe.getEmail(), "d_1940_ago@gmail.com");
			assertEquals(profe.getFechaNacimiento().toFecha(), fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(), "Olympic");
			assertEquals(profe.getDescripcion(), desc);
			assertEquals(profe.getBiografia(), bio);
			assertEquals(profe.getLink(), "www.dygym.com");
			// aldo #AL
			desc = "Dada su gran estatura Aldo siempre jugó al basquetbol, hoy se dedica a enseñarlo.";
			bio = "";
			profe = (DtProfesor)IUC.seleccionarUsuario("aldo");
			fecha =  new DtFecha(1952,7,17,0,0,0);
			assertEquals(profe.getNickname(), "aldo");
			assertEquals(profe.getNombre(), "Aldo");
			assertEquals(profe.getApellido(), "Vivaldi");
			assertEquals(profe.getEmail(), "aldo@outlook.com");
			assertEquals(profe.getFechaNacimiento().toFecha(), fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(), "Telón");
			assertEquals(profe.getDescripcion(), desc);
			assertEquals(profe.getBiografia(), bio);
			assertEquals(profe.getLink(), "www.sportsaldo.net");
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testAltaProfesorOk() {
		try {
			IADC.altaInstitucion("Instituto Muy Nuevo","https://www.nuevoInsti.com", "Sirve como test.");
			// Generamos un Profesor Nuevo		
			// Retorna 0 si se da de alta de manera exitosa; 1 en otro caso;
			DtFecha fecha =  new DtFecha(1980,1,1,0,0,0);
			int operacionExito = IUC.ingresarDatosUsuario(new DtProfesor("newprofe","Nuevo","Profe","new@profe.com", 
					fecha, "Instituto Nuevo", "Descripcion", "Bio", "www.newProfe.com"));
			assertEquals(operacionExito, 0);
			DtProfesor profe = (DtProfesor)IUC.seleccionarUsuario("newprofe");
			assertEquals(profe.getNickname(), "newprofe");
			assertEquals(profe.getNombre(), "Nuevo");
			assertEquals(profe.getApellido(), "Profe");
			assertEquals(profe.getEmail(), "new@profe.com");
			assertEquals(profe.getFechaNacimiento().toFecha(), fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(), "Instituto Nuevo");
			assertEquals(profe.getDescripcion(), "Descripcion");
			assertEquals(profe.getBiografia(), "Bio");
			assertEquals(profe.getLink(), "www.newProfe.com");
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testProfeInstitucionNoEstaSistema() {
		DtFecha fecha = new DtFecha(1980,1,1,0,0,0);
		Assertions.assertThrows(InstitucionException.class, () -> {IUC.ingresarDatosUsuario(new DtProfesor("theInstitutoLess",
				"Profe", "SinInstituto", "profe@sinInstituto.com", fecha, "Instituto Inexistente", "Descripcion", "Bio", 
				"www.noInstituto.com"));});
	}
	
	@Test
	void testCargaActividadesDeportivaOk() {
		try {
			// Aparatos y pesas #A1
			DtActividadDeportiva actividad = IADC.getActDepExt("Fuerza Bruta", "Aparatos y pesas");
			DtFecha fecha = new DtFecha(2021,3,31,0,0,0);
			assertEquals(actividad.getNombre(),"Aparatos y pesas");
			assertEquals(actividad.getDescripcion(),"Clases de aparatos, pesas y calistenia.");
			assertEquals(actividad.getDuracionMinutos(), 90);
			assertEquals(actividad.getCosto(), 550);
			assertEquals(actividad.getFechaRegistro().toFecha(), fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Fuerza Bruta").contains("Aparatos y pesas"), true);
			// Voleibol #A2
			actividad = IADC.getActDepExt("Telón", "Voleibol");
			fecha = new DtFecha(2021,4,20,0,0,0);
			assertEquals(actividad.getNombre(),"Voleibol");
			assertEquals(actividad.getDescripcion(),"Voleibol en todas sus formas.");
			assertEquals(actividad.getDuracionMinutos(), 120);
			assertEquals(actividad.getCosto(), 750);
			assertEquals(actividad.getFechaRegistro().toFecha(), fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Telón").contains("Voleibol"), true);
			// Aeróbica #A3
			actividad = IADC.getActDepExt("Instituto Natural", "Aeróbica");
			fecha = new DtFecha(2021,5,30,0,0,0);
			assertEquals(actividad.getNombre(),"Aeróbica");
			assertEquals(actividad.getDescripcion(),"Para cuidar el aparato cardiovascular.");
			assertEquals(actividad.getDuracionMinutos(), 110);
			assertEquals(actividad.getCosto(), 800);
			assertEquals(actividad.getFechaRegistro().toFecha(), fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Instituto Natural").contains("Aeróbica"), true);
			// Kickboxing #A4
			actividad = IADC.getActDepExt("Fuerza Bruta", "Kickboxing");
			fecha = new DtFecha(2021,6,7,0,0,0);
			assertEquals(actividad.getNombre(),"Kickboxing");
			assertEquals(actividad.getDescripcion(),"En busca del nuevo campeón de boxeo.");
			assertEquals(actividad.getDuracionMinutos(), 100);
			assertEquals(actividad.getCosto(), 980);
			assertEquals(actividad.getFechaRegistro().toFecha(), fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Fuerza Bruta").contains("Kickboxing"), true);
			// Atletismo #A5
			actividad = IADC.getActDepExt("Telón", "Atletismo");
			fecha = new DtFecha(2021,7,8,0,0,0);
			assertEquals(actividad.getNombre(),"Atletismo");
			assertEquals(actividad.getDescripcion(),"100m , 200m, postas y carreras con obstaculos.");
			assertEquals(actividad.getDuracionMinutos(), 150);
			assertEquals(actividad.getCosto(), 500);
			assertEquals(actividad.getFechaRegistro().toFecha(), fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Telón").contains("Atletismo"), true);
			// Basquetbol #A6
			actividad = IADC.getActDepExt("Telón", "Basquetbol");
			fecha = new DtFecha(2021,7,31,0,0,0);
			assertEquals(actividad.getNombre(),"Basquetbol");
			assertEquals(actividad.getDescripcion(),"Espectáculo conmemorando los 30 años de Violeta.");
			assertEquals(actividad.getDuracionMinutos(), 80);
			assertEquals(actividad.getCosto(), 450);
			assertEquals(actividad.getFechaRegistro().toFecha(), fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Telón").contains("Basquetbol"), true);					
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
        	e.printStackTrace();
		}
	}
	
	@Test
	void testAltaActividadOk() {
		try {
			IADC.altaInstitucion("Instituto Muy Nuevo","https://www.nuevoInsti.com", "Sirve como test.");
			DtFecha fecha = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva nuevaActividad = new DtActividadDeportiva("NuevaActividad", "Desc", 1, 10, fecha);
			// Devuelve true si se ingresa con exito la Actividad Deportiva.
			assertEquals(IADC.ingresarDatosActividadDep("Instituto Muy Nuevo", nuevaActividad), true);
			DtActividadDeportiva actividad = IADC.getActDepExt("Instituto Muy Nuevo", "NuevaActividad");
			assertEquals(actividad.getNombre(), nuevaActividad.getNombre());
			assertEquals(actividad.getDescripcion(), nuevaActividad.getDescripcion());
			assertEquals(actividad.getDuracionMinutos(), nuevaActividad.getDuracionMinutos());
			assertEquals(actividad.getCosto(), nuevaActividad.getCosto());
			assertEquals(actividad.getFechaRegistro().toFecha(), fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Instituto Muy Nuevo").contains(nuevaActividad.getNombre()), true);
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
        	e.printStackTrace();
		}
	}
	
	@Test
	void testActividadRepetida() {
		try {
			IADC.altaInstitucion("Instituto Muy Nuevo","https://www.nuevoInsti.com", "Sirve como test.");
			DtFecha fecha = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadRepetida = new DtActividadDeportiva("ActividadARepetir", "Desc", 1, 10, fecha);
			// Devuelve true si se ingresa con exito la Actividad Deportiva.
			assertEquals(IADC.ingresarDatosActividadDep("Instituto Muy Nuevo", actividadRepetida), true);
			DtActividadDeportiva actividadRepetida2 = new DtActividadDeportiva("ActividadARepetir", "OtraDesc", 2, 30, fecha);
			Assertions.assertThrows(ActividadDeportivaException.class, () -> 
				{IADC.ingresarDatosActividadDep("Instituto Muy Nuevo", actividadRepetida2);});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testActividadInstitucionNoEstaSistema() {
		DtFecha fecha = new DtFecha(2020,1,1,0,0,0);
		DtActividadDeportiva nuevaActividad = new DtActividadDeportiva("ActividadSinInsti", "Desc", 1, 10, fecha);
		// Devuelve true si se ingresa con exito la Actividad Deportiva.
		Assertions.assertThrows(InstitucionException.class, () -> 
			{IADC.ingresarDatosActividadDep("InstitutoInexistente", nuevaActividad);});		
	}
	
	@Test
	void testCargaDeClases() {
		try {
			// Calistenia #C1
			DtClase claseOk = new DtClase("Calistenia", "viktor", "viktor", 1, 5, "https://www.musculos.com/Calistenia", 
					new DtFecha(2021,4,15,15,30,0), new DtFecha(2021,3,31,0,0,0));
			DtClaseExt claseIngresada = IDCC.seleccionarClase("Fuerza Bruta", "Aparatos y pesas", "Calistenia");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
			// Peso libre #C2
	        claseOk = new DtClase("Peso libre", "viktor", "viktor", 1, 5, "https://www.musculos.com/pesolibre", 
	        		new DtFecha(2021,5,1,17,0,0), new DtFecha(2021,3,31,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Fuerza Bruta", "Aparatos y pesas", "Peso libre");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
	        // Aparatos #C3
	        claseOk = new DtClase("Aparatos", "viktor", "viktor", 1, 7, "https://www.musculos.com/aparatos", 
	        		new DtFecha(2021,6,1,18,0,0), new DtFecha(2021,3,31,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Fuerza Bruta", "Aparatos y pesas", "Aparatos");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
			// Voleibol #C4
	        claseOk = new DtClase("Voleibol", "denis", "denis", 10, 21, 
	        		"https://telon.com.uy/voley", new DtFecha(2021,6,10,19,0,0), new DtFecha(2021,4,20,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Telón", "Voleibol", "Voleibol");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
	        // Braza #C5
	        claseOk = new DtClase("Braza", "Nelson", "Nelson", 2, 6, "https://telon.com.uy/natacionB", 
	        		new DtFecha(2021,7,10,20,0,0), new DtFecha(2021,4,20,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Telón", "Voleibol", "Braza");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
	        // Mariposa #C6
	        claseOk = new DtClase("Mariposa", "Nelson", "Nelson", 2, 6, "https://telon.com.uy/natacionM", 
	        		new DtFecha(2021,8,10,17,45,0), new DtFecha(2021,4,20,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Telón", "Voleibol", "Mariposa");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
			// Aeróbica niños #C7
	        claseOk = new DtClase("Aeróbica niños", "clazar", "clazar", 5, 10, "https://www.inatural.com/aeroni", 
	        		new DtFecha(2021,8,15,16,30,0), new DtFecha(2021,5,30,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Instituto Natural", "Aeróbica", "Aeróbica niños");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
	        // Aeróbico adulto mayor #C8
	        claseOk = new DtClase("Aeróbico adulto mayor", "clazar", "clazar", 5, 12, "https://www.inatural.com/aeroam", 
	        		new DtFecha(2021,8,31,19,30,0), new DtFecha(2021,5,30,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Instituto Natural", "Aeróbica", "Aeróbico adulto mayor");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
	        // Aeróbico #C9
			claseOk = new DtClase("Aeróbica", "clazar", "clazar", 5, 20, "https://www.inatural.com/aerogral", 
	        		new DtFecha(2021,9,30,20,0,0), new DtFecha(2021,5,30,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Instituto Natural", "Aeróbica", "Aeróbica");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
			 // Boxeo I #C10
	        claseOk = new DtClase("Boxeo I", "TheBoss", "TheBoss", 1, 4, "https://www.musculos.com/boxeo1", 
	        		new DtFecha(2021,9,1,19,30,0), new DtFecha(2021,6,7,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Fuerza Bruta", "Kickboxing", "Boxeo I");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
	        // Boxeo II #C11
	        claseOk = new DtClase("Boxeo II", "TheBoss", "TheBoss", 2, 2, "https://www.musculos.com/boxeo2", 
	        		new DtFecha(2021,9,30,17,0,0), new DtFecha(2021,6,7,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Fuerza Bruta", "Kickboxing", "Boxeo II");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
	        // Músculos para boxeo #C12
	        claseOk = new DtClase("Músculos para boxeo", "viktor", "viktor", 1, 5, "https://www.musculos.com/muscbox", 
	        		new DtFecha(2021,10,15,20,0,0), new DtFecha(2021,6,7,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Fuerza Bruta", "Kickboxing", "Músculos para boxeo");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
			// 100 M #C13
	        claseOk = new DtClase("100 M", "lale", "lale", 3, 10, "https://telon.com.uy/100m", 
	        		new DtFecha(2021,9,25,19,0,0), new DtFecha(2021,7,8,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Telón", "Atletismo", "100 M");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
	        // 200 M #C14
	        claseOk = new DtClase("200 M", "lale", "lale", 3, 10, "https://telon.com.uy/200m", 
	        		new DtFecha(2021,10,25,18,30,0), new DtFecha(2021,7,8,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Telón", "Atletismo", "200 M");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
	        // Posta #C15
	        claseOk = new DtClase("Posta", "lale", "lale", 8, 16, "https://telon.com.uy/posta", 
	        		new DtFecha(2021,11,25,17,45,0), new DtFecha(2021,7,8,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Telón", "Atletismo", "Posta");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
			// Basquet I #C16
	        claseOk = new DtClase("Basquet I", "aldo", "aldo", 10, 15, "https://telon.com.uy/bball1", 
	        		new DtFecha(2021,9,1,21,0,0), new DtFecha(2021,7,31,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Telón", "Basquetbol", "Basquet I");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
	        // Basquet II #C17
	        claseOk =new DtClase("Basquet II", "aldo", "aldo", 10, 10, "https://telon.com.uy/bball2", 
	        		new DtFecha(2021,10,1,21,0,0), new DtFecha(2021,7,31,0,0,0));
			claseIngresada = IDCC.seleccionarClase("Telón", "Basquetbol", "Basquet II");
			assertEquals(claseIngresada.getNombre(), claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(), claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(), claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(), claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(), claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(), claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(), claseOk.getFechaRegistro().toFechaHora());
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
	    	e.printStackTrace();
		} catch (ClaseException e) {
			fail(e.getMessage());
	    	e.printStackTrace();
		}
	}

	@Test
	void testAltaClaseOk() {
		try {
			// Iniciamos las instancias de institucion, profesor y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
			DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
			DtClase claseNueva = new DtClase("ClaseNueva", "profAuxiliar", "profe@auxiliar.com", 1, 99, 
					"https://www.auxiliar.com/auxiliar", inicioClase, registroClase);
			IDCC.ingresarDatosClase("InstitutoAuxiliar", "ActividadAuxiliar", claseNueva);
			// Seleccionamos la clase recien creada.
	        DtClase claseA = IDCC.seleccionarClase("InstitutoAuxiliar", "ActividadAuxiliar", "ClaseNueva");
	        // Verificamos que este en la Institucion y la ActividadDeportiva.
	        assertEquals(IDCC.obtenerClases("InstitutoAuxiliar", "ActividadAuxiliar").contains(claseNueva.getNombre()), true);
	        assertEquals(claseA.getNombre(), claseNueva.getNombre());
	        assertEquals(claseA.getNicknameProfesor(), claseNueva.getNicknameProfesor());
	        assertEquals(claseA.getMinSocios(), claseNueva.getMinSocios());
	        assertEquals(claseA.getMaxSocios(), claseNueva.getMaxSocios());
	        assertEquals(claseA.getURL(), claseNueva.getURL());
	        assertEquals(claseA.getFechaClase().toFecha(), claseNueva.getFechaClase().toFecha());
	        assertEquals(claseA.getFechaRegistro().toFecha(), claseNueva.getFechaRegistro().toFecha());
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ClaseException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testClaseRepetida() {
		try {
			// Iniciamos las instancias de institucion, profesor y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
			DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
			IDCC.ingresarDatosClase("InstitutoAuxiliar", "ActividadAuxiliar", new DtClase("ClaseRepetida", "profAuxiliar", 
					"profe@auxiliar.com", 1, 99, "https://www.auxiliar.com/repetida", inicioClase, registroClase));
			// Volvemos a crear una clase con el mismo nombre, nos debe saltar el throw ClaseException.
			Assertions.assertThrows(ClaseException.class, () -> {IDCC.ingresarDatosClase("InstitutoAuxiliar", "ActividadAuxiliar", 
					new DtClase("ClaseRepetida", "profAuxiliar", "profe@auxiliar.com", 1, 99, "https://www.auxiliar.com/repetida2", 
					inicioClase, registroClase));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ClaseException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testClaseFechaRegistroInvalida() {
		try {
			// Iniciamos las instancias de institucion, profesor y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
			// Creamos una fecha de registro no valida.
			DtFecha registroClase = new DtFecha(2019,1,1,0,0,0);
			// Creamos la clase con la fecha registro invalida, nos debe saltar el throw FechaInvalidaException.
			Assertions.assertThrows(FechaInvalidaException.class, () -> {IDCC.ingresarDatosClase("InstitutoAuxiliar", 
					"ActividadAuxiliar", new DtClase("ClaseFechaInvalida", "profAuxiliar", "profe@auxiliar.com", 1, 99, 
					"https://www.auxiliar.com/registroInvalido", inicioClase, registroClase));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testClaseFechaInicioInvalida() {
		try {
			// Iniciamos las instancias de institucion, profesor y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			// Creamos una fecha de inicio no valida.
			DtFecha inicioClase = new DtFecha(2019,1,2,0,0,0);
			DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
			// Creamos la clase con la fecha inicio invalida, nos debe saltar el throw FechaInvalidaException.
			Assertions.assertThrows(FechaInvalidaException.class, () -> {IDCC.ingresarDatosClase("InstitutoAuxiliar", 
					"ActividadAuxiliar", new DtClase("ClaseFechaInvalida", "profAuxiliar", "profe@auxiliar.com", 1, 99, 
					"https://www.auxiliar.com/registroInvalido", inicioClase, registroClase));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testClaseNoExisteInstitucion() {
		DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
		DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
		// Creamos la clase con una Institucion no valida, nos debe saltar el throw InstitucionException.
		Assertions.assertThrows(InstitucionException.class, () -> {IDCC.ingresarDatosClase("InstitutoFalso", 
				"ActividadAuxiliar", new DtClase("ClaseARomper", "profAuxiliar", "profe@auxiliar.com", 1, 99, 
				"https://www.auxiliar.com/InstitutoFalso", inicioClase, registroClase));});
	}
	
	@Test
	void testClaseActividadNoEsDeInstitucion() {
		try {
			// Iniciamos las instancias de institucion, profesor y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
			DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
			DtFecha registroClase = new DtFecha(2019,1,1,0,0,0);
			// Creamos la clase con una actividad no valida, nos debe saltar el throw ActividadDeportivaException.
			Assertions.assertThrows(ActividadDeportivaException.class, () -> {IDCC.ingresarDatosClase("InstitutoAuxiliar", 
					"ActividadFalsa", new DtClase("ClaseARomper", "profAuxiliar", "profe@auxiliar.com", 1, 99, 
					"https://www.auxiliar.com/ActividadFalsa", inicioClase, registroClase));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testClaseProfesorNoEsDeInstitucion() {
		try {
			// Iniciamos las instancias de institucion, profesor y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			DtFecha inicioClase = new DtFecha(2019,1,2,0,0,0);
			DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
			// Creamos la clase con un Profesor no valido, nos debe saltar el throw UsuarioNoExisteException.
			Assertions.assertThrows(UsuarioNoExisteException.class, () -> {IDCC.ingresarDatosClase("InstitutoAuxiliar", 
					"ActividadAuxiliar", new DtClase("ClaseProfeInvalido", "profesorFalso", "profe@falso.com", 1, 99, 
					"https://www.auxiliar.com/profeInvalido", inicioClase, registroClase));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testCargaDeCuponerasOk() {
		try {
			DtActividadDeportiva voleibol = IADC.getActDepExt("Telón", "Voleibol");
			DtActividadDeportiva basket = IADC.getActDepExt("Telón", "Basquetbol");
			DtActividadDeportiva aero = IADC.getActDepExt("Instituto Natural", "Aeróbica");
			DtActividadDeportiva aypesas = IADC.getActDepExt("Fuerza Bruta", "Aparatos y pesas");
			DtActividadDeportiva kick = IADC.getActDepExt("Fuerza Bruta", "Kickboxing");
	        // Pelota #P1 : Voleibol(Telón, 7 clases, 750 pesos), Basquetbol(Telón, 18 clases, 450)
			Set<String> actividades = new HashSet<>(Arrays.asList("Voleibol", "Basquetbol"));
			DtFecha inicio = new DtFecha(2021,5,1,0,0,0);
			DtFecha fin = new DtFecha(2021,7,31,23,59,59);
			float costoTotal = (float)(0.8*(voleibol.getCosto()*7 + basket.getCosto()*18));
			DtCuponera cuponeraAProbar = ICC.seleccionarCuponera("Pelota");
			assertEquals(cuponeraAProbar.getNombre(), "Pelota");
			assertEquals(cuponeraAProbar.getDescripcion(), "Deportes con pelota.");
			assertEquals(cuponeraAProbar.getDescuento(), 20);
			assertEquals(cuponeraAProbar.getCosto(), costoTotal);
			assertEquals(cuponeraAProbar.getFechaInicio().toFecha(), inicio.toFecha());
			assertEquals(cuponeraAProbar.getFechaFin().toFecha(), fin.toFecha());
			for (DtClasesCuponera x : cuponeraAProbar.getContenido()) {
				assertEquals(actividades.contains(x.getNombreActividad()), true);
			}
			// Gimnasia #P2 : Aeróbica(Instituto Natural, 2 clases, 800 pesos), Aparatos y pesas(Fuerza Bruta, 8 clases, 550)
			actividades = new HashSet<>(Arrays.asList("Aeróbica", "Aparatos y pesas"));
			inicio = new DtFecha(2021,8,1,0,0,0);
			fin = new DtFecha(2021,9,30,23,59,59);
			costoTotal = (float)(0.7*(aero.getCosto()*2 + aypesas.getCosto()*8));
			cuponeraAProbar = ICC.seleccionarCuponera("Gimnasia");
			assertEquals(cuponeraAProbar.getNombre(), "Gimnasia");
			assertEquals(cuponeraAProbar.getDescripcion(), "Aeróbica y aparatos.");
			assertEquals(cuponeraAProbar.getDescuento(), 30);
			assertEquals(cuponeraAProbar.getCosto(), costoTotal);
			assertEquals(cuponeraAProbar.getFechaInicio().toFecha(), inicio.toFecha());
			assertEquals(cuponeraAProbar.getFechaFin().toFecha(), fin.toFecha());
			for (DtClasesCuponera x : cuponeraAProbar.getContenido()) {
				assertEquals(actividades.contains(x.getNombreActividad()), true);
			}
			// Músculos #P2 : Kickboxing(Fuerza Bruta, 11 clases, 980 pesos), Aparatos y pesas(Fuerza Bruta, 12 clases, 550)
			actividades = new HashSet<>(Arrays.asList("Kickboxing", "Aparatos y pesas"));
			inicio = new DtFecha(2021,8,15,0,0,0);
			fin = new DtFecha(2021,11,15,23,59,59);
			costoTotal = (float)(0.9*(kick.getCosto()*11 + aypesas.getCosto()*12));
			cuponeraAProbar = ICC.seleccionarCuponera("Músculos");
			assertEquals(cuponeraAProbar.getNombre(), "Músculos");
			assertEquals(cuponeraAProbar.getDescripcion(), "Pesas.");
			assertEquals(cuponeraAProbar.getDescuento(), 10);
			assertEquals(cuponeraAProbar.getCosto(), costoTotal);
			assertEquals(cuponeraAProbar.getFechaInicio().toFecha(), inicio.toFecha());
			assertEquals(cuponeraAProbar.getFechaFin().toFecha(), fin.toFecha());
			for (DtClasesCuponera x : cuponeraAProbar.getContenido()) {
				assertEquals(actividades.contains(x.getNombreActividad()), true);
			}
		} catch (NoExisteCuponeraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
    }
	
	@Test
	void testAltaCuponeraOk() {
		try {
			// Iniciamos las instancias de institucion y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			// Generamos la cuponera a probar.
	        ICC.ingresarCuponera("CupNueva", "PruebaCuponera", new DtFecha(2020,1,1,0,0,0), new DtFecha(2022,1,1,0,0,0), 
	        		55, new DtFecha(2020,1,1,0,0,0));
	        ICC.agregarActividadCuponera("CupNueva", "InstitutoAuxiliar", "ActividadAuxiliar", 10);
			Set<String> actividades = new HashSet<>(Arrays.asList("ActividadAuxiliar"));
			DtFecha inicio = new DtFecha(2020,1,1,0,0,0);
			DtFecha fin = new DtFecha(2022,1,1,0,0,0);
			float costoTotal = (float)(0.45*(actividadAuxiliar.getCosto())*10);
			DtCuponera cuponeraAProbar = ICC.seleccionarCuponera("CupNueva");
			assertEquals(cuponeraAProbar.getNombre(), "CupNueva");
			assertEquals(cuponeraAProbar.getDescripcion(), "PruebaCuponera");
			assertEquals(cuponeraAProbar.getDescuento(), 55);
			assertEquals(cuponeraAProbar.getCosto(), costoTotal);
			assertEquals(cuponeraAProbar.getFechaInicio().toFecha(), inicio.toFecha());
			assertEquals(cuponeraAProbar.getFechaFin().toFecha(), fin.toFecha());
			for (DtClasesCuponera x : cuponeraAProbar.getContenido()) {
				assertEquals(actividades.contains(x.getNombreActividad()), true);
			}			
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NoExisteCuponeraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (CuponeraRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testCuponeraRepetida() {
		try {
			// Generamos la cuponera a probar.
	        ICC.ingresarCuponera("CupARepetir", "PrimeraIteracion", new DtFecha(2020,1,1,0,0,0), new DtFecha(2022,1,1,0,0,0), 
	        		55, new DtFecha(2020,1,1,0,0,0));
	        Assertions.assertThrows(CuponeraRepetidaException.class, () -> {ICC.ingresarCuponera("CupARepetir", "SegundaIteracion", 
	        		new DtFecha(2020,1,1,0,0,0), new DtFecha(2022,1,1,0,0,0), 55, new DtFecha(2020,1,1,0,0,0));});
		} catch (CuponeraRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testCuponeraNoExisteInstitucion() {
		try {
			// Iniciamos las instancias de institucion y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			// Generamos la cuponera a probar.
			try {
		        ICC.ingresarCuponera("CupAuxiliar", "PruebaCuponera", new DtFecha(2020,1,1,0,0,0), new DtFecha(2022,1,1,0,0,0), 
		        		55, new DtFecha(2020,1,1,0,0,0));
			} catch (CuponeraRepetidaException ignore) { }
	        Assertions.assertThrows(InstitucionException.class, () -> {ICC.agregarActividadCuponera("CupAuxiliar", 
	        		"InstitutoFalso", "ActividadAuxiliar", 10);});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testCuponeraActividadNoEsDeInstitucion() {
		try {
			// Iniciamos las instancias de institucion y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			// Generamos la cuponera a probar.
			try {
		        ICC.ingresarCuponera("CupAuxiliar", "PruebaCuponera", new DtFecha(2020,1,1,0,0,0), new DtFecha(2022,1,1,0,0,0), 
		        		55, new DtFecha(2020,1,1,0,0,0));
			} catch (CuponeraRepetidaException ignore) { }
	        Assertions.assertThrows(ActividadDeportivaException.class, () -> {ICC.agregarActividadCuponera("CupAuxiliar", 
	        		"InstitutoAuxiliar", "ActividadFalsa", 10);});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testCuponeraFechaRegistroInvalida() {
		try {
			// Iniciamos las instancias de institucion y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			// Generamos la cuponera a probar.
	        Assertions.assertThrows(FechaInvalidaException.class, () -> {ICC.ingresarCuponera("CupAuxiliar", "PruebaCuponera", 
	        		new DtFecha(2020,1,1,0,0,0), new DtFecha(2022,1,1,0,0,0), 55, new DtFecha(2022,1,1,0,0,0));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testCargaDeInscripcionesClase() {
		try {
			// Calistenia
			DtClaseExt datosClase = IDCC.seleccionarClase("Fuerza Bruta", "Aparatos y pesas", "Calistenia");
			List<String> alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("caro"), true);
			assertEquals(alumnosClase.contains("sergiop"), true);
			assertEquals(alumnosClase.contains("andy"), true);
			// 
			datosClase = IDCC.seleccionarClase("Fuerza Bruta", "Aparatos y pesas", "Peso libre");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("andy"), true);
			assertEquals(alumnosClase.contains("tonyp"), true);
			assertEquals(alumnosClase.contains("caro"), true);
			assertEquals(alumnosClase.contains("m1k4"), true); 
			// Aparatos
			datosClase = IDCC.seleccionarClase("Fuerza Bruta", "Aparatos y pesas", "Aparatos");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("charly"), true);
			assertEquals(alumnosClase.contains("caro"), true);
			assertEquals(alumnosClase.contains("m1k4"), true);
			// Voleibol
			datosClase = IDCC.seleccionarClase("Telón", "Voleibol", "Voleibol");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("Emi71"), true);
			assertEquals(alumnosClase.contains("euge"), true);
			assertEquals(alumnosClase.contains("sergiop"), true);
			assertEquals(alumnosClase.contains("tonyp"), true);
			// Braza
			datosClase = IDCC.seleccionarClase("Telón", "Voleibol", "Braza");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("guille"), true);
			assertEquals(alumnosClase.contains("euge"), true);
			assertEquals(alumnosClase.contains("m1k4"), true);
			// Mariposa
			datosClase = IDCC.seleccionarClase("Telón", "Voleibol", "Mariposa");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("charly"), true);
			assertEquals(alumnosClase.contains("sergiop"), true);
			assertEquals(alumnosClase.contains("andy"), true);
			// Aeróbica niños
			datosClase = IDCC.seleccionarClase("Instituto Natural", "Aeróbica", "Aeróbica niños");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("m1k4"), true);
			// Aeróbico adulto mayor
			datosClase = IDCC.seleccionarClase("Instituto Natural", "Aeróbica", "Aeróbico adulto mayor");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("Emi71"), true);
			assertEquals(alumnosClase.contains("guille"), true);
			assertEquals(alumnosClase.contains("andy"), true);
			// Aeróbica
			datosClase = IDCC.seleccionarClase("Instituto Natural", "Aeróbica", "Aeróbica");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("caro"), true);
			assertEquals(alumnosClase.contains("euge"), true);
			// Boxeo I
			datosClase = IDCC.seleccionarClase("Fuerza Bruta", "Kickboxing", "Boxeo I");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("andy"), true);
			assertEquals(alumnosClase.contains("tonyp"), true);
			assertEquals(alumnosClase.contains("m1k4"), true);
			// Boxeo II
			datosClase = IDCC.seleccionarClase("Fuerza Bruta", "Kickboxing", "Boxeo II");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("sergiop"), true);
			assertEquals(alumnosClase.contains("guille"), true);
			// Músculos para boxeo
			datosClase = IDCC.seleccionarClase("Fuerza Bruta", "Kickboxing", "Músculos para boxeo");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("Emi71"), true);
			assertEquals(alumnosClase.contains("caro"), true);
			assertEquals(alumnosClase.contains("euge"), true);
			assertEquals(alumnosClase.contains("sergiop"), true);
			// 100 M
			datosClase = IDCC.seleccionarClase("Telón", "Atletismo", "100 M");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("guille"), true);
			assertEquals(alumnosClase.contains("charly"), true);
			// 200 M
			datosClase = IDCC.seleccionarClase("Telón", "Atletismo", "200 M");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("Emi71"), true);
			assertEquals(alumnosClase.contains("charly"), true);
			// Posta
			datosClase = IDCC.seleccionarClase("Telón", "Atletismo", "Posta");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("caro"), true);
			// Basquet I
			datosClase = IDCC.seleccionarClase("Telón", "Basquetbol", "Basquet I");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("sergiop"), true);
			assertEquals(alumnosClase.contains("Emi71"), true);
			assertEquals(alumnosClase.contains("tonyp"), true);
			// Basquet II
			datosClase = IDCC.seleccionarClase("Telón", "Basquetbol", "Basquet II");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("andy"), true);
			assertEquals(alumnosClase.contains("tonyp"), true);
			assertEquals(alumnosClase.contains("caro"), true);
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ClaseException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	@Test
	void testInscripcionClaseSinCuponeraOk() {
		try {
		// Iniciamos las instancias de institucion, socio, profesor, clase, cuponera y actividadDeportiva.
		IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
		IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar","Socio","Auxiliar","socio@auxiliar.com", 
				new DtFecha(1998,1,1,0,0,0)));
		IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
				new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
		DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
		DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
		try {
			IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
		} catch (ActividadDeportivaException ignore) { }
		DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
		DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
		DtClase claseNueva = new DtClase("ClaseAuxiliar", "profAuxiliar", "profe@auxiliar.com", 1, 99, 
				"https://www.auxiliar.com/auxiliar", inicioClase, registroClase);
		try {
			IDCC.ingresarDatosClase("InstitutoAuxiliar", "ActividadAuxiliar", claseNueva);
		} catch (ClaseException ignore) { }
		IDCC.inscribirSocio("InstitutoAuxiliar", "ActividadAuxiliar", "ClaseAuxiliar", "socioAuxiliar", TReg.general, 
				new DtFecha(2020,1,1,0,0,0));
		DtClaseExt datosClase = IDCC.seleccionarClase("InstitutoAuxiliar", "ActividadAuxiliar", "ClaseAuxiliar");
		List<String> alumnosClase = datosClase.getNickAlumnos();
		assertEquals(alumnosClase.contains("socioAuxiliar"), true);
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ClaseException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NoExisteCuponeraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testReinscripcionInvalida() {
		try {
			// Iniciamos las instancias de institucion, socio, profesor, clase, cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioRepetido","Socio","Auxiliar","sociorepetido@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0)));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
			DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
			DtClase claseNueva = new DtClase("ClaseAuxiliar", "profAuxiliar", "profe@auxiliar.com", 1, 99, 
					"https://www.auxiliar.com/auxiliar", inicioClase, registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar", "ActividadAuxiliar", claseNueva);
			} catch (ClaseException ignore) { }
			IDCC.inscribirSocio("InstitutoAuxiliar", "ActividadAuxiliar", "ClaseAuxiliar", "socioRepetido", TReg.general, 
					new DtFecha(2020,1,1,0,0,0));
			Assertions.assertThrows(ClaseException.class, () -> {IDCC.inscribirSocio("InstitutoAuxiliar", 
					"ActividadAuxiliar", "ClaseAuxiliar", "socioRepetido", TReg.general, new DtFecha(2020,1,1,0,0,0));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ClaseException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NoExisteCuponeraException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testInscripcionClaseIniciada() {
		try {
			// Iniciamos las instancias de institucion, socio, profesor, clase, cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar","Socio","Auxiliar","socio@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0)));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
			DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
			DtClase claseNueva = new DtClase("ClaseAuxiliarFail", "profAuxiliar", "profe@auxiliar.com", 1, 99, 
					"https://www.auxiliar.com/auxiliar", inicioClase, registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar", "ActividadAuxiliar", claseNueva);
			} catch (ClaseException ignore) { }
			Assertions.assertThrows(FechaInvalidaException.class, () -> {IDCC.inscribirSocio("InstitutoAuxiliar", 
					"ActividadAuxiliar", "ClaseAuxiliarFail", "socioAuxiliar", TReg.general, new DtFecha(2020,1,3,0,0,0));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testInscripcionFechaInvalida() {
		try {
			// Iniciamos las instancias de institucion, socio, profesor, clase, cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar","Socio","Auxiliar","socio@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0)));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
			DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
			DtClase claseNueva = new DtClase("ClaseAuxiliarFail", "profAuxiliar", "profe@auxiliar.com", 1, 99, 
					"https://www.auxiliar.com/auxiliar", inicioClase, registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar", "ActividadAuxiliar", claseNueva);
			} catch (ClaseException ignore) { }
			Assertions.assertThrows(FechaInvalidaException.class, () -> {IDCC.inscribirSocio("InstitutoAuxiliar", 
					"ActividadAuxiliar", "ClaseAuxiliarFail", "socioAuxiliar", TReg.general, new DtFecha(2019,12,31,0,0,0));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testInscipcionNoExisteInstitucion() {
		try {
			// Iniciamos las instancias de institucion, socio, profesor, clase, cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar","Socio","Auxiliar","socio@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0)));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
			DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
			DtClase claseNueva = new DtClase("ClaseAuxiliarFail", "profAuxiliar", "profe@auxiliar.com", 1, 99, 
					"https://www.auxiliar.com/auxiliar", inicioClase, registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar", "ActividadAuxiliar", claseNueva);
			} catch (ClaseException ignore) { }
			Assertions.assertThrows(InstitucionException.class, () -> {IDCC.inscribirSocio("InstitutoFalso", 
					"ActividadAuxiliar", "ClaseAuxiliarFail", "socioAuxiliar", TReg.general, new DtFecha(2020,1,1,0,0,0));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testInscripcionActividadNoEsDeInstitucion() {
		try {
			// Iniciamos las instancias de institucion, socio, profesor, clase, cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar","Socio","Auxiliar","socio@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0)));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
			DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
			DtClase claseNueva = new DtClase("ClaseAuxiliarFail", "profAuxiliar", "profe@auxiliar.com", 1, 99, 
					"https://www.auxiliar.com/auxiliar", inicioClase, registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar", "ActividadAuxiliar", claseNueva);
			} catch (ClaseException ignore) { }
			Assertions.assertThrows(ActividadDeportivaException.class, () -> {IDCC.inscribirSocio("InstitutoAuxiliar", 
					"ActividadFalsa", "ClaseAuxiliarFail", "socioAuxiliar", TReg.general, new DtFecha(2020,1,1,0,0,0));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testInscripcionClaseNoEsDeInstitucion() {
		try {
			// Iniciamos las instancias de institucion, socio, profesor, clase, cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar","Socio","Auxiliar","socio@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0)));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
			DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
			DtClase claseNueva = new DtClase("ClaseAuxiliarFail", "profAuxiliar", "profe@auxiliar.com", 1, 99, 
					"https://www.auxiliar.com/auxiliar", inicioClase, registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar", "ActividadAuxiliar", claseNueva);
			} catch (ClaseException ignore) { }
			Assertions.assertThrows(ClaseException.class, () -> {IDCC.inscribirSocio("InstitutoAuxiliar", 
					"ActividadAuxiliar", "ClaseFalsa", "socioAuxiliar", TReg.general, new DtFecha(2020,1,1,0,0,0));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testClaseLlena() {
		try {
			// Iniciamos las instancias de institucion, socio, profesor, clase, cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar1","Socio","Auxiliar","socio1@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0)));
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar2","Socio","Auxiliar","socio2@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0)));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
			DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
			DtClase claseNueva = new DtClase("ClaseChica", "profAuxiliar", "profe@auxiliar.com", 1, 1, 
					"https://www.auxiliar.com/auxiliar", inicioClase, registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar", "ActividadAuxiliar", claseNueva);
			} catch (ClaseException ignore) { }
			IDCC.inscribirSocio("InstitutoAuxiliar", "ActividadAuxiliar", "ClaseChica", "socioAuxiliar1", TReg.general, 
					new DtFecha(2020,1,1,0,0,0));
			Assertions.assertThrows(ClaseException.class, () -> {IDCC.inscribirSocio("InstitutoAuxiliar", 
					"ActividadAuxiliar", "ClaseChica", "socioAuxiliar2", TReg.general, new DtFecha(2020,1,1,0,0,0));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ClaseException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NoExisteCuponeraException e) {
			fail(e.getMessage());
			e.printStackTrace();		
		}
	}
	
	@Test
	void testInscripcionSinCuponeraValida() {
		try {
			// Iniciamos las instancias de institucion, socio, profesor, clase, cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar","https://www.auxiliar.com", "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliarSinCuponera","Socio","No Cuponera",
					"socioSinCuponera@auxiliar.com", new DtFecha(1998,1,1,0,0,0)));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar","Profesor","Auxiliar","profe@auxiliar.com", 
					new DtFecha(1998,1,1,0,0,0), "InstitutoAuxiliar", "Auxiliar", "Auxiliar" ,"www.auxiliar.uy"));
			DtFecha fechaActividad = new DtFecha(2020,1,1,0,0,0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar", "Auxiliar", 1, 10, fechaActividad);
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar", actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { }
			DtFecha inicioClase = new DtFecha(2020,1,2,0,0,0);
			DtFecha registroClase = new DtFecha(2020,1,1,0,0,0);
			DtClase claseNueva = new DtClase("ClaseAuxiliarFail", "profAuxiliar", "profe@auxiliar.com", 1, 10, 
					"https://www.auxiliar.com/auxiliar", inicioClase, registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar", "ActividadAuxiliar", claseNueva);
			} catch (ClaseException ignore) { }
			Assertions.assertThrows(NoExisteCuponeraException.class, () -> {IDCC.inscribirSocio("InstitutoAuxiliar", 
					"ActividadAuxiliar", "ClaseAuxiliarFail", "socioAuxiliar", TReg.cuponera, new DtFecha(2020,1,1,0,0,0));});
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
}