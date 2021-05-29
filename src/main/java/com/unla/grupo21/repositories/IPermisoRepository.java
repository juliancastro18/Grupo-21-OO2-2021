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

	@Query(value = "FROM PermisoDiario pd WHERE pd.fecha BETWEEN :startDate AND :endDate")
	public List<Permiso> getAllPermisoDiarioBetweenDates(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);
	
	@Query(value = "SELECT * FROM PermisoPeriodo pp INNER JOIN Permiso p ON p.id = pp.id WHERE"
			+ "(p.fecha BETWEEN ?1 AND ?2) OR"
			+ "( ADDDATE(p.fecha, INTERVAL pp.cantDias DAY) BETWEEN ?1 AND ?2)",
			nativeQuery=true)
	public List<Permiso> getAllPermisoPeriodoBetweenDates(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);
	
	@Query(value = "FROM PermisoDiario pd WHERE pd.fecha BETWEEN :startDate AND :endDate")
	public List<Permiso> getAllPermisoDiarioBetweenDatesAndPlaces(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate,
			@Param("idDesde")int idLugarDesde, @Param("idHasta")int idLugarHasta);
	
}
