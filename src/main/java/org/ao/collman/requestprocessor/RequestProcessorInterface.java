package org.ao.collman.requestprocessor;

import org.ao.collman.model.User;


public interface RequestProcessorInterface {

	public User getCollaboratorById(Integer id);

	public Iterable<User> listAllCollaborators();

	public void updateCollaborator(User user);

	public void addCollaborator(User user);

	public void deleteCollaborator(Integer userId);

}
