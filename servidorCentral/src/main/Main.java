package main;

import webServices.WSUsuarioController;
import test.TestCasos;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO hacer que dependiendo de los args provistos que se debe ejecutar
    	
    	TestCasos.iniciar();
    	
        WSUsuarioController servicio = new WSUsuarioController();
        servicio.publicar();
    }

}
