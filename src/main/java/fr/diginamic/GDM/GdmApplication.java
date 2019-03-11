package fr.diginamic.GDM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("fr.diginamic")
@SpringBootApplication

@EntityScan(basePackages = { "fr.diginamic" })

public class GdmApplication {

	public static void main(String[] args) {

		SpringApplication.run(GdmApplication.class, args);
	}

}
