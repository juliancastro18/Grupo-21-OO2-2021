package com.unla.grupo21.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo21.entities.Permiso;
import com.unla.grupo21.entities.PermisoPeriodo;


@Repository("permisoRepository")
public interface IPermisoRepository extends JpaRepository<Permiso, Serializable> {

	public abstract Permiso findByIdPermiso(int id);
	
	@Query("FROM PermisoPeriodo pp JOIN FETCH pp.desdeHasta JOIN FETCH pp.pedido JOIN FETCH pp.rodado WHERE pp.rodado.id = :idRodado")
	public abstract List<PermisoPeriodo> getAllByIdRodado(@Param("idRodado")int idRodado);
	
	@Query("FROM Permiso p JOIN FETCH p.desdeHasta JOIN FETCH p.pedido WHERE p.pedido.id = :idPersona")
	public abstract List<Permiso> getAllByIdPersona(@Param("idPersona")int idPersona);
	
	@Query(value = "FROM PermisoDiario pd JOIN FETCH pd.pedido JOIN FETCH pd.desdeHasta WHERE pd.fecha BETWEEN :startDate AND :endDate")
	public abstract List<Permiso> getAllPermisoDiarioBetweenDates(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);
	
	@Query(value = "SELECT * FROM permiso p "
			+ "INNER JOIN rel_permiso_lugar rpl ON rpl.fk_permiso = p.id_permiso "
			+ "INNER JOIN lugar l ON rpl.fk_lugar = l.id "
			+ "INNER JOIN persona per ON per.id = p.pedido_id "
			+ "INNER JOIN rodado r ON p.rodado_id = r.id "
			+ "WHERE (p.fecha BETWEEN ?1 AND ?2) AND p.tipo='Periodo' "
			+ "OR ( ADDDATE(p.fecha, INTERVAL pp.cantDias DAY) BETWEEN ?1 AND ?2)",
			nativeQuery=true)
	public abstract List<Permiso> getAllPermisoPeriodoBetweenDates(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

}