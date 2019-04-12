package fr.diginamic.mission.controller;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.boot.model.naming.ImplicitJoinColumnNameSource.Nature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.mission.exception.ErrorLogigDateMission;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.model.MissionDTO;
import fr.diginamic.mission.model.MissionStatusEnum;
import fr.diginamic.mission.service.MissionService;
import fr.diginamic.security.SecurityUtils;
import fr.diginamic.user.model.User;

@CrossOrigin
@RestController()
@RequestMapping("/missions")
public class MissionController {

	@Autowired
	private MissionService missionService;

	@GetMapping
	public List<MissionDTO> findAll() {
		return this.missionService.findAll();
	}

	@GetMapping("/waiting")
	public List<MissionDTO> findAllToApprove() {
		return this.missionService.findByMissionStatus(MissionStatusEnum.EN_ATTENTE);

	}
	@GetMapping("/display-all")
	public List<MissionDTO> findAllForManager() {
		return this.missionService.findByMissionStatus(MissionStatusEnum.VALIDE);

	}

	@PatchMapping
	public MissionDTO updateMission(@RequestBody Mission m) {
		System.err.println(m);
		return this.missionService.update(m);
	}

	
	@GetMapping("/perso")
	public List<MissionDTO> findByUser(){
		return this.missionService.findByUser();
	}

	@GetMapping("/criteria{month}{year}")
	public List<MissionDTO> criteriaMission(@RequestParam("month") int month,@RequestParam("year")  int year){
		LocalDate ld = LocalDate.of(year, month, 1);
		return this.missionService.criteriaMission(ld);
	}
	@PostMapping("/criteria{month}{year}")
	public List<MissionDTO> criteriaMissionUser(@RequestParam("month") int month,@RequestParam("year")  int year,@RequestBody User m){
		
		LocalDate ld = LocalDate.of(year, month, 1);
		return this.missionService.criteriaMissionUser(ld,m);
	}
	@PatchMapping("/update")
	public MissionDTO update(@RequestBody MissionDTO mission) throws ErrorLogigDateMission {
		return missionService.save(mission);
	}
	
	@PostMapping
	public MissionDTO save(@RequestBody MissionDTO m) throws ErrorLogigDateMission {
		return missionService.save(m);
	}
	

}
