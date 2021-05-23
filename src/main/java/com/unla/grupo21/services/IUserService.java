package com.unla.grupo21.services;

import java.util.List;

import com.unla.grupo21.models.UserModel;
import com.unla.grupo21.models.UserRoleModel;

public interface IUserService {

	public UserModel findById(int id);
	
	public List<UserModel> getActivos();
	
	public List<UserModel> getAll();
	
	public UserModel insertOrUpdate(UserModel userModel);
	
	public boolean remove(int id);
	
}
