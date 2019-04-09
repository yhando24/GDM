package fr.diginamic.expenseaccount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.expenseaccount.model.ExpenseAccountDTO;
import fr.diginamic.expenseaccount.service.ExpenseAccountService;


@CrossOrigin
@RestController()
@RequestMapping("/expense-accounts")
public class ExpenseAccountController {

	@Autowired
	private ExpenseAccountService expenseAccountService;
	
	@GetMapping
	public List <ExpenseAccountDTO> findAll(){
		return this.expenseAccountService.findAll();
	}
	
}
