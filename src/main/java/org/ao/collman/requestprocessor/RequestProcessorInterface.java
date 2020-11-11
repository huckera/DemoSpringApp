package org.ao.collman.requestprocessor;

import org.ao.collman.model.dto.Collaborator;


public interface RequestProcessorInterface {

	public Collaborator getCollaboratorById(Integer id);

	public Iterable<Collaborator> listAllCollaborators();

	public void updateCollaborator(Collaborator user);

	public void addCollaborator(Collaborator user);

	public void deleteCollaborator(Integer userId);

}
