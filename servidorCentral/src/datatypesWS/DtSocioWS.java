package datatypesWS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import java.util.Set;

import datatypes.DtFecha;
import datatypes.DtPremio;
import datatypes.DtProfesorExt;
import datatypes.DtSocioExt;
import datatypes.TEstado;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtSocioWS extends DtUsuarioWS{
	private String[] clasesDeActividadesHead;
	private String[][] clasesDeActividadesData;
	private String[] clasesDeActividadesFinalizadasHead;
	private String[][] clasesDeActividadesFinalizadasData;
	private String[] cuponerasCompradas;
	private String[] actividadesFavoritas;
	private String[] premiosHead;
	private DtPremio[] premiosData;
	
	
	public DtSocioWS() {
		super();
	}
	public DtSocioWS(DtSocioExt d) {
		super(d);
		clasesDeActividadesHead = new String[d.getAguadeUwu().size()];
		clasesDeActividadesData = new String[d.getAguadeUwu().size()][];
		int i=0;
		for(Entry<String, Set<String>> x: d.getAguadeUwu().entrySet()) {
			clasesDeActividadesHead[i] = x.getKey();
			clasesDeActividadesData[i++] = x.getValue().toArray(new String[0]);
		}
		clasesDeActividadesFinalizadasHead = new String[d.getAguadeUwu().size()];
		clasesDeActividadesFinalizadasData = new String[d.getAguadeUwu().size()][];
		i=0;
		for(Entry<String, Set<String>> x: d.getAguadeUwu().entrySet()) {
			clasesDeActividadesFinalizadasHead[i] = x.getKey();
			clasesDeActividadesFinalizadasData[i++] = x.getValue().toArray(new String[0]);
		}
		i=0;
		premiosHead = new String[d.getPremios().size()];
		premiosData = new DtPremio[d.getPremios().size()];
		for(Entry<String, DtPremio> x: d.getPremios().entrySet()) {
			premiosHead[i] = x.getKey();
			premiosData[i++] = x.getValue();
		}
		cuponerasCompradas = d.getCuponerasCompradas().toArray(new String[0]);
		actividadesFavoritas = d.getActividadesFavoritas().toArray(new String[0]);
	}
	
	@Override
	public DtSocioExt adapt() {
		Map<String,  Set<String>> clasesDeActividades = new HashMap<>();
		for(int i=0; i<clasesDeActividadesHead.length; i++) {
			clasesDeActividades.put(clasesDeActividadesHead[i], new HashSet<>(Arrays.asList(clasesDeActividadesData[i])));
		}
		Map<String,  Set<String>> clasesDeActividadesFinalizadas = new HashMap<>();
		for(int i=0; i<clasesDeActividadesFinalizadasHead.length; i++) {
			clasesDeActividades.put(clasesDeActividadesFinalizadasHead[i], new HashSet<>(Arrays.asList(clasesDeActividadesFinalizadasData[i])));
		}
		Map<String,  DtPremio> premios = new HashMap<>();
		for(int i=0; i<premiosHead.length; i++) {
			premios.put(premiosHead[i], premiosData[i]);
		}
		
		return new DtSocioExt(this.getNickname(),this.getNombre(),this.getApellido(),this.getEmail(),
				this.getContrasenia(), this.getFechaNacimiento(), clasesDeActividades, this.getImagen(),
				new HashSet<String>(Arrays.asList(this.getSeguidosNickname())), new HashSet<String>(Arrays.asList(this.getSeguidoresNickname())),
				new HashSet<String>(Arrays.asList(cuponerasCompradas)), new HashSet<String>(Arrays.asList(actividadesFavoritas)), premios, clasesDeActividadesFinalizadas);
	}

	public String[] getClasesDeActividadesHead() {
		return clasesDeActividadesHead;
	}


	public void setClasesDeActividadesHead(String[] clasesDeActividadesHead) {
		this.clasesDeActividadesHead = clasesDeActividadesHead;
	}


	public String[][] getClasesDeActividadesData() {
		return clasesDeActividadesData;
	}


	public void setClasesDeActividadesData(String[][] clasesDeActividadesData) {
		this.clasesDeActividadesData = clasesDeActividadesData;
	}


	public String[] getClasesDeActividadesFinalizadasHead() {
		return clasesDeActividadesFinalizadasHead;
	}


	public void setClasesDeActividadesFinalizadasHead(String[] clasesDeActividadesFinalizadasHead) {
		this.clasesDeActividadesFinalizadasHead = clasesDeActividadesFinalizadasHead;
	}


	public String[][] getClasesDeActividadesFinalizadasData() {
		return clasesDeActividadesFinalizadasData;
	}


	public void setClasesDeActividadesFinalizadasData(String[][] clasesDeActividadesFinalizadasData) {
		this.clasesDeActividadesFinalizadasData = clasesDeActividadesFinalizadasData;
	}


	public String[] getCuponerasCompradas() {
		return cuponerasCompradas;
	}


	public void setCuponerasCompradas(String[] cuponerasCompradas) {
		this.cuponerasCompradas = cuponerasCompradas;
	}


	public String[] getActividadesFavoritas() {
		return actividadesFavoritas;
	}


	public void setActividadesFavoritas(String[] actividadesFavoritas) {
		this.actividadesFavoritas = actividadesFavoritas;
	}

	public String[] getPremiosHead() {
		return premiosHead;
	}

	public void setPremiosHead(String[] premiosHead) {
		this.premiosHead = premiosHead;
	}

	public DtPremio[] getPremiosData() {
		return premiosData;
	}

	public void setPremiosData(DtPremio[] premiosData) {
		this.premiosData = premiosData;
	}

}
