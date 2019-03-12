package fr.diginamic.mission.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.mission.repository.MissionRepository;

@Service
public class MissionService {
	
	@Autowired
	private MissionRepository missionRepository;
	
	private void publ() {
	
		System.out.println(missionRepository.findByDepartureCity("paris"));
	}

}
