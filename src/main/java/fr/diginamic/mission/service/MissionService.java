package fr.diginamic.mission.service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.kind.model.Kind;
import fr.diginamic.kind.service.KindService;
import fr.diginamic.mission.exception.ControllerMissionException;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.model.MissionDTO;
import fr.diginamic.mission.model.MissionStatusEnum;
import fr.diginamic.mission.model.TransportEnum;
import fr.diginamic.mission.repository.MissionRepository;
import fr.diginamic.security.SecurityUtils;
import fr.diginamic.user.exception.ControllerUserException;
import fr.diginamic.user.model.User;

@Service
public class MissionService {

	@Autowired
	private MissionRepository missionRepository;

	@Autowired
	private MapperMissionService mapperMissionService;

	@Autowired
	private SecurityUtils securityUtils;

	@Autowired
	private KindService kindService;

	// create
	public MissionDTO save(MissionDTO missionDTO) throws ControllerMissionException {
		missionDTO.setMissionStatus(MissionStatusEnum.INITIAL);
		
		if (missionDTO.getEndDate().isBefore(missionDTO.getStartDate())) {
			throw new ControllerMissionException("La date de fin est avant la date de debut");
		}
		if (missionDTO.getStartDate().isBefore(LocalDate.now().plusDays(1))) {
			throw new ControllerMissionException("Une mission doit commencer à J+1");
		}
		if (missionDTO.getTransportEnum().equals(TransportEnum.AVION)
				&& !missionDTO.getStartDate().isAfter(LocalDate.now().plusDays(7))) {
			throw new ControllerMissionException(
					"Si une mission se deroule grace à l'utilisation d'un avion, elle doit commencer au moin 7 jours apres la date d'aujourd'hui");
		}

//		if (missionRepository.findVeriChevauchement(missionDTO.getEndDate(), missionDTO.getStartDate())>0) {
//			throw new ControllerMissionException("Probleme de chevauchement de date de mission");
//		}

		Mission mission = mapperMissionService.toEntity(missionDTO);
		mission.setUser(securityUtils.getConnectedUser());
		return mapperMissionService.toDTO(missionRepository.save(mission));
	}

	
	// update
	public MissionDTO update(Mission mission) {
		return mapperMissionService.toDTO(missionRepository.save(mission));
	}

	// read
	private List<Mission> findByDepartureCity(String city) {
		return missionRepository.findByDepartureCity(city);
	}

	public MissionDTO findById(Long id) {
		Optional<Mission> missionOptional = missionRepository.findById(id);
		if(missionOptional.isPresent()) {
			return this.mapperMissionService.toDTO(missionOptional.get());
		}
		return null;
	}

	public List<MissionDTO> findByUser() {
		return mapperMissionService.toDTOs(missionRepository.findByUserId(securityUtils.getConnectedUser().getId()));

	}

	public List<MissionDTO> findByUserIdAndPrimeNotNull(){
		return mapperMissionService.toDTOs(missionRepository.findMissionByUserIdAndPrimeNotNull(securityUtils.getConnectedUser().getId()));
	}
	
	public List<Mission> findByArrivalCity(String arrivalCity) {
		return missionRepository.findByArrivalCity(arrivalCity);
	}

	public List<MissionDTO> findByMissionStatus(MissionStatusEnum status) {
		return mapperMissionService.toDTOs(missionRepository.findByMissionStatus(status));
	}

	public List<Mission> findByMissionStatusAndUser(MissionStatusEnum MissionStatusEnum, User user) {
		return missionRepository.findByMissionStatusAndUser(MissionStatusEnum, user);
	}

	public List<Mission> findByKindAndUser(Kind kind, User user) {
		return missionRepository.findByKindAndUser(kind, user);
	}

	public List<MissionDTO> findAll() {
		return mapperMissionService.toDTOs(missionRepository.findAll());
	}

	// delete
	public void deleteById(Long id) {
		Mission m = missionRepository.findById(id)
				.orElseThrow(() -> new ControllerUserException("Cette mission n'existe pas"));
		if (m.getMissionStatus().getName().equals("Validé")) {
			throw new ControllerUserException(
					"Cette Mission ne peut pas etre supprimé car la mission a deja ete validé");
		} else if (m.getExpenseAccounts().size() > 0) {
			throw new ControllerUserException(
					"Cette Mission ne peut pas etre supprimé car la mission a deja des notes de frais");
		} else {
			missionRepository.deleteById(id);
		}

	}

	@Scheduled(cron = "0 0 6 * * *") // tous les jours à 6h //(cron="0 * * * * *") -> pour test toutes les minutes
	public void changeStatusByNight() {

		List<Mission> missions = missionRepository.findByMissionStatus(MissionStatusEnum.INITIAL);

		if (missions.isEmpty()) {
			System.out.println("Pas de modif");
		} else {
			for (Mission mission : missions) {
				mission.setMissionStatus(MissionStatusEnum.EN_ATTENTE);
				update(mission);
			}
		}

	}

	@Scheduled(cron = "0 0 0 15 * *") // tous les 15 du mois
	@Transactional
	public void bonusCalcul() {
		// récupération des missions validées avec prime null
		List<Mission> missions = missionRepository
				.findMissionByEndDateAndMissionStatusValideAndPrimeNull(LocalDate.now());

		if (missions.isEmpty()) {
			System.out.println("Pas de primes à calculer");
		} else {
			// Pour chaque mission, on récupére l'id de la nature, avec cette id
			// on récupère la version de nature correspondant à la date de création de la
			// mission
			for (Mission mission : missions) {
				Long id = mission.getKind().getId();
				Kind kind = kindService.findKindVersionByIdAndTimestamp(id,
						ZonedDateTime.now().toInstant().toEpochMilli());

				// On teste si bonus est à true puis on calcule le nombre de jours de mission,
				// la prime et on sauvegarde en bdd
				if (kind.getBonus() == true) {
					Long days = ChronoUnit.DAYS.between(mission.getStartDate(), mission.getEndDate());
					Float prime = days * kind.getAdr() * kind.getBonusPercentage() / 100;
					mission.setPrime(prime);
					update(mission);
				}

			}

		}

	}

}
