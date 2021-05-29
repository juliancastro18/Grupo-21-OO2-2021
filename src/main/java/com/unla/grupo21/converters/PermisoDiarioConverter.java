package com.unla.grupo21.converters;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unla.grupo21.entities.Lugar;
import com.unla.grupo21.entities.PermisoDiario;
import com.unla.grupo21.models.LugarModel;
import com.unla.grupo21.models.PermisoDiarioModel;

@Component
public class PermisoDiarioConverter {

	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter; 
	
	@Autowired
	@Qualifier("lugarConverter")
	private LugarConverter lugarConverter;
	
	//TODO ver si se usa por el servicio o no
	
	public PermisoDiarioModel entityToModel(PermisoDiario permisoDiario)
	{
		Lugar[] lugares = null; 
		Set<LugarModel> lugaresModel = new HashSet<LugarModel>();
		lugares = (Lugar[]) permisoDiario.getDesdeHasta().toArray();
		lugaresModel.add(lugarConverter.entityToModel(lugares[0]));
		lugaresModel.add(lugarConverter.entityToModel(lugares[1]));
		
		return new PermisoDiarioModel(permisoDiario.getIdPermiso(), personaConverter.entityToModel(permisoDiario.getPedido()), permisoDiario.getFecha(), 
				 lugaresModel, permisoDiario.getMotivo());
	}
	
	public PermisoDiario modelToEntity(PermisoDiarioModel permisoDiarioModel)
	{
		LugarModel[] lugaresModel = null;
		Set<Lugar> lugaresEntity = new HashSet<Lugar>();
		lugaresModel = (LugarModel[]) permisoDiarioModel.getDesdeHasta().toArray();
		lugaresEntity.add(lugarConverter.modelToEntity(lugaresModel[0]));
		lugaresEntity.add(lugarConverter.modelToEntity(lugaresModel[1]));
		
		return new PermisoDiario(permisoDiarioModel.getIdPermiso(), 
				personaConverter.modelToEntity(permisoDiarioModel.getPedido()), permisoDiarioModel.getFecha(), lugaresEntity,
				permisoDiarioModel.getMotivo());
	}
}
