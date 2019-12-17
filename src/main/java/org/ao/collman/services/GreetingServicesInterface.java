package org.ao.collman.services;

import org.springframework.ui.Model;


public interface GreetingServicesInterface {
	
		public String greeting(String name, Model model);
	
		public String greetingVIP(String name, Model model);
}
