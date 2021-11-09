
package webservices;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IOException_QNAME = new QName("http://webServices/", "IOException");
	private final static QName _CuponeraNoExisteException_QNAME = new QName("http://webServices/", "CuponeraNoExisteException");
	private final static QName _NoExisteCuponeraException_QNAME = new QName("http://webServices/", "NoExisteCuponeraException");
	private final static QName _ActividadDeportivaException_QNAME = new QName("http://webServices/", "ActividadDeportivaException");
    private final static QName _FechaInvalidaException_QNAME = new QName("http://webServices/", "FechaInvalidaException");
	private final static QName _ClaseException_QNAME = new QName("http://webServices/", "ClaseException");
    private final static QName _InstitucionException_QNAME = new QName("http://webServices/", "InstitucionException");

    private final static QName _UsuarioNoExisteException_QNAME = new QName("http://webServices/", "UsuarioNoExisteException");
	private final static QName _DtCapsula_QNAME = new QName("http://webServices/", "dtCapsula");

	/**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

	/**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
//    @XmlElementDecl(namespace = "http://webServices/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

	/**
     * Create an instance of {@link DtCapsula }
     * 
     */
    public DtCapsula createDtCapsula() {
        return new DtCapsula();
    }

	/**
     * Create an instance of {@link UsuarioNoExisteException }
     * 
     */
    public UsuarioNoExisteException createUsuarioNoExisteException() {
        return new UsuarioNoExisteException();
    }

	/**
     * Create an instance of {@link NoExisteCuponeraException }
     * 
     */
    public NoExisteCuponeraException createNoExisteCuponeraException() {
        return new NoExisteCuponeraException();
    }

	/**
     * Create an instance of {@link DtCuponeraWS }
     * 
     */
    public DtCuponeraWS createDtCuponeraWS() {
        return new DtCuponeraWS();
    }

	/**
     * Create an instance of {@link DtClasesCuponeraWS }
     * 
     */
    public DtClasesCuponeraWS createDtClasesCuponeraWS() {
        return new DtClasesCuponeraWS();
    }

	/**
     * Create an instance of {@link InstitucionException }
     * 
     */
    public InstitucionException createInstitucionException() {
        return new InstitucionException();
    }

    /**
     * Create an instance of {@link CuponeraNoExisteException }
     * 
     */
    public CuponeraNoExisteException createCuponeraNoExisteException() {
        return new CuponeraNoExisteException();
    }

	/**
     * Create an instance of {@link ActividadDeportivaException }
     * 
     */
    public ActividadDeportivaException createActividadDeportivaException() {
        return new ActividadDeportivaException();
    }

    /**
     * Create an instance of {@link FechaInvalidaException }
     * 
     */
    public FechaInvalidaException createFechaInvalidaException() {
        return new FechaInvalidaException();
    }

	/**
     * Create an instance of {@link ClaseException }
     * 
     */
    public ClaseException createClaseException() {
        return new ClaseException();
    }

    /**
     * Create an instance of {@link DtProfesorWS }
     * 
     */
    public DtProfesorWS createDtProfesorWS() {
        return new DtProfesorWS();
    }

	/**
     * Create an instance of {@link DtActividadWS }
     * 
     */
    public DtActividadWS createDtActividadWS() {
        return new DtActividadWS();
    }

    /**
     * Create an instance of {@link DtClaseWS }
     * 
     */
    public DtClaseWS createDtClaseWS() {
        return new DtClaseWS();
    }

    /**
     * Create an instance of {@link DtFechaWS }
     * 
     */
    public DtFechaWS createDtFechaWS() {
        return new DtFechaWS();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExisteCuponeraException }{@code >}}
     * 
     */
////    @XmlElementDecl(namespace = "http://webServices/", name = "NoExisteCuponeraException")
//    @XmlElementDecl(namespace = "http://webServices/", name = "NoExisteCuponeraException")
	public JAXBElement<NoExisteCuponeraException> createNoExisteCuponeraException(NoExisteCuponeraException value) {
        return new JAXBElement<NoExisteCuponeraException>(_NoExisteCuponeraException_QNAME, NoExisteCuponeraException.class, null, value);
    }

	/**
     * Create an instance of {@link DtPremioWS }
     * 
     */
    public DtPremioWS createDtPremioWS() {
        return new DtPremioWS();
    }

    /**
     * Create an instance of {@link DtSocioWS }
     * 
     */
    public DtSocioWS createDtSocioWS() {
        return new DtSocioWS();
    }

	/**
     * Create an instance of {@link JAXBElement }{@code <}{@link CuponeraNoExisteException }{@code >}}
     * 
     */
////    @XmlElementDecl(namespace = "http://webServices/", name = "CuponeraNoExisteException")
//    @XmlElementDecl(namespace = "http://webServices/", name = "CuponeraNoExisteException")
	public JAXBElement<CuponeraNoExisteException> createCuponeraNoExisteException(CuponeraNoExisteException value) {
        return new JAXBElement<CuponeraNoExisteException>(_CuponeraNoExisteException_QNAME, CuponeraNoExisteException.class, null, value);
    }

	/**
     * Create an instance of {@link DtInstitucionWS }
     * 
     */
    public DtInstitucionWS createDtInstitucionWS() {
        return new DtInstitucionWS();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActividadDeportivaException }{@code >}}
     * 
     */
//////    @XmlElementDecl(namespace = "http://webServices/", name = "ActividadDeportivaException")
////    @XmlElementDecl(namespace = "http://webServices/", name = "ActividadDeportivaException")
//	@XmlElementDecl(namespace = "http://webServices/", name = "ActividadDeportivaException")
	@XmlElementDecl(namespace = "http://webServices/", name = "ActividadDeportivaException")
	public JAXBElement<ActividadDeportivaException> createActividadDeportivaException(ActividadDeportivaException value) {
        return new JAXBElement<ActividadDeportivaException>(_ActividadDeportivaException_QNAME, ActividadDeportivaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FechaInvalidaException }{@code >}}
     * 
     */
//    @XmlElementDecl(namespace = "http://webServices/", name = "FechaInvalidaException")
    public JAXBElement<FechaInvalidaException> createFechaInvalidaException(FechaInvalidaException value) {
        return new JAXBElement<FechaInvalidaException>(_FechaInvalidaException_QNAME, FechaInvalidaException.class, null, value);
    }

	/**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClaseException }{@code >}}
     * 
     */
////    @XmlElementDecl(namespace = "http://webServices/", name = "ClaseException")
//    @XmlElementDecl(namespace = "http://webServices/", name = "ClaseException")
	@XmlElementDecl(namespace = "http://webServices/", name = "ClaseException")
	public JAXBElement<ClaseException> createClaseException(ClaseException value) {
        return new JAXBElement<ClaseException>(_ClaseException_QNAME, ClaseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstitucionException }{@code >}}
     * 
     */
////    @XmlElementDecl(namespace = "http://webServices/", name = "InstitucionException")
//    @XmlElementDecl(namespace = "http://webServices/", name = "InstitucionException")
	@XmlElementDecl(namespace = "http://webServices/", name = "InstitucionException")
	public JAXBElement<InstitucionException> createInstitucionException(InstitucionException value) {
        return new JAXBElement<InstitucionException>(_InstitucionException_QNAME, InstitucionException.class, null, value);
    }

	/**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}}
     * 
     */
////    @XmlElementDecl(namespace = "http://webServices/", name = "UsuarioNoExisteException")
//    @XmlElementDecl(namespace = "http://webServices/", name = "UsuarioNoExisteException")
	public JAXBElement<UsuarioNoExisteException> createUsuarioNoExisteException(UsuarioNoExisteException value) {
        return new JAXBElement<UsuarioNoExisteException>(_UsuarioNoExisteException_QNAME, UsuarioNoExisteException.class, null, value);
    }

	/**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtCapsula }{@code >}}
     * 
     */
////    @XmlElementDecl(namespace = "http://webServices/", name = "dtCapsula")
//    @XmlElementDecl(namespace = "http://webServices/", name = "dtCapsula")
	public JAXBElement<DtCapsula> createDtCapsula(DtCapsula value) {
        return new JAXBElement<DtCapsula>(_DtCapsula_QNAME, DtCapsula.class, null, value);
    }

}
