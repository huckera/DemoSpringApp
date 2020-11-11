package org.ao.collman.services;

import java.util.Optional;

import org.ao.collman.model.dto.Collaborator;
import org.springframework.stereotype.Service;

@Service
public interface CollaboratorServiceInterface {

	
	public String addCollaborator(Collaborator collaborator);
	
	public Iterable<Collaborator> listCollaborators();
	
	public Optional<Collaborator> getCollaborator(Integer id);

	public void deleteCollaborator(Integer id);

	public Collaborator saveCollaborator(Collaborator collaborator);

}
