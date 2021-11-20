package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import datatypes.DtActividadDeportiva;
import datatypes.DtActividadDeportivaExt;
import datatypes.DtClase;
import datatypes.DtClaseExt;
import datatypes.DtClasesCuponera;
import datatypes.DtCuponera;
import datatypes.DtFecha;
import datatypes.DtInstitucion;
import datatypes.DtPremio;
import datatypes.DtProfesor;
import datatypes.DtProfesorExt;
import datatypes.DtSocio;
import datatypes.DtSocioExt;
import datatypes.DtUsuario;
import datatypes.DtUsuarioExt;
import datatypes.TEstado;
import net.java.dev.jaxb.array.StringArray;
import webservices.DtActividadWS;
import webservices.DtClaseWS;
import webservices.DtClasesCuponeraWS;
import webservices.DtCuponeraWS;
import webservices.DtFechaWS;
import webservices.DtInstitucionWS;
import webservices.DtPremioWS;
import webservices.DtProfesorWS;
import webservices.DtSocioWS;
import webservices.DtUsuarioWS;
import webservices.TEstadoWS;

public class Cnv {

	public static DtFecha fecha(DtFechaWS f) {
		return new DtFecha(f.getAnio(),f.getMes(),f.getDia(),f.getHoras(),f.getMinutos(),f.getSegundos());
	}
	public static DtFechaWS fecha(DtFecha f) {
		DtFechaWS x = new DtFechaWS();
		x.setAnio(f.getAnio());
		x.setMes(f.getMes());
		x.setDia(f.getDia());
		x.setHoras(f.getHoras());
		x.setMinutos(f.getMinutos());
		x.setSegundos(f.getSegundos());
		return x;
	}
	
	public static DtUsuarioWS usuario(DtUsuario d) {
		if(d instanceof DtSocio || d instanceof DtSocioExt) {
			DtSocioWS x = new DtSocioWS();
			x.setNickname(d.getNickname());
			x.setNombre(d.getNombre());
			x.setApellido(d.getApellido());
			x.setEmail(d.getEmail());
			x.setFechaNacimiento(Cnv.fecha(d.getFechaNacimiento()));
			x.setContrasenia(d.getContrasenia());
			x.setImagen(d.getImagen());
			if(d instanceof DtSocioExt) {
				for(String n: ((DtSocioExt) d).getSeguidoresNickname())
					x.getSeguidoresNickname().add(n);
				for(String n: ((DtSocioExt) d).getSeguidosNickname())
					x.getSeguidosNickname().add(n);
				for(Entry<String, Set<String>> n: ((DtSocioExt) d).getAguadeUwu().entrySet()) {
					x.getClasesDeActividadesHead().add(n.getKey());
					StringArray e = new StringArray();
					for(String m: n.getValue())
						e.getItem().add(m);
					x.getClasesDeActividadesData().add(e);
				}
				for(Entry<String, Set<String>> n: ((DtSocioExt) d).getClasesDeActividadesFinalizadas().entrySet()) {
					x.getClasesDeActividadesFinalizadasHead().add(n.getKey());
					StringArray e = new StringArray();
					for(String m: n.getValue())
						e.getItem().add(m);
					x.getClasesDeActividadesFinalizadasData().add(e);
				}
				for(String n: ((DtSocioExt) d).getCuponerasCompradas())
					x.getCuponerasCompradas().add(n);
				for(String n: ((DtSocioExt) d).getActividadesFavoritas())
					x.getActividadesFavoritas().add(n);
				for(Entry<String, DtPremio> n: ((DtSocioExt) d).getPremios().entrySet()) {
					x.getPremiosHead().add(n.getKey());
					x.getPremiosData().add(Cnv.premio(n.getValue()));
				}
			}
			return x;
		}
		else {
			DtProfesorWS x = new DtProfesorWS();
			x.setNickname(d.getNickname());
			x.setNombre(d.getNombre());
			x.setApellido(d.getApellido());
			x.setEmail(d.getEmail());
			x.setFechaNacimiento(Cnv.fecha(d.getFechaNacimiento()));
			x.setContrasenia(d.getContrasenia());
			x.setImagen(d.getImagen());
			x.setNombreInstitucion(((DtProfesor)d).getNombreInstitucion());
			x.setBiografia(((DtProfesor) d).getBiografia());
			x.setDescripcion(((DtProfesor) d).getDescripcion());
			x.setLink(((DtProfesor) d).getLink());
			if(d instanceof DtProfesorExt) {
				for(String n: ((DtProfesorExt) d).getSeguidoresNickname())
					x.getSeguidoresNickname().add(n);
				for(String n: ((DtProfesorExt) d).getSeguidosNickname())
					x.getSeguidosNickname().add(n);
				for(Entry<String, TEstado> n: ((DtProfesorExt) d).getHistoralActDepIngresadas().entrySet()) {
					x.getHistoralActDepIngresadasNombre().add(n.getKey());
					x.getHistoralActDepIngresadasEstado().add(TEstadoWS.values()[n.getValue().ordinal()]);
				}
				for(Entry<String, Set<String>> n: ((DtProfesorExt) d).getClasesxActividades().entrySet()) {
					x.getActDepAsociadasHead().add(n.getKey());
					StringArray e = new StringArray();
					for(String m: n.getValue())
						e.getItem().add(m);
					x.getActDepAsociadasData().add(e);
				}
			}
			return x;
		}
	}
	
	public static DtUsuario usuario(DtUsuarioWS d) {
		if(d instanceof DtSocioWS) {
			Set<String> seguidores = new HashSet<>();
			for(String n: d.getSeguidoresNickname()) {
				seguidores.add(n);
			}
			Set<String> seguidos = new HashSet<>();
			for(String n: d.getSeguidosNickname()) {
				seguidos.add(n);
			}
			Set<String> cuponeras = new HashSet<>();
			for(String n: ((DtSocioWS)d).getCuponerasCompradas()) {
				cuponeras.add(n);
			}
			Set<String> actividadesFavoritas = new HashSet<>();
			for(String n: ((DtSocioWS)d).getActividadesFavoritas()) {
				actividadesFavoritas.add(n);
			}
			Map<String,  Set<String>> clasesDeActividades = new HashMap<>();
			for(int i=0;i< ((DtSocioWS) d).getClasesDeActividadesHead().size();i++) {
				clasesDeActividades.put(((DtSocioWS) d).getClasesDeActividadesHead().get(i), new HashSet<String>(((DtSocioWS) d).getClasesDeActividadesData().get(i).getItem()));
			}
			Map<String,  Set<String>> clasesDeActividadesFinalizadas = new HashMap<>();
			for(int i=0;i< ((DtSocioWS) d).getClasesDeActividadesFinalizadasHead().size();i++) {
				clasesDeActividadesFinalizadas.put(((DtSocioWS) d).getClasesDeActividadesFinalizadasHead().get(i), new HashSet<String>(((DtSocioWS) d).getClasesDeActividadesFinalizadasData().get(i).getItem()));
			}
			Map<String,  DtPremio> premios = new HashMap<>();
			for(int i=0; i< ((DtSocioWS) d).getPremiosHead().size(); i++) {
				premios.put(((DtSocioWS) d).getPremiosHead().get(i), Cnv.premio(((DtSocioWS) d).getPremiosData().get(i)));
			}

			return new DtSocioExt(d.getNickname(), d.getNombre(), d.getApellido(), d.getEmail(),
					d.getContrasenia(), Cnv.fecha(d.getFechaNacimiento()), clasesDeActividades, d.getImagen(), seguidos, seguidores,
					cuponeras, actividadesFavoritas, premios, clasesDeActividadesFinalizadas);
		}
		else if(d instanceof DtProfesorWS) {
			Set<String> seguidores = new HashSet<>();
			for(String n: d.getSeguidoresNickname()) {
				seguidores.add(n);
			}
			Set<String> seguidos = new HashSet<>();
			for(String n: d.getSeguidosNickname()) {
				seguidos.add(n);
			}
			Map<String,  Set<String>> actDepAsociadas = new HashMap<>();
			for(int i=0; i<((DtProfesorWS) d).getActDepAsociadasHead().size(); i++) {
				actDepAsociadas.put(((DtProfesorWS) d).getActDepAsociadasHead().get(i), new HashSet<String>(((DtProfesorWS) d).getActDepAsociadasData().get(i).getItem()));
			}
			Map<String,  TEstado> historalActDepIngresadas = new HashMap<>();
			for(int i=0; i< ((DtProfesorWS) d).getHistoralActDepIngresadasNombre().size(); i++) {
				historalActDepIngresadas.put(((DtProfesorWS) d).getHistoralActDepIngresadasNombre().get(i), TEstado.values()[((DtProfesorWS) d).getHistoralActDepIngresadasEstado().get(i).ordinal()]);
			}
			return new DtProfesorExt(d.getNickname(), d.getNombre(), d.getApellido(), d.getEmail(),
					d.getContrasenia(), Cnv.fecha(d.getFechaNacimiento()), ((DtProfesorWS) d).getNombreInstitucion(), ((DtProfesorWS) d).getDescripcion(),
					((DtProfesorWS) d).getBiografia(), ((DtProfesorWS) d).getLink(), actDepAsociadas, d.getImagen(), seguidos, seguidores, historalActDepIngresadas, ((DtProfesorWS) d).getValoracion());
		}
		else {
			Set<String> seguidores = new HashSet<>();
			for(String n: d.getSeguidoresNickname()) {
				seguidores.add(n);
			}
			Set<String> seguidos = new HashSet<>();
			for(String n: d.getSeguidosNickname()) {
				seguidos.add(n);
			}
			return new DtUsuarioExt(d.getNickname(), d.getNombre(), d.getApellido(), d.getEmail(),
					d.getContrasenia(), Cnv.fecha(d.getFechaNacimiento()), d.getImagen(), seguidos, seguidores);
		}
	}
	
	public static DtPremio premio(DtPremioWS p) {
		if(p!=null)
			return new DtPremio(p.getDescripcion(),p.getCantidad(),p.getGanadores(),Cnv.fecha(p.getFechaSorteo()));
		else return null;
	}
	
	public static DtPremioWS premio(DtPremio p) {
		if(p==null)
			return null;
		DtPremioWS x = new DtPremioWS();
		x.setDescripcion(p.getDescripcion());
		x.setCantidad(p.getCantidad());
		x.setFechaSorteo((p.getFechaSorteo()==null) ? Cnv.fecha(new DtFecha(0,0,0,0,0,0)) : Cnv.fecha(p.getFechaSorteo()));
		if(p.getGanadores()!=null)
			x.getGanadores().addAll(p.getGanadores());
		return x;
	}
	
	public static DtActividadDeportiva actividad(DtActividadWS d) {
		return new DtActividadDeportivaExt(d.getNombre(), d.getDescripcion(), d.getDuracionMinutos(), d.getCosto(),
				Cnv.fecha(d.getFechaRegistro()), new HashSet<String>(d.getCategorias()), new HashSet<String>(d.getClases()),
				new HashSet<String>(d.getCup()), TEstado.values()[d.getEstado().ordinal()], d.getCreador(), d.getImgName(),d.getFavoritos());
	}
	public static DtActividadWS actividad(DtActividadDeportiva d) {
		DtActividadWS x = new DtActividadWS();
		x.setNombre(d.getNombre());
		x.setDescripcion(d.getDescripcion());
		x.setDuracionMinutos(d.getDuracionMinutos());
		x.setCosto(d.getCosto());
		x.setFechaRegistro(Cnv.fecha(d.getFechaRegistro()));
		x.getCategorias().addAll(d.getCategorias());
		x.setEstado(TEstadoWS.values()[d.getEstado().ordinal()]);
		x.setCreador(d.getCreador());
		x.setImgName(d.getImgName());
		if(d instanceof DtActividadDeportivaExt) {
			x.setFavoritos(((DtActividadDeportivaExt) d).getFavoritos());
			x.getClases().addAll(((DtActividadDeportivaExt) d).getClases());
			x.getCup().addAll(((DtActividadDeportivaExt) d).getCuponeras());
		}
		return x;
	}
	
	public static DtClase clase(DtClaseWS c) {
		Map<String,  Integer> calificaciones = new HashMap<>();
		for(int i=0; i< c.getCalificacionesHead().size(); i++) {
			calificaciones.put(c.getCalificacionesHead().get(i), c.getCalificacionesData().get(i));
		}
		return new DtClaseExt(c.getNombre(), c.getNicknameProfesor(), c.getCorreoProfesor(), c.getMinSocios(), c.getMaxSocios(),
				c.getUrlwebsite(), Cnv.fecha(c.getFechaClase()), Cnv.fecha(c.getFechaRegistro()), new ArrayList<String>(c.getAlumnos()),
				new ArrayList<String>(c.getSoloNickAlumnos()), c.getImgName(), c.getUrlVideo(), Cnv.premio(c.getPremio()), calificaciones);
	}
	public static DtClaseWS clase(DtClase c) {
		DtClaseWS x = new DtClaseWS();
		x.setNombre(c.getNombre());
		x.setNicknameProfesor(c.getNicknameProfesor());
		x.setCorreoProfesor(x.getCorreoProfesor());
		x.setMinSocios(c.getMinSocios());
		x.setMaxSocios(c.getMaxSocios());
		x.setUrlwebsite(c.getURL());
		x.setFechaClase(Cnv.fecha(c.getFechaClase()));
		x.setFechaRegistro(Cnv.fecha(c.getFechaRegistro()));
		x.setImgName(c.getImgName());
		x.setUrlVideo(c.getUrlVideo());
		x.setPremio(Cnv.premio(c.getPremio()));
		if(c instanceof DtClaseExt) {
			x.getAlumnos().addAll(((DtClaseExt) c).getAlumnos());
			x.getSoloNickAlumnos().addAll(((DtClaseExt) c).getNickAlumnos());
			for(Entry<String, Integer> n: ((DtClaseExt) c).getCalificaciones().entrySet()) {
				x.getCalificacionesHead().add(n.getKey());
				x.getCalificacionesData().add(n.getValue());
			}
		}
		return x;
	}
	public static DtInstitucion institucion(DtInstitucionWS i) {
		return new DtInstitucion(i.getNombre(),i.getDescripcion(),i.getUrl());
	}
	public static DtInstitucionWS institucion(DtInstitucion i) {
		DtInstitucionWS x = new DtInstitucionWS();
		x.setNombre(i.getNombre());
		x.setDescripcion(i.getDescripcion());
		x.setUrl(i.getUrl());
		return x;
	}
	
	public static DtCuponera cuponera(DtCuponeraWS c) {
		List<DtClasesCuponera> cups = new ArrayList<>();
		for(DtClasesCuponeraWS y : c.getContenido()) {
			cups.add(Cnv.clasesCuponera(y));
		}
		return new DtCuponera(c.getNombre(), c.getDescripcion(), c.getDescuento(), c.getCosto(),
				Cnv.fecha(c.getFechaInicio()), Cnv.fecha(c.getFechaFin()), Cnv.fecha(c.getFechaAlta()),
				cups, new ArrayList<String>(c.getCategorias()), c.getImg());
	}
	public static DtCuponeraWS cuponera(DtCuponera c) {
		DtCuponeraWS x = new DtCuponeraWS();
		x.setNombre(c.getNombre());
		x.setDescripcion(x.getDescripcion());
		x.setDescuento(c.getDescuento());
		x.setCosto(c.getCosto());
		x.setFechaAlta(Cnv.fecha(c.getFechaAlta()));
		x.setFechaInicio(Cnv.fecha(c.getFechaInicio()));
		x.setFechaFin(Cnv.fecha(c.getFechaFin()));
		for(DtClasesCuponera y : c.getContenido())
			x.getContenido().add(Cnv.clasesCuponera(y));
		return x;
	}
	public static DtClasesCuponera clasesCuponera(DtClasesCuponeraWS c) {
		return new DtClasesCuponera(c.getNombreActividad(),c.getCantidadClases());
	}
	public static DtClasesCuponeraWS clasesCuponera(DtClasesCuponera c) {
		DtClasesCuponeraWS x = new DtClasesCuponeraWS();
		x.setNombreActividad(c.getNombreActividad());
		x.setCantidadClases(c.getCantidadClases());
		return x;
	}
}
