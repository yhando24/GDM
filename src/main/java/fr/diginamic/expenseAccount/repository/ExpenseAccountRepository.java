package fr.diginamic.expenseAccount.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.expenseAccount.model.ExpenseAccount;
import fr.diginamic.expenseAccount.model.ExpenseAccountStatusEnum;
import fr.diginamic.mission.model.Mission;

@Repository
public interface ExpenseAccountRepository extends CrudRepository<ExpenseAccount, Long>{
	
	List<ExpenseAccount> findByMission(Mission mission);
	List<ExpenseAccount> findByStatus(ExpenseAccountStatusEnum status);
	
}
