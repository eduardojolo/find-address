package br.com.find.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application class.
 * 
 * @author Eduardo
 *
 */
@SpringBootApplication
public class FindAddressApplication {

	/**
	 * Main method that initializes the microservice.
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(FindAddressApplication.class, args);
		
	}
}
