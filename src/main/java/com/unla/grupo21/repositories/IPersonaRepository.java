package com.unla.grupo21.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo21.entities.Persona;
import com.unla.grupo21.entities.User;


@Repository("personaRepository")
public interface IPersonaRepository  extends JpaRepository<Persona, Serializable> {

	public abstract Persona findById(int id);
	
}
