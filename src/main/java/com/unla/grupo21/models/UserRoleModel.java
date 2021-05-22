package com.unla.grupo21.models;

public class UserRoleModel {

	private int id;
	private String role;
	
	public UserRoleModel(){};
	
	public UserRoleModel(int id, String role)
	{
		setId(id);
		setRole(role);
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRoleModel [id=" + id + ", role=" + role + "]";
	}
	

}
