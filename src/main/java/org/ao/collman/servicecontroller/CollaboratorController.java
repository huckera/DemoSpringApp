package org.ao.collman.servicecontroller;

import java.util.List;

import javax.validation.Valid;

import org.ao.collman.model.dto.Collaborator;
import org.ao.collman.requestprocessor.RequestProcessorInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/collab") // class-level annotation: maps a specific request path or pattern ("URI path
														// template") onto a controller; can map multiple URIs
public class CollaboratorController {

	@Autowired
	private RequestProcessorInterface services;

	private static final Logger log = LoggerFactory.getLogger(CollaboratorController.class);

	@RequestMapping(path = "/add", method = RequestMethod.POST, produces = {"application/json"})
	public ResponseEntity<List<Collaborator>> addCollaborator(@RequestBody Collaborator collaborator) {
		log.info("Request received addcollaborator: " + collaborator);
		try {
			log.debug("Calling service addcollaborator: " + collaborator);
			services.addCollaborator(collaborator);

			log.info("Start displaying collaborator list");
			log.debug("Calling service listcollaborator");
			List<Collaborator> collaboratorList = (List<Collaborator>) services.listAllCollaborators();
			return new ResponseEntity<List<Collaborator>>(collaboratorList, HttpStatus.OK);
		} catch (Exception e) {
			// TODO addCollaborator error page
			List<Collaborator> collaboratorList = (List<Collaborator>) services.listAllCollaborators();
			return new ResponseEntity<List<Collaborator>>(collaboratorList, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/list", produces = {"application/json"})
	public ResponseEntity<List<Collaborator>> listAllCollaborators(Model model) {

		log.info("Request received listAllCollaborator");
		log.debug("Calling service listAllcollaborator");
		List<Collaborator> collaboratorList = (List<Collaborator>) services.listAllCollaborators();
		return new ResponseEntity<List<Collaborator>>(collaboratorList, HttpStatus.OK);
	}

	@PostMapping("update/{id}")
	public String updateCollaborator(@PathVariable("id") Integer id, @Valid Collaborator updatedCollaborator, BindingResult result, Model model) {
		log.info("Request received updateCollaborator with id: " + id + ", collaborator changes: " + updatedCollaborator);
		if (result.hasErrors()) {
			updatedCollaborator.setId(id);
			return "update-collab";
		}
		log.debug("Calling service addcollaborator: " + updatedCollaborator);
		services.addCollaborator(updatedCollaborator);
		return "updatesuccess";
	}

	@DeleteMapping("delete/{id}")
	public String deleteCollaborators(@PathVariable("id") Integer collaboratorId, Model model) {

		log.info("Request received deleteCollaborator with id: " + collaboratorId);
		log.debug("Calling service deleteCollaborator");
		services.deleteCollaborator(collaboratorId);

		Iterable<Collaborator> collaboratorList = services.listAllCollaborators();
		model.addAttribute("Collaborators", collaboratorList);
		return "list-collabs";
	}

}

