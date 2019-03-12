package fr.diginamic.GDM;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.diginamic.expenseAccount.model.ExpenseAccountStatusEnum;
import fr.diginamic.expenseAccount.repository.ExpenseAccountRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GdmApplication.class, loader = AnnotationConfigContextLoader.class)
public class ExpenseAccountTest {

	
	@Autowired
	private ExpenseAccountRepository expenseAccountRepository;
	
	@Test
	public void findAllTest() {
		System.err.println("je suis dans find all"+expenseAccountRepository.findAll());
		
	}
	
	@Test
	public void findByStatusTest() {
		System.err.println("je suis dans status"+ expenseAccountRepository.findByStatus(ExpenseAccountStatusEnum.VALIDEE));
		
	}
}
