package com.unla.grupo21.models;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Min;

public class PermisoDiarioModel extends PermisoModel{

	@Min(3)
	private String motivo;

	public PermisoDiarioModel() {
		super();
	}
	
	public PermisoDiarioModel(PersonaModel pedido, LocalDate fecha, 
			Set<LugarModel> desdeHasta,@Min(3) String motivo) {
		super(pedido, fecha, desdeHasta);
		this.motivo = motivo;
	}

	public PermisoDiarioModel(int idPermiso, PersonaModel pedido, LocalDate fecha, 
			Set<LugarModel> desdeHasta,@Min(3) String motivo) {
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
