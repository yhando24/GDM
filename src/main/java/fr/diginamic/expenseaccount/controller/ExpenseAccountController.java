package fr.diginamic.expenseaccount.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.expenseaccount.model.ExpenseAccountDTO;
import fr.diginamic.expenseaccount.repository.ExpenseAccountRepository;
import fr.diginamic.expenseaccount.service.ExpenseAccountService;
import fr.diginamic.expenseaccount.service.MapperExpenseAccount;


@CrossOrigin
@RestController()
@RequestMapping("/expense-accounts")
public class ExpenseAccountController {

	@Autowired
	private ExpenseAccountService expenseAccountService;
	
	@Autowired
	private MapperExpenseAccount mapperExpenseAccount;
	
	@GetMapping
	public List <ExpenseAccountDTO> findAll(){
		return this.expenseAccountService.findAll();
	}
	

	@GetMapping("/{id}")
	public List<ExpenseAccountDTO> findbyMission(Long idMission) {
		return mapperExpenseAccount.toDTOs(expenseAccountService.saveByMissionId(idMission));
	}

	@Transactional
	@PostMapping
	public ExpenseAccountDTO saveExpenseAccount(@RequestBody ExpenseAccountDTO k) {
		System.out.println(k.getMission().getArrivalCity());
		return expenseAccountService.save(k);
	}
	
	@PatchMapping
	public ExpenseAccountDTO update(@RequestBody ExpenseAccountDTO k) {
		return this.expenseAccountService.save(k);
	}
	
	@DeleteMapping("/delete-expense-account/{id}")
	public void deleteExpenseAccount(@PathVariable long id) {
		this.expenseAccountService.deleteExpenseAccount(id);
	}
}
