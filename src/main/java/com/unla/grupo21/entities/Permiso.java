package com.unla.grupo21.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
@Table(name="permiso")
@DiscriminatorColumn(name="tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Permiso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idPermiso;
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	protected Persona pedido;
	@Column(name="fecha", nullable=false)
	protected LocalDate fecha;
	
	//TABLA INTERMEDIA PARA LA RELACION MANY TO MANY CON LUGAR
	@JoinTable(
			name = "rel_permiso_lugar", 
			joinColumns = @JoinColumn(name="fk_permiso", nullable = false),
			inverseJoinColumns = @JoinColumn(name="fk_lugar", nullable=false)
			)
	
	@ManyToMany
	protected Set<Lugar> desdeHasta;
	
	public Permiso() {}
	
	public Permiso(int idPermiso, Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta) {
		super();
		this.idPermiso = idPermiso;
		this.pedido = pedido;
		this.fecha = fecha;
		setDesdeHasta(desdeHasta);
	}

	public Permiso(Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta) {
		super();
		this.pedido = pedido;
		this.fecha = fecha;
		setDesdeHasta(desdeHasta);
	}
	
	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public Persona getPedido() {
		return pedido;
	}

	public void setPedido(Persona persona) {
		this.pedido = persona;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Set<Lugar> getDesdeHasta() {
		return desdeHasta;
	}

	public void setDesdeHasta(Set<Lugar> desdeHasta) {
		this.desdeHasta = desdeHasta;
	}
		
}
