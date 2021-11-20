package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.DtActividadDeportiva;
import datatypes.DtActividadDeportivaExt;
import datatypes.DtCategoria;
import datatypes.DtClase;
import datatypes.DtClaseExt;
import datatypes.DtClasesCuponera;
import datatypes.DtCuponera;
import datatypes.DtFecha;
import datatypes.DtSocio;
import datatypes.DtSocioExt;
import datatypes.DtUsuario;
import datatypes.TEstado;
import datatypes.TReg;
import datatypes.DtInstitucion;
import datatypes.DtPremio;
import datatypes.DtProfesor;
import datatypes.DtProfesorExt;

import excepciones.ActividadDeportivaException;
import excepciones.CategoriaException;
import excepciones.ClaseException;
import excepciones.CuponeraInmutableException;
import excepciones.CuponeraNoExisteException;
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
import logica.persistencia.DataPersistencia;

public class TestCasos {
	
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
		DataPersistencia.getInstance().nuketownDetonator();
	}
	
	public static void cargaDeCasos() {
		try {
			// ALTA INSTITUCIONES
			// Instituto Natural #IN
			IADC.altaInstitucion("Instituto Natural",   "Clases de gimnasia,   aeróbica,   spinning y yoga.",   "https://www.inatural.com");
			// Fuerza Bruta #FB
			IADC.altaInstitucion("Fuerza Bruta",   "Gimnasio especializado en el desarrollo de la musculatura.",   "https://www.musculos.com");
			// Telón #TL
			IADC.altaInstitucion("Telón",   "Actividades deportivas para todas las edades.",   "https://telon.com.uy");
			// Olympic #YT
			IADC.altaInstitucion("Olympic",   "Gimnasia y Aparatos.",   "https://www.olympic21.com");
			
			// ALTA USUARIOS

			// SOCIOS
			// Emi71 #EL
			IUC.ingresarDatosUsuario(new DtSocio("Emi71",   "Emiliano",   "Lucas",   "emi71@gmail.com",   "asdfg456",   new DtFecha(1971,   12,   31,   0,   0,   0),   null));
			// caro #CO
			IUC.ingresarDatosUsuario(new DtSocio("caro",   "Carolina",   "Omega",   "caro@gmail.com",   "123rtgfdv",   new DtFecha(1983,   11,   15,   0,   0,   0),   null));
			// euge #EW
			IUC.ingresarDatosUsuario(new DtSocio("euge",   "Eugenia",   "Williams",   "e.will@gmail.com",   "poiuy086",   new DtFecha(1990,   4,   15,   0,   0,   0),   null));
			// guille #GH
			IUC.ingresarDatosUsuario(new DtSocio("guille",   "Guillermo",   "Hector",   "ghector@gmail.com",   "GTO468",   new DtFecha(1959,   5,   15,   0,   0,   0),   null));
			// sergiop #SP
			IUC.ingresarDatosUsuario(new DtSocio("sergiop",   "Sergio",   "Perez",   "sergi@gmail.com.uy",   "HGF135",   new DtFecha(1950,   1,   28,   0,   0,   0),   null));
			// andy #AR
			IUC.ingresarDatosUsuario(new DtSocio("andy",   "Andrés",   "Roman",   "chino@gmail.org.uy",   "lkj65D",   new DtFecha(1976,   3,   17,   0,   0,   0),   null));
			// tonyp #AP
			IUC.ingresarDatosUsuario(new DtSocio("tonyp",   "Antonio",   "Paz",   "eltony@gmail.org.uy",   "jhvf395",   new DtFecha(1955,   2,   14,   0,   0,   0),   null));
			// m1k4 #ML
			IUC.ingresarDatosUsuario(new DtSocio("m1k4",   "Micaela",   "Lopez",   "mika@gmail.com.ar",   "ijngr024",   new DtFecha(1987,   2,   23,   0,   0,   0),   null));
			// charly #CB
			IUC.ingresarDatosUsuario(new DtSocio("charly",   "Carlos",   "Boston",   "charly@gmail.com.uy",   "987mnbgh",   new DtFecha(1937,   5,   8,   0,   0,   0),   null));	
			
			// PROFESORES
			String desc;
			String bio;
			// viktor #VP
			desc = "Victor es un apasionado de los músculos. Sus clases son organizadas en función de distintos "
					+ "aparatos y pesas con el objetivo de desarrollar músculos.";
			bio = "Victor nació en Moscow en 1977. En el año 2005 emigró a Uruguay luego de quedar "
					+ "encantado con el país en un viaje turístico.";
			IUC.ingresarDatosUsuario(new DtProfesor("viktor",   "Victor",   "Perez",   "vperez@fuerza.com",   "lkj34df",   new DtFecha(1997,   1,   1,   0,   0,   0),   
					"Fuerza Bruta",   desc,   bio ,   "www.vikgym.com",   null));
			// denis #DM
			desc = "A Denis le interesan los deportes con pelota,   principalmente el voleibol y el handball.";
			bio = "Denis fue un jugador de voleibol profesional.";
			IUC.ingresarDatosUsuario(new DtProfesor("denis",   "Denis",   "Miguel",   "den80@fuerza.com",   "poke579",   new DtFecha(1980,   6,   14,   0,   0,   0),   
					"Telón",   desc,   bio ,   "www.depecho.com",   null));
			// clazar #CL
			desc = "Carlos es un profesor muy divertido cuyas clases de aeróbica están cargadas de energía.";
			bio = "El interés por la actividad física llevo a Carlos a dejar su trabajo en un estudio "
					+ "contable y abrir su propio gimnasio.";
			IUC.ingresarDatosUsuario(new DtProfesor("clazar",   "Carlos",   "Lazaro",   "claz4r0@hotmail.com",   "mkji648",   new DtFecha(1953,   6,   22,   0,   0,   0),   
					"Instituto Natural",   desc,   bio ,   "www.enforma.com",   null));
			// TheBoss #BS
			desc = "Bruno es un ex-boxeardor que busca entrenar a futuros campeones.";
			bio = "Bruno,   mejor conocido como Bruce en el ring,   compitió como boxeador entre los años 60s y 70s.";
			IUC.ingresarDatosUsuario(new DtProfesor("TheBoss",   "Bruno",   "Sosa",   "bruceTheBoss@gmail.com",   "fcku0123",   new DtFecha(1949,   9,   23,   0,   0,   0),   
					"Fuerza Bruta",   desc,   bio ,   "www.bruce.net",   null));
			// Nelson #TN
			desc = "Profesor de natación. Especializado en braza y mariposa.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("Nelson",   "Luis",   "Nelson",   "nelson@hotmail.com",   "vbmn4r",   new DtFecha(1998,   1,   1,   0,   0,   0),   
					"Telón",   desc,   bio ,   "www.nelson.uy",   null));
			// lale #LL
			desc = "Luego de una exitosa carrera como jugadora de futbol profesional. Laura dedica sus clases a "
					+ "enseñar tácticas de futbol.";
			bio = "Jugadora profesional de futbol desde 2010 a 2020.";
			IUC.ingresarDatosUsuario(new DtProfesor("lale",   "Laura",   "Leyes",   "la_le@outlook.com",   "ncnl123",   new DtFecha(1987,   2,   14,   0,   0,   0),   
					"Telón",   desc,   bio ,   "www.laley.com",   null));
			// prisc #PI
			desc = "Laura tiene un gran interés por los deportes olímpicos.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("prisc",   "Priscila",   "Pappo",   "pripa@gmail.com",   "mny101",   new DtFecha(1981,   8,   13,   0,   0,   0),   
					"Olympic",   desc,   bio ,   "www.pi314.net",   null));
			// dagost #DY
			desc = "Profesora dedicada y exigente. No acepta un " + '"' + "no puedo" + '"' + " como respuesta.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("dagost",   "Daiana",   "Agostini",   "d_1940_ago@gmail.com",   "1o1vbm",   new DtFecha(1940,   3,   5,   0,   0,   0),   
					"Olympic",   desc,   bio ,   "www.dygym.com",   null));
			// aldo #AL
			desc = "Dada su gran estatura Aldo siempre jugó al basquetbol,   hoy se dedica a enseñarlo.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("aldo",   "Aldo",   "Vivaldi",   "aldo@outlook.com",   "ultraton01",   new DtFecha(1952,   7,   17,   0,   0,   0),   
					"Telón",   desc,   bio ,   "www.sportsaldo.net",   null));
				
			//LOS SEGUIDOS/SEGUIDORES
 			String[] matrizUsuaria = {"Emi71",   "caro",   "euge",   "guille",   "sergiop",   "andy",   "tonyp",   "m1k4",   "charly",   "viktor",   "denis",   "clazar",   "TheBoss",   "Nelson",   "lale",   
			              "prisc",   "dagost",   "aldo"};
			IUC.seguir(matrizUsuaria[0],   matrizUsuaria[3]);
			IUC.seguir(matrizUsuaria[1],   matrizUsuaria[2]);
			IUC.seguir(matrizUsuaria[1],   matrizUsuaria[3]);
			IUC.seguir(matrizUsuaria[2],   matrizUsuaria[0]);
			IUC.seguir(matrizUsuaria[2],   matrizUsuaria[1]);
			IUC.seguir(matrizUsuaria[2],   matrizUsuaria[7]);
			IUC.seguir(matrizUsuaria[3],   matrizUsuaria[0]);
			IUC.seguir(matrizUsuaria[3],   matrizUsuaria[1]);
			IUC.seguir(matrizUsuaria[3],   matrizUsuaria[2]);
			IUC.seguir(matrizUsuaria[3],   matrizUsuaria[12]);
			IUC.seguir(matrizUsuaria[4],   matrizUsuaria[2]);
			IUC.seguir(matrizUsuaria[4],   matrizUsuaria[5]);
			IUC.seguir(matrizUsuaria[4],   matrizUsuaria[11]);
			IUC.seguir(matrizUsuaria[5],   matrizUsuaria[1]);
			IUC.seguir(matrizUsuaria[5],   matrizUsuaria[6]);
			IUC.seguir(matrizUsuaria[5],   matrizUsuaria[11]);
			IUC.seguir(matrizUsuaria[6],   matrizUsuaria[1]);
			IUC.seguir(matrizUsuaria[6],   matrizUsuaria[7]);
			IUC.seguir(matrizUsuaria[6],   matrizUsuaria[8]);
			IUC.seguir(matrizUsuaria[7],   matrizUsuaria[4]);
			IUC.seguir(matrizUsuaria[7],   matrizUsuaria[6]);
			IUC.seguir(matrizUsuaria[8],   matrizUsuaria[6]);
			IUC.seguir(matrizUsuaria[8],   matrizUsuaria[13]);
			IUC.seguir(matrizUsuaria[9],   matrizUsuaria[6]);
			IUC.seguir(matrizUsuaria[9],   matrizUsuaria[7]);
			IUC.seguir(matrizUsuaria[9],   matrizUsuaria[11]);
			IUC.seguir(matrizUsuaria[9],   matrizUsuaria[14]);
			IUC.seguir(matrizUsuaria[9],   matrizUsuaria[15]);
			IUC.seguir(matrizUsuaria[10],   matrizUsuaria[0]);
			IUC.seguir(matrizUsuaria[10],   matrizUsuaria[1]);
			IUC.seguir(matrizUsuaria[10],   matrizUsuaria[2]);
			IUC.seguir(matrizUsuaria[10],   matrizUsuaria[3]);
			IUC.seguir(matrizUsuaria[10],   matrizUsuaria[4]);
			IUC.seguir(matrizUsuaria[10],   matrizUsuaria[5]);
			IUC.seguir(matrizUsuaria[10],   matrizUsuaria[6]);
			IUC.seguir(matrizUsuaria[10],   matrizUsuaria[7]);
			IUC.seguir(matrizUsuaria[10],   matrizUsuaria[8]);
			IUC.seguir(matrizUsuaria[11],   matrizUsuaria[1]);
			IUC.seguir(matrizUsuaria[11],   matrizUsuaria[2]);
			IUC.seguir(matrizUsuaria[11],   matrizUsuaria[3]);
			IUC.seguir(matrizUsuaria[11],   matrizUsuaria[12]);
			IUC.seguir(matrizUsuaria[12],   matrizUsuaria[3]);
			IUC.seguir(matrizUsuaria[12],   matrizUsuaria[5]);
			IUC.seguir(matrizUsuaria[12],   matrizUsuaria[7]);
			IUC.seguir(matrizUsuaria[13],   matrizUsuaria[0]);
			IUC.seguir(matrizUsuaria[13],   matrizUsuaria[5]);
			IUC.seguir(matrizUsuaria[13],   matrizUsuaria[6]);
			IUC.seguir(matrizUsuaria[13],   matrizUsuaria[14]);
			IUC.seguir(matrizUsuaria[13],   matrizUsuaria[15]);
			IUC.seguir(matrizUsuaria[13],   matrizUsuaria[16]);
			IUC.seguir(matrizUsuaria[14],   matrizUsuaria[8]);
			IUC.seguir(matrizUsuaria[14],   matrizUsuaria[13]);
			IUC.seguir(matrizUsuaria[15],   matrizUsuaria[8]);
			IUC.seguir(matrizUsuaria[15],   matrizUsuaria[13]);
			IUC.seguir(matrizUsuaria[16],   matrizUsuaria[6]);
			IUC.seguir(matrizUsuaria[16],   matrizUsuaria[8]);
			IUC.seguir(matrizUsuaria[17],   matrizUsuaria[5]);
			IUC.seguir(matrizUsuaria[17],   matrizUsuaria[6]);
			IUC.seguir(matrizUsuaria[17],   matrizUsuaria[8]);
			IUC.seguir(matrizUsuaria[17],   matrizUsuaria[14]);
			IUC.seguir(matrizUsuaria[17],   matrizUsuaria[15]);
			IUC.seguir(matrizUsuaria[17],   matrizUsuaria[16]);
			
			
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
			Set<String> A10cat = new HashSet<>(); A10cat.add("Gimnasia");
			
			// ALTA ACTIVIDAD DEPORTIVA
	        // Aparatos y pesas #A1
			IADC.ingresarDatosActividadDep("Fuerza Bruta",   new DtActividadDeportiva("Aparatos y pesas",   
					"Clases de aparatos,   pesas y calistenia.",   90,   550,   new DtFecha(2021,   3,   31,   0,   0,   0),   A1cat,   TEstado.aceptada,   "viktor"));
			// Voleibol #A2
			IADC.ingresarDatosActividadDep("Telón",   new DtActividadDeportiva("Voleibol",   
					"Voleibol en todas sus formas.",   120,   750,   new DtFecha(2021,   4,   20,   0,   0,   0),   A2cat,   TEstado.aceptada,   "denis"));
			// Aeróbica #A3
			IADC.ingresarDatosActividadDep("Instituto Natural",   new DtActividadDeportiva("Aeróbica",   
					"Para cuidar el aparato cardiovascular.",   110,   800,   new DtFecha(2021,   5,   30,   0,   0,   0),   A3cat,   TEstado.aceptada,   "Administrador"));
			// Kickboxing #A4
			IADC.ingresarDatosActividadDep("Fuerza Bruta",   new DtActividadDeportiva("Kickboxing",   
					"En busca del nuevo campeón de boxeo.",   100,   980,   new DtFecha(2021,   6,   7,   0,   0,   0),   A4cat,   TEstado.aceptada,   "TheBoss"));
			// Atletismo #A5
			IADC.ingresarDatosActividadDep("Telón",   new DtActividadDeportiva("Atletismo",   
					"100m ,   200m,   postas y carreras con obstaculos.",   150,   500,   new DtFecha(2021,   7,   8,   0,   0,   0),   A5cat,   TEstado.aceptada,   "denis"));
			// Basquetbol #A6
			IADC.ingresarDatosActividadDep("Telón",   new DtActividadDeportiva("Basquetbol",   
					"Espectáculo conmemorando los 30 años de Violeta.",   80,   450,   new DtFecha(2021,   7,   31,   0,   0,   0),   A6cat,   TEstado.aceptada,   "Nelson"));
	        // AparatosII #A7
			IADC.ingresarDatosActividadDep("Fuerza Bruta",   new DtActividadDeportiva("Aparatos II",   
					"Clases de aparatos avanzadas.",   60,   1500,   new DtFecha(2021,   8,   15,   0,   0,   0),   A7cat,   TEstado.rechazada,   "Administrador"));
			// Pilates #A8
			IADC.ingresarDatosActividadDep("Instituto Natural",   new DtActividadDeportiva("Pilates",   
					"El Método Pilates combina diferentes capacidades físicas.",   45,   600,   new DtFecha(2021,   8,   30,   0,   0,   0),   A8cat,   TEstado.ingresada,   "clazar"));
			// VoleibolII #A9
			IADC.ingresarDatosActividadDep("Telón",   new DtActividadDeportiva("Voleibol II",   
					"Voleibol avanzado.",   120,   1000,   new DtFecha(2021,   9,   1,   0,   0,   0),   A9cat,   TEstado.rechazada,   "denis"));
			// BasquetbolII #A10
			IADC.ingresarDatosActividadDep("Telón",   new DtActividadDeportiva("Basquetbol II",   
					"Basequetbol avanzado.",   80,   600,   new DtFecha(2021,   9,   7,   0,   0,   0),   A10cat,   TEstado.ingresada,   "denis"));
			
	        // ALTA CLASE
	        // Calistenia #C1
	        IDCC.ingresarDatosClase("Fuerza Bruta",   "Aparatos y pesas",   new DtClase("Calistenia",   "viktor",   "viktor",   
	        		1,   5,   "https://www.musculos.com/Calistenia",   new DtFecha(2021,   4,   15,   15,   30,   0),   new DtFecha(2021,   3,   31,   0,   0,   0)));
	        // Peso libre #C2
	        IDCC.ingresarDatosClase("Fuerza Bruta",   "Aparatos y pesas",   new DtClase("Peso libre",   "viktor",   "viktor",   
	        		1,   5,   "https://www.musculos.com/pesolibre",   new DtFecha(2021,   5,   1,   17,   0,   0),   new DtFecha(2021,   3,   31,   0,   0,   0)));
	        // Aparatos #C3
	        IDCC.ingresarDatosClase("Fuerza Bruta",   "Aparatos y pesas",   new DtClase("Aparatos",   "viktor",   "viktor",   
	        		1,   7,   "https://www.musculos.com/aparatos",   new DtFecha(2021,   6,   1,   18,   0,   0),   new DtFecha(2021,   3,   31,   0,   0,   0)));
	        // Voleibol #C4
	        IDCC.ingresarDatosClase("Telón",   "Voleibol",   new DtClase("Voleibol",   "denis",   "denis",   
	        		10,   21,   "https://telon.com.uy/voley",   new DtFecha(2021,   6,   10,   19,   0,   0),   new DtFecha(2021,   4,   20,   0,   0,   0)));
	        // Braza #C5
	        IDCC.ingresarDatosClase("Telón",   "Voleibol",   new DtClase("Braza",   "Nelson",   "Nelson",   
	        		2,   6,   "https://telon.com.uy/natacionB",   new DtFecha(2021,   7,   10,   20,   0,   0),   new DtFecha(2021,   4,   20,   0,   0,   0)));
	        // Mariposa #C6
	        IDCC.ingresarDatosClase("Telón",   "Voleibol",   new DtClase("Mariposa",   "Nelson",   "Nelson",   
	        		2,   6,   "https://telon.com.uy/natacionM",   new DtFecha(2021,   8,   10,   17,   45,   0),   new DtFecha(2021,   4,   20,   0,   0,   0)));
	        // Aeróbica niños #C7
	        IDCC.ingresarDatosClase("Instituto Natural",   "Aeróbica",   new DtClase("Aeróbica niños",   "clazar",   "clazar",   
	        		5,   10,   "https://www.inatural.com/aeroni",   new DtFecha(2021,   8,   15,   16,   30,   0),   new DtFecha(2021,   5,   30,   0,   0,   0)));
	        // Aeróbico adulto mayor #C8
	        IDCC.ingresarDatosClase("Instituto Natural",   "Aeróbica",   new DtClase("Aeróbico adulto mayor",   "clazar",   "clazar",   
	        		5,   12,   "https://www.inatural.com/aeroam",   new DtFecha(2021,   8,   31,   19,   30,   0),   new DtFecha(2021,   5,   30,   0,   0,   0)));
	        // Aeróbico #C9
	        IDCC.ingresarDatosClase("Instituto Natural",   "Aeróbica",   new DtClase("Aeróbica",   "clazar",   "clazar",   
	        		5,   20,   "https://www.inatural.com/aerogral",   new DtFecha(2021,   9,   30,   20,   0,   0),   new DtFecha(2021,   5,   30,   0,   0,   0)));
	        // Boxeo I #C10
	        IDCC.ingresarDatosClase("Fuerza Bruta",   "Kickboxing",   new DtClase("Boxeo I",   "TheBoss",   "TheBoss",   
	        		1,   4,   "https://www.musculos.com/boxeo1",   new DtFecha(2021,   9,   1,   19,   30,   0),   new DtFecha(2021,   6,   7,   0,   0,   0)));
	        // Boxeo II #C11
	        IDCC.ingresarDatosClase("Fuerza Bruta",   "Kickboxing",   new DtClase("Boxeo II",   "TheBoss",   "TheBoss",   
	        		2,   2,   "https://www.musculos.com/boxeo2",   new DtFecha(2021,   9,   30,   17,   0,   0),   new DtFecha(2021,   6,   7,   0,   0,   0)));
	        // Músculos para boxeo #C12
	        IDCC.ingresarDatosClase("Fuerza Bruta",   "Kickboxing",   new DtClase("Músculos para boxeo",   "viktor",   "viktor",   
	        		1,   5,   "https://www.musculos.com/muscbox",   new DtFecha(2021,   10,   15,   20,   0,   0),   new DtFecha(2021,   6,   7,   0,   0,   0)));
	        // 100 M #C13
	        IDCC.ingresarDatosClase("Telón",   "Atletismo",   new DtClase("100 M",   "lale",   "lale",   
	        		3,   10,   "https://telon.com.uy/100m",   new DtFecha(2021,   9,   25,   19,   0,   0),   new DtFecha(2021,   7,   8,   0,   0,   0)));
	        // 200 M #C14
	        IDCC.ingresarDatosClase("Telón",   "Atletismo",   new DtClase("200 M",   "lale",   "lale",   
	        		3,   10,   "https://telon.com.uy/200m",   new DtFecha(2021,   10,   25,   18,   30,   0),   new DtFecha(2021,   7,   8,   0,   0,   0)));
	        // Posta #C15
	        IDCC.ingresarDatosClase("Telón",   "Atletismo",   new DtClase("Posta",   "lale",   "lale",   
	        		8,   16,   "https://telon.com.uy/posta",   new DtFecha(2021,   11,   25,   17,   45,   0),   new DtFecha(2021,   7,   8,   0,   0,   0)));
	        // Basquet I #C16
	        IDCC.ingresarDatosClase("Telón",   "Basquetbol",   new DtClase("Basquet I",   "aldo",   "aldo",   
	        		10,   15,   "https://telon.com.uy/bball1",   new DtFecha(2021,   9,   1,   21,   0,   0),   new DtFecha(2021,   7,   31,   0,   0,   0)));
	        // Basquet II #C17
	        IDCC.ingresarDatosClase("Telón",   "Basquetbol",   new DtClase("Basquet II",   "aldo",   "aldo",   
	        		10,   10,   "https://telon.com.uy/bball2",   new DtFecha(2021,   10,   1,   21,   0,   0),   new DtFecha(2021,   7,   31,   0,   0,   0)));
	        
	        // CUPONERAS
	        // Pelota #P1
	        ICC.ingresarCuponera("Pelota",   "Deportes con pelota.",   new DtFecha(2021,   5,   1,   0,   0,   0),   new DtFecha(2021,   7,   31,   23,   59,   59),   
	        		20,   new DtFecha(2021,   4,   30,   0,   0,   0), null);
	        ICC.agregarActividadCuponera("Pelota",   "Telón",   "Voleibol",   7);
	        ICC.agregarActividadCuponera("Pelota",   "Telón",   "Basquetbol",   18);
	        // Gimnasia #P2
	        ICC.ingresarCuponera("Gimnasia",   "Aeróbica y aparatos.",   new DtFecha(2021,   8,   1,   0,   0,   0),   new DtFecha(2021,   9,   30,   23,   59,   59),   
	        		30,   new DtFecha(2021,   7,   15,   0,   0,   0), null);
	        ICC.agregarActividadCuponera("Gimnasia",   "Instituto Natural",   "Aeróbica",   2);
	        ICC.agregarActividadCuponera("Gimnasia",   "Fuerza Bruta",   "Aparatos y pesas",   8);
	        // Músculos #P2
	        ICC.ingresarCuponera("Músculos",   "Pesas.",   new DtFecha(2021,   8,   15,   0,   0,   0),   new DtFecha(2021,   11,   15,   23,   59,   59),   
	        		10,   new DtFecha(2021,   8,   1,   0,   0,   0), null);
	        ICC.agregarActividadCuponera("Músculos",   "Fuerza Bruta",   "Kickboxing",   11);
	        ICC.agregarActividadCuponera("Músculos",   "Fuerza Bruta",   "Aparatos y pesas",   12);
	        
	        // COMPRA CUPONERAS
	        IUC.comprarCuponera("Pelota",   "guille",   new DtFecha());
	        IUC.comprarCuponera("Gimnasia",   "m1k4",   new DtFecha());
	        IUC.comprarCuponera("Gimnasia",   "caro",   new DtFecha());
	        IUC.comprarCuponera("Músculos",   "sergiop",   new DtFecha());
	        IUC.comprarCuponera("Músculos",   "andy",   new DtFecha());
	        IUC.comprarCuponera("Pelota",   "Emi71",   new DtFecha());
	        
	        
	        // REGISTRO A CLASE
        	// #R1
        	IDCC.inscribirSocio("Fuerza Bruta",   "Aparatos y pesas",   "Calistenia",   "caro",   TReg.general,   
        			new DtFecha(2021,   4,   9,   0,   0,   0),   null);
        	// #R2
        	IDCC.inscribirSocio("Fuerza Bruta",   "Aparatos y pesas",   "Calistenia",   "sergiop",   TReg.general,   
        			new DtFecha(2021,   4,   10,   0,   0,   0),   null);
        	// #R3
        	IDCC.inscribirSocio("Fuerza Bruta",   "Aparatos y pesas",   "Calistenia",   "andy",   TReg.general,   
        			new DtFecha(2021,   4,   12,   0,   0,   0),   null);
        	// #R4
        	IDCC.inscribirSocio("Fuerza Bruta",   "Aparatos y pesas",   "Peso libre",   "andy",   TReg.general,   
        			new DtFecha(2021,   4,   15,   0,   0,   0),   null);
        	// #R5
        	IDCC.inscribirSocio("Fuerza Bruta",   "Aparatos y pesas",   "Peso libre",   "tonyp",   TReg.general,   
        			new DtFecha(2021,   4,   20,   0,   0,   0),   null);
        	// #R6
        	IDCC.inscribirSocio("Fuerza Bruta",   "Aparatos y pesas",   "Peso libre",   "caro",   TReg.general,   
        			new DtFecha(2021,   4,   25,   0,   0,   0),   null);
        	// #R7
        	IDCC.inscribirSocio("Fuerza Bruta",   "Aparatos y pesas",   "Peso libre",   "m1k4",   TReg.general,   
        			new DtFecha(2021,   4,   28,   0,   0,   0),   null);
        	// #R8
        	IDCC.inscribirSocio("Fuerza Bruta",   "Aparatos y pesas",   "Aparatos",   "charly",   TReg.general,   
        			new DtFecha(2021,   4,   16,   0,   0,   0),   null);
        	// #R9
        	IDCC.inscribirSocio("Fuerza Bruta",   "Aparatos y pesas",   "Aparatos",   "caro",   TReg.general,   
        			new DtFecha(2021,   5,   14,   0,   0,   0),   null);
        	// #R10
        	IDCC.inscribirSocio("Fuerza Bruta",   "Aparatos y pesas",   "Aparatos",   "m1k4",   TReg.general,   
        			new DtFecha(2021,   5,   20,   0,   0,   0),   null);
        	// #R11
        	IDCC.inscribirSocio("Telón",   "Voleibol",   "Voleibol",   "Emi71",   TReg.general,   
        			new DtFecha(2021,   5,   5,   0,   0,   0),   null);
        	// #R12
        	IDCC.inscribirSocio("Telón",   "Voleibol",   "Voleibol",   "euge",   TReg.general,   
        			new DtFecha(2021,   5,   10,   0,   0,   0),   null);
        	// #R13
        	IDCC.inscribirSocio("Telón",   "Voleibol",   "Voleibol",   "sergiop",   TReg.general,   
        			new DtFecha(2021,   5,   15,   0,   0,   0),   null);
			// #R14
			IDCC.inscribirSocio("Telón",   "Voleibol",   "Voleibol",   "tonyp",   TReg.general,   
					new DtFecha(2021,   5,   20,   0,   0,   0),   null);
			// #R15
			IDCC.inscribirSocio("Telón",   "Voleibol",   "Braza",   "guille",   TReg.general,   
					new DtFecha(2021,   6,   8,   0,   0,   0),   null);
			// #R16
			IDCC.inscribirSocio("Telón",   "Voleibol",   "Braza",   "euge",   TReg.general,   
					new DtFecha(2021,   6,   13,   0,   0,   0),   null);
			// #R17
			IDCC.inscribirSocio("Telón",   "Voleibol",   "Braza",   "m1k4",   TReg.general,   
					new DtFecha(2021,   6,   25,   0,   0,   0),   null);
			// #R18
			IDCC.inscribirSocio("Telón",   "Voleibol",   "Mariposa",   "charly",   TReg.general,   
					new DtFecha(2021,   7,   5,   0,   0,   0),   null);
			// #R19
			IDCC.inscribirSocio("Telón",   "Voleibol",   "Mariposa",   "sergiop",   TReg.general,   
					new DtFecha(2021,   7,   11,   0,   0,   0),   null);
			// #R20
			IDCC.inscribirSocio("Telón",   "Voleibol",   "Mariposa",   "andy",   TReg.general,   
					new DtFecha(2021,   7,   18,   0,   0,   0),   null);
			// #R21
			IDCC.inscribirSocio("Instituto Natural",   "Aeróbica",   "Aeróbica niños",   "m1k4",   TReg.cuponera,   
					new DtFecha(2021,   7,   19,   0,   0,   0),   "Gimnasia");
			// #R22
			IDCC.inscribirSocio("Instituto Natural",   "Aeróbica",   "Aeróbico adulto mayor",   "Emi71",   TReg.general,   
					new DtFecha(2021,   8,   17,   0,   0,   0),   null);
			// #R23
			IDCC.inscribirSocio("Instituto Natural",   "Aeróbica",   "Aeróbico adulto mayor",   "guille",   TReg.general,   
					new DtFecha(2021,   8,   20,   0,   0,   0),   null);
			// #R24
			IDCC.inscribirSocio("Instituto Natural",   "Aeróbica",   "Aeróbico adulto mayor",   "andy",   TReg.general,   
					new DtFecha(2021,   8,   23,   0,   0,   0),   null);
			// #R25
			IDCC.inscribirSocio("Instituto Natural",   "Aeróbica",   "Aeróbica",   "caro",   TReg.cuponera,   
					new DtFecha(2021,   8,   15,   0,   0,   0),   "Gimnasia"); // R25 C9 CO 15/08/21 560
			// #R26
			IDCC.inscribirSocio("Instituto Natural",   "Aeróbica",   "Aeróbica",   "euge",   TReg.general,   
					new DtFecha(2021,   8,   26,   0,   0,   0),   null);
			// #R27
			IDCC.inscribirSocio("Fuerza Bruta",   "Kickboxing",   "Boxeo I",   "andy",   TReg.cuponera,   
					new DtFecha(2021,   7,   19,   0,   0,   0),   "Músculos");
			// #R28
			IDCC.inscribirSocio("Fuerza Bruta",   "Kickboxing",   "Boxeo I",   "tonyp",   TReg.general,   
					new DtFecha(2021,   8,   16,   0,   0,   0),   null);
			// #R29
			IDCC.inscribirSocio("Fuerza Bruta",   "Kickboxing",   "Boxeo I",   "m1k4",   TReg.general,   
					new DtFecha(2021,   8,   24,   0,   0,   0),   null);
			// #R30
			IDCC.inscribirSocio("Fuerza Bruta",   "Kickboxing",   "Boxeo II",   "sergiop",   TReg.cuponera,   
					new DtFecha(2021,   8,   1,   0,   0,   0),   "Músculos");
			// #R31
			IDCC.inscribirSocio("Fuerza Bruta",   "Kickboxing",   "Boxeo II",   "guille",   TReg.general,   
					new DtFecha(2021,   8,   30,   0,   0,   0),   null);
			// #R32
			IDCC.inscribirSocio("Fuerza Bruta",   "Kickboxing",   "Músculos para boxeo",   "Emi71",   TReg.general,   
					new DtFecha(2021,   8,   16,   0,   0,   0),   null);
			// #R33
			IDCC.inscribirSocio("Fuerza Bruta",   "Kickboxing",   "Músculos para boxeo",   "caro",   TReg.general,   
					new DtFecha(2021,   8,   16,   0,   0,   0),   null);
			// #R34
			IDCC.inscribirSocio("Fuerza Bruta",   "Kickboxing",   "Músculos para boxeo",   "euge",   TReg.general,   
					new DtFecha(2021,   9,   1,   0,   0,   0),   null);
			// #R35
			IDCC.inscribirSocio("Fuerza Bruta",   "Kickboxing",   "Músculos para boxeo",   "sergiop",   TReg.general,   
					new DtFecha(2021,   9,   5,   0,   0,   0),   null);
			// #R36
			IDCC.inscribirSocio("Telón",   "Atletismo",   "100 M",   "guille",   TReg.general,   
					new DtFecha(2021,   8,   16,   0,   0,   0),   null);
			// #R37
			IDCC.inscribirSocio("Telón",   "Atletismo",   "100 M",   "charly",   TReg.general,   
					new DtFecha(2021,   9,   3,   0,   0,   0),   null);
			// #R38
			IDCC.inscribirSocio("Telón",   "Atletismo",   "200 M",   "Emi71",   TReg.general,   
					new DtFecha(2021,   8,   16,   0,   0,   0),   null);
			// #R39
			IDCC.inscribirSocio("Telón",   "Atletismo",   "200 M",   "charly",   TReg.general,   
					new DtFecha(2021,   9,   6,   0,   0,   0),   null);
			// #R40
			IDCC.inscribirSocio("Telón",   "Atletismo",   "Posta",   "caro",   TReg.general,   
					new DtFecha(2021,   9,   1,   0,   0,   0),   null);
			// #R41
			IDCC.inscribirSocio("Telón",   "Basquetbol",   "Basquet I",   "sergiop",   TReg.general,   
					new DtFecha(2021,   8,   16,   0,   0,   0),   null);
			// #R42
			IDCC.inscribirSocio("Telón",   "Basquetbol",   "Basquet I",   "Emi71",   TReg.general,   
					new DtFecha(2021,   8,   20,   0,   0,   0),   null);
			// #R43
			IDCC.inscribirSocio("Telón",   "Basquetbol",   "Basquet I",   "tonyp",   TReg.general,   
					new DtFecha(2021,   8,   31,   0,   0,   0),   null);
			// #R44
			IDCC.inscribirSocio("Telón",   "Basquetbol",   "Basquet II",   "andy",   TReg.general,   
					new DtFecha(2021,   8,   16,   0,   0,   0),   null);
			// #R45
			IDCC.inscribirSocio("Telón",   "Basquetbol",   "Basquet II",   "tonyp",   TReg.general,   
					new DtFecha(2021,   8,   20,   0,   0,   0),   null);
			// #R46
			IDCC.inscribirSocio("Telón",   "Basquetbol",   "Basquet II",   "caro",   TReg.general,   
					new DtFecha(2021,   9,   2,   0,   0,   0),   null);
		} catch (CuponeraInmutableException e) {
			fail(e.getMessage());
        	e.printStackTrace();
		} catch (CategoriaException e) {
			fail(e.getMessage());
        	e.printStackTrace();
		} catch (CuponeraNoExisteException e) {
        	fail(e.getMessage());
			e.printStackTrace();
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
			assertEquals(data.getNombre(),   "Instituto Natural");
			assertEquals(data.getDescripcion(),   "Clases de gimnasia,   aeróbica,   spinning y yoga.");
			assertEquals(data.getURL(),   "https://www.inatural.com");
			// Fuerza Bruta #FB
			data = IADC.obtenerDatosInstitucion("Fuerza Bruta");
			assertEquals(data.getNombre(),   "Fuerza Bruta");
			assertEquals(data.getDescripcion(),   "Gimnasio especializado en el desarrollo de la musculatura.");
			assertEquals(data.getURL(),   "https://www.musculos.com");
			// Telón #TL
			data = IADC.obtenerDatosInstitucion("Telón");
			assertEquals(data.getNombre(),   "Telón");
			assertEquals(data.getDescripcion(),   "Actividades deportivas para todas las edades.");
			assertEquals(data.getURL(),   "https://telon.com.uy");
			// Olympic #YT
			data = IADC.obtenerDatosInstitucion("Olympic");
			assertEquals(data.getNombre(),   "Olympic");
			assertEquals(data.getDescripcion(),   "Gimnasia y Aparatos.");
			assertEquals(data.getURL(),   "https://www.olympic21.com");
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
			int exitoEnAlta = IADC.altaInstitucion("Instituto Nuevo",   "Instituto que es muy nuevo.",   "https://www.nuevo.com");
			assertEquals(exitoEnAlta,   0);
			DtInstitucion data = IADC.obtenerDatosInstitucion("Instituto Nuevo");
			assertEquals(data.getNombre(),   "Instituto Nuevo");
			assertEquals(data.getDescripcion(),   "Instituto que es muy nuevo.");
			assertEquals(data.getURL(),   "https://www.nuevo.com");
			assertEquals(IUC.obtenerInstituciones().contains("Instituto Nuevo"),   true);
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testAltaInstitucionRepetida() {
		// Generamos una Institucion Nueva
		IADC.altaInstitucion("Instituto Repetido",   "https://www.repetido.com",   "Instituto que se repite en el sistema.");
		// 0 == operacionExitosa; 1 si no
		int operacionExito = IADC.altaInstitucion("Instituto Repetido",   "https://www.nuevarepetida.com",   "Es repetida.");
		assertEquals(operacionExito,   1);
	}

	@Test
	void testCargaSociosOk() {
		try {
			// Emi71 #EL
			DtUsuario socio = IUC.seleccionarUsuario("Emi71");
			assertEquals(socio.getNickname(),   "Emi71");
			assertEquals(socio.getNombre(),   "Emiliano");
			assertEquals(socio.getApellido(),   "Lucas");
			assertEquals(socio.getEmail(),   "emi71@gmail.com");
			DtFecha fecha = new DtFecha(1971,   12,   31,   0,   0,   0);
			assertEquals(socio.getFechaNacimiento().toFecha(),   fecha.toFecha());
			// caro #CO
			socio = IUC.seleccionarUsuario("caro");
			assertEquals(socio.getNickname(),   "caro");
			assertEquals(socio.getNombre(),   "Carolina");
			assertEquals(socio.getApellido(),   "Omega");
			assertEquals(socio.getEmail(),   "caro@gmail.com");
			fecha = new DtFecha(1983,   11,   15,   0,   0,   0);
			assertEquals(socio.getFechaNacimiento().toFecha(),   fecha.toFecha());
			// euge #EW
			socio = IUC.seleccionarUsuario("euge");
			assertEquals(socio.getNickname(),   "euge");
			assertEquals(socio.getApellido(),   "Williams");
			assertEquals(socio.getEmail(),   "e.will@gmail.com");
			fecha = new DtFecha(1990,   4,   15,   0,   0,   0);
			assertEquals(socio.getFechaNacimiento().toFecha(),   fecha.toFecha());
			// guille #GH
			socio = IUC.seleccionarUsuario("guille");
			assertEquals(socio.getNickname(),   "guille");
			assertEquals(socio.getNombre(),   "Guillermo");
			assertEquals(socio.getApellido(),   "Hector");
			assertEquals(socio.getEmail(),   "ghector@gmail.com");
			fecha = new DtFecha(1959,   5,   15,   0,   0,   0);
			assertEquals(socio.getFechaNacimiento().toFecha(),   fecha.toFecha());
			// sergiop #SP
			socio = IUC.seleccionarUsuario("sergiop");
			assertEquals(socio.getNickname(),   "sergiop");
			assertEquals(socio.getNombre(),   "Sergio");
			assertEquals(socio.getApellido(),   "Perez");
			assertEquals(socio.getEmail(),   "sergi@gmail.com.uy");
			fecha = new DtFecha(1950,   1,   28,   0,   0,   0);
			assertEquals(socio.getFechaNacimiento().toFecha(),   fecha.toFecha());
			// andy #AR
			socio = IUC.seleccionarUsuario("andy");
			assertEquals(socio.getNickname(),   "andy");
			assertEquals(socio.getNombre(),   "Andrés");
			assertEquals(socio.getApellido(),   "Roman");
			assertEquals(socio.getEmail(),   "chino@gmail.org.uy");
			fecha = new DtFecha(1976,   3,   17,   0,   0,   0);
			assertEquals(socio.getFechaNacimiento().toFecha(),   fecha.toFecha());
			// tonyp #AP
			socio = IUC.seleccionarUsuario("tonyp");
			assertEquals(socio.getNickname(),   "tonyp");
			assertEquals(socio.getNombre(),   "Antonio");
			assertEquals(socio.getApellido(),   "Paz");
			assertEquals(socio.getEmail(),   "eltony@gmail.org.uy");
			fecha = new DtFecha(1955,   2,   14,   0,   0,   0);
			assertEquals(socio.getFechaNacimiento().toFecha(),   fecha.toFecha());
			// m1k4 #ML
			socio = IUC.seleccionarUsuario("m1k4");
			assertEquals(socio.getNickname(),   "m1k4");
			assertEquals(socio.getNombre(),   "Micaela");
			assertEquals(socio.getApellido(),   "Lopez");
			assertEquals(socio.getEmail(),   "mika@gmail.com.ar");
			fecha = new DtFecha(1987,   2,   23,   0,   0,   0);
			assertEquals(socio.getFechaNacimiento().toFecha(),   fecha.toFecha());
			// charly #CB
			socio = IUC.seleccionarUsuario("charly");
			assertEquals(socio.getNickname(),   "charly");
			assertEquals(socio.getNombre(),   "Carlos");
			assertEquals(socio.getApellido(),   "Boston");
			assertEquals(socio.getEmail(),   "charly@gmail.com.uy");
			fecha = new DtFecha(1937,   5,   8,   0,   0,   0);
			assertEquals(socio.getFechaNacimiento().toFecha(),   fecha.toFecha());
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
			int exitoEnAlta = IUC.ingresarDatosUsuario(new DtSocio("Newuser",   "Nuevo",   "Usuario",   "new@user.com",   
					"epstein",   new DtFecha(2020,   1,   1,   0,   0,   0),   null));
			assertEquals(exitoEnAlta,   0);
			DtUsuario socio = IUC.seleccionarUsuario("Newuser");
			assertEquals(socio.getNickname(),   "Newuser");
			assertEquals(socio.getNombre(),   "Nuevo");
			assertEquals(socio.getApellido(),   "Usuario");
			assertEquals(socio.getEmail(),   "new@user.com");
			DtFecha fecha = new DtFecha(2020,   1,   1,   0,   0,   0);
			assertEquals(socio.getFechaNacimiento().toFecha(),   fecha.toFecha());
			assertEquals(IADC.obtenerSocios().contains("Newuser"),   true);
			assertEquals(IUC.obtenerUsuarios().contains("Newuser"),   true);
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
			IUC.ingresarDatosUsuario(new DtSocio("userRepetido",   "Repe",   "Tipo",   "repe@tido.com",   "cringe",   new DtFecha(2020,   1,   1,   0,   0,   0),   null));
			// 0 == operacionExitosa; 1 si no
			int operacionExito = IUC.ingresarDatosUsuario(new DtSocio("userRepetido",   "Guido",   "Kazka",   "mostrame@laRepe.com",   
					"seethe",   new DtFecha(1980,   1,   1,   0,   0,   0),   null));
			assertEquals(operacionExito,   1);
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testEditarDatosSocio() {
		try {
			// Generamos un Socio Nuevo			
			// Retorna 0 si se da de alta de manera exitosa; 1 en otro caso;
			int exitoEnAlta = IUC.ingresarDatosUsuario(new DtSocio("UserAEditar",   "Nuevo",   "Usuario",   "newEditado@user.com",   
					"seethe",   new DtFecha(2020,   1,   1,   0,   0,   0),   null));
			assertEquals(exitoEnAlta,   0);
			DtSocio editado = new DtSocio("UserAEditar",   "Editado",   "XD",   "newEditado@user.com",   "seethe",   new DtFecha(2018,   11,   15,   0,   0,   0),   null);
			IUC.editarDatosBasicos("UserAEditar",   editado);
			DtUsuario socio = IUC.seleccionarUsuario("UserAEditar");
			assertEquals(socio.getNickname(),   "UserAEditar");
			assertEquals(socio.getNombre(),   "Editado");
			assertEquals(socio.getApellido(),   "XD");
			assertEquals(socio.getEmail(),   "newEditado@user.com");
			DtFecha fecha = new DtFecha(2018,   11,   15,   0,   0,   0);
			assertEquals(socio.getFechaNacimiento().toFecha(),   fecha.toFecha());
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testSocioCorreoRepetido() {
		try {
			// Generamos un Socio Nuevo
			IUC.ingresarDatosUsuario(new DtSocio("userRepetido",   "Repe",   "Tipo",   "mostrame@laRepe.com",   "seethe",   new DtFecha(2020,   1,   1,   0,   0,   0),   null));
			// 0 == operacionExitosa; 1 si no
			int operacionExito = IUC.ingresarDatosUsuario(new DtSocio("guido",   "Guido",   "Kazka",   "mostrame@laRepe.com",   
					"seethe",   new DtFecha(1980,   1,   1,   0,   0,   0),   null));
			assertEquals(operacionExito,   1);
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
			DtProfesorExt profe = (DtProfesorExt) IUC.seleccionarUsuario("viktor");
			DtFecha fecha = new DtFecha(1997,   1,   1,   0,   0,   0);
			assertEquals(profe.getNickname(),   "viktor");
			assertEquals(profe.getNombre(),   "Victor");
			assertEquals(profe.getApellido(),   "Perez");
			assertEquals(profe.getEmail(),   "vperez@fuerza.com");
			assertEquals(profe.getFechaNacimiento().toFecha(),   fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(),   "Fuerza Bruta");
			assertEquals(profe.getDescripcion(),   desc);
			assertEquals(profe.getBiografia(),   bio);
			assertEquals(profe.getLink(),   "www.vikgym.com");
			// denis #DM
			desc = "A Denis le interesan los deportes con pelota,   principalmente el voleibol y el handball.";
			bio = "Denis fue un jugador de voleibol profesional.";
			profe = (DtProfesorExt) IUC.seleccionarUsuario("denis");
			fecha = new DtFecha(1980,   6,   14,   0,   0,   0);
			assertEquals(profe.getNickname(),   "denis");
			assertEquals(profe.getNombre(),   "Denis");
			assertEquals(profe.getApellido(),   "Miguel");
			assertEquals(profe.getEmail(),   "den80@fuerza.com");
			assertEquals(profe.getFechaNacimiento().toFecha(),   fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(),   "Telón");
			assertEquals(profe.getDescripcion(),   desc);
			assertEquals(profe.getBiografia(),   bio);
			assertEquals(profe.getLink(),   "www.depecho.com");
			// clazar #CL
			desc = "Carlos es un profesor muy divertido cuyas clases de aeróbica están cargadas de energía.";
			bio = "El interés por la actividad física llevo a Carlos a dejar su trabajo en un estudio "
					+ "contable y abrir su propio gimnasio.";
			profe = (DtProfesorExt) IUC.seleccionarUsuario("clazar");
			fecha = new DtFecha(1953,   6,   22,   0,   0,   0);
			assertEquals(profe.getNickname(),   "clazar");
			assertEquals(profe.getNombre(),   "Carlos");
			assertEquals(profe.getApellido(),   "Lazaro");
			assertEquals(profe.getEmail(),   "claz4r0@hotmail.com");
			assertEquals(profe.getFechaNacimiento().toFecha(),   fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(),   "Instituto Natural");
			assertEquals(profe.getDescripcion(),   desc);
			assertEquals(profe.getBiografia(),   bio);
			assertEquals(profe.getLink(),   "www.enforma.com");
			// TheBoss #BS
			desc = "Bruno es un ex-boxeardor que busca entrenar a futuros campeones.";
			bio = "Bruno,   mejor conocido como Bruce en el ring,   compitió como boxeador entre los años 60s y 70s.";
			profe = (DtProfesorExt) IUC.seleccionarUsuario("TheBoss");
			fecha = new DtFecha(1949,   9,   23,   0,   0,   0);
			assertEquals(profe.getNickname(),   "TheBoss");
			assertEquals(profe.getNombre(),   "Bruno");
			assertEquals(profe.getApellido(),   "Sosa");
			assertEquals(profe.getEmail(),   "bruceTheBoss@gmail.com");
			assertEquals(profe.getFechaNacimiento().toFecha(),   fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(),   "Fuerza Bruta");
			assertEquals(profe.getDescripcion(),   desc);
			assertEquals(profe.getBiografia(),   bio);
			assertEquals(profe.getLink(),   "www.bruce.net");
			// Nelson #TN
			desc = "Profesor de natación. Especializado en braza y mariposa.";
			bio = "";
			profe = (DtProfesorExt) IUC.seleccionarUsuario("Nelson");
			fecha = new DtFecha(1998,   1,   1,   0,   0,   0);
			assertEquals(profe.getNickname(),   "Nelson");
			assertEquals(profe.getNombre(),   "Luis");
			assertEquals(profe.getApellido(),   "Nelson");
			assertEquals(profe.getEmail(),   "nelson@hotmail.com");
			assertEquals(profe.getFechaNacimiento().toFecha(),   fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(),   "Telón");
			assertEquals(profe.getDescripcion(),   desc);
			assertEquals(profe.getBiografia(),   bio);
			assertEquals(profe.getLink(),   "www.nelson.uy");
			// lale #LL
			desc = "Luego de una exitosa carrera como jugadora de futbol profesional. Laura dedica sus clases a "
					+ "enseñar tácticas de futbol.";
			bio = "Jugadora profesional de futbol desde 2010 a 2020.";
			profe = (DtProfesorExt) IUC.seleccionarUsuario("lale");
			fecha = new DtFecha(1987,   2,   14,   0,   0,   0);
			assertEquals(profe.getNickname(),   "lale");
			assertEquals(profe.getNombre(),   "Laura");
			assertEquals(profe.getApellido(),   "Leyes");
			assertEquals(profe.getEmail(),   "la_le@outlook.com");
			assertEquals(profe.getFechaNacimiento().toFecha(),   fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(),   "Telón");
			assertEquals(profe.getDescripcion(),   desc);
			assertEquals(profe.getBiografia(),   bio);
			assertEquals(profe.getLink(),   "www.laley.com");
			// prisc #PI
			desc = "Laura tiene un gran interés por los deportes olímpicos.";
			bio = "";
			profe = (DtProfesorExt) IUC.seleccionarUsuario("prisc");
			fecha =  new DtFecha(1981,   8,   13,   0,   0,   0);
			assertEquals(profe.getNickname(),   "prisc");
			assertEquals(profe.getNombre(),   "Priscila");
			assertEquals(profe.getApellido(),   "Pappo");
			assertEquals(profe.getEmail(),   "pripa@gmail.com");
			assertEquals(profe.getFechaNacimiento().toFecha(),   fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(),   "Olympic");
			assertEquals(profe.getDescripcion(),   desc);
			assertEquals(profe.getBiografia(),   bio);
			assertEquals(profe.getLink(),   "www.pi314.net");
			// dagost #DY
			desc = "Profesora dedicada y exigente. No acepta un " + '"' + "no puedo" + '"' + " como respuesta.";
			bio = "";
			profe = (DtProfesorExt) IUC.seleccionarUsuario("dagost");
			fecha =  new DtFecha(1940,   3,   5,   0,   0,   0);
			assertEquals(profe.getNickname(),   "dagost");
			assertEquals(profe.getNombre(),   "Daiana");
			assertEquals(profe.getApellido(),   "Agostini");
			assertEquals(profe.getEmail(),   "d_1940_ago@gmail.com");
			assertEquals(profe.getFechaNacimiento().toFecha(),   fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(),   "Olympic");
			assertEquals(profe.getDescripcion(),   desc);
			assertEquals(profe.getBiografia(),   bio);
			assertEquals(profe.getLink(),   "www.dygym.com");
			// aldo #AL
			desc = "Dada su gran estatura Aldo siempre jugó al basquetbol,   hoy se dedica a enseñarlo.";
			bio = "";
			profe = (DtProfesorExt) IUC.seleccionarUsuario("aldo");
			fecha =  new DtFecha(1952,   7,   17,   0,   0,   0);
			assertEquals(profe.getNickname(),   "aldo");
			assertEquals(profe.getNombre(),   "Aldo");
			assertEquals(profe.getApellido(),   "Vivaldi");
			assertEquals(profe.getEmail(),   "aldo@outlook.com");
			assertEquals(profe.getFechaNacimiento().toFecha(),   fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(),   "Telón");
			assertEquals(profe.getDescripcion(),   desc);
			assertEquals(profe.getBiografia(),   bio);
			assertEquals(profe.getLink(),   "www.sportsaldo.net");
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testAltaProfesorOk() {
		try {
			IADC.altaInstitucion("Instituto Muy Nuevo",   "https://www.nuevoInsti.com",   "Sirve como test.");
			// Generamos un Profesor Nuevo		
			// Retorna 0 si se da de alta de manera exitosa; 1 en otro caso;
			DtFecha fecha =  new DtFecha(1980,   1,   1,   0,   0,   0);
			int operacionExito = IUC.ingresarDatosUsuario(new DtProfesor("newprofe",   "Nuevo",   "Profe",   "new@profe.com",   
					"seethe",   fecha,   "Instituto Nuevo",   "Descripcion",   "Bio",   "www.newProfe.com",   null));
			assertEquals(operacionExito,   0);
			DtProfesorExt profe = (DtProfesorExt) IUC.seleccionarUsuario("newprofe");
			assertEquals(profe.getNickname(),   "newprofe");
			assertEquals(profe.getNombre(),   "Nuevo");
			assertEquals(profe.getApellido(),   "Profe");
			assertEquals(profe.getEmail(),   "new@profe.com");
			assertEquals(profe.getFechaNacimiento().toFecha(),   fecha.toFecha());
			assertEquals(profe.getNombreInstitucion(),   "Instituto Nuevo");
			assertEquals(profe.getDescripcion(),   "Descripcion");
			assertEquals(profe.getBiografia(),   "Bio");
			assertEquals(profe.getLink(),   "www.newProfe.com");
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testEditarDatosProfe() {
		try {
			IADC.altaInstitucion("InstitutoEditado",   "https://www.nuevoInsti.com",   "Sirve como test.");
			// Generamos un Socio Nuevo			
			// Retorna 0 si se da de alta de manera exitosa; 1 en otro caso;
			int exitoEnAlta = IUC.ingresarDatosUsuario(new DtProfesor("newEDITADOprofe",   "Nuevo",   "Profe",   "newEDITADO@profe.com",   
					"seethe",   new DtFecha(2020,   1,   1,   0,   0,   0),   "InstitutoEditado",   "Descripcion",   "Bio",   "www.newProfe.com",   null));
			assertEquals(exitoEnAlta,   0);
			DtProfesor editado = new DtProfesor("newEDITADOprofe",   "Edicion",   "Lol",   "newEDITADO@profe.com",   
					"seethe",   new DtFecha(2019,   1,   1,   0,   0,   0),   "InstitutoEditado",   "Desc Edit",   "Bio Edit",   "www.Editado.com",   null);
			IUC.editarDatosBasicos("newEDITADOprofe",   editado);
			DtProfesorExt profe = (DtProfesorExt) IUC.seleccionarUsuario("newEDITADOprofe");
			assertEquals(profe.getNickname(),   "newEDITADOprofe");
			assertEquals(profe.getNombre(),   "Edicion");
			assertEquals(profe.getApellido(),   "Lol");
			assertEquals(profe.getEmail(),   "newEDITADO@profe.com");
			assertEquals(profe.getFechaNacimiento().toFecha(),   new DtFecha(2019,   1,   1,   0,   0,   0).toFecha());
			assertEquals(profe.getNombreInstitucion(),   "InstitutoEditado");
			assertEquals(profe.getDescripcion(),   "Desc Edit");
			assertEquals(profe.getBiografia(),   "Bio Edit");
			assertEquals(profe.getLink(),   "www.Editado.com");
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
		DtFecha fecha = new DtFecha(1980,   1,   1,   0,   0,   0);
		Assertions.assertThrows(InstitucionException.class,   () -> {
				IUC.ingresarDatosUsuario(new DtProfesor("theInstitutoLess",  
				"Profe",   "SinInstituto",   "profe@sinInstituto.com",   "seethe",   fecha,   "Instituto Inexistente",   "Descripcion",   "Bio",   
				"www.noInstituto.com",   null)); });
	}
	
	@Test
	void testCargaActividadesDeportivaOk() {
		try {
			// Aparatos y pesas #A1
			DtActividadDeportiva actividad = IADC.getActDepExt("Fuerza Bruta",   "Aparatos y pesas");
			DtFecha fecha = new DtFecha(2021,   3,   31,   0,   0,   0);
			assertEquals(actividad.getNombre(),   "Aparatos y pesas");
			assertEquals(actividad.getDescripcion(),   "Clases de aparatos,   pesas y calistenia.");
			assertEquals(actividad.getDuracionMinutos(),   90);
			assertEquals(actividad.getCosto(),   550);
			assertEquals(actividad.getFechaRegistro().toFecha(),   fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Fuerza Bruta").contains("Aparatos y pesas"),   true);
			// Voleibol #A2
			actividad = IADC.getActDepExt("Telón",   "Voleibol");
			fecha = new DtFecha(2021,   4,   20,   0,   0,   0);
			assertEquals(actividad.getNombre(),   "Voleibol");
			assertEquals(actividad.getDescripcion(),   "Voleibol en todas sus formas.");
			assertEquals(actividad.getDuracionMinutos(),   120);
			assertEquals(actividad.getCosto(),   750);
			assertEquals(actividad.getFechaRegistro().toFecha(),   fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Telón").contains("Voleibol"),   true);
			// Aeróbica #A3
			actividad = IADC.getActDepExt("Instituto Natural",   "Aeróbica");
			fecha = new DtFecha(2021,   5,   30,   0,   0,   0);
			assertEquals(actividad.getNombre(),   "Aeróbica");
			assertEquals(actividad.getDescripcion(),   "Para cuidar el aparato cardiovascular.");
			assertEquals(actividad.getDuracionMinutos(),   110);
			assertEquals(actividad.getCosto(),   800);
			assertEquals(actividad.getFechaRegistro().toFecha(),   fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Instituto Natural").contains("Aeróbica"),   true);
			// Kickboxing #A4
			actividad = IADC.getActDepExt("Fuerza Bruta",   "Kickboxing");
			fecha = new DtFecha(2021,   6,   7,   0,   0,   0);
			assertEquals(actividad.getNombre(),   "Kickboxing");
			assertEquals(actividad.getDescripcion(),   "En busca del nuevo campeón de boxeo.");
			assertEquals(actividad.getDuracionMinutos(),   100);
			assertEquals(actividad.getCosto(),   980);
			assertEquals(actividad.getFechaRegistro().toFecha(),   fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Fuerza Bruta").contains("Kickboxing"),   true);
			// Atletismo #A5
			actividad = IADC.getActDepExt("Telón",   "Atletismo");
			fecha = new DtFecha(2021,   7,   8,   0,   0,   0);
			assertEquals(actividad.getNombre(),   "Atletismo");
			assertEquals(actividad.getDescripcion(),   "100m ,   200m,   postas y carreras con obstaculos.");
			assertEquals(actividad.getDuracionMinutos(),   150);
			assertEquals(actividad.getCosto(),   500);
			assertEquals(actividad.getFechaRegistro().toFecha(),   fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Telón").contains("Atletismo"),   true);
			// Basquetbol #A6
			actividad = IADC.getActDepExt("Telón",   "Basquetbol");
			fecha = new DtFecha(2021,   7,   31,   0,   0,   0);
			assertEquals(actividad.getNombre(),   "Basquetbol");
			assertEquals(actividad.getDescripcion(),   "Espectáculo conmemorando los 30 años de Violeta.");
			assertEquals(actividad.getDuracionMinutos(),   80);
			assertEquals(actividad.getCosto(),   450);
			assertEquals(actividad.getFechaRegistro().toFecha(),   fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Telón").contains("Basquetbol"),   true);					
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
			IADC.altaInstitucion("Instituto Muy Nuevo",   "https://www.nuevoInsti.com",   "Sirve como test.");
			DtFecha fecha = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva nuevaActividad = new DtActividadDeportiva("NuevaActividad",   "Desc",   1,   10,   fecha,   null,   TEstado.aceptada,   "Administrador");
			// Devuelve true si se ingresa con exito la Actividad Deportiva.
			assertEquals(IADC.ingresarDatosActividadDep("Instituto Muy Nuevo",   nuevaActividad),   true);
			DtActividadDeportiva actividad = IADC.getActDepExt("Instituto Muy Nuevo",   "NuevaActividad");
			assertEquals(actividad.getNombre(),   nuevaActividad.getNombre());
			assertEquals(actividad.getDescripcion(),   nuevaActividad.getDescripcion());
			assertEquals(actividad.getDuracionMinutos(),   nuevaActividad.getDuracionMinutos());
			assertEquals(actividad.getCosto(),   nuevaActividad.getCosto());
			assertEquals(actividad.getFechaRegistro().toFecha(),   fecha.toFecha());
			assertEquals(IADC.obtenerActividades("Instituto Muy Nuevo").contains(nuevaActividad.getNombre()),   true);
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
			IADC.altaInstitucion("Instituto Muy Nuevo",   "https://www.nuevoInsti.com",   "Sirve como test.");
			DtFecha fecha = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadRepetida = new DtActividadDeportiva("ActividadARepetir",   "Desc",   1,   10,   fecha,   null,   TEstado.aceptada,   "Administrador");
			// Devuelve true si se ingresa con exito la Actividad Deportiva.
			assertEquals(IADC.ingresarDatosActividadDep("Instituto Muy Nuevo",   actividadRepetida),   true);
			DtActividadDeportiva actividadRepetida2 = new DtActividadDeportiva("ActividadARepetir",   "OtraDesc",   2,   30,   fecha,   null,   TEstado.aceptada,   "Administrador");
			Assertions.assertThrows(ActividadDeportivaException.class,   () -> {
					IADC.ingresarDatosActividadDep("Instituto Muy Nuevo",   actividadRepetida2); });
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
		DtFecha fecha = new DtFecha(2020,   1,   1,   0,   0,   0);
		DtActividadDeportiva nuevaActividad = new DtActividadDeportiva("ActividadSinInsti",   "Desc",   1,   10,   fecha,   null,   TEstado.aceptada,   "Administrador");
		// Devuelve true si se ingresa con exito la Actividad Deportiva.
		Assertions.assertThrows(InstitucionException.class,   () -> {
			IADC.ingresarDatosActividadDep("InstitutoInexistente",   nuevaActividad); });		
	}
	@Test
	void testIUCControl() {
		assertEquals(IDCC.obtenerUsuarios().contains("viktor"),   true);
		assertEquals(IDCC.obtenerInstituciones().contains("Telón"),   true);
		try {
			assertEquals(IDCC.obtenerActividades("Fuerza Bruta").contains("Kickboxing"),   true);
			assertEquals(IDCC.obtenerProfesores("Fuerza Bruta").contains("viktor"),   true);
		} catch (InstitucionException e) {
			e.printStackTrace();
		}
	}
	@Test
	void testCargaDeClases() {
		try {
			// Calistenia #C1
			DtClase claseOk = new DtClase("Calistenia",   "viktor",   "viktor",   1,   5,   "https://www.musculos.com/Calistenia",   
					new DtFecha(2021,   4,   15,   15,   30,   0),   new DtFecha(2021,   3,   31,   0,   0,   0));
			DtClaseExt claseIngresada = IDCC.seleccionarClase("Fuerza Bruta",   "Aparatos y pesas",   "Calistenia");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
			// Peso libre #C2
	        claseOk = new DtClase("Peso libre",   "viktor",   "viktor",   1,   5,   "https://www.musculos.com/pesolibre",   
	        		new DtFecha(2021,   5,   1,   17,   0,   0),   new DtFecha(2021,   3,   31,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Fuerza Bruta",   "Aparatos y pesas",   "Peso libre");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
	        // Aparatos #C3
	        claseOk = new DtClase("Aparatos",   "viktor",   "viktor",   1,   7,   "https://www.musculos.com/aparatos",   
	        		new DtFecha(2021,   6,   1,   18,   0,   0),   new DtFecha(2021,   3,   31,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Fuerza Bruta",   "Aparatos y pesas",   "Aparatos");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
			// Voleibol #C4
	        claseOk = new DtClase("Voleibol",   "denis",   "denis",   10,   21,   
	        		"https://telon.com.uy/voley",   new DtFecha(2021,   6,   10,   19,   0,   0),   new DtFecha(2021,   4,   20,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Telón",   "Voleibol",   "Voleibol");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
	        // Braza #C5
	        claseOk = new DtClase("Braza",   "Nelson",   "Nelson",   2,   6,   "https://telon.com.uy/natacionB",   
	        		new DtFecha(2021,   7,   10,   20,   0,   0),   new DtFecha(2021,   4,   20,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Telón",   "Voleibol",   "Braza");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
	        // Mariposa #C6
	        claseOk = new DtClase("Mariposa",   "Nelson",   "Nelson",   2,   6,   "https://telon.com.uy/natacionM",   
	        		new DtFecha(2021,   8,   10,   17,   45,   0),   new DtFecha(2021,   4,   20,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Telón",   "Voleibol",   "Mariposa");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
			// Aeróbica niños #C7
	        claseOk = new DtClase("Aeróbica niños",   "clazar",   "clazar",   5,   10,   "https://www.inatural.com/aeroni",   
	        		new DtFecha(2021,   8,   15,   16,   30,   0),   new DtFecha(2021,   5,   30,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Instituto Natural",   "Aeróbica",   "Aeróbica niños");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
	        // Aeróbico adulto mayor #C8
	        claseOk = new DtClase("Aeróbico adulto mayor",   "clazar",   "clazar",   5,   12,   "https://www.inatural.com/aeroam",   
	        		new DtFecha(2021,   8,   31,   19,   30,   0),   new DtFecha(2021,   5,   30,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Instituto Natural",   "Aeróbica",   "Aeróbico adulto mayor");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
	        // Aeróbico #C9
			claseOk = new DtClase("Aeróbica",   "clazar",   "clazar",   5,   20,   "https://www.inatural.com/aerogral",   
	        		new DtFecha(2021,   9,   30,   20,   0,   0),   new DtFecha(2021,   5,   30,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Instituto Natural",   "Aeróbica",   "Aeróbica");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
			 // Boxeo I #C10
	        claseOk = new DtClase("Boxeo I",   "TheBoss",   "TheBoss",   1,   4,   "https://www.musculos.com/boxeo1",   
	        		new DtFecha(2021,   9,   1,   19,   30,   0),   new DtFecha(2021,   6,   7,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Fuerza Bruta",   "Kickboxing",   "Boxeo I");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
	        // Boxeo II #C11
	        claseOk = new DtClase("Boxeo II",   "TheBoss",   "TheBoss",   2,   2,   "https://www.musculos.com/boxeo2",   
	        		new DtFecha(2021,   9,   30,   17,   0,   0),   new DtFecha(2021,   6,   7,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Fuerza Bruta",   "Kickboxing",   "Boxeo II");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
	        // Músculos para boxeo #C12
	        claseOk = new DtClase("Músculos para boxeo",   "viktor",   "viktor",   1,   5,   "https://www.musculos.com/muscbox",   
	        		new DtFecha(2021,   10,   15,   20,   0,   0),   new DtFecha(2021,   6,   7,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Fuerza Bruta",   "Kickboxing",   "Músculos para boxeo");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
			// 100 M #C13
	        claseOk = new DtClase("100 M",   "lale",   "lale",   3,   10,   "https://telon.com.uy/100m",   
	        		new DtFecha(2021,   9,   25,   19,   0,   0),   new DtFecha(2021,   7,   8,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Telón",   "Atletismo",   "100 M");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
	        // 200 M #C14
	        claseOk = new DtClase("200 M",   "lale",   "lale",   3,   10,   "https://telon.com.uy/200m",   
	        		new DtFecha(2021,   10,   25,   18,   30,   0),   new DtFecha(2021,   7,   8,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Telón",   "Atletismo",   "200 M");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
	        // Posta #C15
	        claseOk = new DtClase("Posta",   "lale",   "lale",   8,   16,   "https://telon.com.uy/posta",   
	        		new DtFecha(2021,   11,   25,   17,   45,   0),   new DtFecha(2021,   7,   8,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Telón",   "Atletismo",   "Posta");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
			// Basquet I #C16
	        claseOk = new DtClase("Basquet I",   "aldo",   "aldo",   10,   15,   "https://telon.com.uy/bball1",   
	        		new DtFecha(2021,   9,   1,   21,   0,   0),   new DtFecha(2021,   7,   31,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Telón",   "Basquetbol",   "Basquet I");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
	        // Basquet II #C17
	        claseOk =new DtClase("Basquet II",   "aldo",   "aldo",   10,   10,   "https://telon.com.uy/bball2",   
	        		new DtFecha(2021,   10,   1,   21,   0,   0),   new DtFecha(2021,   7,   31,   0,   0,   0));
			claseIngresada = IDCC.seleccionarClase("Telón",   "Basquetbol",   "Basquet II");
			assertEquals(claseIngresada.getNombre(),   claseOk.getNombre());
			assertEquals(claseIngresada.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseIngresada.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseIngresada.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseIngresada.getURL(),   claseOk.getURL());
			assertEquals(claseIngresada.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseIngresada.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
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
			// Iniciamos las instancias de institucion,   profesor y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
					"かわいい",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) {
					;
			}
			DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
			DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtClase claseNueva = new DtClase("ClaseNueva",   "profAuxiliar",   "profe@auxiliar.com",   1,   99,   
					"https://www.auxiliar.com/auxiliar",   inicioClase,   registroClase);
			IDCC.ingresarDatosClase("InstitutoAuxiliar",   "ActividadAuxiliar",   claseNueva);
			// Seleccionamos la clase recien creada.
	        DtClase claseA = IDCC.seleccionarClase("InstitutoAuxiliar",   "ActividadAuxiliar",   "ClaseNueva");
	        // Verificamos que este en la Institucion y la ActividadDeportiva.
	        assertEquals(IDCC.obtenerClases("InstitutoAuxiliar",   "ActividadAuxiliar").contains(claseNueva.getNombre()),   true);
	        assertEquals(claseA.getNombre(),   claseNueva.getNombre());
	        assertEquals(claseA.getNicknameProfesor(),   claseNueva.getNicknameProfesor());
	        assertEquals(claseA.getMinSocios(),   claseNueva.getMinSocios());
	        assertEquals(claseA.getMaxSocios(),   claseNueva.getMaxSocios());
	        assertEquals(claseA.getURL(),   claseNueva.getURL());
	        assertEquals(claseA.getFechaClase().toFecha(),   claseNueva.getFechaClase().toFecha());
	        assertEquals(claseA.getFechaRegistro().toFecha(),   claseNueva.getFechaRegistro().toFecha());
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
			// Iniciamos las instancias de institucion,   profesor y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
					"asd",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { 
				; 
			}
			DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
			DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
			IDCC.ingresarDatosClase("InstitutoAuxiliar",   "ActividadAuxiliar",   new DtClase("ClaseRepetida",   "profAuxiliar",   
					"profe@auxiliar.com",   1,   99,   "https://www.auxiliar.com/repetida",   inicioClase,   registroClase));
			// Volvemos a crear una clase con el mismo nombre,   nos debe saltar el throw ClaseException.
			Assertions.assertThrows(ClaseException.class,   () -> {
				IDCC.ingresarDatosClase("InstitutoAuxiliar",   "ActividadAuxiliar",   
					new DtClase("ClaseRepetida",   "profAuxiliar",   "profe@auxiliar.com",   1,   99,   "https://www.auxiliar.com/repetida2",   
					inicioClase,   registroClase)); });
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
			// Iniciamos las instancias de institucion,   profesor y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
					"うぇｒ",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) {
				;
			}
			DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
			// Creamos una fecha de registro no valida.
			DtFecha registroClase = new DtFecha(2019,   1,   1,   0,   0,   0);
			// Creamos la clase con la fecha registro invalida,   nos debe saltar el throw FechaInvalidaException.
			Assertions.assertThrows(FechaInvalidaException.class,   () -> {
					IDCC.ingresarDatosClase("InstitutoAuxiliar",   
					"ActividadAuxiliar",   new DtClase("ClaseFechaInvalida",   "profAuxiliar",   "profe@auxiliar.com",   1,   99,   
					"https://www.auxiliar.com/registroInvalido",   inicioClase,   registroClase)); });
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testClaseFechaInicioInvalida() {
		try {
			// Iniciamos las instancias de institucion,   profesor y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
				"にっがあ",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { 
				;
			}
			// Creamos una fecha de inicio no valida.
			DtFecha inicioClase = new DtFecha(2019,   1,   2,   0,   0,   0);
			DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
			// Creamos la clase con la fecha inicio invalida,   nos debe saltar el throw FechaInvalidaException.
			Assertions.assertThrows(FechaInvalidaException.class,   () -> {
					IDCC.ingresarDatosClase("InstitutoAuxiliar",   
					"ActividadAuxiliar",   new DtClase("ClaseFechaInvalida",   "profAuxiliar",   "profe@auxiliar.com",   1,   99,   
					"https://www.auxiliar.com/registroInvalido",   inicioClase,   registroClase)); });
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testClaseNoExisteInstitucion() {
		DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
		DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
		// Creamos la clase con una Institucion no valida,   nos debe saltar el throw InstitucionException.
		Assertions.assertThrows(InstitucionException.class,   () -> {
					IDCC.ingresarDatosClase("InstitutoFalso",   
				"ActividadAuxiliar",   new DtClase("ClaseARomper",   "profAuxiliar",   "profe@auxiliar.com",   1,   99,   
				"https://www.auxiliar.com/InstitutoFalso",   inicioClase,   registroClase)); });
	}
	
	@Test
	void testClaseActividadNoEsDeInstitucion() {
		try {
			// Iniciamos las instancias de institucion,   profesor y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
					"oof",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
			DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
			DtFecha registroClase = new DtFecha(2019,   1,   1,   0,   0,   0);
			// Creamos la clase con una actividad no valida,   nos debe saltar el throw ActividadDeportivaException.
			Assertions.assertThrows(ActividadDeportivaException.class,   () -> {
					IDCC.ingresarDatosClase("InstitutoAuxiliar",   
					"ActividadFalsa",   new DtClase("ClaseARomper",   "profAuxiliar",   "profe@auxiliar.com",   1,   99,   
					"https://www.auxiliar.com/ActividadFalsa",   inicioClase,   registroClase)); });
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testClaseProfesorNoEsDeInstitucion() {
		try {
			// Iniciamos las instancias de institucion,   profesor y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) {
				;
			}
			DtFecha inicioClase = new DtFecha(2019,   1,   2,   0,   0,   0);
			DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
			// Creamos la clase con un Profesor no valido,   nos debe saltar el throw UsuarioNoExisteException.
			Assertions.assertThrows(UsuarioNoExisteException.class,   () -> {
					IDCC.ingresarDatosClase("InstitutoAuxiliar",   
					"ActividadAuxiliar",   new DtClase("ClaseProfeInvalido",   "profesorFalso",   "profe@falso.com",   1,   99,   
					"https://www.auxiliar.com/profeInvalido",   inicioClase,   registroClase)); });
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testCargaDeCuponerasOk() {
		try {
			DtActividadDeportiva voleibol = IADC.getActDepExt("Telón",   "Voleibol");
			DtActividadDeportiva basket = IADC.getActDepExt("Telón",   "Basquetbol");
			DtActividadDeportiva aero = IADC.getActDepExt("Instituto Natural",   "Aeróbica");
			DtActividadDeportiva aypesas = IADC.getActDepExt("Fuerza Bruta",   "Aparatos y pesas");
			DtActividadDeportiva kick = IADC.getActDepExt("Fuerza Bruta",   "Kickboxing");
	        // Pelota #P1 : Voleibol(Telón,   7 clases,   750 pesos),   Basquetbol(Telón,   18 clases,   450)
			Set<String> actividades = new HashSet<>(Arrays.asList("Voleibol",   "Basquetbol"));
			DtFecha inicio = new DtFecha(2021,   5,   1,   0,   0,   0);
			DtFecha fin = new DtFecha(2021,   7,   31,   23,   59,   59);
			float costoTotal = (float) (0.8*(voleibol.getCosto()*7 + basket.getCosto()*18));
			DtCuponera cuponeraAProbar = ICC.seleccionarCuponera("Pelota");
			assertEquals(cuponeraAProbar.getNombre(),   "Pelota");
			assertEquals(cuponeraAProbar.getDescripcion(),   "Deportes con pelota.");
			assertEquals(cuponeraAProbar.getDescuento(),   20);
			assertEquals(cuponeraAProbar.getCosto(),   costoTotal);
			assertEquals(cuponeraAProbar.getFechaInicio().toFecha(),   inicio.toFecha());
			assertEquals(cuponeraAProbar.getFechaFin().toFecha(),   fin.toFecha());
			for (DtClasesCuponera x : cuponeraAProbar.getContenido()) {
				assertEquals(actividades.contains(x.getNombreActividad()),   true);
			}
			// Gimnasia #P2 : Aeróbica(Instituto Natural,   2 clases,   800 pesos),   Aparatos y pesas(Fuerza Bruta,   8 clases,   550)
			actividades = new HashSet<>(Arrays.asList("Aeróbica",   "Aparatos y pesas"));
			inicio = new DtFecha(2021,   8,   1,   0,   0,   0);
			fin = new DtFecha(2021,   9,   30,   23,   59,   59);
			costoTotal = (float) (0.7*(aero.getCosto()*2 + aypesas.getCosto()*8));
			cuponeraAProbar = ICC.seleccionarCuponera("Gimnasia");
			assertEquals(cuponeraAProbar.getNombre(),   "Gimnasia");
			assertEquals(cuponeraAProbar.getDescripcion(),   "Aeróbica y aparatos.");
			assertEquals(cuponeraAProbar.getDescuento(),   30);
			assertEquals(cuponeraAProbar.getCosto(),   costoTotal);
			assertEquals(cuponeraAProbar.getFechaInicio().toFecha(),   inicio.toFecha());
			assertEquals(cuponeraAProbar.getFechaFin().toFecha(),   fin.toFecha());
			for (DtClasesCuponera x : cuponeraAProbar.getContenido()) {
				assertEquals(actividades.contains(x.getNombreActividad()),   true);
			}
			// Músculos #P2 : Kickboxing(Fuerza Bruta,   11 clases,   980 pesos),   Aparatos y pesas(Fuerza Bruta,   12 clases,   550)
			actividades = new HashSet<>(Arrays.asList("Kickboxing",   "Aparatos y pesas"));
			inicio = new DtFecha(2021,   8,   15,   0,   0,   0);
			fin = new DtFecha(2021,   11,   15,   23,   59,   59);
			costoTotal = (float) 0.9*(kick.getCosto()*11 + aypesas.getCosto()*12);
			cuponeraAProbar = ICC.seleccionarCuponera("Músculos");
			assertEquals(cuponeraAProbar.getNombre(),   "Músculos");
			assertEquals(cuponeraAProbar.getDescripcion(),   "Pesas.");
			assertEquals(cuponeraAProbar.getDescuento(),   10);
			assertEquals(cuponeraAProbar.getCosto(),   costoTotal);
			assertEquals(cuponeraAProbar.getFechaInicio().toFecha(),   inicio.toFecha());
			assertEquals(cuponeraAProbar.getFechaFin().toFecha(),   fin.toFecha());
			for (DtClasesCuponera x : cuponeraAProbar.getContenido()) {
				assertEquals(actividades.contains(x.getNombreActividad()),   true);
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
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) {
				;
			}
			// Generamos la cuponera a probar.
	        ICC.ingresarCuponera("CupNueva",   "PruebaCuponera",   new DtFecha(2020,   1,   1,   0,   0,   0),   new DtFecha(2022,   1,   1,   0,   0,   0),   
	        		55,   new DtFecha(2020,   1,   1,   0,   0,   0), null);
	        try {
				ICC.agregarActividadCuponera("CupNueva",   "InstitutoAuxiliar",   "ActividadAuxiliar",   10);
			} catch (CuponeraInmutableException ignore) {
				;
			}
			Set<String> actividades = new HashSet<>(Arrays.asList("ActividadAuxiliar"));
			DtFecha inicio = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtFecha fin = new DtFecha(2022,   1,   1,   0,   0,   0);
			float costoTotal = (float) (0.45*actividadAuxiliar.getCosto()*10);
			DtCuponera cuponeraAProbar = ICC.seleccionarCuponera("CupNueva");
			assertEquals(cuponeraAProbar.getNombre(),   "CupNueva");
			assertEquals(cuponeraAProbar.getDescripcion(),   "PruebaCuponera");
			assertEquals(cuponeraAProbar.getDescuento(),   55);
			assertEquals(cuponeraAProbar.getCosto(),   costoTotal);
			assertEquals(cuponeraAProbar.getFechaInicio().toFecha(),   inicio.toFecha());
			assertEquals(cuponeraAProbar.getFechaFin().toFecha(),   fin.toFecha());
			for (DtClasesCuponera x : cuponeraAProbar.getContenido()) {
				assertEquals(actividades.contains(x.getNombreActividad()),   true);
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
	        ICC.ingresarCuponera("CupARepetir",   "PrimeraIteracion",   new DtFecha(2020,   1,   1,   0,   0,   0),   new DtFecha(2022,   1,   1,   0,   0,   0),   
	        		55,   new DtFecha(2020,   1,   1,   0,   0,   0), null);
	        Assertions.assertThrows(CuponeraRepetidaException.class,   () -> {
	        		ICC.ingresarCuponera("CupARepetir",   "SegundaIteracion",   
	        		new DtFecha(2020,   1,   1,   0,   0,   0),   new DtFecha(2022,   1,   1,   0,   0,   0),   55,   new DtFecha(2020,   1,   1,   0,   0,   0), null); });
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
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { 
				;
			}
			// Generamos la cuponera a probar.
			try {
		        ICC.ingresarCuponera("CupAuxiliar",   "PruebaCuponera",   new DtFecha(2020,   1,   1,   0,   0,   0),   new DtFecha(2022,   1,   1,   0,   0,   0),   
		        		55,   new DtFecha(2020,   1,   1,   0,   0,   0), null);
			} catch (CuponeraRepetidaException ignore) { 
				;
			}
	        Assertions.assertThrows(InstitucionException.class,   () -> {
	        		ICC.agregarActividadCuponera("CupAuxiliar",   
	        		"InstitutoFalso",   "ActividadAuxiliar",   10); });
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
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { 
				;
			}
			// Generamos la cuponera a probar.
			try {
		        ICC.ingresarCuponera("CupAuxiliar",   "PruebaCuponera",   new DtFecha(2020,   1,   1,   0,   0,   0),   new DtFecha(2022,   1,   1,   0,   0,   0),   
		        		55,   new DtFecha(2020,   1,   1,   0,   0,   0), null);
			} catch (CuponeraRepetidaException ignore) {
				;
			}
	        Assertions.assertThrows(ActividadDeportivaException.class,   () -> {
	        		ICC.agregarActividadCuponera("CupAuxiliar",   
	        		"InstitutoAuxiliar",   "ActividadFalsa",   10); });
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
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { 
				;
			}
			// Generamos la cuponera a probar.
	        Assertions.assertThrows(FechaInvalidaException.class,   () -> {
	        		ICC.ingresarCuponera("CupAuxiliar",   "PruebaCuponera",   
	        		new DtFecha(2020,   1,   1,   0,   0,   0),   new DtFecha(2022,   1,   1,   0,   0,   0),   55,   new DtFecha(2022,   1,   1,   0,   0,   0), null); });
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testCargaDeInscripcionesClase() {
		try {
			// Calistenia
			DtClaseExt datosClase = IDCC.seleccionarClase("Fuerza Bruta",   "Aparatos y pesas",   "Calistenia");
			List<String> alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("caro"),   true);
			assertEquals(alumnosClase.contains("sergiop"),   true);
			assertEquals(alumnosClase.contains("andy"),   true);
			// 
			datosClase = IDCC.seleccionarClase("Fuerza Bruta",   "Aparatos y pesas",   "Peso libre");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("andy"),   true);
			assertEquals(alumnosClase.contains("tonyp"),   true);
			assertEquals(alumnosClase.contains("caro"),   true);
			assertEquals(alumnosClase.contains("m1k4"),   true); 
			// Aparatos
			datosClase = IDCC.seleccionarClase("Fuerza Bruta",   "Aparatos y pesas",   "Aparatos");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("charly"),   true);
			assertEquals(alumnosClase.contains("caro"),   true);
			assertEquals(alumnosClase.contains("m1k4"),   true);
			// Voleibol
			datosClase = IDCC.seleccionarClase("Telón",   "Voleibol",   "Voleibol");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("Emi71"),   true);
			assertEquals(alumnosClase.contains("euge"),   true);
			assertEquals(alumnosClase.contains("sergiop"),   true);
			assertEquals(alumnosClase.contains("tonyp"),   true);
			// Braza
			datosClase = IDCC.seleccionarClase("Telón",   "Voleibol",   "Braza");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("guille"),   true);
			assertEquals(alumnosClase.contains("euge"),   true);
			assertEquals(alumnosClase.contains("m1k4"),   true);
			// Mariposa
			datosClase = IDCC.seleccionarClase("Telón",   "Voleibol",   "Mariposa");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("charly"),   true);
			assertEquals(alumnosClase.contains("sergiop"),   true);
			assertEquals(alumnosClase.contains("andy"),   true);
			// Aeróbica niños
			datosClase = IDCC.seleccionarClase("Instituto Natural",   "Aeróbica",   "Aeróbica niños");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("m1k4"),   true);
			// Aeróbico adulto mayor
			datosClase = IDCC.seleccionarClase("Instituto Natural",   "Aeróbica",   "Aeróbico adulto mayor");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("Emi71"),   true);
			assertEquals(alumnosClase.contains("guille"),   true);
			assertEquals(alumnosClase.contains("andy"),   true);
			// Aeróbica
			datosClase = IDCC.seleccionarClase("Instituto Natural",   "Aeróbica",   "Aeróbica");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("caro"),   true);
			assertEquals(alumnosClase.contains("euge"),   true);
			// Boxeo I
			datosClase = IDCC.seleccionarClase("Fuerza Bruta",   "Kickboxing",   "Boxeo I");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("andy"),   true);
			assertEquals(alumnosClase.contains("tonyp"),   true);
			assertEquals(alumnosClase.contains("m1k4"),   true);
			// Boxeo II
			datosClase = IDCC.seleccionarClase("Fuerza Bruta",   "Kickboxing",   "Boxeo II");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("sergiop"),   true);
			assertEquals(alumnosClase.contains("guille"),   true);
			// Músculos para boxeo
			datosClase = IDCC.seleccionarClase("Fuerza Bruta",   "Kickboxing",   "Músculos para boxeo");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("Emi71"),   true);
			assertEquals(alumnosClase.contains("caro"),   true);
			assertEquals(alumnosClase.contains("euge"),   true);
			assertEquals(alumnosClase.contains("sergiop"),   true);
			// 100 M
			datosClase = IDCC.seleccionarClase("Telón",   "Atletismo",   "100 M");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("guille"),   true);
			assertEquals(alumnosClase.contains("charly"),   true);
			// 200 M
			datosClase = IDCC.seleccionarClase("Telón",   "Atletismo",   "200 M");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("Emi71"),   true);
			assertEquals(alumnosClase.contains("charly"),   true);
			// Posta
			datosClase = IDCC.seleccionarClase("Telón",   "Atletismo",   "Posta");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("caro"),   true);
			// Basquet I
			datosClase = IDCC.seleccionarClase("Telón",   "Basquetbol",   "Basquet I");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("sergiop"),   true);
			assertEquals(alumnosClase.contains("Emi71"),   true);
			assertEquals(alumnosClase.contains("tonyp"),   true);
			// Basquet II
			datosClase = IDCC.seleccionarClase("Telón",   "Basquetbol",   "Basquet II");
			alumnosClase = datosClase.getNickAlumnos();
			assertEquals(alumnosClase.contains("andy"),   true);
			assertEquals(alumnosClase.contains("tonyp"),   true);
			assertEquals(alumnosClase.contains("caro"),   true);
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
		// Iniciamos las instancias de institucion,   socio,   profesor,   clase,   cuponera y actividadDeportiva.
		IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
		IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar",   "Socio",   "Auxiliar",   "socio@auxiliar.com",   
				"ペロペロ",   new DtFecha(1998,   1,   1,   0,   0,   0),   null));
		IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
				"ペロペロ",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
		DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
		DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
		try {
			IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
		} catch (ActividadDeportivaException ignore) { 
			;
		}
		DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
		DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
		DtClase claseNueva = new DtClase("ClaseAuxiliar",   "profAuxiliar",   "profe@auxiliar.com",   1,   99,   
				"https://www.auxiliar.com/auxiliar",   inicioClase,   registroClase);
		try {
			IDCC.ingresarDatosClase("InstitutoAuxiliar",   "ActividadAuxiliar",   claseNueva);
		} catch (ClaseException ignore) {
			;
		}
		IDCC.inscribirSocio("InstitutoAuxiliar",   "ActividadAuxiliar",   "ClaseAuxiliar",   "socioAuxiliar",   TReg.general,   
				new DtFecha(2020,   1,   1,   0,   0,   0),   null);
		DtClaseExt datosClase = IDCC.seleccionarClase("InstitutoAuxiliar",   "ActividadAuxiliar",   "ClaseAuxiliar");
		List<String> alumnosClase = datosClase.getNickAlumnos();
		assertEquals(alumnosClase.contains("socioAuxiliar"),   true);
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
			// Iniciamos las instancias de institucion,   socio,   profesor,   clase,   cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioRepetido",   "Socio",   "Auxiliar",   "sociorepetido@auxiliar.com",   
					"a",   new DtFecha(1998,   1,   1,   0,   0,   0),   null));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
					"b",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { 
				;
			}
			DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
			DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtClase claseNueva = new DtClase("ClaseAuxiliar",   "profAuxiliar",   "profe@auxiliar.com",   1,   99,   
					"https://www.auxiliar.com/auxiliar",   inicioClase,   registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar",   "ActividadAuxiliar",   claseNueva);
			} catch (ClaseException ignore) {
				;
			}
			IDCC.inscribirSocio("InstitutoAuxiliar",   "ActividadAuxiliar",   "ClaseAuxiliar",   "socioRepetido",   TReg.general,   
					new DtFecha(2020,   1,   1,   0,   0,   0),   null);
			Assertions.assertThrows(ClaseException.class,   () -> {
					IDCC.inscribirSocio("InstitutoAuxiliar",   
					"ActividadAuxiliar",   "ClaseAuxiliar",   "socioRepetido",   TReg.general,   new DtFecha(2020,   1,   1,   0,   0,   0),   null); });
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
			// Iniciamos las instancias de institucion,   socio,   profesor,   clase,   cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar",   "Socio",   "Auxiliar",   "socio@auxiliar.com",   
					"a",   new DtFecha(1998,   1,   1,   0,   0,   0),   null));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
					"asd",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { 
				;
			}
			DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
			DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtClase claseNueva = new DtClase("ClaseAuxiliarFail",   "profAuxiliar",   "profe@auxiliar.com",   1,   99,   
					"https://www.auxiliar.com/auxiliar",   inicioClase,   registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar",   "ActividadAuxiliar",   claseNueva);
			} catch (ClaseException ignore) {
				;
			}
			Assertions.assertThrows(FechaInvalidaException.class,   () -> {
					IDCC.inscribirSocio("InstitutoAuxiliar",   
					"ActividadAuxiliar",   "ClaseAuxiliarFail",   "socioAuxiliar",   TReg.general,   new DtFecha(2020,   1,   3,   0,   0,   0),   null); });
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
			// Iniciamos las instancias de institucion,   socio,   profesor,   clase,   cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar",   "Socio",   "Auxiliar",   "socio@auxiliar.com",   
					"x",   new DtFecha(1998,   1,   1,   0,   0,   0),   null));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
					"x",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) {
				;
			}
			DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
			DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtClase claseNueva = new DtClase("ClaseAuxiliarFail",   "profAuxiliar",   "profe@auxiliar.com",   1,   99,   
					"https://www.auxiliar.com/auxiliar",   inicioClase,   registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar",   "ActividadAuxiliar",   claseNueva);
			} catch (ClaseException ignore) {
				;
			}
			Assertions.assertThrows(FechaInvalidaException.class,   () -> {
					IDCC.inscribirSocio("InstitutoAuxiliar",   
					"ActividadAuxiliar",   "ClaseAuxiliarFail",   "socioAuxiliar",   TReg.general,   new DtFecha(2019,   12,   31,   0,   0,   0),   null); });
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
			// Iniciamos las instancias de institucion,   socio,   profesor,   clase,   cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar",   "Socio",   "Auxiliar",   "socio@auxiliar.com",   
					"a",   new DtFecha(1998,   1,   1,   0,   0,   0),   null));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
					"b",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { 
				;
			}
			DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
			DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtClase claseNueva = new DtClase("ClaseAuxiliarFail",   "profAuxiliar",   "profe@auxiliar.com",   1,   99,   
					"https://www.auxiliar.com/auxiliar",   inicioClase,   registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar",   "ActividadAuxiliar",   claseNueva);
			} catch (ClaseException ignore) {
				;
			}
			Assertions.assertThrows(InstitucionException.class,   () -> {
					IDCC.inscribirSocio("InstitutoFalso",   
					"ActividadAuxiliar",   "ClaseAuxiliarFail",   "socioAuxiliar",   TReg.general,   new DtFecha(2020,   1,   1,   0,   0,   0),   null); });
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
			// Iniciamos las instancias de institucion,   socio,   profesor,   clase,   cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar",   "Socio",   "Auxiliar",   "socio@auxiliar.com",   
					"x",   new DtFecha(1998,   1,   1,   0,   0,   0),   null));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
					"x",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) {
				;
				}
			DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
			DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtClase claseNueva = new DtClase("ClaseAuxiliarFail",   "profAuxiliar",   "profe@auxiliar.com",   1,   99,   
					"https://www.auxiliar.com/auxiliar",   inicioClase,   registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar",   "ActividadAuxiliar",   claseNueva);
			} catch (ClaseException ignore) {
				;
			}
			Assertions.assertThrows(ActividadDeportivaException.class,   () -> {
					IDCC.inscribirSocio("InstitutoAuxiliar",   
					"ActividadFalsa",   "ClaseAuxiliarFail",   "socioAuxiliar",   TReg.general,   new DtFecha(2020,   1,   1,   0,   0,   0),   null); });
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
			// Iniciamos las instancias de institucion,   socio,   profesor,   clase,   cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar",   "Socio",   "Auxiliar",   "socio@auxiliar.com",   
					"x",   new DtFecha(1998,   1,   1,   0,   0,   0),   null));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
					"x",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) {
				;
			}
			DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
			DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtClase claseNueva = new DtClase("ClaseAuxiliarFail",   "profAuxiliar",   "profe@auxiliar.com",   1,   99,   
					"https://www.auxiliar.com/auxiliar",   inicioClase,   registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar",   "ActividadAuxiliar",   claseNueva);
			} catch (ClaseException ignore) { 
				;
			}
			Assertions.assertThrows(ClaseException.class,   () -> {
					IDCC.inscribirSocio("InstitutoAuxiliar",   
					"ActividadAuxiliar",   "ClaseFalsa",   "socioAuxiliar",   TReg.general,   new DtFecha(2020,   1,   1,   0,   0,   0),   null); });
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
			// Iniciamos las instancias de institucion,   socio,   profesor,   clase,   cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar1",   "Socio",   "Auxiliar",   "socio1@auxiliar.com",   
					"x",   new DtFecha(1998,   1,   1,   0,   0,   0),   null));
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliar2",   "Socio",   "Auxiliar",   "socio2@auxiliar.com",   
					"x",   new DtFecha(1998,   1,   1,   0,   0,   0),   null));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
					"x",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) { 
				;
			}
			DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
			DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtClase claseNueva = new DtClase("ClaseChica",   "profAuxiliar",   "profe@auxiliar.com",   1,   1,   
					"https://www.auxiliar.com/auxiliar",   inicioClase,   registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar",   "ActividadAuxiliar",   claseNueva);
			} catch (ClaseException ignore) { 
				;
			}
			IDCC.inscribirSocio("InstitutoAuxiliar",   "ActividadAuxiliar",   "ClaseChica",   "socioAuxiliar1",   TReg.general,   
					new DtFecha(2020,   1,   1,   0,   0,   0),   null);
			Assertions.assertThrows(ClaseException.class,   () -> {
					IDCC.inscribirSocio("InstitutoAuxiliar",   
					"ActividadAuxiliar",   "ClaseChica",   "socioAuxiliar2",   TReg.general,   new DtFecha(2020,   1,   1,   0,   0,   0),   null); });
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
			// Iniciamos las instancias de institucion,   socio,   profesor,   clase,   cuponera y actividadDeportiva.
			IADC.altaInstitucion("InstitutoAuxiliar",   "https://www.auxiliar.com",   "Sirve como Auxiliar.");
			IUC.ingresarDatosUsuario(new DtSocio("socioAuxiliarSinCuponera",   "Socio",   "No Cuponera",   
					"socioSinCuponera@auxiliar.com",   "socioSinCuponera@auxiliar.com",   new DtFecha(1998,   1,   1,   0,   0,   0),   null));
			IUC.ingresarDatosUsuario(new DtProfesor("profAuxiliar",   "Profesor",   "Auxiliar",   "profe@auxiliar.com",   
					"socioSinCuponera@auxiliar.com",   new DtFecha(1998,   1,   1,   0,   0,   0),   "InstitutoAuxiliar",   "Auxiliar",   "Auxiliar" ,   "www.auxiliar.uy",   null));
			DtFecha fechaActividad = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva actividadAuxiliar = new DtActividadDeportiva("ActividadAuxiliar",   "Auxiliar",   1,   10,   fechaActividad,   null,   TEstado.aceptada,   "Administrador");
			try {
				IADC.ingresarDatosActividadDep("InstitutoAuxiliar",   actividadAuxiliar);
			} catch (ActividadDeportivaException ignore) {
				;
			}
			DtFecha inicioClase = new DtFecha(2020,   1,   2,   0,   0,   0);
			DtFecha registroClase = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtClase claseNueva = new DtClase("ClaseAuxiliarFail",   "profAuxiliar",   "profe@auxiliar.com",   1,   10,   
					"https://www.auxiliar.com/auxiliar",   inicioClase,   registroClase);
			try {
				IDCC.ingresarDatosClase("InstitutoAuxiliar",   "ActividadAuxiliar",   claseNueva);
			} catch (ClaseException ignore) {
				;
				}
			Assertions.assertThrows(NoExisteCuponeraException.class,   () -> {
					IDCC.inscribirSocio("InstitutoAuxiliar",   
					"ActividadAuxiliar",   "ClaseAuxiliarFail",   "socioAuxiliar",   TReg.cuponera,   new DtFecha(2020,   1,   1,   0,   0,   0),   "Pelota"); });
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
	void testActividadesNoDentroDeCuponeraYInstitucionOk() {
		try {
		    // Pelota #P1
			Set<String> actividades = IADC.obtenerDeltaInstituciones("Pelota",   "Telón");
			assertEquals(actividades.contains("Voleibol"),   false);
			assertEquals(actividades.contains("Basquetbol"),   false);
			assertEquals(actividades.contains("Atletismo"),   true);
			actividades = IADC.obtenerDeltaInstituciones("Pelota",   "Instituto Natural");
			assertEquals(actividades.contains("Aeróbica"),   true);
			actividades = IADC.obtenerDeltaInstituciones("Pelota",   "Fuerza Bruta");
			assertEquals(actividades.contains("Aparatos y pesas"),   true);
			// Gimnasia #P2
			actividades = IADC.obtenerDeltaInstituciones("Gimnasia",   "Instituto Natural");
			assertEquals(actividades.contains("Aeróbica"),   false);
			actividades = IADC.obtenerDeltaInstituciones("Gimnasia",   "Fuerza Bruta");
			assertEquals(actividades.contains("Aparatos y pesas"),   false);
			actividades = IADC.obtenerDeltaInstituciones("Gimnasia",   "Telón");
			assertEquals(actividades.contains("Voleibol"),   true);
			assertEquals(actividades.contains("Basquetbol"),   true);
			assertEquals(actividades.contains("Atletismo"),   true);
			// Músculos #P2
			actividades = IADC.obtenerDeltaInstituciones("Músculos",   "Fuerza Bruta");
			assertEquals(actividades.contains("Kickboxing"),   false);
			assertEquals(actividades.contains("Aparatos y pesas"),   false);
			actividades = IADC.obtenerDeltaInstituciones("Músculos",   "Telón");
			assertEquals(actividades.contains("Voleibol"),   true);
			assertEquals(actividades.contains("Basquetbol"),   true);
			assertEquals(actividades.contains("Atletismo"),   true);
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace(); 
		}
	}
	
	@Test
	void testObtInst() {
		Set<String> instis = IADC.obtenerInstituciones();
		assertEquals(instis.contains("Telón"),   true);
		assertEquals(instis.contains("Instituto Natural"),   true);
		assertEquals(instis.contains("Fuerza Bruta"),   true);
		assertEquals(instis.contains("Olympic"),   true);
		assertEquals(instis.size(),   4);
	}
	
	@Test
	void testSelClase() {
		try {
			DtClaseExt datosClase = IADC.seleccionarClase("Telón",   "Atletismo",   "100 M");
			List<String> datos=datosClase.getAlumnos();
			assertEquals(datos.contains("guille <ghector@gmail.com> - 16/8/2021 (general)"),   true);
			assertEquals(datos.contains("charly <charly@gmail.com.uy> - 3/9/2021 (general)"),   true);
			assertEquals(datos.size(),   2);
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
	void testIngresadas() {
		Set<String> acts = IADC.obtenerActDepIngresadas();
		assertEquals(acts.contains("Pilates"),   true);
		assertEquals(acts.contains("Basquetbol II"),   true);
		assertEquals(acts.size(),   2);
	}
	
	@Test
	void testLasRedesSocialesSonUnaEnfermedad() {
		String[] matrizUsuaria = {"Emi71",   "caro",   "euge",   "guille",   "sergiop",   "andy",   "tonyp",   "m1k4",   "charly",   "viktor",   "denis",   "clazar",   "TheBoss",   "Nelson",   "lale",   
	              "prisc",   "dagost",   "aldo"};
		//GetSeguidos
		try {
			Set<String> datosUsuario = IUC.seleccionarUsuario("guille").getSeguidoresNickname();
			Set<String> esperado = new HashSet<>();
			esperado.add(matrizUsuaria[0]);
			esperado.add(matrizUsuaria[1]);
			esperado.add(matrizUsuaria[10]);
			esperado.add(matrizUsuaria[11]);
			esperado.add(matrizUsuaria[12]);
			assertEquals(datosUsuario,   esperado);
			
			datosUsuario = IUC.seleccionarUsuario("denis").getSeguidosNickname();
			esperado = new HashSet<>();
			esperado.add(matrizUsuaria[0]);
			esperado.add(matrizUsuaria[1]);
			esperado.add(matrizUsuaria[2]);
			esperado.add(matrizUsuaria[3]);
			esperado.add(matrizUsuaria[4]);
			esperado.add(matrizUsuaria[5]);
			esperado.add(matrizUsuaria[6]);
			esperado.add(matrizUsuaria[7]);
			esperado.add(matrizUsuaria[8]);
			assertEquals(datosUsuario,   esperado);
			IUC.dejarDeSeguir(matrizUsuaria[0],   matrizUsuaria[3]);
			assertEquals(IUC.seleccionarUsuario(matrizUsuaria[0]).getSeguidosNickname().size()==0,   true);
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testComprarCuponera() {
		DtClaseExt datosClase;
		try {
			datosClase = IADC.seleccionarClase("Telón",   "Atletismo",   "100 M");
			IUC.comprarCuponera("Pelota",   "caro",   new DtFecha());
			DtSocioExt datoSocio = (DtSocioExt) IUC.seleccionarUsuario("caro");
			Set<String> cuponerasCompradas = datoSocio.getCuponerasCompradas(); 
			assertEquals(cuponerasCompradas.contains("Pelota"),   true);
			
			IDCC.inscribirSocio("Telón",   "Voleibol",   "Mariposa",   "caro",   TReg.cuponera,   new DtFecha(2021,   8,   9,   0,   0,   0),   "Pelota");
			datosClase = IADC.seleccionarClase("Telón",   "Voleibol",   "Mariposa");
			List<String> alumnosClase=datosClase.getAlumnos();
			assertEquals(alumnosClase.contains("caro <caro@gmail.com> - 9/8/2021 (cuponera)"),   true);
		} catch (InstitucionException | ClaseException | FechaInvalidaException | NoExisteCuponeraException | UsuarioNoExisteException | ActividadDeportivaException | CuponeraNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testContrasenias() {
			assertEquals(IUC.verificarIdentidadNickname("euge",   "poiuy086"),   true);
			assertEquals(IUC.verificarIdentidadNickname("euge",   "poiuy08666"),   false);
			assertEquals(IUC.verificarIdentidadEmail("vperez@fuerza.com",   "lkj34df"),   true);
			assertEquals(IUC.verificarIdentidadEmail("vperez@fuerza.com",   "lkj34asddf"),   false);
	}
	
	@Test
	void testParaUnaClaseQueCuponerasPuedoUsar() {
		Set<String> cups = new HashSet<>();
		cups.add("Músculos");
		assertEquals(IDCC.getCuponerasSocioClase("andy",   "Fuerza Bruta",   "Kickboxing",   "Músculos para boxeo"),   cups);
		cups.remove("Músculos");
		assertEquals(IDCC.getCuponerasSocioClase("euge",   "Telón",   "Voleibol",   "Voleibol"),   cups);
	}

	@Test
	void testAprovarActividad() {
		try {
			IADC.aprobarActividad("Pilates",   TEstado.aceptada);
			IADC.aprobarActividad("Basquetbol II",   TEstado.rechazada);
			assertEquals(IADC.getActDepExt("Instituto Natural",   "Pilates").getEstado(),   TEstado.aceptada);
			assertEquals(IADC.getActDepExt("Telón",   "Basquetbol II").getEstado(),   TEstado.rechazada);
		} catch (InstitucionException | ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testCategorias() {
		try {
			Set<String> categorias = new HashSet<>();
			categorias.add("Al aire libre");
			categorias.add("Deportes");
			categorias.add("Fitness");
			categorias.add("Gimnasia");
			assertEquals(IADC.obtenerCategorias(),   categorias);
			IADC.ingresarCatergoria(new DtCategoria("comer"));
			categorias.add("comer");
			assertEquals(IADC.obtenerCategorias(),   categorias);
		} catch (CategoriaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}	
	@Test
	void testCuponerasRecibos() {
		Set<String> auxSet =new HashSet<>();
		Set<String> nombresCuponerasSinRecibos=ICC.getNombreCuponerasSinRecibos();
		assertEquals(auxSet,   nombresCuponerasSinRecibos);
		try {
			ICC.ingresarCuponera("grasa",   "grasiento",   new DtFecha(),   new DtFecha(),   10,   new DtFecha(), null);
		} catch (CuponeraRepetidaException e) {
			e.printStackTrace();
		} catch (FechaInvalidaException e) {
			e.printStackTrace();
		}
		nombresCuponerasSinRecibos.add("grasa");
		auxSet=ICC.getNombreCuponerasSinRecibos();
		assertEquals(auxSet,   nombresCuponerasSinRecibos);
	}
	
	@Test
	void testBusquedaClaseOk() {
		DtClase claseOk = new DtClase("Mariposa",   "Nelson",   "Nelson",   2,   6,   "https://telon.com.uy/natacionM",   
        		new DtFecha(2021,   8,   10,   17,   45,   0),   new DtFecha(2021,   4,   20,   0,   0,   0));
		try {
			DtClaseExt claseABuscar = IDCC.buscarClase("Mariposa");
			assertEquals(claseABuscar.getNombre(),   claseOk.getNombre());
			assertEquals(claseABuscar.getNicknameProfesor(),   claseOk.getNicknameProfesor());
			assertEquals(claseABuscar.getMinSocios(),   claseOk.getMinSocios());
			assertEquals(claseABuscar.getMaxSocios(),   claseOk.getMaxSocios());
			assertEquals(claseABuscar.getURL(),   claseOk.getURL());
			assertEquals(claseABuscar.getFechaClase().toFechaHora(),   claseOk.getFechaClase().toFechaHora());
			assertEquals(claseABuscar.getFechaRegistro().toFechaHora(),   claseOk.getFechaRegistro().toFechaHora());
		} catch (ClaseException e) {
			System.out.println("FIN");
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testFalloBusquedaClase() {
		Assertions.assertThrows(ClaseException.class,   () -> {
					IDCC.buscarClase("ClaseQueNoExiste"); });
	}
	
	@Test
	void testCuponerasDisponibles() {
		try {
			Set<String> cupsDisp = IDCC.getCuponerasDisponibles("guille",   "Telón",   "Voleibol");
			assertEquals(cupsDisp.contains("Pelota"),   true);
			assertEquals(cupsDisp.contains("Gimnasia"),   false);
			assertEquals(cupsDisp.contains("Músculos"),   false);
		} catch (UsuarioNoExisteException | InstitucionException | ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testBuscarActDep() {
		try {
			DtActividadDeportivaExt datosActDep = IADC.buscarActDep("Voleibol");
			DtFecha fecha = new DtFecha(2021,  4,  20,  0,  0,  0);
			assertEquals(datosActDep.getNombre(),  "Voleibol");
			assertEquals(datosActDep.getDescripcion(),  "Voleibol en todas sus formas.");
			assertEquals(datosActDep.getDuracionMinutos(),   120);
			assertEquals(datosActDep.getCosto(),   750);
			assertEquals(datosActDep.getFechaRegistro().toFecha(),   fecha.toFecha());
		} catch(ActividadDeportivaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test 
	void testObtenerInstitucionActDep() {
		String institucion = IDCC.obtenerInstitucionActDep("Kickboxing");
		assertEquals(institucion,   "Fuerza Bruta");
	}
	
	@Test
	void valorarProfesor() {
		try {
			IUC.valorarProfesor("caro", "Fuerza Bruta", "Aparatos y pesas", "Calistenia", 5);
			IUC.valorarProfesor("sergiop", "Fuerza Bruta", "Aparatos y pesas", "Calistenia", 2);
			IUC.valorarProfesor("Emi71", "Fuerza Bruta", "Kickboxing", "Músculos para boxeo", 4);
			IUC.valorarProfesor("caro", "Fuerza Bruta", "Kickboxing", "Músculos para boxeo", 2);
			
			DtProfesorExt dtp = (DtProfesorExt) IUC.seleccionarUsuario("viktor");
			assertEquals(dtp.getValoracion(), 3.25);
			//Editar valoración
			IUC.valorarProfesor("caro", "Fuerza Bruta", "Aparatos y pesas", "Calistenia", 1);
			dtp = (DtProfesorExt) IUC.seleccionarUsuario("viktor");
			assertEquals(dtp.getValoracion(), 2.25);
		} catch(Exception e) { 
			e.printStackTrace();
			fail(e.getMessage());
		}
	} 
	
	@Test
	void favoritearActividad() {
		try {
			IUC.favoritearActividad("caro", "Telón", "Basquetbol");
			IUC.favoritearActividad("caro", "Telón", "Voleibol");
			
			DtSocioExt dts = (DtSocioExt) IUC.seleccionarUsuario("caro");
			Set<String> p1 = new HashSet<>();
			p1.add("Basquetbol");
			p1.add("Voleibol");
			assertEquals(dts.getActividadesFavoritas(),p1);
			IUC.favoritearActividad("caro", "Telón", "Voleibol");
			dts = (DtSocioExt) IUC.seleccionarUsuario("caro");
			p1.remove("Voleibol");
			assertEquals(dts.getActividadesFavoritas(),p1);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test 
    void testConsultaClase2() throws ClaseException, InstitucionException, FechaInvalidaException, UsuarioNoExisteException, ActividadDeportivaException {
         IDCC.ingresarDatosClase("Fuerza Bruta",   "Kickboxing",   new DtClase("Boxeaciones",   "TheBoss",   "TheBoss",   
                    1,   4,   "https://www.musculos.com/boxeo1",   new DtFecha(2021,   9,   1,   19,   30,   0),
                    new DtFecha(2021,   6,   7,   0,   0,   0),null, "https://www.youtube.com/watch?v=HC7CiSQR2vE",
                    new DtPremio("semejante premiardo",3,null,new DtFecha(2021,4,31,0,0,0))));
        
        DtClaseExt URL = IDCC.buscarClase("Boxeaciones");
        assertEquals(URL.getUrlVideo(),   "https://www.youtube.com/watch?v=HC7CiSQR2vE");
        
        String desc = IDCC.buscarClase("Boxeaciones").getPremio().getDescripcion();
        assertEquals(desc,   "semejante premiardo");
        
        int cant = IDCC.buscarClase("Boxeaciones").getPremio().getCantidad();
        assertEquals(cant,3);
        
        List<String> wins = IDCC.buscarClase("Boxeaciones").getPremio().getGanadores();
        assertEquals(wins,new ArrayList<>());
    }
        
    @Test 
    void testVerPremios() throws InstitucionException, ClaseException, ActividadDeportivaException, FechaInvalidaException, NoExisteCuponeraException, UsuarioNoExisteException {
        
        IDCC.ingresarDatosClase("Fuerza Bruta",   "Aparatos y pesas",   new DtClase("PPsas",   "viktor",   "viktor",   
                1,   5,   "https://www.musculos.com/Calistenia",   new DtFecha(2021,   4,   15,   15,   30,   0),
                new DtFecha(2021,   3,   31,   0,   0,   0), null, "https://www.youtube.com/watch?v=HC7CiSQR2vE",
                new DtPremio("semejante premiardo",1,null, new DtFecha(2021,4,31,0,0,0))));

        IDCC.inscribirSocio("Fuerza Bruta",   "Aparatos y pesas",   "PPsas",   "caro",   TReg.general,   
                new DtFecha(2021,   4,   9,   0,   0,   0),   null);
        
        Set<String> wins = IDCC.sortearPremios("Fuerza Bruta", "Aparatos y pesas", "PPsas");
        Set<String> premio = new HashSet<>();
        premio.add("caro");
        assertEquals(wins,   premio);
        
        

        DtClaseExt ppsas = IDCC.seleccionarClase("Fuerza Bruta",   "Aparatos y pesas", "PPsas");
        List<String> w = new ArrayList<>();
        w.add("caro");
        assertEquals(w, ppsas.getPremio().getGanadores());
        
        
        DtSocioExt caro = (DtSocioExt) IUC.seleccionarUsuario("caro");
        Map<String, DtPremio> premios = caro.getPremios();
        for(Entry<String, DtPremio> x: premios.entrySet()) {
        	System.out.println(x.getKey());
        }
        DtPremio pcla = premios.get("PPsas");
        assertEquals(pcla.getCantidad(),ppsas.getPremio().getCantidad()); 	 
        assertEquals(pcla.getDescripcion(),ppsas.getPremio().getDescripcion());
        assertEquals(pcla.getFechaSorteo(),ppsas.getPremio().getFechaSorteo());
        for(String x: pcla.getGanadores())
        	System.out.print(x);
        for(String x: ppsas.getPremio().getGanadores())
        	System.out.print(x);
        assertEquals(pcla.getGanadores(),ppsas.getPremio().getGanadores());

    }    
     
    @Test 
    void testFinalizarAct() throws InstitucionException, ActividadDeportivaException {
    	try {
			DtFecha fecha = new DtFecha(2020,   1,   1,   0,   0,   0);
			DtActividadDeportiva nuevaActividad = new DtActividadDeportiva("NuevaActividadAFinalizar",   "Desc",   1,   10,   fecha,   null,   TEstado.aceptada,   "Administrador");
			assertEquals(IADC.ingresarDatosActividadDep("Fuerza Bruta",   nuevaActividad),   true);
	        IDCC.ingresarDatosClase("Fuerza Bruta",   "NuevaActividadAFinalizar",   new DtClase("PPsas67",   "viktor",   "viktor",   
	                1,   5,   "https://www.musculos.com/Calistenia",   new DtFecha(2021,   4,   15,   15,   30,   0),
	                new DtFecha(2021,   3,   31,   0,   0,   0), null, "https://www.youtube.com/watch?v=HC7CiSQR2vE",
	                new DtPremio("semejante premiardo",1,null, new DtFecha(2021,4,31,0,0,0))));
	        IDCC.inscribirSocio("Fuerza Bruta",   "NuevaActividadAFinalizar",   "PPsas67",   "caro",   TReg.general,   
	                new DtFecha(2021,   4,   9,   0,   0,   0),   null);
			IADC.finalizarActividad("NuevaActividadAFinalizar");
			try {
				IADC.getActDepExt("Fuerza Bruta", "NuevaActividadAFinalizar").getEstado();
				fail("La actividad no debería aparecer en la institución.");
			} catch(ActividadDeportivaException r) { ; }
			DtActividadDeportivaExt actPersistida = IADC.buscarActDep("NuevaActividadAFinalizar");
	    	assertEquals(actPersistida.getNombre(), "NuevaActividadAFinalizar");
	    	assertEquals(actPersistida.getEstado(), TEstado.finalizada);
	        DtSocioExt socio = (DtSocioExt) IUC.seleccionarUsuario("caro");
	        Set<String> clase = new HashSet<>();
	        clase.add("PPsas67");
	        assertEquals(actPersistida.getClases(), clase);
	        Map<String, Set<String>> fins = new HashMap<>();
	        fins.put("NuevaActividadAFinalizar", clase);
	        assertEquals(socio.getClasesDeActividadesFinalizadas(), fins);
	        DtSocioExt caroper = (DtSocioExt) IUC.seleccionarUsuario("caro\uEAEA");
	        assertEquals(caroper.getNickname(),"caro");
	        assertEquals(caroper.getApellido(),"Omega");
	        assertEquals(caroper.getClasesDeActividadesFinalizadas(),fins);
	        DtProfesorExt viktorper = (DtProfesorExt) IUC.seleccionarUsuario("Administrador\uEAEA");
	        Map<String,TEstado> m = new HashMap<>();
	        m.put("NuevaActividadAFinalizar", TEstado.finalizada);
	        assertEquals(DataPersistencia.getInstance().obtenerClases("NuevaActividadAFinalizar").contains("PPsas67"), true);
	        assertEquals(DataPersistencia.getInstance().obtenerClases().contains("PPsas67"), true);
	        assertEquals(viktorper.getHistoralActDepIngresadas(),m);
    	}catch(Exception e) {
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    	DataPersistencia.getInstance().nuketownDetonator(); 
    }   
}