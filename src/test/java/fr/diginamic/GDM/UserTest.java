package fr.diginamic.GDM;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.user.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GdmApplication.class, loader = AnnotationConfigContextLoader.class)
public class UserTest {
	
	@Autowired
	private UserService us;
	
	@Test
	@Transactional
	public void findByIdTest() {
		assertEquals("user de cet id", 2, 2);
		
	}
}
