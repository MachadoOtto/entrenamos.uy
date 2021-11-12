package webServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Endpoint;

import datatypes.DtFecha;
import datatypesWS.DtFechaWS;
import datatypesWS.LogEntryWS;
import logica.HandlerLogs;
import logica.IActividadDeportivaController;
import logica.LaFabrica;
import main.Main;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSContentController {
	private String contentRootPath = Main.config.getProperty("assetfolderPath");
	@SuppressWarnings("serial")
	Map<String,String> typeMap = new HashMap<String,String>(){{
	    this.put("cla", "images/classes/");
	    this.put("cup", "images/cups/");
	    this.put("act", "images/activities/");
	    this.put("usu", "images/users/");
	}};
	private Endpoint endpoint = null;

	public WSContentController(){}
	
    @WebMethod(exclude = true)
    public void publicar(){
    	Properties prp = Main.config;
    	endpoint = Endpoint.publish("http://"+prp.getProperty("hostIP")+":"+prp.getProperty("hostPort")+prp.getProperty("contentController_ServiceName"), this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
    	return endpoint;
    }
    
    @WebMethod
    public byte[] get(String type, String id) throws IOException {    	
    	byte[] byteArray = null;
		try {
		    File f = new File(contentRootPath + typeMap.get(type) + id);
		    FileInputStream streamer = new FileInputStream(f);
		    byteArray = new byte[streamer.available()];
		    streamer.read(byteArray);
		    streamer.close();
		} catch (IOException e) {
		    throw e;
		}
		return byteArray;
    }
    
    @WebMethod
    public void post(String type, String id, byte[] content) throws IOException {
		try {
		    File f = new File(contentRootPath + typeMap.get(type) + id);
		    FileOutputStream streamer = new FileOutputStream(f);
		    streamer.write(content);
		    streamer.close();
		} catch (IOException e) {
		    throw e;
		}
    }
    
    @WebMethod
    public void sendReports(LogEntryWS[] entries) {
    	LaFabrica.getInstance().getILogger().addLogs(entries);
    }
}
