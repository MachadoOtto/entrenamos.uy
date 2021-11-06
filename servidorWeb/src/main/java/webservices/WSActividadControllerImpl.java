
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package webservices;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.9
 * 2021-11-06T19:15:26.496-03:00
 * Generated source version: 2.7.9
 * 
 */

@javax.jws.WebService(
                      serviceName = "WSActividadControllerService",
                      portName = "WSActividadControllerPort",
                      targetNamespace = "http://webServices/",
                      wsdlLocation = "http://localhost:9129/entrenamosuy/actividadController?wsdl",
                      endpointInterface = "webservices.WSActividadController")
                      
public class WSActividadControllerImpl implements WSActividadController {

    private static final Logger LOG = Logger.getLogger(WSActividadControllerImpl.class.getName());

    /* (non-Javadoc)
     * @see webservices.WSActividadController#seleccionarClase(java.lang.String  arg0 ,)java.lang.String  arg1 ,)java.lang.String  arg2 )*
     */
    public webservices.DtClaseWS seleccionarClase(java.lang.String arg0,java.lang.String arg1,java.lang.String arg2) throws ActividadDeportivaException_Exception , ClaseException_Exception , InstitucionException_Exception    { 
        LOG.info("Executing operation seleccionarClase");
        System.out.println(arg0);
        System.out.println(arg1);
        System.out.println(arg2);
        try {
            webservices.DtClaseWS _return = new webservices.DtClaseWS();
            java.util.List<java.lang.String> _returnAlumnos = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnAlumnosVal1 = "_returnAlumnosVal1525626485";
            _returnAlumnos.add(_returnAlumnosVal1);
            _return.getAlumnos().addAll(_returnAlumnos);
            java.util.List<java.lang.Integer> _returnCalificacionesData = new java.util.ArrayList<java.lang.Integer>();
            java.lang.Integer _returnCalificacionesDataVal1 = Integer.valueOf(427319287);
            _returnCalificacionesData.add(_returnCalificacionesDataVal1);
            _return.getCalificacionesData().addAll(_returnCalificacionesData);
            java.util.List<java.lang.String> _returnCalificacionesHead = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnCalificacionesHeadVal1 = "_returnCalificacionesHeadVal-2116468051";
            _returnCalificacionesHead.add(_returnCalificacionesHeadVal1);
            _return.getCalificacionesHead().addAll(_returnCalificacionesHead);
            _return.setCorreoProfesor("CorreoProfesor522077948");
            webservices.DtFechaWS _returnFechaClase = new webservices.DtFechaWS();
            _returnFechaClase.setAnio(-515050492);
            _returnFechaClase.setMes(-1009631093);
            _returnFechaClase.setDia(-933943973);
            _returnFechaClase.setHoras(-1874518852);
            _returnFechaClase.setMinutos(1122723675);
            _returnFechaClase.setSegundos(-2051322133);
            _return.setFechaClase(_returnFechaClase);
            webservices.DtFechaWS _returnFechaRegistro = new webservices.DtFechaWS();
            _returnFechaRegistro.setAnio(76350828);
            _returnFechaRegistro.setMes(-1937020044);
            _returnFechaRegistro.setDia(-1524106752);
            _returnFechaRegistro.setHoras(-665510520);
            _returnFechaRegistro.setMinutos(584180170);
            _returnFechaRegistro.setSegundos(359804826);
            _return.setFechaRegistro(_returnFechaRegistro);
            _return.setImgName("ImgName-1934759474");
            _return.setMaxSocios(-1026336717);
            _return.setMinSocios(222567024);
            _return.setNicknameProfesor("NicknameProfesor-1550023115");
            _return.setNombre("Nombre475648501");
            webservices.DtPremioWS _returnPremio = new webservices.DtPremioWS();
            _returnPremio.setDescripcion("Descripcion-702106929");
            _returnPremio.setCantidad(-1946105080);
            webservices.DtFechaWS _returnPremioFechaSorteo = new webservices.DtFechaWS();
            _returnPremioFechaSorteo.setAnio(549362866);
            _returnPremioFechaSorteo.setMes(450773060);
            _returnPremioFechaSorteo.setDia(81497927);
            _returnPremioFechaSorteo.setHoras(-856183851);
            _returnPremioFechaSorteo.setMinutos(-269792777);
            _returnPremioFechaSorteo.setSegundos(-1446651222);
            _returnPremio.setFechaSorteo(_returnPremioFechaSorteo);
            java.util.List<java.lang.String> _returnPremioGanadores = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnPremioGanadoresVal1 = "_returnPremioGanadoresVal-1972859198";
            _returnPremioGanadores.add(_returnPremioGanadoresVal1);
            _returnPremio.getGanadores().addAll(_returnPremioGanadores);
            _return.setPremio(_returnPremio);
            java.util.List<java.lang.String> _returnSoloNickAlumnos = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnSoloNickAlumnosVal1 = "_returnSoloNickAlumnosVal1791204563";
            _returnSoloNickAlumnos.add(_returnSoloNickAlumnosVal1);
            _return.getSoloNickAlumnos().addAll(_returnSoloNickAlumnos);
            _return.setUrlVideo("UrlVideo-503733064");
            _return.setUrlwebsite("Urlwebsite120838393");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ActividadDeportivaException_Exception("ActividadDeportivaException...");
        //throw new ClaseException_Exception("ClaseException...");
        //throw new InstitucionException_Exception("InstitucionException...");
    }

    /* (non-Javadoc)
     * @see webservices.WSActividadController#obtenerCategorias(*
     */
    public net.java.dev.jaxb.array.StringArray obtenerCategorias() { 
        LOG.info("Executing operation obtenerCategorias");
        try {
            net.java.dev.jaxb.array.StringArray _return = new net.java.dev.jaxb.array.StringArray();
            java.util.List<java.lang.String> _returnItem = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnItemVal1 = "_returnItemVal-1875344875";
            _returnItem.add(_returnItemVal1);
            _return.getItem().addAll(_returnItem);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see webservices.WSActividadController#obtenerActividades(java.lang.String  arg0 )*
     */
    public net.java.dev.jaxb.array.StringArray obtenerActividades(java.lang.String arg0) throws InstitucionException_Exception    { 
        LOG.info("Executing operation obtenerActividades");
        System.out.println(arg0);
        try {
            net.java.dev.jaxb.array.StringArray _return = new net.java.dev.jaxb.array.StringArray();
            java.util.List<java.lang.String> _returnItem = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnItemVal1 = "_returnItemVal-488751332";
            _returnItem.add(_returnItemVal1);
            _return.getItem().addAll(_returnItem);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new InstitucionException_Exception("InstitucionException...");
    }

    /* (non-Javadoc)
     * @see webservices.WSActividadController#obtenerActDepIngresadas(*
     */
    public net.java.dev.jaxb.array.StringArray obtenerActDepIngresadas() { 
        LOG.info("Executing operation obtenerActDepIngresadas");
        try {
            net.java.dev.jaxb.array.StringArray _return = new net.java.dev.jaxb.array.StringArray();
            java.util.List<java.lang.String> _returnItem = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnItemVal1 = "_returnItemVal92089098";
            _returnItem.add(_returnItemVal1);
            _return.getItem().addAll(_returnItem);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see webservices.WSActividadController#obtenerDatosInstitucion(java.lang.String  arg0 )*
     */
    public webservices.DtInstitucion obtenerDatosInstitucion(java.lang.String arg0) throws InstitucionException_Exception    { 
        LOG.info("Executing operation obtenerDatosInstitucion");
        System.out.println(arg0);
        try {
            webservices.DtInstitucion _return = new webservices.DtInstitucion();
            _return.setNombre("Nombre-494151955");
            _return.setDescripcion("Descripcion327052665");
            _return.setUrl("Url1848197260");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new InstitucionException_Exception("InstitucionException...");
    }

    /* (non-Javadoc)
     * @see webservices.WSActividadController#obtenerDeltaInstituciones(java.lang.String  arg0 ,)java.lang.String  arg1 )*
     */
    public net.java.dev.jaxb.array.StringArray obtenerDeltaInstituciones(java.lang.String arg0,java.lang.String arg1) throws InstitucionException_Exception    { 
        LOG.info("Executing operation obtenerDeltaInstituciones");
        System.out.println(arg0);
        System.out.println(arg1);
        try {
            net.java.dev.jaxb.array.StringArray _return = new net.java.dev.jaxb.array.StringArray();
            java.util.List<java.lang.String> _returnItem = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnItemVal1 = "_returnItemVal643845941";
            _returnItem.add(_returnItemVal1);
            _return.getItem().addAll(_returnItem);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new InstitucionException_Exception("InstitucionException...");
    }

    /* (non-Javadoc)
     * @see webservices.WSActividadController#getActDepExt(java.lang.String  arg0 ,)java.lang.String  arg1 )*
     */
    public webservices.DtActividadWS getActDepExt(java.lang.String arg0,java.lang.String arg1) throws ActividadDeportivaException_Exception , InstitucionException_Exception    { 
        LOG.info("Executing operation getActDepExt");
        System.out.println(arg0);
        System.out.println(arg1);
        try {
            webservices.DtActividadWS _return = new webservices.DtActividadWS();
            java.util.List<java.lang.String> _returnClases = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnClasesVal1 = "_returnClasesVal926302829";
            _returnClases.add(_returnClasesVal1);
            _return.getClases().addAll(_returnClases);
            java.util.List<java.lang.String> _returnCup = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnCupVal1 = "_returnCupVal614044541";
            _returnCup.add(_returnCupVal1);
            _return.getCup().addAll(_returnCup);
            java.util.List<java.lang.String> _returnCategorias = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnCategoriasVal1 = "_returnCategoriasVal-1857143384";
            _returnCategorias.add(_returnCategoriasVal1);
            _return.getCategorias().addAll(_returnCategorias);
            _return.setNombre("Nombre1641709005");
            _return.setDescripcion("Descripcion1212606992");
            _return.setCreador("Creador-1967473859");
            _return.setImgName("ImgName387997115");
            _return.setDuracionMinutos(-1653083901);
            _return.setCosto(4.7963858E-4f);
            webservices.DtFechaWS _returnFechaRegistro = new webservices.DtFechaWS();
            _returnFechaRegistro.setAnio(870000084);
            _returnFechaRegistro.setMes(2084113729);
            _returnFechaRegistro.setDia(-1267014344);
            _returnFechaRegistro.setHoras(1753195623);
            _returnFechaRegistro.setMinutos(1697179406);
            _returnFechaRegistro.setSegundos(-1027900791);
            _return.setFechaRegistro(_returnFechaRegistro);
            webservices.TEstadoWS _returnEstado = webservices.TEstadoWS.ACEPTADA;
            _return.setEstado(_returnEstado);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ActividadDeportivaException_Exception("ActividadDeportivaException...");
        //throw new InstitucionException_Exception("InstitucionException...");
    }

    /* (non-Javadoc)
     * @see webservices.WSActividadController#obtenerSocios(*
     */
    public net.java.dev.jaxb.array.StringArray obtenerSocios() { 
        LOG.info("Executing operation obtenerSocios");
        try {
            net.java.dev.jaxb.array.StringArray _return = new net.java.dev.jaxb.array.StringArray();
            java.util.List<java.lang.String> _returnItem = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnItemVal1 = "_returnItemVal907152863";
            _returnItem.add(_returnItemVal1);
            _return.getItem().addAll(_returnItem);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see webservices.WSActividadController#buscarActDep(java.lang.String  arg0 )*
     */
    public webservices.DtActividadWS buscarActDep(java.lang.String arg0) throws ActividadDeportivaException_Exception    { 
        LOG.info("Executing operation buscarActDep");
        System.out.println(arg0);
        try {
            webservices.DtActividadWS _return = new webservices.DtActividadWS();
            java.util.List<java.lang.String> _returnClases = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnClasesVal1 = "_returnClasesVal303273314";
            _returnClases.add(_returnClasesVal1);
            _return.getClases().addAll(_returnClases);
            java.util.List<java.lang.String> _returnCup = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnCupVal1 = "_returnCupVal-1245661035";
            _returnCup.add(_returnCupVal1);
            _return.getCup().addAll(_returnCup);
            java.util.List<java.lang.String> _returnCategorias = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnCategoriasVal1 = "_returnCategoriasVal98636115";
            _returnCategorias.add(_returnCategoriasVal1);
            _return.getCategorias().addAll(_returnCategorias);
            _return.setNombre("Nombre245571531");
            _return.setDescripcion("Descripcion56591647");
            _return.setCreador("Creador2141760580");
            _return.setImgName("ImgName583681959");
            _return.setDuracionMinutos(-1760172369);
            _return.setCosto(0.91926587f);
            webservices.DtFechaWS _returnFechaRegistro = new webservices.DtFechaWS();
            _returnFechaRegistro.setAnio(732571570);
            _returnFechaRegistro.setMes(100650428);
            _returnFechaRegistro.setDia(-837735398);
            _returnFechaRegistro.setHoras(998523220);
            _returnFechaRegistro.setMinutos(-339814433);
            _returnFechaRegistro.setSegundos(-919321768);
            _return.setFechaRegistro(_returnFechaRegistro);
            webservices.TEstadoWS _returnEstado = webservices.TEstadoWS.ACEPTADA;
            _return.setEstado(_returnEstado);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ActividadDeportivaException_Exception("ActividadDeportivaException...");
    }

    /* (non-Javadoc)
     * @see webservices.WSActividadController#obtenerInstituciones(*
     */
    public net.java.dev.jaxb.array.StringArray obtenerInstituciones() { 
        LOG.info("Executing operation obtenerInstituciones");
        try {
            net.java.dev.jaxb.array.StringArray _return = new net.java.dev.jaxb.array.StringArray();
            java.util.List<java.lang.String> _returnItem = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnItemVal1 = "_returnItemVal-1829900790";
            _returnItem.add(_returnItemVal1);
            _return.getItem().addAll(_returnItem);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see webservices.WSActividadController#finalizarActividad(java.lang.String  arg0 )*
     */
    public void finalizarActividad(java.lang.String arg0) { 
        LOG.info("Executing operation finalizarActividad");
        System.out.println(arg0);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see webservices.WSActividadController#ingresarDatosActividadDep(java.lang.String  arg0 ,)webservices.DtActividadWS  arg1 )*
     */
    public boolean ingresarDatosActividadDep(java.lang.String arg0,DtActividadWS arg1) throws ActividadDeportivaException_Exception , InstitucionException_Exception    { 
        LOG.info("Executing operation ingresarDatosActividadDep");
        System.out.println(arg0);
        System.out.println(arg1);
        try {
            boolean _return = false;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ActividadDeportivaException_Exception("ActividadDeportivaException...");
        //throw new InstitucionException_Exception("InstitucionException...");
    }

}
