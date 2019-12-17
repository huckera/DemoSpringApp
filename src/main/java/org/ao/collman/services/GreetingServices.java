package org.ao.collman.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GreetingServices implements GreetingServicesInterface{
	
			//GreetingServices() {}; 
	
			public String greeting(String name, Model model) {
				model.addAttribute("name", name);
		        return "greeting";
		    }

			public String greetingVIP(String name, Model model) { 
		        model.addAttribute("name", name);
		        return "greetingVIP";
		    }
}
