package logica;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.DtFecha;
import datatypes.DtSocio;
import datatypes.DtUsuario;
import datatypes.DtInstitucion;

//import excepciones.UsuarioNoExisteException;
//import excepciones.UsuarioRepetidoException;

import excepciones.InstitucionException;

import logica.LaFabrica;
import logica.IActividadDeportivaController;
import logica.IUsuarioController;
import logica.ICuponeraController;
import logica.IDictadoClaseController;

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
	}

	@Test
	void testAltaInstitucionesOk() {
		try {
			// ALTA INSTITUCIONES
			// Instituto Natural #IN
			IADC.altaInstitucion("Instituto Natural","https://www.inatural.com", "Clases de gimnasia, aeróbica, spinning y yoga.");
			DtInstitucion data = IADC.obtenerDatosInstitucion("Instituto Natural");
			assertEquals(data.getNombre(), "Instituto Natural");
			assertEquals(data.getDescripcion(), "Clases de gimnasia, aeróbica, spinning y yoga.");
			assertEquals(data.getURL(), "https://www.inatural.com");
		} catch (InstitucionException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		/*// Fuerza Bruta #FB
		IADC.altaInstitucion("Fuerza Bruta", "Gimnasio especializado en el desarrollo de la musculatura.","https://www.musculos.com");
		// Telón #TL
		IADC.altaInstitucion("Telón", "Actividades deportivas para todas las edades.","https://telon.com.uy");
		// Olympic #YT
		IADC.altaInstitucion("Olympic", "Gimnasia y Aparatos.","https://www.olympic21.com"); */
	}
	
	/*
	@Test
	void testAltaSociosOk() {
		try {
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
			DtUsuario du;
			assertEquals(du.getNombre(), "Rodrigo");
			assertEquals(du.getApellido(), "Quinta");
			assertEquals(du.getCedulaIdentidad(), "12345678");
		} catch (UsuarioRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
		
	

	
	@Test
	void testRegistrarUsuarioRepetido() {
		try {
			ctrlU.registrarUsuario("Rodrigo1", "Quinta2", "87654321");
		} catch (UsuarioRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(UsuarioRepetidoException.class, () -> {ctrlU.registrarUsuario("Rodrigo1", "Quinta2", "87654321");});
	}


	@Test
	void testVerInfoUsuario() {
		fail("Not yet implemented");
	}

	@Test
	void testGetUsuarios() {
		fail("Not yet implemented");
	}
	/*public Set<String> obtenerInstituciones();

	public Set<String> obtenerUsuarios();
	
	public int ingresarDatosUsuario(DtUsuario datoUser);
	
	public DtUsuario seleccionarUsuario(String userNick);
	
	public void editarDatosBasicos(String userNick, DtUsuario datoUser);*/
} 
