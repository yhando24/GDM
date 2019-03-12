package fr.diginamic.mission.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import fr.diginamic.kindVersion.model.KindVersion;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.model.MissionStatusEnum;
import fr.diginamic.user.model.User;

@Repository
public interface MissionRepository extends CrudRepository<Mission, Long>  {
	
	public List<Mission> findByUser(User user);
	
	public List<Mission> findByDepartureCity(String departureCity);
	
	public List<Mission> findByArrivalCity(String arrivalCity);
	
	public List<Mission> findByMissionStatus(MissionStatusEnum MissionStatusEnum);
	
	public List<Mission> findByMissionStatusAndUser(MissionStatusEnum MissionStatusEnum, User user);
	
	public List<Mission> findByKindVersionAndUser(KindVersion kindVersion, User user);
	

}
