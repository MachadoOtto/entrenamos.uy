package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

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
	public static String logthreshold = null;
    public ConfigListener() { }

    public void contextDestroyed(ServletContextEvent sce)  { }

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext ctx = sce.getServletContext();
    	usuarioController = ctx.getInitParameter("usuarioControllerURL");
    	actividadController = ctx.getInitParameter("actividadControllerURL");
    	claseController = ctx.getInitParameter("claseControllerURL");
    	contentController = ctx.getInitParameter("contentControllerURL");
    	cuponeraController = ctx.getInitParameter("cuponeraControllerURL");
    	logthreshold = ctx.getInitParameter("logthreshold");
    	cargarConfig(sce);
    }

    
    public static void cargarConfig(ServletContextEvent sce) {
    	String home = System.getProperty("user.home");
    	File cfgfolder = new File(home + "/.entrenamosUy");
    	Properties config = new Properties();
    	config.setProperty("usuarioControllerURL", usuarioController);
    	config.setProperty("actividadControllerURL", actividadController);
    	config.setProperty("claseControllerURL", claseController);
    	config.setProperty("contentControllerURL", contentController);
    	config.setProperty("cuponeraControllerURL", cuponeraController);
    	config.setProperty("logthreshold", logthreshold);
    	System.out.println("th"+logthreshold);
    	if(cfgfolder.mkdir()) {
    		System.out.println("Config folder was not found... creating default config folder at "+home);
        	try {
				config.store(new FileOutputStream(home+"/.entrenamosUy/servidorWeb.properties"), null);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	else {
	    	File prp = new File(home+"/.entrenamosUy/servidorWeb.properties");
	    	if(!(prp.exists())) {
	    		System.out.println("Config file was not found... generating default config at "+prp);
	    		try {
					config.store(new FileOutputStream(home+"/.entrenamosUy/servidorWeb.properties"), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
    	}
    	config = new Properties();
    	try {
			config.load(new FileInputStream(home+"/.entrenamosUy/servidorWeb.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	usuarioController = config.getProperty("usuarioControllerURL");
    	actividadController = config.getProperty("actividadControllerURL");
    	claseController = config.getProperty("claseControllerURL");
    	contentController = config.getProperty("contentControllerURL");
    	cuponeraController = config.getProperty("cuponeraControllerURL");
    	logthreshold = config.getProperty("logthreshold");
    	ServletContext ctx = sce.getServletContext();
    	if(usuarioController==null)
    		usuarioController = ctx.getInitParameter("usuarioControllerURL");
    	if(actividadController==null)
    		actividadController = ctx.getInitParameter("actividadControllerURL");
    	if(claseController==null)
    		claseController = ctx.getInitParameter("claseControllerURL");
    	if(contentController==null)
    		contentController = ctx.getInitParameter("contentControllerURL");
    	if(cuponeraController==null)
    		cuponeraController = ctx.getInitParameter("cuponeraControllerURL");
    	if(logthreshold==null) {
        	logthreshold = ctx.getInitParameter("logthreshold");
    	}
    	System.out.println("Configuration loaded successfully.");
    }
}
