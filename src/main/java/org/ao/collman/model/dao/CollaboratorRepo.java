package org.ao.collman.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ao.collman.model.dto.Collaborator;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface CollaboratorRepo extends CrudRepository<Collaborator, Integer> {

}