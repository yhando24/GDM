package fr.diginamic.mission.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.mission.model.MissionDTO;
import fr.diginamic.mission.service.MissionService;

@CrossOrigin
@RestController()
@RequestMapping("/missions")
public class MissionController {
	
	@Autowired
	private MissionService missionService;
	
	
	@GetMapping
	public List<MissionDTO> findAll(){
		return this.missionService.findAll();
	}
}
