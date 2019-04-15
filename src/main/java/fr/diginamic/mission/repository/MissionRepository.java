package fr.diginamic.mission.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.kind.model.Kind;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.model.MissionDTO;
import fr.diginamic.mission.model.MissionStatusEnum;
import fr.diginamic.user.model.User;

@Repository
public interface MissionRepository extends CrudRepository<Mission, Long> {

	// create
	public Mission save(Mission mission);

	// read
	public Optional<Mission> findById(Long id);

	public List<Mission> findByUser(User user);
	
	public List<Mission> findByUserId(Long idUser);

	public List<Mission> findByDepartureCity(String departureCity);

	public List<Mission> findByArrivalCity(String arrivalCity);

	public List<Mission> findByMissionStatus(MissionStatusEnum missionStatusEnum);

	public List<Mission> findByMissionStatusAndUser(MissionStatusEnum MissionStatusEnum, User user);

	public List<Mission> findByKindAndUser(Kind kind, User user);

	public List<Mission> findAll();

	@Query("SELECT COUNT(*) FROM Mission m WHERE (m.startDate>?1 AND ?1<m.endDate) OR (m.startDate>?2 AND ?2<m.endDate) "
			+ "OR (?1<m.startDate AND ?2>m.endDate)")
	public Long findVeriChevauchement(LocalDate dateStart, LocalDate dateEnd);
	
	@Query("SELECT m FROM Mission m WHERE m.endDate<=?1 AND m.missionStatus='VALIDE' AND m.prime=null")
	public List<Mission> findMissionByEndDateAndMissionStatusValideAndPrimeNull(LocalDate endDate);
	
	@Query("SELECT m FROM Mission m WHERE m.prime IS NOT NULL AND m.missionStatus='VALIDE' AND m.user.id=?1")
	public List<Mission> findMissionByUserIdAndPrimeNotNull(Long idUser);

	// delete
	public void delete(Mission mission);

}
