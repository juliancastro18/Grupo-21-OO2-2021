package com.unla.grupo21.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo21.converters.PersonaConverter;
import com.unla.grupo21.converters.UserConverter;
import com.unla.grupo21.entities.Persona;
import com.unla.grupo21.models.PersonaModel;
import com.unla.grupo21.models.UserModel;
import com.unla.grupo21.repositories.IPersonaRepository;
import com.unla.grupo21.repositories.IUserRepository;
import com.unla.grupo21.services.IPersonaService;

@Service("personaService")
public class PersonaService implements IPersonaService {

	@Autowired
	@Qualifier("personaRepository")
	private IPersonaRepository personaRepository;
	
	@Autowired
	@Qualifier("personaConverter")
	private PersonaConverter personaConverter;
	
	@Override
	public PersonaModel findById(int id) {
		Persona p = personaRepository.findById(id);
		return personaConverter.entityToModel(p);
	}

	@Override
	public List<PersonaModel> getAll() {
		List<PersonaModel> lstPersonas = new ArrayList<PersonaModel>();
		
		for(Persona p : personaRepository.findAll()) {
			lstPersonas.add( personaConverter.entityToModel(p) );
		}
		
		return lstPersonas;
	}

	@Override
	public PersonaModel insertOrUpdate(PersonaModel personaModel) {
		Persona persona = personaRepository.save(personaConverter.modelToEntity(personaModel));
		return personaConverter.entityToModel(persona);
	}
	
}
