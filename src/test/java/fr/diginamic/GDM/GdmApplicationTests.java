package fr.diginamic.GDM;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.diginamic.mission.repository.MissionRepository;
import fr.diginamic.mission.service.MissionService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GdmApplication.class, loader = AnnotationConfigContextLoader.class)
public class GdmApplicationTests {
	
	@Autowired
	private MissionRepository mr;
	
	@Autowired
	MissionService ms;
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
	
	@Test
	@Transactional
	public void findOptionnalMission() {
		System.err.println(ms.findAll());
		
	}
	
	@Test
	@Transactional
//	public void saveMission() {
//
//		System.err.println(ms.save(mission));
//	}

}
