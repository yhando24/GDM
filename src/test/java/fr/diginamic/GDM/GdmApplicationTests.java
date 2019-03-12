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

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GdmApplication.class, loader = AnnotationConfigContextLoader.class)
public class GdmApplicationTests {
	
	@Autowired
	private MissionRepository mr;
	
	@Test
	@Transactional
	public void contextLoads() {
		System.err.println("ICICIICCICCCCCCCC" + mr.findByDepartureCity("montpellier"));
		
	}

}
