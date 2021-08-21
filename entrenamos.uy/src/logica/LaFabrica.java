package logica;

public class LaFabrica {

	private static LaFabrica instancia = null;

	public static LaFabrica getInstance(){
		if (instancia == null)
			instancia = new LaFabrica();
		return instancia;
	}
	
	public IActividadDeportivaController obtenerIActDeportivaController() {
		IActividadDeportivaController iact = ActividadDeportivaController.getInstance();
		return iact;
	}
	
	public IUsuarioController obtenerIUsuarioController() {
		IUsuarioController iusu = UsuarioController.getInstance();
		return iusu;
	}
	
    public IDictadoClaseController obtenerIDictadoClaseController(){
    	IDictadoClaseController idic = DictadoClaseControlle.getInstance();
    	return idic;
     }
    
    public IDeportivaController obtenerIDeportivaController() {
    	IDeportivaController idep = DeportivaController.getInstance();
    	return idep;
     }

}
