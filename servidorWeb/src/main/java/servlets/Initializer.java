package servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import models.GestorWeb;

@WebListener
public class Initializer implements ServletContextListener{
	
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ENTRENAMOS.UY SERVLET INITIALIZED");
        GestorWeb.getInstance();
        System.out.println("The default model has been loaded successfully.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("SHUTTING DOWN ENTRENAMOS.UY WEBSERVER");
    }
}
