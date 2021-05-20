package com.unla.grupo21.services;

import java.util.List;

import com.unla.grupo21.models.UserModel;

public interface IUserService {

	public List<UserModel> getAll();
	
	public UserModel insertOrUpdate(UserModel userModel);
	
	public boolean remove(int id);
	
}
