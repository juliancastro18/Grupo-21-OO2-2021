package com.unla.grupo21.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.unla.grupo21.models.UserModel;
import com.unla.grupo21.models.UserRoleModel;

public interface IUserService {

	public UserModel findById(int id);
	
	public long countByActivoTrue();
	
	public long countByUserWhereRoleId(int userRoleId);
	
	public UserModel findUsernameAndFetchUserRoleEagerly(String username);
	
	public List<UserModel> getActivos();
	
	public List<UserModel> getAll();
	
	public UserModel insertOrUpdate(UserModel userModel);
	
	public boolean remove(int id);
	
}
