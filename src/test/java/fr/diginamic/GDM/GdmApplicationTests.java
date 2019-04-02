package fr.diginamic.GDM;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.diginamic.kindversion.model.KindVersion;
import fr.diginamic.kindversion.repository.KindVersionRepository;
import fr.diginamic.mission.exception.ErrorLogigDateMission;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.model.MissionStatusEnum;
import fr.diginamic.mission.model.TransportEnum;
import fr.diginamic.mission.repository.MissionRepository;
import fr.diginamic.mission.service.MissionService;
import fr.diginamic.user.model.User;
import fr.diginamic.user.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GdmApplication.class, loader = AnnotationConfigContextLoader.class)
public class GdmApplicationTests {
	
	@Autowired
	private MissionRepository mr;

	@Autowired
	private MissionService ms;
	
	@Autowired
	private UserService us;
	
	@Autowired
	private KindVersionRepository kindVersionRepository;
//	
//	@Test
//	@Transactional
//	public void contextLoads() {
//		System.err.println("ICICIICCICCCCCCCC" + mr.findByDepartureCity("montpellier"));
//		
//	}
//	
	
//	@Test
//	@Transactional
//	public void findOptionnalMission() {
//		System.err.println(ms.findById(5L));
//		
//	}
	
//	@Test
//	@Transactional
//	public void findOptionnalMission() {
//		System.err.println(ms.findAll());
//		
//	}
	
//	@Test
//	@Transactional
//	public void saveMission() {
//		
//		Optional<User> user = us.findById(1L);
//		KindVersion kindVersion = kindVersionRepository.findById(2l).get();
//		Mission mission = new Mission();
//		mission.setAmountOfBill(20.05f);
//		mission.setArrivalCity("paris");
//		mission.setDepartureCity("marseille");
//		mission.setStartDate(LocalDate.of(2019, 7, 25));
//		mission.setEndDate(LocalDate.of(2018, 8, 25));
//		mission.setKindVersion(kindVersion);
//		mission.setMissionStatus(MissionStatusEnum.VALIDE);
//		mission.setPrime(11.11f);
//		mission.setTransportEnum(TransportEnum.BUS);
//		mission.setUser(us.findById(1L).get());
//		
//
//		try {
//			ms.save(mission);
//		} catch (ErrorLogigDateMission e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.err.println("MESSAGE : " + e.getMessage());
//		}
//	}

}
