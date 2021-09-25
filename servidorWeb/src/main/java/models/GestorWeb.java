package models;

import java.util.Set;

import logica.LaFabrica;
import logica.IUsuarioController;
import logica.IActividadDeportivaController;
import datatypes.DtFecha;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtUsuarioExt;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;

public class GestorWeb {
	private static GestorWeb instancia;
	
	private LaFabrica fabricaSistema;
	
	/**
	 * Da la instancia del singleton
	 * @return 
	 */
	public static GestorWeb getInstance() {
		if(instancia == null)
			instancia = new GestorWeb();
		return instancia;
	}
	
	/**
	 * Crea un conjunto de usuarios aleatorios
	 */
	private GestorWeb() {
		fabricaSistema = LaFabrica.getInstance();
		IActividadDeportivaController IADC = fabricaSistema.obtenerIActDeportivaController();
		IUsuarioController IUC = fabricaSistema.obtenerIUsuarioController();
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
			IUC.ingresarDatosUsuario(new DtSocio("Emi71","Emiliano","Lucas","emi71@gmail.com", "asdfg456", new DtFecha(1971,12,31,0,0,0), null));
			// caro #CO
			IUC.ingresarDatosUsuario(new DtSocio("caro","Carolina","Omega","caro@gmail.com", "123rtgfdv", new DtFecha(1983,11,15,0,0,0), null));
			// euge #EW
			IUC.ingresarDatosUsuario(new DtSocio("euge","Eugenia","Williams","e.will@gmail.com", "poiuy086", new DtFecha(1990,4,15,0,0,0), null));
			// guille #GH
			IUC.ingresarDatosUsuario(new DtSocio("guille","Guillermo","Hector","ghector@gmail.com", "GTO468", new DtFecha(1959,5,15,0,0,0), null));
			// sergiop #SP
			IUC.ingresarDatosUsuario(new DtSocio("sergiop","Sergio","Perez","sergi@gmail.com.uy", "HGF135", new DtFecha(1950,1,28,0,0,0), null));
			// andy #AR
			IUC.ingresarDatosUsuario(new DtSocio("andy","Andrés","Roman","chino@gmail.org.uy", "lkj65D", new DtFecha(1976,3,17,0,0,0), null));
			// tonyp #AP
			IUC.ingresarDatosUsuario(new DtSocio("tonyp","Antonio","Paz","eltony@gmail.org.uy", "jhvf395", new DtFecha(1955,2,14,0,0,0), null));
			// m1k4 #ML
			IUC.ingresarDatosUsuario(new DtSocio("m1k4","Micaela","Lopez","mika@gmail.com.ar", "ijngr024", new DtFecha(1987,2,23,0,0,0), null));
			// charly #CB
			IUC.ingresarDatosUsuario(new DtSocio("charly","Carlos","Boston","charly@gmail.com.uy", "987mnbgh", new DtFecha(1937,5,8,0,0,0), null));	
			
			// PROFESORES
			String desc;
			String bio;
			// viktor #VP
			desc = "Victor es un apasionado de los músculos. Sus clases son organizadas en función de distintos "
					+ "aparatos y pesas con el objetivo de desarrollar músculos.";
			bio = "Victor nació en Moscow en 1977. En el año 2005 emigró a Uruguay luego de quedar "
					+ "encantado con el país en un viaje turístico.";
			IUC.ingresarDatosUsuario(new DtProfesor("viktor","Victor","Perez","vperez@fuerza.com", "lkj34df", new DtFecha(1997,1,1,0,0,0),
					"Fuerza Bruta", desc, bio ,"www.vikgym.com", null));
			// denis #DM
			desc = "A Denis le interesan los deportes con pelota, principalmente el voleibol y el handball.";
			bio = "Denis fue un jugador de voleibol profesional.";
			IUC.ingresarDatosUsuario(new DtProfesor("denis","Denis","Miguel","den80@fuerza.com", "poke579", new DtFecha(1980,6,14,0,0,0),
					"Telón", desc, bio ,"www.depecho.com", null));
			// clazar #CL
			desc = "Carlos es un profesor muy divertido cuyas clases de aeróbica están cargadas de energía.";
			bio = "El interés por la actividad física llevo a Carlos a dejar su trabajo en un estudio "
					+ "contable y abrir su propio gimnasio.";
			IUC.ingresarDatosUsuario(new DtProfesor("clazar","Carlos","Lazaro","claz4r0@hotmail.com", "mkji648", new DtFecha(1953,6,22,0,0,0),
					"Instituto Natural", desc, bio ,"www.enforma.com", null));
			// TheBoss #BS
			desc = "Bruno es un ex-boxeardor que busca entrenar a futuros campeones.";
			bio = "Bruno, mejor conocido como Bruce en el ring, compitió como boxeador entre los años 60s y 70s.";
			IUC.ingresarDatosUsuario(new DtProfesor("TheBoss","Bruno","Sosa","bruceTheBoss@gmail.com", "fcku0123", new DtFecha(1949,9,23,0,0,0),
					"Fuerza Bruta", desc, bio ,"www.bruce.net", null));
			// Nelson #TN
			desc = "Profesor de natación. Especializado en braza y mariposa.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("Nelson","Luis","Nelson","nelson@hotmail.com", "vbmn4r", new DtFecha(1998,1,1,0,0,0),
					"Telón", desc, bio ,"www.nelson.uy", null));
			// lale #LL
			desc = "Luego de una exitosa carrera como jugadora de futbol profesional. Laura dedica sus clases a "
					+ "enseñar tácticas de futbol.";
			bio = "Jugadora profesional de futbol desde 2010 a 2020.";
			IUC.ingresarDatosUsuario(new DtProfesor("lale","Laura","Leyes","la_le@outlook.com", "ncnl123", new DtFecha(1987,2,14,0,0,0),
					"Telón", desc, bio ,"www.laley.com", null));
			// prisc #PI
			desc = "Laura tiene un gran interés por los deportes olímpicos.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("prisc","Priscila","Pappo","pripa@gmail.com", "mny101", new DtFecha(1981,8,13,0,0,0),
					"Olympic", desc, bio ,"www.pi314.net", null));
			// dagost #DY
			desc = "Profesora dedicada y exigente. No acepta un " + '"' + "no puedo" + '"' + " como respuesta.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("dagost","Daiana","Agostini","d_1940_ago@gmail.com", "1o1vbm", new DtFecha(1940,3,5,0,0,0),
					"Olympic", desc, bio ,"www.dygym.com", null));
			// aldo #AL
			desc = "Dada su gran estatura Aldo siempre jugó al basquetbol, hoy se dedica a enseñarlo.";
			bio = "";
			IUC.ingresarDatosUsuario(new DtProfesor("aldo","Aldo","Vivaldi","aldo@outlook.com", "ultraton01", new DtFecha(1952,7,17,0,0,0),
					"Telón", desc, bio ,"www.sportsaldo.net", null));
		} catch (InstitucionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Devuelven los usuarios
	 * @return 
	 */
	public Set<String> getUsuarios() {
		return fabricaSistema.obtenerIUsuarioController().obtenerUsuarios();
	}
	
	/**
	 * Busca un usuario
	 * @param email
	 * @return
	 * @throws Exception 
	 */
	
    public DtUsuarioExt buscarUsuario(String nickEmail) throws UsuarioNoExisteException {
        DtUsuarioExt res;
        try {
            res = fabricaSistema.obtenerIUsuarioController().seleccionarUsuario(nickEmail);
        } catch (UsuarioNoExisteException e) {
            res = fabricaSistema.obtenerIUsuarioController().seleccionarUsuarioEmail(nickEmail);
        }
        return res;
    }
}