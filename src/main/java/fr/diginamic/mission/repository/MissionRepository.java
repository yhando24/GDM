package fr.diginamic.mission.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.kindVersion.model.KindVersion;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.model.MissionStatusEnum;
import fr.diginamic.user.model.User;

@Repository
public interface MissionRepository extends CrudRepository<Mission, Long> {

	// create
	public Mission save(Mission mission);

	// read
	
	public  Optional<Mission>findById(Long id);
	
	
	public List<Mission> findByUser(User user);

	public List<Mission> findByDepartureCity(String departureCity);

	public List<Mission> findByArrivalCity(String arrivalCity);

	public List<Mission> findByMissionStatus(MissionStatusEnum MissionStatusEnum);

	public List<Mission> findByMissionStatusAndUser(MissionStatusEnum MissionStatusEnum, User user);

	public List<Mission> findByKindVersionAndUser(KindVersion kindVersion, User user);

	public List<Mission> findAll();
	
	@Query("SELECT m FROM Mission m WHERE m.startDate>?1 AND m.endDate<?2 ")
	public List<Mission> findVeriChevauchement(LocalDate dateStart, LocalDate dateEnd);
	
	// delete
	public void delete(Mission mission);

}
