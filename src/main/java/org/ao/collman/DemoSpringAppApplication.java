package org.ao.collman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;



/*
@SpringBootApplication is a convenience annotation that adds all of the following:

    @Configuration tags the class as a source of bean definitions for the application context.

    @EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
    Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. 
    This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.

    @ComponentScan tells Spring to look for other components, configurations, and services in the current package, allowing it to find the controllers.
 */


@SpringBootApplication (scanBasePackages= {"org.ao.collman.services", "org.ao.collman.servicecontroller", "org.ao.collman.model"})
@ComponentScan({ "org.ao.collman" })
@EntityScan ({"org.ao.collman.services", "org.ao.collman.servicecontroller", "org.ao.collman.model"})
public class DemoSpringAppApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(DemoSpringAppApplication.class, args);
	}

}
