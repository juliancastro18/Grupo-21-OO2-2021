package com.unla.grupo21.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo21.converters.UserRoleConverter;
import com.unla.grupo21.entities.UserRole;
import com.unla.grupo21.models.UserRoleModel;
import com.unla.grupo21.repositories.IUserRoleRepository;
import com.unla.grupo21.services.IUserRole;

@Service("userRoleService")
public class UserRoleService implements IUserRole{

	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;
	
	@Autowired
	@Qualifier("userRoleConverter")
	private UserRoleConverter userRoleConverter;
	
	@Override
	public List<UserRole> getAll() {
		return userRoleRepository.findAll();
	}
	
	@Override
	public UserRoleModel insertOrUpdate(UserRoleModel userRoleModel) {
	
		UserRole userRole = userRoleRepository.save(userRoleConverter.modelToEntity(userRoleModel));
		return userRoleConverter.entityToModel(userRole);
	}
	
	@Override
	public boolean remove(int id) {
	
		try
		{
			userRoleRepository.deleteById(id);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	
}