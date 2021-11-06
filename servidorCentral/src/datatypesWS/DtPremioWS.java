package datatypesWS;

import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import datatypes.DtPremio;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPremioWS {
	
	private String descripcion;
	private int cantidad;
	private  DtFechaWS fechaSorteo;
	//private List<String> ganadores = null;
	private String[] ganadores;
	
	public DtPremioWS() { }
	public DtPremioWS(DtPremio p) {
		this.setDescripcion(p.getDescripcion());
		this.setCantidad(p.getCantidad());
		this.setGanadores(p.getGanadores().toArray(new String[0]));
		this.setFechaSorteo(new DtFechaWS(p.getFechaSorteo()));
	}
	public DtPremio adapt() {
		return new DtPremio(this.getDescripcion(),this.getCantidad(),new ArrayList<>(Arrays.asList(ganadores)),fechaSorteo.adapt());
	}
	public String[] getGanadores() {
		return ganadores;
	}

	public void setGanadores(String[] ganadores) {
		this.ganadores = ganadores;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public DtFechaWS getFechaSorteo() {
		return fechaSorteo;
	}
	public void setFechaSorteo(DtFechaWS fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}

}