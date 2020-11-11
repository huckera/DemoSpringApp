package org.ao.collman.services;

import java.util.Optional;

import org.ao.collman.model.dao.CollaboratorRepo;
import org.ao.collman.model.dto.Collaborator;
import org.ao.collman.requestprocessor.RequestProcessorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollaboratorServicesImpl implements CollaboratorServiceInterface {

	private static final Logger log = LoggerFactory.getLogger(RequestProcessorImpl.class);

	@Autowired
	private CollaboratorRepo collabRepo;
	
	public String addCollaborator(Collaborator collaborator) {
		collabRepo.save(collaborator);
		log.info("Collaborator saved into the database: " + collaborator.toString());
		return "Saved";
	}
	
	public Iterable<Collaborator> listCollaborators() {
		return collabRepo.findAll();
		
	}

	public Optional<Collaborator> getCollaborator(Integer id) {
		return collabRepo.findById(id);
	}

	public void deleteCollaborator(Integer id) {
		collabRepo.deleteById(id);
	}

	public Collaborator saveCollaborator(Collaborator user) {
		return collabRepo.save(user);
	}

}
