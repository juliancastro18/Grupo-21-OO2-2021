package com.unla.grupo21.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo21.entities.Permiso;



@Repository("permisoRepository")
public interface IPermisoRepository extends JpaRepository<Permiso, Serializable> {

	public abstract Permiso findById(int id);

	//@Query(value = "from Permiso p where p.fecha >= :startDate AND :endDate <= p.fecha")
	//public List<Permiso> getAllBetweenDates(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);
	
}
