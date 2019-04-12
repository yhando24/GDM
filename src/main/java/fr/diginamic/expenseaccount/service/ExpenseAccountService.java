package fr.diginamic.expenseaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.expenseaccount.exception.ExpenseAccountException;
import fr.diginamic.expenseaccount.model.ExpenseAccount;
import fr.diginamic.expenseaccount.model.ExpenseAccountDTO;
import fr.diginamic.expenseaccount.model.ExpenseAccountStatusEnum;
import fr.diginamic.expenseaccount.repository.ExpenseAccountRepository;
import fr.diginamic.kind.exception.KindException;
import fr.diginamic.kind.model.KindDTO;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.service.MissionService;
import fr.diginamic.security.SecurityUtils;
import fr.diginamic.user.exception.ControllerUserException;
import fr.diginamic.user.mapper.UserMapper;
import fr.diginamic.user.model.User;
import fr.diginamic.user.model.UserDTO;

@Service
public class ExpenseAccountService {
	
	@Autowired
	ExpenseAccountRepository expenseAccountRepository;
	
	@Autowired
	private MapperExpenseAccount mapperExpenseAccount;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SecurityUtils securityUtils;

	
	@Autowired
	private MissionService missionService;
	
	public List<ExpenseAccountDTO> findAll(){
		return mapperExpenseAccount.toDTOs(expenseAccountRepository.findAll());
	}
	
	public  List<ExpenseAccountDTO> findByMission(Mission mission){
		return  mapperExpenseAccount.toDTOs(expenseAccountRepository.findByMission(mission));
	}
	
	public List<ExpenseAccountDTO> findByStatus(ExpenseAccountStatusEnum status){
		return mapperExpenseAccount.toDTOs(expenseAccountRepository.findByStatus(status));
	}
	
	public ExpenseAccountDTO save(ExpenseAccountDTO k) {
		k.setStatus(ExpenseAccountStatusEnum.INITIAL);
		k.getMission().setUser(userMapper.userToUserDTO(securityUtils.getConnectedUser()));
		return mapperExpenseAccount.toDTO(expenseAccountRepository.save(mapperExpenseAccount.toEntity(k)));
	}
	
	public void deleteExpenseAccount(Long id) {

		this.expenseAccountRepository.findById(id).orElseThrow(() -> new ExpenseAccountException("Cette nature n'existe pas"));

		expenseAccountRepository.deleteById(id);
	}
	
	public ExpenseAccountDTO findById(Long id) throws ExpenseAccountException {
		Optional<ExpenseAccount> expenseAccountOptional = expenseAccountRepository.findById(id);
		if (expenseAccountOptional.isPresent()) {
			return mapperExpenseAccount.toDTO(expenseAccountOptional.get());
		} else {
			throw new ExpenseAccountException("L'id correpond à aucun frais");
		}
	}

	public List<ExpenseAccount> saveByMissionId(Long idMission) {
		// TODO Auto-generated method stub
	return expenseAccountRepository.findByMissionId(idMission);
	}

}
