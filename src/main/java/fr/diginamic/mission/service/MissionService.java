package fr.diginamic.mission.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.kindversion.model.KindVersion;
import fr.diginamic.mission.exception.ErrorLogigDateMission;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.model.MissionStatusEnum;
import fr.diginamic.mission.model.TransportEnum;
import fr.diginamic.mission.repository.MissionRepository;
import fr.diginamic.user.model.User;

@Service
public class MissionService {
	
	@Autowired
	private MissionRepository missionRepository;
	
	// create
	public Mission save(Mission mission) throws ErrorLogigDateMission {
		mission.setMissionStatus(MissionStatusEnum.INITIAL);


		if(mission.getEndDate().isBefore(mission.getStartDate())) {
			throw new ErrorLogigDateMission("La date de fin est avant la date de debut");
		}	
		if(mission.getStartDate().isBefore(LocalDate.now())) {
			throw new ErrorLogigDateMission("Une mission doit commencer à J+1");
		}
		if(mission.getTransportEnum().equals(TransportEnum.AVION) && !mission.getStartDate().isAfter(LocalDate.now().plusDays(7))){
			throw new ErrorLogigDateMission("Si une mission se deroule grace à l'utilisation d'un avion, elle doit commencer au moin 7 jours apres la date d'aujourd'hui");
		}
		if(missionRepository.findVeriChevauchement( mission.getEndDate() ,mission.getStartDate()).size()!=0) {
			throw new ErrorLogigDateMission("Probleme de chevauchement de date de mission");
		}
	return	missionRepository.save(mission);
	}
	
	//update
	public Mission update(Mission mission) {
	return	missionRepository.save(mission);
	}
	
	//read
	private List<Mission> findByDepartureCity(String city) {
	return missionRepository.findByDepartureCity(city);
	}
	
	public  Optional<Mission >findById(Long id){
		return	missionRepository.findById(id);
	}
	
	public List<Mission> findByUser(User user){
		return	missionRepository.findByUser(user);
	}

	public List<Mission> findByArrivalCity(String arrivalCity){
		return	missionRepository.findByArrivalCity(arrivalCity);
	}
	
	public List<Mission> findByMissionStatus(MissionStatusEnum MissionStatusEnum){
		return	missionRepository.findByMissionStatus(MissionStatusEnum);
	}
	
	public List<Mission> findByMissionStatusAndUser(MissionStatusEnum MissionStatusEnum, User user){
		return	missionRepository.findByMissionStatusAndUser(MissionStatusEnum, user);
	}
	
	public List<Mission> findByKindVersionAndUser(KindVersion kindVersion, User user){
		return	missionRepository.findByKindVersionAndUser(kindVersion, user);
	}
	
	public List<Mission> findAll(){
		return	missionRepository.findAll();
	}
}
