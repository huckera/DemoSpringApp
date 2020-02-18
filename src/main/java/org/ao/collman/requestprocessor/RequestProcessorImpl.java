package org.ao.collman.requestprocessor;

import org.ao.collman.model.User;
import org.ao.collman.services.UserServicesInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestProcessorImpl implements RequestProcessorInterface {

	private static final Logger log = LoggerFactory.getLogger(RequestProcessorImpl.class);

	@Autowired // find automatically the bean
	private UserServicesInterface userI;

	@Override
	public void deleteCollaborator(Integer userId) {
		userI.deleteById(userId);
		log.info("Collaborator deleted from the database with Id: " + userId.toString());

	}

	public Iterable<User> listAllCollaborators() {

		Iterable<User> users = userI.getAllUsers();
		return users;

	}

	public void addCollaborator(User user) {
		userI.save(user);
		log.info("Collaborator saved into the database: " + user.toString());
	}

	@Override
	public User getCollaboratorById(Integer id) {
		User user = userI.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		return user;
	}

	@Override
	public void updateCollaborator(User user) {
		// TODO Auto-generated method stub

	}
	


}
