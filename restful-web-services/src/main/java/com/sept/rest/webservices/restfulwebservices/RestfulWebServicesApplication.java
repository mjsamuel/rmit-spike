package com.sept.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EnableJpaRepositories(basePackages = "com.sept.rest.webservices.restfulwebservices.repository")
@EntityScan("com.sept.rest.webservices.restfulwebservices.model")
@SpringBootApplication(scanBasePackages={
		"com.sept.rest.webservices.restfulwebservices.resource",
		"com.sept.rest.webservices.restfulwebservices.jwt",
		"com.sept.rest.webservices.restfulwebservices.exception",
		"com.sept.rest.webservices.restfulwebservices.config"
})
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
}
