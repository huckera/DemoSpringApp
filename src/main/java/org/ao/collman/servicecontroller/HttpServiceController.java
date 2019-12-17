package org.ao.collman.servicecontroller;

import javax.validation.Valid;

import org.ao.collman.model.User;
import org.ao.collman.services.GreetingServicesInterface;
import org.ao.collman.services.UserServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

//class-level annotation maps a specific request path or pattern onto a controller
//can map multiple URIs
@RequestMapping(value = {"/aocollman", "/ao", "/collman"}) 
public class HttpServiceController {
	
		
	@Autowired
	private UserServicesInterface userI;

	// @Autowired: find automatically the bean
	// XML: NOT OK
	// if GreetingServices is defined as @Service, OK
	@Autowired
	private GreetingServicesInterface greet1;
	
			
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping(path = "/addcollab", method = { RequestMethod.GET, RequestMethod.POST })
	public String addCollab(@Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "add-collab";
		}

		userI.save(user);
		Iterable<User> users = userI.getAllUsers(model);
		model.addAttribute("Users", users);
		return "list-collabs";
	}

	@GetMapping(path = "/listcolls")
	public String listCollabs(Model model) {
		Iterable<User> users = userI.getAllUsers(model);
		model.addAttribute("Users", users);
		return "list-collabs";
	}

	@GetMapping("/add")
	public String showAddForm(User user) {
		return "add-collab";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		User users = userI.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		model.addAttribute("user", users);
		return "update-collab";
		}

	@PostMapping("update/{id}")
	public String updateCollab(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "update-collab";
		}
		userI.save(user);
		return "updatesuccess";
	}

	@GetMapping("delete/{id}")
	public String deleteCollab(@PathVariable("id") Integer id, Model model) {
		userI.deleteById(id);
		Iterable<User> users = userI.getAllUsers(model);
		model.addAttribute("Users", users);
		return "list-collabs";
	}

//old versions

	@GetMapping(path = "/viewallcollold")
		public @ResponseBody Iterable<User> getAllUsers(Model model) {
			// This returns a JSON or XML with the users
			
			return userI.getAllUsers(model);
		}
			
	@GetMapping(path = "/addcollold") // Map ONLY GET Requests
	public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email, Model model) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		return userI.addNewUser(name, email, model);
	}

//greetings

	// method-level annotation maps a specific request path or pattern onto a
	// controller
	@RequestMapping(value = "/greeting", method = RequestMethod.GET) // =@GetMapping("/greeting") from Spring 4.3 ("method level variants" =
																		// "composed annotations")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) { // view template, Thymeleaf

		// AbstractApplicationContext context = new
		// ClassPathXmlApplicationContext("BeansFactory.xml"); //IoC container created,
		// metadata is in XML
		// GreetingServicesInterface greet1 =
		// (GreetingServicesInterface)context.getBean("greet1"); //greet1 bean created
		System.out.println("ServiceController received request: " + greet1);
		return greet1.greeting(name, model);
	}

	@RequestMapping(value = "/greeting", method = RequestMethod.GET, params = "name=Adam") // invoked on a specific parameter received
	public String greetingAdam(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) { // view template, Thymeleaf
		System.out.println("ServiceController received request: " + greet1);
		return greet1.greetingVIP(name, model);
	}
		
}

