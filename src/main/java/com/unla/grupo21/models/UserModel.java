package com.unla.grupo21.models;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.unla.grupo21.models.TipoDocumento;

public class UserModel {

	private int id;
	
	private String nombre;
	
	private String apellido;
	
	@NotNull(message = "El tipo de documento no puede ser nulo")
	private TipoDocumento tipoDocumento;
	
	@NotNull(message = "El documento no puede ser nulo")
	@Min(1000000)
	@Max(50000000)
	private Long documento;
	
	@NotNull
	@Email(message = "El email debe ser válido")
	private String email;
	
	@NotNull
	@Size(min=6, max=45, message = "El nombre de usuario debe contener entre 6 y 18 caracteres")
	private String username;
	
	@NotNull
	@Size(min=6, max=60, message = "La contraseña debe contener entre 6 y 18 caracteres")
	private String password;
	
	
	private UserRoleModel userRole;
	
	@AssertTrue
	private boolean activo = true;
	
	public UserModel(){};

	public UserModel(int id, String nombre, String apellido, TipoDocumento tipoDocumento, long documento, String email,
			String username, String password, UserRoleModel userRole, boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = tipoDocumento;
		this.documento = Long.valueOf(documento);
		this.email = email;
		this.username = username;
		this.password = password;
		this.userRole = userRole;
		this.activo = activo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(long documento) {
		this.documento = Long.valueOf(documento);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRoleModel getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleModel userRole) {
		this.userRole = userRole;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", tipoDocumento="
				+ tipoDocumento + ", documento=" + documento + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", userRole=" + userRole + ", activo=" + activo + "]";
	};
	
	
}
