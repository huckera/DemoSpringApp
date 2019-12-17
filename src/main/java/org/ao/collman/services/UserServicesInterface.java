package org.ao.collman.services;

import java.util.Optional;

import org.ao.collman.model.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface UserServicesInterface {

	
	public String addNewUser(String name, String email, Model model); 
	
	public Iterable<User> getAllUsers(Model model); 
	
	public Optional<User> findById(Integer id);

	public void deleteById(Integer id);

	public User save(User user);

}
