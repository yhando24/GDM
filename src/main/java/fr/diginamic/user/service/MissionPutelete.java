package fr.diginamic.user.service;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import fr.diginamic.mission.model.Mission;

public class MissionPutelete {

	/*
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mission updateMessage(Mission missionToUpdateHehe) {
		return messageService.updateMessage(message);
	}
	*/
	
	@PostMapping
	public String createUser() {
		return "Utilisateur crée héhé";
	}
	
	@GetMapping
	public String findById(Long id) {
		return "Utilisateur trouvé héhé";
	}
	
	@PutMapping
	public String updateById(Long id) {
		return "Utilisateur modifié héhé";
	}
	
	@DeleteMapping
	public String deleteById(Long id) {
		return "Utilisateur supprimé héhé";
	}

}
