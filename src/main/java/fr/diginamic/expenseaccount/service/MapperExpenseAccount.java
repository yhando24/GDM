package fr.diginamic.expenseaccount.service;

import java.util.List;

import org.mapstruct.Mapper;

import fr.diginamic.expenseaccount.model.ExpenseAccount;
import fr.diginamic.expenseaccount.model.ExpenseAccountDTO;

@Mapper(componentModel = "spring", uses = {})
public interface MapperExpenseAccount {

	ExpenseAccountDTO toDTO (ExpenseAccount expenseAccount);
	
	List <ExpenseAccountDTO> toDTOs (List<ExpenseAccount> expenseAccount);
	
	ExpenseAccount toEntity (ExpenseAccountDTO expenseAccountDTO);
	
	List <ExpenseAccount> toEntitys (List <ExpenseAccountDTO> expenseAccountDTO);

}
