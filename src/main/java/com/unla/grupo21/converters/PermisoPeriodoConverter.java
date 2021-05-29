package com.unla.grupo21.converters;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.grupo21.entities.Lugar;
import com.unla.grupo21.entities.PermisoPeriodo;
import com.unla.grupo21.models.LugarModel;
import com.unla.grupo21.models.PermisoPeriodoModel;

@Component
public class PermisoPeriodoConverter {

	@Autowired
	@Qualifier("lugarConverter")
	private LugarConverter lugarConverter;
	
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter; 
	
	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;
	
	public PermisoPeriodoModel entityToModel(PermisoPeriodo permisoPeriodo)
	{
		
		Lugar[] lugares = null; 
		Set<LugarModel> lugaresModel = new HashSet<LugarModel>();
		lugares = (Lugar[]) permisoPeriodo.getDesdeHasta().toArray();
		lugaresModel.add(lugarConverter.entityToModel(lugares[0]));
		lugaresModel.add(lugarConverter.entityToModel(lugares[1]));
		
		return new PermisoPeriodoModel(permisoPeriodo.getIdPermiso(), personaConverter.entityToModel(permisoPeriodo.getPedido()),
				permisoPeriodo.getFecha(), lugaresModel, permisoPeriodo.getCantDias(), permisoPeriodo.isVacaciones(), 
				rodadoConverter.entityToModel(permisoPeriodo.getRodado()));
	}
	
	public PermisoPeriodo modelToEntity(PermisoPeriodoModel permisoPeriodoModel)
	{
		LugarModel[] lugaresModel = null;
		Set<Lugar> lugaresEntity = new HashSet<Lugar>();
		lugaresModel = (LugarModel[]) permisoPeriodoModel.getDesdeHasta().toArray();
		lugaresEntity.add(lugarConverter.modelToEntity(lugaresModel[0]));
		lugaresEntity.add(lugarConverter.modelToEntity(lugaresModel[1]));
		
		return new PermisoPeriodo(permisoPeriodoModel.getIdPermiso(), personaConverter.modelToEntity(permisoPeriodoModel.getPedido()), 
				permisoPeriodoModel.getFecha(), lugaresEntity, permisoPeriodoModel.getCantDias(), permisoPeriodoModel.isVacaciones(),
				rodadoConverter.modelToEntity(permisoPeriodoModel.getRodado()));
	}
}
