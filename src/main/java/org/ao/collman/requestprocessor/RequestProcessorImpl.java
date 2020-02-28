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

	@Autowired // find automatically the bean
	private CollaboratorServiceInterface userI;

	@Override
	public void deleteCollaborator(Integer userId) {
		userI.deleteById(userId);
		log.info("Collaborator deleted from the database with Id: " + userId.toString());

	}

	@Override
	public Iterable<Collaborator> listAllCollaborators() {

		Iterable<Collaborator> users = userI.getAllUsers();
		return users;

	}

	@Override
	public void addCollaborator(Collaborator user) {
		userI.save(user);
		log.info("Collaborator saved into the database: " + user.toString());
	}

	@Override
	public Collaborator getCollaboratorById(Integer id) {
		Collaborator user = userI.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		return user;
	}

	@Override
	public void updateCollaborator(Collaborator user) {
		// TODO Auto-generated method stub

	}

}
