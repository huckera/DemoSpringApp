package org.ao.collman.servicecontroller;

import javax.validation.Valid;

import org.ao.collman.model.dto.Collaborator;
import org.ao.collman.requestprocessor.RequestProcessorInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ao") // class-level annotation: maps a specific request path or pattern ("URI path
															// template") onto a controller; can map multiple URIs
public class CollaboratorController {

	@Autowired
	private RequestProcessorInterface services;

	private static final Logger log = LoggerFactory.getLogger(CollaboratorController.class);

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping(path = "/addcollab", method = RequestMethod.POST)
	public String addCollaborator(@Valid Collaborator collaborator, BindingResult result, Model model) {
		log.info("Request received addcollaborator: " + collaborator);
		if (result.hasErrors()) {
			return "add-collab";
		}
		try {
			log.debug("Calling service addcollaborator: " + collaborator);
			services.addCollaborator(collaborator);
			log.info("Start displaying collaborator list");
			log.debug("Calling service listcollaborator");
			Iterable<Collaborator> collaboratorList = services.listAllCollaborators();
			model.addAttribute("Collaborators", collaboratorList);
			return "list-collabs";
		} catch (Exception e) {
			// TODO addCollaborator error page
			Iterable<Collaborator> collaboratorList = services.listAllCollaborators();
			model.addAttribute("Collaborators", collaboratorList);
			return "list-collabs";
		}

	}

	@GetMapping(path = "/listcolls")
	public String listAllCollaborators(Model model) {

		log.info("Request received listAllCollaborator");
		log.debug("Calling service listAllcollaborator");
		Iterable<Collaborator> collaboratorList = services.listAllCollaborators();
		model.addAttribute("Collaborators", collaboratorList);
		return "list-collabs";
	}

	@GetMapping("/add")
	public String showAddForm(Collaborator collaborator) {
		return "add-collab";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Collaborator collaborator = services.getCollaboratorById(id);
		model.addAttribute("collaborator", collaborator);
		return "update-collab";
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

	@GetMapping("delete/{id}")
	public String deleteCollaborators(@PathVariable("id") Integer collaboratorId, Model model) {

		log.info("Request received deleteCollaborator with id: " + collaboratorId);
		log.debug("Calling service deleteCollaborator");
		services.deleteCollaborator(collaboratorId);

		Iterable<Collaborator> collaboratorList = services.listAllCollaborators();
		model.addAttribute("Collaborators", collaboratorList);
		return "list-collabs";
	}

}

