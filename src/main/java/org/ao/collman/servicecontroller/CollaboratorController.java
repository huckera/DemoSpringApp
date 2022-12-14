package org.ao.collman.servicecontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.ao.collman.model.dto.Collaborator;
import org.ao.collman.requestprocessor.RequestProcessorInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/collab") // class-level annotation: maps a specific request path or pattern ("URI path
														// template") onto a controller; can map multiple URIs
public class CollaboratorController {

	@Autowired
	private RequestProcessorInterface services;

	private static final Logger log = LoggerFactory.getLogger(CollaboratorController.class);

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> addCollaborator(@Valid Collaborator collaborator) {
		log.info("Request received addcollaborator: " + collaborator);
		log.debug("Calling service addcollaborator: " + collaborator);
		services.addCollaborator(collaborator);
		return new ResponseEntity<String>("Collaborator successfully added", HttpStatus.OK);
	}

	@GetMapping(path = "get/{id}", produces = {"application/json"})
	public ResponseEntity<Collaborator> getCollaborator(@PathVariable("id") Integer id) {

		log.info("Request received getCollaborator");
		log.debug("Calling service getCollaborator");
		Collaborator result = (Collaborator) services.getCollaboratorById(id);
		log.debug("Returning result:" + result);
		return new ResponseEntity<Collaborator>(result, HttpStatus.OK);
	}
	
	@GetMapping(path = "list", produces = {"application/json"})
	public ResponseEntity<List<Collaborator>> listAllCollaborators() {

		log.info("Request received listAllCollaborator");
		log.debug("Calling service listAllcollaborator");
		List<Collaborator> collaboratorList = (List<Collaborator>) services.listAllCollaborators();
		return new ResponseEntity<List<Collaborator>>(collaboratorList, HttpStatus.OK);
	}

	@PostMapping("update/{id}")
	public ResponseEntity<String> updateCollaborator(@PathVariable("id") Integer id, @Valid Collaborator updatedCollaborator) {
		log.info("Request received updateCollaborator with id: " + id + ", collaborator changes: " + updatedCollaborator);
		log.debug("Calling service updateCollaborator: " + updatedCollaborator);
		services.addCollaborator(updatedCollaborator);
		return new ResponseEntity<String>("Collaborator successfully updated", HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteCollaborators(@PathVariable("id") Integer collaboratorId) {

		log.info("Request received deleteCollaborator with id: " + collaboratorId);
		log.debug("Calling service deleteCollaborator");
		services.deleteCollaborator(collaboratorId);
		
		return new ResponseEntity<String>("Collaborator successfully deleted", HttpStatus.OK);

	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}

}

