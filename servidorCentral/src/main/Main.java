package main;

import server.WebServerService;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO hacer que dependiendo de los args provistos que se debe ejecutar
    	
        WebServerService servicio = new WebServerService();
        servicio.publicar();
    }

}
