package fr.diginamic.GDM;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.repository.MissionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GdmApplication.class, loader = AnnotationConfigContextLoader.class)
public class MissionTest {
	
	@Autowired
	private MissionRepository mr;
	
	
	@Before 
	public void init() {
		Mission mission = new Mission();
		mission.setAmountOfBill(12.05f);
		mission.setArrivalCity("marseille");
		mission.setDepartureCity("paris");
		mission.setEndDate(LocalDate.of(2019, 7, 25));
		
	}
	

	@Test
	@Transactional
	public void findByDepartureCity() {
		System.err.println("ICICIICCICCCCCCCC" + mr.findByDepartureCity("montpellier"));
	}

}
