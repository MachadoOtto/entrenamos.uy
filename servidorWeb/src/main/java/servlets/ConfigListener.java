package servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConfigListener implements ServletContextListener {
	public static String usuarioController = null;
	public static String actividadController = null;
	public static String claseController = null;
	public static String contentController = null;
	public static String cuponeraController = null;
    public ConfigListener() { }

    public void contextDestroyed(ServletContextEvent sce)  { }

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext ctx = sce.getServletContext();
    	usuarioController = ctx.getInitParameter("usuarioControllerURL");
    	actividadController = ctx.getInitParameter("actividadControllerURL");
    	claseController = ctx.getInitParameter("claseControllerURL");
    	contentController = ctx.getInitParameter("contentControllerURL");
    	cuponeraController = ctx.getInitParameter("cuponeraControllerURL");
    	System.out.println("Webservice location read from context successfully.");
    }
	
}
