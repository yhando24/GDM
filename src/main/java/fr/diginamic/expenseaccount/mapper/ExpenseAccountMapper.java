package fr.diginamic.expenseaccount.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public class ExpenseAccountMapper {

	ExpenseAccountMapper INSTANCE = Mappers.getMapper(ExpenseAccountMapper.class);

	// ExpenseAccountDTO expenseAccountToExpenseAccountDTO(ExpenseAccount e);

}
