package fr.diginamic.GDM;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.diginamic.kind.model.Kind;
import fr.diginamic.kind.repository.KindRepository;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.model.MissionStatusEnum;
import fr.diginamic.mission.model.TransportEnum;
import fr.diginamic.mission.repository.MissionRepository;
import fr.diginamic.user.model.RoleEnum;
import fr.diginamic.user.model.User;
import fr.diginamic.user.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GdmApplication.class, loader = AnnotationConfigContextLoader.class)
public class MissionRepositoryTest {

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	KindRepository kindRepository;

	@Before
	public void init() {

		User u = new User(1L, "Plop1", "Plopeur1", "$2a$10$xtCYOPKjj4yx3OUskgTANem5HXneF.yLOkeQ7Iu7JX.KY58j3nEn6",
				"admin@admin.fr", RoleEnum.ADMIN);

		userRepository.save(u);

		Kind k1 = new Kind("Formation", 12.05f, 10f, LocalDateTime.now(), true, true, 10.1f, true, true);
		Kind k2 = new Kind("Formation", 12.05f, 10f, LocalDateTime.now(), true, false, 10.1f, true, true);

		kindRepository.save(k1);
		kindRepository.save(k2);

		Mission m1 = new Mission(LocalDate.of(2019, Month.JANUARY, 3), LocalDate.of(2019, Month.JANUARY, 6), "paris",
				"madrid", 12f,MissionStatusEnum.VALIDE, TransportEnum.BUS, k1, u, 150.01f);// pas ok

		Mission m2 = new Mission(LocalDate.of(2019, Month.JANUARY, 3), LocalDate.of(2019, Month.JANUARY, 6), "paris",
				"madrid", null,MissionStatusEnum.VALIDE, TransportEnum.BUS, k1, u, 150.01f);// ok

		Mission m3 = new Mission(LocalDate.of(2019, Month.JANUARY, 3), LocalDate.of(2019, Month.JANUARY, 6), "paris",
				"madrid", null,MissionStatusEnum.EN_ATTENTE, TransportEnum.BUS, k1, u, 150.01f);// pas ok

		Mission m4 = new Mission(LocalDate.of(2019, Month.JANUARY, 3), LocalDate.now(), "paris", "madrid", null,
				MissionStatusEnum.VALIDE, TransportEnum.BUS, k1, u, 150.01f);// pas ok

		Mission m5 = new Mission(LocalDate.of(2019, Month.JANUARY, 3), LocalDate.of(2019, Month.JANUARY, 6), "paris",
				"madrid", null,MissionStatusEnum.VALIDE, TransportEnum.BUS, k2, u, 150.01f);// ok
		
		missionRepository.save(m1);
		missionRepository.save(m2);
		missionRepository.save(m3);
		missionRepository.save(m4);
		missionRepository.save(m5);
	}

	@Test
	@Transactional
	public void findMissionByEndDateAndMissionStatusValideAndPrimeNull() {
		List<Mission> missions = missionRepository
				.findMissionByEndDateAndMissionStatusValideAndPrimeNull(LocalDate.of(2019, Month.APRIL, 3));
		assertEquals(2, missions.size());

	}

}
