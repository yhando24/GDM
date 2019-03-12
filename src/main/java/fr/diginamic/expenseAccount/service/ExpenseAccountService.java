package fr.diginamic.expenseAccount.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.expenseAccount.model.ExpenseAccount;
import fr.diginamic.expenseAccount.model.ExpenseAccountStatusEnum;
import fr.diginamic.expenseAccount.repository.ExpenseAccountRepository;
import fr.diginamic.mission.model.Mission;

@Service
public class ExpenseAccountService {
	
	@Autowired
	ExpenseAccountRepository expenseAccountRepository;
	
	public List<ExpenseAccount> findAll(){
		return expenseAccountRepository.findAll();
	}
	
	public  List<ExpenseAccount> findByMission(Mission mission){
		
		return  expenseAccountRepository.findByMission(mission);
	}
	
	public List<ExpenseAccount> findByStatus(ExpenseAccountStatusEnum status){
		return expenseAccountRepository.findByStatus(status);
	}
	public List<ExpenseAccount> findByUser(Mission mission){
		return expenseAccountRepository.findByUser(mission.getUser());
	}
}
