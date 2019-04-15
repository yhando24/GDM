package fr.diginamic.mission.controller;


import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
	
	
	
	@GetMapping("/export")
	public void findAllForExport(HttpServletResponse response) {
		response.addHeader("Content-Disposition", "attachement; filename=\"filename.xlsx\"");
		response.addHeader("Content-Type","application/vnd.ms-excel");
//		response.addHeader("filename", "filename.xls");
		this.missionService.exportExcel(response);
	
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
	
	@GetMapping("/findById/{id}")
	public MissionDTO findById(@PathVariable Long id){
		return this.missionService.findById(id);
	}

	@GetMapping("/criteria{month}{year}")
	public List<MissionDTO> criteriaMission(@RequestParam("month") int month,@RequestParam("year")  int year){
		LocalDate ld = null;
		if(month == 0) {
			ld = LocalDate.of(year, 1, 1);
			return this.missionService.criteriaMission(ld,11);
		}else {
			ld = LocalDate.of(year, month, 1);
			return this.missionService.criteriaMission(ld);
		}
		 
	}
	@PostMapping("/criteria{month}{year}")
	public List<MissionDTO> criteriaMissionUser(@RequestParam("month") int month,@RequestParam("year")  int year,@RequestBody User m){
		LocalDate ld = null;
		if(year == 0) {
			ld = LocalDate.of(year, 1, 1);
			return this.missionService.criteriaMission(m);
		}
		if(month == 0) {
			ld = LocalDate.of(year, 1, 1);
			return this.missionService.criteriaMission(ld,m,11);
		}else {
			ld = LocalDate.of(year, month, 1);
			return this.missionService.criteriaMission(ld,m);
		}
	}
	@PatchMapping("/update")
	public MissionDTO update(@RequestBody MissionDTO mission) throws ErrorLogigDateMission {
		return missionService.save(mission);
	}
	
	@PostMapping
	public MissionDTO save(@RequestBody MissionDTO m) throws ErrorLogigDateMission {
		return missionService.save(m);
	}
	
	@GetMapping("/primes")
	public List<MissionDTO> findByUserAndPrimeNotNull(){
		return this.missionService.findByUserIdAndPrimeNotNull();
	}

}
