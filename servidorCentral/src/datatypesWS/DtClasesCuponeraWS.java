package datatypesWS;

import datatypes.DtClasesCuponera;

public class DtClasesCuponeraWS {
	private String nombreActividad;
	private int cantidadClases;
	public DtClasesCuponeraWS() { }
	public DtClasesCuponeraWS(DtClasesCuponera dtcc) {
		setNombreActividad(dtcc.getNombreActividad());
		setCantidadClases(dtcc.getCantidadClases());
	}
	
	public DtClasesCuponera adapt() {
		return new DtClasesCuponera(nombreActividad,cantidadClases);
	}
	public int getCantidadClases() {
		return cantidadClases;
	}
	public void setCantidadClases(int cantidadClases) {
		this.cantidadClases = cantidadClases;
	}
	public String getNombreActividad() {
		return nombreActividad;
	}
	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

}
