package com.unla.grupo21.models;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PermisoDiarioModel extends PermisoModel{

	@NotNull
	@Min(6)
	private String motivo;

	public PermisoDiarioModel() {
		super();
	}
	
	public PermisoDiarioModel(PersonaModel pedido, LocalDate fecha, 
			Set<LugarModel> desdeHasta,@Min(6) String motivo) {
		super(pedido, fecha, desdeHasta);
		this.motivo = motivo;
	}

	public PermisoDiarioModel(int idPermiso, PersonaModel pedido, LocalDate fecha, 
			Set<LugarModel> desdeHasta,@Min(6) String motivo) {
		super(idPermiso, pedido, fecha, desdeHasta);
		this.motivo = motivo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Override
	public String toString() {
		return super.toString() + "PermisoDiario [motivo=" + motivo + "]";
	}
	
	
}
