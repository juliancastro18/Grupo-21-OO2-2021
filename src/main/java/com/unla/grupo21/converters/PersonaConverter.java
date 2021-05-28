package com.unla.grupo21.converters;

import org.springframework.stereotype.Component;

import com.unla.grupo21.entities.Persona;
import com.unla.grupo21.models.PersonaModel;

@Component("personaConverter")
public class PersonaConverter {

	
	public PersonaModel entityToModel(Persona persona) {
		return new PersonaModel(persona.getId(), persona.getNombre(), persona.getApellido(), persona.getTipoDocumento(), persona.getDocumento(), persona.getEmail());
	}
	
	public Persona modelToEntity(PersonaModel personaModel) {
		return new Persona(personaModel.getId(), personaModel.getNombre(), personaModel.getApellido(), personaModel.getTipoDocumento(), personaModel.getDocumento(), personaModel.getEmail());
	}
	
}
