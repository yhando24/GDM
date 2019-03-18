package fr.diginamic.GDM;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.user.model.RoleEnum;
import fr.diginamic.user.model.User;
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
		Optional<User> u = us.findById(2L);
		assertEquals("user de cet id", u.get(), u.get());
	}
	
	@Test
	@Transactional
	public void finbByLastNameTest() {
		List<User> u = us.findByLastName("Delteil");
		assertNotNull(u);
		assertEquals("user name", "Delteil", "Delteil");
		assertEquals("user de cet id",1, 1);
		assertEquals(u.size(), 0);	
		System.out.println(us.findByLastName("Delteil"));
		}
	
	@Test
	@Transactional
	public void finbByFirstNameTest() {
		List<User> u = us.findByFirstName("Yoann");
		assertNotNull(u);
		assertEquals("user name", "Yoann", "Yoann");
		assertEquals("user de cet id",1, 1);
		assertEquals(u.size(), 1);	
		}
	
	@Test
	@Transactional
	public void findByRole() {
		List<User> u = us.findByRole(RoleEnum.MANAGER);
		assertNotNull(u);
		assertEquals("role name", "MANAGER", "MANAGER");
		assertEquals("user de cet id",2 ,2);
		assertEquals(u.size(), 1);	
		}
}
