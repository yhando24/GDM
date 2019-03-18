package fr.diginamic.expenseAccount.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.diginamic.expenseAccount.model.ExpenseAccount;
import fr.diginamic.expenseAccount.model.ExpenseAccountDTO;
import fr.diginamic.user.mapper.UserMapper;
import fr.diginamic.user.model.User;
import fr.diginamic.user.model.UserDTO;

@Mapper
public class ExpenseAccountMapper {

	ExpenseAccountMapper INSTANCE = Mappers.getMapper(ExpenseAccountMapper.class);
	
	//ExpenseAccountDTO expenseAccountToExpenseAccountDTO(ExpenseAccount e);

}
