package com.unla.grupo21.models;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.unla.grupo21.entities.Lugar;

public abstract class PermisoModel {

	protected int idPermiso;
	
	@NotNull(message = "Debe haber una persona asignada con el permiso")
	protected PersonaModel pedido;
	
	@NotNull(message = "La fecha no puede ser nula")
	protected LocalDate fecha;
	protected Set<LugarModel> desdeHasta;
	
	public PermisoModel() {
		pedido = new PersonaModel();
		desdeHasta = new LinkedHashSet<LugarModel>();
	}
	
	public PermisoModel(int idPermiso, PersonaModel pedido, LocalDate fecha, Set<LugarModel> desdeHasta)
	{
		setIdPermiso(idPermiso);
		setPedido(pedido);
		setFecha(fecha);
		setDesdeHasta(desdeHasta);
	}
	
	public PermisoModel(PersonaModel pedido, LocalDate fecha, Set<LugarModel> desdeHasta)
	{
		setPedido(pedido);
		setFecha(fecha);
		setDesdeHasta(desdeHasta);
	}

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public PersonaModel getPedido() {
		return pedido;
	}

	public void setPedido(PersonaModel persona) {
		this.pedido = persona;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Set<LugarModel> getDesdeHasta() {
		return desdeHasta;
	}

	public void setDesdeHasta(Set<LugarModel> desdeHasta) {
		this.desdeHasta = desdeHasta;
	}
	
	public boolean agregarLugar(LugarModel lugar) {
		if(desdeHasta == null) {
			desdeHasta = new LinkedHashSet<LugarModel>();
		} else if(desdeHasta.size()>=2) {
			return false;
		}
		return desdeHasta.add(lugar);
	}

	@Override
	public String toString() {
		return "PermisoModel [idPermiso=" + idPermiso + ", pedido=" + pedido + ", fecha=" + fecha + ", desdeHasta="
				+ desdeHasta + "]";
	}
	
}
