package org.ao.collman.servicecontroller;

import javax.validation.Valid;

import org.ao.collman.model.User;
import org.ao.collman.requestprocessor.RequestProcessorInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//class-level annotation: maps a specific request path or pattern ("URI path template") onto a controller; can map multiple URIs
@RequestMapping(value = {"/aocollman", "/ao", "/collman"}) 
public class HttpServiceController {

	@Autowired // find automatically the bean
	private RequestProcessorInterface services;

	private static final Logger log = LoggerFactory.getLogger(HttpServiceController.class);

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping(path = "/addcollab", method = RequestMethod.POST)
	public String addCollaborator(@Valid User user, BindingResult result, Model model) {
		log.info("addCollaborator request received");
		if (result.hasErrors()) {
			return "add-collab";
		}
		try {
			services.addCollaborator(user);
			Iterable<User> users = services.listAllCollaborators();
			model.addAttribute("Users", users);
			return "list-collabs";
		} catch (Exception e) {
			// TODO error page
			Iterable<User> users = services.listAllCollaborators();
			model.addAttribute("Users", users);
			return "list-collabs";
		}

	}

	@GetMapping(path = "/listcolls")
	public String listAllCollaborators(Model model) {

		log.info("listAllCollaborator request received");
		Iterable<User> users = services.listAllCollaborators();
		model.addAttribute("Users", users);
		return "list-collabs";
	}

	@GetMapping("/add")
	public String showAddForm(User user) {
		return "add-collab";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		User user = services.getCollaboratorById(id);
		model.addAttribute("user", user);
		return "update-collab";
		}

	@PostMapping("update/{id}")
	public String updateCollab(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "update-collab";
		}
		services.addCollaborator(user);
		return "updatesuccess";
	}

	@GetMapping("delete/{id}")
	public String deleteCollaborators(@PathVariable("id") Integer collaboratorId, Model model) {

		log.info("deleteCollaborator request received");
		services.deleteCollaborator(collaboratorId);

		Iterable<User> users = services.listAllCollaborators();
		model.addAttribute("Users", users);
		return "list-collabs";
	}

}

