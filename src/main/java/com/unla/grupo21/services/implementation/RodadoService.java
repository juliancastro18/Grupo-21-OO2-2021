package com.unla.grupo21.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo21.converters.RodadoConverter;
import com.unla.grupo21.entities.Rodado;
import com.unla.grupo21.models.RodadoModel;
import com.unla.grupo21.repositories.IRodadoRepository;
import com.unla.grupo21.services.IRodadoService;

@Service("rodadoService")
public class RodadoService implements IRodadoService{
	
	@Autowired
	@Qualifier("rodadoRepository")
	private IRodadoRepository rodadoRepository;
	
	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;
	
	@Override
	public RodadoModel findById(int id) {
		Rodado r = rodadoRepository.findById(id);
		return rodadoConverter.entityToModel(r);
	}

	@Override
	public List<RodadoModel> getAll() {
		List<RodadoModel> lstRodados = new ArrayList<RodadoModel>();
		
		for(Rodado r : rodadoRepository.findAll()) {
			lstRodados.add( rodadoConverter.entityToModel(r) );
		}
		
		return lstRodados;
	}

	@Override
	public RodadoModel insertOrUpdate(RodadoModel rodadoModel) {
		Rodado rodado = rodadoRepository.save(rodadoConverter.modelToEntity(rodadoModel));
		return rodadoConverter.entityToModel(rodado);
	}
	
}
