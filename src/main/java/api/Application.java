package api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* This starts the application.
* @author  Zach Firestone
* 
*/
@SpringBootApplication
public class Application {
	
	/**
	 * Initializes the application.
	 * @param args The arguments to start the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
