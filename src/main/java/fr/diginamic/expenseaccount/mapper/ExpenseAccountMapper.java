package fr.diginamic.expenseaccount.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.diginamic.expenseaccount.model.ExpenseAccount;
import fr.diginamic.expenseaccount.model.ExpenseAccountDTO;

@Mapper
public interface ExpenseAccountMapper {

	ExpenseAccountMapper INSTANCE = Mappers.getMapper(ExpenseAccountMapper.class);

	 ExpenseAccountDTO expenseAccountToExpenseAccountDTO(ExpenseAccount expenseAccount);

}
