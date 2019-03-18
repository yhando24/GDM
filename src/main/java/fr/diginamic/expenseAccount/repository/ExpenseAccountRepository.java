package fr.diginamic.expenseAccount.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.expenseAccount.model.ExpenseAccount;
import fr.diginamic.expenseAccount.model.ExpenseAccountStatusEnum;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.user.model.User;

@Repository
public interface ExpenseAccountRepository extends CrudRepository<ExpenseAccount, Long>{
	
	public List<ExpenseAccount> findByMission(Mission mission);
	public List<ExpenseAccount> findByStatus(ExpenseAccountStatusEnum status);
//	public List<ExpenseAccount> findByUser(User user);
	public List<ExpenseAccount> findAll();
	public ExpenseAccount save(ExpenseAccount expenseAccount);
	public Optional<ExpenseAccount> findById(Long id);
	public boolean existsById(Long id);
	void delete(ExpenseAccount expenseAccount);
}
