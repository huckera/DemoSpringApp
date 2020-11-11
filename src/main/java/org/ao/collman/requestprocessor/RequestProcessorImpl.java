package org.ao.collman.requestprocessor;

import org.ao.collman.model.dto.Collaborator;
import org.ao.collman.services.CollaboratorServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestProcessorImpl implements RequestProcessorInterface {

	private static final Logger log = LoggerFactory.getLogger(RequestProcessorImpl.class);

	@Autowired
	private CollaboratorServiceInterface collabServiceI;

	@Override
	public void deleteCollaborator(Integer userId) {
		collabServiceI.deleteCollaborator(userId);
		log.info("Collaborator deleted from the database with Id: " + userId.toString());

	}

	@Override
	public Iterable<Collaborator> listAllCollaborators() {

		Iterable<Collaborator> users = collabServiceI.listCollaborators();
		return users;

	}

	@Override
	public void addCollaborator(Collaborator collaborator) {
		collabServiceI.addCollaborator(collaborator);
	}

	@Override
	public Collaborator getCollaboratorById(Integer id) {
		Collaborator user = collabServiceI.getCollaborator(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		return user;
	}

	@Override
	public void updateCollaborator(Collaborator user) {
		// TODO Auto-generated method stub

	}

}
