package fr.diginamic.GDM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("fr.diginamic")
@SpringBootApplication
@EntityScan(basePackages = { "fr.diginamic" })
@EnableJpaRepositories("fr.diginamic")
@EnableScheduling
public class GdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(GdmApplication.class, args);
	}

}
