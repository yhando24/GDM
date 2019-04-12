package fr.diginamic.expenseaccount.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.expenseaccount.model.ExpenseAccount;
import fr.diginamic.expenseaccount.model.ExpenseAccountStatusEnum;
import fr.diginamic.mission.model.Mission;

@Repository
public interface ExpenseAccountRepository extends CrudRepository<ExpenseAccount, Long> {

	public List<ExpenseAccount> findByMission(Mission mission);

	public List<ExpenseAccount> findByStatus(ExpenseAccountStatusEnum status);

	public List<ExpenseAccount> findAll();

	public Optional<ExpenseAccount> findById(Long id);

	public void deleteById(Long id);

	void delete(ExpenseAccount expenseAccount);

	public List<ExpenseAccount> findByMissionId(Long idMission);
}
