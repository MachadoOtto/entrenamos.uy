package models;

import datatypes.DtUsuarioExt;
import excepciones.UsuarioNoExisteException;

public class GestorWeb {
	private static GestorWeb instancia;
	private static IActividadDeportivaController IADC;
	private static IUsuarioController IUC;
	private static ICuponeraController ICC;
	private static IDictadoClaseController IDCC;
	private static IContentController ICOC;
	
	public static GestorWeb getInstance() {
		if (instancia == null)
			instancia = new GestorWeb();
		return instancia;
	}
	
	private GestorWeb() {
		LaFabricaWS fabricaSistema = LaFabricaWS.getInstance();
		IADC = fabricaSistema.obtenerIActDeportivaController();
		IUC = fabricaSistema.obtenerIUsuarioController();
		ICC = fabricaSistema.obtenerICuponeraController();
		IDCC = fabricaSistema.obtenerIDictadoClaseController();
		ICOC = fabricaSistema.obtenerIContentController();
	}

    public static DtUsuarioExt buscarUsuario(String nickEmail) throws UsuarioNoExisteException {
        DtUsuarioExt res;
        try {
            res = IUC.seleccionarUsuario(nickEmail);
        } catch (UsuarioNoExisteException e) {
            res = IUC.seleccionarUsuarioEmail(nickEmail);
        }
        return res;
    }
    
    //Las siguientes funciones te parmiten escribir menos al invocar funciones del modelo (lógica)
    public static IActividadDeportivaController getIADC() {
    	return IADC;
    }
    public static IUsuarioController getIUC() {
    	return IUC;
    }
    public static ICuponeraController getICC() {
    	return ICC;
    }
    public static IDictadoClaseController getIDCC() {
    	return IDCC;
    }

	public static IContentController getICOC() {
		return ICOC;
	}
}