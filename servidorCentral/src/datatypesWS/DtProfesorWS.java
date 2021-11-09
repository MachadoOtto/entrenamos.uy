package datatypesWS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import datatypes.DtProfesorExt;
import datatypes.TEstado;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtProfesorWS extends DtUsuarioWS{
	private String [] historalActDepIngresadasNombre;
	private TEstadoWS [] historalActDepIngresadasEstado;
	private String [] actDepAsociadasHead;
	private String [][] actDepAsociadasData;
	private String nombreInstitucion,  descripcion,  biografia,  link;
	private float valoracion;
	
	public DtProfesorWS() {
		super();
	}
	public DtProfesorWS(DtProfesorExt d) {
		super(d);
		int i =0;
		actDepAsociadasHead = new String[d.getActividadesDepAsociadas().size()];
		actDepAsociadasData = new String[d.getActividadesDepAsociadas().size()][];
		for(Entry<String, Set<String>> x: d.getClasesxActividades().entrySet()) {
			actDepAsociadasHead[i] = x.getKey();
			actDepAsociadasData[i++] = x.getValue().toArray(new String[0]);
		}
		i=0;
		historalActDepIngresadasNombre = new String[d.getHistoralActDepIngresadas().size()];
		historalActDepIngresadasEstado = new TEstadoWS[d.getHistoralActDepIngresadas().size()];
		for(Entry<String, TEstado> x: d.getHistoralActDepIngresadas().entrySet()) {
			historalActDepIngresadasNombre[i] = x.getKey();
			historalActDepIngresadasEstado[i++] = TEstadoWS.values()[x.getValue().ordinal()];
		}
		this.setNombreInstitucion(d.getNombreInstitucion());
		this.setDescripcion(d.getDescripcion());
		this.setBiografia(d.getBiografia());
		this.setLink(d.getLink());
		this.setValoracion(d.getValoracion());
	}
	@Override
	public DtProfesorExt adapt() {
		Map<String,  Set<String>> actDepAsociadas = new HashMap<>();
		for(int i=0;actDepAsociadasHead!=null && i<actDepAsociadasHead.length; i++) {
			actDepAsociadas.put(actDepAsociadasHead[i], new HashSet<>(Arrays.asList(actDepAsociadasData[i])));
		}
		Map<String,  TEstado> historalActDepIngresadas = new HashMap<>();
		for(int i=0;historalActDepIngresadasNombre!=null && i<historalActDepIngresadasNombre.length; i++) {
			historalActDepIngresadas.put(historalActDepIngresadasNombre[i], TEstado.values()[historalActDepIngresadasEstado[i].ordinal()]);
		}
		if (this.getSeguidosNickname()==null)
			this.setSeguidosNickname(new String [0]);
		if (this.getSeguidoresNickname()==null)
			this.setSeguidoresNickname(new String [0]);
		return new DtProfesorExt(this.getNickname(),this.getNombre(),this.getApellido(),this.getEmail(),
				this.getContrasenia(), this.getFechaNacimiento().adapt(), nombreInstitucion, descripcion, biografia, 
				link, actDepAsociadas, this.getImagen(),new HashSet<String>(Arrays.asList(this.getSeguidosNickname())),
				new HashSet<String>(Arrays.asList(this.getSeguidoresNickname())), historalActDepIngresadas, valoracion);
	}
	public String [] getHistoralActDepIngresadasNombre() {
		return historalActDepIngresadasNombre;
	}
	public void setHistoralActDepIngresadasNombre(String [] historalActDepIngresadasNombre) {
		this.historalActDepIngresadasNombre = historalActDepIngresadasNombre;
	}
	public TEstadoWS [] getHistoralActDepIngresadasEstado() {
		return historalActDepIngresadasEstado;
	}
	public void setHistoralActDepIngresadasEstado(TEstadoWS [] historalActDepIngresadasEstado) {
		this.historalActDepIngresadasEstado = historalActDepIngresadasEstado;
	}
	public String [] getActDepAsociadasHead() {
		return actDepAsociadasHead;
	}
	public void setActDepAsociadasHead(String [] actDepAsociadasHead) {
		this.actDepAsociadasHead = actDepAsociadasHead;
	}
	public String [][] getActDepAsociadasData() {
		return actDepAsociadasData;
	}
	public void setActDepAsociadasData(String [][] actDepAsociadasData) {
		this.actDepAsociadasData = actDepAsociadasData;
	}
	public String getNombreInstitucion() {
		return nombreInstitucion;
	}
	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public float getValoracion() {
		return valoracion;
	}
	public void setValoracion(float valoracion) {
		this.valoracion = valoracion;
	}
	
	
}
