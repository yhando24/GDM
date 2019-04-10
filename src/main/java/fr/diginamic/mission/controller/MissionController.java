package fr.diginamic.mission.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.mission.exception.ErrorLogigDateMission;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.model.MissionDTO;
import fr.diginamic.mission.model.MissionStatusEnum;
import fr.diginamic.mission.service.MissionService;
import fr.diginamic.security.SecurityUtils;

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

	@PatchMapping
	public MissionDTO updateMission(@RequestBody Mission m) {
		System.err.println(m);
		return this.missionService.update(m);
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
