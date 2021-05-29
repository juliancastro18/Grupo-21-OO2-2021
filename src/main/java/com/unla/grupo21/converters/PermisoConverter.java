package com.unla.grupo21.converters;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.grupo21.entities.Lugar;
import com.unla.grupo21.entities.Permiso;
import com.unla.grupo21.entities.PermisoDiario;
import com.unla.grupo21.entities.PermisoPeriodo;
import com.unla.grupo21.models.LugarModel;
import com.unla.grupo21.models.PermisoDiarioModel;
import com.unla.grupo21.models.PermisoModel;
import com.unla.grupo21.models.PermisoPeriodoModel;

@Component("permisoConverter")
public class PermisoConverter {

	@Autowired
	@Qualifier("lugarConverter")
	private LugarConverter lugarConverter;
	
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter; 
	
	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;
	
	public PermisoModel entityToModel(Permiso permiso)
	{
		
		Lugar[] lugares = null; 
		Set<LugarModel> lugaresModel = new HashSet<LugarModel>();
		lugares = (Lugar[]) permiso.getDesdeHasta().toArray();
		lugaresModel.add(lugarConverter.entityToModel(lugares[0]));
		lugaresModel.add(lugarConverter.entityToModel(lugares[1]));
		
		PermisoModel pm = null;
		
		if(permiso instanceof PermisoPeriodo) {
			PermisoPeriodo pp = (PermisoPeriodo) permiso;
			pm = new PermisoPeriodoModel(pp.getIdPermiso(), personaConverter.entityToModel(pp.getPedido()),
					pp.getFecha(), lugaresModel, pp.getCantDias(), pp.isVacaciones(), 
					rodadoConverter.entityToModel(pp.getRodado()));
		}
		
		if(permiso instanceof PermisoDiario) {
			PermisoDiario pd = (PermisoDiario) permiso;
			pm = new PermisoDiarioModel(pd.getIdPermiso(), personaConverter.entityToModel(pd.getPedido()), pd.getFecha(), 
					 lugaresModel, pd.getMotivo());
		}
		
		return pm;
	}
	
	public Permiso modelToEntity(PermisoModel permisoModel)
	{
		LugarModel[] lugaresModel = null;
		Set<Lugar> lugaresEntity = new HashSet<Lugar>();
		lugaresModel = (LugarModel[]) permisoModel.getDesdeHasta().toArray();
		lugaresEntity.add(lugarConverter.modelToEntity(lugaresModel[0]));
		lugaresEntity.add(lugarConverter.modelToEntity(lugaresModel[1]));
		
		Permiso p = null;
		
		if(permisoModel instanceof PermisoPeriodoModel) {
			PermisoPeriodoModel ppm = (PermisoPeriodoModel) permisoModel;
			p = new PermisoPeriodo(ppm.getIdPermiso(), personaConverter.modelToEntity(ppm.getPedido()), 
					ppm.getFecha(), lugaresEntity, ppm.getCantDias(), ppm.isVacaciones(),
					rodadoConverter.modelToEntity(ppm.getRodado()));
		}
		
		if(permisoModel instanceof PermisoDiarioModel) {
			PermisoDiarioModel pdm = (PermisoDiarioModel) permisoModel;
			p = new PermisoDiario(pdm.getIdPermiso(), 
					personaConverter.modelToEntity(pdm.getPedido()), pdm.getFecha(), lugaresEntity,
					pdm.getMotivo());
		}
		
		return p;
	}
	
}
