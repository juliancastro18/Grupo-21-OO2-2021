package com.unla.grupo21.services;

import java.util.List;
import com.unla.grupo21.entities.UserRole;
import com.unla.grupo21.models.UserRoleModel;

public interface IUserRole {

	public List<UserRole> getAll();
	public UserRoleModel insertOrUpdate(UserRoleModel userRoleModel);
	public boolean remove(int id);
}
