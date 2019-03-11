package fr.diginamic.GDM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="fr.diginamic")
public class GdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(GdmApplication.class, args);
	}

}
