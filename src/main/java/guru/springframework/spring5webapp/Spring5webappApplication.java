package guru.springframework.spring5webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring5webappApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring5webappApplication.class, args);
	}
}
/*
spring.main.banner-mode=off enlève le ascii art spring banner à l'execution
logging.level.org.springframework=ERROR la console ne montre que les erreurs

*/