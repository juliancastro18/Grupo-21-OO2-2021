package com.unla.grupo21.services;

import java.util.List;

import com.unla.grupo21.models.PersonaModel;

public interface IPersonaService {

	public PersonaModel findById(int id);
	public List<PersonaModel> getAll();
	public PersonaModel insertOrUpdate(PersonaModel personaModel);
}
