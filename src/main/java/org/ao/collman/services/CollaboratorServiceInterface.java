package org.ao.collman.services;

import java.util.Optional;

import org.ao.collman.model.dto.Collaborator;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface CollaboratorServiceInterface {

	
	public String addNewUser(String name, String email, Model model); 
	
	public Iterable<Collaborator> getAllUsers();
	
	public Optional<Collaborator> findById(Integer id);

	public void deleteById(Integer id);

	public Collaborator save(Collaborator user);

}
