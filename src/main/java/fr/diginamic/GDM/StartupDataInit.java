package fr.diginamic.GDM;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import fr.diginamic.expenseaccount.model.ExpenseAccount;
import fr.diginamic.expenseaccount.model.ExpenseAccountStatusEnum;
import fr.diginamic.expenseaccount.repository.ExpenseAccountRepository;
import fr.diginamic.kind.model.Kind;
import fr.diginamic.kind.service.KindService;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.model.MissionStatusEnum;
import fr.diginamic.mission.model.TransportEnum;
import fr.diginamic.mission.repository.MissionRepository;
import fr.diginamic.user.model.RoleEnum;
import fr.diginamic.user.model.User;
import fr.diginamic.user.repository.UserRepository;

@Component
public class StartupDataInit {

	@Autowired
	MissionRepository missionRepository;

	@Autowired
	fr.diginamic.kind.repository.KindRepository kindRepository;

	@Autowired
	ExpenseAccountRepository expenseAccountRepository;

	@Autowired
	KindService ks;

	@Autowired
	UserRepository userRepository;

	@EventListener(ContextRefreshedEvent.class)
	@org.springframework.transaction.annotation.Transactional
	public void init() {

		// Ajout user
		User u = new User(1L, "ADMIN", "Administrateur", "$2a$10$xtCYOPKjj4yx3OUskgTANem5HXneF.yLOkeQ7Iu7JX.KY58j3nEn6",
				"admin@admin.fr", RoleEnum.ADMIN);
		userRepository.save(u);
		
		User u2 = new User(2L, "MANAGER", "Manager", "$2a$10$xtCYOPKjj4yx3OUskgTANem5HXneF.yLOkeQ7Iu7JX.KY58j3nEn6",
				"manager@manager.fr", RoleEnum.MANAGER); 
		userRepository.save(u2);
		
		User u3 = new User(3L, "USER", "User",
				"$2a$10$xtCYOPKjj4yx3OUskgTANem5HXneF.yLOkeQ7Iu7JX.KY58j3nEn6", "user@user.fr", RoleEnum.USER);
		userRepository.save(u3);

		Kind k = new Kind("Formation", 405.05f, 10f, LocalDateTime.of(2019,Month.JANUARY,3,0,0,0), true, true, 10.1f, true, true);
		Kind k1 = new Kind("Expertise technique", 603.5f, 0f, LocalDateTime.of(2019,Month.FEBRUARY,3,0,0,0), false, false, 50f, true, true);
		Kind k2 = new Kind("Conseil", 707.1f, 15f, LocalDateTime.of(2019,Month.FEBRUARY,10,0,0,0), true, true, 50f, false, true);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		Mission m = new Mission(LocalDate.now(), LocalDate.now().plusDays(5), "paris", "madrid", 12f,

				MissionStatusEnum.INITIAL, TransportEnum.BUS, k, u, 150.01f);

				//MissionStatusEnum.VALIDE, TransportEnum.BUS, k, u, 150.01f);

		
		Mission m2 = new Mission(LocalDate.now().plusDays(51), LocalDate.now().plusDays(55), "MARSEILLE", "TATAOUINE", 12f,
				MissionStatusEnum.EN_ATTENTE, TransportEnum.BUS, k2, u2, 150.01f);
		Mission m3 = new Mission(LocalDate.now().plusDays(56), LocalDate.now().plusDays(58), "TUNIS", "PARIS", 12f,
				MissionStatusEnum.VALIDE, TransportEnum.BUS, k1, u, 150.01f);
		Mission m4 = new Mission(LocalDate.of(2019, Month.JANUARY, 3), LocalDate.of(2019, Month.JANUARY, 6), "paris",
				"madrid", null,MissionStatusEnum.VALIDE, TransportEnum.BUS, k, u3, 150.01f);
		Mission m5 = new Mission(LocalDate.of(2019, Month.FEBRUARY, 15), LocalDate.of(2019, Month.FEBRUARY, 25), "nantes",
				"montpellier", 150f,MissionStatusEnum.VALIDE, TransportEnum.AVION, k2, u2, 150.01f);
		Mission m6 = new Mission(LocalDate.of(2019, Month.FEBRUARY, 15), LocalDate.of(2019, Month.FEBRUARY, 25), "Saint-Jean",
				"montpellier", 250f,MissionStatusEnum.VALIDE, TransportEnum.AVION, k1, u3, 150.01f);
		Mission m7 = new Mission(LocalDate.of(2019, Month.JANUARY, 10), LocalDate.of(2019, Month.JANUARY, 13), "nantes",
				"montpellier", 350f,MissionStatusEnum.VALIDE, TransportEnum.AVION, k1, u3, 150.01f);
		Mission m8 = new Mission(LocalDate.of(2019, Month.MARCH, 18), LocalDate.of(2019, Month.MARCH, 23), "nantes",
				"montpellier", 400f,MissionStatusEnum.VALIDE, TransportEnum.AVION, k1, u3, 150.01f);
		

		ExpenseAccount ea = new ExpenseAccount(1L,"Transport" ,LocalDate.now(), 1250F, ExpenseAccountStatusEnum.INITIAL, m);
		m.addexpenseAccounts(ea);
		
		Mission m21 = new Mission(LocalDate.now().plusDays(20), LocalDate.now().plusDays(25), "londres", "boston", 12f,
				MissionStatusEnum.EN_ATTENTE, TransportEnum.VELO, k, u, 150.01f);
		ExpenseAccount ea2 = new ExpenseAccount(2L,"Hebergement", LocalDate.now(), 1250F, ExpenseAccountStatusEnum.ATTENTE, m21);
		m21.addexpenseAccounts(ea2);
		
		Mission m31 = new Mission(LocalDate.now().plusDays(35), LocalDate.now().plusDays(45), "montpellier", "nimes", 12f,
				MissionStatusEnum.EN_ATTENTE, TransportEnum.HELICOPTERE, k, u, 150.01f);
		ExpenseAccount ea3 = new ExpenseAccount(3L,"Transport", LocalDate.now(), 1250F, ExpenseAccountStatusEnum.ATTENTE, m31);
		m31.addexpenseAccounts(ea3);

		

		kindRepository.save(k);
		kindRepository.save(k1);
		kindRepository.save(k2);
		
		missionRepository.save(m);
		missionRepository.save(m2);
		missionRepository.save(m3);
		missionRepository.save(m4);
		missionRepository.save(m5);
		missionRepository.save(m7);
		missionRepository.save(m6);
		missionRepository.save(m8);

		missionRepository.save(m21);
		missionRepository.save(m31);


		expenseAccountRepository.save(ea);
		expenseAccountRepository.save(ea2);
		expenseAccountRepository.save(ea3);

		// System.out.println(kindVersionRepository.findTopByNameOrderByVersionDesc(kv2.getName()).getVersion());

	}
}
