package datatypesWS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import datatypes.DtClasesCuponera;
import datatypes.DtCuponera;
import datatypes.DtFecha;

public class DtCuponeraWS {
	private String nombre,  descripcion;
	private float descuento,  costo;
	private DtFechaWS fechaInicio,  fechaFin,  fechaAlta;
	private DtClasesCuponeraWS [] contenido;
	private String [] categorias;
	private String img;
	
	public DtCuponeraWS(){ }
	public DtCuponeraWS(DtCuponera c){
		this.setNombre(c.getNombre());
		this.setDescripcion(c.getDescripcion());
		this.setDescuento(c.getDescuento());
		this.setCosto(c.getCosto());
		this.setFechaInicio(new DtFechaWS(c.getFechaInicio()));
		this.setFechaFin(new DtFechaWS(c.getFechaFin()));
		this.setFechaAlta(new DtFechaWS(c.getFechaAlta()));
		contenido = new DtClasesCuponeraWS[c.getContenido().size()];
		int i=0;
		for(DtClasesCuponera x: c.getContenido()) {
			contenido[i++]=new DtClasesCuponeraWS(x);
		}
		this.setCategorias(c.getCategorias().toArray(new String[0]));
		this.setImg(c.getImgName());
	}
	public DtCuponera adapt() {
		List<DtClasesCuponera> cont = new ArrayList<>();
		for(int i = 0; i<contenido.length; i++)
			cont.add(contenido[i].adapt());
		List<String> cat = Arrays.asList(categorias);
		return new DtCuponera(nombre, descripcion, descuento,  costo, fechaInicio.adapt(),  fechaFin.adapt(),  fechaAlta.adapt(), cont,cat,img);
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String [] getCategorias() {
		return categorias;
	}
	public void setCategorias(String [] categorias) {
		this.categorias = categorias;
	}
	public DtClasesCuponeraWS [] getContenido() {
		return contenido;
	}
	public void setContenido(DtClasesCuponeraWS [] contenido) {
		this.contenido = contenido;
	}
	public DtFechaWS getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(DtFechaWS fechaFin) {
		this.fechaFin = fechaFin;
	}
	public DtFechaWS getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(DtFechaWS fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public DtFechaWS getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(DtFechaWS fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
