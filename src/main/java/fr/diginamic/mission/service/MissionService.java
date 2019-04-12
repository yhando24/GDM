package fr.diginamic.mission.service;

import java.math.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.WorkBook.entities.MissionExcel;
import fr.diginamic.WorkBook.service.SheetParser;
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
		if (missionRepository.findVeriChevauchement(missionDTO.getEndDate(), missionDTO.getStartDate()).size() != 0) {
			throw new ControllerMissionException("Probleme de chevauchement de date de mission");
		}

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

	public void exportExcel() {
		 SheetParser sp = new SheetParser();
		 
		 List<MissionExcel> missionsExcel = new ArrayList<>(); 
		 
		 List<MissionDTO>  missionsDTO = mapperMissionService.toDTOs(missionRepository.findAll());
		 
		 
		System.out.println("dans le export");
		
		//transformation des missionsDTO EN missionsExcel
		 for (MissionDTO mdto : missionsDTO) {
			 MissionExcel me = new MissionExcel(mdto.getId(),mdto.getStartDate(),mdto.getEndDate(),mdto.getDepartureCity(), mdto.getArrivalCity(),
					 mdto.getMissionStatus().toString(), mdto.getTransportEnum().toString(), mdto.getKind().getName(),mdto.getUser().getEmail());
			 if(mdto.getPrime() == null) {
				 me.setPrime("Pas de prime");
			 }else {
				 me.setPrime(mdto.getPrime().toString());
			 }
			 if(mdto.getAmountOfBill() == null) {
				 me.setAmountOfBill("Non facturée");
			 }else {
				 me.setAmountOfBill(mdto.getAmountOfBill().toString());
			 }
			 missionsExcel.add(me);
				System.out.println("je rajoute :"+ me.toString());
		 }
		 
		 final String OUTPUT_FILE = "C:\\Users\\formation\\Desktop\\liste.xlsx";
		 OutputStream out = null;
		try {
			out = new FileOutputStream(OUTPUT_FILE);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 try {
			sp.createXLS(out, "Liste des missions", MissionExcel.class, missionsExcel);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
