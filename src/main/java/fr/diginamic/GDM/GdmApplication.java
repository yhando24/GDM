package fr.diginamic.GDM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("fr.diginamic")
@SpringBootApplication
@EntityScan(basePackages = { "fr.diginamic" })
<<<<<<< HEAD
@EnableJpaRepositories

=======
@EnableJpaRepositories("fr.diginamic")
>>>>>>> 8674672e007a74d8edecdebd48f42466fd8ff2eb
public class GdmApplication {

	public static void main(String[] args) {

		SpringApplication.run(GdmApplication.class, args);
	}

}
