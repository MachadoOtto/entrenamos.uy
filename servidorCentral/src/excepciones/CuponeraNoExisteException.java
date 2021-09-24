package excepciones;

/**
 * Excepción utilizada para indicar  que la clase seleccionada esta llena.
 */

@SuppressWarnings("serial")
public class CuponeraNoExisteException extends Exception {

    public CuponeraNoExisteException(String string) {
        super(string);
    }
}