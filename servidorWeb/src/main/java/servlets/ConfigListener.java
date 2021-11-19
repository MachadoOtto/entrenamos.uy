package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ConfigListener implements ServletContextListener {
	public static Properties cfg = new Properties();
    public ConfigListener() { }

    public void contextDestroyed(ServletContextEvent sce)  { }

    public void contextInitialized(ServletContextEvent sce)  { 
    	cargarConfig(sce);
    }

    
    public static void cargarConfig(ServletContextEvent sce) {
    	ServletContext ctx = sce.getServletContext();
    	String home = System.getProperty("user.home");
    	File cfgfolder = new File(home + "/.entrenamosUy");
    	Properties config = cfg;
    	config.setProperty("usuarioControllerURL", ctx.getInitParameter("usuarioControllerURL"));
    	config.setProperty("actividadControllerURL", ctx.getInitParameter("actividadControllerURL"));
    	config.setProperty("claseControllerURL", ctx.getInitParameter("claseControllerURL"));
    	config.setProperty("contentControllerURL", ctx.getInitParameter("contentControllerURL"));
    	config.setProperty("cuponeraControllerURL", ctx.getInitParameter("cuponeraControllerURL"));
    	config.setProperty("logthreshold", ctx.getInitParameter("logthreshold"));
    	config.setProperty("contentCacheAge", ctx.getInitParameter("contentCacheAge"));
    	config.setProperty("cookieMaxAge", ctx.getInitParameter("cookieMaxAge"));
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
			for(Entry<Object, Object> x: config.entrySet()) {
				if(x.getValue()==null)
					config.setProperty((String) x.getKey(), ctx.getInitParameter((String) x.getKey()));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}    	

    	cfg = config;
    	System.out.println("Configuration loaded successfully.");
    }
}
